package com.github.LuizCzaikowski.APICovid19;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ListIterator;

/**
 *
 * @author Luiz_
 */
public class CuritibaCovid19 {

    public void printar(PessoasRep lista) {
        ListIterator<Pessoas> p = lista.lista.listIterator();
        while (p.hasNext()) {
            System.out.println(p.next());
        }
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public PessoasRep run() throws IOException {

        PessoasRep lista = new PessoasRep();

        String arquivoCSV = "CasosCovid.csv";
        String linha = "";
        String csvDivisor = ";";
        FileReader fileReader = new FileReader(arquivoCSV);

        try (BufferedReader br = new BufferedReader(fileReader)) {
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(csvDivisor, -1);
                if (!dados[0].contains("POS")) {
                    if(isNumeric(dados[3])){
                    if (dados.length > 6) {
                        Pessoas p = new Pessoas(Long.parseLong(dados[0].replace(".", "").trim()), dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5], dados[6]);
                        lista.lista.add(p);
                        continue;
                    } else if (dados[5].contains("CONF") || dados[5].contains("REC")) {
                        Pessoas p = new Pessoas(Long.parseLong(dados[0].replace(".", "").trim()), dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], "---", "###");
                        lista.lista.add(p);
                        continue;
                    } else if (dados[3].isEmpty()) {
                        Pessoas p = new Pessoas(Long.parseLong(dados[0].replace(".", "").trim()), dados[1], dados[2], 0, dados[4], "---", "###");
                        lista.lista.add(p);
                        continue;
                    } else {
                        Pessoas p = new Pessoas(Long.parseLong(dados[0].replace(".", "").trim()), dados[1], dados[2], Integer.parseInt(dados[3]), dados[4], dados[5], "###");
                        lista.lista.add(p);
                        continue;
                    }
                    }else{
                          Pessoas p = new Pessoas(Long.parseLong(dados[0].replace(".", "").trim()), dados[1], dados[2], 0, dados[4], dados[5]);
                        lista.lista.add(p);
                        continue;
                    }
                } else {
                    continue;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lista;

    }
}
