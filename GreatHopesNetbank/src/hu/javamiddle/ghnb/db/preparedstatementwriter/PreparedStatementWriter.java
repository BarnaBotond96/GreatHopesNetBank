package hu.javamiddle.ghnb.db.preparedstatementwriter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementWriter<E> {

	void write(PreparedStatement preparedStatement, E entity) throws SQLException;

}