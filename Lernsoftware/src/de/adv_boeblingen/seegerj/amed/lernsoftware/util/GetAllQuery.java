package de.adv_boeblingen.seegerj.amed.lernsoftware.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.adv_boeblingen.seegerj.amed.lernsoftware.controller.DatabaseController.DatabaseRunnable;

public class GetAllQuery<T> implements DatabaseRunnable<List<T>> {
	Class<T> clazz;

	public GetAllQuery(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<T> run(EntityManager manager, EntityTransaction transaction) {
		String queryString = String.format(Constants.BASIC_QUERY,
				this.clazz.getName());

		Query query = manager.createQuery(queryString);

		@SuppressWarnings("unchecked")
		List<T> all = query.getResultList();
		if (all == null) {
			return new ArrayList<T>();
		}
		return all;
	}
}
