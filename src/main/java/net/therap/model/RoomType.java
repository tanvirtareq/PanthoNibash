package net.therap.model;

/**
 * @author tanvirtareq
 * @since 8/22/23
 */
public enum RoomType {
    Standard("Standard"),
    Business("Business"),
    Suit("Suite"),
    Family("Family");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
