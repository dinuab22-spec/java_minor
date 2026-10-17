import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class database_test
{
    int test ; 
  public static void main(String []args) {
   try{
     //      mariadb changeto(sql)             database_name
    String url = "jdbc:mariadb://localhost:3306/dinu";
    String username = "oop"; 
    String password= "dinu";
     
    Connection con=DriverManager.getConnection(url,username,password);
     
    System.out.println("sucessfull");

   }
   catch(SQLException e){
     System.out.println("not working ");
   }

  }
}
