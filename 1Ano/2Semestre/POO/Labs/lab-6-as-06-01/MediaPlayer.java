public class MediaPlayer {

    private int volume;
    private boolean muted;

    public MediaPlayer(){
        this.volume = 5;
        this.muted = false;
    }

    public void increaseVolume(){
        if(volume == 30){
            return;
        }

        this.volume++;
    }

    public void decreaseVolume(){
        if( volume == 0){
            return;
        }

        this.volume--;
    }

    public String whatsPlaying(){
        return "Nothing to play.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Volume: ");
        sb.append(this.volume + "\n");
        sb.append(whatsPlaying());

        return sb.toString();
    }
}
