package scene;

public class Viewrect {

	private double l;
	private double r;
	private double b;
	private double t;
	private double d;
	private int pixel_width;
	private int pixel_height;
	private Camera camera;
	
	public Viewrect(double l, double r, double b, double t, double d, int pixel_width, int pixel_height, Camera camera)
	{
		this.l = l;
		this.r = r;
		this.b = b;
		this.t = t;
		this.d = d;
		this.pixel_width = pixel_width;
		this.pixel_height = pixel_height;
		this.camera = camera;
	}
	
	public double getL()
	{
		return this.l;
	}
	
	public double getR()
	{
		return this.r;
	}
	
	public double getB()
	{
		return this.b;
	}
	
	public double getT()
	{
		return this.t;
	}
	
	public double getD()
	{
		return this.d;
	}
	
	public double getPixelWidth()
	{
		return this.pixel_width;
	}
	
	public double getPixelHeight()
	{
		return this.pixel_height;
	}
	
	public Camera getCamera()
	{
		return this.camera;
	}
}
