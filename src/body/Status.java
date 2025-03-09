package body;

public enum Status {
    FIRED,
    HIRED,
    RECOMMENDED_FOR_PROMOTION,
    REGULAR;

    @Override
    public String toString() {
        return this.name();
    }
}