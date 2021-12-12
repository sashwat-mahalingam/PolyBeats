import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.applet.*;

import java.io.File;

import java.net.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

import javax.sound.sampled.Line;

import javax.sound.sampled.LineEvent;

import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JFileChooser;


import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;


class RectangleBlinker extends JComponent {

	private int x,y,w,h;
	private Color c;
	private char shape;

	public RectangleBlinker(int x, int y, int w, int h, Color c, char shape) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
		this.shape = shape;

	}

	public void paintComponent(Graphics g) {

		g.setColor(c);

		if(shape == 'r') {
			g.fillRect(x, y,w,h);
		}
		else if (shape == 'o') {
			g.fillOval(x, y,w,h);
		}
	}
}

class WesternBeatPlane extends JComponent {
        
        private WesternBeatPlane plane = this;

        private JPanel panel;

	private WesternMetronome wMet;

	private volatile boolean isRunning;
        
        private long topRect;
                
        private long botRect;



		 
	public WesternBeatPlane(WesternMetronome m, JPanel p,long topRect, long botRect) {

                panel = p;
		isRunning = false;
		wMet = m;
                this.topRect = topRect;
                this.botRect = botRect;
                
	}


	public void paintComponent(Graphics g) {
		this.setVisible(true);
		g.setColor(Color.WHITE);
		g.fillRect(10,119,864,507);
		g.setColor(Color.BLUE);

		for(int i = 0; i<=wMet.getMeasure(); i++) {

                    
                        if(i == topRect) {
                        
                         g.setColor(Color.BLACK);
                        
                        }
			g.fillRect(20+90*i, 160, 10, 10);
                        
                        if(i==topRect) {
                        
                            g.setColor(Color.BLUE);
                        
                        }
		}

		g.setColor(Color.GREEN);

		for(int i = 0; i<wMet.getCount(); i++) {

                    
                        if(i==botRect) {
                        
                        g.setColor(Color.ORANGE);
                        }
			g.fillOval((int)(20+90*i/(1.0*wMet.getCount()/wMet.getMeasure())),140,5,5);

                        if(i==botRect) {
                        
                            g.setColor(Color.GREEN);
                        
                        }

		}
            
                

	}

	public void start() {

		isRunning = true;
                
	}

	public void stop() {

		isRunning = false;


	}

	public boolean running() {


		return isRunning;
	}


}

class CarnaticBeatPlane extends JComponent {

	private CarnaticBeatPlane plane = this;

        private JPanel panel;

	private CarnaticMetronome cMet;

	private volatile boolean isRunning;
        
        private long topRect;
                
        private long botRect;



		 
	public CarnaticBeatPlane(CarnaticMetronome m, JPanel p,long topRect, long botRect) {

                panel = p;
		isRunning = false;
		cMet = m;
                this.topRect = topRect;
                this.botRect = botRect;
                
	}


	public void paintComponent(Graphics g) {
		this.setVisible(true);
		g.setColor(Color.WHITE);
		g.fillRect(10,119,864,507);
		g.setColor(Color.BLUE);

		for(int i = 0; i<=cMet.getMeasure(); i++) {

                    
                        if(i == topRect) {
                        
                         g.setColor(Color.BLACK);
                        
                        }
			g.fillRect(20+90*i, 160, 10, 10);
                        
                        if(i==topRect) {
                        
                            g.setColor(Color.BLUE);
                        
                        }
		}

		g.setColor(Color.GREEN);

		for(int i = 0; i<cMet.getCount()*cMet.getKalam(); i++) {

                    
                        if(i==botRect) {
                        
                        g.setColor(Color.ORANGE);
                        }
			g.fillOval((int)(20+90*i/(cMet.getKalam()*cMet.getCount()/cMet.getMeasure())),140,5,5);

                        if(i==botRect) {
                        
                            g.setColor(Color.GREEN);
                        
                        }

		}
            
                

	}

	public void start() {

		isRunning = true;
                
	}

	public void stop() {

		isRunning = false;


	}

	public boolean running() {


		return isRunning;
	}





}

class StopButton extends JButton {

	public boolean pressed = false;

	public StopButton(String str) {

		super(str);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated cMethod stub

				pressed = true;

			}
		});

	}

}

public class PolyBeats {

	private CarnaticMetronome cMet = new CarnaticMetronome();
	private WesternMetronome wMet = new WesternMetronome();
        private char mode = 'C';

        
        private Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated cMethod stub

                long startTime = System.currentTimeMillis();

                long currTopRect = -1;
                long currBotRect = -1;
                
                while (true) {

                    
                    if ( (cBeatPlane == null||!cBeatPlane.running()) && (wBeatPlane == null||!wBeatPlane.running())) {

                        startTime = System.currentTimeMillis();

  

                    }

                    else if(mode == 'W') {
                    
                        long currentTime = System.currentTimeMillis()-startTime;
                       
                        
                       boolean topChanged = false;
                       boolean botChanged = false;
                       
                        
                       
                       
                        long newTopRect = ((long)(currentTime / (1000 * 60.0 / wMet.getBpm()))) % wMet.getMeasure();
                        
                        if(wMet.getMeasure() == 1) {
                        
                            newTopRect = ((long)(currentTime / (1000 * 60.0 / wMet.getBpm()))) / wMet.getMeasure();
                            
                       }
                        
                        long newBotRect = ((long)(currentTime / (((1.0*wMet.getMeasure()) / (wMet.getCount())) * (1000 * 60.0 / wMet.getBpm())))) % ((long)(wMet.getCount()));

                        if(wMet.getCount() == 1) {
                        
newBotRect = ((long)(currentTime / (((1.0*wMet.getMeasure()) / (wMet.getCount())) * (1000 * 60.0 / wMet.getBpm())))) / ((long)(wMet.getCount()));
                            
                       }
                        
                        
                        if(newTopRect == currTopRect && newBotRect == currBotRect) {
                        
                             continue;
                        
                        }
                        
                        topChanged = newTopRect != currTopRect;
                        
                        botChanged = newBotRect != currBotRect;
                        
                        
                        
                        
                        currTopRect = newTopRect; 
                        currBotRect = newBotRect;
                       
                        
                 //    System.out.println(((1.0*currentTime) %  (1000 * 60.0 / cMet.getBpm()) == 0)+" "+((1.0*currentTime) % (((1.0*cMet.getMeasure()) / (cMet.getKalam()*cMet.getCount())) * (1000 * 60.0 / cMet.getBpm())) == 0));
                    
                   //  //System.out.print(currentTime+" ");
                     //  //System.out.println(currentTime % 1000 == 0);
                       
                     
                     panel.remove(wBeatPlane);
                            
		            wBeatPlane = new WesternBeatPlane(wMet,panel,currTopRect,currBotRect);
                            
                            if(wMet.getCount()==1) {
                            
                                wBeatPlane = new WesternBeatPlane(wMet,panel,currTopRect,0);

                            }
                            
                            
                            if(wMet.getMeasure()==1) {
                            
                                wBeatPlane = new WesternBeatPlane(wMet,panel,0,currBotRect);

                            }
                            
                            if(wMet.getMeasure() == 1 && wMet.getCount() == 1) {
                            
                                wBeatPlane = new WesternBeatPlane(wMet,panel,0,0);

                            }
                            
				//currBottomRect == -1 ? -1:currBottomRect % (int)(cMet.getCount()*cMet.getKalam())	
                                 panel.add(wBeatPlane);
					wBeatPlane.setBounds(10,119,864,507);
                                        wBeatPlane.start(); 
                                        
                  
                   
                    if(topChanged && botChanged) {
                        try {
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("beat.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);



								clip.start();
                                                             
                                                              /*  Thread.sleep(2);

								clip2.start();   */                                                          
                    }
                    catch(Exception e) {}

                    
                    
                    }                   
                                        
                    if(topChanged) {
                    
                        
                    try {
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("beat.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();
                                                             
                    }
                    catch(Exception e) {}

                    }
                    
                    if(botChanged) {
                try {
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("matrai.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();
                                                             
                    }
                    catch(Exception e) {}
                    
                    
                    
                    
                    }
                    
                    
                       
                        
                    }
                    else if(mode=='C') {

                       long currentTime = System.currentTimeMillis()-startTime;
                       
                       boolean topChanged = false;
                       boolean botChanged = false;
                       
                        
                       
                        long newTopRect = ((long)(currentTime / (1000 * 60.0 / cMet.getBpm()))) % cMet.getMeasure();
                        
                        long newBotRect = ((long)(currentTime / (((1.0*cMet.getMeasure()) / (cMet.getKalam()*cMet.getCount())) * (1000 * 60.0 / cMet.getBpm())))) % ((long)(cMet.getCount()*cMet.getKalam()));

                        
                        if(newTopRect == currTopRect && newBotRect == currBotRect) {
                        
                             continue;
                        
                        }
                        
                        topChanged = newTopRect != currTopRect;
                        
                        botChanged = newBotRect != currBotRect;
                        
                        
                        
                        
                        currTopRect = newTopRect; 
                        currBotRect = newBotRect;
                        
                 //    System.out.println(((1.0*currentTime) %  (1000 * 60.0 / cMet.getBpm()) == 0)+" "+((1.0*currentTime) % (((1.0*cMet.getMeasure()) / (cMet.getKalam()*cMet.getCount())) * (1000 * 60.0 / cMet.getBpm())) == 0));
                    
                   //  //System.out.print(currentTime+" ");
                     //  //System.out.println(currentTime % 1000 == 0);
                       
                     
                     panel.remove(cBeatPlane);
                            
		            cBeatPlane = new CarnaticBeatPlane(cMet,panel,currTopRect,currBotRect);
				//currBottomRect == -1 ? -1:currBottomRect % (int)(cMet.getCount()*cMet.getKalam())	
                                 panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);
                                        cBeatPlane.start(); 
                                        
                  
                   
                    if(topChanged && botChanged) {
                        try {
                        
                        if(currTopRect == 0) {
                          AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("avarthanam.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);
                                                                
                                AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("matrai.wav"));

								Clip clip2 = AudioSystem.getClip();

								clip2.open(audioInputStream2);

								clip.start();
                                                             
                                Thread.sleep(1);


								clip2.start();
                                                                
                                                                continue;
                        
                        }
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("beat.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);


                                                                     AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("matrai.wav"));

								Clip clip2 = AudioSystem.getClip();

								clip2.open(audioInputStream2);

								clip.start();
                                                             
                                                                Thread.sleep(1);

								clip2.start();                                                             
                    }
                    catch(Exception e) {}

                    
                    
                    }                   
                                        
                    if(topChanged) {
                    
                        
                    try {
                        
                        if(currTopRect == 0) {
                          AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("avarthanam.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();
                                                                
                                                                continue;
                        
                        }
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("beat.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();
                                                             
                    }
                    catch(Exception e) {e.printStackTrace();}

                    }
                    
                    if(botChanged) {
                try {
                        
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("matrai.wav"));

								Clip clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();
                                                             
                    }
                    catch(Exception e) {}
                    
                    
                    
                    
                    }
                    
                    
                                        
                          
                                        
                                        
                         //   //System.out.println(currTopRect);
                       
                       
                       
                       
                       /*   if(1.0*currentTime %  (1000 * 60.0 / cMet.getBpm()) == 0) {

                            panel.remove(cBeatPlane);
                            currTopRect++;
                            
		                 cBeatPlane = new CarnaticBeatPlane(cMet,panel,currTopRect == -1 ? -1:currTopRect % cMet.getMeasure(),currBottomRect == -1 ? -1:currBottomRect % (int)(cMet.getCount()*cMet.getKalam()));
					
                                 panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);
                                        cBeatPlane.start();
                            //System.out.println(currTopRect);
                            




                        }

                        if(1.0*currentTime % (((1.0*cMet.getMeasure()) / (cMet.getKalam()*cMet.getCount())) * (1000 * 60.0 / cMet.getBpm())) == 0) {
                            
                            
                            panel.remove(cBeatPlane);

                            currBottomRect++;
		                 cBeatPlane = new CarnaticBeatPlane(cMet,panel,currTopRect == -1 ? -1:(currTopRect % cMet.getMeasure()),currBottomRect == -1 ? -1:(currBottomRect % (int)(cMet.getCount()*cMet.getKalam())));
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);
                                                                                cBeatPlane.start();
                                                                                        
                                                        //System.out.println(currBottomRect);

                            




                        } */


                    }



                }

            }});

	private final HashMap<String, Integer> nadaiDict;
	private final HashMap<String, Integer> baseDict;
	private final HashMap<String, Integer> jathiDict;
	private final HashMap<String, Double> kalamDict;

	private CarnaticBeatPlane cBeatPlane;

	private WesternBeatPlane wBeatPlane;

	private JFrame frame;
	private JTextField txtMetronome;
        
        private JPanel panel;
        


	public CarnaticBeatPlane getBeatPlane() {


		return cBeatPlane;
	}
	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {
		try {
			PolyBeats window = new PolyBeats();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public PolyBeats() {
		nadaiDict = new HashMap<String, Integer>();
		baseDict = new HashMap<String, Integer>();
		kalamDict = new HashMap<String, Double>();

		nadaiDict.put("Chatushra", 4);
		nadaiDict.put("Tishra", 3);
		nadaiDict.put("Khanda", 5);
		nadaiDict.put("Mishra", 7);
		nadaiDict.put("Sankeerna", 9);

		baseDict.put("Nadai", 4);
		baseDict.put("Tishram", 3);
		baseDict.put("Khandam", 5);
		baseDict.put("Mishram", 7);
		baseDict.put("Sankeernam", 9);

		kalamDict.put("athi keezh",0.25);
		kalamDict.put("keezh",0.5);
		kalamDict.put("sama",1.0);
		kalamDict.put("mEl",2.0);
		kalamDict.put("athi mEl",4.0);


		jathiDict = nadaiDict;

		initialize();
                
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {



		frame = new JFrame("PolyBeats");
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setPreferredSize(new Dimension(900,700));
		frame.pack();
		frame.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		panel.setLayout(null);



		JTextArea txtrNadai = new JTextArea();
                
                
		txtrNadai.setEditable(false);
                txtrNadai.setForeground(Color.WHITE);
		txtrNadai.setBounds(210, 96, 45, 22);
		txtrNadai.setText("Base");
                txtrNadai.setFont(new Font("Consolas",Font.PLAIN,15));

		txtrNadai.setOpaque(false);
		panel.add(txtrNadai);

		SpinnerModel sm = new SpinnerNumberModel(60, 60, 300, 1); // default value,lower bound,upper bound,increment by

                SpinnerModel sm1 = new SpinnerNumberModel(60, 60, 300, 1);
                
		JSpinner spinner = new JSpinner(sm);
		spinner.setBounds(274, 65, 56, 22);
		panel.add(spinner);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(603, 93, 89, 20);
		panel.add(comboBox_3);

		comboBox_3.addItem("Chatushra");
		comboBox_3.addItem("Tishra");
		comboBox_3.addItem("Khanda");
		comboBox_3.addItem("Mishra");
		comboBox_3.addItem("Sankeerna");

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(274, 129, 89, 22);
		panel.add(comboBox_1);
		comboBox_1.addItem("Nadai");
		comboBox_1.addItem("Tishram");
		comboBox_1.addItem("Khandam");
		comboBox_1.addItem("Mishram");
		comboBox_1.addItem("Sankeernam");

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(503, 65, 89, 22);
		panel.add(comboBox_4);
		comboBox_4.addItem("athi keezh");
		comboBox_4.addItem("keezh");
		comboBox_4.addItem("sama");
		comboBox_4.addItem("mEl");
		comboBox_4.addItem("athi mEl");


		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.setFont(new Font("Consolas", Font.PLAIN, 15));

		btnNewButton_1.setBounds(253, 205, 94, 23);
		panel.add(btnNewButton_1);

		StopButton btnNewButton = new StopButton("Stop");
		btnNewButton.setBounds(436, 205, 94, 23);
		panel.add(btnNewButton);

		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 15));
                
		JRadioButton rdbtnCarnaticMode = new JRadioButton("Carnatic Mode");
		rdbtnCarnaticMode.setBounds(522, 7, 150, 23);
                rdbtnCarnaticMode.setFont(new Font("Consolas",Font.PLAIN,15));

                rdbtnCarnaticMode.setForeground(Color.WHITE);
		panel.add(rdbtnCarnaticMode);

		JRadioButton rdbtnWesternMode = new JRadioButton("Western Mode");
		rdbtnWesternMode.setBounds(522, 33, 150, 23);
                rdbtnWesternMode.setFont(new Font("Consolas",Font.PLAIN,15));
                rdbtnWesternMode.setForeground(Color.WHITE);
		panel.add(rdbtnWesternMode);
                

		ButtonGroup modes = new ButtonGroup();

		modes.add(rdbtnCarnaticMode);
		modes.add(rdbtnWesternMode);
                
                rdbtnCarnaticMode.setBackground(null);
                rdbtnWesternMode.setBackground(null);
                

                
                rdbtnCarnaticMode.setSelected(true);
                
                SpinnerModel box1 = new SpinnerNumberModel(1,1,13,1); // default value,lower bound,upper bound,increment by
                SpinnerModel box2 = new SpinnerNumberModel(1,1,13,1); // default value,lower bound,upper bound,increment by

		JSpinner spinner_1 = new JSpinner(box1);
		spinner_1.setBounds(225, 100, 150,100);
                spinner_1.setFont(spinner_1.getFont().deriveFont(30f));
		panel.add(spinner_1);
                
           
                JSpinner spinner_2 = new JSpinner(box2);
		spinner_2.setBounds(425, 100, 150,100);
                spinner_2.setFont(spinner_2.getFont().deriveFont(30f));
		panel.add(spinner_2);
                
                JSpinner spinner_3 = new JSpinner(sm1);
		spinner_3.setBounds(325, 40, 130,40);
                spinner_3.setFont(spinner_3.getFont().deriveFont(30f));
		panel.add(spinner_3);
                                
                JTextArea semiColon = new JTextArea();
		semiColon.setEditable(false);
		semiColon.setText(":");
		semiColon.setOpaque(false);
                semiColon.setForeground(Color.WHITE);
		semiColon.setBounds(385,80,50,100);
                semiColon.setFont(semiColon.getFont().deriveFont(100f));
		panel.add(semiColon);
               
               	JButton wStart = new JButton("Start");
		wStart.setFont(new Font("Consolas", Font.PLAIN, 15));
		wStart.setBounds(258, 211, 89, 23);
		panel.add(wStart);
                
		StopButton wStop = new StopButton("Stop");
		wStop.setBounds(441, 211, 89, 23);
                wStop.setFont(new Font("Consolas", Font.PLAIN, 15));
		panel.add(wStop);


                
                
                
                
                


		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(274, 98, 89, 20);
		panel.add(comboBox);
		comboBox.addItem("Chatushra");
		comboBox.addItem("Tishra");
		comboBox.addItem("Khanda");
		comboBox.addItem("Mishra");
		comboBox.addItem("Sankeerna");

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(274, 165, 89, 20);
		panel.add(comboBox_2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("PolyBeats");

                comboBox_2.setVisible(false);
                
                JTextArea wSpeed = new JTextArea();
		wSpeed.setEditable(false);
		wSpeed.setText("Speed");
		wSpeed.setOpaque(false);
                wSpeed.setForeground(Color.WHITE);
		wSpeed.setBounds(240,45,70,100);
                wSpeed.setFont(new Font("Consolas",Font.PLAIN,24));
                
		panel.add(wSpeed);
                
                comboBox_2.setVisible(false);
                comboBox_3.setVisible(false);

                for (String[] i : CarnaticMetronome.commonTalams) {

			comboBox_2.addItem(i[0]);

		}

		cMet.setCount(4*nadaiDict.get(comboBox.getSelectedItem()));

		cMet.setMeasure(baseDict.get(comboBox_1.getSelectedItem()));

		cMet.setJathi(jathiDict.get(comboBox_3.getSelectedItem()));

		cMet.setTalam((String) comboBox_2.getSelectedItem());

		cMet.setBpm((int) ((SpinnerNumberModel) spinner.getModel()).getValue());

		cMet.setKalam(kalamDict.get(comboBox_4.getSelectedItem()));
                
                wMet.setCount((int) ((SpinnerNumberModel) spinner_1.getModel()).getValue());

                wMet.setMeasure((int) ((SpinnerNumberModel) spinner_2.getModel()).getValue());
                
                wMet.setBpm((int) ((SpinnerNumberModel) spinner_3.getModel()).getValue());

                


			cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
                        wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
                        
                                      t.start();

                        
                        
                wBeatPlane.setVisible(false);
                wSpeed.setVisible(false);
                spinner_1.setVisible(false);
                spinner_2.setVisible(false);
                spinner_3.setVisible(false);
                semiColon.setVisible(false);
                wStart.setVisible(false);
                wStop.setVisible(false);

                
                
                
               
               
               
                
                
                
                
	
		comboBox_3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated cMethod stub

				boolean cMetInterrupted = false;
				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setJathi(jathiDict.get(comboBox_3.getSelectedItem()));
				cMet.setTalam((String) comboBox_2.getSelectedItem());

				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted) {
					cMet.start();cBeatPlane.start();
                                }


			}

		});

                
          
                
                
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated cMethod stub
				boolean cMetInterrupted = false;

				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setCount(4*nadaiDict.get(comboBox.getSelectedItem()));
				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted){
					cMet.start();cBeatPlane.start();
			}
                        }

		});

		comboBox_1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated cMethod stub
				boolean cMetInterrupted = false;
				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setMeasure(baseDict.get(comboBox_1.getSelectedItem()));
				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted){
					cMet.start();cBeatPlane.start();
			}
                        }

		});

		comboBox_2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated cMethod stub
				boolean cMetInterrupted = false;
				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setTalam((String) comboBox_2.getSelectedItem());

				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted){
					cMet.start();cBeatPlane.start();
			}}

		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

                            
                                rdbtnWesternMode.setVisible(false);
                                rdbtnCarnaticMode.setVisible(false);
				
                                if(cMet.isPlaying()) {
                                
                                    return;
                                }
				cMet.start();cBeatPlane.start();

			}
		});
                spinner_1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        
                            boolean winterrupt = false;
                            
                            if(wMet.isPlaying()) {
                            
                                winterrupt = true;
                                wMet.stop();
                                wBeatPlane.stop();
                                
                            }
                            
                            wBeatPlane.stop();
                            panel.remove(wBeatPlane);
					wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
					panel.add(wBeatPlane);
					wBeatPlane.setBounds(10,119,864,507);
				wMet.setCount((int) ((SpinnerNumberModel) spinner_1.getModel()).getValue());
                                
                                if(winterrupt) {
                                    wMet.start();
                                wBeatPlane.start();
                                }
                        
                    }
                
                });
                
                spinner_2.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        
                        boolean winterrupt = false;
                            
                            if(wMet.isPlaying()) {
                            
                                winterrupt = true;
                                wMet.stop();
                                wBeatPlane.stop();
                                
                            }
                            
                            wBeatPlane.stop();
                            panel.remove(wBeatPlane);
					wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
					panel.add(wBeatPlane);
					wBeatPlane.setBounds(10,119,864,507);
				wMet.setMeasure((int) ((SpinnerNumberModel) spinner_2.getModel()).getValue());
                                
                                 if(winterrupt) {
                                    wMet.start();
                                wBeatPlane.start();
                                 }
                        
                    }
                
                });
                
                spinner_3.addChangeListener(new ChangeListener() {
                
                     public void stateChanged(ChangeEvent e) {
                         boolean winterrupt = false;
                            
                            if(wMet.isPlaying()) {
                            
                                winterrupt = true;
                                wMet.stop();
                                wBeatPlane.stop();
                                
                            }
                            
                            wBeatPlane.stop();
                            panel.remove(wBeatPlane);
					wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
					panel.add(wBeatPlane);
					wBeatPlane.setBounds(10,119,864,507);
				wMet.setBpm((int) ((SpinnerNumberModel) spinner_3.getModel()).getValue());
                                
                                 if(winterrupt) {
                                    wMet.start();
                                wBeatPlane.start();
                                
                                 }
                        
                    }
                
                });
                
                wStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        
                                rdbtnWesternMode.setVisible(false);
                                rdbtnCarnaticMode.setVisible(false);
                                
                                if(wMet.isPlaying()) {return;}
                                
                        
                        wMet.start();
                        wBeatPlane.start();
                        

                    }
                
                    
                
                
                
                });
                
                
                
                
                

		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated cMethod stub
				boolean cMetInterrupted = false;
				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setBpm((int) ((SpinnerNumberModel) spinner.getModel()).getValue());
				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted) {
					cMet.start();cBeatPlane.start();

                                }
			}

		});


		comboBox_4.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated cMethod stub
				boolean cMetInterrupted = false;
				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
					cMetInterrupted = true;

				}
				cMet.setKalam(kalamDict.get(comboBox_4.getSelectedItem()));

				try {

					panel.remove(cBeatPlane);
					cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
					panel.add(cBeatPlane);
					cBeatPlane.setBounds(10,119,864,507);


				} catch (Exception e) {

					e.printStackTrace();
				}

				if (cMetInterrupted){
					cMet.start();cBeatPlane.start();

                                }

			}});

                    
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (cMet.isPlaying()) {
					cMet.stop();cBeatPlane.stop();
                                        
                                }
				cMet.setCount(4*nadaiDict.get(comboBox.getSelectedItem()));

				cMet.setMeasure(baseDict.get(comboBox_1.getSelectedItem()));

				cMet.setJathi(jathiDict.get(comboBox_3.getSelectedItem()));

				cMet.setTalam((String) comboBox_2.getSelectedItem());

				cMet.setBpm((int) ((SpinnerNumberModel) spinner.getModel()).getValue());

				cMet.setKalam(kalamDict.get(comboBox_4.getSelectedItem()));
				panel.remove(cBeatPlane);
				cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
				panel.add(cBeatPlane);
				cBeatPlane.setBounds(10,119,864,507);
                                rdbtnWesternMode.setVisible(true);
                                rdbtnCarnaticMode.setVisible(true);
                                
                                




			}

		});
                
                wStop.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                
                        
                        if(wMet.isPlaying()) {
                        
                            wMet.stop();
                            
                        }
                        
                          wMet.setCount((int) ((SpinnerNumberModel) spinner_1.getModel()).getValue());

                wMet.setMeasure((int) ((SpinnerNumberModel) spinner_2.getModel()).getValue());
                
                wMet.setBpm((int) ((SpinnerNumberModel) spinner_3.getModel()).getValue());
                
                 panel.remove(wBeatPlane);
				wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
				panel.add(wBeatPlane);
				wBeatPlane.setBounds(10,119,864,507);
                        
                                rdbtnWesternMode.setVisible(true);
                                rdbtnCarnaticMode.setVisible(true);
                        
                    
                    }
                });
                
                
		JTextArea txtrCount = new JTextArea();
		txtrCount.setEditable(false);
		txtrCount.setText("Spacing");
                txtrCount.setFont(new Font("Consolas",Font.PLAIN,15));

                txtrCount.setForeground(Color.WHITE);
		txtrCount.setOpaque(false);
		txtrCount.setBounds(190, 128, 60, 22);
		panel.add(txtrCount);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText("Talam");
                textArea.setForeground(Color.WHITE);
                textArea.setVisible(false);
		textArea.setOpaque(false);
		textArea.setBounds(210, 162, 45, 22);
		panel.add(textArea);
                
                

		JTextArea txtrJathiifApplicable = new JTextArea();
		txtrJathiifApplicable.setEditable(false);
		txtrJathiifApplicable.setText("Jathi (when needed)");
		txtrJathiifApplicable.setOpaque(false);
		txtrJathiifApplicable.setBounds(445, 93, 178, 25);
		panel.add(txtrJathiifApplicable);
                txtrJathiifApplicable.setVisible(false);

		JTextArea txtrSpeed = new JTextArea();
		txtrSpeed.setEditable(false);
		txtrSpeed.setText("Speed");
                txtrSpeed.setFont(new Font("Consolas",Font.PLAIN,15));
		txtrSpeed.setOpaque(false);
                txtrSpeed.setForeground(Color.WHITE);
		txtrSpeed.setBounds(210, 65, 45, 22);
		panel.add(txtrSpeed);

		JTextArea txtrKalam = new JTextArea();
		txtrKalam.setText("Kalam");
                txtrKalam.setFont(new Font("Consolas",Font.PLAIN,15));

		txtrKalam.setOpaque(false);
		txtrKalam.setEditable(false);
                txtrKalam.setForeground(Color.WHITE);
		txtrKalam.setBounds(448, 64, 45, 22);
		panel.add(txtrKalam);

		panel.add(cBeatPlane);
                panel.add(wBeatPlane);
		cBeatPlane.setBounds(10,119,864,507);
                wBeatPlane.setBounds(10,119,864,507);
                wBeatPlane.setVisible(false);
                

                
		txtMetronome = new JTextField();
		txtMetronome.setForeground(Color.RED);
		txtMetronome.setEditable(false);
		txtMetronome.setText("PolyBeats");
		txtMetronome.setBounds(323, -1, 183, 39);
		txtMetronome.setColumns(10);
		txtMetronome.setBackground(null);

                Font timeburnerbold = null;
		try {
			timeburnerbold = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResourceAsStream("timeburnerbold.ttf"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtMetronome.setFont(timeburnerbold.deriveFont(30f));
		txtMetronome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtMetronome);
                
                rdbtnWesternMode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                            if(mode=='W') {
                            
                                return;
                            }
                            
                            mode = 'W';
                        
                            if(cMet.isPlaying()) {
                                
                                cMet.stop();
                                cBeatPlane.stop();
                                panel.remove(cBeatPlane);
				cBeatPlane = new CarnaticBeatPlane(cMet,panel,-1,-1);
				panel.add(cBeatPlane);
				cBeatPlane.setBounds(10,119,864,507);
                                
                                
                                
                            }
                                txtrSpeed.setVisible(false);
                                txtrNadai.setVisible(false);
                                txtrCount.setVisible(false);
                                textArea.setVisible(false);
                                txtrKalam.setVisible(false);
                                txtrJathiifApplicable.setVisible(false);
                                spinner.setVisible(false);
                                comboBox.setVisible(false);
                                comboBox_1.setVisible(false);
                                comboBox_2.setVisible(false);
                                comboBox_3.setVisible(false);
                                comboBox_4.setVisible(false);
                                btnNewButton.setVisible(false);
                                btnNewButton_1.setVisible(false);
                                cBeatPlane.setVisible(false);

                                
                                
                                wBeatPlane.setVisible(true);
                                wSpeed.setVisible(true);
                                spinner_1.setVisible(true);
                                spinner_2.setVisible(true);
                                spinner_3.setVisible(true);
                                semiColon.setVisible(true);
                                wStart.setVisible(true);
                                wStop.setVisible(true);
                                
                                
                                
                                
                                
                          
                            
                        
                        
                        
                    }

               
                });
                
                rdbtnCarnaticMode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                            if(mode=='C') {
                            
                                return;
                            }
                            
                            mode = 'C';
                        
                            if(wMet.isPlaying()) {
                                
                                wMet.stop();
                                wBeatPlane.stop();
                                panel.remove(wBeatPlane);
				wBeatPlane = new WesternBeatPlane(wMet,panel,-1,-1);
				panel.add(wBeatPlane);
			        wBeatPlane.setBounds(10,119,864,507);
                                
                            }
                            
                                wBeatPlane.setVisible(false);
                                wSpeed.setVisible(false);
                                spinner_1.setVisible(false);
                                spinner_2.setVisible(false);
                                spinner_3.setVisible(false);
                                semiColon.setVisible(false);
                                wStart.setVisible(false);
                                wStop.setVisible(false);
                                
                                txtrSpeed.setVisible(true);
                                txtrNadai.setVisible(true);
                                txtrCount.setVisible(true);
                                txtrKalam.setVisible(true);
                                spinner.setVisible(true);
                                comboBox.setVisible(true);
                                comboBox_1.setVisible(true);
                                comboBox_4.setVisible(true);
                                btnNewButton.setVisible(true);
                                btnNewButton_1.setVisible(true);
                                cBeatPlane.setVisible(true);


                                

                                
                               
                                
                                
                                
                                
                                
                          
                            
                        
                        
                        
                    }

               
                });
               
               












	}

}