package techne.report.crystal.connection;

/**
 * Classe abstrata para representar um expecifico SGDB.
 *
 * @author Arthemus C. Moreira
 * @since 1.0.0
 */
public abstract class SuperBanco {

  private String host;
  private int port;
  private String databaseName;
  private String instanceName;
  private String user;
  private String password;

  /**
   * Retorna o nome do SGDB.
   *
   * @return
   */
  abstract public String getName();

  /**
   * Retorna a URL de conexão com o banco.
   *
   * @return
   */
  abstract public String getUrl();

  /**
   * Retorna a String de identificação do Driver do banco.
   *
   * @return
   */
  abstract public String getDrive();

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
