package com.api.doarmais.services;

import com.api.doarmais.dtos.request.CriarUsuarioRequestDto;
import com.api.doarmais.dtos.request.RequestDto;
import com.api.doarmais.dtos.response.ResponseDto;
import com.api.doarmais.exceptions.AccountNotVerifiedByAdm;
import com.api.doarmais.exceptions.BlockedAccount;
import com.api.doarmais.exceptions.EmailAccountNotVerified;
import com.api.doarmais.exceptions.SuspendedAccount;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private JwtService jwtService;

  @Autowired private AuthenticationManager authenticationManager;

  public ResponseDto register(CriarUsuarioRequestDto request) {
    var user =
        UsuarioModel.builder()
            .nome(request.getNome())
            .telefone(request.getTelefone())
            .documento(request.getDocumento())
            .email(request.getEmail())
            .senha(passwordEncoder.encode(request.getSenha()))
            .cargo("USER")
            .build();
    usuarioRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return ResponseDto.builder().token(jwtToken).build();
  }

  public ResponseDto authenticate(RequestDto request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
    var user = usuarioRepository.findByEmail(request.getEmail());

    if (user.getSituacaoModel().getId().equals(SituacaoModel.CONTA_SEM_EMAIL_VERIFICADO))
      throw new EmailAccountNotVerified("Seu email ainda não foi validado");

    if (user.getSituacaoModel().getId().equals(SituacaoModel.CONTA_SEM_APROVACAO_DO_ADM))
      throw new AccountNotVerifiedByAdm("O administrador ainda não aprovou sua conta");

    if (user.getSituacaoModel().getId().equals(SituacaoModel.CONTA_SUSPENSA))
      throw new SuspendedAccount("Essa conta não pode mais ser utilizada pois foi excluída");

    if (user.getSituacaoModel().getId().equals(SituacaoModel.CONTA_BLOQUEADA))
      throw new BlockedAccount("Sua conta foi bloqueada por determinado tempo");

    var jwtToken = jwtService.generateToken(user);
    return ResponseDto.builder().token(jwtToken).build();
  }
}
