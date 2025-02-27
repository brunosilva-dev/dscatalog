package br.com.ist.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ist.dscatalog.entities.Category;
import br.com.ist.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	// Metod findAll
	public List<Category> findAll() {
		return repository.findAll();
	}
}
