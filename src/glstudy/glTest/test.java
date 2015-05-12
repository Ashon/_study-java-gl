package glstudy.glTest;

import glstudy.glObject.glPoint;
import glstudy.glUtil.bufferedCanvas;
import glstudy.glUtil.eventedFrame;

import java.awt.Graphics;

public class test implements Runnable{
	volatile Thread timer;
	eventedFrame ef = new eventedFrame("FrameTest");
	bufferedCanvas bc = new bufferedCanvas(500, 500){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void draw(Graphics g){
			this.drawBackground = false;
			p.draw(g);
			c.draw(g);
		}
	};
	
	glPoint p = new glPoint(250, 60);
	glPoint c = new glPoint(200, 70);
	
	public void start(){
		timer = new Thread(this);
		timer.start();
	}
	public void run(){
		while(timer == Thread.currentThread()){
			try{
				Thread.sleep(10);
			}catch(Exception e){ }
			p.rotate(c, Math.toRadians(5));
			bc.repaint();
		}
		
	}
	
	public test(){
		ef.add(bc);
		ef.pack();
		ef.setVisible(true);
		start();
	}
	
	public static void main(String [] args){
		new test();
	}
}