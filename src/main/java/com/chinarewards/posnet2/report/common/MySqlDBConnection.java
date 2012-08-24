package com.chinarewards.posnet2.report.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MySqlDBConnection {

	private static int maxConnectNum = 5;

	private static List<Connection> connectPool = new ArrayList<Connection>();

	private static Boolean flag = Boolean.TRUE;

	public static Connection getConnectionFromDatabase() {
		Connection trueConn = null;
		try {
			String ip = "119.146.223.2";
			String dbname = "posnet";
			String username = "report";
			String password = "posnet";
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+ip+":3306/"+dbname;
			trueConn = DriverManager.getConnection(url, username, password);
		} catch (Exception ex) {
			System.out.println("数据连接出错了:" + ex.toString());
		}
		return trueConn;
	}

	public static void initialize() {
		if (flag == Boolean.TRUE) {
			// 这里建立所有的连接;
			for (int i = 0; i < maxConnectNum; i++) {
				connectPool.add(i, getConnectionFromDatabase());
			}
			flag = Boolean.FALSE;
		}
	}

	// 从连接池中取得一个可用的连接
	public static Connection getConnection() {
		Connection conn = null;
		if (connectPool.size() == 0) {
			try {
				java.lang.Thread.sleep(100);
				getConnection();
			} catch (InterruptedException ex) {
				System.out.println("连接全部用光,这里sleep出错了.");
			}
		} else {
			conn = (Connection) connectPool.remove(0);
		}
		return conn;
	}

	// 提供给外部程序调用,不用的连接放回连接池当中...
	public static Boolean release(Connection conn) {
		Boolean res = false;
		try {
			if(conn != null){
				if(conn.isClosed() == true){
					connectPool.remove(conn);
					connectPool.add(getConnectionFromDatabase());
					res = true;
				}else {
					res = connectPool.add(conn);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
