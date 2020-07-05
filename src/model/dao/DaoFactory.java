package model.dao;

import db.DB;
import model.dao.impl.ContaDaoJDBC;

public class DaoFactory {

	public static ContaDao createContaDao() {
		return new ContaDaoJDBC(DB.getConnection());
	}

}
