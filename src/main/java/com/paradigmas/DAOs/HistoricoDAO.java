package com.paradigmas.DAOs;

import com.paradigmas.Models.Matricula;
import com.paradigmas.Models.Aluno;
import com.paradigmas.Models.Disciplina;
import com.paradigmas.Lib.CsvReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class HistoricoDAO
{
    private static String historico_path = "./src/main/resources/data/historico.csv";
    private static String delimiter = ";";

    private enum Header {
        MATR_ALUNO(0),
        NOME_PESSOA(1),
        NUM_VERSAO(4),
        ANO(5),
        MEDIA_FINAL(6),
        PERIODO(8),
        SITUACAO(9),
        COD_ATIV_CURRIC(10),
        FREQUENCIA(14);

        public final int value;
        Header(int opValue) {
            this.value = opValue;
        }
    }

    public static boolean importar_historico(String path) throws IOException
    {
        File src = new File(path);
        if (src.exists())
        {
        	try
        	{
        		File dest = new File(historico_path);
                Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        	}
        	catch(Exception e)
        	{
        		return false;
        	}
            return true;  
        }
        return false;
    }

    public Aluno ler_historico() throws IOException
    {
        List<List<String>> records = CsvReader.ler_csv(historico_path, delimiter);

        String grr = (records.get(0)).get(Header.MATR_ALUNO.value);
        String nome = (records.get(0)).get(Header.NOME_PESSOA.value);
        Disciplina.Versao versao = ((records.get(0)).get(Header.NUM_VERSAO.value) == "2011") ? Disciplina.Versao.v2011 : Disciplina.Versao.v2019;
        List<Matricula> matricula = this.ler_matriculas(records);

        Aluno aluno = new Aluno(grr, nome, versao, matricula);
        return aluno;
    }

    private List<Matricula> ler_matriculas(List<List<String>> records)
    {
        List<Matricula> matriculas = new ArrayList<>();
        for (List<String> matr : records) {
            String grr = matr.get(Header.MATR_ALUNO.value);
            String cod_disciplina = matr.get(Header.COD_ATIV_CURRIC.value);
            Double media = Double.parseDouble(matr.get(Header.MEDIA_FINAL.value));
            int ano = Integer.parseInt(matr.get(Header.ANO.value));
            int frequencia = Integer.parseInt(matr.get(Header.FREQUENCIA.value));
            int periodo = Integer.parseInt(matr.get(Header.PERIODO.value));
            Matricula.Situacao situacao;
            switch (matr.get(Header.COD_ATIV_CURRIC.value))
            {
                case "Aprovado":
                    situacao = Matricula.Situacao.APROVADO;
                    break;
                case "Reprovado por nota":
                    situacao = Matricula.Situacao.REPROVADO_NOTA;
                    break;
                case "Reprovado por Frequência":
                    situacao = Matricula.Situacao.REPROVADO_FREQUENCIA;
                    break;
                default:
                    situacao = null;
            }

            matriculas.add((new Matricula(grr, cod_disciplina, media, ano, situacao, frequencia, periodo)));
        }
        return matriculas;
    }

}

