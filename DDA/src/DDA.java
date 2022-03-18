import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.util.Random;
import javax.swing.JFrame;

public class DDA implements GLEventListener {
    void dda(int x1,int y1, int x2,int y2,GL2 gl){
        int step, l;

        float x, y, xInc, yInc;

        step = Math.abs(x2 - x1);
        if (Math.abs(y2 - y1) > step) {
            step = Math.abs(y2 - y1);
        }
        xInc = (float) (x2 - x1) / step;
        yInc = (float) (y2 - y1) / step;
        x = x1;
        y = y1;

        while (x < x2||y!=y2) {
            System.out.println("X,Y "+x+","+y);

            gl.glVertex2i(Math.round(x), Math.round(y));
            x += xInc;
            y += yInc;
        }
        System.out.println("X,Y "+x+","+y);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(0, 1f, 0);

        float i = 1;

        Random gerador = new Random();
        int X = 2;

        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        gl.glLineWidth(50);
        gl.glBegin(GL.GL_LINES);


        dda(  0,  0, 0,10,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(  0,  0,10, 0,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(  0,-10, 0, 0,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(-10,  0, 0, 0,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(  0,  0, 10,10,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(  0,  0,-10, 10,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(  -10,-10, 0, 0,gl);
        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        dda(10,  -10, 0, 0,gl);
        gl.glEnd();
        gl.glDisable(GL2.GL_LINES);
        i = i + 1;
        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glMatrixMode(GL2.GL_MATRIX_MODE);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-20f, 20f, -15f, 15f);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {

        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);

        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new DDA());

        JFrame frame = new JFrame("Desenho Primitivas");

        frame.add(canvas);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
