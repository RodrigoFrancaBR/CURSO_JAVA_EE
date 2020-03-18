package br.com.franca.web.api.implement;

import br.com.franca.domain.BaseEntity;

public abstract class ResourceGeneric<Dominio extends BaseEntity<Long>> {

	protected String endPoint;
	protected String uri = "/curso-preparatorio/";

	public String getUri(String endPoint) {
		return this.uri + endPoint;
	}
	
	protected boolean domainIsNull(Dominio dominio) {
		return dominio == null ? true : false;
	}

}
