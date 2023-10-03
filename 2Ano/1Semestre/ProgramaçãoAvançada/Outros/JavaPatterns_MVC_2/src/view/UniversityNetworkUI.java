package view;

import controller.UniversityNetworkController;
import model.Person;
import model.UniversityNetwork;
import observerpattern.Observer;

/**
 * Interface that a specific user interface must implement.
 *
 *  @author brunomnsilva
 */
public interface UniversityNetworkUI extends Observer {

    String getNamePersonAdd();
    String getIdPersonAdd();
    String getRolePersonAdd();

    String getRelationshipFirstPersonId();
    String getRelationshipSecondPersonId();
    String getRelationShipDescriptionAdd();

    void displayError(String msg);
    void clearError();
    void clearControls();

    void setTriggers(UniversityNetworkController controller);
}
