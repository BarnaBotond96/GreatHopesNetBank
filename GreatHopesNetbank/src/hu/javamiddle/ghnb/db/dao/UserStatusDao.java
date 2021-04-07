package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.UserStatus;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class UserStatusDao extends AbstractDatabaseDao<UserStatus> {

	public UserStatusDao(SqlBuilder sqlBuilder, PreparedStatementWriter<UserStatus> preparedStatementWriter, ResultSetReader<UserStatus> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}