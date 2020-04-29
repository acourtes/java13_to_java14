package fr.arolla.statistics;

/**
 * FIXME Wouldn't be a value object ?
 */
public final class FullStatisticsResult {
    public final int numberOfSurgeryPatients;
    public final int numberOfPsychiatryPatients;
    public final int numberOfReanimationPatients;

    public final int totalNumberOfPlannedPsychiatrySeances;
    public final int totalNumberOfReeducationDays;
    public final int patientsWithStillCoronavirus;

    public FullStatisticsResult(int numberOfSurgeryPatients,
                                int numberOfPsychiatryPatients,
                                int numberOfReanimationPatients,
                                int totalNumberOfPlannedPsychiatrySeances,
                                int totalNumberOfReeducationDays,
                                int patientsWithStillCoronavirus) {
        this.numberOfSurgeryPatients = numberOfSurgeryPatients;
        this.numberOfPsychiatryPatients = numberOfPsychiatryPatients;
        this.numberOfReanimationPatients = numberOfReanimationPatients;
        this.totalNumberOfPlannedPsychiatrySeances = totalNumberOfPlannedPsychiatrySeances;
        this.totalNumberOfReeducationDays = totalNumberOfReeducationDays;
        this.patientsWithStillCoronavirus = patientsWithStillCoronavirus;
    }
}
