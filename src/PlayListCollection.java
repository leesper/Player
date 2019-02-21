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
	
	public void addSongToPlayList(boolean isMain) {
		int numOfSongs = 0;
		Scanner s = new Scanner(System.in);
		if (isMain) {
			System.out.println("请输入要添加的歌曲的数量：");
			numOfSongs = s.nextInt();
			for (int i = 0; i < numOfSongs; i++) {
				System.out.printf("请输入第%d首歌曲：\n", i+1);
				System.out.println("请输入歌曲的id：");
				String songID = s.next();
				System.out.println("请输入歌曲的名称：");
				String songName = s.next();
				System.out.println("请输入演唱者：");
				String singer = s.next();
				getPlayListMap().get("主播放列表").addToPlayList(new Song(songID, songName, singer));
			}
		} else {
			System.out.println("请输入要添加的播放列表名称：");
			String playListName = s.next();
			if (!getPlayListMap().containsKey(playListName)) {
				System.out.println("该播放列表不存在，请先将播放列表添加到播放器中！");
			} else {
				System.out.println("请输入要添加的歌曲的数量：");
				numOfSongs = s.nextInt();
				for (int i = 0; i < numOfSongs; i++) {
					System.out.printf("请输入第%d首歌曲：\n", i+1);
					System.out.println("请输入歌曲的id：");
					String songID = s.next();
					boolean found = false;
					for (Song song : getPlayListMap().get("主播放列表").getMusicList()) {
						if (song.getId().equals(songID)) {
							found = true;
							getPlayListMap().get(playListName).addToPlayList(song);
						}
					}
					if (!found) {
						System.out.println("该歌曲在主播放列表不存在，继续输入歌曲的其他信息！");
						System.out.println("请输入歌曲的名称：");
						String songName = s.next();
						System.out.println("请输入演唱者：");
						String singer = s.next();
						getPlayListMap().get(playListName).addToPlayList(new Song(songID, songName, singer));
					}
				}
				System.out.println("主播放列表：")	;
				System.out.println("播放列表中的所有歌曲为：");
				getPlayListMap().get("主播放列表").displayAllSong();
				for (String name : getPlayListMap().keySet()) {
					if (!name.equals("主播放列表")) {
						getPlayListMap().get(name).displayAllSong();
					}
				}
			}
		}
	}
	
	public void searchSongById() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要查询的播放列表名称：");
		String playListName = s.next();
		System.out.println("请输入要查询的歌曲ID：");
		String songID = s.next();
		Song song = getPlayListMap().get(playListName).searchSongById(songID);
		if (song != null) {
			System.out.println("该歌曲的信息为：");
			System.out.println(song);
		} else {
			System.out.printf("该歌曲在播放列表%s中不存在！\n", playListName);
		}
	}
	
	public void searchSongByName() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要查询的播放列表名称：");
		String playListName = s.next();
		System.out.println("请输入要查询的歌曲名称：");
		String songName = s.next();
		Song song = getPlayListMap().get(playListName).searchSongByName(songName);
		if (song != null) {
			System.out.println("该歌曲的信息为：");
			System.out.println(song);
		} else {
			System.out.printf("该歌曲在播放列表%s中不存在！\n", playListName);
		}
	}
	
	public void updateSongInfo() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要修改的歌曲id：");
		String songID = s.next();
		System.out.println("请输入修改后的歌曲名称：");
		String songName = s.next();
		System.out.println("请输入修改后的演唱者：");
		String singer = s.next();
		for (PlayList l : getPlayListMap().values()) {
			Song song = l.searchSongById(songID);
			song.setName(songName);
			song.setSinger(singer);
			l.updateSong(song);
		}
		System.out.println("修改成功！");
	}
	
	public void displayAllSongsInPlayList() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要显示的播放列表名称：");
		String playListName = s.next();
		System.out.println("播放列表中的所有歌曲为：");
		PlayList list = searchPlayListByName(playListName);
		if (list != null) {
			list.displayAllSong();
		}
	}
	
	public void removeSong() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要删除的歌曲id：");
		String songID = s.next();
		for (PlayList list : getPlayListMap().values()) {
			list.deleteSong(songID);
		}
		System.out.println("删除成功！");
	}
	
	public void exportPlayList() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入要导出歌单的播放列表名称：");
		String playListName = s.next();
		PlayList playList = searchPlayListByName(playListName);
		if (playList != null) {
			playList.exportPlayList();
		}
		System.out.println("导出成功！");
	}
}
