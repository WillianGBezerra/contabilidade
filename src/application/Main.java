package application;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.ContaDao;
import model.dao.DaoFactory;
import model.entities.Conta;

public class Main extends Application {

	Scanner sc = new Scanner(System.in);

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Parent parent = loader.load();
			Scene mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("JavaFX application - Contabilidade");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);

		ContaDao contaDao = DaoFactory.createContaDao();
		Conta c = new Conta(null, 2, "DOIS", "N", 1, "N", "C", "TESTE");
		contaDao.insert(c);
		System.out.println("Inserted! New id = " + c.getId());

		System.out.println("\n=== TESTE Conta update ===");
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

	public static void main(String[] args) {
		launch(args);
	}
}
