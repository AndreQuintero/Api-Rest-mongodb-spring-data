package com.andre.firstSpringData.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andre.firstSpringData.entity.Students;
import com.andre.firstSpringData.repository.StudentsRepository;

@RestController
public class StudentsController {

	@Autowired
	StudentsRepository studentRepository;
	
	@RequestMapping(value="/students", method = RequestMethod.GET) // lista tudo
	public List<Students>  listStudent(){
		
		return this.studentRepository.findAll();
	}
	
	@RequestMapping(value="/students", method = RequestMethod.POST) // insere um estudante
	public Students saveStudent(@RequestBody Students students) {
		return this.studentRepository.save(students);
	}
	
	@RequestMapping(value="/students/{id}", method = RequestMethod.GET) // recupera um estudante pelo id
	public Optional<Students>  findById(@PathVariable String id) {
		
		return this.studentRepository.findById(id);
	}
	
	@RequestMapping(value="/students/{name}/name", method = RequestMethod.GET) // recupera estudantes pelo nome
	public List<Students>  findByName(@PathVariable String name){
		
		return this.studentRepository.findByNameLikeIgnoreCase(name);
	}
	
	@RequestMapping(value="/students/{id}", method = RequestMethod.DELETE) // deleta um usuário
	public void deleteById(@PathVariable String id) {
		
		this.studentRepository.deleteById(id);
		System.out.println("Deletado com sucesso");
	}
	
	@RequestMapping(value="/students/{id}", method = RequestMethod.PUT) // atualiza um usuário
	public void updateStudent(@PathVariable String id,@Valid @RequestBody Students students) {
		 students.setId(id);
		 this.studentRepository.save(students);
		 System.out.println("atualizou");
	}
	
}
