package fr.arolla.patient;

import java.util.Objects;

public class ReceptionFile {
    public final String firstName;
    public final String lastName;
    public final String socialSecurityNumber;

    public ReceptionFile(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceptionFile that = (ReceptionFile) o;
        return firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                socialSecurityNumber.equals(that.socialSecurityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, socialSecurityNumber);
    }

    @Override
    public String toString() {
        return "SimplePatient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                '}';
    }
}
