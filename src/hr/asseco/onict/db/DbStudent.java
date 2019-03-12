package hr.asseco.onict.db;

import hr.asseco.onict.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DbStudent {

    private ConcurrentHashMap<String, Student> studentsMap;

    private static DbStudent instance;

    private DbStudent() {};

    public static DbStudent getInstance() {
        if(instance == null){
            synchronized (DbStudent.class) {
                if(instance == null){
                    instance = new DbStudent();
                }
            }
        }
        return instance;
    }

    public void init(ConcurrentHashMap<String, Student> studentsMap) {
        this.studentsMap = studentsMap;
    }

    public void createStudent (Student newStudent) {
        this.studentsMap.put(newStudent.getJmbag(), newStudent);
    }

    public Student readStudent (String jmbag) {
        return this.studentsMap.get(jmbag);

        /*
        Optional<Student> student = studentsMap.entrySet().stream()
                .filter(element ->
                        element.getValue().getJmbag().equals(jmbag))
                .map(e -> e.getValue())
                .findFirst();

        if (student.isPresent()) return student.get();
        return null;
        */
    }

    public List<Student> filterGrade (Short grade, String condition) {
        return studentsMap.entrySet().stream()
                .filter(element ->
                        element.getValue().getOcjena().compareTo(grade) > 0 && "g".equals(condition) ||
                        element.getValue().getOcjena().compareTo(grade) < 0 && "l".equals(condition) ||
                        element.getValue().getOcjena().compareTo(grade) == 0 && "e".equals(condition))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<Student> filterName (String nameStartsWith) {
        return studentsMap.entrySet().stream()
                .filter(element ->
                        element.getValue().getIme().toLowerCase().startsWith(nameStartsWith.toLowerCase()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
