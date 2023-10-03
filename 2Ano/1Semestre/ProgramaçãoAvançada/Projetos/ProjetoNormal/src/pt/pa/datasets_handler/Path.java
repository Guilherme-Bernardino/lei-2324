package pt.pa.datasets_handler;

public enum Path {
    DEMO("./datasets/demo/"),
    IBERIA("./datasets/iberia/"),
    EUROPE("./datasets/europe/"),
    EXPORTED("./datasets/exported");

    private String path;


    private Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
