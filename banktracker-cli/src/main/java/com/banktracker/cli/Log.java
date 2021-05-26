/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banktracker.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author aless
 */
public class Log {
    private String errorMessage;
    private String errorException;

    public Log(String errorMessage, String errorException) {
        this.errorMessage = errorMessage;
        this.errorException = errorException;
    }
   
    public void GerarLog(){
        try{
        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
        String date = new SimpleDateFormat("dd-MM-yyyy").format(dataDeHoje.getTime());
        
        System.out.println("Gerando log de erro...");
        File arquivo = new File("c:\\logs\\"+date+".txt");
        FileWriter fw = new FileWriter(arquivo, true);
        PrintWriter gravarArq = new PrintWriter(fw);
        gravarArq.println(dataDeHoje+" "+errorMessage+": "+errorException+"\n");
        fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }  
}
