package glstudy.glObject;

import java.awt.Graphics;

/**
 * GLstydy를 위한 2차원 벡터 클래스이다.<br />
 * x, y좌표는 double형으로 이루어 진다.
 * @author ashon
 */

public class glPoint implements glShape, glDrawable, Cloneable{
	public double x;
	public double y;

	/**
	 * 생성자이다. (0, 0)좌표를 가진 인스턴스를 생성한다.
	 */
	public glPoint(){
		x = 0;
		y = 0;
	}
	/**
	 * x좌표와 y좌표를 입력받아 (x, y)좌표를 가진 인스턴스를 생성한다.
	 * @param x
	 * @param y
	 */
	public glPoint(double x, double y){
		this.x = x;
		this.y = y;
	}

	public glPoint(glPoint p){
		this.x = p.x;
		this.y = p.y;
	}

	/**
	 * x 좌표를 설정한다. 
	 * @param x - x좌표
	 */
	public void setX(double x){
		this.x = x;
	}

	/**
	 * y 좌표를 설정한다.
	 * @param y - y좌표
	 */
	public void setY(double y){
		this.y = y;
	}
	/**
	 * x 좌표 더하기.
	 * @param x
	 */
	public void addX(double x){
		this.x += x;
	}
	/**
	 * y좌표 더하기.
	 * @param y
	 */
	public void addY(double y){
		this.y += y;
	}
	/**
	 * x, y좌표 더하기.
	 * @param x
	 * @param y
	 */
	public void add(double x, double y){
		addX(x);
		addY(y);
	}
	/**
	 * 벡터 더하기
	 * @param gp
	 */
	public void add(glPoint gp){
		add(gp.x, gp.y);
	}
	public static glPoint add(glPoint p1, glPoint p2){
		return new glPoint(p1.x + p2.x, p1.y + p2.y);
	}
	
	public void subX(double x){
		this.x -= x;
	}
	public void subY(double y){
		this.y -= y;
	}
	public void sub(double x, double y){
		subX(x);
		subY(y);
	}
	public void sub(glPoint gp){
		sub(gp.x, gp.y);
	}
	public static glPoint sub(glPoint p1, glPoint p2){
		return new glPoint(p1.x - p2.x, p1.y - p2.y);
	}
	
	public void multX(double x){
		this.x *= x;
	}
	public void multY(double y){
		this.y *= y;
	}
	public void mult(double x, double y){
		multX(x);
		multY(y);
	}
	public void mult(glPoint gp){
		mult(gp.x, gp.y);
	}
	public static glPoint mult(glPoint p1, glPoint p2){
		return new glPoint(p1.x * p2.x, p2.y * p2.y);
	}
	/**
	 * 내적 연산을 한다.
	 * @param gp : glPoint 벡터
	 * @return double형의 내적
	 */
	public double dot(glPoint gp){
		return x * gp.x + y * gp.y;
	}

	public static double dot(glPoint gp1, glPoint gp2){
		return gp1.dot(gp2);
	}

	/**
	 * 2차원 벡터의 외적 연산을 한다. 행렬식과 같은 형태
	 * @param gp : glPoint 벡터
	 * @return double형의 외적
	 */
	public double cross(glPoint gp){
		return x * gp.y - y * gp.x;
	}

	public static double cross(glPoint gp1, glPoint gp2){
		return gp1.cross(gp2);
	}

	/**
	 * 해당 인스턴스의 좌표를 p2d와 동일하게 설정한다.
	 * @param p2d - P2D 클래스
	 */
	public void set(glPoint p2d){
		this.x = p2d.getX();
		this.y = p2d.getY();
	}

	/**
	 * P2D클래스의 getter
	 * @return x좌표를 반환한다.
	 */
	public double getX(){
		return x;
	}

	/**
	 * P2D클래스의 getter
	 * @return y좌표를 반환한다.
	 */
	public double getY(){
		return y;
	}

	/**
	 * GLPoint의 객체를 복제한다.
	 * @return GLPoint - 객체의 클론 오브젝트.
	 */
	public glPoint clone(){
		return new glPoint(x, y);
	}

	

	/**
	 * 포인트 간의 제곱거리를 반환한다.
	 * @param p - glPoint
	 * @return double 형의 거리의 제곱
	 */
	public double getSqrDist(glPoint p){
		return (this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y);
	}
	/**
	 * 포인트 간의 거리를 반환한다.
	 * @param p - GLPoint
	 * @return double - 형의 거리
	 */
	public double getDist(glPoint p){
		return Math.sqrt(getSqrDist(p));
	}
	@Override
	public String toString(){
		String s = super.toString();
		s += "[" + x + ", " + y +"]";
		return s;
	}

	/**
	 * drawable 메소드를 구현. 점을 캔버스상에 표현한다.
	 */
	public void draw(Graphics g) {
		int size = 2;
		g.drawLine((int)x - size, (int)y, (int)x + size, (int)y);
		g.drawLine((int)x, (int)y - size, (int)x, (int)y + size);
	}

	/**
	 * 점의 좌표가 같은지 판별
	 * @param p - GLPoint
	 * @return 점의 위치가 같으면 true, 그렇지 않으면 false
	 */
	public boolean equals(glPoint p){
		return p.x == this.x && p.y == this.y;
	}

	@Override
	public void rotate(glPoint anchor, double radian) {
		double dx = x - anchor.x;
		double dy = y - anchor.y;
		double cosR = Math.cos(radian);
		double sinR = Math.sin(radian);
		x = anchor.x + (dx * cosR - dy * sinR);
		y = anchor.y + (dx * sinR + dy * cosR);
	}

	@Override
	public void translate(double x, double y){
		add(x, y);
	}
	public void translate(glPoint gp){
		add(gp);
	}
	public boolean intersects(glCircle c){
		return getDist(c.center) <= c.radius;
	}
	public glPoint getContact(glCircle c){
		return intersects(c) ? this.clone() : null;
	}
	public boolean intersects(glPoint p){
		return p.equals(this);
	}
	public glPoint getContact(glPoint p){
		return intersects(p) ? this.clone() : null;
	}	
	public boolean intersects(glSegment s){
		return s.start.getDist(this) + s.end.getDist(this) == s.getLength();
	}
	public glPoint getContact(glSegment s){
		return intersects(s) ? this.clone() : null;
	}
}