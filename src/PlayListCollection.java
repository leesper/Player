import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayListCollection {
	Map<String, PlayList> playListMap;

	public PlayListCollection() {
		playListMap = new HashMap<>();
		playListMap.put("主播放列表", new PlayList("主播放列表"));
	}

	public Map<String, PlayList> getPlayListMap() {
		return playListMap;
	}

	public void setPlayListMap(Map<String, PlayList> playListMap) {
		this.playListMap = playListMap;
	}
	
	public void addPlayList(PlayList playList) {
		getPlayListMap().put(playList.getPlayListName(), playList);
	}

	public void deletePlayList(PlayList playList) {
		getPlayListMap().remove(playList.getPlayListName());
	}

	public PlayList searchPlayListByName(String playListName) {
		for (PlayList playList : getPlayListMap().values()) {
			if (playList.getPlayListName().equals(playListName)) {
				return playList;
			}
		}
		return null;
	}

	public void displayPlayListName() {
		for (String name : playListMap.keySet()) {
			System.out.println(name);
		}
	}
}
