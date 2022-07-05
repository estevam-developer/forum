package br.com.estevam.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estevam.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
}
