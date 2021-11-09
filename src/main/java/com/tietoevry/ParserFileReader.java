package com.tietoevry;

import java.io.*;
import java.util.List;

public class ParserFileReader {
    public static List<String> parserFileReaderMethod (String filepath, List<String> messages) throws Exception{
        File file = new File(filepath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
