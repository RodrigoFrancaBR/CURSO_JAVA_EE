package br.com.franca.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.franca.dao.TurmaDAO;
import br.com.franca.domain.Turma;
import br.com.franca.domain.Unidade;
import br.com.franca.domain.dto.TurmaDTO;
import br.com.franca.domain.enun.Status;
import br.com.franca.exceptions.CursoDAOException;
import br.com.franca.exceptions.CursoServiceException;
import br.com.franca.msg.Mensagem;

public class TurmaService extends CommonServiceValidations {

	@Inject
	private TurmaDAO dao;

	@Inject
	private UnidadeService unidadeService;

	public List<TurmaDTO> findAll() throws CursoDAOException {

		List<TurmaDTO> listaDeTurmasDTO = 
				this.dao.findAll()
				.parallelStream()
				.map(turma -> new ModelMapper()
									.map(turma, TurmaDTO.class))
				.collect(Collectors.toList());
		
		return listaDeTurmasDTO;
				
	}

	public TurmaDTO findById(Long id) throws CursoServiceException, CursoDAOException {

		Turma turma = dao.fimdById(id);
		TurmaDTO turmaDTO = new ModelMapper().map(turma, TurmaDTO.class);

		return turmaDTO;

	}

	public Turma save(TurmaDTO turmaDTO) throws CursoServiceException, CursoDAOException {

		if (turmaDTO == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		if (nomeInvalido(turmaDTO.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));

		if (turmaDTO.getUnidadeId() == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));
		
		Unidade unidadeEncontrada = unidadeService.findById(turmaDTO.getUnidadeId());

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));		

		Turma turma = new ModelMapper().map(turmaDTO, Turma.class);
		
		turma.setUnidade(unidadeEncontrada);

		return this.dao.save(turma);
	}
	
	public Turma update(Long id, TurmaDTO turmaDTO) throws CursoServiceException, CursoDAOException {
		TurmaDTO turmaEncontrada = null;

		if (turmaDTO == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		turmaEncontrada = findById(id);

		if (turmaEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		if (nomeInvalido(turmaDTO.getNome()))
			throw new CursoServiceException(Mensagem.getMessage("nome_invalido"));						

		if (turmaDTO.getUnidadeId() == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_null"));

		Unidade unidadeEncontrada = unidadeService.findById(turmaDTO.getUnidadeId());

		if (unidadeEncontrada == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));
		
		Turma turma = new ModelMapper().map(turmaDTO, Turma.class);
		
		turma.setUnidade(unidadeEncontrada);

		return this.dao.update(turma);
		
	}

	public void delete(Long id) throws CursoServiceException, CursoDAOException {

		TurmaDTO turmaDTO = null;

		turmaDTO = findById(id);

		if (turmaDTO == null)
			throw new CursoServiceException(Mensagem.getMessage("entidade_nao_encontrada"));

		turmaDTO.setStatus(Status.DESATIVADA);	
		
		Turma turma = new ModelMapper().map(turmaDTO, Turma.class);

		dao.delete(turma);
	}
	
/*	private boolean unidadeExiste(Long unidadeId) throws CursoServiceException, CursoDAOException {
		Unidade unidadeEncontrada = unidadeService.findById(unidadeId);
		return unidadeEncontrada != null ? true : false;
	}*/

}
