package hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccountstatus;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import hu.javamiddle.ghnb.db.entity.BankAccountStatus;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;

public class GetBankAccountStatusByIdPreparedStatementWriter implements PreparedStatementWriter<BankAccountStatus> {

	@Override
	public void write(PreparedStatement preparedStatement, BankAccountStatus entity) throws SQLException {
		preparedStatement.setLong(1, entity.getId());
	}

}