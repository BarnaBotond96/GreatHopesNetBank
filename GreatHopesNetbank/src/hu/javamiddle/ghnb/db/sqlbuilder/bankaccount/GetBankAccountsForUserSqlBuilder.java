package hu.javamiddle.ghnb.db.sqlbuilder.bankaccount;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetBankAccountsForUserSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM bank_account WHERE login_name = ?";
	}

}