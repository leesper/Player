import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDemo {
	private static Scanner s = new Scanner(System.in);
	
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
					break;
				case 2:
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
	
	public static void playListManageMenu() {
		System.out.println("*********************");
		System.out.println("** 播放列表管理 **");
		System.out.println("1--播放列表管理");
		System.out.println("2--播放器管理");
		System.out.println("3--通过歌曲id查询播放列表中的歌曲");
		System.out.println("4--通过歌曲名称查询播放列表中的歌曲");
		System.out.println("5--修改播放列表中的歌曲");
		System.out.println("6--删除播放列表中的歌曲");
		System.out.println("7--显示播放列表中的所有歌曲");
		System.out.println("8--导出歌单");
		System.out.println("9--返回上一级菜单");
		System.out.println("*********************");
		System.out.println("请输入对应的数字对播放列表进行管理：");
	}
}
