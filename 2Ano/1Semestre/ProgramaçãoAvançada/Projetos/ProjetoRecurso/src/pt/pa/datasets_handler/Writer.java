package pt.pa.datasets_handler;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class responsible for writing data from a graph into a new exported .json file.
 * Writes information regarding stops and routes of a graph, parses it and inserts it into the file.
 *
 * @author Guilherme B. 202001870
 */
public class Writer {
    private Graph<Stop,Route> exportGraph;
    private File dir;
    private List<String> originalImagePath;

    private DataList dataList = new DataList();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Writer (Graph<Stop,Route> exportGraph, List<String> originalImagePath){
        this.exportGraph = exportGraph;
        this.originalImagePath = originalImagePath;
    }

    /**
     * Method creates a file directory regarding exported data from a selected graph.
     * Creates an image folder and copies the images of the dataset.
     *
     * @throws IOException
     */
    public void createFile() throws IOException {

        dir = new File("./datasets/exported/img");
        dir.mkdirs();

        //Retrieve images and add to directory.
        for (String url : originalImagePath) {
            try {
                // retrieve image
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                BufferedImage newImg = ImageIO.read(new File(url));
                File outputFile = new File("./datasets/exported/img/" + fileName);
                ImageIO.write(newImg, "png", outputFile);
            } catch (IOException e) {
                throw new IOException("No image found!");
            }
        }

        //Call write data.
        writeData();
    }

    /**
     * Method for writing data regarding selected graph edge and vertex lists, and adding it to the exported .json file.
     *
     */
    public void writeData() {
        //Iterate through exported graph's vertex list, create a new StopData and add to DataList.
        for(Vertex<Stop> stopVertex : exportGraph.vertices()){

            DataList.StopData sd = dataList.createStopData(stopVertex.element().getStopCode(), stopVertex.element().getStopName(),
                    "" + stopVertex.element().getLatitude(), "" + stopVertex.element().getLongitude(),
                    "" + stopVertex.element().getX(), "" + stopVertex.element().getY());
            dataList.getStops().add(sd);
        }

        //Iterate through exported graph's edge list, create a new RouteData and add to DataList.
        for(Edge<Route, Stop> routeEdge : exportGraph.edges()){
            DataList.RouteData rd = dataList.createRouteData(routeEdge.element().getStopCodeStart(), routeEdge.element().getStopCodeEnd(),
                    "" + routeEdge.element().getDistance(), "" + routeEdge.element().getDuration());
            dataList.getRoutes().add(rd);
        }

        //New .json file
        File file = new File("./datasets/exported/", "data.json");

        //Write data into dataset
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(dataList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
