package com.company;

import com.opencsv.CSVWriter;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> result = new ArrayList<>();

        List<String> records = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("subject3.csv"));) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                records.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String record : records) {
            result.add(getMimeDecodedWord(record));
        }

        FileWriter file = new FileWriter("subject33.csv");
        CSVWriter writer = new CSVWriter(file);
        String[] headers = {"key", "value"};
        writer.writeNext(headers);
        for (int i = 0; i < records.size(); i++) {
            String[] data = {records.get(i), result.get(i)};
            writer.writeNext(data);
        }
        writer.close();

    }

    public static String getMimeDecodedWord(String encoded) {
        if (encoded == null) {
            return null;
        }
        try {
            return MimeUtility.decodeText(encoded);
        } catch (UnsupportedEncodingException e) {
            return encoded;
        }
    }
}
