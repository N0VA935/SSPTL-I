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

/**
 *
 * @author José David Delgado Ramírez
 */
public class SSPTL {

    /**
     * @param args the command line arguments
     */
    public static void reader(String txt) throws FileNotFoundException, IOException{
        String line;
        FileReader fr = new FileReader(txt);
        BufferedReader br = new BufferedReader(fr);
        while((line = br.readLine())!= null){
            System.out.println(line);
        }
        fr.close();
    }
    public static void main(String[] args) throws IOException {
        reader("src/txt/P1ASM.txt");
    }
    
}
