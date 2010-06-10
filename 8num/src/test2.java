
public class test2 {
	public static void main(String argv[]) {
		String initState = "283104765";
		int array[][] = new int[4][5];
		int k = 0, j, p = 0, h = 0;
		for (int i = 0; i < 3; i++)
			for (j = 0; j < 3; j++) {
				array[i][j] = Integer.valueOf(initState.substring(k, k + 1))
						.intValue();
				System.out.println(array[i][j]);
				k = k + 1;
			}
		for (int i = 0; i < 3; i++)
			for (j = 0; j < 3; j++) {
				switch (array[i][j]) {
				case 0: {
					h = Math.abs(i - 1) + Math.abs(j - 1);
					break;
				}
				case 1: {
					h = Math.abs(i - 0) + Math.abs(j - 0);
					break;
				}
				case 2: {
					h = Math.abs(i - 0) + Math.abs(j - 1);
					break;
				}
				case 3: {
					h = Math.abs(i - 0) + Math.abs(j - 2);
					break;
				}
				case 4: {
					h = Math.abs(i - 1) + Math.abs(j - 2);
					break;
				}
				case 5: {
					h = Math.abs(i - 2) + Math.abs(j - 2);
					break;
				}
				case 6: {
					h = Math.abs(i - 2) + Math.abs(j - 1);
					break;
				}
				case 7: {
					h = Math.abs(i - 2) + Math.abs(j - 0);
					break;
				}
				case 8: {
					h = Math.abs(i - 1) + Math.abs(j - 0);
					break;
				}

				}
				p = p + h;
			}
		System.out.println(p);
}
}