package hr.asseco.onict.actions;

import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.util.concurrent.Callable;

public class CallableCreate implements Callable {

    private Student newStudent;

    public CallableCreate(Student newStudent) {
        this.newStudent = newStudent;
    }

    @Override
    public Void call() {
        DbStudent.getInstance().createStudent(newStudent);
        System.out.println("Student uspješno kreiran.");
        return null;
    }
}
