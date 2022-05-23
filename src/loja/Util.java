package loja;

import java.time.LocalDate;

public class Util {


    public static String formatarData(LocalDate data){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);       
        return dataFormatada;
    }
    
}
