package scene;

public class Light {

	private float green;
	private float red;
	private float blue;
	
	public Light(float red, float green, float blue)
	{
		this.green = green;
		this.red = red;
		this.blue = blue;
	}
	
	public float getGreen()
	{
		return this.green;
	}
	
	public float getRed()
	{
		return this.red;
	}
	
	public float getBlue()
	{
		return this.blue;
	}
}
