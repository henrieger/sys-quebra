package com.paradigmas.Controllers;


import com.paradigmas.DAOs.PedidoDAO;
import com.paradigmas.Models.Pedido;

public class PedidoController {
	
	public static boolean salva_pedido(String disciplina, String nome, String grr, String telefone,String is_formando,String total_disc_obrigatoria, String total_disc_optativas, String justificativa)
	{
		Boolean formando = parseBoolean(is_formando);
		int obrigatorias = Integer.parseInt(total_disc_obrigatoria);
		int optativas =Integer.parseInt(total_disc_optativas);
		long tel = Long.parseLong(telefone);
		Pedido pedido = new Pedido(disciplina, nome,  grr, tel, formando, obrigatorias, optativas, justificativa);
		
		PedidoDAO.salva_pedido(pedido);
		return true;
	}
	
	public static boolean parseBoolean(String b) 
	{
	  return "true".equalsIgnoreCase(b) ? true : false;
	}
	
}
