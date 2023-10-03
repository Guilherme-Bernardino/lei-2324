package pt.pa.model;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;

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

    public void setStopCodeStart(String stopCodeStart){
        this.stopCodeStart = stopCodeStart;
    }

    public String getStopCodeEnd(){
        return stopCodeEnd;
    }

    public void setStopCodeEnd(String stopCodeEnd){
        this.stopCodeEnd= stopCodeEnd;
    }

}
