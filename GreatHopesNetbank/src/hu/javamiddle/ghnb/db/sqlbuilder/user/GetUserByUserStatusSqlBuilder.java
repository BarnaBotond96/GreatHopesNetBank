package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetUserByUserStatusSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM users WHERE user_status_id = ?";
	}

}