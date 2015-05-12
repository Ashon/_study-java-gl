package glstudy.glTest;

import glstudy.glObject.glCircle;
import glstudy.glObject.glPoint;
import glstudy.glObject.glSegment;
import glstudy.glUtil.cameraCanvas;
import glstudy.glUtil.eventedFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import dead.glLine;

public class line_equation extends cameraCanvas implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6862615570113933400L;
	glPoint p1, p2;
	glSegment l1, l2, l3;
	glCircle c1;
	glPoint p3;
	glPoint p4;
	glLine ll;
	
	
	public line_equation(int width, int height){
		super(width, height);
		p1 = new glPoint(50, 4);
		p2 = new glPoint(50, 60);
		l1 = new glSegment(p1, p2);
		l2 = new glSegment(new glPoint(30, 80), new glPoint(10, 20));
		c1 = new glCircle(new glPoint(50, 60), 20);
		
		l3 = new glSegment(new glPoint(60, 80), new glPoint(20, 60));
		ll = new glLine(l3);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void draw(Graphics g){
		g.setColor(new Color(240,240,240));
		drawGrid(g);
		//draw center
		g.setColor(Color.black);
		drawCenter(g);
		
		ll.draw(g);
		// draw line eq
		glSegment s;
		g.setColor(Color.black);
		if(p1.x != p2.x){
			double slope = l1.getSlope();
			double intercept = l1.getIntercept();
			double x1 = 0 - getCamera().anchor.x;
			double y1 = slope * x1 + intercept;
			double x2 = getWidth() - getCamera().anchor.x;
			double y2 = slope * x2 + intercept;
			glPoint sp = new glPoint(x1, y1);
			glPoint ep = new glPoint(x2, y2);
			s = new glSegment(sp, ep);
			g.drawString("y = " + (int)(slope * 1000) / 1000.0 + "x + " + (int)(intercept * 1000) / 1000.0 ,
					0 - (int)getCamera().anchor.x + 5, 0 - (int)getCamera().anchor.y + 15);
		} else {
			s = new glSegment(new glPoint(p1.x, 0 - getCamera().anchor.y),
					new glPoint(p1.x, getHeight() - getCamera().anchor.y));
			g.drawString("x = " + p1.x ,
					0 - (int)getCamera().anchor.x + 5, 0 - (int)getCamera().anchor.y + 15);
		}
		g.setColor(Color.lightGray);
		s.draw(g);
		
		g.setColor(Color.black);
		p1.draw(g);
		g.drawString("(" + p1.x + ", " + p1.y + ")", (int)p1.x + 5, (int)p1.y -5);
		
		p2.draw(g);
		g.drawString("(" + p2.x + ", " + p2.y + ")", (int)p2.x + 5, (int)p2.y -5);
		
		l1.draw(g);
		
		glPoint res0 = l1.getContact(l2);
		Vector<glPoint> res = l1.getContact(c1);
		
		if(res0 != null)
			g.setColor(Color.blue);
		else
			g.setColor(Color.green);
		l2.draw(g);
		l2.start.draw(g);
		l2.end.draw(g);
		
		if(res != null)
			g.setColor(Color.blue);
		else
			g.setColor(Color.green);
		c1.draw(g);
		
		g.setColor(Color.red);
		if(res0 != null)
			res0.draw(g);
		if(res != null)
			for(glPoint glv : res)
				glv.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		p1.setX(e.getX() - getCamera().anchor.x);
		p1.setY(e.getY() - getCamera().anchor.y);
		p2.setX(e.getX() - getCamera().anchor.x);
		p2.setY(e.getY() - getCamera().anchor.y);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		p2.setX(e.getX() - getCamera().anchor.x);
		p2.setY(e.getY() - getCamera().anchor.y);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String [] args){
		eventedFrame ef = new eventedFrame();
		line_equation le = new line_equation(500,500);
		ef.add(le);
		ef.pack();
		ef.setVisible(true);
		le.repaint();
	}

}
 
