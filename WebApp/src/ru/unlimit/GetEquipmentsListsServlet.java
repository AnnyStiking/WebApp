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
	
	Connect_Function db;
	
	@Override
	public void init() throws ServletException {
		super.init();
		db = new Connect_Function();
	}

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String itemId = req.getParameter("itemId");
		EquipmentsList item = performTask(new Integer(itemId).intValue());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ItemDetails.jsp");
		req.setAttribute("results", item);
		dispatcher.forward(req, resp);
	}
	
	private EquipmentsList performTask(int itemId){
		EquipmentsList ai = null;
		try {
				try {//4
					//� ������ ResultSet ���������� ���������� ���������� ��������.
					ResultSet rs = null;
						try {//5
							String sqlCommand = "SELECT * FROM equipment WHERE ID_TYPE =" + itemId;//���� �������
							rs = db.st.executeQuery(sqlCommand);//����� executeQuery() ���������� ������ ResultSet,
									                                           //������� ����� ������������ ��� ����������� ��������� �����������.
							ai = new EquipmentsList();
							rs.next();
								ai.setCNAME(rs.getString(2));
								//ai.setMEASUREID(rs.getInt(3));
							
								
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
							if (db.st != null) db.st.close();
							else
								System.out.print("Statement �� ������");
						}
				 
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
