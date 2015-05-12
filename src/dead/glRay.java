package dead;

import glstudy.glObject.glDrawable;
import glstudy.glObject.glPoint;
import glstudy.glObject.glShape;

import java.awt.Graphics;

public class glRay implements glShape, glDrawable{
	
	public glPoint start;
	public double slope;
	
	public glRay(double x, double y, double slope){
		this.start = new glPoint(x, y);
		this.slope = slope;
	}
	public glRay(glPoint start, double slope){
		this.start = start;
		this.slope = slope;
	}
	public glRay(glPoint start, glPoint direction){
		this.start = start;
		slope = (start.y - direction.y) / (start.x - direction.x);
	}
	public double get(double x){
		double intercept = start.y - slope * start.x;
		return slope * x + intercept;
	}
	
	@Override
	public void draw(Graphics g){
		
	}

	@Override
	public void translate(double x, double y) {
		// TODO Auto-generated method stub
	}
	public void translate(glPoint gp) {
		
	}

	@Override
	public void rotate(glPoint anchor, double radian) {
		// TODO Auto-generated method stub
		
	}
}
