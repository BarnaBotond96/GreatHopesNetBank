package hu.javamiddle.ghnb.db.dao;

import java.util.List;

/**
 * CRUD operations = Create Retrieve Update Delete
 */

public interface DatabaseDao<E> {

	void create(E entity);

	List<E> retrieve(E entity);

	void update(E entity);

}