package br.com.estevam.forum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.estevam.forum.modelo.Curso;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CursoRepositoryTest {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveRecuperarUmCursoAoBuscarPeloNome() {
	
		var nomeCurso = "HTML 5";
		var categoriaCurso = "Programação";
		
		Curso curso;
		
		curso = new Curso();
		curso.setNome(nomeCurso);
		curso.setCategoria(categoriaCurso);
		
		em.persist(curso);
		
		curso = cursoRepository.findByNome(nomeCurso);
		
		assertNotNull(curso);
		assertEquals(nomeCurso, curso.getNome());		
		assertEquals(categoriaCurso, curso.getCategoria());
	}
	
	@Test
	void naoDeveRecuperarUmCursoQuandoNomeNaoEstaCadastrado() {
		var nomeCurso = "JPA";
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		assertNull(curso);		
	}

}
