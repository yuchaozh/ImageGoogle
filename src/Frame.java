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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.regex.Pattern;

class Frame extends JFrame implements ActionListener
{
	public static final int DEFAULT_WIDTH = 490;
	public static final int DEFAULT_HEIGHT = 490;
	
	JLabel queryImage = new JLabel();
	JLabel Image1 = new JLabel();
	JLabel Image2 = new JLabel();
	JLabel Image3 = new JLabel();
	JLabel Image4 = new JLabel();
	JLabel Image5 = new JLabel();
	JLabel Image6 = new JLabel();
	JLabel Image7 = new JLabel();
	JLabel Image8 = new JLabel();
	JLabel Image9 = new JLabel();
	JLabel Image10 = new JLabel();
	JLabel Image11 = new JLabel();
	JLabel Image12 = new JLabel();
	JLabel Image13 = new JLabel();
	JLabel Image14 = new JLabel();
	JLabel Image15 = new JLabel();
	JLabel Image16 = new JLabel();
	JLabel Image17 = new JLabel();
	JLabel Image18 = new JLabel();
	JLabel Image19 = new JLabel();
	JLabel Image20 = new JLabel();
	JLabel Image21 = new JLabel();
	JLabel Image22 = new JLabel();
	JLabel Image23 = new JLabel();
	JLabel Image24 = new JLabel();
	
	JButton offset;
	JButton previous;
	JButton next;
	JPanel basicPanel;
	JPanel panel2;
	JPanel panel3;
	
	String picName = "";
	String histogram = "";
	String distance = "";
	
	public Frame() 
	{
		String[] distanceList = {"", "Euclidean distance", "Intersection distance"};
		String[] histogramList = {"", "Global color histogram", "Segment color histogram"};
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("ImageGoogle");
		setResizable(true);
		
		//图片
		queryImage.setPreferredSize(new Dimension(160,160));
		queryImage.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image1.setPreferredSize(new Dimension(160,160));
		Image1.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image2.setPreferredSize(new Dimension(160,160));
		Image2.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image3.setPreferredSize(new Dimension(160,160));
		Image3.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image4.setPreferredSize(new Dimension(160,160));
		Image4.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image5.setPreferredSize(new Dimension(160,160));
		Image5.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image6.setPreferredSize(new Dimension(160,160));
		Image6.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image7.setPreferredSize(new Dimension(160,160));
		Image7.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image8.setPreferredSize(new Dimension(160,160));
		Image8.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image9.setPreferredSize(new Dimension(160,160));
		Image9.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image11.setPreferredSize(new Dimension(160,160));
		Image11.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image12.setPreferredSize(new Dimension(160,160));
		Image12.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image13.setPreferredSize(new Dimension(160,160));
		Image13.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image14.setPreferredSize(new Dimension(160,160));
		Image14.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image15.setPreferredSize(new Dimension(160,160));
		Image15.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image16.setPreferredSize(new Dimension(160,160));
		Image16.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image17.setPreferredSize(new Dimension(160,160));
		Image17.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image18.setPreferredSize(new Dimension(160,160));
		Image18.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image10.setPreferredSize(new Dimension(160,160));
		Image10.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image19.setPreferredSize(new Dimension(160,160));
		Image19.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image20.setPreferredSize(new Dimension(160,160));
		Image20.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image21.setPreferredSize(new Dimension(160,160));
		Image21.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image22.setPreferredSize(new Dimension(160,160));
		Image22.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image23.setPreferredSize(new Dimension(160,160));
		Image23.setBorder(BorderFactory.createLineBorder(Color.gray));
		Image24.setPreferredSize(new Dimension(160,160));
		Image24.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//buttons
		JButton uploadButton = new JButton("Upload");
		JButton search = new JButton("Search");
		uploadButton.addActionListener(this);
		search.addActionListener(this);
		
		offset = new JButton("offset");
		previous = new JButton("Previous");
		next = new JButton("Next");
		previous.addActionListener(this);
		next.addActionListener(this);
		
		//ComnoBox
		JComboBox histogramBox = new JComboBox(histogramList);
		histogramBox.addItemListener(new ItemListener() 
		{
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                if(e.getStateChange() == 1) {
                	histogram = e.getItem().toString();
                	System.out.println(e.getItem().toString());
                }
            }
         
        });
		
		JComboBox distanceBox = new JComboBox(distanceList);
		distanceBox.addItemListener(new ItemListener() 
		{
            @Override
            public void itemStateChanged(ItemEvent ie) 
            {
                if(ie.getStateChange() == 1) {
                	distance = ie.getItem().toString();
                	System.out.println(ie.getItem().toString());
                }
            }
        });
		
		//Panels
		basicPanel = new JPanel();
		JPanel panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		JPanel bottom = new JPanel();
		
		basicPanel.setLayout(new BorderLayout());
		setContentPane(basicPanel);
		panel1.setLayout(new FlowLayout());
		basicPanel.add(panel1, "North");
		basicPanel.add(panel2, "Center");
		basicPanel.add(bottom, "South");
		panel1.add(queryImage);
		panel1.add(uploadButton);
		panel1.add(histogramBox);
		panel1.add(distanceBox);
		panel1.add(search);
		
		//使用GridLayout时无法固定JLabel的大小，大小会随着窗口的改变而改变,
		//所以只能使用GridBagLayout
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel2.setBorder(BorderFactory.createTitledBorder("Result"));
		Image2.setText("Image2");
		Image1.setText("Image1");
		Image3.setText("Image3");
		Image4.setText("Image4");
		Image5.setText("Image5");
		Image6.setText("Image6");
		Image7.setText("Image7");
		Image8.setText("Image8");
		Image9.setText("Image9");
		Image10.setText("Image10");
		Image11.setText("Image11");
		Image12.setText("Image12");
		addComponent(panel2, Image1, con, 0, 0, 1 ,1);
		addComponent(panel2, Image2, con, 1, 0, 1 ,1);
		addComponent(panel2, Image3, con, 2, 0, 1 ,1);
		addComponent(panel2, Image4, con, 3, 0, 1 ,1);
		addComponent(panel2, Image5, con, 0, 1, 1 ,1);
		addComponent(panel2, Image6, con, 1, 1, 1 ,1);
		addComponent(panel2, Image7, con, 2, 1, 1 ,1);
		addComponent(panel2, Image8, con, 3, 1, 1 ,1);
		addComponent(panel2, Image9, con, 0, 2, 1 ,1);
		addComponent(panel2, Image10, con, 1, 2, 1 ,1);
		addComponent(panel2, Image11, con, 2, 2, 1 ,1);
		addComponent(panel2, Image12, con, 3, 2, 1 ,1);
		
		
		panel3.setLayout(new GridBagLayout());
		panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel3.setBorder(BorderFactory.createTitledBorder("Result"));		
		Image13.setText("Image13");
		Image14.setText("Image14");
		Image15.setText("Image15");
		Image16.setText("Image16");
		Image17.setText("Image17");
		Image18.setText("Image18");
		Image19.setText("Image19");
		Image20.setText("Image20");
		Image21.setText("Image21");
		Image22.setText("Image22");
		Image23.setText("Image23");
		Image24.setText("Image24");
		addComponent(panel3, Image13, con, 0, 0, 1 ,1);
		addComponent(panel3, Image14, con, 1, 0, 1 ,1);
		addComponent(panel3, Image15, con, 2, 0, 1 ,1);
		addComponent(panel3, Image16, con, 3, 0, 1 ,1);
		addComponent(panel3, Image17, con, 0, 1, 1 ,1);
		addComponent(panel3, Image18, con, 1, 1, 1 ,1);
		addComponent(panel3, Image19, con, 2, 1, 1 ,1);
		addComponent(panel3, Image20, con, 3, 1, 1 ,1);
		addComponent(panel3, Image21, con, 0, 2, 1 ,1);
		addComponent(panel3, Image22, con, 1, 2, 1 ,1);
		addComponent(panel3, Image23, con, 2, 2, 1 ,1);
		addComponent(panel3, Image24, con, 3, 2, 1 ,1);
		
		
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
	
	//x指控件位于第几列
	//y指控件位于第几行
	//w指控件需要占几列
	//h指控件需要占几行
	public void addComponent(JPanel panel, Component c, GridBagConstraints constraints, int x, int y, int w, int h)
	{
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
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Upload"))
	    {
			JFileChooser fileChooser = new JFileChooser("c:\\");
			FileNameExtensionFilter filter =new FileNameExtensionFilter("Image Files", "jpg","jpeg","gif", "bmp", "png");  
			fileChooser.setFileFilter(filter);
			int result=fileChooser.showOpenDialog(this);
			if(result==JFileChooser.APPROVE_OPTION)
			{  
				String picPath = fileChooser.getSelectedFile().getPath();  
				//Pattern pattern = Pattern.compile("/");
				//String[] strs = pattern.split(picPath);
				//picName = strs[strs.length - 1];
				picName = picPath;
				//System.out.println(picName);
				ImageIcon icon = new ImageIcon(picPath);  
				//按等比缩放  
				Image temp = icon.getImage().getScaledInstance(160, 160, icon.getImage().SCALE_DEFAULT);  
				icon = new ImageIcon(temp);
				queryImage.setIcon(icon);  
			}
	    }
	
		if (e.getActionCommand().equals("Search"))
		{
			if (picName == "")
			{
				javax.swing.JOptionPane.showMessageDialog(null,"Please choose one image to query!");
			}
			else
			{
				System.out.println("QUERY!");
				System.out.println(picName);
				System.out.println(histogram);
				System.out.println(distance);
			}
		}
		
		if (e.getActionCommand().equals("Previous"))
		{
			previous.setEnabled(false);
			next.setEnabled(true);
			basicPanel.remove(panel3);
			basicPanel.add(panel2);
			basicPanel.revalidate();
			basicPanel.repaint();
		}
		
		if (e.getActionCommand().equals("Next"))
		{
			previous.setEnabled(true);
			next.setEnabled(false);
			basicPanel.remove(panel2);
			basicPanel.add(panel3);
			basicPanel.revalidate();
			basicPanel.repaint();
		}
    }
}

