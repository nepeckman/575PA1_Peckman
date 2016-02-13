package scene;

import geometry.Point3;
import geometry.Ray;
import geometry.Vector3;

public class Camera {

	private Point3 position;
	
	public Camera(Point3 position)
	{
		this.position = position;
	}
	
	public Point3 getPosition()
	{
		return this.position;
	}
	
	public Ray getRay(Pixel pixel)
	{
		return new Ray(this.position, new Vector3(this.position, pixel.getPosition()));
	}
}
