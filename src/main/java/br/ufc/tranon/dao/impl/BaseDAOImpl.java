package br.ufc.tranon.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.postgresql.PGConnection;

import br.ufc.tranon.dao.BaseDAO;

@SuppressWarnings("deprecation")
public class BaseDAOImpl implements BaseDAO
{
	private static final String ORG_POSTGRESQL_DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/tranon";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	private Connection conn;
	protected Statement s;

	public void createStatement()
			throws Exception
	{
		Class.forName(ORG_POSTGRESQL_DRIVER);
		this.conn = DriverManager.getConnection(URL, USER, PASS);

		((PGConnection)this.conn).addDataType("geometry", "org.postgis.PGgeometry");
		((PGConnection)this.conn).addDataType("box3d", "org.postgis.PGbox3d");

		this.s = this.conn.createStatement();
	}

	public void closeStatement()
			throws Exception
	{
		this.s.close();
		this.conn.close();
	}
}
