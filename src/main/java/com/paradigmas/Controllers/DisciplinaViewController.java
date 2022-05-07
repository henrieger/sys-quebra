package com.paradigmas.Controllers;

import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

import com.paradigmas.DAOs.HistoricoDAO;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Disciplina;

public class DisciplinaViewController {
	private ListView<Disciplina> list_ant_barreira;
	private ListView<Disciplina> list_disc_faltantes;
	private ListView<Disciplina> list_materias;
	
	public DisciplinaViewController() {}
	public DisciplinaViewController(ListView<Disciplina> list_ant_barreira, ListView<Disciplina> list_disc_faltantes, ListView<Disciplina> list_materias) {
		this.list_ant_barreira = list_ant_barreira;
		this.list_disc_faltantes = list_disc_faltantes;
		this.list_materias = list_materias;
	}
	
	List<Disciplina> disc_barreira = null;
	List<Disciplina> disc_faltantes = null;
	Aluno aluno = null;

	public void initialize() {
		DisciplinaController dc = new DisciplinaController();
		PedidoController pc = new PedidoController();
		disc_barreira = null;
		disc_faltantes = null;
		
		try
		{	
			aluno = HistoricoDAO.ler_historico();
			disc_faltantes = dc.getDisciplinasFaltantes(aluno);
			
			disc_barreira = dc.getDisciplinasAntesBarreira(aluno);
	
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}
		
		System.out.println(list_ant_barreira);
		list_ant_barreira.getItems().addAll(disc_barreira);
		list_disc_faltantes.getItems().addAll(disc_faltantes);
		
		list_ant_barreira.setOnMouseClicked(e -> {
			Disciplina item = list_ant_barreira.getSelectionModel().getSelectedItem();
			if(item != null)
			{
				try
				{
					dc.verificarDisciplina(aluno, item, list_materias.getItems());
					list_ant_barreira.getItems().remove(list_ant_barreira.getSelectionModel().getSelectedIndex());
					list_materias.getItems().add(item);
				} catch(Exception e2)
				{
					this.alert(e2.getMessage(), true);
				}
			}
        });
		
		list_disc_faltantes.setOnMouseClicked(e -> {
			Disciplina item = list_disc_faltantes.getSelectionModel().getSelectedItem();
			if(item != null)
			{
				try
				{
					dc.verificarDisciplina(aluno, item, list_materias.getItems());
					list_disc_faltantes.getItems().remove(list_disc_faltantes.getSelectionModel().getSelectedIndex());
					list_materias.getItems().add(item);
				}catch(Exception e1)
				{
					this.alert(e1.getMessage(), true);
				}
			}
        });
		
		list_materias.setOnMouseClicked(e -> {
			Disciplina item = list_materias.getSelectionModel().getSelectedItem();
			if(item != null)
			{
				list_materias.getItems().remove(list_materias.getSelectionModel().getSelectedIndex());
				if(disc_barreira.contains(item))
					list_ant_barreira.getItems().add(item);
				else
					list_disc_faltantes.getItems().add(item);
			}
        });		
	}

	private void alert(String message, boolean erro)
	{
		AlertType at = (erro) ? AlertType.ERROR : AlertType.CONFIRMATION;
		Alert alert = new Alert(at);
		alert.setTitle((erro) ? "Erro": "Sucesso");
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	public ListView<Disciplina> getList_materias()
	{
		return list_materias;
	}
}
