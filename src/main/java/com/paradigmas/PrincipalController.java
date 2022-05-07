package com.paradigmas;

import java.util.List;
import com.paradigmas.Models.*;
import com.paradigmas.Controllers.*;
import com.paradigmas.DAOs.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class PrincipalController implements Initializable{

	private void alert(String message, boolean erro)
	{
		AlertType at = (erro) ? AlertType.ERROR : AlertType.CONFIRMATION;
		Alert alert = new Alert(at);
		alert.setTitle((erro) ? "Erro": "Sucesso");
		alert.setHeaderText(message);
		alert.showAndWait();
	}
	
	@FXML
	private ListView<Disciplina> list_ant_barreira;
	
	@FXML
	private ListView<Disciplina> list_disc_faltantes;
	
	@FXML
	private ListView<Disciplina> list_materias;
	
	
	List<Disciplina> disc_barreira = null;
	List<Disciplina> disc_faltantes = null;
	Aluno aluno = null;
	
	public PrincipalController() throws IOException
	{
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DisciplinaController dc = new DisciplinaController();
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
}