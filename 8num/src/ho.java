
import java.util.Random;

public class ho {
	public static void main(String argv[]) {
		int[] result = new int[9];
		for (int i = 0; i < 9; i++)
			result[i] = i;
		int w;
		Random rand = new Random();
		for (int i = 9 - 1; i > 0; i--) {
			w = rand.nextInt(i);
			int t = result[i];
			result[i] = result[w];
			result[w] = t;
		}
		// for (int i = 0; i < 9; i++)
		// System.out.print(result[].toString());

		
		String Sreslut = "";
		for (int i = 0; i < 9; i++) {
			Sreslut = Sreslut + String.valueOf(result[i]);
		}
		if(test(result))
		{
			System.out.print(Sreslut+"\t");
			String solution[] = new EightPuzzle().getStrategy(Sreslut, 1);
		}
		
		

	}

	static boolean test(int num[]) {
		int[] num0 = new int[8];
		num0 = num;
		int num1[] = { 1, 2, 3, 8, 0, 4, 7, 6, 5 };
		int i, j, sum1 = 0, sum2 = 0;
		for (i = 1; i < 9; i++)
			for (j = 0; j < i; j++) {
				if (num0[i] == 0) {
					j = i;
				} else {
					if (num0[j] > num0[i]) {
						sum1++;
					}
				}
			}
		for (i = 1; i < 9; i++)
			for (j = 0; j < i; j++) {
				if (num1[i] == 0) {
					j = i;
				} else {
					if (num1[j] > num1[i]) {
						sum2++;
					}
				}
			}
		if (sum1 % 2 == sum2 % 2) {
			//System.out.print("Y");
			return true;
		} else {
			//System.out.println("N");
			return false;
		}
	}
}
