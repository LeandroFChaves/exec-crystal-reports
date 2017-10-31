package techne.report.crystal.connection;

/**
 * Classe para gerenciar conexão com um banco de dados Microsoft SQL Server.
 *
 * @author Arthemus C. Moreira
 * @since 1.0.0
 */
public final class MSSQLServer extends SuperBanco {

	public static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

	@Override
	public String getName() {
		return "Microsoft SQL Server";
	}

	@Override
	public String getUrl() {
		return "jdbc:jtds:sqlserver://" + getHost() + ":" + getPort() + "/" + getDatabaseName() + (super.getInstanceName() == null ? "" : ";instance=" + super.getInstanceName());
	}

	@Override
	public String getDrive() {
		return DRIVER;
	}

}
