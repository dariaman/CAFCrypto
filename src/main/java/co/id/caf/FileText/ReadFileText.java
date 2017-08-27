/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.caf.FileText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author DJ
 */
public class ReadFileText {

    private String FileText;
    
    public ReadFileText() {
        FileText="C:\\tmp\\KEY.txt";
    }

    public ReadFileText(String FilePath) {
        FileText = FilePath;
    }

    public ArrayList Read() {
        ArrayList<String> arr = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(FileText)))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
        return arr;
    }
}