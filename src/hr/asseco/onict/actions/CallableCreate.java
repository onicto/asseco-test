package hr.asseco.onict.actions;

import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.util.concurrent.Callable;

public class CallableCreate implements Callable {

    private Student newStudet;

    public CallableCreate(Student newStudet) {
        this.newStudet = newStudet;
    }

    @Override
    public Void call() {
        DbStudent.getInstance().createStudent(newStudet);
        System.out.println("Student uspješno kreiran.");
        return null;
    }
}
