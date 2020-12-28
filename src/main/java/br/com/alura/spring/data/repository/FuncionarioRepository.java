package br.com.alura.spring.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
	
	//derived query
	List<Funcionario> findByNome(String nome);
	List<Funcionario> findByNomeLike(String nome);
	List<Funcionario> findByNomeEndingWith(String nome);
	List<Funcionario> findByNomeStartingWith(String nome);
	List<Funcionario> findByNomeIsNull();
	List<Funcionario> findByNomeIsNotNull();
	List<Funcionario> findByNomeOrderByNomeAsc(String nome);
	
	
}
