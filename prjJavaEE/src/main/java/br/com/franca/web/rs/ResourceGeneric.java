package br.com.franca.web.rs;

import br.com.franca.web.business.BusinessFactory;

public abstract class ResourceGeneric {
	
	private BusinessFactory businessFactory= null;

	public BusinessFactory getBusinessFactory() {
		if (businessFactory == null) {
			businessFactory = new BusinessFactory();
		}
		return businessFactory;
	}
	
}
