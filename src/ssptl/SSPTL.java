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

    private static ArrayList<String> TAG = new ArrayList<String>();
    private static ArrayList<String> OPCODE = new ArrayList<String>();
    private static ArrayList<String> OPER = new ArrayList<String>();
    //private static boolean exit = false;
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
        
        while((line = br.readLine())!= null){
                String[] instructionSet = line.split("\t");
                for (int i = 0; i < instructionSet.length; i++) {
                    if(instructionSet[i].matches("^\\p{Alpha}*\\p{Alnum}*") && (i == 0 && instructionSet[i].length() <= 8)) {
                        if(instructionSet[i].isEmpty()){
                            TAG.add(etq + "NULL");
                        } else {
                            TAG.add(etq + instructionSet[i]);
                        }
                    }else
                        if(instructionSet[i].matches("(\\p{Alpha}*)|(\\p{Alpha}*.{0,1}\\p{Alpha}*)|(\\p{Alpha}*)") && ((i == 1) && instructionSet[i].length() <= 5)){
                            if(instructionSet[i].isEmpty()){
                                OPCODE.add(CODOP + "NULL");
                            } else {
                                OPCODE.add(CODOP + instructionSet[i]);
                            }
                        }else
                            if(instructionSet[i].matches("\\p{Punct}*\\p{Alnum}*") && i==2) {
                                if(instructionSet[i].isEmpty()){
                                    OPER.add(op + "NULL");
                                } else {
                                    OPER.add(op + instructionSet[i]);
                                }
                            }
            }
        }
        
        fr.close();
    }
    
    public static void main(String[] args) throws IOException {
        reader("src/txt/P1ASM.txt");
        
       
         for (String etiqueta : TAG) {
            System.out.println(etiqueta);
            
            
        }
        for (String codop : OPCODE) {
                System.out.println(codop);
        }
        for (String op : OPER) {
                    System.out.println(op);
                }
    }
    }
