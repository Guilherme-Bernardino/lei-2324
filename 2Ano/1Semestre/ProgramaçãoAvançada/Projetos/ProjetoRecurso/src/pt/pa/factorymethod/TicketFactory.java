package pt.pa.factorymethod;

import java.util.List;

/**
 * Ticket factory class, implements the factory method pattern.
 * Declares a method for creating a new ticket, according user request.
 *
 * The concrete products are declared through the concrete factories that implement this interface.
 *
 * @author Guilherme B. 202001870
 */
public interface TicketFactory{

    /**
     * Method for creating a ticket.
     *
     * For the types:
     * <li> Basic.
     *      <b>Type:</b> basic
     *      <b>StartCode:</b> startCode
     *      <b>StopCode:</b> stopCode
     *      <b>Routes:</b> route
     *      <b>Ints:</b> distance, duration
     * </li>
     * <li> Detailed.
     *      <b>Type:</b> detailed
     *      <b>StartCode:</b> startCode
     *      <b>StopCode:</b> stopCode
     *      <b>Routes:</b> route
     *      <b>Ints:</b> distance, duration, number of stops
     * </li>
     *
     * @param type type of ticket
     * @param startCode
     * @param stopCode
     * @param route
     * @param ints list of integers
     * @return a ticket instance
     */
    Ticket create(String type, String startCode, String stopCode, List<String> route, int... ints);
}
