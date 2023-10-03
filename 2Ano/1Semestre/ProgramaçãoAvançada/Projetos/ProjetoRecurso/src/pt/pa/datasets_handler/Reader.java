package pt.pa.datasets_handler;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.google.gson.Gson;
import javafx.scene.image.Image;
import pt.pa.datasets_handler.reader_strategy.ReaderStrategy;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for reading a selected .json file.
 * Reads information regarding stops and routes of a directory, parses it and inserts to graph.
 * Keeps information about map image and respective path.
 *
 * @author Guilherme B. 202001870
 */
public class Reader {
    private ReaderStrategy readerStrategy;
    private Gson gson;
    private DataList dataList;
    private List<Image> images;
    private List<String> thisPath;

    public Reader(ReaderStrategy readerStrategy){
        this.gson = new Gson();
        this.dataList = new DataList();
        this.images = new ArrayList<>();
        this.thisPath = new ArrayList<>();
        this.readerStrategy = readerStrategy;
    }

    /**
     * Method reads data from json file and inserts into the graph vertices and edges with said information.
     *
     * @param graph
     * @param readerStrategy
     * @throws IOException
     */
    public void readFileData(Graph<Stop, Route> graph, ReaderStrategy readerStrategy) throws IOException {
        readerStrategy.readFileData(graph);
    }

    /**
     * Stores in a collection all images regarding the selected directory path.
     * @param readerStrategy
     * @return list of images for this dataset
     */
    public List<Image> readAllImages(ReaderStrategy readerStrategy){
        return readerStrategy.readAllImages();
    }

    /**
     * Returns collection of paths for the images.
     * @return thisPath
     */
    public List<String> getPaths(){
        return readerStrategy.getPaths();
    }

}
