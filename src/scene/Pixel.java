package scene;

import geometry.Point3;

public class Pixel {

	private int x;
	private int y;
	private Viewrect viewrect;
	
	public Pixel(int x, int y, Viewrect viewrect)
	{
		this.x = x;
		this.y = y;
		this.viewrect = viewrect;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Point3 getPosition()
	{
		double u = viewrect.getL() + (viewrect.getR() - viewrect.getL())*(x+0.5)/viewrect.getPixelWidth();
		double v = viewrect.getB() + (viewrect.getT() - viewrect.getB())*(y+0.5)/viewrect.getPixelHeight();
		double d = -0.1;
		Point3 rel_position = new Point3(u, v, d);
		Point3 position = rel_position.addPoint(this.viewrect.getCamera().getPosition());
		return position;
	}
}
