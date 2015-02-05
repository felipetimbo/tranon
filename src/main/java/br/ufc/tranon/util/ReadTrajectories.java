package br.ufc.tranon.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ReadTrajectories
{
	public static void main(String[] args)
	{
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost/tranon";
		String user = "postgres";
		String password = "postgres";
		String path = "C:\\Texto";
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			System.err.println("Can't load driver " + e.getMessage());
		}
		try
		{
			Connection con = DriverManager.getConnection(url, user, password);
			System.err.println("Conection OK");

			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();

			Statement stmt = con.createStatement();

			System.out.println("Copy started");
			for (int i = 0; i < listOfFiles.length; i++) {
				try
				{
					stmt.executeUpdate("COPY trajectories (taxi_id, date_time, longitude, latitude) FROM '" + listOfFiles[i] + "' (DELIMITER(','));");
				}
				catch (Exception e)
				{
					System.err.println(e.getMessage());
				}
			}
			System.out.println("Copy finished");

			con.close();
		}
		catch (Exception e)
		{
			System.err.println("Connection Attempt failed");
			System.err.println(e.getMessage());
		}
	}
}
