package br.com.franca.web.business;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class BusinessFactory {

	private HashMap<String, BusinessGeneric> listBusiness;

	public BusinessFactory() {
		this.listBusiness = new HashMap<>();
	}

	/**
	 * Obtem a inst�ncia de uma classe de neg�cio, de acordo com a classe informada.
	 * 
	 * @param clazz Classe de neg�cio desejada.
	 * @return Inst�ncia da classe de neg�cio ou null se a classe informada n�o
	 *         herdar de BusinessGeneric
	 */
	public <U extends BusinessGeneric> U getBusiness(Class<U> clazz) {
		U retorno = getInstance(clazz);
		if (retorno == null) {
			retorno = createInstance(clazz);
		}
		return retorno;
	}

	/**
	 * Busca uma inst�ncia da classe de neg�cio na listagem.
	 * 
	 * @param clazz Classe de neg�cio desejada.
	 * @return Inst�ncia da classe de neg�cio ou null
	 */
	@SuppressWarnings("unchecked")
	protected <U extends BusinessGeneric> U getInstance(Class<U> clazz) {
		U retorno = (U) listBusiness.get(clazz.getName());
		return retorno;
	}

	/**
	 * Cria uma inst�ncia da classe de neg�cio.
	 * 
	 * @param clazz Classe de neg�cio desejada.
	 * @return Inst�ncia da classe de neg�cio ou null
	 */
	protected <U extends BusinessGeneric> U createInstance(Class<U> clazz) {
		U retorno = null;
		try {
			Constructor<U> construtor = clazz.getDeclaredConstructor(BusinessFactory.class);
			construtor.setAccessible(true);
			retorno = construtor.newInstance();
			// retorno = construtor.newInstance(this);
			if (retorno != null) {
				addInstanceList(clazz.getName(), retorno);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error(e, e);
		}
		return retorno;
	}

	/**
	 * Adiciona inst�ncia �nica a lista
	 * 
	 * @param className Nome da classe que gerou a inst�ncia
	 * @param instance  Inst�ncia que ser� adicionada
	 */
	protected void addInstanceList(String className, BusinessGeneric instance) {
		this.listBusiness.put(className, instance);
	}
}
