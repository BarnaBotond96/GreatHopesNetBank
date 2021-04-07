package hu.javamiddle.ghnb;

public enum BankAccountType {

	NORMAL(1),
	DEBIT(2),
	BUSINESS(3);

	private final long bankAccountTypeId;

	private BankAccountType(long bankAccountTypeId) {
		this.bankAccountTypeId = bankAccountTypeId;
	}

	public long getBankAccountTypeId() {
		return bankAccountTypeId;
	}

	public static BankAccountType getById(long bankAccountTypeId) {
		for (BankAccountType bankAccountType : values()) {
			if (bankAccountType.getBankAccountTypeId() == bankAccountTypeId) {
				return bankAccountType;
			}
		}
		throw new IllegalArgumentException("Érvénytelen bankszámla típus azonosító.");
	}

}