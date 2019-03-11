package hr.asseco.onict.actions;

import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableFilterName implements Callable {

    private String startsWith;
    private String caseUorL;

    public CallableFilterName(String startsWith, String caseUorL) {
        this.startsWith = startsWith;
        this.caseUorL = caseUorL;
    }

    @Override
    public Void call() {
        List<Student> studentList = DbStudent.getInstance().filterName(startsWith);
        for (Student s : studentList) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getIme());
            sb.append(" ");
            sb.append(s.getPrezime());
            if ("-u".equals(caseUorL)) {
                System.out.println(sb.toString().toUpperCase());
            } else if ("-l".equals(caseUorL)) {
                System.out.println(sb.toString().toLowerCase());
            } else {
                System.out.println(sb.toString());
            }
        }
        return null;
    }
}
