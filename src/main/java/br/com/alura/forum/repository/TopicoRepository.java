package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	//Só pelo nome do método o spring já entende que dentro da entidade Topico precisa buscar um curso cujo nome é nomeCurso
	//Se fosse findByX ele filtraria pelo atributo X
	//Se fosse findByXY ele checaria se existe um atributo XY e filtrará por ele, caso contrário, filtrará no relacionamento X, o atributo Y
	//Se fosse findByX_Y ele filtraria pelo relacionamento X no atributo Y
	List<Topico> findByCurso_Nome(String nomeCurso);
	
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);

}
