package ru.unlimit;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.sql.Connection;

//import com.sun.corba.se.pept.transport.Connection;

public class CreatingConnection {

	public static void main(String[] args) {
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);   //Для загрузки класса по его имени
			System.out.println("Driver loading success!");
			
			//У MySQL обязательно есть системная база,
			//к ней и будем создавать соединение
			String url = "jdbc:mysql://localhost:3306/business";
			String name = "root";
			String password = "root";
			//List <EquipmentsList> results = new Vector<EquipmentsList>();
			try {
				Connection con = DriverManager.getConnection(url, name, password);
				System.out.println("Connected.");
				Statement st = con.createStatement(); //Для выполнения SQL-команд нужно создать объект Statement.
				//String sqlCommand = "select count(*) from equipment";//Сама команда
				String sqlCommand = "select * from equipment";//Сама команда
				ResultSet rs = st.executeQuery(sqlCommand);//Метод executeQuery() возвращает объект ResultSet,
				                                           //который можно использовать для построчного просмотра результатов.
				
				//Для анализа набора результатов
			/*	while (rs.next()) {
					EquipmentsList ai = new EquipmentsList();
					ai.setEquipment_id(rs.getInt(1));
					ai.setEquipment_name(rs.getString(2));
					//int count = rs.getInt(1); //Метод getInt() возвращает целочисленные переменные, в качестве аргумента принимает номер столбца
					results.add(ai);
				}
					List <EquipmentsList> list;
					Iterator <EquipmentsList> iterator =  list.iterator();
					int i = 0;
					
					while (iterator.hasNext()) {
					i++;
					EquipmentsList item = new EquipmentsList();
					item = (EquipmentsList)iterator.next();
					item.getEquipment_id();
					item.getEquipment_name();
					
					//System.out.println("Total number of equipments in the table : " + count);
				}*/
				while (rs.next()){
					int id_equip = rs.getInt(1);
					String name_equip = rs.getString(2);
					int id_descrip = rs.getInt(3);
					System.out.println("Id equipments : " + id_equip + " Equpment's names: " + name_equip + " Description id: " + id_descrip);
				}
				con.close();
			System.out.println("Disconnected");
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

