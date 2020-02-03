package br.com.franca.dao.jdbc.implement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.franca.dao.jdbc.interfaces.ConnectionJDBC;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.enun.Status;

public class UnidadeDAO {
	
	private ConnectionJDBC connection;
	private PreparedStatement stm;
	private ResultSet rs;	
	
	public UnidadeDAO() throws ClassNotFoundException, SQLException {
		this.connection = new ConnectionMYSQL();
	}

	public List<Unidade> findAll() {
		List<Unidade> unidades = new ArrayList<Unidade>();		
		String sql = "SELECT * FROM TB_UNIDADE;";

		try {
			stm = this.connection.getConnection().prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				unidades.add(this.parser(rs));
			}
			
		} catch (SQLException e) {
			System.out.println("Ocorreu algum erro no metodo buscar()");
			e.printStackTrace();
			throw new RuntimeException();
		}
		return unidades;
	}

	public Unidade insert(Unidade unidade) {
		String sqlInsert = "INSERT INTO TB_UNIDADE (nome, endereco, status) values (?,?,?)";

		try {
			stm = this.connection.getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, unidade.getNome());
			stm.setString(2, unidade.getEndereco());
			stm.setInt(3, Status.ATIVA.getChave());

			connection.commit();

			final ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				unidade.setId(rs.getLong(1));
			}
			System.out.println("Unidade salva com sucesso!");
		} catch (Exception e) {
			System.out.println("Ocorreu algum erro no metodo insert(Unidade unidade)");
			e.printStackTrace();
			connection.rollback();
		}		
		return unidade;
	}
	
	private Unidade parser(ResultSet rs) throws SQLException {
		return new Unidade(rs.getLong("id"),
						  Status.getStatus(rs.getInt(("status"))),						  
						  rs.getString("nome"),
						  rs.getString("endereco"),
						  null);
	}
	
}
