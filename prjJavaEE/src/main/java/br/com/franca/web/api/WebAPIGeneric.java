package br.com.franca.web.api;

import br.com.franca.domain.BaseEntity;

public abstract class WebAPIGeneric<Dominio extends BaseEntity<Long>> {

	protected String endPoint;
	protected String uri = "/curso/";

	public String getUri(String endPoint) {
		return this.uri + endPoint;
	}
	
	protected boolean domainIsNull(Dominio dominio) {
		return dominio == null ? true : false;
	}

}
