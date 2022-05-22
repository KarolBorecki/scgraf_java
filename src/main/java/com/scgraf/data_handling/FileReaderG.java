package src.main.com.scgraf.data_handling;

import src.main.com.scgraf.utils.CFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReaderG {

    private static boolean isOpen = false;

    private File handledFile;

    public FileReaderG(File file) throws java.io.FileNotFoundException{
        this.handledFile = file;

        BufferedReader reader = new BufferedReader(new FileReader(handledFile));
        try {
            String line = reader.readLine();
            Object [] arr = CFormat.scanf("%d %d", line);
            int x = (Integer) arr[0];
            int y = (Integer) arr[1];
            System.out.println(x + " " + y);
            for(int i= 0; i<x*y; i++){
                String newline = reader.readLine();
                newline = newline.stripIndent();
                while(!newline.isBlank()){
                    Object [] a = CFormat.scanf("%d: %f ", newline);
                    int node = (Integer) a[0];
                    double weight = (Double) a[1];
                    String inp = node + ": " + weight;
                    newline = newline.substring(inp.length());
                    System.out.println("inp = " + inp);
                    System.out.println(node + ": " + weight);
                }
            }
        }catch(java.io.IOException e){
            e.printStackTrace();
        }
    }

    public FileReaderG(String filePath){
        this.handledFile = new File(filePath);
    }

}
