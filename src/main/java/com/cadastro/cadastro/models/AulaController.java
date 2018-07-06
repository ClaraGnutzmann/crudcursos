package com.cadastro.cadastro.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cadastro.cadastro.repository.AulaRepository;
import com.cadastro.cadastro.repository.ConvidadoRepository;

@Controller
public class AulaController {
	
	@Autowired
	private AulaRepository ar;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/CadastrarAula", method=RequestMethod.GET)
	public String form() {
		return "aula/FormAula";
		
		
		
			}
	@RequestMapping(value="/CadastrarAula", method=RequestMethod.POST)
	public String form( Aula aula) {
	
		ar.save(aula);
				return "redirect:CadastrarAula";		
		
		
			}
	@RequestMapping("/Aulas")
	public ModelAndView listaAulas() {
		ModelAndView mv = new ModelAndView("Index");
		Iterable<Aula> aulas = ar.findAll();
		mv.addObject("aulas", aulas);
		return	mv;
}
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesAula(@PathVariable("codigo") long codigo) {
		Aula aula = ar.findBycodigo(codigo);
		ModelAndView mv = new ModelAndView("aula/detalhesAula");
		mv.addObject("aula", aula);
		System.out.println("aula" + aula);
		return mv;

	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesAulaPost(@PathVariable("codigo") long codigo, Convidado convidado) {
	Aula aula = ar.findBycodigo(codigo);
	convidado.setAula(aula);
	cr.save(convidado);
	return "redirect:/{codigo}";
	}
	
	}



