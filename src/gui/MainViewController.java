package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ContaService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem MenuItemPlanodeContas;
	@FXML
	private MenuItem MenuItemCentroCusto;
	@FXML
	private MenuItem MenuItemRelatorioPlanodeContas;
	@FXML
	private MenuItem MenuItemRelatorioCentroCusto;
	@FXML
	private MenuItem MenuItemAbout;

	@FXML
	public void onMenuItemCentroCustoAction() {
		System.out.println("onMenuItemCentroCusto");
	}

	@FXML
	public void onMenuItemRelatorioPlanodeContasAction() {
		System.out.println("onMenuItemRelatorioPlanodeContasAction");
	}

	@FXML
	public void onMenuItemMenuItemRelatorioCentroCustoAction() {
		System.out.println("onMenuItemRelatorioCentroCustoAction");
	}

	@FXML
	public void onMenuItemPlanodeContasAction() {
		loadView2("/gui/ContaList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			ContaListController controller = loader.getController();
			controller.setContaService(new ContaService());
			controller.updateTableView();
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}

