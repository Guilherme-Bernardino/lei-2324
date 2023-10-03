package pt.pa.datasets_handler.reader_strategy;

import com.brunomnsilva.smartgraph.graph.Graph;
import javafx.scene.image.Image;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import java.io.IOException;
import java.util.List;

/**
 * Reader Strategy (Strategy pattern), is made of methods for reading a paths to datasets and images.
 *
 * @author Guilherme B. 202001870
 */
public interface ReaderStrategy {

    /**
     * Method reads data from json file and inserts into the graph vertices and edges with said information.
     *
     * @param graph
     * @throws IOException
     */
    void readFileData(Graph<Stop, Route> graph);

    /**
     * Stores in a collection all images regarding the selected directory path.
     *
     * @return list of images for this dataset
     */
    List<Image> readAllImages();

    /**
     * Returns collection of paths for the images.
     *
     * @return thisPath
     */
    List<String> getPaths();
}
