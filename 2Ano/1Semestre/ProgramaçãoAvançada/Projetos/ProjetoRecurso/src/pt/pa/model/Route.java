package pt.pa.model;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;

/**
 * Object to be stored in edge.
 * Data regarding starting and ending stop code, distance and duration of route.
 *
 * @author Inês P. 201701984
 */
public class Route {
    private int distance;
    private int duration;
    private String stopCodeStart;
    private String stopCodeEnd;

    public Route(String stopCodeStart, String stopCodeEnd, int distance, int duration){
        this.stopCodeStart = stopCodeStart;
        this.stopCodeEnd = stopCodeEnd;
        this.distance = distance;
        this.duration = duration;
    }

    /**
     * Smart Label changed to only show distance and duration on the smart graph.
     * @return smart label
     */
    @SmartLabelSource
    public String getLabel(){
        return distance + "km" + " : " + duration + "min";
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public String getStopCodeStart(){
        return stopCodeStart;
    }

    public String getStopCodeEnd(){
        return stopCodeEnd;
    }

    @Override
    public String toString() {
        return "Route{" +
                "stopCodeStart='" + stopCodeStart + '\'' +
                ", stopCodeEnd=" + stopCodeEnd +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
