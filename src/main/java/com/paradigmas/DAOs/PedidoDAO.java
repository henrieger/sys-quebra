package com.paradigmas.DAOs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import com.paradigmas.Lib.CsvReader;
import com.paradigmas.Models.Pedido;

public class PedidoDAO {
	
//	private static String pedido_path = "./src/main/resources/data/pedido/pedido.csv";
//    private static String delimiter = ";";
	
//    private enum Header {
//    	disciplina(0),
//    	nome(1),
//    	grr(2),
//    	telefone(3),
//    	is_formando(4),
//    	total_disc_obrigatoria(5),
//    	total_disc_optativas(6),
//    	justificativa(7);
//
//        public final int value;
//        Header(int opValue) {
//            this.value = opValue;
//        }
//    }
    
//	public Pedido ler_pedido(String path) throws IOException
//	{
//		List<List<String>> records = CsvReader.ler_csv(pedido_path, delimiter);
//		
//		String[] disciplina = (String[]) (records.get(0)).get(Header.disciplina.value);
//		String nome = (records.get(0)).get(Header.nome.value);
//		String grr = (records.get(0)).get(Header.grr.value);
//		String tel = (records.get(0)).get(Header.telefone.value);
//		String formando = (records.get(0)).get(Header.is_formando.value);
//		String obrigatoria = (records.get(0)).get(Header.total_disc_obrigatoria.value);
//		String optativas = (records.get(0)).get(Header.total_disc_optativas.value);
//		String justificativa = (records.get(0)).get(Header.justificativa.value);
//		
//		Boolean is_formando = parseBoolean(formando);
//		int telefone = Integer.valueOf(tel);
//		int total_disc_obrigatoria = Integer.valueOf(obrigatoria);
//		int total_disc_optativas = Integer.valueOf(optativas);
//		
//		
//		Pedido pedido = new Pedido(disciplina, nome, grr, telefone, is_formando,  total_disc_obrigatoria, total_disc_optativas, justificativa);
//		
//		return pedido;
//	}
	
	
	public static void salva_pedido(Pedido pedido)
	{
		 try {
			   PrintWriter pw= new PrintWriter(new File("./src/main/resources/data/pedido/pedido.csv"));
			   StringBuilder sb=new StringBuilder();
			   sb.append("disciplina");
			   sb.append(",");
			   sb.append("nome");
			   sb.append(",");
			   sb.append("grr");
			   sb.append(",");
			   sb.append("telefone");
			   sb.append(",");
			   sb.append("is_formando");
			   sb.append(",");
			   sb.append("total_disc_obrigatoria");
			   sb.append(",");
			   sb.append("total_disc_optativas");
			   sb.append(",");
			   sb.append("justificativa");
			   sb.append(",");
			   sb.append("\r\n");
			   sb.append(pedido.getDisciplina());
			   sb.append(",");
			   sb.append(pedido.getNome());
			   sb.append(",");
			   sb.append(pedido.getGrr());
			   sb.append(",");
			   sb.append(pedido.getTelefone());
			   sb.append(",");
			   sb.append(pedido.getIsFormando());
			   sb.append(",");
			   sb.append(pedido.getTotalObrigatorias());
			   sb.append(",");
			   sb.append(pedido.getTotalOptativas());
			   sb.append(",");
			   sb.append(pedido.getJustificativa());
			   sb.append("\r\n");
			   pw.write(sb.toString());
			   pw.close();
			   } catch (Exception e) {
			      // TODO: handle exception
			   }
	}
	
	
}
