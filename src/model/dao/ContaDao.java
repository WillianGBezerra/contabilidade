package model.dao;

import java.util.List;

import model.entities.Conta;
import model.entities.GrupoDeContas;

public interface ContaDao {

	void insert(Conta obj);

	void update(Conta obj);

	void deleteById(Integer id);

	Conta findById(Integer id);

	List<Conta> findAll();

	Conta findConta(Integer conta);

}
