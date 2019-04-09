
//3101 강한나

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
	static String decryption; // 값 초기화해도 안나옴
	static String encryption;
	static String blankCheck = "";
	static int blankCheckCount = 0;

	public static char alphabetBoard[][] = new char[5][5];
	public static boolean oddFlag = false; // 글자수 출력
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
		JLabel title = new JLabel("다중 치환 암호화");
		JLabel pw = new JLabel("암호 키 : ");
		Tkey = new JTextField(20);
		JLabel ptext = new JLabel("평문 : ");
		Tstr = new JTextField(20);

		b1 = new JButton("암호문");
		b2 = new JButton("복호문");
		b1.setBackground(color2);
		b2.setBackground(color2);
		b1.setOpaque(true);
		b2.setOpaque(true);
		title.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 30));
		pw.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
		Tkey.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
		ptext.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
		Tstr.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
		b1.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
		b2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));

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
		f.setTitle("다중 치환 암호화");
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

		setBoard(key); // 암호화에 쓰일 암호판 세팅
		
		// 출력부분
		// System.out.println("암호화된 문자열 : " + encryption);

		for (int i = 0; i < encryption.length(); i++) {
			if (encryption.charAt(i) == ' ') // 공백제거
				encryption = encryption.substring(0, i) + encryption.substring(i + 1, encryption.length());
		}

		for (int i = 0; i < decryption.length(); i++) {
			if (blankCheck.charAt(i) == '1') {
				decryption = decryption.substring(0, i) + " " + decryption.substring(i, decryption.length());
			}
		}

		// System.out.println("복호화된 문자열 : " + decryption);
	}

	private static void Setstr() {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') // 공백제거(i번째 인덱스가 공백일 때)
			{
				str = str.substring(0, i) + str.substring(i + 1, str.length()); // 공백 제거한 문자 str에 넣기
				blankCheck += 10; // ?
			} else {
				blankCheck += 0; // ?
			}
			if (str.charAt(i) == 'z') // z를 q로 바꿔줘서 처리함.
			{
				str = str.substring(0, i) + 'q' + str.substring(i + 1, str.length());
				zCheck += 1; // ?
			} else {
				zCheck += 0; // ?
			}
		}
	}

	private static String strEncryption(String key, String str) { // 암호화

		ArrayList<char[]> playFair = new ArrayList<char[]>(); // 바꾸기 전 문자열을 저장할 곳(2칸)
		ArrayList<char[]> encPlayFair = new ArrayList<char[]>(); // 암호화된 문자열 저장할 곳
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		String encStr = "";

		for (int i = 0; i < str.length(); i += 2) // arraylist 세팅
		{
			char[] tmpArr = new char[2]; // 2칸짜리 배열을 배열에 추가
			tmpArr[0] = str.charAt(i); // 0,2,4 짝수 인덱스로 접근 그 옆은 i + 1로 접근
			try {
				if (str.charAt(i) == str.charAt(i + 1)) // 글이 반복되면 x추가
				{
					tmpArr[1] = 'x';
					i--; // ?
				} else { // 글이 반복되지 않으면 두번째 인덱스에 문자 추가
					tmpArr[1] = str.charAt(i + 1);
				}
			} catch (StringIndexOutOfBoundsException e) {
				tmpArr[1] = 'x';
				oddFlag = true; // ?
			}
			playFair.add(tmpArr); // RX DL 이런 식의 문자열을 배열에 추가
		}

		/*
		 * for (int i = 0; i < playFair.size(); i++) // 배열 사이즈만큼 반복 {
		 * System.out.print(playFair.get(i)[0] + "" + playFair.get(i)[1] + " "); //
		 * arrayList를 가져올 땐 get } System.out.println();
		 */
		for (int i = 0; i < playFair.size(); i++) {

			char[] tmpArr = new char[2];
			for (int j = 0; j < alphabetBoard.length; j++) // 쌍자암호의 각각 위치체크
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

			if (x1 == x2) // 행이 같은경우
			{
				tmpArr[0] = alphabetBoard[x1][(y1 + 1) % 5];
				tmpArr[1] = alphabetBoard[x2][(y2 + 1) % 5];
			} else if (y1 == y2) // 열이 같은 경우
			{
				tmpArr[0] = alphabetBoard[(x1 + 1) % 5][y1];
				tmpArr[1] = alphabetBoard[(x2 + 1) % 5][y2];
			} else // 행, 열 모두 다른경우
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

	private static String strDecryption(String key, String str, String zCheck) { // 복호화

		ArrayList<char[]> playFair = new ArrayList<char[]>(); // 바꾸기 전 쌍자암호를 저장할 곳(암호)
		ArrayList<char[]> decPlayFair = new ArrayList<char[]>(); // 바꾼 후의 쌍자암호 저장할 곳(복호)
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0; // 쌍자 암호 두 글자의 각각의 행,열 값
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

			if (x1 == x2) // 행이 같은 경우 각각 바로 아래열 대입
			{
				tmpArr[0] = alphabetBoard[x1][(y1 + 4) % 5];
				tmpArr[1] = alphabetBoard[x2][(y2 + 4) % 5];
			} else if (y1 == y2) // 열이 같은 경우 각각 바로 옆 열 대입
			{
				tmpArr[0] = alphabetBoard[(x1 + 4) % 5][y1];
				tmpArr[1] = alphabetBoard[(x2 + 4) % 5][y2];
			} else // 행, 열 다른경우 각자 대각선에 있는 곳.
			{
				tmpArr[0] = alphabetBoard[x2][y1];
				tmpArr[1] = alphabetBoard[x1][y2];
			}

			decPlayFair.add(tmpArr);

		}

		for (int i = 0; i < decPlayFair.size(); i++) // 중복 문자열 돌려놓음
		{
			if (i != decPlayFair.size() - 1 && decPlayFair.get(i)[1] == 'x'
					&& decPlayFair.get(i)[0] == decPlayFair.get(i + 1)[0]) {
				decStr += decPlayFair.get(i)[0];
			} else {
				decStr += decPlayFair.get(i)[0] + "" + decPlayFair.get(i)[1];
			}
		}

		for (int i = 0; i < zCheck.length(); i++)// z위치 찾아서 q로 돌려놓음
		{
			if (zCheck.charAt(i) == '1')
				decStr = decStr.substring(0, i) + 'z' + decStr.substring(i + 1, decStr.length());

		}

		if (oddFlag)
			decStr = decStr.substring(0, decStr.length() - 1);

		// 띄어쓰기
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
		String keyForSet = ""; // 중복된 문자가 제거된 문자열을 저장할 문자열.
		boolean duplicationFlag = false; // 문자 중복을 체크하기 위한 flag 변수.
		int keyLengthCount = 0; // alphabetBoard에 keyForSet을 넣기 위한 count변수.

		key += "abcdefghijklmnopqrstuvwxyz"; // 키에 모든 알파벳을 추가.
		// 중복처리
		for (int i = 0; i < key.length(); i++) // 알파벳 수만큼 반복
		{
			for (int j = 0; j < keyForSet.length(); j++) {
				if (key.charAt(i) == keyForSet.charAt(j)) {
					duplicationFlag = true;
					break;
				}
			}
			if (!(duplicationFlag))
				keyForSet += key.charAt(i); // 중복이 없으면 key의 문자를 추가
			duplicationFlag = false;
		}
		// 배열에 대입
		for (int i = 0; i < alphabetBoard.length; i++) // 행(세로)
		{
			for (int j = 0; j < alphabetBoard[i].length; j++) // 열(가로)
			{
				alphabetBoard[i][j] = keyForSet.charAt(keyLengthCount++);
			}
		}
		// 배열에 대입
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
		JLabel jl = new JLabel("암호문");
		JLabel jl2 = new JLabel(encryption);
		TitledBorder tb = new TitledBorder(new LineBorder(Color.black));
		jl.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
		jl2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
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
		JLabel jl = new JLabel("복호문");
		JLabel jl2 = new JLabel(decryption); // decryption
		TitledBorder tb = new TitledBorder(new LineBorder(Color.black));
		jl.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
		jl2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 15));
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
