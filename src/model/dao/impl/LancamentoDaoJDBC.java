package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.LancamentoDao;
import model.entities.Conta;
import model.entities.Lancamento;

public class LancamentoDaoJDBC implements LancamentoDao {

	private Connection conn;

	public LancamentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Lancamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO razao "
					+ "(Documento, DataLancamento, Historico, Valor, ContaDevedoraId, ContaCredoraId) " + "VALUES"
					+ "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getDocumento());
			st.setDate(2, new java.sql.Date(obj.getDataLancamento().getTime()));
			st.setString(3, obj.getHistorico());
			st.setDouble(4, obj.getValor());
			st.setInt(5, obj.getContaDevedora().getId());
			st.setInt(6, obj.getContaCredora().getId());

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
	public void update(Lancamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE razao "
					+ "SET Documento = ?, DataLancamento = ?, Historico = ?, Valor = ?, ContaDevedoraId = ?, ContaCredoraId = ? "
					+ "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getDocumento());
			st.setDate(2, new java.sql.Date(obj.getDataLancamento().getTime()));
			st.setString(3, obj.getHistorico());
			st.setDouble(4, obj.getValor());
			st.setInt(5, obj.getContaDevedora().getId());
			st.setInt(6, obj.getContaCredora().getId());
			st.setInt(7, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM razao WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {

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
	public Lancamento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT razao.*,conta.Conta as Conta1Conta ,conta.Descricao as Conta1Descricao "
					+ "FROM razao INNER JOIN conta " + "ON razao.ContaDevedoraId = contaId "
					+ "ON razao.ContaCredoraId = contaId " + "WHERE razao.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Conta cont1 = instantiateContaDevedora(rs);
				Conta cont2 = instantiateContaCredora(rs);
				Lancamento obj = instantiateLancamento(rs, cont1, cont2);
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

	private Lancamento instantiateLancamento(ResultSet rs, Conta cont1, Conta cont2) throws SQLException {
		Lancamento obj = new Lancamento();
		obj.setId(rs.getInt("Id"));
		obj.setDocumento(rs.getString("Documento"));
		obj.setDataLancamento(rs.getDate("DataLancamento"));
		obj.setHistorico(rs.getString("Historico"));
		obj.setValor(rs.getDouble("Valor"));
		obj.setContaDevedora(cont1);
		obj.setContaCredora(cont2);
		return obj;
	}

	private Conta instantiateContaDevedora(ResultSet rs) throws SQLException {
		Conta cont1 = new Conta();
		cont1.setId(rs.getInt("ContaDevedoraId"));
		cont1.setConta(rs.getInt("Conta1Conta"));
		cont1.setDescricao(rs.getString("Conta1Descricao"));
		return cont1;
	}

	private Conta instantiateContaCredora(ResultSet rs) throws SQLException {
		Conta cont2 = new Conta();
		cont2.setId(rs.getInt("ContaCredoraId"));
		cont2.setConta(rs.getInt("Conta2Conta"));
		cont2.setDescricao(rs.getString("Conta2Descricao"));
		return cont2;
	}

	@Override
	public List<Lancamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT razao.*,conta.Conta as Conta1Conta ,conta.Descricao as Conta1Descricao "
					+ "FROM razao INNER JOIN conta " + "ON razao.ContaDevedoraId = contaId "
					+ "ON razao.ContaCredoraId = contaId " + "ORDER BY Id");
			rs = st.executeQuery();
			List<Lancamento> list = new ArrayList<>();
			Map<Integer, Conta> map = new HashMap<>();
			while (rs.next()) {
				Conta cont1 = map.get(rs.getInt("ContaDevedoraId"));
				Conta cont2 = map.get(rs.getInt("ContaCredoraId"));
				if (cont1 == null) {
					cont1 = instantiateContaDevedora(rs);
					map.put(rs.getInt("ContaDevedoraId"), cont1);
				}
				if (cont2 == null) {
					cont2 = instantiateContaDevedora(rs);
					map.put(rs.getInt("ContaCredoraId"), cont2);
				}
				Lancamento obj = instantiateLancamento(rs, cont1, cont2);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Lancamento findConta(Integer lancamento) {
		// TODO Auto-generated method stub
		return null;
	}

}
