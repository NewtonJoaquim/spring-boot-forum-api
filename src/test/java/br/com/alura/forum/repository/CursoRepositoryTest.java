package br.com.alura.forum.repository;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.alura.forum.modelo.Curso;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveriaCarregarUmCursoAoBuscarPeloSeuNome() {
		Curso html5 = new Curso();
		
		String nomeCurso = "HTML 5";
		html5.setNome(nomeCurso);
		html5.setCategoria("Programação");
		em.persist(html5);
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(nomeCurso, curso.getNome());
	}

	@Test
	void naoDeveriaCarregarUmCursoNaoCadastrado() {
		String nomeCurso = "asuhasiughad";
		Curso curso = repository.findByNome(nomeCurso);
		Assert.assertNull(curso);
	}
}