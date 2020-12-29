package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioDto;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
											JpaSpecificationExecutor<Funcionario> {
	
	//derived query
	List<Funcionario> findByNome(String nome);
	List<Funcionario> findByNomeLike(String nome);
	List<Funcionario> findByNomeEndingWith(String nome);
	List<Funcionario> findByNomeStartingWith(String nome);
	List<Funcionario> findByNomeIsNull();
	List<Funcionario> findByNomeIsNotNull();
	List<Funcionario> findByNomeOrderByNomeAsc(String nome);
	
	List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate dataContratacao);
	
	List<Funcionario> findByCargoDescricao(String descricao);
	
	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	//JPQL
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);
	
	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> findByCargoPelaDescricao(String descricao);
	
	@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	List<Funcionario> findByUnidadeTrabalhosPelaDescricao(String descricao);
	
	//Native Query
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	//projecao com Interface
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	
	//projecao com Classe
	@Query(value = "SELECT new br.com.alura.spring.data.orm.FuncionarioDto(f.id, f.nome, f.salario) FROM Funcionario f")
	List<FuncionarioDto> findFuncionarioSalarioComProjecaoClasse();
}
