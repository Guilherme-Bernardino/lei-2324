package pt.pa.model;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;

public class Stop{
    private String stopCode;
    private String stopName;
    private float longitude, latitude;
    private int x,y;

    public Stop(String stopCode, String stopName, float longitude, float latitude, int x, int y) {
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.x = x;
        this.y = y;

    }

    public Stop(String stopCode, String stopName, float longitude, float latitude) {
        this(stopCode,stopName,longitude,latitude,0,0);
    }

    @SmartLabelSource
    public String getLabel(){
        return stopCode;
    }

    public String getStopCode() {
        return stopCode;
    }

    public void setStopCode (String stopCode) {
        this.stopCode = stopCode;
    }


    public String getStopName() {
        return stopName;
    }

    public void setStopName (String stopName) {
        this.stopName = stopName;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude (Float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude (Float latitude) {
        this.latitude = latitude;
    }

    public int getX() {
        return x;
    }

    public void setX (int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }

    public void setY (int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "stopCode='" + stopCode + '\'' +
                ", stopName=" + stopName +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
