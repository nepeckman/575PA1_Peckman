package geometry;

public class Point3 {

	private double x;
	private double y;
	private double z;
	
	public Point3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
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
	
	public Point3 addPoint(Point3 other)
	{
		return new Point3(this.x+other.x, this.y+other.y, this.z+other.z);
	}
	
	public Point3 subtractPoint(Point3 other)
	{
		return new Point3(this.x-other.x, this.y-other.y, this.z-other.z);
	}
	
	public double dotProduct(Point3 other)
	{
		return this.getX()*other.getX() + this.getY()*other.getY() + this.getZ()*other.getZ();
	}
}
