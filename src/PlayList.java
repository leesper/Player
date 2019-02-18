import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private String playListName;
	private List<Song> musicList;
	
	public PlayList(String playListName) {
		this.playListName = playListName;
		musicList = new ArrayList<>();
	}

	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public List<Song> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<Song> musicList) {
		this.musicList = musicList;
	}
	
	public void addToPlayList(Song song) {}

	public void displayAllSong() {}

//	public Song searchSongById(String id) {}

//	public Song searchSongByName(String n) {}

	public void updateSong(String id,Song song) {}

	public void deleteSong(String id) {}

    public void exportPlayList() {}
}
