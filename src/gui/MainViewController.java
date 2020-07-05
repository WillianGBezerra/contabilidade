package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem MenuItemConta;
	@FXML
	private MenuItem MenuItemCentroCusto;
	@FXML
	private MenuItem MenuItemAbout;

	@FXML
	public void onMenuItemContaAction() {
		System.out.println("onMenuItemConta");
	}

	@FXML
	public void onMenuItemCentroCustoAction() {
		System.out.println("MenuItemCentroCusto");
	}

	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("MenuItemAbout");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

}
