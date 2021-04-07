package hu.javamiddle.ghnb.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.javamiddle.ghnb.BankAccountStatus;
import hu.javamiddle.ghnb.BankAccountType;
import hu.javamiddle.ghnb.db.dao.BankAccountDao;
import hu.javamiddle.ghnb.db.entity.BankAccount;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.CreateBankAccountPreparedStatementWriter;
import hu.javamiddle.ghnb.db.preparedstatementwriter.bankaccount.GetBankAccountByBankAccountNumberPreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.bankaccount.FullBankAccountResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.CreateBankAccountSqlBuilder;
import hu.javamiddle.ghnb.db.sqlbuilder.bankaccount.GetBankAccountByBankAccountNumberSqlBuilder;

public class CreateBankAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankAccountNumber = request.getParameter("bankAccountNumber");
		String aliasName = request.getParameter("aliasName");
		String selectedClientLoginName = request.getParameter("selectedClientLoginName");
		String startingMoneyAmountAsString = request.getParameter("startingMoneyAmount");
		String currencyType = request.getParameter("currencyType");
		String selectedBankAccountTypeIdAsString = request.getParameter("selectedBankAccountTypeId");

		BankAccountDao queryBankAccountDao = new BankAccountDao(new GetBankAccountByBankAccountNumberSqlBuilder(), new GetBankAccountByBankAccountNumberPreparedStatementWriter(),
			new FullBankAccountResultSetReader());
		List<BankAccount> retrievedBankAccounts = queryBankAccountDao.retrieve(BankAccount.builder()
			.withBankAccountNumber(bankAccountNumber)
			.build());

		Map<String, String> validationErrors = new HashMap<>();
		if (!retrievedBankAccounts.isEmpty()) {
			validationErrors.put("bankAccountNumberWrong", "Foglalt bankszámlaszám.");
		}
		
		if (!bankAccountNumber.matches("^[0-9]{8}-[0-9]{8}-[0-9]{8}")) {
			validationErrors.put("bankAccountNumberInvalid", "Érvénytelen bankszámlaszám");
		}

		if (aliasName == null || aliasName.isBlank()) {
			validationErrors.put("aliasNameInvalid", "Kötelező mező.");
		}

		Long startingMoneyAmount = null;
		if (startingMoneyAmountAsString == null || startingMoneyAmountAsString.isBlank()) {
			validationErrors.put("startingMoneyAmountInvalid", "Kötelező mező.");
		} else {
			startingMoneyAmount = Long.parseLong(startingMoneyAmountAsString);
			if (startingMoneyAmount < 0 || startingMoneyAmount > 2_000_000) {
				validationErrors.put("startingMoneyAmountInvalid", "A kezdő egyenleg nem lehet negatív és nem lehet 2 000 000-nál nagyobb.");
			}
		}

		if (selectedClientLoginName == null) {
			validationErrors.put("selectedClientLoginNameInvalid", "Kérem, válasszon a lehetőségek közül!");
		}
		Long selectedBankAccountTypeId = Long.parseLong(selectedBankAccountTypeIdAsString);
		BankAccountType bankAccountType = null;
		if (selectedBankAccountTypeId == 0) {
			validationErrors.put("selectedBankAccountTypeInvalid", "Kérem, válasszon a lehetőségek közül!");
		} else {
			bankAccountType = BankAccountType.getById(selectedBankAccountTypeId);
		}

		if (validationErrors.isEmpty()) {
			BankAccount bankAccount = BankAccount.builder()
				.withBankAccountNumber(bankAccountNumber)
				.withAliasName(aliasName)
				.withBankAccountStatus(BankAccountStatus.ACTIVE)
				.withCurrentBalance(startingMoneyAmount)
				.withCurrencyType(currencyType)
				.withBankAccountType(bankAccountType)
				.withLoginName(selectedClientLoginName)
				.build();
			BankAccountDao createBankAccountDao = new BankAccountDao(new CreateBankAccountSqlBuilder(), new CreateBankAccountPreparedStatementWriter());
			createBankAccountDao.create(bankAccount);
			response.sendRedirect("adminHome.jsp?message=successfulBankAccountCreation");
		} else {
			request.setAttribute("validationErrors", validationErrors);
			request.getRequestDispatcher("loadCreateBankAccount").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}