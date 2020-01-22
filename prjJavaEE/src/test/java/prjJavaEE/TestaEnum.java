package prjJavaEE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class TestaEnum {
	@DisplayName("Teste de Enum")
	@Test
	public void main() {

		Unidade u = new Unidade();

		u.setId(1l);
		u.setNome("CASCADURA");
		u.setEndereco("Quintão 153");
		u.setStatus(Status.ATIVA);

		assertEquals(1, u.getId());
		assertEquals("CASCADURA", u.getNome());
		assertEquals("Quintão 153", u.getEndereco());
		assertEquals("Ativa", u.getStatus().getValor());
		assertEquals(1, u.getStatus().getChave());

		assertEquals(Status.ATIVA, u.getStatus().getStatus(1));
		assertEquals(Status.DESATIVADA, u.getStatus().getStatus(0));
		
		
	}
	
	

}
