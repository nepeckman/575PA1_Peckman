package src;
import java.nio.Buffer;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import geometry.*;
import scene.*;

import javax.swing.JFrame;

public class Picture3 extends JFrame implements GLEventListener {
	final static String name = "Nicolas";
	
	final int width = 512;
	final int height = 512;
	
	Buffer scene;
	Plane plane;
	Sphere sphere1;
	Sphere sphere2;
	Sphere sphere3;
	Surface[] surfaces;
	Camera camera;
	LightSource light;
	Viewrect viewrect;
	
	public Picture3() {
		super(name + "'s Raytracer: Scene 3");
		plane = new Plane(new Point3(0, -2, 0), new Vector3(0, 1, 0), new SurfaceInfo(new Light(0.2f,0.2f,0.2f), new Light(1,1,1), new Light(0,0,0), 0));
		sphere1 = new Sphere(1, new Point3(-4, 0, -7), new SurfaceInfo(new Light(0.2f,0,0), new Light(1,0,0), new Light(0,0,0), 0));
		sphere2 = new Sphere(2, new Point3(0, 0, -7), new SurfaceInfo(new Light(0,0.2f,0), new Light(0,0.5f,0), new Light(0.5f,0.5f,0.5f), 32));
		sphere3 = new Sphere(1, new Point3(4, 0, -7), new SurfaceInfo(new Light(0,0,0.2f), new Light(0,0,1), new Light(0,0,0), 0));
		surfaces = new Surface[4];
		surfaces[0] = plane;
		surfaces[1] = sphere1;
		surfaces[2] = sphere2;
		surfaces[3] = sphere3;
		camera = new Camera(new Point3(0, 0, 0));
		light = new LightSource(new Point3(-4, 4, -3));
		viewrect = new Viewrect(-0.1, 0.1, -0.1, 0.1, 0.1, width, height, camera);
		this.scene = renderScene();
		
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(profile);
		
		GLCanvas canvas = new GLCanvas(caps);
		canvas.addGLEventListener(this);
		
		this.setName(name + "'s Raytracer");
		this.getContentPane().add(canvas);
		
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		canvas.requestFocusInWindow();
	}
	
	public Buffer renderScene() {
		float[] data = new float[width * height * 3];
		float[][] samples = new float[64][width * height * 3];
		for(int idx = 0; idx < samples.length; idx++)
		{
			float[] sample = new float[width * height * 3];
			for (int y = 0; y < height; y++) 
			{
				for (int x = 0; x < width; x++) 
				{
					int i = (y * height) + x;
					i *= 3;
					Point3 center = new Pixel(x, y, viewrect).getPosition();
					Point3 variation = new Point3(0.0002*Math.random(), 0.0002*Math.random(), 0);
					int sign = (int) Math.ceil(2*Math.random());
					Point3 point = (sign == 1) ? center.subtractPoint(variation) : center.addPoint(variation);
					Ray ray = camera.getRay(point);
					float[] pixelcolor = pixelColor(ray);
					sample[i + 0] = pixelcolor[0];
					sample[i + 1] = pixelcolor[1];
					sample[i + 2] = pixelcolor[2];
				}
			}
			samples[idx] = sample;
		}
		for(int pIdx = 0; pIdx<samples[0].length; pIdx++)
		{
			for(int sIdx = 0; sIdx<samples.length; sIdx++)
			{
				data[pIdx] += samples[sIdx][pIdx]/64;
			}
		}
		return FloatBuffer.wrap(data);
	}
	
	private float[] pixelColor(Ray ray)
	{
		float[] pixelcolor = new float[3];
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
		if(hit_surface != null)
		{
			pixelcolor[0] = (float) Math.pow(hit_surface.redLight(ray, light, surfaces), 1/2.2);
			pixelcolor[1] = (float) Math.pow(hit_surface.greenLight(ray, light, surfaces), 1/2.2);
			pixelcolor[2] = (float) Math.pow(hit_surface.blueLight(ray, light, surfaces), 1/2.2);
		}
		return pixelcolor;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glDrawPixels(width, height, GL2.GL_RGB, GL2.GL_FLOAT, this.scene);
		
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		
	}
	
	public static void main(String[] args) {
		Picture3 pic3 = new Picture3();
	}
}