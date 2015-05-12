package glstudy.glObject;

import java.awt.Graphics;
import java.util.Vector;

public class glSegment implements glShape, glDrawable{

	public glPoint start;
	public glPoint end;

	public glSegment(double x1, double y1, double x2, double y2){
		start = new glPoint(x1, y1);
		end = new glPoint(x2, y2);
	}

	public glSegment(glPoint p1, glPoint p2){
		this.start = p1;
		this.end = p2;
	}

	public double getLength(){
		return start.getDist(end);
	}

	@Override
	public void draw(Graphics g) {
		start.draw(g);
		end.draw(g);
		g.drawLine((int)start.x, (int)start.y, (int)end.x, (int)end.y);
	}
	
	public glPoint getContact(glPoint p){
		return p.getContact(this);
	}

	/**
	 * 세그먼트 들과의 교점을 리턴합니다.
	 * @param s1
	 * @param s2
	 * @return 두 선분간의 교점인 glPoint 인스턴스를 리턴합니다. 
	 */
	public static glPoint getContact(glSegment s1, glSegment s2){
		if(glSegment.getSlope(s1) != glSegment.getSlope(s2)) {
			double x = (glSegment.getIntercept(s1) - glSegment.getIntercept(s2))
					/ (glSegment.getSlope(s2) - glSegment.getSlope(s1));
			if(Math.max(Math.min(s2.start.x, s2.end.x), Math.min(s1.start.x, s1.end.x)) <= x 
					&& x <=  Math.min(Math.max(s2.start.x, s2.end.x), Math.max(s1.start.x, s1.end.x))){
				double y = x * glSegment.getSlope(s1) + glSegment.getIntercept(s1);
				return new glPoint(x, y);
			}
		}
		return null;
	}
	
	public glPoint getContact(glSegment s){
		return glSegment.getContact(this, s);
	}
	
	public static Vector<glPoint> getContact(glSegment s, glCircle c){
		double sqrCr = c.radius * c.radius;
		if(s.start.x != s.end.x){
			double slope = getSlope(s);									// line slope
			double i = getIntercept(s);								// line y-intercept
			double d = i - c.center.y;								// delta
			double A2 = 2 * (1 + slope * slope);							// 2A of D
			double B = 2 * (slope * d - c.center.x);					// B of D
			double C = c.center.x * c.center.x + d * d - sqrCr;		// C of D
			double D = B * B - 2 * A2 * C;							// D = B ^ 2 - 2 * 2A * C
			if(D >= 0){
				double sqrtD = (double)Math.sqrt(D);
				double s1 = (sqrtD - B) / A2;
				double s2 = -(B + sqrtD) / A2;
				double mx = Math.min(s.start.x, s.end.x);
				double Mx = Math.max(s.start.x, s.end.x);
				if(mx <= s1 && s2 <= Mx){
					Vector<glPoint> res = new Vector<glPoint>();
					if(mx <= s1 && s1 <= Mx)
						res.add(new glPoint(s1, slope * s1 + i));
					if(mx <= s2 && s2 <= Mx)
						res.add(new glPoint(s2, slope * s2 + i));
					return res;
				}
			}
		} else {
			double d = s.start.x - c.center.x;
			double D = sqrCr - d * d;
			if(D >= 0){
				double sqrtD = (double)Math.sqrt(D);
				double s1 = c.center.y + sqrtD;
				double s2 = c.center.y - sqrtD;
				double my = Math.min(s.start.y, s.end.y);
				double My = Math.max(s.start.y, s.end.y);
				if(my <= s1 && s2 <= My){
					Vector<glPoint> res = new Vector<glPoint>();
					if(my <= s1 && s1 <= My)
						res.add(new glPoint(s.start.x, s1));
					if(my <= s2 && s2 <= My)
						res.add(new glPoint(s.start.x, s2));
					return res;
				}
			}
		}
		return null;
	}
	
	public Vector<glPoint> getContact(glCircle c){
		return glSegment.getContact(this, c);
	}
	
	public static double getSlope(glSegment s){
		if(s.start.x != s.end.x)
			return (s.start.y - s.end.y) / (s.start.x - s.end.x);
		else
			return Double.NaN;
	}
	
	public double getSlope(){
		return glSegment.getSlope(this);
	}
	
	public static double getIntercept(glSegment s){
		if(s.start.x != s.end.x)
			return s.start.y - glSegment.getSlope(s) * s.start.x;
		else
			return Double.NaN;
	}
	public double getIntercept(){
		return glSegment.getIntercept(this);
	}
	
	public boolean equals(glSegment l){
		return start.equals(l.start) && end.equals(l.end);
	}
	
	public boolean upper(glPoint p){
		return Math.min(start.x, end.x) <= p.x && p.x <= Math.max(start.x, end.x) 
				&& glSegment.getSlope(this) * p.x + glSegment.getIntercept(this) < p.y;
	}
	@Override
	public glSegment clone(){
		return new glSegment(start.clone(), end.clone());
	}
	@Override
	public void rotate(glPoint anchor, double radian) {
		// TODO Auto-generated method stub
		start.rotate(anchor, radian);
		end.rotate(anchor, radian);
	}

	public void translate(double x, double y){
		start.add(x, y);
		end.add(x, y);
	}
	
	public void translate(glPoint gp) {
		// TODO Auto-generated method stub
		translate(gp.x, gp.y);
	}
}