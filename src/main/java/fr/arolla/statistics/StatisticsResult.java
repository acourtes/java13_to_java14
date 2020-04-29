package fr.arolla.statistics;

/**
 * FIXME Wouldn't be a value object ?
 */
public final class StatisticsResult {
    public final int numberOfPatients;
    public final int percentage;

    public StatisticsResult(int numberOfPatients, int percentage) {
        this.numberOfPatients = numberOfPatients;
        this.percentage = percentage;
    }
}
