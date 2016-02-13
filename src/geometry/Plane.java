package geometry;

import scene.LightSource;
import scene.SurfaceInfo;

public class Plane implements Surface{

	private Point3 point;
	private Vector3 normal;
	private SurfaceInfo surface;
	
	public Plane(Point3 point, Vector3 normal, SurfaceInfo surface)
	{
		this.point = point;
		this.normal = normal;
		this.surface = surface;
	}
	
	public Point3 getPoint()
	{
		return this.point;
	}
	
	public Vector3 getNormal()
	{
		return this.normal;
	}
	
	public boolean intersectsRay(Ray ray, double min, double max)
	{
		Point3 p0l0 = point.subtractPoint(ray.getOrigin());
		Vector3 n = Vector3.normalize(normal);
		Vector3 l = Vector3.normalize(ray.getVector());
		double denom = n.dotProduct(l);
		if(denom > 0.0000001)
		{
			double t = n.dotProduct(p0l0) / denom;
			return (-t>=min && -t<=max);
		} else
		{
			return false;
		}
	}

	@Override
	public double intersectPoint(Ray ray, double min, double max) 
	{
		double denom = ray.getVector().dotProduct(normal);
		if(denom > 0.0000001)
		{
			double t = normal.dotProduct((point.subtractPoint(ray.getOrigin()))) / denom;
			return Math.min(max, -t);
		} else
		{
			return -1;
		}
	}
	


	@Override
	public float greenLight(Ray ray, LightSource light) {
		return this.surface.getAmbient().getGreen();
	}

	@Override
	public float redLight(Ray ray, LightSource light) {
		// TODO Auto-generated method stub
		return this.surface.getAmbient().getRed();
	}

	@Override
	public float blueLight(Ray ray, LightSource light) {
		// TODO Auto-generated method stub
		return this.surface.getAmbient().getBlue();
	}
}
