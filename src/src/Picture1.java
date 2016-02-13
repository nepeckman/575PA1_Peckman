package src;
import java.nio.Buffer;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import geometry.Point3;
import geometry.Ray;
import geometry.Sphere;
import scene.Camera;
import scene.Pixel;
import scene.SurfaceInfo;
import scene.Viewrect;

import javax.swing.JFrame;

public class Picture1 extends JFrame implements GLEventListener {
	final static String name = "Nicolas";
	
	final int width = 512;
	final int height = 512;
	
	Buffer scene;
	Sphere sphere1 = new Sphere(2, new Point3(0, 0, -7), new SurfaceInfo(null, null, null, 0));
	Camera camera = new Camera(new Point3(0, 0, 0));
	Viewrect viewrect = new Viewrect(-0.1, 0.1, -0.1, 0.1, 0.1, width, height, camera);
	
	public Picture1() {
		super(name + "'s Raytracer");
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
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = (y * height) + x;
				i *= 3;
				Ray ray = camera.getRay(new Pixel(x, y, viewrect));
				if(sphere1.intersectsRay(ray, 0, 1000000000))
				{
					data[i + 0] = 1.0f; // red
					data[i + 1] = 1.0f; // green
					data[i + 2] = 1.0f; // blue
				}
				else
				{
					data[i + 0] = 0.0f; // red
					data[i + 1] = 0.0f; // green
					data[i + 2] = 0.0f; // blue
				}
			}
		}
		
		return FloatBuffer.wrap(data);
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
		Picture1 pic1 = new Picture1();
	}
}