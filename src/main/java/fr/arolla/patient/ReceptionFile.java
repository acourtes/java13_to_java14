package fr.arolla.patient;

public record ReceptionFile(String firstName, String lastName, String socialSecurityNumber) {
    @Override
    public String toString() {
        return "SimplePatient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                '}';
    }
}
