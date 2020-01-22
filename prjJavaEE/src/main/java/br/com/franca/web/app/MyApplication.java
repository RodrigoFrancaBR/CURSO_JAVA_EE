package br.com.franca.web.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {
/**
	@PostConstruct
	private void init() {
		System.out.println("Teste");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cursoPU");
		EntityManager em = emf.createEntityManager();
		System.out.println("Connected");
	}

*/
	public MyApplication() {
		packages("br.com.franca.web.rs");
	}
}
