package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class TransactionDao extends AbstractDatabaseDao<Transaction> {

	public TransactionDao(SqlBuilder sqlBuilder, PreparedStatementWriter<Transaction> preparedStatementWriter) {
		super(sqlBuilder, preparedStatementWriter, null);
	}

	public TransactionDao(SqlBuilder sqlBuilder, PreparedStatementWriter<Transaction> preparedStatementWriter, ResultSetReader<Transaction> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}