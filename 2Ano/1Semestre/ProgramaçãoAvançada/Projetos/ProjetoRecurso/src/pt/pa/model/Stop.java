package pt.pa.model;

import com.brunomnsilva.smartgraph.graphview.SmartLabelSource;

/**
 * Object to be stored in vertex.
 * Data regarding stop code, stop name, longitude, latitude, x and y coordinates of a stop.
 *
 * @author Inês P. 201701984
 */
public class Stop{
    private String stopCode;
    private String stopName;
    private float lon, lat;
    private int x,y;

    public Stop(String stopCode, String stopName, float lon, float lat, int x, int y) {
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.lon = lon;
        this.lat = lat;
        this.x = x;
        this.y = y;

    }

    public Stop(String stopCode, String stopName, float longitude, float latitude) {
        this(stopCode,stopName,longitude,latitude,0,0);
    }

    /**
     * Smart label to only show stop code.
     * @return smart label
     */
    @SmartLabelSource
    public String getLabel(){
        return stopCode;
    }

    public String getStopCode() {
        return stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public float getLongitude() {
        return lon;
    }

    public float getLatitude() {
        return lat;
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
                ", longitude=" + lon +
                ", latitude=" + lat +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
