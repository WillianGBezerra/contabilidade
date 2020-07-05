package model.dao;

import db.DB;
import model.dao.impl.ContaDaoJDBC;
import model.dao.impl.GrupoDeContasDaoJDBC;

public class DaoFactory {

	public static ContaDao createContaDao() {
		return new ContaDaoJDBC(DB.getConnection());
	}

	public static GrupoDeContasDao createGrupoDeContasDao() {
		return new GrupoDeContasDaoJDBC(DB.getConnection());
	}

}
