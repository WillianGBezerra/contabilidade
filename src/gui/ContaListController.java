package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Conta;

public class ContaListController implements Initializable {

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
	public void onbtNovoRegistro() {
		System.out.println("Novo Registro");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnConta.setCellValueFactory(new PropertyValueFactory<>("conta"));
		tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		/*Faz a tabela acompanhar a altura da janela*/
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewConta.prefHeightProperty().bind(stage.heightProperty());

	}

}
