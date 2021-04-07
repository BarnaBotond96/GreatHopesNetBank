package hu.javamiddle.ghnb.db.sqlbuilder.bankaccount;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class GetBankAccountByBankAccountNumberSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "SELECT * FROM bank_account WHERE bank_account_number = ?";
	}

}