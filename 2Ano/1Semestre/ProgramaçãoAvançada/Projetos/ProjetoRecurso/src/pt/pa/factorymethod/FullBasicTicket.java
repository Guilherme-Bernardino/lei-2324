package pt.pa.factorymethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Concrete Product of the Full Ticket type.
 *
 * Represents the product for a basic format of this type.
 *
 * @author Guilherme B. 202001870, Inês P. 201701984
 */
public class FullBasicTicket implements Ticket{

    private final String TITLE = "FullBasicTicket";
    private Date departureDate;
    private Date returnDate;
    private String origin;
    private String destination;
    private int distance;
    private int duration;
    private List<String> route;

    public FullBasicTicket(String origin, String destination, List<String> route ,int distance, int duration) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.route = route;

        if (!hasWayBack()) {
            return;
        }

        this.departureDate = Calendar.getInstance().getTime();
        this.returnDate = setReturnDate();
    }

    /**
     * Checks if there's a way of returning to starting stop.
     *
     * @return false if it doesn't, true if it does
     */
    private boolean hasWayBack(){
        if (!route.isEmpty() && route.get(0).equals(destination)) {
            System.out.println("Não existe forma de regressar.");
            return false;
        }
        return true;
    }

    /**
     * Method for returning the returning date a day later.
     *
     * @return return date
     */
    private Date setReturnDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.departureDate);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    @Override
    public String toStringFormatted() {
        StringBuilder sb = new StringBuilder();

        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                                +\n");
        sb.append("+ Ida:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(departureDate) + " " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+                                     " +
                "" +
                "" +
                "                                                       +\n");
        sb.append("+ Distância: " + distance + " km                                                           +\n");
        sb.append("+ Duração: " + duration + "                                                                 +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                                +\n");
        sb.append("+ Volta:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(returnDate) + " " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km                                                           +\n");
        sb.append("+ Duração: " + duration + "                                                                 +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        return sb.toString();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

}
