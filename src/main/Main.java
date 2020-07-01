package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entidade.Departamento;

//Aula 250 - DAO JDBC - (Implementando CRUD - Departamento).
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartamentoDao depDao = DaoFactory.criarDepartamentoDao();
		
		System.out.println("=== TESTE 1: department buscarPorId ok ===");		
		Departamento dep = depDao.buscarPorId(5);		
		System.out.println(dep);                   
				
		System.out.println("\n=== TESTE 2: department buscarTudo ok ===");
		List<Departamento> lista = new ArrayList<>();
		lista = depDao.buscarTudo();		
		for(Departamento d : lista) {
			System.out.println(d);
		}                                           
		
/*		System.out.println("\n=== TESTE 3: department Insert ok ===");
		Departamento novoDepartamento = new Departamento(
				null, "Departamento-Teste");
		depDao.inserir(novoDepartamento);
		System.out.println("INSERT realizado com sucesso! Novo ID: "+ novoDepartamento.getId());     */
		
		System.out.println("\n=== TESTE 4: department Update ok ===");
		dep = depDao.buscarPorId(5);
		dep.setNome("Almoxarifado");;
		depDao.atualizar(dep);
		System.out.println("UPDATE realizado com sucesso!");     
		
/*		System.out.println("\n=== TESTE 5: department Delete  ok ===");
		System.out.print("Informe o ID do Departamento para teste de DELETE? ");
		int idDepartamento = sc.nextInt();
		depDao.excluirPorId(idDepartamento);
		System.out.println("DELETE realizado com sucesso!");      */
		
		sc.close();
	}

}
