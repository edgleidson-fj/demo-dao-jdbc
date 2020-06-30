package main;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entidade.Departamento;
import model.entidade.Vendedor;

//Aula 249 - DAO JDBC - (Implementando DELETE - Vendedor).
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("\n=== TESTE 6: seller Delete  ===");
		System.out.print("Informe o ID do Vendedor para teste de DELETE? ");
		int idVendendedor = sc.nextInt();
		vendDao.excluirPorId(idVendendedor);
		System.out.println("DELETE realizado com sucesso!"); 
		
		sc.close();
	}

}
