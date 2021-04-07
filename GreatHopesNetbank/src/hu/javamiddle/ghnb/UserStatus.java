package hu.javamiddle.ghnb;

public enum UserStatus {

	PENDING(1),
	ACTIVE(2),
	INACTIVE(3);

	private final long userStatusId;

	private UserStatus(long userStatusId) {
		this.userStatusId = userStatusId;
	}

	public long getUserStatusId() {
		return userStatusId;
	}

	public static UserStatus getById(long userStatusId) {
		for (UserStatus userStatus : values()) {
			if (userStatus.getUserStatusId() == userStatusId) {
				return userStatus;
			}
		}
		throw new IllegalArgumentException("Érvénytelen felhasználói státusz azonosító.");
	}

}