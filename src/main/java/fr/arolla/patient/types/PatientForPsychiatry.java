package fr.arolla.patient.types;

public class PatientForPsychiatry extends Patient {
    private int leftNumberOfSeances;

    public int getLeftNumberOfSeances() {
        return leftNumberOfSeances;
    }

    public void setLeftNumberOfSeances(int leftNumberOfSeances) {
        this.leftNumberOfSeances = leftNumberOfSeances;
    }
}
