package hu.javamiddle.ghnb.db.sqlbuilder.role;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetAllRolesSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM roles";
	}

}