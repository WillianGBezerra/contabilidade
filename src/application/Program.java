package application;

import java.text.ParseException;
import java.util.Scanner;

import model.dao.ContaDao;
import model.dao.DaoFactory;
import model.entities.Conta;

public class Program {

	public static void main(String args[]) throws ParseException {

		Scanner sc = new Scanner(System.in);

		ContaDao contaDao = DaoFactory.createContaDao();
		Conta c = new Conta(null, 2, "DOIS", "N", 1, "N", "C", "TESTE");
		contaDao.insert(c);
		System.out.println("Inserted! New id = " + c.getId());

		System.out.println("\n=== TESTE 10: department update ===");
		System.out.println("Enter id for Update name: ");
		Conta conta = contaDao.findById(sc.nextInt());
		conta.setConta(1001000000);
		conta.setDescricao("ATIVO CIRCULANTE");
		conta.setCentroCusto("N");
		conta.setContaSupeior(1000000000);
		conta.setTipoConta("S");
		conta.setNaturezadaConta("D");
		conta.setObservacaoConta("UPDATE");
		contaDao.update(conta);
		System.out.println("Update completed!");
		
		sc.close();
	}

}
