package glstudy.glUtil;

import glstudy.glObject.glAABB;
import glstudy.glObject.glPoint;

/**
 * world-coordinate system으로 변환한다. 클리핑 공간을 생성한다.
 * @author ashon
 */
public class glCamera extends glAABB{
	public glPoint anchor;
	public glCamera(float x1, float y1, float x2, float y2){
		super(x1, y1, x2, y2);
		this.anchor = new glPoint((x1 + x2) / 2, (y1 + y2) / 2);
	}
}