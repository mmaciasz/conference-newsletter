package pl.com.pollub.constant;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public enum ChangeType {
    APPLICATION_DATE("Zmieniono termin zgłaszania artykułu z %s na %s"),
    PAYMENT_DATE("Zmieniono termin opłaty z %s na %s"),
    FULLPAPER_DATE("Zmieniono termin wysyłania pełnych tekstów artykułów z %s na %s"),
    DATE_TO("Zmieniono datę rozpoczęcia konferencji z %s na %s"),
    DATE_FROM("Zmieniono datę zakończenia konferencji z %s na %s");

    private String changeName;

    ChangeType(String changeName) {
        this.changeName = changeName;
    }

    public static ChangeType getByName(String name) {
        for (ChangeType changeType : ChangeType.values()) {
            if (changeType.name().equals(name)) {
                return changeType;
            }
        }
        throw new IllegalArgumentException("There is no enum for name - " + name);
    }

    public String getChangeName() {
        return changeName;
    }
}
