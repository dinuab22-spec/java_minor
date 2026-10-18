
import java.sql.*;
public class database_test
{
    int test ; 
  public static void main(String []args) {
   try{
    
    String url = "jdbc:mariadb://localhost:3306/dinu";
    String username = "oop"; 
    String password= "dinu";
     
    Connection con=DriverManager.getConnection(url,username,password);
    System.out.println("sucessfull");


   Statement st = con.createStatement();
   ResultSet r=st.executeQuery("SELECT * FROM  hey");
   int a;
   while(r.next())
   {
   a=r.getInt(1);
   System.out.println(a);
   }


   }
   catch(SQLException e){
     System.out.println("not working ");
   }

     }
}
