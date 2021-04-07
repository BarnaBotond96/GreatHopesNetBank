package hu.javamiddle.ghnb.db.entity;

import hu.javamiddle.ghnb.BankAccountStatus;
import hu.javamiddle.ghnb.BankAccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BankAccount {

	private String loginName;
	private String aliasName;
	@Setter
	private Long currentBalance;
	private String currencyType;
	private String bankAccountNumber;
	private BankAccountType bankAccountType;
	private BankAccountStatus bankAccountStatus;

	private BankAccount(Builder builder) {
		this.loginName = builder.loginName;
		this.aliasName = builder.aliasName;
		this.currentBalance = builder.currentBalance;
		this.currencyType = builder.currencyType;
		this.bankAccountNumber = builder.bankAccountNumber;
		this.bankAccountType = builder.bankAccountType;
		this.bankAccountStatus = builder.bankAccountStatus;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String loginName;
		private String aliasName;
		private String currencyType;
		private Long currentBalance;
		private String bankAccountNumber;
		private BankAccountType bankAccountType;
		private BankAccountStatus bankAccountStatus;

		public Builder() {
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder withAliasName(String aliasName) {
			this.aliasName = aliasName;
			return this;
		}

		public Builder withCurrencyType(String currencyType) {
			this.currencyType = currencyType;
			return this;
		}

		public Builder withCurrentBalance(Long currentBalance) {
			this.currentBalance = currentBalance;
			return this;
		}

		public Builder withBankAccountNumber(String bankAccountNumber) {
			this.bankAccountNumber = bankAccountNumber;
			return this;
		}

		public Builder withBankAccountType(BankAccountType bankAccountType) {
			this.bankAccountType = bankAccountType;
			return this;
		}

		public Builder withBankAccountStatus(BankAccountStatus bankAccountStatus) {
			this.bankAccountStatus = bankAccountStatus;
			return this;
		}

		public BankAccount build() {
			return new BankAccount(this);
		}

	}

}