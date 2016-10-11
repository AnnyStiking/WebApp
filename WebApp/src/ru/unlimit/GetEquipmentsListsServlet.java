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
					//В объект ResultSet помещаются результаты выполнения запросов.
					ResultSet rs = null;
						try {//5
							String sqlCommand = "SELECT * FROM equipment WHERE ID_TYPE =" + itemId;//Сама команда
							rs = db.st.executeQuery(sqlCommand);//Метод executeQuery() возвращает объект ResultSet,
									                                           //который можно использовать для построчного просмотра результатов.
							ai = new EquipmentsList();
							rs.next();
								ai.setCNAME(rs.getString(2));
								//ai.setMEASUREID(rs.getInt(3));
							
								
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
							if (db.st != null) db.st.close();
							else
								System.out.print("Statement не создан");
						}
				 
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
