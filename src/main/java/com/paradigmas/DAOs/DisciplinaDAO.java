package com.paradigmas.DAOs;

import com.paradigmas.Models.Disciplina;
import com.paradigmas.Lib.CsvReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class DisciplinaDAO {
    private static String disciplinas_path = "data/disciplinas.csv";
    private static String delimiter = ",";

    private enum Header {
        NUM_VERSAO(0),
        COD_DISCIPLINA(1),
        NOME_DISCIPLINA(2),
        PERIODO_IDEAL(3),
        TIPO_DISCIPLINA(4),
        CH_TOTAL(5),
        SITUACAO_VERSAO(6);

        public final int value;
        Header(int opValue) {
            this.value = opValue;
        }
    }

    public List<Disciplina> ler_disciplinas() throws IOException
    {
        List<Disciplina> disciplina = new ArrayList<>();

        List<List<String>> records = CsvReader.ler_csv(disciplinas_path, delimiter);

        if (!records.isEmpty())
        {
            for (List<String> disp : records) {
                String cod_disciplina = disp.get(Header.COD_DISCIPLINA.value);
                String nome = disp.get(Header.NOME_DISCIPLINA.value);
                int periodo = Integer.parseInt(((disp.get(Header.PERIODO_IDEAL.value)).isEmpty()) ? "-1" : disp.get(Header.PERIODO_IDEAL.value));
                Disciplina.Versao versao = ((disp.get(Header.NUM_VERSAO.value)).equals("2011")) ? Disciplina.Versao.v2011 : Disciplina.Versao.v2019;
                int ch_total = Integer.parseInt(disp.get(Header.CH_TOTAL.value));
                Disciplina.Tipo tipo = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("ATIVA")) ? Disciplina.Tipo.OBRIGATORIA : Disciplina.Tipo.OPTATIVA;
                Disciplina.Situacao situacao = ((disp.get(Header.TIPO_DISCIPLINA.value)).equals("Obrigatoria")) ? Disciplina.Situacao.ATIVA : Disciplina.Situacao.DESATIVADA;
    
                disciplina.add(new Disciplina(cod_disciplina, nome, periodo, versao, ch_total, tipo, situacao));
            }
    
            return disciplina;
        }
        return new ArrayList<>();
    }
}
