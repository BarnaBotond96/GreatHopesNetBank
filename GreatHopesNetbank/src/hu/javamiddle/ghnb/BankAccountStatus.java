package hu.javamiddle.ghnb;

public enum BankAccountStatus {

	ACTIVE(1),
	INACTIVE(2);

	private final long bankAccountStatusId;

	private BankAccountStatus(long bankAccountStatusId) {
		this.bankAccountStatusId = bankAccountStatusId;
	}

	public long getBankAccountStatusId() {
		return bankAccountStatusId;
	}

	public static BankAccountStatus getById(long bankAccountStatusId) {
		for (BankAccountStatus bankAccountStatus : values()) {
			if (bankAccountStatus.getBankAccountStatusId() == bankAccountStatusId) {
				return bankAccountStatus;
			}
		}
		throw new IllegalArgumentException("Érvénytelen bankszámla státusz azonosító.");
	}

}