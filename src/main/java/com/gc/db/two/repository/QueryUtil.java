package com.gc.db.two.repository;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jegatheesh.mageswaran <br>
 * 			Created on <b>07-Apr-2020</b>
 *
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryUtil {
	/**
	 * Method to skip NoResultException with default value
	 * 
	 * @param <T>
	 * @param query
	 * @param clazz
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T skipNRE(Query<?> query, Class<T> clazz, T defaultValue) {
		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			log.info("NoResultException occured");
			return defaultValue;
		}
	}

	/**
	 * Method to skip NoResultException with default value and Result Transform
	 * 
	 * @param <T>
	 * @param query
	 * @param clazz
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T skipNREWithRT(Query<?> query, Class<T> clazz, T defaultValue) {
		try {
			return (T) query.setResultTransformer(Transformers.aliasToBean(clazz)).getSingleResult();
		} catch (NoResultException e) {
			log.info("NoResultException occured");
			return defaultValue;
		}
	}

	/**
	 * Transform Alias to bean
	 * 
	 * @param <T>
	 * @param query
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static <T> Query<T> transform(Query<T> query, Class<T> clazz) {
		return query.setResultTransformer(Transformers.aliasToBean(clazz));
	}

}
