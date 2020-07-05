package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ContaDao;
import model.entities.Conta;

public class ContaDaoJDBC implements ContaDao {

	private Connection conn;

	public ContaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public void insert(Conta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO conta "
					+ "(Conta, Descricao, CentroCusto, ContaSupeior, TipoConta, NaturezadaConta, ObservacaoConta) "
					+ "VALUES" + "(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getConta());
			st.setString(2, obj.getDescricao());
			st.setString(3, obj.getCentroCusto());
			st.setInt(4, obj.getContaSupeior());
			st.setString(5, obj.getTipoConta());
			st.setString(6, obj.getNaturezadaConta());
			st.setString(7, obj.getObservacaoConta());

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
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Conta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE conta "
					+ "SET Conta = ?, Descricao = ?, CentroCusto = ?, ContaSupeior = ?, TipoConta = ?, NaturezadaConta = ?, ObservacaoConta = ? "
					+ "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getConta());
			st.setString(2, obj.getDescricao());
			st.setString(3, obj.getCentroCusto());
			st.setInt(4, obj.getContaSupeior());
			st.setString(5, obj.getTipoConta());
			st.setString(6, obj.getNaturezadaConta());
			st.setString(7, obj.getObservacaoConta());
			st.setInt(8, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Conta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM conta " + "WHERE Id = ? " + "ORDER BY Conta");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta conta = instantiateConta(rs);
				return conta;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Conta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conta findConta(Integer conta) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM conta " + "WHERE Conta = ? " + "ORDER BY Conta");
			st.setInt(1, conta);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta obj = instantiateConta(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Conta instantiateConta(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		conta.setId(rs.getInt("Id"));
		conta.setConta(rs.getInt("Conta"));
		conta.setDescricao(rs.getString("Descricao"));
		conta.setCentroCusto(rs.getString("CentroCusto"));
		conta.setContaSupeior(rs.getInt("ContaSupeior"));
		conta.setTipoConta(rs.getString("TipoConta"));
		conta.setNaturezadaConta(rs.getString("NaturezadaConta"));
		conta.setObservacaoConta(rs.getString("ObservacaoConta"));
		return conta;
	}
}
