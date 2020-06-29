package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bd.BD;
import bd.BDException;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection connection;

	// For�a inje��o de dependencia (Connection) dentro da Classe.
	public VendedorDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void inserir(Vendedor obj) {
	}

	@Override
	public void atualizar(Vendedor obj) {
	}

	@Override
	public void excluirPorId(Integer id) {
	}

	@Override
	public Vendedor buscarPorId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT seller.*, department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id " 
					+ "WHERE seller.Id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Departamento dep = instanciarFuncaoDepartamento(rs);
				Vendedor vend = instaciarFuncaoVendedor(rs, dep);
				return vend;
			}
			return null;
		} 
		catch (SQLException ex) {
			throw new BDException(ex.getMessage());
		} 
		finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
	
	@Override
	public List<Vendedor> buscarTudo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName  FROM seller "
					+ "INNER JOIN department  ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name ");
			rs = ps.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			//Map<chave , valor> - Para controlar a n�o repeti��o do Departamento desnecess�riamente.
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {				
				Departamento depNaoRepetido = map.get(rs.getInt("DepartmentId"));
				
				//Se n�o existir Map com ID do Departamento o sistema cria um, se n�o, utiliza o que j� existe no Map. 
				if(depNaoRepetido == null) {
					depNaoRepetido = instanciarFuncaoDepartamento(rs);	
					map.put(rs.getInt("DepartmentId"), depNaoRepetido); //Inserir no Map<chave , valor>.
				}				
				Vendedor vend = instaciarFuncaoVendedor(rs, depNaoRepetido);
				lista.add(vend); 
			}
			return lista;
		} 
		catch (SQLException ex) {
			throw new BDException(ex.getMessage());
		} 
		finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}

	@Override
	public List<Vendedor> buscarPorDepartamento(Departamento departamento) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT seller.*,department.Name as DepName  FROM seller "
					+ "INNER JOIN department  ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name ");
			ps.setInt(1, departamento.getId());
			rs = ps.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			//Map<chave , valor> - Para controlar a n�o repeti��o do Departamento desnecess�riamente.
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {				
				Departamento depNaoRepetido = map.get(rs.getInt("DepartmentId"));
				
				//Se n�o existir Map com ID do Departamento o sistema cria um, se n�o, utiliza o que j� existe no Map. 
				if(depNaoRepetido == null) {
					depNaoRepetido = instanciarFuncaoDepartamento(rs);	
					map.put(rs.getInt("DepartmentId"), depNaoRepetido); //Inserir no Map<chave , valor>.
				}				
				Vendedor vend = instaciarFuncaoVendedor(rs, depNaoRepetido);
				lista.add(vend); 
			}
			return lista;
		} 
		catch (SQLException ex) {
			throw new BDException(ex.getMessage());
		} 
		finally {
			BD.fecharStatement(ps);
			BD.fecharResultSet(rs);
		}
	}
	
	// Fun��o Departamento(ResultSet) - Propagando Exception.
		private Departamento instanciarFuncaoDepartamento(ResultSet rs) throws SQLException {
			Departamento dep = new Departamento();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setNome(rs.getString("DepName"));
			return dep;
		}
	
	// Fun��o Vendedor(ResultSet, Departamento).
	private Vendedor instaciarFuncaoVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vend = new Vendedor();
		new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setNome(rs.getString("Name"));
		vend.setEmail(rs.getString("Email"));
		vend.setSalarioBase(rs.getDouble("BaseSalary"));
		vend.setNascimento(rs.getDate("BirthDate"));
		vend.setDepartamento(dep);
		return vend;
	}
}
