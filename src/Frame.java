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
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 450;
	
	JLabel queryImage = new JLabel();
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
		
		//ͼƬ
		queryImage.setPreferredSize(new Dimension(160,160));
		queryImage.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		//buttons
		JButton uploadButton = new JButton("Upload");
		JButton search = new JButton("Search");
		uploadButton.addActionListener(this);
		
		//ComnoBox
		JComboBox histogramBox = new JComboBox(histogramList);
		histogramBox.addItemListener(new ItemListener() 
		{
            @Override
            public void itemStateChanged(ItemEvent e) 
            {
                if(e.getStateChange() == 1) {
                    //label.setText(ie.getItem().toString());
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
                    //label.setText(ie.getItem().toString());
                	System.out.println(ie.getItem().toString());
                }
            }
        });
		
		//Panels
		JPanel basicPanel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		basicPanel.setLayout(new BorderLayout());
		setContentPane(basicPanel);
		panel1.setLayout(new FlowLayout());
		basicPanel.add(panel1, "North");
		basicPanel.add(panel2, "South");
		panel1.add(queryImage);
		panel1.add(uploadButton);
		panel1.add(histogramBox);
		panel1.add(distanceBox);
		panel1.add(search);
/*		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 4;
		c.weighty = 4;
		
		addComponent(basicPanel, uploadButton, c, 0, 0, 1, 1);
		addComponent(basicPanel, search, c, 1, 0, 1, 1);
		addComponent(basicPanel, histogramBox, c, 0, 1, 1, 1);
		addComponent(basicPanel, distanceBox, c, 1, 1, 1, 1);*/
		
		pack();
	}
	
	//xָ�ؼ�λ�ڵڼ���
	//yָ�ؼ�λ�ڵڼ���
	//wָ�ؼ���Ҫռ����
	//hָ�ؼ���Ҫռ����
	public void addComponent(JPanel panel, Component c, GridBagConstraints constraints, int x, int y, int w, int h)
	{
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		panel.add(c, constraints);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser("c:\\");
		FileNameExtensionFilter filter =new FileNameExtensionFilter("Image Files", "jpg","jpeg","gif", "bmp", "png");  
        fileChooser.setFileFilter(filter);
        int result=fileChooser.showOpenDialog(this);
        if(result==JFileChooser.APPROVE_OPTION)
	    {  
        	String picPath = fileChooser.getSelectedFile().getPath();  
        	System.out.println(picPath);
        	//Pattern pattern = Pattern.compile("/");
        	//String[] strs = pattern.split(picPath);
        	//picName = strs[strs.length - 1];
        	picName = picPath;
        	//System.out.println(picName);
	        ImageIcon icon = new ImageIcon(picPath);  
	        //���ȱ�����  
	        Image temp = icon.getImage().getScaledInstance(160, 160, icon.getImage().SCALE_DEFAULT);  
	        icon = new ImageIcon(temp);
	        queryImage.setIcon(icon);  
	    }  
	}

}

