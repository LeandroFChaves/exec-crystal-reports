package techne.report.teste;

import java.io.File;
import java.io.IOException;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import techne.report.crystal.CrystalFile;
import techne.report.crystal.connection.CrystalConnection;

public class TesteCrystal {

	public static void main(String args[]) {
		System.out.println("Exportando para PDF ... ");
		
		try {
			CrystalFile cf = new CrystalFile("src/test/resources/AcaCreditosTotal.rpt");

			// Define conexão com o banco de dados
			cf.onConnection(CrystalConnection.newMSSQLServerConnection().host("localhost").port(1433).databaseName("Lyceum")
					.instanceName("SQL2012EXPRESS").user("sa").password("bhimA123"));

			// Parâmetros do relatório
			cf.setParameter("Ano", "2017");
			cf.setParameter("Período", "0");
			cf.setParameter("Unidade", "102");
			
			// Nome do arquivo que será gerado e o caminho onde será gravado
			File tempFile = File.createTempFile("ReportTest", ".pdf", new File("C:/temp"));
			
			// Exporta o arquivo para PDF
			cf.doExportPdf().doSaveFile(tempFile);
			
		} catch (ReportSDKException | IOException io) {
			System.out.println("Erro !!!");
		}
	}
}