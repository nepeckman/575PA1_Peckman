package scene;

import geometry.Point3;

public class LightSource {

	private Point3 position;
	
	public LightSource(Point3 position)
	{
		this.position = position;
	}
	
	public Point3 getPosition()
	{
		return this.position;
	}
}
