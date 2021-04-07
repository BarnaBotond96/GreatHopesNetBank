package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetUsersSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM users";
	}

}