package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Conta;
import model.entities.Natureza;
import model.entities.PermiteCentroCusto;
import model.entities.TipoConta;
import model.services.ContaService;

public class ContaFormController implements Initializable {

	private List<TipoConta> TiposContas = new ArrayList<>();
	private ObservableList<TipoConta> ObsTiposContas;

	private List<PermiteCentroCusto> PermiteCentroCustos = new ArrayList<>();
	private ObservableList<PermiteCentroCusto> ObsPermiteCentroCusto;

	private List<Natureza> TiposNatureza = new ArrayList<>();
	private ObservableList<Natureza> ObsTiposNatureza;

	private Conta entity;

	private ContaService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtConta;
	@FXML
	private TextField txtDescricao;
	@FXML
	private TextField txtContaSuperior;
	@FXML
	private TextField txtObservacao;
	@FXML
	private ComboBox<PermiteCentroCusto> cbPermiteCentroCusto;
	@FXML
	private ComboBox<TipoConta> cbTipoConta;
	@FXML
	private ComboBox<Natureza> cbNatureza;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;

	public void setConta(Conta entity) {
		this.entity = entity;
	}

	public void setContaService(ContaService service) {
		this.service = service;
	}
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null.");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null.");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListener();
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Conta getFormData() {
		Conta obj = new Conta();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setConta(Utils.tryParseToInt(txtConta.getText()));
		obj.setDescricao(txtDescricao.getText());
		obj.setCentroCusto(cbPermiteCentroCusto.getValue().toString());
		obj.setContaSupeior(Utils.tryParseToInt(txtContaSuperior.getText()));
		obj.setTipoConta(cbTipoConta.getValue().toString());
		obj.setNaturezadaConta(cbNatureza.getValue().toString());
		obj.setObservacaoConta(txtObservacao.getText());
		return obj;

	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		carregarTipoConta();
		carregarPermiteCentroCusto();
		carregarNatureza();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtConta);
		Constraints.setTextFieldInteger(txtContaSuperior);

	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null.");
		}
		
		
		
		txtId.setText(String.valueOf(entity.getId()));
		
		txtConta.setText(String.valueOf(entity.getConta()));
		
		txtDescricao.setText(entity.getDescricao());
		
		PermiteCentroCusto S = new PermiteCentroCusto(1, entity.getCentroCusto());
		PermiteCentroCustos.add(S);
		ObsPermiteCentroCusto = FXCollections.observableArrayList(PermiteCentroCustos);
		cbPermiteCentroCusto.setItems(ObsPermiteCentroCusto);
		
		txtContaSuperior.setText(String.valueOf(entity.getContaSupeior()));
		
		TipoConta A = new TipoConta(1, entity.getTipoConta());
		TiposContas.add(A);
		ObsTiposContas = FXCollections.observableArrayList(TiposContas);
		cbTipoConta.setItems(ObsTiposContas);
		
		Natureza D = new Natureza(1, entity.getNaturezadaConta());
		TiposNatureza.add(D);
		ObsTiposNatureza = FXCollections.observableArrayList(TiposNatureza);
		cbNatureza.setItems(ObsTiposNatureza);
		
		txtObservacao.setText(entity.getObservacaoConta());
		
		
	}

	public void carregarTipoConta() {
		TipoConta Analitica = new TipoConta(1, "Analítica");
		TipoConta Sintetica = new TipoConta(2, "Sintética");
		TiposContas.add(Analitica);
		TiposContas.add(Sintetica);
		ObsTiposContas = FXCollections.observableArrayList(TiposContas);
		cbTipoConta.setItems(ObsTiposContas);
	}

	public void carregarPermiteCentroCusto() {
		PermiteCentroCusto N = new PermiteCentroCusto(1, "Não");
		PermiteCentroCusto S = new PermiteCentroCusto(2, "Sim");
		PermiteCentroCustos.add(N);
		PermiteCentroCustos.add(S);
		ObsPermiteCentroCusto = FXCollections.observableArrayList(PermiteCentroCustos);
		cbPermiteCentroCusto.setItems(ObsPermiteCentroCusto);
	}

	public void carregarNatureza() {
		Natureza Devedora = new Natureza(1, "Devedora");
		Natureza Credora = new Natureza(2, "Credora");
		TiposNatureza.add(Devedora);
		TiposNatureza.add(Credora);
		ObsTiposNatureza = FXCollections.observableArrayList(TiposNatureza);
		cbNatureza.setItems(ObsTiposNatureza);
	}
}
