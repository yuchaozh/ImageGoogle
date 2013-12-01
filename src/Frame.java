//package process;

import matlabTool.*;
import com.mathworks.toolbox.javabuilder.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.regex.Pattern;

class Frame extends JFrame implements ActionListener {
	public static final int DEFAULT_WIDTH = 490;
	public static final int DEFAULT_HEIGHT = 490;

	JLabel intro = new JLabel();
	JLabel introPic = new JLabel();
	JLabel queryImage = new JLabel();
	JLabel[] Image = new JLabel[24];

	JButton offset;
	JButton previous;
	JButton next;
	JPanel basicPanel;
	JPanel panel2;
	JPanel panel3;
	JPanel introduction;
	JPanel bottom;

	String picName = "";
	String histogram = "";
	String distance = "";
	String databasePath = "";

	/* connect the matlab */
	Tool tool = null;//
	private Object[] result = null;//

	public Frame() {
		String[] distanceList = { "", "Euclidean distance",
				"Intersection distance" };
		String[] histogramList = { "", "Global color histogram",
				"Segment color histogram" };

		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("ImageGoogle");
		setResizable(true);

		Toolkit toolkit = this.getToolkit();
		// Image img=toolkit.createImage(
		// this.getClass().getResource("/image/icon.png"));
		// this.setIconImage(img);//set the demo image

		// ��剧��
		queryImage.setPreferredSize(new Dimension(160, 160));
		queryImage.setBorder(BorderFactory.createLineBorder(Color.gray));
		for (int i = 0; i < Image.length; i++) {
			Image[i] = new JLabel();
			Image[i].setPreferredSize(new Dimension(160, 160));
			Image[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			// Image[i].setText("Image"+i);
		}

		intro = new JLabel("welcome");

		// buttons
		JButton uploadButton = new JButton("Upload");
		JButton databaseButton = new JButton("Database");
		JLabel query = new JLabel("Query Image: ");
		JLabel databse = new JLabel("Database: ");
		JButton search = new JButton("Search");
		databaseButton.addActionListener(this);
		uploadButton.addActionListener(this);
		search.addActionListener(this);

		offset = new JButton("offset");
		previous = new JButton("Previous");
		next = new JButton("Next");
		previous.addActionListener(this);
		next.addActionListener(this);

		// ComnoBox
		JComboBox histogramBox = new JComboBox(histogramList);
		histogramBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					histogram = e.getItem().toString();
					System.out.println(e.getItem().toString());
				}
			}

		});

		JComboBox distanceBox = new JComboBox(distanceList);
		distanceBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == 1) {
					distance = ie.getItem().toString();
					System.out.println(ie.getItem().toString());
				}
			}
		});

		// Panels
		basicPanel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel1_uper = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		introduction = new JPanel();
		bottom = new JPanel();

		basicPanel.setLayout(new BorderLayout());
		panel1_uper.setLayout(new GridBagLayout());
		setContentPane(basicPanel);
		panel1.setLayout(new FlowLayout());
		basicPanel.add(introduction, "Center");
		basicPanel.add(panel1, "North");
		// basicPanel.add(panel2, "Center");
		basicPanel.add(bottom, "South");
		panel1.add(queryImage);
		panel1.add(panel1_uper);
		GridBagConstraints co = new GridBagConstraints();
		addComponent(panel1_uper, query, co, 0, 0, 1, 1);
		addComponent(panel1_uper, uploadButton, co, 1, 0, 1, 1);
		addComponent(panel1_uper, databse, co, 0, 1, 1, 1);
		addComponent(panel1_uper, databaseButton, co, 1, 1, 1, 1);
		// panel1.add(databaseButton);
		// panel1.add(uploadButton);
		panel1.add(histogramBox);
		panel1.add(distanceBox);
		panel1.add(search);
		introduction.setLayout(new BorderLayout());
		introduction.setPreferredSize(new Dimension(532, 765));// ������纭锋�烽����ゆ�烽��锟�,�����ゆ�烽����ゆ��JPanel���渚ヨ揪��峰��
		introduction.add(intro, "East");
		introduction.add(introPic, "West");

		// 浣块����ゆ��GridLayout��堕�������ゆ�烽�����璁规��JLabel���渚ヨ揪��峰�������ゆ�烽����ゆ�峰�������ゆ�烽����ゆ�烽�����杈炬�烽����������规����烽��渚ユ�����,
		// �����ゆ�烽����ゆ�峰�������ゆ�蜂娇�����ゆ��GridBagLayout
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel2.setBorder(BorderFactory.createTitledBorder("Result"));
		addComponent(panel2, Image[0], con, 0, 0, 1, 1);
		addComponent(panel2, Image[1], con, 1, 0, 1, 1);
		addComponent(panel2, Image[2], con, 2, 0, 1, 1);
		addComponent(panel2, Image[3], con, 3, 0, 1, 1);
		addComponent(panel2, Image[4], con, 0, 1, 1, 1);
		addComponent(panel2, Image[5], con, 1, 1, 1, 1);
		addComponent(panel2, Image[6], con, 2, 1, 1, 1);
		addComponent(panel2, Image[7], con, 3, 1, 1, 1);
		addComponent(panel2, Image[8], con, 0, 2, 1, 1);
		addComponent(panel2, Image[9], con, 1, 2, 1, 1);
		addComponent(panel2, Image[10], con, 2, 2, 1, 1);
		addComponent(panel2, Image[11], con, 3, 2, 1, 1);

		panel3.setLayout(new GridBagLayout());
		panel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel3.setBorder(BorderFactory.createTitledBorder("Result"));
		addComponent(panel3, Image[12], con, 0, 0, 1, 1);
		addComponent(panel3, Image[13], con, 1, 0, 1, 1);
		addComponent(panel3, Image[14], con, 2, 0, 1, 1);
		addComponent(panel3, Image[15], con, 3, 0, 1, 1);
		addComponent(panel3, Image[16], con, 0, 1, 1, 1);
		addComponent(panel3, Image[17], con, 1, 1, 1, 1);
		addComponent(panel3, Image[18], con, 2, 1, 1, 1);
		addComponent(panel3, Image[19], con, 3, 1, 1, 1);
		addComponent(panel3, Image[20], con, 0, 2, 1, 1);
		addComponent(panel3, Image[21], con, 1, 2, 1, 1);
		addComponent(panel3, Image[22], con, 2, 2, 1, 1);
		addComponent(panel3, Image[23], con, 3, 2, 1, 1);

		bottom.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 4;
		c.weighty = 4;

		addComponent(bottom, offset, c, 0, 0, 1, 1);
		offset.setVisible(false);
		addComponent(bottom, previous, c, 1, 0, 1, 1);
		addComponent(bottom, next, c, 2, 0, 1, 1);

		previous.setEnabled(false);
		pack();
	}

	// x���������纭锋�蜂��������绗�纭锋�烽����ゆ��
	// y���������纭锋�蜂��������绗�纭锋�烽����ゆ��
	// w���������纭锋�烽����ゆ�疯����������ゆ�烽����ゆ��
	// h���������纭锋�烽����ゆ�疯����������ゆ�烽����ゆ��
	public void addComponent(JPanel panel, Component c,
			GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.insets.bottom = 4;
		constraints.insets.left = 4;
		constraints.insets.right = 4;
		constraints.insets.top = 4;
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		panel.add(c, constraints);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Upload")) {
			JFileChooser fileChooser = new JFileChooser(
					"G:/EclipseWworkspace/GoogleImage/src/image");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Image Files", "jpg", "jpeg", "gif", "bmp", "png");
			fileChooser.setFileFilter(filter);
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				String picPath = fileChooser.getSelectedFile().getPath();
				Pattern pattern = Pattern.compile("/");
				String[] strs = pattern.split(picPath);
				picName = strs[strs.length - 1];
				picName = picPath;
				System.out.println(picName);
				ImageIcon icon = new ImageIcon(picPath);
				// �����ゆ�烽��楗烘����烽����ゆ�烽����ゆ��
				Image temp = icon.getImage().getScaledInstance(160, 160,
						icon.getImage().SCALE_DEFAULT);
				icon = new ImageIcon(temp);
				queryImage.setIcon(icon);
			}
		}

		if (e.getActionCommand().equals("Database")) {
			JFileChooser directoryChooser = new JFileChooser("c:/");
			directoryChooser.setFileSelectionMode(1);// 璁惧�������介����╁�版��浠跺す
			int result = directoryChooser.showOpenDialog(this);// 姝ゅ�ユ�����寮����浠堕����╁�ㄧ����㈢��瑙����璇����
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = directoryChooser.getSelectedFile();// f涓洪����╁�扮�����浠�
				databasePath = f.getAbsolutePath();
				// System.out.println(f.getAbsolutePath());
			}
		}

		if (e.getActionCommand().equals("Search")) {
			if (picName == "") {
				javax.swing.JOptionPane.showMessageDialog(null,
						"Please choose one image to query!");
			} 
			else if ((histogram == "")||(distance == ""))
			{
				javax.swing.JOptionPane.showMessageDialog(null,
						"Please choose the query methods!");
			}	
			else
			{
				try {
					tool = new Tool();
					result = tool.Cbir(1, 24, picName);

					System.out.println("the result is" + result[0]);
					String namesString = result[0].toString();
					String[] sortFileName = namesString.split(",");
					// System.out.println("sorted filenames are"+sortFileName[1]);

					for (int i = 0; i < 24; i++) {
						// ImageIcon icon = new ImageIcon("pic.png");

						String qString = "/image/" + sortFileName[i];
						ImageIcon icon = new ImageIcon(this.getClass()
								.getResource(qString));
						// �����ゆ�烽��楗烘����烽����ゆ�烽����ゆ��
						Image temp = icon.getImage().getScaledInstance(160,
								160, icon.getImage().SCALE_DEFAULT);
						icon = new ImageIcon(temp);
						Image[i].setIcon(icon);

					}
				} catch (MWException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// for (int i = 0; i < 24; i++)
				// {
				// // ImageIcon icon = new ImageIcon("pic.png");
				// String sortFileName="";
				// String qString="/image/pic.png"+sortFileName;
				// ImageIcon icon = new
				// ImageIcon(this.getClass().getResource(qString));
				// //�����ゆ�烽��楗烘����烽����ゆ�烽����ゆ��
				// Image temp = icon.getImage().getScaledInstance(160, 160,
				// icon.getImage().SCALE_DEFAULT);
				// icon = new ImageIcon(temp);
				// Image[i].setIcon(icon);
				//
				// }

				basicPanel.remove(introduction);
				basicPanel.add(panel2, "Center");
				basicPanel.add(bottom, "South");
				basicPanel.revalidate();
				basicPanel.repaint();
				System.out.println(picName);
				System.out.println(histogram);
				System.out.println(distance);
			}
		}

		if (e.getActionCommand().equals("Previous")) {
			previous.setEnabled(false);
			next.setEnabled(true);
			basicPanel.remove(panel3);
			basicPanel.add(panel2);
			basicPanel.revalidate();
			basicPanel.repaint();
		}

		if (e.getActionCommand().equals("Next")) {
			previous.setEnabled(true);
			next.setEnabled(false);
			basicPanel.remove(panel2);
			basicPanel.add(panel3);
			basicPanel.revalidate();
			basicPanel.repaint();
		}
	}
}
