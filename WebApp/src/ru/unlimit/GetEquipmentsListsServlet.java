package ru.unlimit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetEquipmentsListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	private final static String URL = "jdbc:mysql://localhost:3306/business";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemId = req.getParameter("itemId");
		EquipmentsList item = performTask(new Integer(itemId).intValue());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ItemDetails.jsp");
		req.setAttribute("results", item);
		dispatcher.forward(req, resp);
	}
	
	private EquipmentsList performTask(int itemId){
		EquipmentsList ai = null;
		try {//2
			Class.forName(DRIVER_CLASS);   //��� �������� ������ �� ��� �����
			System.out.println("Driver loading success!");
			Connection cn = null;
			//Properties props = new Properties();
				try {//3
					//��� �������� ����������. ����� getConnection() ������������� ������ ��������� �, 
					//���� ������� ���������� � ���������� URL, �� ������ � ���������� ����������.
					//String name=new ServletForDataBase().toString();
					//String password=new ServletForDataBase().toString();

					//String name = request.getParameter("name");
					//String password = request.getParameter("password");
					String name = "root";
					String password = "root";
					cn = DriverManager.getConnection(URL, name,password);
				//Statement - ������ ��� �������� ��������
					Statement st = null;
						try {//4
							//��� ���������� SQL-������ 
							st = cn.createStatement();
							//� ������ ResultSet ���������� ���������� ���������� ��������.
							ResultSet rs = null;
								try {//5
									String sqlCommand = "SELECT * FROM equipment WHERE equipment_id=" + itemId;//���� �������
									rs = st.executeQuery(sqlCommand);//����� executeQuery() ���������� ������ ResultSet,
									                                           //������� ����� ������������ ��� ����������� ��������� �����������.
									//out.print("From DataBase: ");
									ai = new EquipmentsList();
									rs.next();
										ai.setEquipment_id(rs.getInt(1));
										ai.setEquipment_name(rs.getString(2));
										ai.setCharacteristic(rs.getString(3));

								} finally {
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
						}
				} finally {//��� 3-�� ����� try
					/*������� Connection, ���� �� ��� ������ � ������ ���������
					 �� ����� �������� ResultSet ��� �������� � ������������� Statement*/
					//��������, ����� �� ��������� Connection
					if (cn != null) cn.close();
					else
						System.out.print("Connection �� ������");
				}
		} catch (ClassNotFoundException e) {//��� 2-�� ����� try
			System.out.print("������ �� ����� �������� �������� ��");
			e.printStackTrace();
		} 
//} 
/*����� ��������� � ���� SQLException � IOException � ������ finally,
 ������� ��������� ����� catch ��������� �������*/
catch (SQLException e) {//��� 1-�� ����� try
}
//	catch (IOException e) {//��� 1-�� ����� try
//	}
//finally {//��� 1-�� ����� try
	/*������� PrintWriter, ���� �� ��� ��������������� � ������ ���������
	 �� ����� ������ � ��*/
	//��������, ����� �� ������������������ PrintWriter
	/*	if (out != null) out.close();
	//else 
	//	out.print("PrintWriter �� ������������������");
}  */
return ai;		
}
}
