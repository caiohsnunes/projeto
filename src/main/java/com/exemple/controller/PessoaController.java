package com.exemple.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.repository.PessoaRepository;

import com.exemple.model.Pessoa;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	  @GetMapping
	    public List<Pessoa> getAllnotes(){
	        return pessoaRepository.findAll();
	    }
	  
	  @GetMapping(path = {"/{id}"})
	  public ResponseEntity findById(@PathVariable int id){
	     return pessoaRepository.findById(id)
	             .map(record -> ResponseEntity.ok().body(record))
	             .orElse(ResponseEntity.notFound().build());
	  }
	  
	    @PostMapping
	    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
	        return pessoaRepository.save(pessoa);
	    }
	    
	    @DeleteMapping(path = {"/{id}"})
	    public ResponseEntity deletePessoa (@PathVariable(value = "id")Integer id){
	      
	        return pessoaRepository.findById(id)
	                .map(record -> {
	                	pessoaRepository.deleteById(id);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }
	    
	    
	    @PutMapping(value="/{id}")
	    public ResponseEntity updatePessoa(@PathVariable("id") int id,
	                                          @RequestBody Pessoa pessoa) {
	       return pessoaRepository.findById(id)
	               .map(record -> {
	                   record.setNome(pessoa.getNome());
	                   record.setEmail(pessoa.getEmail());
	                   record.setCelular(pessoa.getCelular());
	                   Pessoa updated = pessoaRepository.save(record);
	                   return ResponseEntity.ok().body(updated);
	               }).orElse(ResponseEntity.notFound().build());
	    }
}
