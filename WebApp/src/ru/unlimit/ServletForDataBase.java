package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ServletForDataBase")
public class ServletForDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EquipmentsList> results = performTask();
		//request.setAttribute("results", results);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ShowEquipments.jsp");
		request.setAttribute("results", results);
		dispatcher.forward(request, response);
		//List results = performTask(request, response);
		//request.setAttribute("results", results);
		//performTask(request, response);
	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}*/
	
	/*public void showInfo(PrintWriter out, ResultSet rs) throws SQLException {
		out.print("From DataBase: ");
		//����� next() ����� false, ����� ��������� ����� ResultSet.
		//� � ������ ResultSet ������ ���������� ���������� ���������� ��������.
		while (rs.next()) {
			out.print("<br>Number:-> " + rs.getInt(1) + " Equipment's names" + rs.getString(2));
		}
	}*/
	

	private List<EquipmentsList> performTask() {

		//HttpServletResponse response = null;
		//ServletForDataBase response = new ServletForDataBase();
		//HttpServletResponse res=(HttpServletResponse)response;
		//HttpServletResponse response=(HttpServletResponse)out;
		//response.setContentType("text/html; charset=UTF-8");
		List results = new Vector();
		//PrintWriter out = null;
			//try {//1
		//		out = response.getWriter();
					try {//2
					    String driverName = "com.mysql.jdbc.Driver";
						Class.forName(driverName);   //��� �������� ������ �� ��� �����
						System.out.println("Driver loading success!");
						Connection cn = null; 
							try {//3
								String url = "jdbc:mysql://localhost:3306/business";
								String name = "root";
								String password = "root";
								//��� �������� ����������. ����� getConnection() ������������� ������ ��������� �, 
								//���� ������� ���������� � ���������� URL, �� ������ � ���������� ����������.
								cn = DriverManager.getConnection(url, name, password);
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
													//ai.setDescription_id(rs.getInt(3));
													results.add(ai);
												}
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
						//System.out.print("������ �� ����� �������� �������� ��");
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
			return results;		
	}

}
