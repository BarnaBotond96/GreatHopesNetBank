package hu.javamiddle.ghnb.db.dao;

import hu.javamiddle.ghnb.db.entity.Role;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class RoleDao extends AbstractDatabaseDao<Role> {

	public RoleDao(SqlBuilder sqlBuilder, PreparedStatementWriter<Role> preparedStatementWriter) {
		super(sqlBuilder, preparedStatementWriter, null);
	}

	public RoleDao(SqlBuilder sqlBuilder, PreparedStatementWriter<Role> preparedStatementWriter, ResultSetReader<Role> resultSetReader) {
		super(sqlBuilder, preparedStatementWriter, resultSetReader);
	}

}