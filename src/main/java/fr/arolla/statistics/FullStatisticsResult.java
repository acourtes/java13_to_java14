package fr.arolla.statistics;

public record FullStatisticsResult(int numberOfSurgeryPatients,
                                   int numberOfPsychiatryPatients,
                                   int numberOfReanimationPatients,
                                   int totalNumberOfPlannedPsychiatrySeances,
                                   int totalNumberOfReeducationDays,
                                   int patientsWithStillCoronavirus) {

}
