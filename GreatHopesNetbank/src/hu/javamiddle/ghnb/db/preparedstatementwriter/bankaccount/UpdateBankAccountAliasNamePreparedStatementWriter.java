package hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class UpdateBankAccountAliasNamePreparedStatementWriter implements PreparedStatementWriter<BankAccount> {

	@Override
	public void write(PreparedStatement preparedStatement, BankAccount entity) throws SQLException {
		preparedStatement.setString(1, entity.getAliasName());
		preparedStatement.setString(2, entity.getBankAccountNumber());
	}

}