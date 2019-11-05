package agenda.factory;
import java.sql.*;
public class ConnectionFactory {
    //Nome do usuário do mysql
   private static final String usuario= "root";
 
   //Senha do mysql
  private static final String senha = "root";
   
   //Dados de caminho, porta e nome da base de dados que irá ser feita a conexão
  private static final String caminho = "jdbc:mysql://localhost:3306/agenda";
   
   /**
   * Cria uma conexão com o banco de dados MySQL utilizando o nome de usuário e senha fornecidos
   * @param username
   * @param senha
   * @return uma conexão com o banco de dados
   * @throws Exception
   */
   public static Connection createConnection() throws Exception{
     // Class.forName("com.mysql.jdbc.Driver"); //Faz com que a classe seja carregada pela JVM
      Class.forName("com.mysql.jdbc.Driver");
 
      //Cria a conexão com o banco de dados
      Connection connection = DriverManager.getConnection(caminho, usuario, senha);
 
      return connection;
   }
   public static void main(String[] args) throws Exception{
 
      //Recupera uma conexão com o banco de dados
      Connection con = createConnection();
 
      //Testa se a conexão é nula
      if(con != null){
         System.out.println("Conexão obtida com sucesso!" );
         con.close();
      }
 
   }
}
