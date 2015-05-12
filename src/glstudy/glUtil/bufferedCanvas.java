package glstudy.glUtil;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

/** 
 * GLStudy용 캔버스. awt.Canvas를 상속받아 만들어 졌고 더블버퍼링을 기본적으로 지원한다.<br/>
 * paint 메소드가 아닌 draw메소드를 이용해 캔버스를 그린다.
 * @author ashon
 */
public class bufferedCanvas extends Canvas{
	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = -6967356913966007838L;
	
	/**
	 * 더블버퍼링을 위한 그래픽 인스턴스이다.
	 */
	Graphics offGraphics;
	
	/**
	 * 더블버퍼링을 위한 이미지 인스턴스이다.
	 */
	Image offScreen;
	
	/**
	 * 백그라운드 컬러 흰색이 기본이다.
	 */
	Color background = Color.white;
	
	/**
	 * 포어그라운드 컬러 검은색이 기본이다.
	 */
	Color foreground = Color.black;
	/**
	 * 백그라운드를 그릴 것인지에 대한 부울 변수
	 */
	public boolean drawBackground = true;
	
	/**
	 * awt.Canvas생성자를 상속받는다. 
	 */
	public bufferedCanvas(){
		super();
	}
	
	/**
	 * awt.Canvas생성자이다. width와 height를 입력받아 그 크기의 캔버스를 생성한다.
	 * @param width : 가로 길이
	 * @param height : 세로 길이
	 */
	public bufferedCanvas(int width, int height){
		this();
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(width, height);
	}
	
	/**
	 * paint메소드 내에서 더블버퍼링 로직을 실행하므로 오버라이딩 하지 않도록 한다.
	 * @deprecated
	 */
	public void paint(Graphics g){
		if(offScreen == null)
			offScreen = this.createImage(getWidth(), getHeight());
		if(offGraphics == null){
			offGraphics = offScreen.getGraphics();
			((Graphics2D)offGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		if(drawBackground){
			offGraphics.setColor(background);
			offGraphics.fillRect(0, 0,getWidth(), getHeight());
			offGraphics.setColor(foreground);
		}
		set(offGraphics);
		draw(offGraphics);
		unset(offGraphics);
		g.drawImage(offScreen, 0, 0, this);
	}
	
	public void set(Graphics g){
		
	}
	
	public void unset(Graphics g){
		
	}
	/**
	 * paint메소드 대신 사용되는 메소드이다. Graphics 클래스를 인자로 받아 처리한다.
	 * 이 메소드를 상속받아서 캔버스에 드로잉 할 수 있다.
	 * @param g - awt.Graphics 클래스
	 */
	public void draw(Graphics g){
	}
	
	/**
	 * update 메소드는 paint메소드를 실행한다.<br/>
	 * 기존 awt캔버스에서 화면을 지우던 로직은 paint내에서 이루어진다.
	 * @deprecated
	 */
	public void update(Graphics g){
		paint(g);
	}
}
