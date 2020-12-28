package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	//JPQL
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
}
