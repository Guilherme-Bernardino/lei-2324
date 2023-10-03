package pt.pa.model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Ticket {
    private Date departureDate;
    private Date returnDate;
    private String origin;
    private String destination;
    private int distance;
    private int duration;
    private int stops;
    private List<String> route;

    public Ticket(String origin, String destination, int distance, int duration, int stops, List<String> route) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.stops = stops;
        this.route = route;

        // Verifica se existe forma de regressar
        if (!route.isEmpty() && route.get(0).equals(destination)) {
            System.out.println("Não existe forma de regressar.");
            return;
        }

        // Define a data de ida como a data atual do computador
        this.departureDate = Calendar.getInstance().getTime();

        // Define a data de volta como o dia seguinte à data de ida
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.departureDate);
        calendar.add(Calendar.DATE, 1);
        this.returnDate = calendar.getTime();
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public String getBasicFormatIda() {
        StringBuilder sb = new StringBuilder();
        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ " +
                "Data: " + new SimpleDateFormat("yyyy-MM-dd").format(departureDate) + " " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination
                + "                                   +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km                                                                          +\n");
        sb.append("+ Duração: " + duration + "                                                                               +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        return sb.toString();
    }

    public String getDetailedFormatIda() {
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

    public String getBasicFormatIda_Volta() {
        StringBuilder sb = new StringBuilder();

        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                                +\n");
        sb.append("Ida:\n");
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
        sb.append("Volta:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(returnDate) + " " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km                                                           +\n");
        sb.append("+ Duração: " + duration + "                                                                 +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        return sb.toString();
    }

    public String getDetailedFormatIda_Volta() {
        StringBuilder sb = new StringBuilder();

        sb.append("+--------------------------------------------------------------------------------------------+\n");
        sb.append("+                                                                                            +\n");
        sb.append("Ida:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(departureDate) + " Origem: " + origin + " Destino: " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km Duração: " + duration + " Nº paragens: " + stops + "         +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Percurso: " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");
        sb.append("+--------------------------------------------------------------------------------------------+");
        sb.append("+                                                                                            +\n");
        sb.append("Volta:\n");
        sb.append("+ Data: " + new SimpleDateFormat("yyyy-MM-dd").format(returnDate) + " Origem: " + origin + " Destino: " + destination + " +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Distância: " + distance + " km Duração: " + duration + " Nº paragens: " + stops + "         +\n");
        sb.append("+                                                                                            +\n");
        sb.append("+ Percurso: " + origin + " -> " + String.join(" -> ", route) + " " + " -> " + destination + " +\n");


        return sb.toString();
    }
}

