package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class BankAccountDao extends AbstractDatabaseDao<BankAccount> {

	public BankAccountDao(SqlBuilder sqlBuilder, PreparedStatementWriter<BankAccount> preparedStatementWriter) {
		super(sqlBuilder, preparedStatementWriter, null);
	}

	public BankAccountDao(SqlBuilder sqlBuilder, PreparedStatementWriter<BankAccount> preparedStatementWriter, ResultSetReader<BankAccount> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}