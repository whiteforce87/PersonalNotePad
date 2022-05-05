package lab2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;


public class SimpleNotePad extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mnOpen;
	private JMenuItem mnSave;
	private JPanel panel;
	private JLabel info;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField wordCount;
	private JTextField coordinates;
	private JLabel mouse;
	private JMenuItem exit;
	private JButton bold;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JRadioButton toUpperCase;
	private JRadioButton rdbtnNewRadioButton;
	private JTextField fontSize;
	private JLabel fontSizeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleNotePad frame = new SimpleNotePad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimpleNotePad() {
		initGUI();
	}

	private void initGUI() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				do_textArea_mouseMoved(e);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 366);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		mnOpen = new JMenuItem("Open");
		mnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mnOpen_actionPerformed(e);
			}
		});
		mnNewMenu.add(mnOpen);

		mnSave = new JMenuItem("Save");
		mnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mnSave_actionPerformed(e);
			}
		});
		mnNewMenu.add(mnSave);

		exit = new JMenuItem("Exit");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				do_exit_mousePressed(e);
			}
		});
		mnNewMenu.add(exit);

		bold = new JButton("B");
		bold.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				do_bold_mousePressed(e);
			}
		});

		btnNewButton_2 = new JButton("P");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				do_btnNewButton_2_mousePressed(e);
			}
		});
		btnNewButton_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		menuBar.add(btnNewButton_2);

		bold.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		menuBar.add(bold);

		btnNewButton = new JButton("I");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				do_btnNewButton_mousePressed(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		menuBar.add(btnNewButton);

		toUpperCase = new JRadioButton("UpperCase");
		toUpperCase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_centered_mouseClicked(e);
			}
		});

		fontSizeLabel = new JLabel("FontSize:");
		menuBar.add(fontSizeLabel);

		fontSize = new JTextField();
		fontSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_fontSize_actionPerformed(e);
			}
		});
		menuBar.add(fontSize);
		fontSize.setColumns(3);
		menuBar.add(toUpperCase);

		rdbtnNewRadioButton = new JRadioButton("LowerCase");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_rdbtnNewRadioButton_mouseClicked(e);
			}
		});
		menuBar.add(rdbtnNewRadioButton);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		mouse = new JLabel("Mouse:");
		panel.add(mouse);

		coordinates = new JTextField();
		panel.add(coordinates);
		coordinates.setColumns(8);

		info = new JLabel("Info:");

		panel.add(info);

		wordCount = new JTextField();

		panel.add(wordCount);
		wordCount.setColumns(25);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				do_textArea_mousePressed(e);
			}
		});

		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				do_textArea_keyTyped(e);
			}
		});

		textArea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				do_textArea_mouseMoved(e);
			}
		});
		scrollPane.setViewportView(textArea);
	}

	protected void do_mnOpen_actionPerformed(ActionEvent e) {

		// JOptionPane.showMessageDialog(this, "Open menu cliked!");

		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File fileSelected = fileChooser.getSelectedFile();

			List<String> lines;
			try {
				lines = Files.readAllLines(Paths.get(fileSelected.getAbsolutePath()));
				lines.forEach(line -> textArea.setText(textArea.getText() + "\n" + line));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else {

		}

	}

	protected void do_mnSave_actionPerformed(ActionEvent e) {

		// JOptionPane.showMessageDialog(this, "Save menu cliked!");

		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(selectedFile.getAbsolutePath()),
					StandardOpenOption.CREATE);) {
				writer.write(textArea.getText());
				JOptionPane.showMessageDialog(this, "File Saved!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	protected void do_textArea_mouseMoved(MouseEvent e) {
		coordinates.setText("X: " + e.getX() + " Y: " + e.getY());
	}

	protected void do_textArea_keyTyped(KeyEvent e) {
		String[] words = textArea.getText().split("\\s+");
		int lineCount = textArea.getLineCount();
		int letterCount = textArea.getText().length();
		wordCount.setText(words.length + " words, " + lineCount + " lines and " + letterCount + " characters.");
	}

	protected void do_exit_mousePressed(MouseEvent e) {
		System.exit(0);
	}

	protected void do_bold_mousePressed(MouseEvent e) {

		textArea.setFont(getFont().deriveFont(Font.BOLD, textArea.getFont().getSize()));
	}

	protected void do_btnNewButton_mousePressed(MouseEvent e) {
		textArea.setFont(getFont().deriveFont(Font.ITALIC, textArea.getFont().getSize()));
	}

	protected void do_btnNewButton_2_mousePressed(MouseEvent e) {
		textArea.setFont(getFont().deriveFont(Font.PLAIN, textArea.getFont().getSize()));
	}

	protected void do_textArea_mousePressed(MouseEvent e) {
		textArea.setSelectedTextColor(getForeground().BLUE);
	}

	protected void do_centered_mouseClicked(MouseEvent e) {
		String text = textArea.getText();
		String text2 = text.toUpperCase();
		textArea.setText(text2);

	}

	protected void do_rdbtnNewRadioButton_mouseClicked(MouseEvent e) {
		String text = textArea.getText();
		String text2 = text.toLowerCase();
		textArea.setText(text2);
	}

	protected void do_fontSize_actionPerformed(ActionEvent e) {
		if (fontSize.getText() == "") {
			int size = textArea.getFont().getSize();
			fontSize.setText(String.valueOf(size));
		} else {
			textArea.setFont(new Font(getName(), NORMAL, Integer.valueOf(fontSize.getText())));
		}
	}
}
