package hr.asseco.onict.reader;

import hr.asseco.onict.model.Student;

import java.util.concurrent.ConcurrentHashMap;

public interface StudentReader {

    public ConcurrentHashMap<String, Student> readStudentsToMap(String path);

}
