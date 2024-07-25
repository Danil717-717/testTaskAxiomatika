package ru.stroev.testTaskAxiomatika.models.enums;

/**
 * Family status
 *
 * @author Строев Д.В.
 * @version 1.0
 */
public enum FamilyStatus {

    SINGLE("SINGLE"),
    MARRIED("MARRIED"),
    DIVORCED("DIVORCED");

    private final String familyStatus;

    FamilyStatus(String familyStatusTxt) {
        this.familyStatus = familyStatusTxt;
    }
}
