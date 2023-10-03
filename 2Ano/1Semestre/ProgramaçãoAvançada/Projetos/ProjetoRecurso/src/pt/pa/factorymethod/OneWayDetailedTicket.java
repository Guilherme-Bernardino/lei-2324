package pt.pa.factorymethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Concrete Product of the One-Way Ticket type.
 *
 * Represents the product for a detailed format of this type.
 *
 * @author Guilherme B. 202001870, Inês P. 201701984
 */
public class OneWayDetailedTicket implements Ticket{

    private final String TITLE = "OneWayDetailedTicket";
    private Date departureDate;
    private String origin;
    private String destination;
    private int distance;
    private int duration;
    private int stops;
    private List<String> route;

    public OneWayDetailedTicket(String origin, String destination, List<String> route ,int distance, int duration, int stops) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.stops = stops;
        this.route = route;
        this.departureDate = Calendar.getInstance().getTime();
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    @Override
    public String toStringFormatted() {
        StringBuilder sb = new StringBuilder();
        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(departureDate) + " Origem: " + origin + " Destino: "  +  destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km Duração: " + duration + " Nº paragens: " + stops + "         +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Percurso: " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        return sb.toString();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
