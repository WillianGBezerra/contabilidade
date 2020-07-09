package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
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

public class ContaListController implements Initializable, DataChangeListener {

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
	private TableColumn<Conta, String> tableColumnCentroCusto;
	@FXML
	private TableColumn<Conta, String> tableColumnContaSupeior;
	@FXML
	private TableColumn<Conta, String> tableColumnTipoConta;
	@FXML
	private TableColumn<Conta, String> tableColumnNaturezadaConta;
	@FXML
	private TableColumn<Conta, String> tableColumnObservacaoConta;
	@FXML
	private Button btNovoRegistro;
	@FXML
	private Button btEditarRegistro;
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
		Conta obj = new Conta();
		createDialogForm(obj, "/gui/ContaForm.fxml", parentStage);
	}

	@FXML
	public void onbtPrimeiroRegistroAction() {
		tableViewConta.getSelectionModel().selectFirst();
	}
	@FXML
	public void onbtRegistroAnteriorAction() {
		tableViewConta.getSelectionModel().selectPrevious();
	}
	@FXML
	public void onbtProximoRegistroAction() {
		tableViewConta.getSelectionModel().selectNext();
	}
	@FXML
	public void onbtUltimoRegistroAction() {
		tableViewConta.getSelectionModel().selectLast();
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
		tableColumnCentroCusto.setCellValueFactory(new PropertyValueFactory<>("CentroCusto"));
		tableColumnContaSupeior.setCellValueFactory(new PropertyValueFactory<>("ContaSupeior"));
		tableColumnTipoConta.setCellValueFactory(new PropertyValueFactory<>("TipoConta"));
		tableColumnNaturezadaConta.setCellValueFactory(new PropertyValueFactory<>("NaturezadaConta"));
		tableColumnObservacaoConta.setCellValueFactory(new PropertyValueFactory<>("ObservacaoConta"));
		

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
	
	private void createDialogForm(Conta obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ContaFormController controller = loader.getController();
			controller.setConta(obj);
			controller.setContaService(new ContaService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			
			Stage dialoagStage = new Stage();
			dialoagStage.setTitle("Nova Conta");
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

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
}
