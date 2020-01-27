package prjJavaEE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.franca.dao.implement.UnidadeDAOImp;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class TestaUnidadeDAO {
	@DisplayName("Teste de dao")
	@Test
	public void main() {
		Unidade u = new Unidade();

		// u.setId(1l);
		u.setNome("CASCADURA");
		u.setEndereco("Quintão 153");
		u.setStatus(Status.ATIVA);

		UnidadeDAOImp dao = new UnidadeDAOImp();
		dao.save(u);
		
		Unidade u2 = new Unidade();
		u2.setNome("CASCADURA");
		u2.setEndereco("Quintão 153");
		u2.setStatus(Status.ATIVA);
		
		dao.save(u2);
		
		assertEquals(1, u.getId());
		assertEquals(2, u2.getId());
	}
}
