package br.com.estevam.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.estevam.forum.modelo.StatusTopico;
import br.com.estevam.forum.modelo.Topico;

public class TopicoDetalhesDto {

	private Long id;
	
	private String titulo;
	
	private String mensagem;
	
	private LocalDateTime dataCriacao;
	
	private String nomeAutor;
	
	private StatusTopico status;
	
	private List<RespostaDto> respostas;

	public TopicoDetalhesDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDto> getRespostas() {
		return respostas;
	}
	
	
}
