package br.com.franca.repository;

import java.util.List;

import br.com.franca.domain.Turma;

public interface TurmaRepository {

	List<Turma> findAllTurmasByUnidadeId(Long unidadeId);
}
