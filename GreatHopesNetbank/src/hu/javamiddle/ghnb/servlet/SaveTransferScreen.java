package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.db.dao.BankAccountDao;
import hu.javamiddle.ghnb.db.dao.TransactionDao;
import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.entity.Transaction;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.GetBankAccountByBankAccountNumberPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.UpdateBankAccountCurrentBalancePreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.transaction.CreateTransactionPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.bankaccount.FullBankAccountResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.GetBankAccountByBankAccountNumberSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.UpdateBankAccountCurrentBalanceSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.transaction.CreateTransactionSqlBuilder;

public class SaveTransferScreen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String toBankAccountNumber = request.getParameter("toBankAccountNumber");
		String beneficiaryName = request.getParameter("beneficiaryName");
		String amountAsString = request.getParameter("amount");
		String transactionComment = request.getParameter("transactionComment");

		Long amount = Long.parseLong(amountAsString);

		BankAccountDao queryBankAccountDao = new BankAccountDao(new GetBankAccountByBankAccountNumberSqlBuilder(), new GetBankAccountByBankAccountNumberPreparedStatementWriter(),
			new FullBankAccountResultSetReader());
		BankAccount fromBankAccount = queryBankAccountDao.retrieve(BankAccount.builder()
			.withBankAccountNumber(bankAccountNumber)
			.build()).get(0);
		BankAccount toBankAccount = queryBankAccountDao.retrieve(BankAccount.builder()
			.withBankAccountNumber(toBankAccountNumber)
			.build()).get(0);

		Map<String, String> validationErrors = new HashMap<>();
		if (fromBankAccount.getCurrentBalance() < amount) {
			validationErrors.put("amountTooMuch", "Nincs elég fedezete.");
		}
		
		List<BankAccount> retrievedToBankAccounts = queryBankAccountDao.retrieve(BankAccount.builder()
			.withBankAccountNumber(toBankAccountNumber)
			.build());
		
		if (retrievedToBankAccounts.isEmpty()) {
			validationErrors.put("toBankAccountNumberNotExist", "Nem létező számlaszámot adott meg.");
		}

		if (validationErrors.isEmpty()) {
			Transaction transaction = Transaction.builder()
				.withFromBankAccountNumber(bankAccountNumber)
				.withToBankAccountNumber(toBankAccountNumber)
				.withBeneficiaryName(beneficiaryName)
				.withAmount(amount)
				.withTransactionComment(transactionComment)
				.build();
			fromBankAccount.setCurrentBalance(fromBankAccount.getCurrentBalance() - amount);
			toBankAccount.setCurrentBalance(toBankAccount.getCurrentBalance() + amount);
			TransactionDao transactionDao = new TransactionDao(new CreateTransactionSqlBuilder(), new CreateTransactionPreparedStatementWriter());
			BankAccountDao updateBankAccountDao = new BankAccountDao(new UpdateBankAccountCurrentBalanceSqlBuilder(), new UpdateBankAccountCurrentBalancePreparedStatementWriter());
			
			transactionDao.create(transaction);
			updateBankAccountDao.update(fromBankAccount);
			updateBankAccountDao.update(toBankAccount);
			response.sendRedirect("userHome.jsp?message=successfulTransfer");
		} else {
			request.setAttribute("validationErrors", validationErrors);
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}