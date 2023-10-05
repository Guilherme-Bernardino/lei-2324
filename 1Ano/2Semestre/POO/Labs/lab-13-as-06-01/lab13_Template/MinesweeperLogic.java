import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Escreva uma descrição da classe MinesweeperLogic aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class MinesweeperLogic
{
    private static MinesweeperLogic _instance = null;
    
    private ArrayList<ArrayList<Tile>> board;
    private int difficulty; // 1, 2 or 3
    private boolean flaggingMode = false; 

    private MinesweeperLogic(int difficulty)
    {
        board = new ArrayList<>();
        this.difficulty = difficulty;
        flaggingMode = false;
        populateAll();
        generateBombs();
        generateNumbers();
    }
    
    public static synchronized MinesweeperLogic getInstance(){
        if(_instance == null){
            _instance = new MinesweeperLogic(1);
        }
        return _instance;
    }
    
    public boolean isFlaggingMode(){
        return flaggingMode;
    }
    
    public void toggleFlaggingMode(){
        this.flaggingMode = !this.flaggingMode;
    }
    
    public int getDifficulty(){
        return this.difficulty;
    }
    
    private void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    
    public void setGame(){
        _instance = new MinesweeperLogic(this.difficulty);
    }

    private void populateAll(){
        for(int x = 0; x < getBoardSizeX(); x++){
            board.add(new ArrayList<>());
            for(int y = 0; y < getBoardSizeY(); y++){
                board.get(x).add(new Tile(x,y,0));
            }
        }
    }
    
    private void generateBombs() {
        Random rand = new Random(33*System.currentTimeMillis());
        for (int i = 0; i < getNumberOfBombs(); i++) {
            while (true) {
                int x = rand.nextInt(getBoardSizeX());
                int y = rand.nextInt(getBoardSizeY());
                Tile tile = getTile(x, y);
                if(tile.getValue() != -1) {
                    board.get(x).set(y, new Tile(x,y,-1));
                    break;
                }
            }
        }
    }
    
    public void generateNumbers(){
        for(int x = 0; x < getBoardSizeX(); x++){
            for(int y = 0; y < getBoardSizeY(); y++){
                Tile currentTile = getTile(x,y);
                if (currentTile.getValue() == -1){
                    continue;
                } 
                int value = getTilesAround(x,y)
                    .stream()
                    .filter(e -> e.getValue() == -1)
                    .collect(Collectors.toList())
                    .size();
                
                currentTile.setValue(value);
            }
        }
    }
    
    public List<Tile> propagateWaterAlgorithm(Tile tile){
        int x = tile.getX();
        int y = tile.getY();
        List<Tile> numbers = new ArrayList(); //para mostrar
        List<Tile> waterVisited = new ArrayList();//nao repetir estes
        List<Tile> nextToBeVisited = getTilesAround(x, y);//proximos a visitar
        
        // Check if the initial tile is water, add to visited list and numbers list
        if (getTile(x, y) != null && getTile(x, y).getValue() == 0) {
            Tile initialWaterTile = getTile(x, y);
            waterVisited.add(initialWaterTile);
            numbers.add(initialWaterTile); // Add initial water tile to the list
        } else {
            return new ArrayList<>();
        }
        
        // Continue propagating water until there are no more tiles to visit
        while(!nextToBeVisited.isEmpty()){
            ArrayList<Tile> temporary = new ArrayList();//lista que irá ser os proximos a ser visitados
            
            // Iterate over each tile that needs to be visited
            for (Tile t : nextToBeVisited) {
                // Check if the tile is water and hasn't been visited yet
                if (t != null && t.getValue() == 0 && !waterVisited.contains(t)) {
                    waterVisited.add(t); // Add to visited list
                    numbers.add(t); // Add to list of tiles to show
                    
                    // Get the tiles around this one
                    List<Tile> surroundingTiles = getTilesAround(t.getX(), t.getY());
                    
                    // Add the surrounding tiles that are water and haven't been visited yet to the temporary list
                    for (Tile s : surroundingTiles) {
                        if (s != null && s.getValue() == 0 && !waterVisited.contains(s)) {
                            temporary.add(s);
                        }
                    }
                }
            }
            
            // Update the next tiles to be visited with the ones found in the previous step
            nextToBeVisited = temporary;
        }
        
        // Now that the water has finished propagating, we add any surrounding tiles that are not already in the numbers list
        for (Tile visitedTile : waterVisited) {
            List<Tile> surroundingTiles = getTilesAround(visitedTile.getX(), visitedTile.getY());
            for (Tile surroundingTile : surroundingTiles) {
                if (surroundingTile != null && surroundingTile.getValue() != 0 && !numbers.contains(surroundingTile)) {
                    numbers.add(surroundingTile);
                }
            }
        }
        
        return numbers;
    }
    
    public List<Tile> getTilesAround(int x, int y){
        if(flaggingMode) return new ArrayList<>();
        
        List<Tile> tilesAround = new ArrayList<>();
                tilesAround.add(getTile(x-1, y-1));
                tilesAround.add(getTile(x-1, y));
                tilesAround.add(getTile(x-1, y+1));
                tilesAround.add(getTile(x, y-1));
                tilesAround.add(getTile(x, y+1));
                tilesAround.add(getTile(x+1, y-1));
                tilesAround.add(getTile(x+1, y));
                tilesAround.add(getTile(x+1, y+1));
        return tilesAround.stream()
            .filter(e -> e != null)
            .collect(Collectors.toList());
    }
    public void changeDifficulty(){
        this.difficulty = this.difficulty + 1;
        if(this.difficulty > 3){
            this.difficulty = 1;
        }
        setGame();
    }
    public Tile getTile(int x, int y){
        if(x >= getBoardSizeX() || x < 0) return null;
        if(y >= getBoardSizeY() || y < 0) return null;
        return this.board.get(x).get(y);
    }
    
    public int getNumberOfBombs(){
        switch(difficulty){
            case 1:
                return 8;
            case 2:
                return 30;
            case 3:
                return 70;
            default:
                return 10;
        }
    }
    
    public int[] getBoardSize(){
        switch(difficulty){
            case 1:
                return new int[]{10, 8};
            case 2:
                return new int[]{14, 10};
            case 3:
                return new int[]{18, 14};
            default:
                return new int[]{8, 6};
        }
    }
    public int getBoardSizeX(){
        return getBoardSize()[0];
    }
    public int getBoardSizeY(){
        return getBoardSize()[1];
    }
}
