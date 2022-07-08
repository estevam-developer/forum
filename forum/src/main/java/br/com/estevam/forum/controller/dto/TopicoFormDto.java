package br.com.estevam.forum.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.estevam.forum.modelo.Topico;
import br.com.estevam.forum.repository.CursoRepository;

public class TopicoFormDto {

	@NotNull @NotEmpty
	private String titulo;

	@NotNull @NotEmpty
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensage) {
		this.mensagem = mensage;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico convertToModel(CursoRepository cursoRepository) {
				
		return new Topico(this.titulo, this.mensagem, cursoRepository.findByNome(this.nomeCurso));
		
	}

}
