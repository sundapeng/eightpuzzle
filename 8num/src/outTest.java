
import java.util.Random;

public class outTest {
	public static void main(String argv[]) {
		String string = null;
		int j, i, k;
		for (j = 0; j < 1000; j++) {
			string = RandomTar();
			if (string.equals("a") == false) {
				for (i = 0; i < 3; i++) {
					{
						System.out.print("\t");
						String solution[] = new EightPuzzle().getStrategy(string, i);
						for (k = 0; k < solution.length; k++) {
							;//System.out.println(solution[k]+" "+k);
						}
						
					}
				}
				System.out.print("\n");
			}
		}
	}

	static String RandomTar() {
Random rd = null;
		   int[] src={1,2,3,4,5,6,7,8,0};

		  
		        rd = new Random();
		         int[] tmp = new int[src.length];
		  
		        int num = src.length;
		  

		        int index;

		        for(int i = 0;i < src.length;i++)
		        {
		  
		            index = Math.abs(rd.nextInt()) % num;
		          tmp[i] = src[index];
		            src[index] = src[num - 1];
		            num--;
		        }
		String Sreslut = "";
		for (int i = 0; i < 9; i++) {
			Sreslut = Sreslut + String.valueOf(tmp[i]);
		}
		if (test(tmp)) {
			System.out.print(Sreslut);
			// String solution[] = new EightPuzzle().getStrategy(Sreslut, 1);
			return Sreslut;
		}
		return "a";
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
			// System.out.print("Y");
			return true;
		} else {
			// System.out.println("N");
			return false;
		}
	}
}
