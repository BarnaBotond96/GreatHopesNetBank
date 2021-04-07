package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.BankAccountType;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class BankAccountTypeDao extends AbstractDatabaseDao<BankAccountType> {

	public BankAccountTypeDao(SqlBuilder sqlBuilder, PreparedStatementWriter<BankAccountType> preparedStatementWriter, ResultSetReader<BankAccountType> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}