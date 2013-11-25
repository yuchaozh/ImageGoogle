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
		for (int i = 0; i < Image.length; i++)
		{
			Image[i] = new JLabel();
			Image[i].setPreferredSize(new Dimension(160,160));
			Image[i].setBorder(BorderFactory.createLineBorder(Color.gray));
			Image[i].setText("Image"+i);
		}
		
		intro = new JLabel("welcome");
		
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
		introduction = new JPanel();
		bottom = new JPanel();
		
		basicPanel.setLayout(new BorderLayout());
		setContentPane(basicPanel);
		panel1.setLayout(new FlowLayout());
		basicPanel.add(introduction, "Center");
		basicPanel.add(panel1, "North");
		//basicPanel.add(panel2, "Center");
		basicPanel.add(bottom, "South");
		panel1.add(queryImage);
		panel1.add(uploadButton);
		panel1.add(histogramBox);
		panel1.add(distanceBox);
		panel1.add(search);
		introduction.setLayout(new BorderLayout());
		introduction.setPreferredSize(new Dimension(532, 765));//关键代码,设置JPanel的大小  
		introduction.add(intro, "East");
		introduction.add(introPic, "West");
		
		
		//使用GridLayout时无法固定JLabel的大小，大小会随着窗口的改变而改变,
		//所以只能使用GridBagLayout
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel2.setBorder(BorderFactory.createTitledBorder("Result"));
		addComponent(panel2, Image[0], con, 0, 0, 1 ,1);
		addComponent(panel2, Image[1], con, 1, 0, 1 ,1);
		addComponent(panel2, Image[2], con, 2, 0, 1 ,1);
		addComponent(panel2, Image[3], con, 3, 0, 1 ,1);
		addComponent(panel2, Image[4], con, 0, 1, 1 ,1);
		addComponent(panel2, Image[5], con, 1, 1, 1 ,1);
		addComponent(panel2, Image[6], con, 2, 1, 1 ,1);
		addComponent(panel2, Image[7], con, 3, 1, 1 ,1);
		addComponent(panel2, Image[8], con, 0, 2, 1 ,1);
		addComponent(panel2, Image[9], con, 1, 2, 1 ,1);
		addComponent(panel2, Image[10], con, 2, 2, 1 ,1);
		addComponent(panel2, Image[11], con, 3, 2, 1 ,1);
		
		
		panel3.setLayout(new GridBagLayout());
		panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel3.setBorder(BorderFactory.createTitledBorder("Result"));		
		addComponent(panel3, Image[12], con, 0, 0, 1 ,1);
		addComponent(panel3, Image[13], con, 1, 0, 1 ,1);
		addComponent(panel3, Image[14], con, 2, 0, 1 ,1);
		addComponent(panel3, Image[15], con, 3, 0, 1 ,1);
		addComponent(panel3, Image[16], con, 0, 1, 1 ,1);
		addComponent(panel3, Image[17], con, 1, 1, 1 ,1);
		addComponent(panel3, Image[18], con, 2, 1, 1 ,1);
		addComponent(panel3, Image[19], con, 3, 1, 1 ,1);
		addComponent(panel3, Image[20], con, 0, 2, 1 ,1);
		addComponent(panel3, Image[21], con, 1, 2, 1 ,1);
		addComponent(panel3, Image[22], con, 2, 2, 1 ,1);
		addComponent(panel3, Image[23], con, 3, 2, 1 ,1);
		
		
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
				for (int i = 0; i < 24; i++)
				{
/*					ImageIcon icon = new ImageIcon("pic.png");  
					//按等比缩放  
					Image temp = icon.getImage().getScaledInstance(160, 160, icon.getImage().SCALE_DEFAULT);  
					icon = new ImageIcon(temp);
					Image[i].setIcon(icon);*/
					
				}
				
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

