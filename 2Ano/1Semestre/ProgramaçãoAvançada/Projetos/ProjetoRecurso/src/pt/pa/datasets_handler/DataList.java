package pt.pa.datasets_handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds data regarding information contained in dataset files.
 * Maintains two collections, one for stops, another for routes, and two object classes:
 * StopData - for string data regarding stop information
 * RouteData - for string data regarding route information
 *
 * @author Guilherme B. 202001870
 */
public class DataList {
    private List<StopData> stops;

    private List<RouteData> routes;

    public DataList(){
        this.stops = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    public List<StopData> getStops() {
        return stops;
    }

    public void setStops(List<StopData> stops) {
        this.stops = stops;
    }

    public List<RouteData> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteData> routes) {
        this.routes = routes;
    }

    /**
     * Creates a new Stop Data object.
     * Used in Writer class.
     * @param stopCode
     * @param stopName
     * @param lat
     * @param lon
     * @param x
     * @param y
     * @return new StopData with information passed through params
     */
    public StopData createStopData(String stopCode, String stopName, String lat, String lon,String x, String y){
        return new StopData( stopCode,  stopName,  lat,  lon, x,  y);
    }

    /**
     * Creates and returns a new Route Data object.
     * Used in Writer class.
     * @param stopCodeStart
     * @param stopCodeEnd
     * @param distance
     * @param duration
     * @return new RouteData with information passed through params
     */
    public RouteData createRouteData(String stopCodeStart, String stopCodeEnd, String distance, String duration){
        return new RouteData( stopCodeStart,  stopCodeEnd,  distance,  duration);
    }

    /**
     * StopData class.
     * Data regarding stopCode, stopName, latitude, longitude, x and y coordinates of a stop.
     */
    public class StopData {
        private String stopCode;
        private String stopName;
        private String lat, lon;
        private String x,y;

        public StopData(String stopCode, String stopName, String lat, String lon,String x, String y) {
            this.stopCode = stopCode;
            this.stopName = stopName;
            this.lat = lat;
            this.lon = lon;
            this.x = x;
            this.y = y;
        }

        public String getStopCode() {
            return stopCode;
        }

        public String getStopName() {
            return stopName;
        }

        public String getLongitude() {
            return lon;
        }

        public String getLatitude() {
            return lat;
        }

        public String getX() {
            return x;
        }

        public void setX (String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY (String y) {
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

    /**
     * RouteData class.
     * Data regarding start and end stop codes, distance and duration of a route.
     */
    public class RouteData{
        private String stopCodeStart;
        private String stopCodeEnd;
        private String distance;
        private String duration;

        public RouteData(String stopCodeStart, String stopCodeEnd, String distance, String duration){
            this.stopCodeStart = stopCodeStart;
            this.stopCodeEnd = stopCodeEnd;
            this.distance = distance;
            this.duration = duration;
        }

        public String getDistance() {
        return distance;
        }

        public void setDistance(String distance){
            this.distance = distance;
        }

        public String getDuration(){
            return duration;
        }

        public void setDuration(String duration){
            this.duration = duration;
        }

        public String getStopCodeStart(){
            return stopCodeStart;
        }

        public String getStopCodeEnd(){
            return stopCodeEnd;
        }
        
    }
}
