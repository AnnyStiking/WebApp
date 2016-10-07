package ru.unlimit;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletForDataBase extends HttpServlet {

	//private final static String driverName = "com.mysql.jdbc.Driver";
	//String url = "jdbc:mysql://localhost:3306/business";
	private static final long serialVersionUID = 1L;
	
	/*public static void Resources() {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/business");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("root");
		p.setPassword("root");
	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List results = performTask(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ShowEquipments.jsp");
		request.setAttribute("results", results);
		dispatcher.forward(request, response);

	}

	private List performTask(HttpServletRequest request, HttpServletResponse response) {

		List results = new Vector();
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/business");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("root");
		p.setPassword("root");

					//String driverName = "com.mysql.jdbc.Driver";
					//Class.forName(driverName);   //��� �������� ������ �� ��� �����
					//System.out.println("Driver loading success!");
					//Connection cn = null; 
		try {//2		
			   // Class.forName("com.mysql.jdbc.Driver"); 
				Connection cn = null;
					
						try {//3
							//String url = "jdbc:mysql://localhost:3306/business";
							//String name = "root";
							//String password = "root";
							//auten name1 = new auten();
							//auten password1 = new auten();
							
							String name = request.getParameter("name");
							String password = request.getParameter("password");

							//HttpSession session = request.getSession();
							

							//��� �������� ����������. ����� getConnection() ������������� ������ ��������� �, 
							//���� ������� ���������� � ���������� URL, �� ������ � ���������� ����������.
							
							//auten name1 = new auten();
							//auten password1 = new auten();
							//cn = DriverManager.getConnection(url, name, password);
							
							//Context ct = new InitialContext();
							//DataSource ds = (DataSource) ct.lookup("java:jdbc/db");
							
							
							DataSource ds = new DataSource();
							//cn = ds.getConnection(name, password);
							 //InitialContext initContext = new InitialContext();
							// DataSource ds = (DataSource) initContext.lookup("java:jdbc/db");
							 cn = ds.getConnection();

							
							
							
							

						//Statement - ������ ��� �������� ��������
							Statement st = null;
								try {//4
									//��� ���������� SQL-������ 
									st = cn.createStatement();
									//� ������ ResultSet ���������� ���������� ���������� ��������.
									ResultSet rs = null;
										try {//5
											String sqlCommand = "SELECT * FROM equipment";//���� �������
											rs = st.executeQuery(sqlCommand);//����� executeQuery() ���������� ������ ResultSet,
											                                           //������� ����� ������������ ��� ����������� ��������� �����������.
											//out.print("From DataBase: ");
											while (rs.next()) {
												EquipmentsList ai = new EquipmentsList();
												
												//out.print("<br>Number:-> " + rs.getInt(1) + " Equipment's names" + rs.getString(2));
												ai.setEquipment_id(rs.getInt(1));
												ai.setEquipment_name(rs.getString(2));
												ai.setCharacteristic(rs.getString(3));
												//ai.setDescription_id(rs.getInt(3));
												results.add(ai);
											
											
											} 
										}
										finally {//��� 5-�� ����� try 
											/*������� ResultSet, ���� �� ��� ������ � ������ ��������� �� �����
											  ������ �� ���� ������*/
											//��������, ����� �� ��������� ResultSet
											if (rs != null) rs.close();
											else
												System.out.print("������ �� ����� ������ ������ �� ��");
										}
								} finally {//��� 4-�� ����� try 
									/*������� Statement, ���� �� ��� ������ � ������ ���������
									 �� ����� �������� ResultSet*/
									//��������, ����� �� ��������� Statement
									if (st != null) st.close();
									else
										System.out.print("Statement �� ������");
									
									//if (cn != null) cn.close();
									//else
									//	System.out.print("Connection �� ������");
								}
						} /*catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/ /*catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
								finally {//��� 3-�� ����� try
									/*������� Connection, ���� �� ��� ������ � ������ ���������
									 �� ����� �������� ResultSet ��� �������� � ������������� Statement*/
									//��������, ����� �� ��������� Connection
									if (cn != null) cn.close();
									else
										System.out.print("Connection �� ������");
								}
		}
						 /*catch (ClassNotFoundException e) {
							 System.out.print("������ �� ����� �������� �������� ��");
							 e.printStackTrace();
						 }*/
		
		catch (SQLException e) {//��� 1-�� ����� try
			}

			
					return results;		
}
	
}


