package prjJavaEE;

import br.com.franca.dao.UnidadeDAO;

public class TesteDAO {
	public static void main(String[] args) {
		UnidadeDAO dao = new UnidadeDAO();
		System.out.println(dao.getClazz());
		dao.find(1l);

	}
}
