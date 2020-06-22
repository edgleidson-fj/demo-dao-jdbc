package main;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

//Aula 240 - DAO JDBC - (Departamento DAO).
public class Main {

	public static void main(String[] args) {
		
		Departamento dep = new Departamento(1, "BOOKS");
		Vendedor vend = new Vendedor(1, "Fulano", "fulano@teste,com", new Date(), 3000.0, dep);
		System.out.println(vend);
		
		// Factory.
		VendedorDao vendedorDao = DaoFactory.criarVendedorDao();
		DepartamentoDao departamentoDao = DaoFactory.criarDepartamentoDao();
	}

}
