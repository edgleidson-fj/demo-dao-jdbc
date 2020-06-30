package main;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

//Aula 248 - DAO JDBC - (Implementando UPDATE - Vendedor).
public class Main {

	public static void main(String[] args) {
		
		VendedorDao vendDao = DaoFactory.criarVendedorDao();
		
		System.out.println("=== TESTE 1: seller buscarPorId  ===");		
		Vendedor vend = vendDao.buscarPorId(3);		
		System.out.println(vend);
		
		System.out.println("\n=== TESTE 2: seller buscarPorDepartamento  ===");	
		Departamento dep = new Departamento(4, null);
		List<Vendedor> lista = vendDao.buscarPorDepartamento(dep);		
		for(Vendedor v : lista) {
			System.out.println(v);
		}
		
		System.out.println("\n=== TESTE 3: seller buscarTudo  ===");	
		lista = vendDao.buscarTudo();		
		for(Vendedor v : lista) {
			System.out.println(v);
		}
		
		System.out.println("\n=== TESTE 4: seller Insert  ===");
		Vendedor novoVendedor = new Vendedor(
				null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep); //Reutilizando a variavel (dep) do teste 2.
//		vendDao.inserir(novoVendedor);
		System.out.println("INSERT realizado com sucesso! Novo ID: "+ novoVendedor.getId());     
		
		System.out.println("\n=== TESTE 5: seller Update  ===");
		vend = vendDao.buscarPorId(10);
		vend.setNome("Martha Waine");;
		vend.setEmail("martha.waine@teste.com");
		vend.setNascimento(new Date());
		vend.setSalarioBase(1167.0);
		vend.setDepartamento(dep);
		vendDao.atualizar(vend);
		System.out.println("UPDATE realizado com sucesso!");
	}

}
