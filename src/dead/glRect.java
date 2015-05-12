package dead;
import glstudy.glObject.glDrawable;
import glstudy.glObject.glPoint;
import glstudy.glObject.glSegment;
import glstudy.glObject.glShape;

import java.awt.Graphics;
import java.util.Vector;

public class glRect implements glShape, glDrawable, Cloneable{
	public glPoint position;
	public glPoint size;
	public glPoint anchor;
	public double angle; // radian
	
	public glRect(double x, double y, double w, double h){
		position = new glPoint(x, y);
		size = new glPoint(w, h);
		angle = 0;
		anchor = position.clone();
	}
	
	public glRect(glPoint position, glPoint size){
		this.position = position;
		this.size = size;
		anchor = this.position.clone();
	}
	
	@Override
	public void draw(Graphics g){
	}

	public double getWidth(){
		return size.x;
	}
	
	public double getHeight(){
		return size.y;
	}
	
	public glRect clone(){
		glRect r = new glRect(position.x, position.y, size.x, size.y);
		r.angle = angle;
		return r;
	}

	public glPoint getCenter(){
		glPoint center = new glPoint(position.x + size.x, position.y + size.y);
		center.rotate(position, angle);
		return center;
	}
/*
	public boolean intersects(glPoint p){
	}

	public Vector<glPoint> getContact(glSegment s){
	}
*/
	@Override
	public void translate(double x, double y){
		// TODO Auto-generated method stub
		position.x += x;
		position.y += y;
	}
	
	public void rotate(double radian){
		angle = radian;
	}

	@Override
	public void rotate(glPoint anchor, double radian) {
		// TODO Auto-generated method stub
		this.position.rotate(anchor, radian);
		angle = radian;
	}
	
	public void translate(glPoint gp) {
		
		
	}
}
