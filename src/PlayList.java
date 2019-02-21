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
	
	public void addToPlayList(Song song) {
		musicList.add(song);
	}

	public void displayAllSong() {
		for (Song song : getMusicList()) {
			System.out.println(song);
		}
	}

	public Song searchSongById(String id) {
		for (Song song : getMusicList()) {
			if (song.getId().equals(id)) {
				return song;
			}
		}
		return null;
	}

	public Song searchSongByName(String n) {
		for (Song song : getMusicList()) {
			if (song.getName().equals(n)) {
				return song;
			}
		}
		return null;
	}

	public void updateSong(Song song) {}

	public void deleteSong(String id) {}

    public void exportPlayList() {}
}
