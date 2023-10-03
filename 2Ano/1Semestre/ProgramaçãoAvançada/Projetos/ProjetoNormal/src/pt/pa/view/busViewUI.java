package pt.pa.view;

import pt.pa.controller.GraphController;
import pt.pa.observerpattern.Observer;

public interface busViewUI extends Observer {
    String getNameStopAdd();

    String getPopStopAdd();

    String getXStopAdd();

    String getYStopAdd();

    String getRouteSourceStop();

    String getRouteDestStop();

    String getDistanceAdd();

    String getPathSelection();

    String getRouteSelection();

    Double getGraphWidth();

    Double getGraphHeight();

    void displayError(String var1);

    void clearError();

    void clearControls();

    void setTriggers(GraphController var1);


}
