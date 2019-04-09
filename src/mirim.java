
//3101 ���ѳ�

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class mirim extends JFrame {

	static JTextField Tkey, Tstr;
	static JButton b1, b2;
	static String key, str;
	static String decryption; // �� �ʱ�ȭ�ص� �ȳ���
	static String encryption;
	static String blankCheck = "";
	static int blankCheckCount = 0;

	public static char alphabetBoard[][] = new char[5][5];
	public static boolean oddFlag = false; // ���ڼ� ���
	public static String zCheck = ""; // ?

	mirim() {

		JFrame f = new JFrame();
		Color color = new Color(0xBEEFFF);
		// Color color1 = new Color(0x9DE4FF);
		Color color2 = new Color(0x969696);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JLabel title = new JLabel("���� ġȯ ��ȣȭ");
		JLabel pw = new JLabel("��ȣ Ű : ");
		Tkey = new JTextField(20);
		JLabel ptext = new JLabel("�� : ");
		Tstr = new JTextField(20);

		b1 = new JButton("��ȣ��");
		b2 = new JButton("��ȣ��");
		b1.setBackground(color2);
		b2.setBackground(color2);
		b1.setOpaque(true);
		b2.setOpaque(true);
		title.setFont(new Font("�޸յձ�������", Font.BOLD, 30));
		pw.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		Tkey.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		ptext.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		Tstr.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		b1.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		b2.setFont(new Font("�޸յձ�������", Font.BOLD, 15));

		p1.add(title);
		p2.add(pw);
		p2.add(Tkey);
		p3.add(ptext);
		p3.add(Tstr);
		p4.add(b1);
		p4.add(b2);

		p1.setBackground(color);
		p2.setBackground(color);
		p3.setBackground(color);
		p4.setBackground(color);
		p1.setOpaque(true);
		p2.setOpaque(true);
		p3.setOpaque(true);
		p4.setOpaque(true);
		f.setLayout(new GridLayout(4, 1));
		f.add(p1);
		f.add(p2);
		f.add(p3);
		f.add(p4);
		f.setTitle("���� ġȯ ��ȣȭ");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocation(600, 200);
		f.setSize(440, 300);
		f.setVisible(true);
		f.setResizable(false);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = Tkey.getText();
				str = Tstr.getText();
				Action1();

			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = Tkey.getText();
				str = Tstr.getText();
				Action2();
			}
		});

		// btnAction();
	} // constructor

	public static void main(String[] args) {

		mirim m = new mirim();

		String blankCheck = "";
		int blankCheckCount = 0;

		setBoard(key); // ��ȣȭ�� ���� ��ȣ�� ����
		
		// ��ºκ�
		// System.out.println("��ȣȭ�� ���ڿ� : " + encryption);

		for (int i = 0; i < encryption.length(); i++) {
			if (encryption.charAt(i) == ' ') // ��������
				encryption = encryption.substring(0, i) + encryption.substring(i + 1, encryption.length());
		}

		for (int i = 0; i < decryption.length(); i++) {
			if (blankCheck.charAt(i) == '1') {
				decryption = decryption.substring(0, i) + " " + decryption.substring(i, decryption.length());
			}
		}

		// System.out.println("��ȣȭ�� ���ڿ� : " + decryption);
	}

	private static void Setstr() {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') // ��������(i��° �ε����� ������ ��)
			{
				str = str.substring(0, i) + str.substring(i + 1, str.length()); // ���� ������ ���� str�� �ֱ�
				blankCheck += 10; // ?
			} else {
				blankCheck += 0; // ?
			}
			if (str.charAt(i) == 'z') // z�� q�� �ٲ��༭ ó����.
			{
				str = str.substring(0, i) + 'q' + str.substring(i + 1, str.length());
				zCheck += 1; // ?
			} else {
				zCheck += 0; // ?
			}
		}
	}

	private static String strEncryption(String key, String str) { // ��ȣȭ

		ArrayList<char[]> playFair = new ArrayList<char[]>(); // �ٲٱ� �� ���ڿ��� ������ ��(2ĭ)
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>(); // ��ȣȭ�� ���ڿ� ������ ��
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		String encStr = "";

		for (int i = 0; i < str.length(); i += 2) // arraylist ����
		{
			char[] tmpArr = new char[2]; // 2ĭ¥�� �迭�� �迭�� �߰�
			tmpArr[0] = str.charAt(i); // 0,2,4 ¦�� �ε����� ���� �� ���� i + 1�� ����
			try {
				if (str.charAt(i) == str.charAt(i + 1)) // ���� �ݺ��Ǹ� x�߰�
				{
					tmpArr[1] = 'x';
					i--; // ?
				} else { // ���� �ݺ����� ������ �ι�° �ε����� ���� �߰�
					tmpArr[1] = str.charAt(i + 1);
				}
			} catch (StringIndexOutOfBoundsException e) {
				tmpArr[1] = 'x';
				oddFlag = true; // ?
			}
			playFair.add(tmpArr); // RX DL �̷� ���� ���ڿ��� �迭�� �߰�
		}

		/*
		 * for (int i = 0; i < playFair.size(); i++) // �迭 �����ŭ �ݺ� {
		 * System.out.print(playFair.get(i)[0] + "" + playFair.get(i)[1] + " "); //
		 * arrayList�� ������ �� get } System.out.println();
		 */
		for (int i = 0; i < playFair.size(); i++) {

			char[] tmpArr = new char[2];
			for (int j = 0; j < alphabetBoard.length; j++) // ���ھ�ȣ�� ���� ��ġüũ
			{
				for (int k = 0; k < alphabetBoard[j].length; k++) {
					if (alphabetBoard[j][k] == playFair.get(i)[0]) {
						x1 = j;
						y1 = k;
					}
					if (alphabetBoard[j][k] == playFair.get(i)[1]) {
						x2 = j;
						y2 = k;
					}
				}
			}

			if (x1 == x2) // ���� �������
			{
				tmpArr[0] = alphabetBoard[x1][(y1 + 1) % 5];
				tmpArr[1] = alphabetBoard[x2][(y2 + 1) % 5];
			} else if (y1 == y2) // ���� ���� ���
			{
				tmpArr[0] = alphabetBoard[(x1 + 1) % 5][y1];
				tmpArr[1] = alphabetBoard[(x2 + 1) % 5][y2];
			} else // ��, �� ��� �ٸ����
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			encPlayFair.add(tmpArr);

		}

		for (int i = 0; i < encPlayFair.size(); i++) {
			encStr += encPlayFair.get(i)[0] + "" + encPlayFair.get(i)[1] + " ";
		}

		// System.out.println(encStr); // null
		return encStr;
	}

	private static String strDecryption(String key, String str, String zCheck) { // ��ȣȭ

		ArrayList<char[]> playFair = new ArrayList<char[]>(); // �ٲٱ� �� ���ھ�ȣ�� ������ ��(��ȣ)
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // �ٲ� ���� ���ھ�ȣ ������ ��(��ȣ)
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0; // ���� ��ȣ �� ������ ������ ��,�� ��
		String decStr = "";

		int lengthOddFlag = 1;

		for (int i = 0; i < str.length(); i += 2) {
			char[] tmpArr = new char[2];
			tmpArr[0] = str.charAt(i);
			tmpArr[1] = str.charAt(i + 1);
			playFair.add(tmpArr);
		}

		for (int i = 0; i < playFair.size(); i++) {

			char[] tmpArr = new char[2];
			for (int j = 0; j < alphabetBoard.length; j++) {
				for (int k = 0; k < alphabetBoard[j].length; k++) {
					if (alphabetBoard[j][k] == playFair.get(i)[0]) {
						x1 = j;
						y1 = k;
					}
					if (alphabetBoard[j][k] == playFair.get(i)[1]) {
						x2 = j;
						y2 = k;
					}
				}
			}

			if (x1 == x2) // ���� ���� ��� ���� �ٷ� �Ʒ��� ����
			{
				tmpArr[0] = alphabetBoard[x1][(y1 + 4) % 5];
				tmpArr[1] = alphabetBoard[x2][(y2 + 4) % 5];
			} else if (y1 == y2) // ���� ���� ��� ���� �ٷ� �� �� ����
			{
				tmpArr[0] = alphabetBoard[(x1 + 4) % 5][y1];
				tmpArr[1] = alphabetBoard[(x2 + 4) % 5][y2];
			} else // ��, �� �ٸ���� ���� �밢���� �ִ� ��.
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmpArr);

		}

		for (int i = 0; i < decPlayFair.size(); i++) // �ߺ� ���ڿ� ��������
		{
			if (i != decPlayFair.size() - 1 && decPlayFair.get(i)[1] == 'x'
					&& decPlayFair.get(i)[0] == decPlayFair.get(i + 1)[0]) {
				decStr += decPlayFair.get(i)[0];
			} else {
				decStr += decPlayFair.get(i)[0] + "" + decPlayFair.get(i)[1];
			}
		}

		for (int i = 0; i < zCheck.length(); i++)// z��ġ ã�Ƽ� q�� ��������
		{
			if (zCheck.charAt(i) == '1')
				decStr = decStr.substring(0, i) + 'z' + decStr.substring(i + 1, decStr.length());

		}

		if (oddFlag)
			decStr = decStr.substring(0, decStr.length() - 1);

		// ����
		for (int i = 0; i < decStr.length(); i++) {
			if (i % 2 == lengthOddFlag) {
				decStr = decStr.substring(0, i + 1) + " " + decStr.substring(i + 1, decStr.length());
				i++;
				lengthOddFlag = ++lengthOddFlag % 2;
			}
		}
		return decStr;
	}

	private static void setBoard(String key) {
		String keyForSet = ""; // �ߺ��� ���ڰ� ���ŵ� ���ڿ��� ������ ���ڿ�.
		boolean duplicationFlag = false; // ���� �ߺ��� üũ�ϱ� ���� flag ����.
		int keyLengthCount = 0; // alphabetBoard�� keyForSet�� �ֱ� ���� count����.

		key += "abcdefghijklmnopqrstuvwxyz"; // Ű�� ��� ���ĺ��� �߰�.
		// �ߺ�ó��
		for (int i = 0; i < key.length(); i++) // ���ĺ� ����ŭ �ݺ�
		{
			for (int j = 0; j < keyForSet.length(); j++) {
				if (key.charAt(i) == keyForSet.charAt(j)) {
					duplicationFlag = true;
					break;
				}
			}
			if (!(duplicationFlag))
				keyForSet += key.charAt(i); // �ߺ��� ������ key�� ���ڸ� �߰�
			duplicationFlag = false;
		}
		// �迭�� ����
		for (int i = 0; i < alphabetBoard.length; i++) // ��(����)
		{
			for (int j = 0; j < alphabetBoard[i].length; j++) // ��(����)
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}
		// �迭�� ����
		for (int i = 0; i < alphabetBoard.length; i++) {
			for (int j = 0; j < alphabetBoard[i].length; j++) {
				// System.out.print(alphabetBoard[i][j]+"-");
			}
			System.out.println();
		}

	}

	private static void Action1() {

		encryption = strEncryption(key, str);
		Color color3 = new Color(0xF5DEAD);
		JFrame f = new JFrame();
		JPanel jp = new JPanel();
		JLabel jl = new JLabel("��ȣ��");
		JLabel jl2 = new JLabel(encryption);
		TitledBorder tb = new TitledBorder(new LineBorder(Color.black));
		jl.setFont(new Font("�޸յձ�������", Font.BOLD, 20));
		jl2.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		jl.setHorizontalAlignment(jl.CENTER);
		jl2.setHorizontalAlignment(jl2.CENTER);
		jl2.setBorder(tb);
		jl2.setBackground(Color.white);
		jl2.setOpaque(true);
		jp.setLayout(new GridLayout(3,0));
		jp.setBackground(color3);
		jp.setOpaque(true);
		jp.add(jl);
		jp.add(jl2);
		f.add(jp);
		
		// f.getContentPane().setBackground(color3);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocation(600, 200);
		f.setSize(440, 300);
		f.setVisible(true);
		f.setResizable(false);

	}

	private static void Action2() {

		decryption = strDecryption(key, str, zCheck);

		Color color3 = new Color(0xF5DEAD);
		JFrame f = new JFrame();
		f.setBackground(color3);
		JPanel jp = new JPanel();
		JLabel jl = new JLabel("��ȣ��");
		JLabel jl2 = new JLabel(decryption); // decryption
		TitledBorder tb = new TitledBorder(new LineBorder(Color.black));
		jl.setFont(new Font("�޸յձ�������", Font.BOLD, 20));
		jl2.setFont(new Font("�޸յձ�������", Font.BOLD, 15));
		jl.setHorizontalAlignment(jl.CENTER);
		jl2.setHorizontalAlignment(jl2.CENTER);
		jl2.setBorder(tb);
		jl2.setBackground(Color.white);
		jl2.setOpaque(true);
		jp.setBackground(color3);
		jp.setOpaque(true);
		jp.setLayout(new GridLayout(3,0));
		jp.add(jl);
		jp.add(jl2);
		f.add(jp);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocation(600, 200);
		f.setSize(440, 300);
		f.setVisible(true);
		f.setResizable(false);

	}
}
