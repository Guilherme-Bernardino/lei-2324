import java.util.ArrayList;
import java.util.List;

/**
 * Main entry class
 *
 * @author POO
 * @version April '23
 */
public class AppStart
{
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        System.out.println(player.toString());

        player.increaseVolume();
        player.increaseVolume();

        System.out.println(player.toString());

        MediaPlayer streamPlayer = new StreamPlayer();

        Podcast podcast = new Podcast("O Homem que Mordeu o Cão");
        ((StreamPlayer) streamPlayer).setPodcast(podcast);

        System.out.println(streamPlayer.whatsPlaying());
        //System.out.println(player.toString());

        MediaPlayer liveRadioPlayer = new LiveRadioPlayer();

        List<Station> stationList = new ArrayList<>();
        stationList.add(new Station(103.4, "Renascença"));
        stationList.add(new Station(213.3, "1"));
        stationList.add(new Station(321.1, "2"));

        ((LiveRadioPlayer) liveRadioPlayer).setStationList(stationList);
        ((LiveRadioPlayer) liveRadioPlayer).setCurrentStation(stationList.get(0));
        ((LiveRadioPlayer) liveRadioPlayer).previousStation();
        ((LiveRadioPlayer) liveRadioPlayer).previousStation();
        ((LiveRadioPlayer) liveRadioPlayer).previousStation();

        System.out.println(player.whatsPlaying());
        //System.out.println(player.toString());

        //Nivel 4
        MediaPlayer adaptPlayer = new StreamPlayer();

        List<Podcast> podcasts = new ArrayList<>();
        podcasts.add(new Podcast("O Homem que Mordeu o Cão"));
        podcasts.add(new Podcast("O CEO é o limite"));
        podcasts.add(new Podcast("Portugalex"));

        ((StreamPlayer) adaptPlayer).setPodcast(podcasts.get(1));

        System.out.println(adaptPlayer.whatsPlaying());

        adaptPlayer = new LiveRadioPlayer();

        List<Station> stations = new ArrayList<>();
        stations.add(new Station(88.3, "KFM"));
        stations.add(new Station(93.2, "RFM"));
        stations.add(new Station(95.7, "Antena 1"));
        stations.add(new Station(97.4, "Rádio Comercial"));
        stations.add(new Station(101.9, "Orbital"));

        ((LiveRadioPlayer) adaptPlayer).setStationList(stations);

        adaptPlayer.increaseVolume();
        adaptPlayer.increaseVolume();
        adaptPlayer.increaseVolume();
        ((LiveRadioPlayer) adaptPlayer).nextStation();
        System.out.println(adaptPlayer.whatsPlaying());
        ((LiveRadioPlayer) adaptPlayer).nextStation();
        System.out.println(adaptPlayer.whatsPlaying());

        //Nivel 5

        List<MediaPlayer> mediaPlayers = new ArrayList<>();
        mediaPlayers.add(player);
        mediaPlayers.add(streamPlayer);
        mediaPlayers.add(liveRadioPlayer);

        for (MediaPlayer a : mediaPlayers) {
            System.out.println(a.whatsPlaying());
        }
    }
}
