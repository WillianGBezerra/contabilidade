package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Conta;

public class ContaService {
	
	public List<Conta> findAll(){
		List<Conta> list = new ArrayList<>();
		list.add(new Conta(1, 1, "ATIVO"));
		list.add(new Conta(1, 2, "PASSIVO"));
		list.add(new Conta(1, 3, "PATRIMONIO"));
		list.add(new Conta(1, 4, "CUSTO"));
		list.add(new Conta(1, 5, "DESPESA"));
		list.add(new Conta(1, 6, "RECEITA"));
		return list;
	}

}
