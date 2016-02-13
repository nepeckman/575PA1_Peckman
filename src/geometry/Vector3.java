package geometry;

public class Vector3 {

	private double x;
	private double y;
	private double z;
	
	static public Vector3 normalize(Vector3 vector)
	{
		return new Vector3(vector.getX()/vector.getLength(), vector.getY()/vector.getLength(), vector.getZ()/vector.getLength());
	}
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Point3 point)
	{
		this.x = point.getX();
		this.y = point.getY();
		this.z = point.getZ();
	}
	
	public Vector3(Point3 point1, Point3 point2)
	{
		this.x = point1.getX() - point2.getX();
		this.y = point1.getY() - point2.getY();
		this.z = point1.getZ() - point2.getZ();
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
	
	public double getLength()
	{
		return Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ());
	}
	
	public double dotProduct(Vector3 other)
	{
		return this.getX()*other.getX() + this.getY()*other.getY() + this.getZ()*other.getZ();
	}
	
	public double dotProduct(Point3 other)
	{
		return this.getX()*other.getX() + this.getY()*other.getY() + this.getZ()*other.getZ();
	}
	
	public Vector3 addVector3(Vector3 other)
	{
		return new Vector3(x+other.getX(), y+other.getY(), z+other.getZ());
	}
}
