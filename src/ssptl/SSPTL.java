/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssptl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author José David Delgado Ramírez
 */
public class SSPTL {

    private static ArrayList<String> COMENT = new ArrayList<String>();
    private static boolean exit = false;
    private static String etq = "ETIQUETA= ";
    private static String CODOP = "CODOP= ";
    private static String op = "OPERANDO= ";
    
    /**
     * @param txt Direccion de donde se tomara el txt para la lectura
     */
    public static void reader(String txt) throws FileNotFoundException, IOException{
        String line;    // Variable para leer temporalmente las lineas del txt
        int contAux = 0;
        // Lectores para cargar el .txt
        FileReader fr = new FileReader(txt);
        BufferedReader br = new BufferedReader(fr);
        
        while((line = br.readLine())!= null && exit == false){
                String[] instructionSet = line.split("\t");
                for (int i = 0; i < instructionSet.length; i++) {
                    contAux++;
                    if(instructionSet[i].startsWith(";") && instructionSet[i].length() < 80) {
                        COMENT.add(instructionSet[i]);
                    }
                    if(instructionSet[i].matches("^\\p{Alpha}*\\p{Alnum}*") && (i == 0 && instructionSet[i].length() <= 8)) {
                        if(instructionSet[i].isEmpty()){
                            COMENT.add(etq + "NULL");
                        } else {
                            COMENT.add(etq + instructionSet[i]);
                        }
                    }
                    if(instructionSet[i].matches("(\\p{Alpha}*)|(\\p{Alpha}*.{0,1}\\p{Alpha}*)|(\\p{Alpha}*)") && ((i == 1) && instructionSet[i].length() <= 5)){
                        if((instructionSet[i].equals("end") || instructionSet[i].equals("End") || instructionSet[i].equals("END"))) {
                            exit=true;
                        }else
                            if(instructionSet.length == 2) {
                                COMENT.add(CODOP + instructionSet[i]);
                                COMENT.add(op + "NULL");
                            } else{
                                COMENT.add(CODOP + instructionSet[i]);
                            }
                        
                    }
                    if(instructionSet[i].matches("\\p{Punct}*\\p{Alnum}*") && i==2) {
                        if(instructionSet[i].isEmpty() && contAux == 1){
                            COMENT.add(op + "NULL");
                            contAux = 0;
                        } else {
                            COMENT.add(op + instructionSet[i]);
                        }
                    }
            }
        }
        
        fr.close();
    }
    
    public static void main(String[] args) throws IOException {
        reader("src/txt/P1ASM.txt");
        
        for (int i = 0; i < COMENT.size()-1; i++) {
            System.out.println(COMENT.get(i));
            
            
        }
    }
}
