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
  String doc_drop_result; //selected dropt list item
  
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
    db_statement = test.con.createStatement();
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
    doc_drop_result = (String)doc_drop.getSelectedItem();
    if(doc_drop_result == null || doc_drop_result.isEmpty())
    {
      System.out.println("not selected anything");
     // return;
    }
    else{
      //System.out.println(doc_drop_result);
     new doctor_dashboard_frame(dep_drop_result,doc_drop_result,test);//passing docotor name
                                                             //and department name to displaying 
                                                             //dashboard
    }
  }




  }

}

class doctor_dashboard_frame extends dep_doc implements ActionListener {

  String department;
  String doctor_name;

  JLabel doctor_dash= new JLabel("dashboard");
  JLabel doctor_namee=new JLabel("");
  JLabel department_name=new JLabel("");
  JButton Available = new JButton("available");
  JButton busy = new JButton("busy");
  JButton on_break = new JButton("break");
  JButton offline = new JButton("offline");

  
  database obj;//database object passed from main_frame
  Statement update_db;//to set up statement
  Statement offline_db;
  Statement Available_db;
  Statement busy_db;

    doctor_dashboard_frame(String departmen_t,String doctor,database val_obj){
      obj = val_obj; //database object from  main frame
      try{

     update_db = obj.con.createStatement();

     update_db.executeQuery("update doctor set status='busy' where doctor_name = 'sanji' ");
      department = departmen_t;
      doctor_name = doctor;

       doctor_namee.setText("name : "+doctor_name);
       department_name.setText("department:"+department);


      this.add(doctor_dash);
      this.add(doctor_namee);
      this.add(department_name);

      this.add(Available);
      this.add(busy);
      this.add(offline);
      this.add(on_break);
      
      //acton event setup


      Available.addActionListener(this);
      busy.addActionListener(this);
      offline.addActionListener(this);
      on_break.addActionListener(this);




      this.setLayout(new FlowLayout());
      this.setVisible(true);
      this.setSize(500,500);
      }
      catch(SQLException x){
        System.out.println("not working");
      }
    }
    public void actionPerformed(ActionEvent e){
      if(e.getSource()==Available){
        //setting all other except clicked to white
        Available.setBackground(Color.YELLOW);
        busy.setBackground(Color.WHITE);
        on_break.setBackground(Color.WHITE);
        offline.setBackground(Color.WHITE);

        try{
          Available_db = obj.con.createStatement();
          Available_db.executeQuery("update doctor set status = 'Available' where doctor_name = '" + doctor_name + "'");
        }
        catch(SQLException dd){
          System.out.println("database error");
        }
      }
      else if(e.getSource() == busy){
        //setting all other except clicked to white
        Available.setBackground(Color.WHITE);
        busy.setBackground(Color.YELLOW);
        on_break.setBackground(Color.WHITE);
        offline.setBackground(Color.WHITE);

        try{
          busy_db = obj.con.createStatement();
          busy_db.executeQuery("update doctor set status = 'busy' where doctor_name = '" + doctor_name + "'");
        }
        catch(SQLException dd){
          System.out.println("database error");
        }
      }
        else if(e.getSource() == offline){
        //setting all other except clicked to white
        Available.setBackground(Color.WHITE);
        busy.setBackground(Color.WHITE);
        on_break.setBackground(Color.WHITE);
        offline.setBackground(Color.YELLOW);

        try{
          offline_db = obj.con.createStatement();
          offline_db.executeQuery("update doctor set status = 'offline' where doctor_name = '" + doctor_name + "'");
        }
        catch(SQLException dd){
          System.out.println("database error");
        }
      }
        else if(e.getSource() == on_break){
        //setting all other except clicked to white
        Available.setBackground(Color.WHITE);
        busy.setBackground(Color.WHITE);
        on_break.setBackground(Color.YELLOW);
        offline.setBackground(Color.WHITE);

        try{
          offline_db = obj.con.createStatement();
          offline_db.executeQuery("update doctor set status = 'on_break' where doctor_name = '" + doctor_name + "'");
        }
        catch(SQLException dd){
          System.out.println("database error");
        }
      }
    }



}







public class dep_doc extends JFrame{
  public static void main(String args[]){
  database data_obj = new database();
  main_frame menu = new main_frame(data_obj);
   }
  }

