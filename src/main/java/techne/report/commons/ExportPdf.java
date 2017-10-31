package techne.report.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * Classe utilitária responsável por processar arquivos para o formato PDF.
 * 
 * @author arthemus
 * @since 30/06/2015
 */
public class ExportPdf {
  
  private final InputStream _byteArrayInputStream;
  
  /**
   * Construtor príncipal.
   * 
   * @param inputStream
   *          Stream do relatório.
   */
  public ExportPdf(InputStream inputStream) {
    _byteArrayInputStream = inputStream;
  }
  
  /**
   * Obtem o inputStream do relatório já processado.
   * 
   * @return Instancia InputStream do relatório.
   */
  public InputStream getInputStream() {
    return _byteArrayInputStream;
  }
  
  /**
   * Abre o relatório em uma nova aba do navegador.
   * 
   * @param response
   *          HttpResponse atual.
   */
  public void doOpen(HttpServletResponse response) {
    response.reset();
    response.setHeader("Content-disposition", "inline;filename=report.pdf");
    response.setContentType("application/pdf");
    try {
      this.doSave(response.getOutputStream());
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Salva o relatório em um determinado ponto de saída.
   * 
   * @param outputStream
   *          Destino.
   */
  public void doSave(OutputStream outputStream) {
    byte[] buffer = new byte[Short.MAX_VALUE];
    int bytesRead = 0;
    try {
      while((bytesRead = _byteArrayInputStream.read(buffer)) != -1)
        outputStream.write(buffer, 0, bytesRead);
      outputStream.flush();
      outputStream.close();
    }
    catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Escreve o relatório em um determinado arquivo local.
   * 
   * @param file
   *          Arquivo a ser escrito.
   */
  public void doSaveFile(File file) {
    FileOutputStream fileOutputStream;
    try {
      fileOutputStream = new FileOutputStream(file);
      this.doSave(fileOutputStream);
    }
    catch(FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
}
