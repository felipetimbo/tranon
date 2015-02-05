package br.ufc.tranon.dao;

public abstract interface BaseDAO
{
	void createStatement() throws Exception;

	void closeStatement() throws Exception;
}