package techne.report.crystal;


import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import org.junit.Assert;
import org.junit.Test;
import techne.report.crystal.connection.CrystalConnection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Testes unitários para o processamento de relatórios Crystal.
 *
 * @author Arthemus
 * @since 30/06/2015
 */
public class CrystalTest {

  @Test
  public void processa_input_stream_do_relatorio_local() {
    InputStream inputStream = Crystal.onFile("CR10_JRC_BeginHere.rpt").doExportPdf().getInputStream();
    Assert.assertNotNull(inputStream);
  }

  @Test
  public void exporta_relatorio_local_para_pdf() {
    try {
      File tempFile = File.createTempFile("ReportTest", "pdf");
      Crystal.onFile("CR10_JRC_BeginHere.rpt").doExportPdf().doSaveFile(tempFile);
      Assert.assertTrue(tempFile.length() > 0);
      tempFile.delete();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void exporta_relatorio_local_para_pdf_explicito() {
    InputStream inputStream = Crystal.onFile("CR10_JRC_BeginHere.rpt").doExport(ReportExportFormat.PDF);
    Assert.assertNotNull(inputStream);
  }

  @Test
  public void exporta_relatorio_local_para_word_explicito() {
    InputStream inputStream = Crystal.onFile("CR10_JRC_BeginHere.rpt").doExport(ReportExportFormat.MSWord);
    Assert.assertNotNull(inputStream);
  }

  /**
   * Para realizar esse teste, crie no banco de destino a seguinte tabela:
   *
   * CREATE TABLE [cars] (
      [id] [int] IDENTITY(1,1) NOT NULL,
      [nome] [varchar](30) NOT NULL)
   *
   * Com as palavras chaves e estrutura necessária para o banco em questão.
   *
   * Insira alguns valores a tabela e atualize os dados de conexão de acordo.
   *
   */
  // @Test
  public void exporta_relatorio_com_conexao() {
    try {
      File tempFile = File.createTempFile("ReportTest", "pdf");
      Crystal.onFile("crystalCars.rpt")
              .onConnection(CrystalConnection.newMSSQLServerConnection()
                      .host("localhost")
                      .port(1433)
                      .databaseName("crystalteste")
                      .instanceName("SQL2012EXPRESS")
                      .user("sa")
                      .password("senha123"))
              .doExportPdf()
              .doSaveFile(tempFile);
      Assert.assertTrue(tempFile.length() > 0);
      Desktop.getDesktop().open(tempFile);
      // tempFile.delete();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ReportSDKException e) {
      throw new RuntimeException(e);
    }
  }

}
