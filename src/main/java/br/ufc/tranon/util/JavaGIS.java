package br.ufc.tranon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.postgis.PGgeometry;
import org.postgresql.PGConnection;

@SuppressWarnings("deprecation")
public class JavaGIS
{
	
	public static void main(String[] args)
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/tranon";
			Connection conn = DriverManager.getConnection(url, "postgres", "postgres");

			((PGConnection)conn).addDataType("geometry", "org.postgis.PGgeometry");
			((PGConnection)conn).addDataType("box3d", "org.postgis.PGbox3d");

			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("select * from taxi_routes where taxi_id = 2");
			while (r.next())
			{
				PGgeometry geom = (PGgeometry)r.getObject(2);
				int id = r.getInt(1);
				System.out.println("Row " + id + ":");
				System.out.println(geom.toString());
			}
			s.close();
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
