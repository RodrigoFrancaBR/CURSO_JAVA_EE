package br.com.franca.domain;

import java.io.Serializable;

/**
 * 
 * @author rfranca DAO genérica aceita todo tipo de entidade que implemente esta
 *         interface e atenda aos requisito de IS-A BaseEntity.
 */
public interface BaseEntity<T> extends Serializable {
	public T getId();
}
