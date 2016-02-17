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
		if(denom < -0.0000001)
		{
			double t = n.dotProduct(p0l0) / denom;
			return (t>=min && t<=max);
		} else
		{
			return false;
		}
	}

	@Override
	public double intersectPoint(Ray ray, double min, double max) 
	{
		double denom = ray.getVector().dotProduct(normal);
		if(denom < -0.0000001)
		{
			double t = normal.dotProduct((point.subtractPoint(ray.getOrigin()))) / denom;
			return Math.min(max, t);
		} else
		{
			return -1;
		}
	}
	

	@Override
	public float greenLight(Ray ray, LightSource light, Surface[] surfaces) {
		return calculateLight(ray, light, surfaces, surface.getAmbient().getGreen(), surface.getDiffuse().getGreen(), surface.getSpecular().getGreen());
	}

	@Override
	public float redLight(Ray ray, LightSource light, Surface[] surfaces) {
		return calculateLight(ray, light, surfaces, surface.getAmbient().getRed(), surface.getDiffuse().getRed(), surface.getSpecular().getRed());
	}

	@Override
	public float blueLight(Ray ray, LightSource light, Surface[] surfaces) {
		return calculateLight(ray, light, surfaces, surface.getAmbient().getBlue(), surface.getDiffuse().getBlue(), surface.getSpecular().getBlue());
	}
	
	private float calculateLight(Ray ray, LightSource light, Surface[] surfaces, float ka, float kd, float ks)
	{
		double t = this.intersectPoint(ray, 0, 1000000000) - 0.01;
		Vector3 rayV = ray.getVector();
		Point3 rayO = ray.getOrigin();
		Point3 phit = new Point3(rayO.getX()+t*rayV.getX(), rayO.getY()+t*rayV.getY(), rayO.getZ()+t*rayV.getZ());
		Vector3 n = this.normal;
		Vector3 l = new Vector3(light.getPosition().subtractPoint(phit));
		l = Vector3.normalize(l);
		Vector3 v = new Vector3(-rayV.getX(), -rayV.getY(), -rayV.getZ());
		v = Vector3.normalize(v);
		Vector3 vl = v.addVector3(l);
		Vector3 h = Vector3.normalize(vl);
		Ray shadow = new Ray(phit, l);
		if(hitsShadow(surfaces, shadow))
		{
			kd = 0;
			ks = 0;
		}
		return (float) (ka + kd*Math.max(0, n.dotProduct(l))+ks*Math.pow(Math.max(0, n.dotProduct(h)), surface.getSpecularPower()));
	}
	
	private boolean hitsShadow(Surface[] surfaces, Ray ray)
	{
		Surface hit_surface = null;
		double t = 1000000000;
		for(int idx = 0; idx < surfaces.length; idx++)
		{
			if(surfaces[idx].intersectsRay(ray, 0, t))
			{
				t = surfaces[idx].intersectPoint(ray, 0, t);
				hit_surface = surfaces[idx];
			}
		}
		
		if(hit_surface == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
