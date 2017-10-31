package techne.report.crystal;

import com.crystaldecisions.report.web.viewer.CrPrintMode;
import com.crystaldecisions.report.web.viewer.CrystalReportViewer;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import techne.report.commons.ExportPdf;
import techne.report.crystal.connection.CrystalConnection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Faz uso da API Crystal Report para manipular arquivos .rpt.
 *
 * @author arthemus
 * @see ExportPdf
 * @since 30/06/2015
 */
public class CrystalFile {

  private final ReportClientDocument _reportClientDocument;

  /**
   * Construtor príncipal.
   *
   * @param fileName Nome do arquivo.
   */
  public CrystalFile(String fileName) {
    _reportClientDocument = new ReportClientDocument();
    try {
      _reportClientDocument.open(fileName, 0);
    } catch (ReportSDKException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Exporta o relatório para um determinado formato.
   *
   * @param reportExportFormat
   * @return Instancia da classe ExportPdf para manipulação do arquivo de relatório.
   */
  public InputStream doExport(ReportExportFormat reportExportFormat) {

    InputStream resultStream = null;
    try {
      resultStream = _reportClientDocument.getPrintOutputController().export(reportExportFormat);
    } catch (ReportSDKException e) {
      throw new RuntimeException(e);
    }

    try {
      _reportClientDocument.close();
    } catch (ReportSDKException e) {
      // NoCommand
    }

    return resultStream;
  }

  /**
   * Exporta o relatório atual para o formato PDF.
   *
   * @return Instancia da classe ExportPdf para manipulação do arquivo de relatório.
   */
  public ExportPdf doExportPdf() {
    return new ExportPdf(this.doExport(ReportExportFormat.PDF));
  }

  /**
   * Abre uma nova aba no navegador e utiliza a interface nativa do Crystal Reports para visualizar o relatório atual.
   * <p>
   * É necessário adicionar o pacote /crystalreportviewers e definir os parametros de contexto e servlet no
   * web-fragment.xml da aplicação.
   *
   * @param request        HttpRequest atual.
   * @param response       HttpResponse atual.
   * @param servletContext Instancia da Servlet atual.
   */
  public void doOpenCrystalView(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) {
    Object reportSource = _reportClientDocument.getReportSource();
    try {
      _reportClientDocument.close();
    } catch (ReportSDKException e) {
      throw new RuntimeException(e);
    }

    CrystalReportViewer crystalReportViewer = new CrystalReportViewer();
    crystalReportViewer.setOwnPage(true);
    crystalReportViewer.setPrintMode(CrPrintMode.ACTIVEX);

    try {
      crystalReportViewer.setReportSource(reportSource);
    } catch (com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase reportSDKExceptionBase) {
      reportSDKExceptionBase.printStackTrace();
    }

    try {
      crystalReportViewer.processHttpRequest(request, response, servletContext, null);
    } catch (com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase reportSDKExceptionBase) {
      reportSDKExceptionBase.printStackTrace();
    }
  }

  /**
   * Defini um parametro interno necessário ao relatório.
   *
   * @param param Nome do parametro.
   * @param value Valor do parametro.
   * @return Instancia atual da classe CrystalFile.
   */
  public CrystalFile setParameter(String param, Object value) {
    try {
      _reportClientDocument
              .getDataDefController()
              .getParameterFieldController()
              .setCurrentValue("", param, value);
    } catch (ReportSDKException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * Define uma conexão externa para o relatório.
   *
   * @param crystalConnection Instancia da classe CrystalConnection com os dados necessários para conexão com o banco.
   * @return Instancia atual da classe CrystalFile.
   * @throws ReportSDKException Problemas durante a conexão.
   */
  public CrystalFile onConnection(CrystalConnection crystalConnection) throws ReportSDKException {
    try {
      CRJavaHelper.changeDataSource(_reportClientDocument,
              crystalConnection.get().getUser(),
              crystalConnection.get().getPassword(),
              crystalConnection.get().getUrl(),
              crystalConnection.get().getDrive(),
              null);
    } catch (ReportSDKException e) {
      throw e;
    }
    return this;
  }

}
