package com.lojaJogos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Rotinas uteis para operacoes gerais no sistema
 */
public class Util {
    /**
     * Retorna a data informada formatada no padrao do sistema
     * 
     * @param LocalDate data
     * @return String
     */
    public static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = data.format(formatter);
        return dataFormatada;
    }
}
