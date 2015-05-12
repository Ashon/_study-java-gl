package dead;

import glstudy.glObject.glDrawable;
import glstudy.glObject.glPoint;
import glstudy.glObject.glSegment;
import glstudy.glObject.glShape;

import java.awt.Color;
import java.awt.Graphics;

public class glLine implements glShape, glDrawable{

	public double slope;
	public double intercept;
	
	public glLine(double slope, double intercept){
		this.slope = slope;
		this.intercept = intercept;
	}
	
	public glLine(glSegment gls){
		slope = gls.getSlope();
		intercept = gls.getIntercept();
	}
	
	public glLine(glPoint p1, glPoint p2){
		slope = (p1.y - p2.y) / (p1.x - p2.x);
		intercept = p1.y - slope * p1.x;
	}

	@Override
	public void draw(Graphics g){
		double min = -1000000;
		double max = 1000000;
		glPoint p1 = new glPoint(min, min * slope + intercept);
		glPoint p2 = new glPoint(max, max * slope + intercept);
		glSegment s1 = new glSegment(p1, p2); 
		g.setColor(Color.magenta);
		s1.draw(g);
		g.setColor(Color.black);
	}

	public double get(double x){
		return slope * x + intercept;
	}

	public glSegment getSegment(double x1, double x2){
		return new glSegment(x1, get(x1), x2, get(x2));
	}
	public glRay getRay(double x1){
		return new glRay(x1, get(x1), slope);
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
