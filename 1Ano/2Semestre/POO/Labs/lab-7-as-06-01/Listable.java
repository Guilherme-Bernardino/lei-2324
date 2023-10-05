public interface Listable {

    int STANDARD_HOW_LONG = 60;
    double STANDARD_HOW_MUCH = 20.0;

    /**
     * Return information about an object.
     * @return string
     */
    String getWhat();

    /**
     * Get location.
     * @return string
     */
    String getWhere();

    /**
     * Get time.
     * @return string
     */
    String getWhen();

    /**
     * Get duration in minutes.
     * @return time in minutes
     */
    int getHowLong();

    /**
     * Get price.
     * @return price
     */
    double getHowMuch();

    /**
     * Get if it's free or not.
     * @return true if its free, false otherwise
     */
    boolean isFree();
}
