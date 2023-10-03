package pt.pa.datasets_handler;

/**
 * Enum that equals the file path that is referencing.
 *
 * @author Guilherme B. 202001870
 */
public enum Path {
    DEMO("./datasets/demo/"),
    IBERIA("./datasets/iberia/"),
    EUROPE("./datasets/europe/"),
    EXPORTED("./datasets/exported/");

    private String path;

    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
