package prjJavaEE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.franca.business.UnidadeBusiness;
import br.com.franca.dao.implement.UnidadeDAO;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class TestaUnidadeDAO {
	@DisplayName("Teste de UnidadeDao")
	@Test
	public void main() {
		Unidade u = new Unidade();

		u.setNome("INhaúma");
		u.setEndereco("Piragibe 78");
		u.setStatus(Status.ATIVA);

		UnidadeDAO dao = new UnidadeDAO();

		// dao.save(u);

		/*
		 * Unidade u2 = new Unidade(); u2.setNome("MADUREIRA");
		 * u2.setEndereco("Am�lia 500"); u2.setStatus(Status.ATIVA);
		 */

		/*
		 * Unidade save = dao.save(u2);
		 * 
		 * assertEquals(1, u.getId()); assertEquals(2, u2.getId());
		 * 
		 * Unidade find = dao.find(save.getId()); System.out.println(find.toString());
		 * //find.setId(9l); find.setEndereco("Rua piragibe 79"); Unidade update =
		 * dao.save(find); dao.delete(find.getId());
		 * System.out.println(update.toString());
		 */

	}

}
