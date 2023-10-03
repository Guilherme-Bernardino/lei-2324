package pt.pa.factorymethod;

import java.util.List;

/**
 * Concrete factory of Full Ticket type.
 *
 * This factory creates products of the Full Ticket type.
 *
 * @author Guilherme B. 202001870
 */
public class FullTicketFactory implements TicketFactory{

    @Override
    public Ticket create(String type, String startCode, String stopCode, List<String> route, int... ints) {
        switch(type.toLowerCase()) {
            case "basic": return new FullBasicTicket(startCode, stopCode, route, ints[0], ints[1]);

            case "detailed": return new FullDetailedTicket(startCode, stopCode, route, ints[0], ints[1], ints[2]);

            default:
                throw new UnsupportedOperationException("Type not supported: " + type);
        }
    }
}
