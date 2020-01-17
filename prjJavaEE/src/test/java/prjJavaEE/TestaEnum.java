package prjJavaEE;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.franca.domain.Unidade;
import br.com.franca.enun.Situacao;

public class TestaEnum {
	@DisplayName("Teste de Enum")
	@Test
	public void main() {
		
		Unidade u = new Unidade();
		
		u.setId(1l);
		u.setNome("CASCADURA");
		u.setEndereco("Quintão 153");
		u.setSituacao(Situacao.ATIVA);
		
		assertEquals(1, u.getId());				
		assertEquals("CASCADURA", u.getNome());
		assertEquals("Quintão 153", u.getEndereco());					
		assertEquals("Ativa", u.getSituacao().getValor());
		assertEquals(1, u.getSituacao().getChave());
				
		assertEquals(Situacao.ATIVA, u.getSituacao().getSituacao(1));
		assertEquals(Situacao.DESATIVADA, u.getSituacao().getSituacao(0));

		// ERRO
		assertEquals(Situacao.INVALIDA, u.getSituacao().getSituacao(10));
		assertEquals(Situacao.INVALIDA, u.getSituacao().getSituacao(11));
		assertEquals(Situacao.INVALIDA, u.getSituacao().getSituacao(12));
		
		assertEquals(100, u.getSituacao().getSituacao(12).getChave());
		assertEquals("Situação inválida", u.getSituacao().getSituacao(12).getValor());

		// não deve permitir
		//u.setSituacao(Sexo.FEMININO);
		u.setSituacao(Situacao.MATRICULADO);

		// deve permitir
		u.setSituacao(Situacao.ATIVA);
	}

}
