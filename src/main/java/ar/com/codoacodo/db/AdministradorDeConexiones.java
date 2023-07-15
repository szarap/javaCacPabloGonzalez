package ar.com.codoacodo.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
    
	public static Connection getConnection() {
		String hosts = "127.0.0.1"; 
		String port = "3306";
		String password = "root";
		String username = "root";
		
		//driver de conexion a db
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		//nombre db
		String dbName = "cac23049";
		
		Connection connection;
		try {
			Class.forName(driverClassName);
			
			String url = "jdbc:mysql://" + hosts + ":"+ port +"/"+ dbName +"?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
			
			connection = DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
            System.out.println("Ups! ocurrió un error, ve y consultale a Carlos" + e.getMessage()); 
			connection = null;
		}
		
		return connection;
	}
}
