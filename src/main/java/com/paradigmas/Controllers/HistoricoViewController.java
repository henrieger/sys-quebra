package com.paradigmas.Controllers;

import java.util.*;

import com.paradigmas.DAOs.HistoricoDAO;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Matricula;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class HistoricoViewController{
    
    private Pane painelHistorico;

    public HistoricoViewController() {}
    public HistoricoViewController(Pane painelHistorico) {
        this.painelHistorico = painelHistorico; 
    }

    public void initialize()
    {
        try{
            Aluno aluno = HistoricoDAO.ler_historico();
            List<TableWithTitle> tabelas = createTables(aluno);
            for (TableWithTitle tabela : tabelas) {
                painelHistorico.getChildren().add(tabela.getTitle());
                painelHistorico.getChildren().add(tabela.getTable());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    List<TableWithTitle> createTables(Aluno aluno)
    {
        Map<String, List<Matricula>> matriculasPorPeriodo = getMatriculasPorPeriodo(aluno);
        List<TableWithTitle> tabelas = new ArrayList<TableWithTitle>();

        for (Map.Entry<String, List<Matricula>> listaPeriodos : matriculasPorPeriodo.entrySet()) {            
            ObservableList<Matricula> conteudo = FXCollections.observableArrayList(listaPeriodos.getValue());
            if(conteudo.size() > 0)
            {
                String ano = getAnoConcatenado(listaPeriodos.getKey());
                String periodo = getPeriodoConcatenado(listaPeriodos.getKey());

                Label tableTitle = new Label(periodo + "º Semestre de " + ano);
                tableTitle.setMaxWidth(Double.MAX_VALUE);
                TableView<Matricula> table = novaTabelaMatriculas(conteudo);

                TableWithTitle tableWithTitle = new TableWithTitle(table, tableTitle);
                tabelas.add(tableWithTitle);
            }
        }

        return tabelas;
    }

    TableView<Matricula> novaTabelaMatriculas(ObservableList<Matricula> conteudo)
    {
        TableView<Matricula> table = new TableView<Matricula>();
        TableColumn<Matricula, String> codigo = new TableColumn<Matricula, String>("Cod. Disciplina");
        // TableColumn<Matricula, String> disciplina = new TableColumn<Matricula, String>("Disciplina");
        TableColumn<Matricula, Double> nota = new TableColumn<Matricula, Double>("Nota");
        TableColumn<Matricula, String> frequencia = new TableColumn<Matricula, String>("Frequência");
        // TableColumn<Matricula, Matricula.Situacao> situacao = new TableColumn<Matricula, Matricula.Situacao>("Situação");

        codigo.setCellValueFactory(new PropertyValueFactory<>("cod_disciplina"));
        // disciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        nota.setCellValueFactory(new PropertyValueFactory<>("media_final"));
        frequencia.setCellValueFactory(new PropertyValueFactory<>("frequencia"));
        // situacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        table.getColumns().add(codigo);
        // table.getColumns().add(disciplina);
        table.getColumns().add(nota);
        table.getColumns().add(frequencia);
        // table.getColumns().add(situacao);

        table.setItems(conteudo);
        table.setFixedCellSize(20);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.4)));
        table.minHeightProperty().bind(table.prefHeightProperty());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    Map<String, List<Matricula>> getMatriculasPorPeriodo(Aluno aluno)
    {
        List<Matricula> matriculas = aluno.getMatricula();
        Map<String, List<Matricula>> matriculasPorPeriodo = new HashMap<String, List<Matricula>>();
        for (Matricula matricula : matriculas) {
            String periodoFormatado = concatenaPeriodo(matricula.getAno(), matricula.getPeriodo());
            if(matricula.getFrequencia() > 0)
            {
                if(matriculasPorPeriodo.get(periodoFormatado) == null)
                {
                    List<Matricula> lista = new ArrayList<Matricula>();
                    matriculasPorPeriodo.put(periodoFormatado, lista);
                }
                matriculasPorPeriodo.get(periodoFormatado).add(matricula);
            }
        }

        return matriculasPorPeriodo;
    }

    String concatenaPeriodo(int ano, int periodo)
    {
        return ano + "-" + periodo;
    }

    String getPeriodoConcatenado(String periodoFormatado)
    {
        return periodoFormatado.split("-")[1];
    }

    String getAnoConcatenado(String periodoFormatado)
    {
        return periodoFormatado.split("-")[0];
    }
}

class TableWithTitle
{
    private TableView<Matricula> table;
    private Label title;

    public TableWithTitle() {}    
    public TableWithTitle(TableView<Matricula> table, Label title) {
        this.table = table;
        this.title = title;
    }

    public TableView<Matricula> getTable() { return table; }
    public Label getTitle() { return title; }
}
