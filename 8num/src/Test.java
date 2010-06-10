
public class Test {
	public static void main(String argv[]) {
		String test0 = "123840765";
		String test1 = "283164705";
		String test2 = "834265170";
		String test3 = "216408753";
		String test4 = "280163754";
		String test5 = "134802765";
		String test6 = "306512784";
		for (int n = 0; n < 3; n++) {
			String solution[] = new EightPuzzle().getStrategy(test3, n);
			System.out.print("\n");
			if (solution != null) {
				for (int i = 0; i < solution.length; i++)
					System.out.println(solution[i]+" "+i);
			}
		}
	}
}
