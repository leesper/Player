import java.util.HashMap;
import java.util.Map;

public class PlayListCollection {
	Map<String, PlayList> playListMap;

	public PlayListCollection() {
		playListMap = new HashMap<>();
	}

	public Map<String, PlayList> getPlayListMap() {
		return playListMap;
	}

	public void setPlayListMap(Map<String, PlayList> playListMap) {
		this.playListMap = playListMap;
	}
	
	public void addPlayList(PlayList playList) {}

	public void deletePlayList(PlayList playList) {}

//	public PlayList searchPlayListByName(String playListName) {}

	public void displayPlayListName() {}
}
