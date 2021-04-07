package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.BankAccountStatus;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class BankAccountStatusDao extends AbstractDatabaseDao<BankAccountStatus> {

	public BankAccountStatusDao(SqlBuilder sqlBuilder, PreparedStatementWriter<BankAccountStatus> preparedStatementWriter, ResultSetReader<BankAccountStatus> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}