package pl.com.pollub.constant;

/**
 * Created by mmaciasz on 2016-06-13.
 */
public enum ChangeType {
    APPLICATION_DATE("Zmieniono termin zgłaszania artykułu z %s na %s"),
    PAYMENT_DATE(""),
    FULLPAPER_DATE(""),
    DATE_TO(""),
    DATE_FROM("");

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
        throw new IllegalArgumentException();
    }

    public String getChangeName() {
        return changeName;
    }
}
