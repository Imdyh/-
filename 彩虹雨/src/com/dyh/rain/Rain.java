package com.dyh.rain;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class Rain extends Frame{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH=1920;
	public static final int HEIGHT=1080;
	
	public static ArrayList<RainInfo> rainInfos = new ArrayList<RainInfo>();
	
	public Rain(){
		this.setSize(WIDTH,HEIGHT);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.BLACK);
		this.addWindowListener(new CloseWindow());
		this.setTitle("�ʺ���.DYh-11.03.19");
		new MyThread().start();
	}
	
	class MyThread extends Thread{
		@Override
		public void run() {
			while (true) {
				try {
					repaint();
					sleep(41);
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	//˫�ػ���������˸
	Image bufferImage=null; 
	Graphics GraImage = null;
	@Override
	public void update(Graphics g) {
		//����б���ͼƬ����Ҫ�ж�bufferImage�Ƿ�Ϊ��
		//if(bufferImage==null)
	    bufferImage = createImage(this.getWidth(), this.getHeight());   //����ͼ�λ�����  
	    GraImage = bufferImage.getGraphics();       //��ȡͼ�λ�������ͼ��������  
	    paint(GraImage);        //��paint�����б�д�Ļ�ͼ���̶�ͼ�λ�������ͼ  
	    GraImage.dispose();     //�ͷ�ͼ����������Դ  
	    g.drawImage(bufferImage, 0, 0, this);   //��ͼ�λ��������Ƶ���Ļ�� 
	}
	
	@Override
	public void paint(Graphics g) {
		run(g);
	}
	
	RainInfo tempInfo;
	Random random=new Random();
	public void run(Graphics g){
		if(rainInfos.size()<WIDTH/50){
			tempInfo=new RainInfo(random.nextInt(WIDTH), 0, random.nextInt(5)+5,Color.GREEN, random.nextInt(20)+10, random.nextInt(30)+10);
			rainInfos.add(tempInfo);
		}
		for (int i = 0; i < rainInfos.size(); i++) {
			tempInfo=rainInfos.get(i);
			tempInfo.drawRain(g);
			if(tempInfo.isAlive==false){
				rainInfos.remove(tempInfo);
			}
		}
	}
	
	
}
