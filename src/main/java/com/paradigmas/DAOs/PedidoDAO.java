package com.paradigmas.DAOs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.paradigmas.Models.Disciplina;
import com.paradigmas.Models.Pedido;

public class PedidoDAO {
	
public static Pedido ler_pedido() throws IOException
	{
	    Gson gson = new Gson();
	    Reader reader = Files.newBufferedReader(Paths.get("pedido.json"));
	    Pedido pedido = gson.fromJson(reader, Pedido.class);
	    
	    reader.close();
	    
	    return pedido;
	}

	public static void salva_pedido(Pedido pedido) throws JsonSyntaxException, IOException
	{
	    String caminho = "./src/main/resources/data/pedido.json";
		try (Writer writer = new FileWriter(caminho)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(pedido, writer);
		}
	}
	
	
	public static void gera_pedido(Pedido pedido) throws IOException
    {
        try {
            FileWriter fw= new FileWriter("./src/main/resources/data/pedido.txt");


            fw.write("Nome: "+ pedido.getNome() + "\n");
            fw.write("GRR: "+ pedido.getGrr() + "\n");
            fw.write("Disciplinas:\n");
			for (Disciplina disciplina : pedido.getDisciplina()) {
				fw.write("\t"+disciplina+"\n");
			}

            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }

    }
}
