package hu.javamiddle.ghnb.db.resultsetreader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ResultSetReader<E> {

	List<E> read(ResultSet resultSet) throws SQLException;

}