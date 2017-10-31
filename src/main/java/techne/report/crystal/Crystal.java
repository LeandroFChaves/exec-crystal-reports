package techne.report.crystal;

/**
 * Classe utilitário para acesso aos objetos de manipulação da API Crystal Reports.
 * 
 * @author arthemus
 * @since 30/06/2015
 */
public final class Crystal {
  
  private Crystal() {
  }
  
  /**
   * Utilizar a API a partir de um arquivo físico local dentro do projeto ou informado na aplicação.
   * 
   * @param fileName
   *          Nome do arquivo: 'Relatorio.rpt'
   * @return
   *         Instancia da classe {@link CrystalFile}
   */
  public static final CrystalFile onFile(String fileName) {
    return new CrystalFile(fileName);
  }
  
}
