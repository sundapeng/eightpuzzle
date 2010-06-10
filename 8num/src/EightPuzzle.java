import java.util.*;

public class EightPuzzle {

	PriorityQueue<iNode> p = new PriorityQueue<iNode>(1,
			new Comparator<iNode>() // ����PriorityQueue
			{
				// ʵ��Comparator�ӿ��еĳ�Ա����������PriorityQueue�����򣬽�fֵ����С��������
				public int compare(iNode o1, iNode o2) {
					if (o1.f < o2.f)
						return -1;
					if (o1.f == o2.f)
						return 0;
					else
						return 1;
				}// PriorityQueue����open��
			});

	HashSet<String> s = new HashSet<String>(); // HashSet����closed��
	iNode r = new iNode(null, 0, 0);
	String result[] = new String[1000]; // ���ڼ�¼���
	int loc, count = 0; // ��¼��0����λ�ú�״̬����
	boolean find = false; // ��������Ƿ��н�
	int solveflag = 0;
	int countflag = 0;
	int nodesflag = 0;
	String result1[];

	public String[] getStrategy(String initState, int solveflag) { // ��ȡ
		r.state = initState; // ����״̬
		this.solveflag = solveflag;
		Strategy(r, initState); // ���ú���
		if (find == true) { // ����н�
			result1 = new String[count]; // �½�����
			for (int i = 0; i < count; i++) {
				result1[i] = result[count - 1 - i];
			} // ��result��Ԫ�������ƹ���
			System.out
					.print(countflag + "\t" + nodesflag );
			if(solveflag == 2)
				System.out.print("\t" + count);
			return result1; // ���ظ��½�����
		} else {
			System.out.println("no solution!");
			return null; // ����޽⣬���"no solution!"�������ؿ�ֵ
		}
	}

	public void Strategy(iNode r, String initState) {
		while (r != null && find != true) { // ���open��ջ����ҵ�Ŀ�꣬�˳�ѭ��
			if (r.state.equals("123804765")) {
				find = true; // ����ҵ�Ŀ�꣬����findΪtrue
				if (solveflag == 0)
					;// System.out.print("ʹ��h(x)=0");
				else if (solveflag == 1) {
					;// System.out.print("ʹ��h(x)='����λ'");
				} else if (solveflag == 2) {
					;// System.out.print("ʹ��h(x)='������'");
				}
				// System.out.println("��������չ" + countflag + "���ڵ�");

				while (r != null) {
					result[count] = r.state;
					count++;
					r = r.head;
				}

			} else {
				countflag++;
				s.add(initState); // ����ǰ״̬����closed��
				loc = location(initState); // ��ȡ��ǰ״̬��0����λ��

				/* ����Ϊ��չ���õ��µ��ӽڵ� */
				if (loc % 3 != 2) { // �����0����λ�ò���2��5��8

					if (loc % 3 != 0
							&& s.contains(move(initState, loc, 0)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 0);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // ����ͬ��
						nodesflag++;
					}
					if (loc >= 2
							&& s.contains(move(initState, loc, 2)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 2);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // ����ͬ��
						nodesflag++;
					}
					if (s.contains(move(initState, loc, 1)) == false) { // ��������ƺ��״̬����closed����
						// System.out.println(s.contains(move(initState,loc,1)));
						iNode rr = new iNode(null, 0, 0); // �½�һ��iNode���͵Ķ���
						rr.state = move(initState, loc, 1); // ����״̬Ϊ���ƺ��״̬
						rr.height = r.height + 1; // �������Ϊ���ڵ����+1
						rr.f = h(rr.state) + rr.height; // ����fֵ
						// System.out.println(rr.height + " " + rr.f + " "+
						// rr.state);
						rr.head = r; // ���ø��ڵ�Ϊr
						p.add(rr); // ���������open��
						nodesflag++;
					}
					if (loc <= 5
							&& s.contains(move(initState, loc, 3)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 3);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // ͬ��
						nodesflag++;
					}
				}

				else { // �����0����λ����2��5��8
					if (s.contains(move(initState, loc, 0)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 0);
						rr.height = r.height + 1;
						rr.f =  h(rr.state) + rr.height;
						// //System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // ͬ��
						nodesflag++;
					}
					if (loc != 2
							&& s.contains(move(initState, loc, 2)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 2);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						// //System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // ͬ��
						nodesflag++;
					}

					if (loc != 8
							&& s.contains(move(initState, loc, 3)) == false) { // �����������
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 3);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						// System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // ͬ��
						nodesflag++;
					}

				}

				r = p.poll(); // ȡ��open���еĵ�һ��Ԫ�أ���ȡfֵ��С��Ԫ�أ������丳ֵ��r
				if (r != null) { // ���r��Ϊ��
					initState = r.state; // ��initState����Ϊr.state
				//System.out.println(initState+"\t"+r.f);
				}
			}
		}
	}

	public int h(String initState) { // h����
		int i, c;
		if (solveflag == 1) {// ͳ���ַ����С�����λ���ַ��ĸ���
			String init = "123804765";
			c = 0;
			for (i = 0; i < 9; i++)
				if (initState.charAt(i) != init.charAt(i))
					c++;
		} else if (solveflag == 2) { // ͳ���ַ�����ÿ����δ��λ�����뵽��Ŀ��λ�õľ���֮��

			int array[][] = new int[4][5];
			int k = 0, j, p = 0, h = 0;
			for (i = 0; i < 3; i++)
				for (j = 0; j < 3; j++) {
					array[i][j] = Integer.valueOf(initState.substring(k, k + 1))
							.intValue();
					// System.out.println(initState.substring(k, k + 1));
					k = k + 1;
				}
			for (i = 0; i < 3; i++)
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
			c = p;
			p = 0;
		} else {
			c = 0;
		}
		return c;
	}

	public int location(String initState) { // ��ȡ�ַ����С�0����λ��
		int j = 0;
		for (int i = 0; i < 9; i++) {
			if (initState.charAt(i) == '0')
				j = i;
		}
		return j;
	}

	public String move(String s, int loc, int m) {
		// �ƶ��ַ����С�0����λ�ã����m=0�����ƣ�m=1���ƣ�m=2���ƣ�m=3����
		StringBuffer ss = new StringBuffer(s);
		if (m == 0) { // ����
			char a = ss.charAt(loc - 1);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc - 1, '0');
		}
		if (m == 1) { // ����
			char a = ss.charAt(loc + 1);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc + 1, '0');
		}
		if (m == 2) { // ����
			char a = ss.charAt(loc - 3);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc - 3, '0');
		}
		if (m == 3) { // ����
			char a = ss.charAt(loc + 3);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc + 3, '0');
		}
		return ss.toString(); // �����ƶ�֮����ַ���
	}

}

class iNode { // �Լ�������࣬���ڼ�¼��ǰ����״̬���ַ���������ȣ�fֵ�Լ����ڵ�
	String state;
	int height;
	iNode head;
	int f;

	public iNode(String s, int h, int fi) { // ���캯��
		state = s;
		height = h;
		f = fi;
	}
}
