package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class Data {
    // Função para ler um arquivo JSON

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));


    }

    // Função para ler um arquivo CSV

    public List<String[]> lerCsv(String caminhoCsv) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(caminhoCsv));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        List<String[]> users = csvReader.readAll();
        return users;


    }
}
