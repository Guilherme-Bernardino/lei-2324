public class StreamPlayer extends MediaPlayer{

    private Podcast podcast;

    public StreamPlayer(){
        super();
        this.podcast = new Podcast("");
    }

    public Podcast getPodcast() {
        return podcast;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }

    @Override
    public String whatsPlaying(){
        return "Stream Player: " + this.podcast.getTitle();
    }
}
