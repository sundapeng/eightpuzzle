import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ENumber extends JFrame implements ActionListener, KeyListener {
	private JButton buttons[];
	private final String names[] = { "1", "2", "3", "8", "", "4", "7", "6", "5" };
	private String array = "";
	private Container container;
	private JPanel panel;
	private int nowLocal;// local of bank square
	private final int UP = 3, DOWN = -3, LEFT = 1, RIGHT = -1;
	private JMenuItem theItem[];
	private JMenu allMenu[];
	private JMenuBar bar;
	String test1 = "283164750";

	// String test2 = "123456780";
	// String solution[] = new EightPuzzle().getStrategy(test1);

	public ENumber() {
		super("Number");
		// get content pane and set its layout,create buttons
		container = getContentPane();
		container.setLayout(new BorderLayout(5, 5));
		panel = new JPanel(new GridLayout(3, 3, 5, 5));
		buttons = new JButton[names.length];
		theItem = new JMenuItem[8];
		allMenu = new JMenu[3];
		bar = new JMenuBar();

		initMenu();
		initButtons();
		container.add(bar, BorderLayout.NORTH);
		container.add(panel, BorderLayout.CENTER);

		setSize(280, 320);
		setLocation(250, 250);
		setVisible(true);

	} // end ENumber

	public void initMenu() {// 初始化按钮
		String menu[] = { "File", "Option", "More" };
		char men[] = { 'F', 'O', 'M' };
		String item[] = { "New", "Set", "Exit", "Random", "Slove-Absence",
				"Slove-Manhattan", "Help", "About..." };
		char ite[] = { 'N', 'S', 'x', 'R', 'b', 'M', 'e', 'A' };

		for (int i = 0; i < 8; i++) {
			theItem[i] = new JMenuItem(item[i]);
			theItem[i].setMnemonic(ite[i]);
			theItem[i].addActionListener(this);
		}

		for (int i = 0; i < 3; i++) {
			allMenu[i] = new JMenu(menu[i]);
			allMenu[i].setMnemonic(men[i]);
			allMenu[i].add(theItem[i * 3 + 0]);
			allMenu[i].add(theItem[i * 3 + 1]);
			if ((i * 3 + 2) < 6)
				allMenu[i].add(theItem[i * 3 + 2]);
			bar.add(allMenu[i]);
		}
	}

	public void initButtons() {// 初始化布局界面
		for (int count = 0; count < names.length; count++) {
			buttons[count] = new JButton(names[count]);
			buttons[count].addActionListener(this);
			buttons[count].addKeyListener(this);
			buttons[count].setFont(new Font("Serif", Font.BOLD, 40));// set font
			panel.add(buttons[count]);
		}
		buttons[4].setEnabled(false);
		nowLocal = 4;
	}

	// handle button events
	public void actionPerformed(ActionEvent event) {// 按钮事件响应
		int num = -1;
		if (event.getActionCommand() == "New")
			reInitButtons();
		else if (event.getActionCommand() == "Exit")
			System.exit(0);
		else if (event.getActionCommand() == "Random")
			randomButtons();
		else if (event.getActionCommand() == "Slove-Absence") {
			SloveButtons slove = new SloveButtons(1);// 创建Absence自动解答的线程

			slove.RunWithAllMethod();
			slove.start();
		} else if (event.getActionCommand() == "Slove-Manhattan") {
			SloveButtons slove = new SloveButtons(2);// 创建Manhattan自动解答的线程

			slove.RunWithAllMethod();
			slove.start();
		} else if (event.getActionCommand() == "Set") {
			String SetStr = JOptionPane.showInputDialog(null, "请输入一个序列（0-8）：");
			RefleshButtons(SetStr);
		} else if (event.getActionCommand() == "Help")
			JOptionPane
					.showMessageDialog(
							ENumber.this,
							"New---开始新的一局\nRandom---随机生成布局\nSet---设置布局\nslove---一步一步走回去",
							"Help", JOptionPane.PLAIN_MESSAGE);
		else if (event.getActionCommand() == "About...")
			JOptionPane
					.showMessageDialog(ENumber.this, "姓名:孙大鹏\nID:1291407066\nNENU",
							"About", JOptionPane.PLAIN_MESSAGE);
		else {
			// 寻找事件的按钮位置
			for (num = 0; num < buttons.length; num++)
				if (event.getSource() == buttons[num])
					break;

			// 判断按钮是否位于空格的隔壁(上下左右)
			if (num == nowLocal + 3 || num == nowLocal - 3
					|| num == nowLocal + 1 || num == nowLocal - 1)
				moveButton(num - nowLocal);
		}
	}// end actionPerformed

	// key event
	public void keyPressed(KeyEvent event) {// 键盘事件响应
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_UP)
			moveButton(UP);
		if (key == KeyEvent.VK_DOWN)
			moveButton(DOWN);
		if (key == KeyEvent.VK_LEFT)
			moveButton(LEFT);
		if (key == KeyEvent.VK_RIGHT)
			moveButton(RIGHT);
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

	public String getNowState() {
		String str2 = "";
		for (int count = 0; count < names.length; count++) {
			if (buttons[count].getText().equals("")) {
				str2 = str2 + "0";
			} else {
				str2 = str2 + buttons[count].getText();
			}
		}
		return str2;
	}

	public void moveButton(int num) {// 往num值代表的方向移动
		if (canMove(num)) {
			buttons[nowLocal].setText(buttons[nowLocal + num].getText());
			buttons[nowLocal].setEnabled(true);
			buttons[nowLocal + num].setText("");
			buttons[nowLocal + num].setEnabled(false);
			nowLocal = nowLocal + num;
		}
		num = num + 3;
		array += num; // 将移动方向的相对值存进数组
	}

	public boolean canMove(int num) {// 检测是否可以上、下、左、右移动
		boolean move = false;
		if ((num == UP) && (nowLocal + 3 < 9))// can move up
			move = true;
		else if ((num == DOWN) && (nowLocal - 3 >= 0))// can move down
			move = true;
		else if ((num == RIGHT) && (nowLocal % 3 != 0))// can move right
			move = true;
		else if ((num == LEFT) && ((nowLocal + 1) % 3 != 0))// can move left
			move = true;
		return move;
	}

	public void reInitButtons() {// 重新布局
		for (int count = 0; count < names.length; count++) {
			buttons[count].setText(names[count]);
			buttons[count].setEnabled(true);
		}
		buttons[4].setEnabled(false);
		nowLocal = 4;
		array = "";
	}

	public void RefleshButtons(String str) {// 刷新
		for (int count = 0; count < names.length; count++) {
			if (str.substring(count, count + 1).equals("0")) {
				buttons[count].setEnabled(false);
				buttons[count].setText("");
				nowLocal = count;
			} else {
				buttons[count].setText(str.substring(count, count + 1));
				buttons[count].setEnabled(true);
			}
		}
		// buttons[8].setEnabled(false);
		// nowLocal = 8;
		// array = "";
	}

	public void randomButtons() {// 生成随机组合
		reInitButtons();
		int step = 27, go[] = { 2, 4, 6, 0, 0, 6, 4, 2, 2, 4, 6, 0, 2 };
		int pos = 0, way, forwordWay = -1;
		pos = (int) (Math.random() * 11);
		while (step > 0) {
			way = go[pos] - 3;
			if (!canMove(way))
				way = -way;
			if (forwordWay + way != 0) {
				moveButton(way);
				forwordWay = way;
				step--;
			}
			pos = (int) (Math.random() * 11);
		}
	}

	private class SloveButtons extends Thread {
		private int solveflag;
		EightPuzzle Absence = new EightPuzzle();
		EightPuzzle Manhattan = new EightPuzzle();
		String solution[];

		// 调用算法
		public SloveButtons(int i) {
			solveflag = i;
		}

		public void RunWithAllMethod() {
			Absence.getStrategy(getNowState(), 1);
			Manhattan.getStrategy(getNowState(), 2);
		}

		public void run() {
			System.out.println(solveflag);
			if (solveflag == 1) {
				solution = Absence.result1;
			} else if (solveflag == 2) {
				solution = Manhattan.result1;
			}
			if (solution != null) {
				for (int i = 0; i < solution.length; i++) {
					try {
						Thread.sleep(800);
					} catch (InterruptedException exception) {
					}
					// way = -(Integer.parseInt("" + array.charAt(len - 1 - i))
					// - 3);
					// moveButton(1);
					RefleshButtons(solution[i]);
					System.out.println(solution[i]);
				}
				JOptionPane.showMessageDialog(ENumber.this, "使用‘不在位’拓展"
						+ Absence.countflag + "个节点\n使用‘曼哈顿’拓展"
						+ Manhattan.countflag + "个节点\n", "OK",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(ENumber.this, "No solution!",
						"OK", JOptionPane.PLAIN_MESSAGE);
			}
			array = "";

			reInitButtons();
		}
	}

	public static void main(String args[]) {
		ENumber application = new ENumber();
		application.setResizable(false);// windows can not resizable
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
} // end class ENumber

