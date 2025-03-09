package utillity;


public class IDGenerator {
    private static long lastId = 0;

    public static Long generateUniqueId() {
        return ++lastId;
    }
}