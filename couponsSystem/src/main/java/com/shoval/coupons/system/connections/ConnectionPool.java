package com.shoval.coupons.system.connections;
//package com.example.demo.connections;
//
//import java.util.ArrayList;
//
//public class ConnectionPool {
//	
//	private static final int NUMBER_OF_CONNECTIONS = 5;
//	private static final int postgresqlPort = 5432; //If not available use 5433
//	private static ConnectionPool _INSTANCE = null;
//	private ArrayList<DBConnection> connections = null;
//	
//	//initiate five BDConnection
//	//what is the DB port number?
//	private ConnectionPool() 
//	{
//		this.connections = new ArrayList<>();
//		for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) 
//		{
//			DBConnection newConnection = new DBConnection(postgresqlPort,
//					                                      "jdbc:postgresql://localhost/Coupons");
//			this.connections.add(newConnection);
//		}
//	
//	}
//	
//	public synchronized static ConnectionPool getInstance()
//	{
//		if(_INSTANCE == null)
//		{
//			_INSTANCE = new ConnectionPool();
//		}
//		return _INSTANCE;
//	}
//	
//	//A connection yet to be removed
//	public DBConnection getConnection()
//	{
//		return this.connections.get(0);
//	}
//	
//	public void returnConnection(DBConnection connection)
//	{
//		this.connections.add(connection);
//	}
//	
//	public void closeAllConnections()
//	{
//		this.connections.clear();
//	}
//
//}
