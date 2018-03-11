import java.sql.*;
import java.util.jar.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;  
class Book extends Frame implements ActionListener{ 
  TextField name,number;
  Button a,d,b;
  String k;
  Long l;
  int x=10,y=200;
  Book()
  {
     setLayout(new FlowLayout());
     Label n=new Label("Name: ",Label.LEFT);
     Label p=new Label("Number: ",Label.LEFT);
     name=new TextField(25);
     number=new TextField(10);
     a=new Button("add");
     d=new Button("display");
     b=new Button("delete");
     add(n);
     add(name);
     add(p);
     add(number);
     add(a);
     add(d);
     add(b);
     d.addActionListener(this);
     a.addActionListener(this);
     b.addActionListener(this);
     addWindowListener(new WindowAdapter()
     {
       public void windowClosing(WindowEvent we)
       {
           System.exit(0);
       }
    });
 }
public void actionPerformed(ActionEvent ae){ 
try{
  
//step1 load the driver class  
Class.forName("oracle.jdbc.driver.OracleDriver");  
  
//step2 create  the connection object  
Connection con=DriverManager.getConnection(  
"jdbc:oracle:thin:@localhost:1521:xe","system","karthik");  
  
//step3 create the statement object  
Statement stmt=con.createStatement();
if(ae.getActionCommand()=="add")
{ 
String s=name.getText();
  Long n=Long.parseLong(number.getText());  
int norows=stmt.executeUpdate("insert into book(name,num) values('"+s+"',"+n+")");
name.setText(" ");
number.setText(" ");
 }
else if(ae.getActionCommand()=="display")
{
  ResultSet rs=stmt.executeQuery("select * from book");
  while(rs.next())
  {   
   k=rs.getString(1);
   l=rs.getLong(2); 
   Graphics g=this.getGraphics();
   g.drawString(" "+k,x,y);
   x=x+90;
   g.drawString(" "+((Long)l).toString(),x,y);
   y=y+10;  
   x=x-90; 
  }
}
else if(ae.getActionCommand()=="delete")
{
  int norows=stmt.executeUpdate("delete from book");
}
//step5 close the connection object  
con.close();  
  
}catch(Exception e){ System.out.println(e);}  
  
}   
public static void main(String args[])
{
  Book b=new Book();
  b.setTitle("phonebook");
  b.setVisible(true);
  b.setSize(400,400);
}
}  