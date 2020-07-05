package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.GrupoDeContasDao;
import model.entities.GrupoDeContas;

public class GrupoDeContasDaoJDBC implements GrupoDeContasDao{
	
	private Connection conn;

	public GrupoDeContasDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(GrupoDeContas obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO grupoconta " + "(Nome) "
		              + "VALUES " + "(?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(GrupoDeContas obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GrupoDeContas findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrupoDeContas> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GrupoDeContas findByName(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM grupoconta "
			 						   +"WHERE Nome = ? "
					                   +"ORDER BY Id");
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				GrupoDeContas grupoc = instantiateDepartment(rs);
				return grupoc;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	private GrupoDeContas instantiateDepartment(ResultSet rs) throws SQLException {
		GrupoDeContas grupoc = new GrupoDeContas();
		grupoc.setId(rs.getInt("Id"));
		grupoc.setNome(rs.getString("Name"));
		return grupoc;
	}

}
