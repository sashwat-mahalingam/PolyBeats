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

public class WesternMetronome {

	private double bpm;

	private volatile boolean isPlaying;
	private volatile int measure, count;


	

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


	private Clip clip;

	private Clip clip2;

	public WesternMetronome() {


		isPlaying = false;

	}

	public void start() {
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
