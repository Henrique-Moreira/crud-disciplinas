package com.henrique.AtividadeCrud.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.henrique.AtividadeCrud.dao.DisciplinaDAO;
import com.henrique.AtividadeCrud.dominio.Disciplina;

import java.util.List;
@Controller
public class DisciplinaControle {

	
	@GetMapping("/disciplinas")
	public String disciplinasTabela(Model modelo) {
		DisciplinaDAO dao = new DisciplinaDAO();
		List<Disciplina> lista = dao.todos();
		modelo.addAttribute("lista",lista);
		return "disciplinas"; 
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/cadastra-disciplina")
	public String exibeForm(Model modelo) {
		modelo.addAttribute("disciplina", new Disciplina());
		return "form-disciplina";
	}
	
	@PostMapping("/cadastra-disciplina")
	public String processaForm(Disciplina disciplina) {
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.inserir(disciplina);
		return "redirect:/disciplinas";
	}
	
	@GetMapping("/excluir-disciplina")
	public String excluirDisciplina(@RequestParam(value = "id", required = false)Integer p) {
		System.out.println("excluindo a disciplina com id = " + p);
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.excluir(p);
		return "redirect:/disciplinas";
	}
	
	@GetMapping("/editar-disciplina")
	public String editarDisciplina(Model modelo, @RequestParam(value = "id", required = false)Integer p) {
		System.out.println("editanto a disciplina com id = " + p);
		DisciplinaDAO dao = new DisciplinaDAO();
		modelo.addAttribute("disciplina", dao.buscaPorId(p));
		return "form-disciplina";
	}
	
	@PostMapping("/editar-disciplina")
	public String editarDisciplina(Disciplina disciplina) {
		System.out.println("atualizando a disciplina com id = " + disciplina.id);
		DisciplinaDAO dao = new DisciplinaDAO();
		dao.atualizar(disciplina);
		return "redirect:/disciplinas";
	}
}
