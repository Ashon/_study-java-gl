package glstudy.glUtil;

import java.awt.Graphics;


public class cameraCanvas extends bufferedCanvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8987118661224012013L;
	glCamera glc;
	
	public cameraCanvas(){
		super();
	}
	
	public cameraCanvas(int width, int height){
		super(width, height);
		glc = new glCamera(0, 0, width, height);
	}
	
	public void set(Graphics g){
		g.translate((int)glc.anchor.x, (int)glc.anchor.y);
	}
	public void unset(Graphics g){
		g.translate((int)-glc.anchor.x, (int)-glc.anchor.y);
	}
	
	public glCamera getCamera(){
		return glc;
	}
	
	public void drawCenter(Graphics g){
		g.drawLine(0, 0 - (int)glc.anchor.y, 0, getHeight() - (int)glc.anchor.y);
		g.drawLine(0 - (int)glc.anchor.x, 0, getWidth() - (int)glc.anchor.x, 0);
	}
	
	public void drawGrid(Graphics g){
		for(int i = 0; i < (int)(getWidth() / 10); i++)
			g.drawLine(i * 10- (int)glc.anchor.x, 0 - (int)glc.anchor.y, i * 10- (int)glc.anchor.x, getHeight() - (int)glc.anchor.y);
		
		for(int i = 0; i < (int)(getHeight() / 10); i++)
			g.drawLine(0 - (int)glc.anchor.x, i * 10- (int)glc.anchor.y, getWidth() - (int)glc.anchor.x, i * 10- (int)glc.anchor.y);
	}
}
