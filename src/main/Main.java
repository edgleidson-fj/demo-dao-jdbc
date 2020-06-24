package main;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Vendedor;

//Aula 243 - DAO JDBC - (Reutilizando Instanciação).
public class Main {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criarVendedorDao();
		
		System.out.println("=== TESTE 1: seller buscarPorId  ===");		
		Vendedor vend = vendDao.buscarPorId(3);		
		System.out.println(vend);
	}

}
