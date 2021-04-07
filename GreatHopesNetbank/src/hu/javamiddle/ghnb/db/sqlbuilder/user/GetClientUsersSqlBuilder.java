package hu.javamiddle.ghnb.db.sqlbuilder.user;

import hu.javamiddle.ghnb.Role;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetClientUsersSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM users WHERE role_id = " + Role.USER.getRoleId();
	}

}