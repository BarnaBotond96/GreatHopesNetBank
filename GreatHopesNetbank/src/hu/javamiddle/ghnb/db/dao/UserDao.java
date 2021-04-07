package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.User;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class UserDao extends AbstractDatabaseDao<User> {

	public UserDao(SqlBuilder sqlBuilder, PreparedStatementWriter<User> preparedStatementWriter) {
		super(sqlBuilder, preparedStatementWriter, null);
	}

	public UserDao(SqlBuilder sqlBuilder, PreparedStatementWriter<User> preparedStatementWriter, ResultSetReader<User> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}