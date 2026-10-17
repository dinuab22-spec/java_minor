import java.sql.*;
import java.awt.*;
import javax.swing.*;

public class dep_and_doc extends JFrame {
  //database variable 
      String url;
      String username;
      String password;
      Connection con;
      int num=10; 
      //end
  dep_and_doc()
  {

      //database_start;
    try{
     //      mariadb changeto(sql)             database_name
    url = "jdbc:mariadb://localhost:3306/dinu";
    username = "oop"; 
    password= "dinu";
    con=DriverManager.getConnection(url,username,password);
    System.out.println("sucessfull");

  }
  catch(SQLException e){
    System.out.println("connecton failed");
  }
      //database_end
  this.setSize(300,500);
  this.setLayout(new FlowLayout());
  this.setVisible(true);
  }
    
  public static void main(String args[]) {
  

  dep_and_doc obj = new dep_and_doc(); //for inialize purpose database,
  doctor objj=new doctor(); 
  
    
    

    


  }
}
  public class doctor extends dep_and_doc{
    int num;
    doctor(){
      num=super.num; 
      System.out.println(num);
    }
  
  }
  



