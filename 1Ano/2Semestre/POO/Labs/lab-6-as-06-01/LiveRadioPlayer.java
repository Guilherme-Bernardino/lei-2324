import java.util.ArrayList;
import java.util.List;

public class LiveRadioPlayer extends MediaPlayer{
    List<Station> stationList;
    Station currentStation;

    public LiveRadioPlayer(){
        super();
        this.stationList = new ArrayList<>();
        this.currentStation = null;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public void setStationList(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Override
    public String whatsPlaying(){
        return "Live Radio Station: " + this.currentStation.getFrequency() + ": " + this.currentStation.getName();
    }

    public void nextStation(){
        if(stationList.isEmpty()){
            return;
        }
        int n = stationList.indexOf(currentStation);

        if(n == stationList.size()-1){
            currentStation = stationList.get(0);
            return;
        }

        currentStation = stationList.get(n + 1);
    }

    public void previousStation(){
        if(stationList.isEmpty()){
            return;
        }
        int n = stationList.indexOf(currentStation);

        if(n == 0){
            currentStation = stationList.get(stationList.size() -1);
            return;
        }

        currentStation = stationList.get(n - 1);
    }

}
