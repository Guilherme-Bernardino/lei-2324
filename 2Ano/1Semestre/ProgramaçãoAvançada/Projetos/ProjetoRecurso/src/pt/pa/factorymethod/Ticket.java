package pt.pa.factorymethod;

/**
 * Ticket interface used as a product for creating a ticket.
 *
 * @author Guilherme B. 202001870
 */
public interface Ticket {

    /**
     * Returns a formatted ticket according to ticket type.
     *
     * @return formatted ticket
     */
    String toStringFormatted();

    /**
     * Returns the title of a ticket according to ticket type.
     *
     * @return title of ticket
     */
    String getTitle();
}
