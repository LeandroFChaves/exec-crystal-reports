package techne.report.crystal.connection;

/**
 * Definições para conexão com um banco de dados Oracle.
 * 
 * @author arthemus.moreira
 * @since 05/06/2014
 * @see SuperBanco
 *
 */
public class Oracle extends SuperBanco {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	@Override
	public String getName() {
		return "Oracle";
	}

	@Override
	public String getUrl() {
		return "jdbc:oracle:thin:@" + super.getHost() + ":" + super.getPort() + ":" + super.getDatabaseName();
	}

	@Override
	public String getDrive() {
		return DRIVER;
	}

}
