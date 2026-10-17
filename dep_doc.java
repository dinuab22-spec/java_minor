import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class database{
 public  String url;
 public String username;
 public String password;
 Connection con;
 
 database(){
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
  }

} //for displaying menu for doctor(dep,name)and selection
 class main_frame extends dep_doc implements ActionListener{

  JComboBox<String> dep_drop=new JComboBox<>();//dropdown menu for department;
  String dep_drop_result;
  
  
  JComboBox<String> doc_drop=new JComboBox<>();//dropdown menu for doc;
  
  JLabel heading =new JLabel("DOCTOR LOGIN");
  JButton submit = new JButton("submit_department");

  JButton final_submit = new JButton("final_submit");
  //for department print
  Statement db_statement;
  ResultSet r; 

//for doctor print based on department
  Statement doc_list;
  ResultSet doc_resul;

  database test;
  main_frame(database obj){
    try{
    test=obj;
    db_statement = obj.con.createStatement();
     r = db_statement.executeQuery("SELECT * FROM  department");
     while(r.next()){
       String b = r.getString(2);
       dep_drop.addItem(b);

     }

    }

    catch(SQLException e){
      System.out.println("not working");
    }
    
  submit.addActionListener(this);
  final_submit.addActionListener(this);



    this.add(heading);
    this.add(dep_drop);
    this.add(submit);
    this.add(doc_drop);
    this.add(final_submit);
    this.setLayout(new FlowLayout());
    this.setSize(400,500);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e){ // to get which department is selected and diplay doctor in that department
    if(e.getSource()== submit){
    dep_drop_result = (String)dep_drop.getSelectedItem();
   
    //System.out.println(dep_drop_result);
    
    doc_drop.removeAllItems();
   try{
    doc_list = test.con.createStatement();
    doc_resul = doc_list.executeQuery(  "SELECT doctor_name FROM doctor WHERE department_id = " +"(SELECT department_id FROM department WHERE department_name='" +dep_drop_result + "')");
    while(doc_resul.next()){
      String temp = doc_resul.getString(1);
      doc_drop.addItem(temp);
    }
  }

  catch(SQLException x){

      System.out.println("not working");
    }
  }
  else if(e.getSource() == final_submit){
    System.out.println("final_submit work");
  }




  }

}

public class dep_doc extends JFrame{
  public static void main(String args[]){
  database data_obj = new database();
  main_frame menu = new main_frame(data_obj);
   }
  }

