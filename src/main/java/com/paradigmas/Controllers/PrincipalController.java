package com.paradigmas.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.paradigmas.App;
import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Pedido;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.control.SingleSelectionModel;

public class PrincipalController implements Initializable {

	// Tab Pane pai
	@FXML private TabPane tPane;
	@FXML private Tab tabPedidos;
	
	// Campos de disciplina
	@FXML private ListView<Disciplina> list_ant_barreira;
	@FXML private ListView<Disciplina> list_disc_faltantes;
	@FXML private ListView<Disciplina> list_materias;

	// Campos de pedido
	@FXML private TableView<Disciplina> tabelaPedido;

	// Campos de histórico
	@FXML private Pane painelHistorico;

	// Campos de perfil
	@FXML private Label perfilNome;
    @FXML private Label perfilMaterias;
    @FXML private Label perfilAprovacao;
    @FXML private Label perfilReprovacaoNota;
    @FXML private Label perfilReprovacaoFalta;

	private DisciplinaViewController disciplinaController;
	private PedidoViewController pedidoViewController;
	private HistoricoViewController historicoViewController;
	private PerfilController perfilController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disciplinaController = new DisciplinaViewController(list_ant_barreira, list_disc_faltantes, list_materias);
		pedidoViewController = new PedidoViewController(tabelaPedido);
		historicoViewController = new HistoricoViewController(painelHistorico);
		perfilController = new PerfilController(perfilNome, perfilMaterias, perfilAprovacao, perfilReprovacaoNota, perfilReprovacaoFalta);

		disciplinaController.initialize();
		pedidoViewController.initialize();
		historicoViewController.initialize();
		perfilController.initialize();
	}

	@FXML
	private void prosseguirParaPedido() throws Exception {
		ObservableList<Disciplina> disciplinas = disciplinaController.getList_materias().getItems();
		Pedido pedido = PedidoController.cria_pedido(disciplinas);
		pedidoViewController.updateTableData(pedido);
		SingleSelectionModel<Tab> selectionModel = tPane.getSelectionModel();
		selectionModel.select(tabPedidos);
		// disciplinas.removeAll(disciplinas);
	}

	@FXML
	private void salvarPedido() throws Exception
	{
		ObservableList<Disciplina> disciplinas = disciplinaController.getList_materias().getItems();
		Pedido pedido = PedidoController.cria_pedido(disciplinas);
		PedidoController.salva_pedido(pedido);
		App.setRoot("primary");
		alert("Sessão salva com sucesso!", false);
	}

	@FXML
	private void gerarPedido() throws Exception
	{
		ObservableList<Disciplina> disciplinas = disciplinaController.getList_materias().getItems();
		Pedido pedido = PedidoController.cria_pedido(disciplinas);
		String caminho = PedidoController.gera_pedido(pedido);
		App.setRoot("primary");
		alert("Pedido gerado no arquivo " + caminho, false);
	}

	private void alert(String message, boolean erro)
	{
		AlertType at = (erro) ? AlertType.ERROR : AlertType.CONFIRMATION;
		Alert alert = new Alert(at);
		alert.setTitle((erro) ? "Erro": "Sucesso");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
}