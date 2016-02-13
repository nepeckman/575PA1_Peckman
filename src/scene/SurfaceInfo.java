package scene;

public class SurfaceInfo {

	private Light ambient;
	private Light diffuse;
	private Light specular;
	private float specular_power;
	
	public SurfaceInfo(Light ambient, Light diffuse, Light specular, float specular_power)
	{
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.specular_power = specular_power;
	}
	
	public Light getAmbient()
	{
		return this.ambient;
	}
	
	public Light getDiffuse()
	{
		return this.diffuse;
	}
	
	public Light getSpecular()
	{
		return this.specular;
	}
	
	public float getSpecularPower()
	{
		return this.specular_power;
	}
}
