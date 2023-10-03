package pt.pa.controller;

import javafx.scene.image.Image;
import pt.pa.datasets_handler.reader_strategy.ReaderStrategy;
import pt.pa.dijsktra.DijsktraResult;
import pt.pa.factorymethod.TicketFactory;
import pt.pa.model.BusModel;
import pt.pa.model.BusModelException;
import pt.pa.model.Route;
import pt.pa.model.Stop;
import pt.pa.view.BusView;

import java.io.IOException;
import java.util.List;

/**
 * Bus Controller class, belongs to MVC pattern, responsible for managing delegation between buttons and view, and model methods and data.
 *
 * @author Guilherme B. 202001870
 */
public class BusController {

    private BusModel model;

    private BusView view;

    public BusController (BusModel model, BusView view){
        this.model = model;
        this.view = view;
        this.view.setTriggers(this);
        this.model.addObserver(this.view);
    }

    /**
     * Do export graph, params the pathList of images and delegates to model.
     *
     * @param pathList
     * @throws IOException
     */
    public void doExportGraph(List<String> pathList) throws IOException {
        try{
            model.exportGraph(pathList);
            this.view.displayError("Export Successful!");

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        }
    }

    /**
     * Do set reader, sets a new reader with the path of the dataset.
     *
     * @param readerStrategy
     */
    public void doSetReader(ReaderStrategy readerStrategy) {
        try{
            model.setReader(readerStrategy);
        }catch(BusModelException e){
            this.view.displayError(e.getMessage());
        }
    }

    /**
     * Do change Background Image enter with a number for selection of an image on the array of dataset images.
     *
     * @param i
     * @return image for changing
     */
    public Image doChangeBackgroundImage(int i) {
        return model.changeBackgroundImage(i);
    }

    /**
     * Add stop, grabs text field information, delegates to model for vertex insertion.
     */
    public void doAddStop() {
        try{
            String stopName = this.view.getStopName();
            String stopCode = this.view.getStopCode();
            String latitude = this.view.getLatitude();
            String longitude = this.view.getLongitude();
            String x = this.view.getXCoord();
            String y = this.view.getYCoord();

            if(stopName.trim().isEmpty()){
                this.view.displayError("Stop Name is empty!");
                return;
            }
            if(stopCode.trim().isEmpty()){
                this.view.displayError("Stop Code is empty!");
                return;
            }
            if(latitude.trim().isEmpty()){
                this.view.displayError("Stop Latitude is empty!");
                return;
            }
            if(longitude.trim().isEmpty()){
                this.view.displayError("Stop Longitude is empty!");
                return;
            }

            model.addStop(new Stop(stopCode,stopName, Float.parseFloat(latitude), Float.parseFloat(longitude), Integer.parseInt(x), Integer.parseInt(y)));

            view.clearError();
            view.clearControls();

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        } catch (NumberFormatException e){
            this.view.displayError("Expected a number!");
        }
    }

    /**
     * Add route, grabs text field information, delegates to model for edge insertion.
     */
    public void doAddRoute() {
        try{
            String stopCodeStart = this.view.getRouteStartCode();
            String stopCodeEnd = this.view.getRouteEndCode();
            String distance = this.view.getDistance();
            String duration = this.view.getDuration();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Start Code is empty!");
                return;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("End Code is empty!");
                return;
            }
            if(distance.trim().isEmpty()){
                this.view.displayError("Distance is empty!");
                return;
            }
            if(duration.trim().isEmpty()){
                this.view.displayError("Duration is empty!");
                return;
            }

            model.addRoute(stopCodeStart, stopCodeEnd,Integer.parseInt(distance), Integer.parseInt(duration));
            view.clearError();
            view.clearControls();

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        } catch (NumberFormatException e){
            this.view.displayError("Expected a number!");
        }
    }

    /**
     * Remove stop, grabs text field information, delegates to model for vertex deletion.
     */
    public void doRemoveStop() {
        try{
            String stopCode = this.view.getStopCode();

            if(stopCode.trim().isEmpty()){
                this.view.displayError("Stop Code is empty!");
                return;
            }

            model.removeStop(stopCode);
            view.clearError();
            view.clearControls();

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        }
    }

    /**
     * Remove route, grabs text field information, delegates to model for edge deletion.
     */
    public void doRemoveRoute() {
        try{
            String stopCodeStart = this.view.getRouteStartCode();
            String stopCodeEnd = this.view.getRouteEndCode();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Start Code is empty!");
                return;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("End Code is empty!");
                return;
            }

            model.removeRoute(stopCodeStart, stopCodeEnd);
            view.clearError();
            view.clearControls();

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        }
    }

    /**
     * Replace stop, grabs text field information, delegates to model for route element change.
     */
    public void doReplaceRoute(){
        try{
            String stopCodeStart = this.view.getRouteStartCode();
            String stopCodeEnd = this.view.getRouteEndCode();
            String distance = this.view.getDistance();
            String duration = this.view.getDuration();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Start Code is empty!");
                return;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("End Code is empty!");
                return;
            }
            if(distance.trim().isEmpty()){
                this.view.displayError("Distance is empty!");
                return;
            }
            if(duration.trim().isEmpty()){
                this.view.displayError("Duration is empty!");
                return;
            }

            model.replaceRoute(stopCodeStart,stopCodeEnd, Integer.parseInt(distance), Integer.parseInt(duration));
            view.clearError();
            view.clearControls();

        } catch (BusModelException e){
            this.view.displayError(e.getMessage());
        } catch (NumberFormatException e){
            this.view.displayError("Expected a number!");
        }

    }

    /**
     * Calculate the shortest path using Route Distance as a metric, delegate to model with information about two stops.
     *
     * @return result of Dijkstra algorithm
     */
    public DijsktraResult<Stop,Route> doCalculateDistanceShortestPath() {
        try{
            String stopCodeStart = this.view.getStartingCode();
            String stopCodeEnd = this.view.getEndingCode();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Starting Code is empty!");
                return null;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("Ending Code is empty!");
                return null;
            }

            view.clearError();
            view.clearControls();
            return model.getShortestDistancePath(stopCodeStart,stopCodeEnd);


        } catch (BusModelException e) {
            this.view.displayError(e.getMessage());
        }
        return null;
    }

    /**
     * Calculate the shortest path using Route Duration as a metric, delegate to model with information about two stops.
     *
     * @return result of Dijkstra algorithm
     */
    public DijsktraResult<Stop,Route> doCalculateDurationShortestPath() {
        try{
            String stopCodeStart = this.view.getStartingCode();
            String stopCodeEnd = this.view.getEndingCode();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Starting Code is empty!");
                return null;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("Ending Code is empty!");
                return null;
            }

            view.clearError();
            view.clearControls();
            return model.getShortestDurationPath(stopCodeStart,stopCodeEnd);

        } catch (BusModelException e) {
            this.view.displayError(e.getMessage());
        }
        return null;
    }

    /**
     * Generate full ticket, grabs text field information, generates a ticket through model delegation.
     *
     * @param ticketFactory
     * @param type
     */
    public void doGenerateTicket(TicketFactory ticketFactory, String type){
        try{
            String stopCodeStart = this.view.getStartingCode();
            String stopCodeEnd = this.view.getEndingCode();

            if(stopCodeStart.trim().isEmpty()){
                this.view.displayError("Starting Code is empty!");
                return;
            }
            if(stopCodeEnd.trim().isEmpty()){
                this.view.displayError("Ending Code is empty!");
                return;
            }

            model.generateTicket(stopCodeStart,stopCodeEnd, ticketFactory, type);
            view.clearError();
            view.clearControls();

        } catch (BusModelException e) {
            this.view.displayError(e.getMessage());
        }
    }

    /**
     * Do undo, delegation on model for restoring previous state.
     */
    public void doUndo(){
        model.undo();
    }
}
