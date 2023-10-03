package pt.pa.view;

import pt.pa.controller.BusController;
import pt.pa.observerpattern.Observer;

/**
 * BusNetworkUI, interface for extension of Observer
 *
 * @author Guilherme Bernardino, 202001870
 */
public interface BusNetworkUI extends Observer {

    /**
     * Sets a message error on the error label depending on what error message it gets.
     *
     * @param msg
     */
    void displayError(String msg);

    /**
     * Clears error label.
     */
    void clearError();

    /**
     * Clears text fields.
     */
    void clearControls();

    /**
     * Sets triggers for all buttons included on the user interface and action events for each button associated to the controller.
     * @param controller
     */
    void setTriggers(BusController controller);
}
