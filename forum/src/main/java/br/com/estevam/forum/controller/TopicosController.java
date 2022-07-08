package br.com.estevam.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estevam.forum.controller.dto.AtualizacaoTopicoFormDto;
import br.com.estevam.forum.controller.dto.TopicoDetalhesDto;
import br.com.estevam.forum.controller.dto.TopicoDto;
import br.com.estevam.forum.controller.dto.TopicoFormDto;
import br.com.estevam.forum.modelo.Topico;
import br.com.estevam.forum.repository.CursoRepository;
import br.com.estevam.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
@Transactional
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> listar(String nomeCurso) {
		
		List<Topico> topicos;
		
		if (nomeCurso == null)
			topicos = topicoRepository.findAll(); 
		else
			topicos = topicoRepository.findByCursoNome(nomeCurso);
		
		return TopicoDto.convertToDto(topicos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TopicoDetalhesDto> detalhar(@PathVariable Long id) {
		
		var result = topicoRepository.findById(id);
		
		if (result.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(new TopicoDetalhesDto(result.get()));
		
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoFormDto form, UriComponentsBuilder uriBuilder) {
		
		Topico topico = form.convertToModel(cursoRepository);
		
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

		return ResponseEntity.created(uri).body(new TopicoDto(topico));
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoFormDto form) {
		
		var result = topicoRepository.findById(id);
		
		if (result.isEmpty())
			return ResponseEntity.notFound().build(); 
		
		var topico = form.atualizar(result.get());

		return ResponseEntity.ok(new TopicoDto(topico));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		
		var result = topicoRepository.findById(id);
		
		if (result.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		topicoRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}
}
