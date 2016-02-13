package geometry;

public class Ray {

	private Vector3 vector;
	private Point3 origin;
	
	public Ray(Point3 origin, Vector3 vector)
	{
		this.vector = vector;
		this.origin = origin;
	}
	
	public Vector3 getVector()
	{
		return this.vector;
	}
	
	public Point3 getOrigin()
	{
		return this.origin;
	}
}
