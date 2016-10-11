package ru.unlimit;

import java.sql.*;

public class Connect_Function {
    Connection conn = null;
    Statement st;
    
    public Connect_Function() {
    try {
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/minegear", "root", "root");
  //Для выполнения SQL-команд 
    st = conn.createStatement();
    
}
    catch (Exception e) {
    	e.printStackTrace();
   
}
}
}
