package com.paradigmas.Lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;

public class CsvReader 
{
    public static List<List<String>> ler_csv(String path, String delimiter) throws IOException {
        if ((new File(path)).exists()) {
            List<List<String>> records = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(delimiter);
                    records.add(Arrays.asList(values));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ArrayList<>();
            }
            records.remove(0);
            return records;
        }
        return new ArrayList<>();
    }
}
