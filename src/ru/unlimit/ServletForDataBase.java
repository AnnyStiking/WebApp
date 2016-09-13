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
		//Метод next() вернёт false, когда достигнет конца ResultSet.
		//А в объект ResultSet вообще помещаются результаты выполнения запросов.
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
						Class.forName(driverName);   //Для загрузки класса по его имени
						System.out.println("Driver loading success!");
						Connection cn = null; 
							try {//3
								String url = "jdbc:mysql://localhost:3306/business";
								String name = "root";
								String password = "root";
								//Для создания соединения. Метод getConnection() просматривает список драйверов и, 
								//если находит подходящий к указанному URL, то создаёт и возвращает соединение.
								cn = DriverManager.getConnection(url, name, password);
							//Statement - объект для передачи запросов
								Statement st = null;
									try {//4
										//Для выполнения SQL-команд 
										st = cn.createStatement();
										//В объект ResultSet помещаются результаты выполнения запросов.
										ResultSet rs = null;
											try {//5
												String sqlCommand = "SELECT * FROM equipment";//Сама команда
												rs = st.executeQuery(sqlCommand);//Метод executeQuery() возвращает объект ResultSet,
												                                           //который можно использовать для построчного просмотра результатов.
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
										if (st != null) st.close();
										else
											System.out.print("Statement не создан");
									}
							} finally {//Для 3-го блока try
								/*Закрыть Connection, если он был открыт и ошибка произошла
								 во время создания ResultSet или создания и использования Statement*/
								//Проверка, успел ли создаться Connection
								if (cn != null) cn.close();
								else
									System.out.print("Connection не создан");
							}
					} catch (ClassNotFoundException e) {//Для 2-го блока try
						//System.out.print("Ошибка во время загрузки драйвера БД");
						e.printStackTrace();
					} 
			//} 
			/*Вывод сообщения о всех SQLException и IOException в блоках finally,
			 поэтому следующие блоки catch оставлены пустыми*/
			catch (SQLException e) {//Для 1-го блока try
			}
		//	catch (IOException e) {//Для 1-го блока try
		//	}
			//finally {//Для 1-го блока try
				/*Закрыть PrintWriter, если он был инициализирован и ошибка произошла
				 во время работы с БД*/
				//Проверка, успел ли инициализироваться PrintWriter
				/*	if (out != null) out.close();
				//else 
				//	out.print("PrintWriter не проинициализирован");
			}  */
			return results;		
	}

}
