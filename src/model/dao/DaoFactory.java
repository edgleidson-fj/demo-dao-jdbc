package model.dao;

import model.dao.implementacao.DepartamentoDaoJDBC;
import model.dao.implementacao.VendedorDaoJDBC;

public class DaoFactory {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoJDBC();
	}
	
	public static DepartamentoDao criarDepartamentoDao() {
		return new DepartamentoDaoJDBC();
	}
}
