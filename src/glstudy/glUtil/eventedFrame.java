package glstudy.glUtil;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class eventedFrame extends Frame{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4287590613885843443L;

	public eventedFrame(){
		super();
		addListener();
	}
	
	public eventedFrame(String title){
		super(title);
		addListener();
	}
	
	public void addListener(){
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}
