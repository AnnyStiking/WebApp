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
				//Для выполнения SQL-команд 
				//В объект ResultSet помещаются результаты выполнения запросов.
				ResultSet rs = null;
					try {//5
						String sqlCommand = "SELECT * FROM equipment";//Сама команда
						rs = db.st.executeQuery(sqlCommand);//Метод executeQuery() возвращает объект ResultSet,
						while (rs.next()) {
								EquipmentsList ai = new EquipmentsList();
								ai.setID_TYPE(rs.getInt(4));
								ai.setENAME(rs.getString(2));								
								results.add(ai);
									} 
								}
									finally {//Для 5-го блока try 
											/*Закрыть ResultSet, если он был открыт и ошибка произошла во время
											  чтения из него данных*/
											//Проверка, успел ли создаться ResultSet
											if (rs != null) rs.close();
											else
												System.out.print("Ошибка во время чтения данных из БД");
										}
			} finally {//Для 4-го блока try 
									/*Закрыть Statement, если он был открыт и ошибка произошла
									 во время создания ResultSet*/
									//Проверка, успел ли создаться Statement
									if (db.st != null) db.st.close();
									else
										System.out.print("Statement не создан");
								}
		}
		
		catch (SQLException e) {//Для 1-го блока try
			}
					return results;		
}
	
}


