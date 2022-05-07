package com.paradigmas.Controllers;


import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.paradigmas.DAOs.HistoricoDAO;
import com.paradigmas.DAOs.PedidoDAO;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Pedido;

import javafx.collections.ObservableList;

import java.util.List;
public class PedidoController {
	
	
	public static Pedido gera_pedido(ObservableList<Disciplina> disciplina) throws Exception
	{
		Aluno aluno = HistoricoDAO.ler_historico();
		Pedido pedido = new Pedido(disciplina, aluno.getNome(), aluno.getGrr());
		
		return pedido;
	}
	
	public static boolean salva_pedido(Pedido pedido) throws JsonSyntaxException, IOException
	{

		PedidoDAO.salva_pedido(pedido);
		
		return true;
	}
	
	
	public static Pedido ler_pedido() throws IOException
	{
		return PedidoDAO.ler_pedido();
	}
	
	
}
