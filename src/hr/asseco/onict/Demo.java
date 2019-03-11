package hr.asseco.onict;

import hr.asseco.onict.actions.CallableCreate;
import hr.asseco.onict.actions.CallableFilterGrade;
import hr.asseco.onict.actions.CallableFilterName;
import hr.asseco.onict.actions.CallableRead;
import hr.asseco.onict.db.DbStudent;
import hr.asseco.onict.model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Demo {

    public static void main(String[] args) throws IOException {
        String csvPath = args[0];
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        if (args.length != 1) {
            System.out.println("Pogrešan broj parametara programa.");
            return;
        }

        System.out.println("Dobrodošao dragi korisniče.");

        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        String line =  null;
        ConcurrentHashMap<String,Student> initialMap = new ConcurrentHashMap<String, Student>();

        while((line=br.readLine())!=null){
            String str[] = line.split(";");
            for(int i=1;i<str.length;i++){
                String jmbag = str[0];
                String ime = str[1];
                String prezime = str[2];
                Short ocjena = Short.valueOf(str[3]);
                initialMap.put(jmbag, new Student(jmbag, ime, prezime, ocjena));
            }
        }

        DbStudent dbStudent = DbStudent.getInstance();
        dbStudent.init(initialMap);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();

            if (!input.isEmpty()) {
                String[] inputArray = input.split(" ");
                if (inputArray[0].toLowerCase().equals("exit")) {
                    executorService.shutdown();
                    return;
                } else if (inputArray[0].toLowerCase().equals("create")) {
                    if (inputArray.length != 5) {
                        System.out.println("Greška. Nedostaje relacija.");
                    } else {
                        Student newStudent = new Student(inputArray[1], inputArray[2], inputArray[3], Short.valueOf(inputArray[4]));
                        FutureTask create = new FutureTask(new CallableCreate(newStudent));
                        executorService.execute(create);
                    }
                } else if (inputArray[0].toLowerCase().equals("read")) {
                    if (inputArray.length != 2) {
                        System.out.println("Greška. Nedostaje relacija.");
                    } else {
                        String jmbag = inputArray[1];
                        FutureTask read = new FutureTask(new CallableRead(jmbag));
                        executorService.execute(read);
                    }
                } else if (inputArray[0].toLowerCase().equals("filter-grade")) {
                    if (inputArray.length != 3) {
                        System.out.println("Greška. Nedostaje relacija.");
                    } else {
                        Short grade = Short.valueOf(inputArray[1]);
                        String condition = inputArray[2];
                        FutureTask filterGrade = new FutureTask(new CallableFilterGrade(grade, condition));
                        executorService.execute(filterGrade);
                    }
                } else if (inputArray[0].toLowerCase().equals("filter-name")) {
                    if (inputArray.length != 3 && inputArray.length != 2) {
                        System.out.println("Greška. Nedostaje relacija.");
                    } else {
                        String nameStartsWith = inputArray[1];
                        String caseUorL = inputArray.length == 3 ? inputArray[2] : null;
                        FutureTask filterName = new FutureTask(new CallableFilterName(nameStartsWith, caseUorL));
                        executorService.execute(filterName);
                    }
                }
            }
        }
    }
}
