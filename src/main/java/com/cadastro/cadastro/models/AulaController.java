package com.cadastro.cadastro.models;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@RequestMapping("/deletarAula")
	public String deletarAula(long codigo) {
		Aula aula = ar.findBycodigo(codigo);
		ar.delete(aula);
		return "redirect:/Aulas";
		
	}
	
	public String deletarConvidado(String rg) {
		Convidado convidado = cr.findByRg(rg);
		cr.delete(convidado);
		
		Aula aula = convidado.getAula();
		long codigoLong = aula.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/"+ codigo;
		
		
		
	}
	
	
	
	
	@RequestMapping(value="/CadastrarAula", method=RequestMethod.POST)
	public String form(@Valid Aula aula, BindingResult result, RedirectAttributes attributes ) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
		return "redirect:/CadastrarAula";
		}

		ar.save(aula);
		attributes.addFlashAttribute("mensagem", "Aula cadastrada com sucesso");
				return "redirect:/CadastrarAula";		
		
		
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
		
		Iterable<Convidado> convidados = cr.findByAula(aula);
		mv.addObject("convidados", convidados);
		return mv;

	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesAulaPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
	if(result.hasErrors()) {
		attributes.addFlashAttribute("mensagem", "Verifique os dados!");
		return "redirect:/{codigo}";
	}
		Aula aula = ar.findBycodigo(codigo);
	convidado.setAula(aula);
	cr.save(convidado);
	attributes.addFlashAttribute("mensagem", "Aluno adicionado com sucesso!");
	return "redirect:/{codigo}";
	}
	
	}



