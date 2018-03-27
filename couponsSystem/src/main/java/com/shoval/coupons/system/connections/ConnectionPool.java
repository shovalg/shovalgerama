package com.shoval.coupons.system.connections;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.shoval.coupons.system.exceptions.SystemShutDownException;

/**
 * <br>This class is responsible for the limitation of DB entrances.</br>
 * The maximum is five entrances
 * @author Shoval_G
 * @category ConnectionPool
 */
@Component
public class ConnectionPool {
	
	private static final int NUMBER_OF_CONNECTIONS = 5;
	private static final int SLEEP_TIME_SECONDS = 30;
	private boolean SHUTDOWN = false;
	private static ConnectionPool _INSTANCE = null;
	private ArrayList<SyncObject> connections = null;
	
	private ConnectionPool() 
	{
		this.connections = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) 
		{
			SyncObject dbConnection  = new SyncObject();
			this.connections.add(dbConnection );
		}
	}
	
	/**
	 * This function is part of singleton pattern and responsible for creating an instance of CoonectionPool. 
	 * @return _INSTANCE - instance of connection
	 */
	public static synchronized ConnectionPool getInstance()
	{
		if(_INSTANCE == null)
		{
			_INSTANCE = new ConnectionPool();
		}
		return _INSTANCE;
	}
	
	public boolean isSHUTDOWN() 
	{
		return this.SHUTDOWN;
	}

	/**
	 * <br>This function is responsible for giving one DB connection.<br>
	 * When a connection is given there is a subtraction of one connection from connections (ArrayList) pool.
	 * @return connection - DB connection.
	 * @throws InterruptedException
	 */
	public synchronized SyncObject getConnection() throws InterruptedException
	{
		if(this.SHUTDOWN == true)
		{
			throw new SystemShutDownException("System is shutting down");
		}
		while (this.connections.isEmpty()) 
		{
			System.out.println("Thread " + Thread.currentThread().getName()
								+ " is now Waiting since no available connections...");
			wait();
		}
		SyncObject connection = this.connections.get(0);
		this.connections.remove(0);
		System.out.println("Giving connection : " + connection + " to Thread " + 
							Thread.currentThread().getName());
		return connection;
	}
	
	/**
	 * <br>This function is responsible for returning one DB connection.<br>
	 * When a connection is returned it is added to the connections (ArrayList) pool.
	 * @param connection - one connection from connections (ArrayList) pool.
	 */
	public synchronized void returnConnection(SyncObject connection)
	{
		this.connections.add(connection);
		System.out.println(Thread.currentThread().getName() + " is Calling notify!");
		notify();
	}
	
	public void shutdown()
	{
		this.SHUTDOWN = true;
		if(this.connections.size() == NUMBER_OF_CONNECTIONS)
		{
			this.connections.clear();
			System.exit(0);
		}
		
		try 
		{
			Thread.sleep(1000 * this.SLEEP_TIME_SECONDS);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		this.connections.clear();
		System.exit(-1);
	}
}