import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JPanel panel;
	private JButton btnFill;
	private JButton btnRun;
	private JTextArea textField;

	public GUI() {
	}

	public void Run() {
		frame.setVisible(true);
	}

	public void ie() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 781, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 747, 607);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnFill = new JButton("Fill");
		btnFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(FileOptions.getWhole(JOptionPane.showInputDialog("Enter Path: ")));
			}
		});
		btnFill.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnFill.setBounds(10, 10, 102, 26);
		panel.add(btnFill);

		btnRun = new JButton("Run Algorithms");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] temp = new String[6][3];
				String colname[] = { "Type", "Score", "Level" };

				for (int i = 0; i < temp[0].length; i++) {
					for (int u = 0; u < temp.length; u++) {
						temp[u][i] = "";
					}
				}

				temp[0][0] = "Flesch";
				temp[0][1] = String.valueOf(new TextAnalyzer(textField.getText(), true).Flesch());
				temp[0][2] = String
						.valueOf(new Index().findIndex("Flesch", new TextAnalyzer(textField.getText(), true).Flesch()));
				temp[1][0] = "Flesch_Kincaid";
				temp[1][1] = String.valueOf(new TextAnalyzer(textField.getText(), true).Flesch_Kincaid());
				temp[1][2] = String.valueOf(new Index().findIndex("Flesch_Kincaid",
						new TextAnalyzer(textField.getText(), true).Flesch_Kincaid()));
				temp[2][0] = "SMOG";
				temp[2][1] = String.valueOf(new TextAnalyzer(textField.getText(), true).SMOG());
				temp[2][2] = String
						.valueOf(new Index().findIndex("SMOG", new TextAnalyzer(textField.getText(), true).SMOG()));
				temp[3][0] = "GunningFog";
				temp[3][1] = String.valueOf(new TextAnalyzer(textField.getText(), true).GunningFog());
				temp[3][2] = String.valueOf(
						new Index().findIndex("GunningFog", new TextAnalyzer(textField.getText(), true).GunningFog()));
				temp[4][0] = "Coleman_Liau";
				temp[4][1] = String.valueOf(new TextAnalyzer(textField.getText(), true).Coleman_Liau());
				temp[4][2] = String.valueOf(new Index().findIndex("Coleman_Liau",
						new TextAnalyzer(textField.getText(), true).Coleman_Liau()));
				temp[5][0] = String.valueOf((new TextAnalyzer(textField.getText(), true).getSyllables()));
				temp[5][1] = String.valueOf((new TextAnalyzer(textField.getText(), true).getWords()));
				temp[5][2] = String.valueOf((new TextAnalyzer(textField.getText(), true).getSentences()));
				
				
				
				JFrame g = new JFrame();
				JTable j;

				j = new JTable(temp, colname);
				j.setBounds(30, 40, 200, 300);
				g.setTitle("Algorithms:");
				JScrollPane sp = new JScrollPane(j);
				g.add(sp);
				g.setSize(450, 160);
				g.setVisible(true);
			}
		});
		btnRun.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnRun.setBounds(122, 10, 137, 26);
		panel.add(btnRun);

		textField = new JTextArea();
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setBounds(10, 46, 727, 551);
		panel.add(textField);
		textField.setColumns(10);
	}
}
