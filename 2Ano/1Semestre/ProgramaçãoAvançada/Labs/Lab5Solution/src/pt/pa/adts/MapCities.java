package pt.pa.adts;
import com.brunomnsilva.smartgraph.graph.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapCities {
    private Graph<City, Integer> map = new GraphEdgeList<>();

    public MapCities() {

    }

    public Graph<City, Integer> getMap() {
        return map;
    }

    public int size() {
        return map.vertices().size();
    }

    private Vertex<City> findCity(String city) {
        Iterable<Vertex<City>> cities = map.vertices();
        for (Vertex<City> item : cities)
            if (item.element().toString().trim().equalsIgnoreCase(city.trim()))
                return item;
        return null;
    }

    private boolean existCity(String city) {
        return findCity(city)!=null;
    }

    public void addCity(String city) throws CityInvalidOperation {
        if (existCity(city))
            throw new CityInvalidOperation();
        City newCity = new City(city);
        map.insertVertex(newCity);

    }

    public Vertex<City> removeCity(String city) throws CityInvalidOperation {
        if (!existCity(city))
            throw new CityInvalidOperation();
        Vertex<City> cityToRemove = findCity(city);
        map.removeVertex(cityToRemove);
        return cityToRemove;
    }

    private Edge<Integer, City> findConnection(String sourceCity, String destinationCity) throws ConnectionInvalidOperation
    {
        if (!existCity(sourceCity) || !existCity(destinationCity))
            throw new ConnectionInvalidOperation();
        Vertex<City> city1 = findCity(sourceCity);
        Vertex<City> city2 = findCity(destinationCity);

        for(Edge<Integer, City> edge: map.edges())
            if (edge.vertices()[0].equals(city1) && edge.vertices()[1].equals(city2))
                return edge;
        return null;
    }

    // Checks the connection from sourceCity->destinationCity
    private boolean existConnection(String sourceCity, String destinationCity)
    {
        return findConnection(sourceCity, destinationCity)!=null;
    }

    public Collection<City> neighbors(String city) throws CityInvalidOperation {
        if (!existCity(city))
            throw new CityInvalidOperation("City " + city + " does not exist");

        Vertex<City> cityToFind = findCity(city); // vertex to find

        Set<City> set = new HashSet<>();
        for (Edge<Integer, City> edge: map.edges())
            if (edge.vertices()[0].equals(cityToFind))
                set.add(edge.vertices()[1].element());
            else
                if(edge.vertices()[1].equals(cityToFind) )
                    set.add(edge.vertices()[0].element());

        return set;
    }

    public void addConnection(String sourceCity, String destinationCity, int distance) throws ConnectionInvalidOperation {
        if (existConnection(sourceCity, destinationCity))
            throw new ConnectionInvalidOperation("Connection already exists!");
        //inserts the already existent vertices
        map.insertEdge(findCity(sourceCity), findCity(destinationCity), distance);
    }

    public void removeConnection(String sourceCity, String destinationCity) throws ConnectionInvalidOperation {
        if (!existConnection(sourceCity, destinationCity))
            throw new ConnectionInvalidOperation("Connection already exists!");
        // Removes the connection between sourceCity and destinationCity
        map.removeEdge(findConnection(sourceCity, destinationCity));
    }

    public boolean isIsolated(String city) throws CityInvalidOperation {
        if (!existCity(city))
            throw new CityInvalidOperation("City does not exist!");
        return neighbors(city).size()==0;
    }

    public void load(String filepath) throws FileNotFoundException {
        map = new GraphEdgeList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = br.readLine()) != null) {
                int indexStart=0, indexEnd=0, indexDistance=0;
                String tokens[] = line.trim().split( " ");
                switch(tokens.length) {
                    case 1: // (Ex: Abrantes) Apenas uma cidade (um vértice)
                        if (!existCity(tokens[0]))
                            addCity(tokens[0]);
                        continue;

                    case 2: // (Ex: Faro 2)   Uma cidade e a distância para si própria (Faro-->Faro 5kms)
                        indexStart = indexEnd = 0;
                        indexDistance = indexEnd+1;
                        break;
                    case 3: // (Ex: Viseu Coimbra 95)
                        indexStart = 0;
                        indexEnd = indexStart+1;
                        indexDistance = indexEnd+1;
                        break;
                    default: return; // Fim do ficheiro
                }
                // Add Cities and Connection, if not exists
                if (!existCity(tokens[indexStart]))
                    addCity(tokens[indexStart]);
                if (!existCity(tokens[indexEnd]))
                    addCity(tokens[indexEnd]);
                if (!existConnection(tokens[indexStart], tokens[indexEnd]))
                    addConnection(tokens[indexStart], tokens[indexEnd],Integer.parseInt(tokens[indexDistance]));
            }
        } catch (IOException e) {
            throw new FileNotFoundException(String.format("The file %s does not seem to exist.", filepath));
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MapCities ");
        sb.append("\n\tVertices { ");
        for(Vertex<City> city: map.vertices())
            sb.append(city.element()+" ");
        sb.append("}");
        sb.append("\n\tEdges { ");
        for(Edge<Integer, City> edge : map.edges())
            sb.append("[" + edge.vertices()[0].element() + "-->" + edge.vertices()[1].element()+"] ");
        sb.append("}");
        return sb.toString();
    }
}