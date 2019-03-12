package hr.asseco.onict.reader.impl;

import hr.asseco.onict.model.Student;
import hr.asseco.onict.reader.StudentReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class StudentCSVReader implements StudentReader {

    @Override
    public ConcurrentHashMap<String, Student> readStudentsToMap(String path) {
        String line;
        ConcurrentHashMap<String,Student> initialMap = new ConcurrentHashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] str = line.split(";");
                for (int i = 1; i < str.length; i++) {
                    String jmbag = str[0];
                    String ime = str[1];
                    String prezime = str[2];
                    Short ocjena = Short.valueOf(str[3]);
                    initialMap.put(jmbag, new Student(jmbag, ime, prezime, ocjena));
                }
            }
        } catch (IOException e) {
            System.out.println("Greška kod učitavanje inicijalnih podataka iz datoteke.");
            System.exit(-1);
        }
        return initialMap;
    }

}
