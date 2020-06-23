package main;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Vendedor;

//Aula 242 - DAO JDBC - (Implementando buscarPorId - [Vendedor]).
public class Main {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criarVendedorDao();
		
		Vendedor vend = vendDao.buscarPorId(3);
		
		System.out.println(vend);
	}

}
