package br.com.franca.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/")
public class CursoPreparatorioApplication extends ResourceConfig {
	public CursoPreparatorioApplication() {
		packages("br.com.franca.web.api.implement");
	}
}
