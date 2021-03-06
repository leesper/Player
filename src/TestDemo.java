import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDemo {
	private static Scanner s = new Scanner(System.in);
	private static PlayListCollection collection = new PlayListCollection();
	
	public static void main(String[] args) {
		mainMenu();
	}
	
	public static void mainMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("** 主菜单 **");
			System.out.println("1--播放列表管理");
			System.out.println("2--播放器管理");
			System.out.println("0--退出");
			System.out.println("*********************");
			System.out.println("请输入对应数字进行操作：");
			
			try {
				int mainCommand = s.nextInt();
				switch (mainCommand) {
				case 1:
					playListMenu();
					break;
				case 2:
					playerMenu();
					break;
				case 0:
					return;
				default:
					System.out.println("该数字没有对应的操作！");
				}
			} catch (InputMismatchException ex) {
				System.out.println("请不要输入非数字！");
				// swallow the invalid input
				s.next();
			}
		}
	}
	
	public static void playListMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("** 播放列表管理 **");
			System.out.println("1--将歌曲添加到主播放列表");
			System.out.println("2--将歌曲添加到普通播放列表");
			System.out.println("3--通过歌曲id查询播放列表中的歌曲");
			System.out.println("4--通过歌曲名称查询播放列表中的歌曲");
			System.out.println("5--修改播放列表中的歌曲");
			System.out.println("6--删除播放列表中的歌曲");
			System.out.println("7--显示播放列表中的所有歌曲");
			System.out.println("8--导出歌单");
			System.out.println("9--返回上一级菜单");
			System.out.println("*********************");
			System.out.println("请输入对应的数字对播放列表进行管理：");
			try {
				int playListCommand = s.nextInt();
				switch (playListCommand) {
				case 1:
					System.out.println("将歌曲添加到主播放列表");
					PlayList mainPlayList = collection.searchPlayListByName("主播放列表");
					if (mainPlayList == null) {
						System.out.println("找不到主播放列表，添加歌曲失败！");
						return;
					}
					System.out.println("请输入要添加的歌曲的数量：");
					int numOfSongs = s.nextInt();
					for (int i = 0; i < numOfSongs; i++) {
						System.out.printf("请输入第%d首歌曲：\n", i+1);
						System.out.println("请输入歌曲的id：");
						String songID = s.next();
						System.out.println("请输入歌曲的名称：");
						String songName = s.next();
						System.out.println("请输入演唱者：");
						String singer = s.next();
						mainPlayList.addToPlayList(new Song(songID, songName, singer));
					}
					break;
				case 2:
					System.out.println("将歌曲添加到普通播放列表");
					System.out.println("请输入要添加的播放列表名称：");
					String playListName = s.next();
					PlayList ordinaryPlayList = collection.searchPlayListByName(playListName);
					mainPlayList = collection.searchPlayListByName("主播放列表");
					if (ordinaryPlayList == null) {
						System.out.println("该播放列表不存在，请先将播放列表添加到播放器中！");
						continue;
					}
					System.out.println("请输入要添加的歌曲的数量：");
					numOfSongs = s.nextInt();
					for (int i = 0; i < numOfSongs; i++) {
						System.out.printf("请输入第%d首歌曲：\n", i+1);
						System.out.println("请输入歌曲的id：");
						String songID = s.next();
						Song song = mainPlayList.searchSongById(songID);
						if (song != null) {
							ordinaryPlayList.addToPlayList(song);
						} else {
							System.out.println("该歌曲在主播放列表不存在，继续输入歌曲的其他信息！");
							System.out.println("请输入歌曲的名称：");
							String songName = s.next();
							System.out.println("请输入演唱者：");
							String singer = s.next();
							Song newSong = new Song(songID, songName, singer);
							ordinaryPlayList.addToPlayList(newSong);
							mainPlayList.addToPlayList(newSong);
						}
					}
					
					System.out.println("主播放列表：")	;
					System.out.println("播放列表中的所有歌曲为：");
					collection.searchPlayListByName("主播放列表").displayAllSong();
					System.out.println("普通播放列表：")	;
					System.out.println("播放列表中的所有歌曲为：");
					for (String name : collection.getPlayListMap().keySet()) {
						if (!name.equals("主播放列表")) {
							collection.searchPlayListByName(name).displayAllSong();
						}
					}
					
					break;
				case 3:
					System.out.println("通过歌曲id查询播放列表中的歌曲");
					System.out.println("请输入要查询的播放列表名称：");
					playListName = s.next();
					PlayList playList = collection.searchPlayListByName(playListName);
					if (playList == null) {
						System.out.println("该播放列表不存在！");
						return;
					}
					System.out.println("请输入要查询的歌曲ID：");
					String songID = s.next();
					Song song = playList.searchSongById(songID);
					if (song != null) {
						System.out.println("该歌曲的信息为：");
						System.out.println(song);
					} else {
						System.out.printf("该歌曲在播放列表%s中不存在！\n", playListName);
					}
					break;
				case 4:
					System.out.println("通过歌曲名称查询播放列表中的歌曲");
					System.out.println("请输入要查询的播放列表名称：");
					playListName = s.next();
					playList = collection.searchPlayListByName(playListName);
					if (playList == null) {
						System.out.println("该播放列表不存在！");
						return;
					}
					System.out.println("请输入要查询的歌曲名称：");
					String songName = s.next();
					song = playList.searchSongByName(songName);
					if (song != null) {
						System.out.println("该歌曲的信息为：");
						System.out.println(song);
					} else {
						System.out.printf("该歌曲在播放列表%s中不存在！\n", playListName);
					}
					break;
				case 5:
					System.out.println("修改播放列表中的歌曲");
					System.out.println("请输入要修改的歌曲id：");
					songID = s.next();
					System.out.println("请输入修改后的歌曲名称：");
					songName = s.next();
					System.out.println("请输入修改后的演唱者：");
					String singer = s.next();
					for (PlayList l : collection.getPlayListMap().values()) {
						song = l.searchSongById(songID);
						if (song != null) {
							song.setName(songName);
							song.setSinger(singer);
							l.updateSong(song);
						}
					}
					System.out.println("修改成功！");
					break;
				case 6:
					System.out.println("删除播放列表中的歌曲");
					System.out.println("请输入要删除的歌曲id：");
					songID = s.next();
					for (PlayList list : collection.getPlayListMap().values()) {
						list.deleteSong(songID);
					}
					System.out.println("删除成功！");
					break;
				case 7:
					System.out.println("显示播放列表中的所有歌曲");
					System.out.println("请输入要显示的播放列表名称：");
					playListName = s.next();
					System.out.println("播放列表中的所有歌曲为：");
					PlayList list = collection.searchPlayListByName(playListName);
					if (list != null) {
						list.displayAllSong();
					}
					break;
				case 8:
					System.out.println("导出歌单");
					System.out.println("请输入要导出歌单的播放列表名称：");
					playListName = s.next();
					playList = collection.searchPlayListByName(playListName);
					if (playList != null) {
						playList.exportPlayList();
					}
					System.out.println("导出成功！");
					break;
				case 9:
					return;
				default:
					System.out.println("该数字没有对应的操作！");
				}
			} catch (InputMismatchException ex) {
				System.out.println("请不要输入非数字！");
				// swallow the invalid input
				s.next();
			}
		}
		
	}
	
	public static void playerMenu() {
		while (true) {
			System.out.println("*********************");
			System.out.println("** 播放器管理 **");
			System.out.println("1--向播放器添加播放列表");
			System.out.println("2--从播放器删除播放列表");
			System.out.println("3--通过名字查询播放列表信息");
			System.out.println("4--显示所有播放列表名称");
			System.out.println("9--返回上一级菜单");
			System.out.println("*********************");
			System.out.println("请输入对应的数字对播放器进行管理：");
			
			String playListName;
			try {
				int playerCommand = s.nextInt();
				switch (playerCommand) {
				case 1:
					System.out.println("向播放器添加播放列表");
					System.out.println("输入要添加的播放列表名称：");
					playListName = s.next();
					collection.getPlayListMap().put(playListName, new PlayList(playListName));
					break;
				case 2:
					break;
				case 3:
					System.out.println("通过名字查询播放列表信息");
					System.out.println("请输入要查询的播放列表名称：");
					playListName = s.next();
					boolean found = collection.getPlayListMap().containsKey(playListName);
					if (found) {
						System.out.println("该播放列表存在！");
						System.out.println("该播放列表的名称为：" + playListName);
						System.out.println("播放列表中所有歌曲为：");
						for (Song song : collection.getPlayListMap().get(playListName).getMusicList()) {
							System.out.println(song);
						}
					}
					break;
				case 4:
					System.out.println("显示所有播放列表名称");
					System.out.println("播放列表名称为：");
					for (PlayList pl : collection.getPlayListMap().values()) {
						System.out.println(pl.getPlayListName());
					}
					break;
				case 9:
					return;
				default:
					System.out.println("该数字没有对应的操作！");
				}
			} catch (InputMismatchException ex) {
				System.out.println("请不要输入非数字！");
				// swallow the invalid input
				s.next();
			}
		}
	}
	
	public void testSong() {
		Song song1 = new Song("s001", "种太阳", "太阳合唱团");
		Song song2 = new Song("s001", "种太阳", "太阳合唱团");
		System.out.println(song1.equals(song2));
		System.out.println(song1);
	}
	
	public void testPlayList() {
		PlayList pl = new PlayList("test");
		Song song1 = new Song("s001", "种太阳", "太阳合唱团");
		pl.addToPlayList(song1);
		Song song2 = pl.searchSongById(song1.getId());
		System.out.println(song1.equals(song2));
	}
	
	public void testPlayListCollection() {
		PlayListCollection pc = new PlayListCollection();
		PlayList pl = new PlayList("test");
		Song song1 = new Song("s001", "种太阳", "太阳合唱团");
		pl.addToPlayList(song1);
		pc.addPlayList(pl);
	}
}
