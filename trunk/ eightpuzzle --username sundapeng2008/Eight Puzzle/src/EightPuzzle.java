import java.util.*;

public class EightPuzzle {

	PriorityQueue<iNode> p = new PriorityQueue<iNode>(1,
			new Comparator<iNode>() // 定义PriorityQueue
			{
				// 实现Comparator接口中的成员函数，用于PriorityQueue中排序，将f值按从小到大排列
				public int compare(iNode o1, iNode o2) {
					if (o1.f < o2.f)
						return -1;
					if (o1.f == o2.f)
						return 0;
					else
						return 1;
				}// PriorityQueue用作open表
			});

	HashSet<String> s = new HashSet<String>(); // HashSet用作closed表
	iNode r = new iNode(null, 0, 0);
	String result[] = new String[1000]; // 用于记录结果
	int loc, count = 0; // 记录‘0’的位置和状态个数
	boolean find = false; // 用作标记是否有解
	int solveflag = 0;
	int countflag = 0;
	int nodesflag = 0;
	String result1[];

	public String[] getStrategy(String initState, int solveflag) { // 获取
		r.state = initState; // 设置状态
		this.solveflag = solveflag;
		Strategy(r, initState); // 调用函数
		if (find == true) { // 如果有解
			result1 = new String[count]; // 新建数组
			for (int i = 0; i < count; i++) {
				result1[i] = result[count - 1 - i];
			} // 将result中元素逆序复制过来
			System.out
					.print(countflag + "\t" + nodesflag );
			if(solveflag == 2)
				System.out.print("\t" + count);
			return result1; // 返回该新建数组
		} else {
			System.out.println("no solution!");
			return null; // 如果无解，输出"no solution!"，并返回空值
		}
	}

	public void Strategy(iNode r, String initState) {
		while (r != null && find != true) { // 如果open表空或者找到目标，退出循环
			if (r.state.equals("123804765")) {
				find = true; // 如果找到目标，设置find为true
				if (solveflag == 0)
					;// System.out.print("使用h(x)=0");
				else if (solveflag == 1) {
					;// System.out.print("使用h(x)='不在位'");
				} else if (solveflag == 2) {
					;// System.out.print("使用h(x)='曼哈顿'");
				}
				// System.out.println("方法共拓展" + countflag + "个节点");

				while (r != null) {
					result[count] = r.state;
					count++;
					r = r.head;
				}

			} else {
				countflag++;
				s.add(initState); // 将当前状态加入closed表
				loc = location(initState); // 获取当前状态‘0’的位置

				/* 以下为拓展结点得到新的子节点 */
				if (loc % 3 != 2) { // 如果‘0’的位置不是2，5，8

					if (loc % 3 != 0
							&& s.contains(move(initState, loc, 0)) == false) { // 如果能向左移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 0);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // 基本同上
						nodesflag++;
					}
					if (loc >= 2
							&& s.contains(move(initState, loc, 2)) == false) { // 如果能向上移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 2);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // 基本同上
						nodesflag++;
					}
					if (s.contains(move(initState, loc, 1)) == false) { // 如果向右移后的状态不在closed表中
						// System.out.println(s.contains(move(initState,loc,1)));
						iNode rr = new iNode(null, 0, 0); // 新建一个iNode类型的对象
						rr.state = move(initState, loc, 1); // 设置状态为左移后的状态
						rr.height = r.height + 1; // 设置深度为父节点深度+1
						rr.f = h(rr.state) + rr.height; // 设置f值
						// System.out.println(rr.height + " " + rr.f + " "+
						// rr.state);
						rr.head = r; // 设置父节点为r
						p.add(rr); // 将对象加入open表
						nodesflag++;
					}
					if (loc <= 5
							&& s.contains(move(initState, loc, 3)) == false) { // 如果能向下移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 3);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						rr.head = r;
						p.add(rr); // 同上
						nodesflag++;
					}
				}

				else { // 如果‘0’的位置是2，5，8
					if (s.contains(move(initState, loc, 0)) == false) { // 如果能向左移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 0);
						rr.height = r.height + 1;
						rr.f =  h(rr.state) + rr.height;
						// //System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // 同上
						nodesflag++;
					}
					if (loc != 2
							&& s.contains(move(initState, loc, 2)) == false) { // 如果能向上移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 2);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						// //System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // 同上
						nodesflag++;
					}

					if (loc != 8
							&& s.contains(move(initState, loc, 3)) == false) { // 如果能向下移
						iNode rr = new iNode(null, 0, 0);
						rr.state = move(initState, loc, 3);
						rr.height = r.height + 1;
						rr.f = h(rr.state) + rr.height;
						// System.out.println(rr.height + " " + rr.f + " " +
						// rr.state);
						rr.head = r;
						p.add(rr); // 同上
						nodesflag++;
					}

				}

				r = p.poll(); // 取出open表中的第一个元素，即取f值最小的元素，并将其赋值给r
				if (r != null) { // 如果r不为空
					initState = r.state; // 将initState设置为r.state
				//System.out.println(initState+"\t"+r.f);
				}
			}
		}
	}

	public int h(String initState) { // h函数
		int i, c;
		if (solveflag == 1) {// 统计字符串中“不在位”字符的个数
			String init = "123804765";
			c = 0;
			for (i = 0; i < 9; i++)
				if (initState.charAt(i) != init.charAt(i))
					c++;
		} else if (solveflag == 2) { // 统计字符串中每个“未就位”数码到其目标位置的距离之和

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

	public int location(String initState) { // 获取字符串中‘0’的位置
		int j = 0;
		for (int i = 0; i < 9; i++) {
			if (initState.charAt(i) == '0')
				j = i;
		}
		return j;
	}

	public String move(String s, int loc, int m) {
		// 移动字符串中‘0’的位置，如果m=0则左移，m=1右移，m=2上移，m=3下移
		StringBuffer ss = new StringBuffer(s);
		if (m == 0) { // 左移
			char a = ss.charAt(loc - 1);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc - 1, '0');
		}
		if (m == 1) { // 右移
			char a = ss.charAt(loc + 1);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc + 1, '0');
		}
		if (m == 2) { // 上移
			char a = ss.charAt(loc - 3);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc - 3, '0');
		}
		if (m == 3) { // 下移
			char a = ss.charAt(loc + 3);
			ss.setCharAt(loc, a);
			ss.setCharAt(loc + 3, '0');
		}
		return ss.toString(); // 返回移动之后的字符串
	}

}

class iNode { // 自己定义的类，用于记录当前结点的状态（字符串），深度，f值以及父节点
	String state;
	int height;
	iNode head;
	int f;

	public iNode(String s, int h, int fi) { // 构造函数
		state = s;
		height = h;
		f = fi;
	}
}
