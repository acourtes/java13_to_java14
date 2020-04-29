package fr.arolla.statistics;

import fr.arolla.patient.types.Patient;
import fr.arolla.patient.types.PatientForPsychiatry;
import fr.arolla.patient.types.PatientForReanimation;
import fr.arolla.patient.types.PatientForSurgery;

import java.util.List;

public final class Statistics {

    private Statistics() {
        throw new IllegalArgumentException("Utility class");
    }

    public static StatisticsResult getStatisticForSurgeryPatient(List<Patient> patients) {
        final var numberOfPatientsTreatedInSurgery = (int) patients.stream()
                .filter(p -> p instanceof PatientForSurgery)
                .count();
        final var percentage = getPercentage(patients, numberOfPatientsTreatedInSurgery);
        return new StatisticsResult(numberOfPatientsTreatedInSurgery, percentage);
    }

    public static StatisticsResult getStatisticForReanimationPatient(List<Patient> patients) {
        final var numberOfPatientsTreatedInReanimation = (int) patients.stream()
                .filter(p -> p instanceof PatientForReanimation)
                .count();
        final var percentage = getPercentage(patients, numberOfPatientsTreatedInReanimation);

        return new StatisticsResult(numberOfPatientsTreatedInReanimation, percentage);
    }

    public static StatisticsResult getStatisticForPsychiatryPatient(List<Patient> patients) {
        final var numberOfPatientsTreatedInPsychiatry = (int) patients.stream()
                .filter(p -> p instanceof PatientForPsychiatry)
                .count();
        final var percentage = getPercentage(patients, numberOfPatientsTreatedInPsychiatry);
        return new StatisticsResult(numberOfPatientsTreatedInPsychiatry, percentage);
    }

    private static int getPercentage(List<Patient> patients, int numberOfPatientsTreatedInReanimation) {
        return 100 * numberOfPatientsTreatedInReanimation / patients.size();
    }

    public static FullStatisticsResult getFullStatisticsOnPatientsList(List<Patient> patients) {
        int numberOfPatientsInPsychiatry = 0;
        int totalNumberOfPlannedPsychiatrySeances = 0;
        int numberOfPatientsInSurgery = 0;
        int totalNumberOfReeducationDays = 0;
        int numberOfPatientsInReanimation = 0;
        int patientsWithStillCoronavirus = 0;

        /* FIXME There is a better way to handle instanceOf and cast after */
        for (Patient patient : patients) {
            if (patient instanceof PatientForPsychiatry) {
                numberOfPatientsInPsychiatry++;
                totalNumberOfPlannedPsychiatrySeances += ((PatientForPsychiatry) patient).getLeftNumberOfSeances();
            } else if (patient instanceof PatientForSurgery) {
                numberOfPatientsInSurgery++;
                totalNumberOfReeducationDays += ((PatientForSurgery) patient).getNumberOfDayInReeducation();
            } else if (patient instanceof PatientForReanimation) {
                numberOfPatientsInReanimation++;
                if (((PatientForReanimation) patient).getHasStillCoronavirus()) {
                    patientsWithStillCoronavirus++;
                }
            }
        }

        return new FullStatisticsResult(numberOfPatientsInSurgery, numberOfPatientsInPsychiatry,
                numberOfPatientsInReanimation, totalNumberOfPlannedPsychiatrySeances,
                totalNumberOfReeducationDays, patientsWithStillCoronavirus);
    }
}
