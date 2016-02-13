package geometry;

import scene.LightSource;

public interface Surface {

	public boolean intersectsRay(Ray ray, double min, double max);
	public double intersectPoint(Ray ray, double min, double max);
	public float greenLight(Ray ray, LightSource light);
	public float redLight(Ray ray, LightSource light);
	public float blueLight(Ray ray, LightSource light);
}
