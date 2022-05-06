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
import javafx.scene.control.ListView;

public class PrincipalController implements Initializable{

	@FXML
	private ListView<Disciplina> list_ant_barreira;
	
	@FXML
	private ListView<Disciplina> list_disc_faltantes;
	
	@FXML
	private ListView<Disciplina> list_materias;
	
	
	List<Disciplina> disc_barreira = null;
	List<Disciplina> disc_faltantes = null;
	
	public PrincipalController() throws IOException
	{
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		disc_barreira = null;
		disc_faltantes = null;
		
		try
		{
			Aluno aluno = HistoricoDAO.ler_historico();
			
			disc_faltantes = DisciplinaDAO.getDisciplinas();
			
			disc_barreira = DisciplinaDAO.getDisciplinasAntesBarreira();
			disc_faltantes.removeAll(disc_barreira);
			
			for (Matricula m : aluno.getMatricula())
			{
				disc_barreira.removeIf(obj -> obj.getCod_disciplina().equals(m.getCod_disciplina()));
				disc_faltantes.removeIf(obj -> obj.getCod_disciplina().equals(m.getCod_disciplina()));
			}
	
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		list_ant_barreira.getItems().addAll(disc_barreira);
		list_disc_faltantes.getItems().addAll(disc_faltantes);
		
		list_ant_barreira.setOnMouseClicked(e -> {
			Disciplina item = list_ant_barreira.getSelectionModel().getSelectedItem();
			if(item != null)
			{
				list_ant_barreira.getItems().remove(list_ant_barreira.getSelectionModel().getSelectedIndex());
				list_materias.getItems().add(item);
			}
        });
		
		list_disc_faltantes.setOnMouseClicked(e -> {
			Disciplina item = list_disc_faltantes.getSelectionModel().getSelectedItem();
			if(item != null)
			{
				list_disc_faltantes.getItems().remove(list_disc_faltantes.getSelectionModel().getSelectedIndex());
				list_materias.getItems().add(item);
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