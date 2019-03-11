package hr.asseco.onict.db;

import hr.asseco.onict.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    }

    public List<Student> filterGrade (Short grade, String condition) {
        List<Student> result = new ArrayList<>();

        for (String jmbag : studentsMap.keySet()) {
            Student check = studentsMap.get(jmbag);
            if (check.getOcjena().compareTo(grade) > 0 && "g".equals(condition)
                    || check.getOcjena().compareTo(grade) < 0 && "l".equals(condition)
                    || check.getOcjena().compareTo(grade) == 0 && "e".equals(condition)) {
                result.add(check);
            }
        }

        return result;
    }

    public List<Student> filterName (String nameStartsWith) {
        List<Student> result = new ArrayList<>();

        for (String jmbag : studentsMap.keySet()) {
            Student check = studentsMap.get(jmbag);
            if (check.getIme().toLowerCase().startsWith(nameStartsWith.toLowerCase())) {
                result.add(check);
            }
        }

        return result;
    }

}
