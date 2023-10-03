package pt.pa.datasets_handler;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import pt.pa.graph.*;
import pt.pa.model.Stop;
import pt.pa.model.Route;

import javax.imageio.ImageIO;

public class Writer {

    private Graph<Stop,Route> exportGraph;
    private File dir;
    private List<String> originalImagePath;
    public Writer (Graph<Stop,Route> exportGraph, List<String> originalImagePath){
        this.exportGraph = exportGraph;
        this.originalImagePath = originalImagePath;
    }

    public void createFile() throws IOException {

        dir = new File("./datasets/exported/img");
        dir.mkdirs();


        for (String url : originalImagePath) {
            try {
                // retrieve image
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                BufferedImage newImg = ImageIO.read(new File(url));
                File outputfile = new File("./datasets/exported/img/" + fileName);
                ImageIO.write(newImg, "png", outputfile);
            } catch (IOException e) {
                throw new IOException("No image found!");
            }
        }

        writeStops();
        writeRoutesDistance();
        writeRoutesDuration();
        writeXY();
    }

    public File writeStops() throws IOException {
        File fileStops = new File("./datasets/exported/", "stops.txt");
        FileWriter newStops = new FileWriter(fileStops);
        BufferedWriter writer = new BufferedWriter(newStops);

        newStops.write("#");
        newStops.write(System.lineSeparator());
        newStops.write("# data file: exported-stops\t");
        newStops.write(System.lineSeparator());
        newStops.write("#");
        newStops.write(System.lineSeparator());
        newStops.write("stop_code\tstop_name\tlat\tlon");
        newStops.write(System.lineSeparator());

        for (Vertex<Stop> stopVertex : exportGraph.vertices()) {
            newStops.write(stopVertex.element().getStopCode() + "\t" +
                           stopVertex.element().getStopName() + "\t" +
                           stopVertex.element().getLatitude() + "\t" +
                           stopVertex.element().getLongitude());
            newStops.write(System.lineSeparator());
        }

        newStops.flush();
        newStops.close();

        return fileStops;
    }

    public File writeRoutesDistance() throws IOException {
        File fileRoutesDistance = new File("./datasets/exported/", "routes-distance.txt");
        FileWriter newRoutesDistance = new FileWriter(fileRoutesDistance);

        newRoutesDistance.write("#");
        newRoutesDistance.write(System.lineSeparator());
        newRoutesDistance.write("# data file: exported-routes\t");
        newRoutesDistance.write(System.lineSeparator());
        newRoutesDistance.write("#");
        newRoutesDistance.write(System.lineSeparator());
        newRoutesDistance.write("stop_code_start\tstop_code_end\tdistance");
        newRoutesDistance.write(System.lineSeparator());

        for (Edge<Route,Stop> routeEdge : exportGraph.getEdgesInList()) {
            newRoutesDistance.write(routeEdge.element().getStopCodeStart() + "\t" +
                    routeEdge.element().getStopCodeEnd()  + "\t" +
                    routeEdge.element().getDistance());
            newRoutesDistance.write(System.lineSeparator());
        }

        newRoutesDistance.flush();
        newRoutesDistance.close();

        return fileRoutesDistance;
    }

    public File writeRoutesDuration() throws IOException {
        File fileRoutesDuration = new File("./datasets/exported/", "routes-duration.txt");
        FileWriter newRoutesDuration = new FileWriter(fileRoutesDuration);

        newRoutesDuration.write("#");
        newRoutesDuration.write(System.lineSeparator());
        newRoutesDuration.write("# data file: exported-routes\t");
        newRoutesDuration.write(System.lineSeparator());
        newRoutesDuration.write("#");
        newRoutesDuration.write(System.lineSeparator());
        newRoutesDuration.write("stop_code_start\tstop_code_end\tduration");
        newRoutesDuration.write(System.lineSeparator());

        for (Edge<Route,Stop> routeEdge : exportGraph.getEdgesInList()) {
            newRoutesDuration.write(routeEdge.element().getStopCodeStart() + "\t" +
                    routeEdge.element().getStopCodeEnd()  + "\t" +
                    routeEdge.element().getDuration());
            newRoutesDuration.write(System.lineSeparator());
        }

        newRoutesDuration.flush();
        newRoutesDuration.close();

        return fileRoutesDuration;
    }

    public File writeXY() throws IOException {
        File fileXY = new File("./datasets/exported/", "xy.txt");
        FileWriter newXY = new FileWriter(fileXY);

        newXY.write("#");
        newXY.write(System.lineSeparator());
        newXY.write("# data file: exported-positions XY\t");
        newXY.write(System.lineSeparator());
        newXY.write("#");
        newXY.write(System.lineSeparator());
        newXY.write("stop_code\tx\ty");
        newXY.write(System.lineSeparator());

        for (Vertex<Stop> stopVertex : exportGraph.vertices()) {
            newXY.write(stopVertex.element().getStopCode() + "\t" +
                    stopVertex.element().getX() + "\t" +
                    stopVertex.element().getY());
            newXY.write(System.lineSeparator());
        }

        newXY.flush();
        newXY.close();

        return fileXY;
    }
}
