package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	private ObservableList<Conta> obsList;

	@FXML
	public void onbtNovoRegistro() {
		System.out.println("Novo Registro");
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
		
		/*Faz a tabela acompanhar a altura da janela*/
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewConta.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Conta> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewConta.setItems(obsList);
	}
}
