package utils;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log {

    public void iniciarLog() throws IOException {
        String[] cabecalho = {"dataHora", "tipo", "mensagem", "duracao"};
        String dataHorainicial = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
        System.out.println("Data e hora : " + dataHorainicial);
        Writer writer = Files.newBufferedWriter(Paths.get("target/logs/userDD-" + dataHorainicial + ".csv"));
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(cabecalho);
        csvWriter.flush();
        csvWriter.close();
        writer.close();

    }
    public void logar (String dataHora, String tipo, String mensagem, int duracao){


    }
    public void finalizarLog(){


    }
}
