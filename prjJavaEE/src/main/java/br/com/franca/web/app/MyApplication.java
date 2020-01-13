package br.com.franca.web.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		packages("br.com.franca.web.rs");
	}
}
