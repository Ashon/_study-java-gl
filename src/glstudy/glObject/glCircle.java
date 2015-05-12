package glstudy.glObject;

import java.awt.Graphics;

public class glCircle implements glShape, glDrawable{
	
	public glPoint center;
	public double radius;
	
	public glCircle(glPoint center, float radius){
		this.center = center;
		this.radius = radius;
	}
	
	@Override
	public void draw(Graphics g){
		g.drawOval((int)(center.x - radius), (int)(center.y - radius), (int)radius * 2, (int)radius * 2);
		center.draw(g);
	}

	@Override
	public void translate(double x, double y) {
		center.translate(x, y);
	}
	
	public void translate(glPoint gp) {
		// TODO Auto-generated method stub
		center.translate(gp);
	}

	@Override
	public void rotate(glPoint anchor, double radian) {
		// TODO Auto-generated method stub
		center.rotate(anchor, radian);
	}
}
