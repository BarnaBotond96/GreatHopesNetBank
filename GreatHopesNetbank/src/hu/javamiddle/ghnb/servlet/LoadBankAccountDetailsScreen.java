package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.BankAccountStatus;
import hu.javamiddle.ghnb.BankAccountType;
import hu.javamiddle.ghnb.db.dao.BankAccountDao;
import hu.javamiddle.ghnb.db.dao.BankAccountStatusDao;
import hu.javamiddle.ghnb.db.dao.BankAccountTypeDao;
import hu.javamiddle.ghnb.db.dao.TransactionDao;
import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.GetBankAccountByBankAccountNumberPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccountstatus.GetBankAccountStatusByIdPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccounttype.GetBankAccountTypeByIdPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.transaction.GetLastTransactionTimestampByBankAccountNumberPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.bankaccount.FullBankAccountResultSetReader;
import hu.javamiddle.ghnb.db.resultsetreader.bankaccountstatus.FullBankAccountStatusResultSetReader;
import hu.javamiddle.ghnb.db.resultsetreader.bankaccounttype.FullBankAccountTypeResultSetReader;
import hu.javamiddle.ghnb.db.resultsetreader.transaction.TransactionTimestampResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.GetBankAccountByBankAccountNumberSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccountstatus.GetBankAccountStatusByIdSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccounttype.GetBankAccountTypeByIdSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.transaction.GetLastTransactionTimestampByBankAccountNumberSqlBuilder;
import hu.javamiddle.ghnb.dto.BankAccountDetailsDto;

public class LoadBankAccountDetailsScreen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		BankAccount bankAccount = BankAccount.builder()
				.withBankAccountNumber(bankAccountNumber)
				.build();
		BankAccountDao bankAccountDao = new BankAccountDao(new GetBankAccountByBankAccountNumberSqlBuilder(), new GetBankAccountByBankAccountNumberPreparedStatementWriter(),
			new FullBankAccountResultSetReader());
		List<BankAccount> results = bankAccountDao.retrieve(bankAccount);
		BankAccount result = results.get(0);

		TransactionDao transactionDao = new TransactionDao(new GetLastTransactionTimestampByBankAccountNumberSqlBuilder(),
			new GetLastTransactionTimestampByBankAccountNumberPreparedStatementWriter(bankAccountNumber), new TransactionTimestampResultSetReader());
		List<Transaction> transactions = transactionDao.retrieve(null);
		String lastTransactionTimestamp = transactions.isEmpty() ? "Nincs ilyen" : transactions.get(0).getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd. hh:mm:ss"));

		BankAccountStatus bankAccountStatus = result.getBankAccountStatus();
		BankAccountStatusDao bankAccountStatusDao = new BankAccountStatusDao(new GetBankAccountStatusByIdSqlBuilder(), new GetBankAccountStatusByIdPreparedStatementWriter(),
			new FullBankAccountStatusResultSetReader());
		List<hu.javamiddle.ghnb.db.entity.BankAccountStatus> bankAccountStatusResults = bankAccountStatusDao
			.retrieve(new hu.javamiddle.ghnb.db.entity.BankAccountStatus(bankAccountStatus.getBankAccountStatusId(), null, null));

		BankAccountType bankAccountType = result.getBankAccountType();
		BankAccountTypeDao bankAccountTypeDao = new BankAccountTypeDao(new GetBankAccountTypeByIdSqlBuilder(), new GetBankAccountTypeByIdPreparedStatementWriter(),
			new FullBankAccountTypeResultSetReader());
		List<hu.javamiddle.ghnb.db.entity.BankAccountType> bankAccountTypeResults = bankAccountTypeDao
			.retrieve(new hu.javamiddle.ghnb.db.entity.BankAccountType(bankAccountType.getBankAccountTypeId(), null));

		BankAccountDetailsDto bankAccountDetailsDto = BankAccountDetailsDto.builder()
				.withBankAccountNumber(result.getBankAccountNumber())
				.withAliasName(result.getAliasName())
				.withCurrentBalance(result.getCurrentBalance())
				.withCurrencyType(result.getCurrencyType())
				.withBankAccountStatus(bankAccountStatusResults.get(0)
				.getDisplayName())
				.withBankAccountType(bankAccountTypeResults.get(0).getName())
				.withLastTransactionTimestamp(lastTransactionTimestamp)
				.build();

		request.setAttribute("bankAccountDetailsDto", bankAccountDetailsDto);
		request.getRequestDispatcher("bankAccountDetails.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}