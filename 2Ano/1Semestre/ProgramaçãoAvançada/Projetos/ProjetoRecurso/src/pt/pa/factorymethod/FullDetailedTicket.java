package pt.pa.factorymethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Concrete Product of the Full Ticket type.
 *
 * Represents the product for a detailed format of this type.
 *
 * @author Guilherme B. 202001870, Inês P. 201701984
 */
public class FullDetailedTicket implements Ticket{

    private final String TITLE = "FullDetailedTicket";
    private Date departureDate;
    private Date returnDate;
    private String origin;
    private String destination;
    private int distance;
    private int duration;
    private int stops;
    private List<String> route;

    public FullDetailedTicket(String origin, String destination, List<String> route ,int distance, int duration, int stops) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.stops = stops;
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
        sb.append("+                                                                                            +\n");
        sb.append("+ Ida:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(departureDate) + " Origem: " + origin + " Destino: " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km Duração: " + duration + " Nº paragens: " + stops + "         +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Percurso: " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        sb.append("+                                                                                            +\n");
        sb.append("+ Volta:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(returnDate) + " Origem: " + origin + " Destino: " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km Duração: " + duration + " Nº paragens: " + stops + "         +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Percurso: " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");

        return sb.toString();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
