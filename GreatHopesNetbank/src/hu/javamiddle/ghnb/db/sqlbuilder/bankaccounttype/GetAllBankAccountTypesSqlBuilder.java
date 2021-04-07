package hu.javamiddle.ghnb.db.sqlbuilder.bankaccounttype;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetAllBankAccountTypesSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM bank_account_type ORDER BY bank_account_type_id";
	}

}