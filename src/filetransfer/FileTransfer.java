/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filetransfer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author batuh
 */
public class FileTransfer {

    /**
     * @param args the command line arguments
     */
    
    
    private static final String BASE_PATH = "C:\\Users\\batuh\\Desktop\\dosyalar\\";
    private static File testDataTxt;
    private static File trainDataTxt;
    private static String[] files;
    private static File Base_Directory;
    private static String TestDataPath = "C:\\Users\\batuh\\Desktop\\testData\\";
    private static String TrainDataPath = "C:\\Users\\batuh\\Desktop\\trainData\\";
    private static FileWriter wr = null;
    
    public static void main(String[] args) {
        //initial config
        Base_Directory = new File(BASE_PATH);
        if (Base_Directory.exists()) {
            try {
                Base_Directory.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        files = Base_Directory.list();
        testDataTxt = new File("C:\\Users\\batuh\\Desktop\\" + "TestData.txt");
        if (!testDataTxt.exists()) {
            try {
                testDataTxt.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        trainDataTxt = new File("C:\\Users\\batuh\\Desktop\\" + "TrainData.txt");
        if (!trainDataTxt.exists()) {
            try {
                trainDataTxt.createNewFile();
            } catch (Exception e) {
                Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
        int filesCount = files.length;
        int testData = (int)(filesCount * 0.2f);   
        String transferData = null;
        for (int i = 0; i < testData; i++) {
            transferData = files[(int)(Math.random()*filesCount)]; 
            FileTransfer(TestDataPath, transferData, true);
        }
        
        for (int i = 0; i < filesCount; i++) {
             transferData = files[i];
             FileTransfer(TrainDataPath, transferData, false);
        }
    }
   
  
    
    public static void FileTransfer(String transferPath, String fileName,boolean isTest){
        try {
            Path temp = Files.move(Paths.get(BASE_PATH + fileName),Paths.get(transferPath+fileName));
            if(temp != null)
            {
                if (isTest) {
                    SaveLog(transferPath, fileName, true);
                }else{
                    SaveLog(transferPath, fileName, false);
                }
                
            }
            else
            {
                System.out.println(fileName + "error.");
            }
        } catch (IOException ex) {
            
        }
    }
    
    public static void SaveLog(String path, String fileName, boolean isTest){
        try {
            if (isTest) {
            FileWriter wr = null;
           
            wr = new FileWriter(testDataTxt,true);
            
            BufferedWriter bufferedWriter = new BufferedWriter(wr);
 
            bufferedWriter.write(path+fileName);
            bufferedWriter.newLine();
 
            bufferedWriter.close();
            }else{
            FileWriter wr = null;
           
            wr = new FileWriter(trainDataTxt,true);
            
            BufferedWriter bufferedWriter = new BufferedWriter(wr);
 
            bufferedWriter.write(path+fileName);
            bufferedWriter.newLine();
 
            bufferedWriter.close();  
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
