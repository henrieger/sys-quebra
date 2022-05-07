package com.paradigmas.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.paradigmas.Models.Disciplina;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class PrincipalController implements Initializable {
	
	// Campos de disciplina
	@FXML private ListView<Disciplina> list_ant_barreira;
	@FXML private ListView<Disciplina> list_disc_faltantes;
	@FXML private ListView<Disciplina> list_materias;

	// Campos de histórico
	@FXML private Pane painelHistorico;

	// Campos de perfil
	@FXML private Label perfilNome;
    @FXML private Label perfilMaterias;
    @FXML private Label perfilAprovacao;
    @FXML private Label perfilReprovacaoNota;
    @FXML private Label perfilReprovacaoFalta;

	private DisciplinaViewController disciplinaController = new DisciplinaViewController(list_ant_barreira, list_disc_faltantes, list_materias);
	private HistoricoViewController historicoViewController = new HistoricoViewController(painelHistorico);
	private PerfilController perfilController = new PerfilController(perfilNome, perfilMaterias, perfilAprovacao, perfilReprovacaoNota, perfilReprovacaoFalta);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disciplinaController = new DisciplinaViewController(list_ant_barreira, list_disc_faltantes, list_materias);
		historicoViewController = new HistoricoViewController(painelHistorico);
		perfilController = new PerfilController(perfilNome, perfilMaterias, perfilAprovacao, perfilReprovacaoNota, perfilReprovacaoFalta);

		disciplinaController.initialize();
		historicoViewController.initialize();
		perfilController.initialize();
	}
}