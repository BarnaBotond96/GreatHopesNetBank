package hu.javamiddle.ghnb.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hu.javamiddle.ghnb.GHNBRuntimeException;
import hu.javamiddle.ghnb.db.preparedstatementwriter.PreparedStatementWriter;
import hu.javamiddle.ghnb.db.resultsetreader.ResultSetReader;
import hu.javamiddle.ghnb.db.sqlbuilder.SqlBuilder;

public abstract class AbstractDatabaseDao<E> implements DatabaseDao<E> {

	private SqlBuilder sqlBuilder;
	private PreparedStatementWriter<E> preparedStatementWriter;
	private ResultSetReader<E> resultSetReader;

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/GreatHopesNetbank";
	private static final String USER_NAME = "postgres";
	private static final String PASSWORD = "admin";

	public AbstractDatabaseDao(SqlBuilder sqlBuilder, PreparedStatementWriter<E> preparedStatementWriter, ResultSetReader<E> resultSetReader) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new GHNBRuntimeException("Az adatbázis illesztőprogramot nem sikerült betölteni.");
		}
		this.sqlBuilder = sqlBuilder;
		this.preparedStatementWriter = preparedStatementWriter;
		this.resultSetReader = resultSetReader;
	}

	@Override
	public void create(E entity) {
		try (Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); 
			PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.build())) {
			preparedStatementWriter.write(preparedStatement, entity);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new GHNBRuntimeException("Hiba történt a rekord adatbázisba való beillesztése során.", e);
		}
	}

	@Override
	public List<E> retrieve(E entity) {
		try (Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); 
			PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.build())) {
			preparedStatementWriter.write(preparedStatement, entity);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSetReader.read(resultSet);
		} catch (SQLException e) {
			throw new GHNBRuntimeException("Hiba történt az adatbázisból való lekérdezés közben.", e);
		}
	}

	@Override
	public void update(E entity) {
		try (Connection connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); 
			PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.build())) {
			preparedStatementWriter.write(preparedStatement, entity);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new GHNBRuntimeException("Hiba történt az adatbázis rekord frissítése során", e);
		}
	}

}