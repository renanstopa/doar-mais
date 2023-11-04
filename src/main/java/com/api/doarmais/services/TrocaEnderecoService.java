package com.api.doarmais.services;

import com.api.doarmais.dtos.request.EnderecoRequestDto;
import com.api.doarmais.models.tabelas.SituacaoModel;
import com.api.doarmais.models.tabelas.TrocaEnderecoModel;
import com.api.doarmais.models.tabelas.UsuarioModel;
import com.api.doarmais.repositories.TrocaEnderecoRepository;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TrocaEnderecoService {

  @Autowired private TrocaEnderecoRepository trocaEnderecoRepository;

  public TrocaEnderecoModel criarSolicitacao(
      EnderecoRequestDto enderecoRequestDto, UsuarioModel usuarioModel) {
    TrocaEnderecoModel trocaEnderecoModel = new TrocaEnderecoModel();
    BeanUtils.copyProperties(enderecoRequestDto, trocaEnderecoModel);
    armazenarDocumento(enderecoRequestDto.getComprovante(), trocaEnderecoModel);
    trocaEnderecoModel.setIdUsuario(usuarioModel.getId());
    trocaEnderecoModel.setIdSituacao(SituacaoModel.SOLICITACAO_TROCA_ENDERECO_POENDENTE);
    return trocaEnderecoRepository.save(trocaEnderecoModel);
  }

  private void armazenarDocumento(
      MultipartFile comprovante, TrocaEnderecoModel trocaEnderecoModel) {
    try {
      String originalFileName = comprovante.getOriginalFilename();
      String pathDir = "doarmais/comprovantes/";

      File destinationFile = new File(pathDir);
      if (!destinationFile.exists()) destinationFile.mkdirs();

      UUID codigoArquivo = UUID.randomUUID();
      File arquivo = new File(pathDir + codigoArquivo);

      trocaEnderecoModel.setArquivo(originalFileName);
      trocaEnderecoModel.setCaminhoArquivo(pathDir + codigoArquivo);

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

  public TrocaEnderecoModel consultar(Integer id) {
    return trocaEnderecoRepository.findById(id).get();
  }

  public void gravar(TrocaEnderecoModel trocaEnderecoModel) {
    trocaEnderecoRepository.save(trocaEnderecoModel);
  }
}
