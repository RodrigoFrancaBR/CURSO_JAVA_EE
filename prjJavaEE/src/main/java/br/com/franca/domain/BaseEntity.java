package br.com.franca.domain;
/**
 * 
 * @author rfranca
 * DAO gen�rica aceita todo tipo de entidade que implemente esta interface e atenda aos requisito de IS-A BaseEntity. 
 */
public interface BaseEntity<T> {
	public T getId();
}