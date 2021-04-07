package hu.javamiddle.ghnb.db.sqlbuilder.bankaccount;

import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public class UpdateBankAccountAliasNameSqlBuilder implements SqlBuilder {

	@Override
	public String build() {
		return "UPDATE bank_account SET alias_name = ? WHERE bank_account_number = ?";
	}

}