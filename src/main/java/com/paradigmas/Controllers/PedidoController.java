package com.paradigmas.Controllers;


import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.paradigmas.DAOs.PedidoDAO;
import com.paradigmas.Models.Pedido;

public class PedidoController {
	
	
	public Pedido gera_pedido(String [] disciplina, String nome, String grr, String tel,String formando,String obrigatoria, String optativa, String justificativa)
	{
		int telefone = Integer.parseInt(tel);
		boolean is_formando = Boolean.parseBoolean(formando);
		int total_disc_obrigatoria = Integer.parseInt(obrigatoria);
		int total_disc_optativas = Integer.parseInt(optativa);
		
		Pedido pedido = new Pedido(disciplina, nome, grr, telefone, is_formando, total_disc_obrigatoria, total_disc_optativas, justificativa);
		
		return pedido;
	}
	
	public static boolean salva_pedido(String [] disciplina, String nome, String grr, String tel,String formando,String obrigatoria, String optativa, String justificativa) throws JsonSyntaxException, IOException
	{
		int telefone = Integer.parseInt(tel);
		boolean is_formando = Boolean.parseBoolean(formando);
		int total_disc_obrigatoria = Integer.parseInt(obrigatoria);
		int total_disc_optativas = Integer.parseInt(optativa);
		
		Pedido pedido = new Pedido(disciplina, nome, grr, telefone, is_formando, total_disc_obrigatoria, total_disc_optativas, justificativa);
		
		PedidoDAO.salva_pedido(pedido);
		
		return true;
	}
	
	
	public static Pedido ler_pedido() throws IOException
	{
		return PedidoDAO.ler_pedido();
	}
	
	
}
