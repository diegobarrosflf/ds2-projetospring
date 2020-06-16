package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.services.exception.DataIntegrityException;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;

	public Categoria find(Integer id) {

		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Perdoe! Macho vei não achei teu objeto id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repositorio.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}

	}
	
	public List<Categoria> findAll(){
		return repositorio.findAll();
	}

}
