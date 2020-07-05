package model.dao;

import java.util.List;

import model.entities.GrupoDeContas;

public interface GrupoDeContasDao {
	
	void insert(GrupoDeContas obj);

	void update(GrupoDeContas obj);

	void deleteById(Integer id);

	GrupoDeContas findById(Integer id);
	
	GrupoDeContas findByName(String name);

	List<GrupoDeContas> findAll();

}
