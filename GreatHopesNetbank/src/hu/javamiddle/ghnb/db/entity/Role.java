package hu.javamiddle.ghnb.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Role {

	private Long id;
	private String name;
	private String displayName;

}