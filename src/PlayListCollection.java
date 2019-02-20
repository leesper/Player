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
	
	public void addPlayList(PlayList playList) {}

	public void deletePlayList(PlayList playList) {}

//	public PlayList searchPlayListByName(String playListName) {}

	public void displayPlayListName() {}
	
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
}
