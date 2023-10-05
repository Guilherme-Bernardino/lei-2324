public abstract class Tour implements Listable{

    protected final String ID;

    protected Tour(String id) {
        ID = id;
    }

    /**
     * Returns characteristics of a tour.
     * @return string
     */
    protected abstract String getTag();


    public int getHowLong(){
        return STANDARD_HOW_LONG;
    }

    public double getHowMuch(){
        return STANDARD_HOW_MUCH;
    }

    public boolean isFree(){
        return false;
    }
}
