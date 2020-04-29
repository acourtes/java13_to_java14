package fr.arolla.patient.types;


public class PatientForSurgery extends Patient {
    private int numberOfDayInReeducation;

    public int getNumberOfDayInReeducation() {
        return numberOfDayInReeducation;
    }

    public void setNumberOfDayInReeducation(int numberOfDayInReeducation) {
        this.numberOfDayInReeducation = numberOfDayInReeducation;
    }
}
