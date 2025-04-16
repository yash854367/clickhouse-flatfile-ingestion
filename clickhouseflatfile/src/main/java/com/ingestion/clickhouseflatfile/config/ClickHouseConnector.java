package com.ingestion.clickhouseflatfile.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClickHouseConnector {

	public static Connection getConnection(String host, int port, String db, String user, String jwtToken) throws SQLException{
		String url = "jdbc:clickhouse://" + host + ":" + port + "/" + db;
		 return DriverManager.getConnection(url, user, jwtToken);
	}
	
}
