package hr.asseco.onict.model;

import java.util.Objects;

public class Student {

    private String jmbag;
    private String ime;
    private String prezime;
    private Short ocjena;

    public Student (String jmbag, String ime, String prezime, Short ocjena) {
        this.jmbag = jmbag;
        this.ime = ime;
        this.prezime = prezime;
        this.ocjena = ocjena;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Short getOcjena() {
        return ocjena;
    }

    public void setOcjena(Short ocjena) {
        this.ocjena = ocjena;
    }

    @Override
    public String toString() {
        return "{" +
                "jmbag: " + jmbag +
                ", ime: " + ime +
                ", prezime: " + prezime +
                ", ocjena: " + ocjena +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return jmbag.equals(student.jmbag) &&
                ime.equals(student.ime) &&
                prezime.equals(student.prezime) &&
                ocjena.equals(student.ocjena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag, ime, prezime, ocjena);
    }
}
