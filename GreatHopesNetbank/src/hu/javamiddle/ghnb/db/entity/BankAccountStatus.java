package hu.javamiddle.ghnb.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BankAccountStatus {

	private Long id;
	private String name;
	private String displayName;

}