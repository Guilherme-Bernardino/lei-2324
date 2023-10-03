package pt.pa.factorymethod;

import java.util.List;

/**
 * Concrete factory of One Way Ticket type.
 *
 * This factory produces products of the One Way Ticket type.
 *
 * @author Guilherme B. 202001870, Inês P. 201701984
 */
public class OneWayTicketFactory implements TicketFactory{

    @Override
    public Ticket create(String type, String startCode, String stopCode, List<String> route, int... ints) {
        switch(type.toLowerCase()) {
            case "basic": return new OneWayBasicTicket(startCode, stopCode, route, ints[0], ints[1]);

            case "detailed": return new OneWayDetailedTicket(startCode, stopCode, route, ints[0], ints[1], ints[2]);

            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }
}
