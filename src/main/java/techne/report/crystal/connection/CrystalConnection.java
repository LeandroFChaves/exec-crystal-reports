package techne.report.crystal.connection;

/**
 * Fornece as definições de conexão com um banco de dados externo.
 */
public class CrystalConnection {

  private final SuperBanco _superBanco;

  private CrystalConnection(SuperBanco superBanco) {
    _superBanco = superBanco;
  }

  public static CrystalConnection newMSSQLServerConnection() {
    return new CrystalConnection(new MSSQLServer());
  }

  public SuperBanco get() {
    return _superBanco;
  }

  public CrystalConnection host(String host) {
    _superBanco.setHost(host);
    return this;
  }

  public CrystalConnection port(int port) {
    _superBanco.setPort(port);
    return this;
  }

  public CrystalConnection databaseName(String databaseName) {
    _superBanco.setDatabaseName(databaseName);
    return this;
  }

  public CrystalConnection instanceName(String instanceName) {
    _superBanco.setInstanceName(instanceName);
    return this;
  }

  public CrystalConnection user(String user) {
    _superBanco.setUser(user);
    return this;
  }

  public CrystalConnection password(String password) {
    _superBanco.setPassword(password);
    return this;
  }

}
