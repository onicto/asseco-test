package hr.asseco.onict.actions;

import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableFilterGrade implements Callable {

    private Short grade;
    private String condition;

    public CallableFilterGrade(Short grade, String condition) {
        this.grade = grade;
        this.condition = condition;
    }

    @Override
    public Void call() {
        List<Student> studentList = DbStudent.getInstance().filterGrade(grade, condition);
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
        return null;
    }
}
