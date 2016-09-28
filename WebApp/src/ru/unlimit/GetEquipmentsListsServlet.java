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
			Class.forName(DRIVER_CLASS);   //Для загрузки класса по его имени
			System.out.println("Driver loading success!");
			Connection cn = null;
			//Properties props = new Properties();
				try {//3
					//Для создания соединения. Метод getConnection() просматривает список драйверов и, 
					//если находит подходящий к указанному URL, то создаёт и возвращает соединение.
					//String name=new ServletForDataBase().toString();
					//String password=new ServletForDataBase().toString();

					//String name = request.getParameter("name");
					//String password = request.getParameter("password");
					String name = "root";
					String password = "root";
					cn = DriverManager.getConnection(URL, name,password);
				//Statement - объект для передачи запросов
					Statement st = null;
						try {//4
							//Для выполнения SQL-команд 
							st = cn.createStatement();
							//В объект ResultSet помещаются результаты выполнения запросов.
							ResultSet rs = null;
								try {//5
									String sqlCommand = "SELECT * FROM equipment WHERE equipment_id=" + itemId;//Сама команда
									rs = st.executeQuery(sqlCommand);//Метод executeQuery() возвращает объект ResultSet,
									                                           //который можно использовать для построчного просмотра результатов.
									//out.print("From DataBase: ");
									ai = new EquipmentsList();
									rs.next();
										ai.setEquipment_id(rs.getInt(1));
										ai.setEquipment_name(rs.getString(2));
										ai.setCharacteristic(rs.getString(3));

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
			System.out.print("Ошибка во время загрузки драйвера БД");
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
return ai;		
}
}
