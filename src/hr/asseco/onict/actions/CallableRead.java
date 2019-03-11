package hr.asseco.onict.actions;

import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.util.concurrent.Callable;

public class CallableRead implements Callable {

    private String jmbag;

    public CallableRead(String jmbag) {
        this.jmbag = jmbag;
    }

    @Override
    public Void call() {
        Student student = DbStudent.getInstance().readStudent(jmbag);
        if (student == null) {
            System.out.println("Student s JMBAG-om "+jmbag+" nije pronađen");
        }
        System.out.println(student.toString());
        return null;
    }
}
