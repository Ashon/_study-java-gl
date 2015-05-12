package glstudy.glObject;

public class glAABB implements glShape{
	glPoint position;
	glPoint size;

	public glAABB(double x, double y, double w, double h){
		position = new glPoint(x, y);
		size = new glPoint(w, h);
	}
	public glAABB(glPoint position, glPoint size){
		this.position = position.clone();
		this.size = size.clone();
	}
	@Override
	public void translate(double x, double y) {
		// TODO Auto-generated method stub
		this.position.translate(x, y);
	}

	@Override
	public void rotate(glPoint anchor, double radian) {
		// TODO Auto-generated method stub
	}
}
