package org.generation.blogPessoal.seguranca;

import org.generation.blogPessoal.model.Tema;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerGeral {
	
	@Autowired
//@autowired marca os pontos de injeção dentro da sua classe	
	private TemaRepository repository;
	
	//localhost:0808 <- direciona p a index
	@GetMapping(value= "/")
	public String login() {
		return "index";
	}	
	@GetMapping(value="/cadastrarTema")
	public String form(){
		return "formTema";
	}	
	
	@PostMapping(value="/cadastrarTema")
	public String form(Tema tema){			
		repository.save(tema);
		return "redirect:/cadastrarTema";
	}

}

