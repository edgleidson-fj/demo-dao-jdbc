package main;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

//Aula 244 - DAO JDBC - (Implementando buscarPorDepartamento).
public class Main {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criarVendedorDao();
		
		System.out.println("=== TESTE 1: seller buscarPorId  ===");		
		Vendedor vend = vendDao.buscarPorId(3);		
		System.out.println(vend);
		
		System.out.println("\n=== TESTE 2: seller buscarPorDepartamento  ===");	
		Departamento dep = new Departamento(2, null);
		List<Vendedor> lista = vendDao.buscarPorDepartamento(dep);
		
		for(Vendedor v : lista) {
			System.out.println(v);
		}
	}

}
