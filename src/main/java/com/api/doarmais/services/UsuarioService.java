package com.api.doarmais.services;

import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TipoUsuarioModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.UsuarioRepository;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService {

  @Autowired private UsuarioRepository usuarioRepository;

  @Autowired private TipoUsuarioService tipoUsuarioService;

  public UsuarioModel gravar(UsuarioModel usuarioModel) {
    return usuarioRepository.save(usuarioModel);
  }

  public Optional<UsuarioModel> buscarUsuario(UsuarioModel usuarioModel) {
    return usuarioRepository.findById(usuarioModel.getId());
  }

  public Optional<UsuarioModel> buscarUsuarioPorId(Integer id) {
    return usuarioRepository.findById(id);
  }

  public boolean verificarUsuarioPorEmail(String email) {
    return usuarioRepository.existsByEmailAndSituacaoModelIdNot(email, 16);
  }

  public boolean verificarUsuarioPorDocumento(String documento) {
    return usuarioRepository.existsByDocumentoAndSituacaoModelIdNot(documento, 16);
  }

  public List<UsuarioModel> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  public UsuarioModel buscarUsuarioPorEmail(String email) {
    return usuarioRepository.findByEmail(email);
  }

  public void completarInfoUsuario(
      UsuarioModel usuarioModel, PasswordEncoder passwordEncoder, Integer cdTipoUsuario) {
    usuarioModel.setSenha(passwordEncoder.encode(usuarioModel.getSenha()));
    usuarioModel.setCargo("USER");

    TipoUsuarioModel tipoUsuarioModel = tipoUsuarioService.buscarTipoUsuario(cdTipoUsuario).get();
    usuarioModel.setTipoUsuarioModel(tipoUsuarioModel);

    usuarioModel.setSituacaoModel(new SituacaoModel(SituacaoModel.CONTA_SEM_EMAIL_VERIFICADO));
  }

  public boolean validarCPF(String cpf) {
    char dig10, dig11;
    int sm, i, r, num, peso;

    try {
      sm = 0;
      peso = 10;
      for (i = 0; i < 9; i++) {
        num = (int) (cpf.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11)) dig10 = '0';
      else dig10 = (char) (r + 48);

      sm = 0;
      peso = 11;
      for (i = 0; i < 10; i++) {
        num = (int) (cpf.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }

      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11)) dig11 = '0';
      else dig11 = (char) (r + 48);

      return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
    } catch (Exception e) {
      return false;
    }
  }

  public boolean verificarSenhaAtual(
      String senhaAtual, PasswordEncoder passwordEncoder, UsuarioModel usuarioLogado) {
    return (passwordEncoder.matches(senhaAtual, usuarioLogado.getSenha()));
  }

  public void armazenarDocumento(MultipartFile comprovante, UsuarioModel usuarioModel) {
    try {
      String originalFileName = comprovante.getOriginalFilename();
      String pathDir = "comprovantes/";

      File destinationFile = new File(pathDir);
      if (!destinationFile.exists()) destinationFile.mkdirs();

      UUID codigoArquivo = UUID.randomUUID();
      File arquivo = new File(pathDir + codigoArquivo);

      usuarioModel.setArquivo(originalFileName);
      usuarioModel.setCaminhoArquivo(pathDir + codigoArquivo);

      FileOutputStream outputStream = null;
      byte[] bufferedBytes = new byte[1024];

      BufferedInputStream fileInputStream = new BufferedInputStream(comprovante.getInputStream());
      outputStream = new FileOutputStream(arquivo);
      int count = 0;
      while ((count = fileInputStream.read(bufferedBytes)) != -1) {
        outputStream.write(bufferedBytes, 0, count);
      }
      outputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
