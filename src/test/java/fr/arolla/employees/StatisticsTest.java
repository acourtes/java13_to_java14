package fr.arolla.employees;

import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;
import fr.arolla.patient.types.PatientForReanimation;
import fr.arolla.patient.types.PatientForSurgery;
import fr.arolla.statistics.FullStatisticsResult;
import fr.arolla.statistics.Statistics;
import fr.arolla.statistics.StatisticsResult;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(RandomBeansExtension.class)
public class StatisticsTest {

    private static final List<Patient> patients = new ArrayList<>();
    private static int initialNumberOfPsychiatryPatients;
    private static int initialNumberOfSurgeryPatients;
    private static int initialNumberOfReanimationPatients;

    @BeforeAll
    static void setUp(@Random(type = PatientForSurgery.class, size = 5) List<PatientForSurgery> patientForSurgeryList,
                      @Random(type = PatientForReanimation.class, size = 6) List<PatientForReanimation> patientForReanimationList,
                      @Random(type = PatientForPsychiatry.class, size = 4) List<PatientForPsychiatry> patientForPsychiatryList) {
        patients.addAll(patientForPsychiatryList);
        patients.addAll(patientForReanimationList);
        patients.addAll(patientForSurgeryList);
        initialNumberOfPsychiatryPatients = patientForPsychiatryList.size();
        initialNumberOfSurgeryPatients = patientForSurgeryList.size();
        initialNumberOfReanimationPatients = patientForReanimationList.size();
    }

    @Test
    void should_give_statistics_for_surgery_patients_with_5_patients_on_11() {
        final var expectedNumberOfSurgeryPatients = StatisticsTest.initialNumberOfSurgeryPatients;

        final StatisticsResult result = Statistics.getStatisticForSurgeryPatient(patients);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfSurgeryPatients);
        assertThat(result.percentage).isEqualTo(100 * expectedNumberOfSurgeryPatients / patients.size());
    }

    @Test
    void should_give_statistics_for_surgery_patients_with_6_patients_on_11() {
        final var patientsCopy = getPatientsCopy();
        patientsCopy.add(new PatientForSurgery());
        final var expectedNumberOfSurgeryPatients = initialNumberOfSurgeryPatients + 1;

        final StatisticsResult result = Statistics.getStatisticForSurgeryPatient(patientsCopy);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfSurgeryPatients);
        assertThat(result.percentage).isEqualTo(expectedNumberOfSurgeryPatients * 100 / patientsCopy.size());
    }

    @Test
    void should_give_statistics_for_reanimation_patients_with_6_patients_on_15() {
        final var expectedNumberOfReanimationPatients = initialNumberOfReanimationPatients;

        final StatisticsResult result = Statistics.getStatisticForReanimationPatient(patients);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfReanimationPatients);
        assertThat(result.percentage).isEqualTo(100 * expectedNumberOfReanimationPatients / patients.size());
    }

    @Test
    void should_give_statistics_for_reanimation_patients_with_7_patients_on_16() {
        final var patientsCopy = getPatientsCopy();
        patientsCopy.add(new PatientForReanimation());
        final var expectedNumberOfReanimationPatients = initialNumberOfReanimationPatients + 1;

        final StatisticsResult result = Statistics.getStatisticForReanimationPatient(patientsCopy);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfReanimationPatients);
        assertThat(result.percentage).isEqualTo(100 * expectedNumberOfReanimationPatients / patientsCopy.size());
    }

    @Test
    void should_give_statistics_for_psychiatry_patients_with_4_patients_on_15() {
        final var expectedNumberOfPsychiatryPatients = initialNumberOfPsychiatryPatients;
        final StatisticsResult result = Statistics.getStatisticForPsychiatryPatient(patients);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfPsychiatryPatients);
        assertThat(result.percentage).isEqualTo(100 * expectedNumberOfPsychiatryPatients / patients.size());
    }

    @Test
    void should_give_statistics_for_psychiatry_patients_with_5_patients_on_16() {
        final var patientsCopy = getPatientsCopy();
        patientsCopy.add(new PatientForPsychiatry());
        final var expectedNumberOfPsychiatryPatients = initialNumberOfPsychiatryPatients + 1;

        final StatisticsResult result = Statistics.getStatisticForPsychiatryPatient(patientsCopy);

        assertThat(result.numberOfPatients).isEqualTo(expectedNumberOfPsychiatryPatients);
        assertThat(result.percentage).isEqualTo(100 * expectedNumberOfPsychiatryPatients / patientsCopy.size());
    }

    @Test
    void should_give_global_statistics_result_on_patients() {
        final FullStatisticsResult result = Statistics.getFullStatisticsOnPatientsList(patients);

        assertThat(result.numberOfPsychiatryPatients).isEqualTo(initialNumberOfPsychiatryPatients);
        assertThat(result.numberOfReanimationPatients).isEqualTo(initialNumberOfReanimationPatients);
        assertThat(result.numberOfSurgeryPatients).isEqualTo(initialNumberOfSurgeryPatients);

        final var numberOfPsychiatrySeances = patients.stream()
                .filter(p -> p instanceof PatientForPsychiatry)
                .map(p -> (PatientForPsychiatry) p)
                .map(PatientForPsychiatry::getLeftNumberOfSeances)
                .reduce(0, Integer::sum);
        assertThat(result.totalNumberOfPlannedPsychiatrySeances).isEqualTo(numberOfPsychiatrySeances);

        final var numberOfReeducationDays = patients.stream()
                .filter(p -> p instanceof PatientForSurgery)
                .map(p -> (PatientForSurgery) p)
                .map(PatientForSurgery::getNumberOfDayInReeducation)
                .reduce(0, Integer::sum);
        assertThat(result.totalNumberOfReeducationDays).isEqualTo(numberOfReeducationDays);

        final var numberOfPatientsWithCoronavirus = (int) patients.stream()
                .filter(p -> p instanceof PatientForReanimation)
                .map(p -> (PatientForReanimation) p)
                .map(PatientForReanimation::getHasStillCoronavirus)
                .filter(coronavirus -> coronavirus)
                .count();
        assertThat(result.patientsWithStillCoronavirus).isEqualTo(numberOfPatientsWithCoronavirus);
    }

    private ArrayList<Patient> getPatientsCopy() {
        return new ArrayList<>(StatisticsTest.patients);
    }
}
