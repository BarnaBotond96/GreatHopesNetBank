package hu.javamiddle.ghnb;

public enum Role {

	ADMIN(1),
	USER(2);

	private final long roleId;

	private Role(long roleId) {
		this.roleId = roleId;
	}

	public long getRoleId() {
		return roleId;
	}

	public static Role getById(long roleId) {
		for (Role role : values()) {
			if (role.getRoleId() == roleId) {
				return role;
			}
		}
		throw new IllegalArgumentException("Érvénytelen szerepkör azonosító.");
	}

}