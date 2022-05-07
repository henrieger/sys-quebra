package com.paradigmas.Controllers;

import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Pedido;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

public class PedidoViewController {
    private TableView<Disciplina> table;

    private Pedido pedidoAtual;
    
    public Pedido getPedidoAtual()
    {
        return pedidoAtual;
    }

    public void setPedidoAtual(Pedido pedido)
    {
        this.pedidoAtual = pedido;
    }

    public PedidoViewController() {}
    public PedidoViewController(TableView<Disciplina> table) {
        this.table = table;
    }

    public void initialize() {
        ObservableList<TableColumn<Disciplina, ?>> columns = table.getColumns();

        TableColumn<Disciplina, ?> cod_disc = columns.get(0);
        TableColumn<Disciplina, ?> nome_disc = columns.get(1);

        cod_disc.setCellValueFactory(new PropertyValueFactory<>("cod_disciplina"));
        nome_disc.setCellValueFactory(new PropertyValueFactory<>("nome"));

        updateTableData(pedidoAtual);
    }

    public void updateTableData(Pedido pedido)
    {
        ObservableList<Disciplina> disciplinas;
        setPedidoAtual(pedido);
        try {
            disciplinas = FXCollections.observableList(pedido.getDisciplina());
        } catch (Exception e)
        {
            disciplinas = FXCollections.observableArrayList();
        }

        table.getItems().clear();
        table.getItems().addAll(disciplinas);
        table.refresh();
    }
}
