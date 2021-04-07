package hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class UpdateBankAccountCurrentBalancePreparedStatementWriter implements PreparedStatementWriter<BankAccount> {

	@Override
	public void write(PreparedStatement preparedStatement, BankAccount entity) throws SQLException {
		preparedStatement.setLong(1, entity.getCurrentBalance());
		preparedStatement.setString(2, entity.getBankAccountNumber());
	}

}