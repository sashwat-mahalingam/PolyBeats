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

public class CarnaticMetronome {

	private volatile double bpm;

	private volatile boolean isPlaying;
	private volatile int measure, count, jathi;
	private volatile double kalam;
	private volatile ArrayList<String> talam;
	public volatile static ArrayList<String[]> commonTalams;

/* public Thread t = new Thread(new Runnable() {
		@Override
		
		public void run() {
			int baseCount = talam.size() + 1;
			long startTime = System.currentTimeMillis();

			while (true) {
				
			

				if (!isPlaying) {
					baseCount = talam.size() + 1;
					startTime = System.currentTimeMillis();

					continue;
				}
                                
                                

				try {

                                    
					long currentTime = System.currentTimeMillis() - startTime;

                                          
                              //   //System.out.println(currentTime+" "+(1.0 * currentTime % (1000 * 60.0 / bpm) == 0));
					if (1.0 * currentTime % (1000 * 60.0 / bpm) == 0) {

						if (baseCount == talam.size() + 1) {
							baseCount = 1;
							AudioInputStream audioInputStream1 = AudioSystem
									.getAudioInputStream(new File("avarthanam.wav").getAbsoluteFile());

							clip = AudioSystem.getClip();

							clip.open(audioInputStream1);

							clip.start();

						}

						else {

							if (talam.get(baseCount - 1).equals("beat")) {
								AudioInputStream audioInputStream1 = AudioSystem
										.getAudioInputStream(new File("beat.wav").getAbsoluteFile());

								clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();

							}

							else {
								AudioInputStream audioInputStream1 = AudioSystem
										.getAudioInputStream(new File("turn.wav").getAbsoluteFile());

								clip = AudioSystem.getClip();

								clip.open(audioInputStream1);

								clip.start();

							}

						}

						//System.out.println(baseCount);
						baseCount++;

					} else if (1.0 * currentTime % (((1.0 * measure) / (kalam * count)) * (1000 * 60.0 / bpm)) == 0) {

						AudioInputStream audioInputStream = AudioSystem
								.getAudioInputStream(new File("matrai.wav").getAbsoluteFile());

						clip2 = AudioSystem.getClip();

						clip2.open(audioInputStream);

						clip2.start();

					}

				}

				catch (Exception exc)

				{

					exc.printStackTrace(System.out);

				}

			}
		}

	}); */

	public boolean isPlaying() {

		return isPlaying;
	}


	
	public double getBpm() {
		return bpm;
	}

	public void setBpm(double bpm) {
		this.bpm = bpm;
	}

	public int getMeasure() {
		return measure;
	}

	public void setMeasure(int measure) {
		this.measure = measure;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;

	}

	public ArrayList<String> getTalam() {
		return talam;
	}

	public void setTalam(String tal) {

		talam = new ArrayList<String>();
		String[] talamTemp = null;

		for (String[] arr : commonTalams) {

			if (arr[0].equals(tal)) {

				talamTemp = arr;
				break;

			}

		}
		for (String str : talamTemp) {

			if (str.equals("-Laghu")) {

				for (int i = 0; i < talamTemp.length; i++) {

					if (talamTemp[i].equals("-Laghu")) {

						talamTemp[i] = jathi + "Laghu";

					}

				}

				break;

			}

		}

		for (String str : talamTemp) {

			if (str.equals("Dhritam")) {

				talam.add("beat");
				talam.add("turn");

			}

			else if (str.substring(1).equals("Laghu")) {

				talam.add("beat");

				for (int i = 1; i < jathi; i++) {

					talam.add("turn");

				}

			}

			else if (str.equals("Anudhritam")) {

				talam.add("beat");

			}

		}

	}

	public int getJathi() {
		return jathi;
	}

	public void setJathi(int jathi) {
		this.jathi = jathi;
		//System.out.println(this.jathi);
	}

	public void setKalam(double k) {

		kalam = k;
	}

	public double getKalam() {

		return kalam;
	}

	private Clip clip;

	private Clip clip2;

	public CarnaticMetronome() {

		kalam = 0.5;
		isPlaying = false;
		talam = new ArrayList<String>();

		commonTalams = new ArrayList<String[]>();

		commonTalams.add(new String[] { "Eka", "-Laghu" });

		commonTalams.add(new String[] { "Ata", "-Laghu", "-Laghu", "Dhritam", "Dhritam" });

		commonTalams.add(new String[] { "Jhampa", "-Laghu", "Anudhritam", "Dhritam" });

		commonTalams.add(new String[] { "Dhruva", "-Laghu", "Dhritam", "-Laghu", "-Laghu" });

		commonTalams.add(new String[] { "Rupaka", "Dhritam", "-Laghu" });

		commonTalams.add(new String[] { "Matya", "-Laghu", "Dhritam", "-Laghu" });

		commonTalams.add(new String[] { "Triputa", "-Laghu", "Dhritam", "Dhritam" });

		commonTalams.add(new String[] { "Adi", "4Laghu", "Dhritam", "Dhritam" });

		commonTalams.add(new String[] { "Madhyaadi", "Anudhritam", "Anudhritam", "Dhritam" });

		commonTalams.add(new String[] { "Rupakam", "Anudhritam", "Dhritam" });
	//	t.start();

	}

	public void start() {
		//System.out.println(talam.size());
		isPlaying = true;

	}

	public void stop() {

		isPlaying = false;

		if (clip != null) {
			clip.stop();
			clip.close();
		}

		if (clip2 != null) {
			clip2.stop();
			clip2.close();
		}
		

	}
        
        

}
