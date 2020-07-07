package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Conta;
import model.services.ContaService;

public class ContaListController implements Initializable {

	private ContaService service;

	@FXML
	private TableView<Conta> tableViewConta;
	@FXML
	private TableColumn<Conta, Integer> tableColumnId;
	@FXML
	private TableColumn<Conta, Integer> tableColumnConta;
	@FXML
	private TableColumn<Conta, String> tableColumnDescricao;
	@FXML
	private Button btNovoRegistro;
	@FXML
	private Button btPrimeiroRegistro;
	@FXML
	private Button btRegistroAnterior;
	@FXML
	private Button btProximoRegistro;
	@FXML
	private Button btUltimoRegistro;

	private ObservableList<Conta> obsList;

	@FXML
	public void onbtNovoRegistroAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/ContaForm.fxml", parentStage);
	}

	@FXML
	public void onbtPrimeiroRegistroAction() {
		return;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	public void setContaService(ContaService service) {
		this.service = service;
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnConta.setCellValueFactory(new PropertyValueFactory<>("conta"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		/* Faz a tabela acompanhar a altura da janela */
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewConta.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Conta> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewConta.setItems(obsList);
	}
	
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			Stage dialoagStage = new Stage();
			dialoagStage.setTitle("Enter Conta data");
			dialoagStage.setScene(new Scene(pane));
			dialoagStage.setResizable(false);
			dialoagStage.initOwner(parentStage);
			dialoagStage.initModality(Modality.WINDOW_MODAL);
			dialoagStage.showAndWait();
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
