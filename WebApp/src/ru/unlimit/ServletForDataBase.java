package ru.unlimit;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletForDataBase extends HttpServlet {
	
	Connect_Function db;
	
	

	@Override
	public void init() throws ServletException {
				super.init();
				db = new Connect_Function();
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List results = performTask(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ShowEquipments.jsp");
		request.setAttribute("results", results);
		dispatcher.forward(request, response);

	}

	private List performTask(HttpServletRequest request, HttpServletResponse response) {
		
		List results = new Vector();

		try {
			try {//4
				//��� ���������� SQL-������ 
				//� ������ ResultSet ���������� ���������� ���������� ��������.
				ResultSet rs = null;
					try {//5
						String sqlCommand = "SELECT * FROM equipment";//���� �������
						rs = db.st.executeQuery(sqlCommand);//����� executeQuery() ���������� ������ ResultSet,
						while (rs.next()) {
								EquipmentsList ai = new EquipmentsList();
								ai.setID_TYPE(rs.getInt(4));
								ai.setENAME(rs.getString(2));								
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
									if (db.st != null) db.st.close();
									else
										System.out.print("Statement �� ������");
								}
		}
		
		catch (SQLException e) {//��� 1-�� ����� try
			}
					return results;		
}
	
}


