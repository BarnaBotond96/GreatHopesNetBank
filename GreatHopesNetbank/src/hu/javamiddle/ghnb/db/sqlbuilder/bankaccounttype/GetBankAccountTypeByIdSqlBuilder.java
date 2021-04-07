package hu.javamiddle.ghnb.db.sqlbuilder.bankaccounttype;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetBankAccountTypeByIdSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM bank_account_type WHERE bank_account_type_id = ?";
	}

}