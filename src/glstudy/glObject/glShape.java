package glstudy.glObject;

public interface glShape {
	/**
	 * translate 변환
	 * @param gp : glPoint
	 */
	public void translate(double x, double y);
	
	/**
	 * 회전변환을 수행한다.
	 * @param anchor : glPoint 앵커 점
	 * @param radian : 회전할 라디안 값
	 */
	public void rotate(glPoint anchor, double radian);
}