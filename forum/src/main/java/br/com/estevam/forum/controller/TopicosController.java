package br.com.estevam.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estevam.forum.controller.dto.TopicoDto;
import br.com.estevam.forum.modelo.Topico;
import br.com.estevam.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController {

	@Autowired
	TopicoRepository topicoRepository;
	
	@GetMapping
	public List<TopicoDto> listar() {
		
		List<Topico> topicos = topicoRepository.findAll(); 
				
		return TopicoDto.convertToDto(topicos);
	}
}
