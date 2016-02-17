package geometry;

import scene.LightSource;
import scene.SurfaceInfo;

public class Sphere implements Surface{

	private int radius;
	private Point3 center;
	private SurfaceInfo surface;
	
	public Sphere(int radius, Point3 center, SurfaceInfo surface)
	{
		this.radius = radius;
		this.center = center;
		this.surface = surface;
	}
	
	public boolean intersectsRay(Ray ray, double min, double max)
	{
		double a = ray.getVector().dotProduct(ray.getVector());
		double b = 2*ray.getVector().dotProduct(ray.getOrigin().subtractPoint(this.center));
		double c = (ray.getOrigin().subtractPoint(center)).dotProduct((ray.getOrigin().subtractPoint(center))) - radius*radius;
		double discriminate = b*b - a*4*c;
		if(discriminate >= 0)
		{
			double t0 = (-b + Math.sqrt(discriminate))/(2*a);
			double t1 = (-b - Math.sqrt(discriminate))/(2*a);
			return (((t0 >= min) && (t0 <= max)) || ((t1 >= min) && (t1 <= max)));
		}
		else
		{
			return false;
		}
	}

	@Override
	public double intersectPoint(Ray ray, double min, double max) 
	{
		double a = ray.getVector().dotProduct(ray.getVector());
		double b = 2*ray.getVector().dotProduct(ray.getOrigin().subtractPoint(this.center));
		double c = (this.center).dotProduct(this.center) - radius*radius;
		double discriminate = b*b - a*4*c;
		if(discriminate >= 0)
		{
			double t0 = (-b + Math.sqrt(discriminate))/(2*a);
			double t1 = (-b - Math.sqrt(discriminate))/(2*a);
			if(Math.min(t0, t1) >= 0) return Math.min(Math.min(t0, t1), max); 
			else return Math.min(Math.max(t0, t1), max);
		}
		else
		{
			return -1;
		}
	}
	
	public Vector3 getNormal(Ray ray)
	{
		double t = this.intersectPoint(ray, 0, 1000000000);
		t = t-0.001;
		Vector3 vector = ray.getVector();
		Point3 origin = ray.getOrigin();
		Point3 phit = new Point3(origin.getX()+t*vector.getX(), origin.getY()+t*vector.getY(), origin.getZ()+t*vector.getZ());
		return Vector3.normalize(new Vector3(phit.subtractPoint(center)));
	}

	@Override
	public float greenLight(Ray ray, LightSource light) {
		double t = this.intersectPoint(ray, 0, 1000000000);
		Vector3 rayV = ray.getVector();
		Point3 rayO = ray.getOrigin();
		Point3 phit = new Point3(rayO.getX()+t*rayV.getX(), rayO.getY()+t*rayV.getY(), rayO.getZ()+t*rayV.getZ());
		Vector3 n = getNormal(ray);
		Vector3 l = new Vector3(light.getPosition().subtractPoint(phit));
		Vector3 v = new Vector3(-rayV.getX(), -rayV.getY(), -rayV.getZ());
		Vector3 vl = v.addVector3(l);
		Vector3 h = Vector3.normalize(vl);
		float ka = this.surface.getAmbient().getGreen();
		float kd = this.surface.getDiffuse().getGreen();
		float ks = this.surface.getSpecular().getGreen();
		return (float) (ka + kd*Math.max(0, n.dotProduct(l))+ ks*Math.pow(Math.max(0, n.dotProduct(h)), surface.getSpecularPower()));
	}

	@Override
	public float redLight(Ray ray, LightSource light) {
		double t = this.intersectPoint(ray, 0, 1000000000);
		Vector3 rayV = ray.getVector();
		Point3 rayO = ray.getOrigin();
		Point3 phit = new Point3(rayO.getX()+t*rayV.getX(), rayO.getY()+t*rayV.getY(), rayO.getZ()+t*rayV.getZ());
		Vector3 n = getNormal(ray);
		Vector3 l = new Vector3(light.getPosition().subtractPoint(phit));
		Vector3 v = new Vector3(-rayV.getX(), -rayV.getY(), -rayV.getZ());
		Vector3 vl = v.addVector3(l);
		Vector3 h = Vector3.normalize(vl);
		float ka = this.surface.getAmbient().getRed();
		float kd = this.surface.getDiffuse().getRed();
		float ks = this.surface.getSpecular().getRed();
		return (float) (ka + kd*Math.max(0, n.dotProduct(l))+ks*Math.pow(Math.max(0, n.dotProduct(h)), surface.getSpecularPower()));
	}

	@Override
	public float blueLight(Ray ray, LightSource light) {
		double t = this.intersectPoint(ray, 0, 1000000000);
		Vector3 rayV = ray.getVector();
		Point3 rayO = ray.getOrigin();
		Point3 phit = new Point3(rayO.getX()+t*rayV.getX(), rayO.getY()+t*rayV.getY(), rayO.getZ()+t*rayV.getZ());
		Vector3 n = getNormal(ray);
		Vector3 l = new Vector3(light.getPosition().subtractPoint(phit));
		Vector3 v = new Vector3(-rayV.getX(), -rayV.getY(), -rayV.getZ());
		Vector3 vl = v.addVector3(l);
		Vector3 h = Vector3.normalize(vl);
		float ka = this.surface.getAmbient().getBlue();
		float kd = this.surface.getDiffuse().getBlue();
		float ks = this.surface.getSpecular().getBlue();
		return (float) (ka + kd*Math.max(0, n.dotProduct(l))+ks*Math.pow(Math.max(0, n.dotProduct(h)), surface.getSpecularPower()));
	}
}
