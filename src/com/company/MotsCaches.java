package com.company;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MotsCaches {

    BufferedReader reader;
    List<String> dictionary = new ArrayList<>();
    List<String> grid = new ArrayList<>();
    List<String> rows = new ArrayList<>();
    List<String> resolvedWord = new ArrayList<>();
    int dimension;

    public MotsCaches(String dictionaryFile, String gridFile){
        fileToList(dictionaryFile,"dict");
        fileToList(gridFile, "grid");
        getRows();
        horizontalSearch();
    }

    public void fileToList(String file, String typeOfFile) {
        try {
            reader = new BufferedReader(new FileReader(file));
            switch(typeOfFile) {
                case "dict":
                    String line = reader.readLine();
                    while(line != null) {
                        dictionary.add(line);
                        line = reader.readLine();
                    }
                    break;
                default:
                    int value = reader.read();
                    while(value != -1) {
                        char charValue = (char) value;
                        if(value != 10){
                            grid.add(String.valueOf(charValue));
                        }
                        value = reader.read();
                    }
                    dimension =  Integer.parseInt(grid.get(0));
                    grid.remove(0);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readList(List<String> array){
        for(int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i) + " at position: " + i);
        }
    }

    public void getRows() {
        String rowValue = "";
        for(int i = 0; i < grid.size(); i++){
            rowValue += grid.get(i);
            if(rowValue.length() == 8) {
                rows.add(rowValue);
                rowValue = "";
            }
        }
    }

    public void horizontalSearch() {
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dictionary.size(); j++){
                if(rows.get(i).contains(dictionary.get(j))) {
                    resolvedWord.add(dictionary.get(j));
                }
            }
        }
    }
}
