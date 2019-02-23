import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayList implements Serializable {
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

	public void updateSong(Song song) {
		Song old = searchSongById(song.getId());
		if (old != null) {
			old.setName(song.getName());
			old.setSinger(song.getSinger());
		}
	}

	public void deleteSong(String id) {
		Song song = searchSongById(id);
		if (song != null) {
			musicList.remove(song);
		}
	}

    public void exportPlayList() {
    	try {
			FileOutputStream fos = new FileOutputStream(getPlayListName() + "的歌单.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.flush();
			fos.close();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
