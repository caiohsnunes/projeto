package com.example.ws;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.PessoaDAO;
import com.example.entity.Pessoa;

@RestController
public class PessoaRest {
	@Inject
	PessoaDAO pessoaDao;

	@GetMapping("/pessoa")
	public List<Pessoa> pessoas(){
		
		return pessoaDao.getInstance().findAll();
		
	}
	
	@DeleteMapping("/pessoa")
	public void remove(int id){
		pessoaDao.getInstance().removeById(id);
		
	}
	
	@PostMapping("/pessoa")
	public void persist(Pessoa p) {
		pessoaDao.getInstance().persist(p);
	}
	
	@PutMapping("/pessoa")
	public void merge(Pessoa p) {
		pessoaDao.getInstance().merge(p);
	}
}
