package fr.arolla;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    public final String firstName;
    public final String lastName;
    public final LocalDate birthDate;
    public final String socialSecurityNumber;
    public final String street;
    public final String postalCode;
    public final String city;
    public final String phoneNumber;

    public Person(String firstName, String lastName, LocalDate birthDate,
                  String socialSecurityNumber, String street,
                  String postalCode, String city, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.socialSecurityNumber = socialSecurityNumber;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                birthDate.equals(person.birthDate) &&
                socialSecurityNumber.equals(person.socialSecurityNumber) &&
                street.equals(person.street) &&
                postalCode.equals(person.postalCode) &&
                city.equals(person.city) &&
                phoneNumber.equals(person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, socialSecurityNumber, street, postalCode, city, phoneNumber);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
