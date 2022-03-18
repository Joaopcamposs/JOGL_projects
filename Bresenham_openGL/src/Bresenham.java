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

public class Bresenham implements GLEventListener {

    void bresenhamCompleto(int x1, int y1, int x2, int y2, GL2 gl) {
        int dx, dy, d, x, y;
        int inclinacao = 0;
        dx = x2 - x1;
        dy = y2 - y1;

        if (dx < 0) {
            bresenhamCompleto(x2, y2, x1, y1, gl);
            return;
        }
        if (dy < 0) {
            inclinacao = -1;
        } else {
            inclinacao = 1;
        }

        x = x1;
        y = y1;
        System.out.println("X,Y " + x + "," + y);

        gl.glVertex2i(x, y);
        if (dx >= inclinacao * dy) { //m<=1
            if (dy <= 0) {//caso y2 < y1
                d = 2 * (dy + dx);
                while (x < x2) {
                    if (d < 0) { // escolhido Ã© o ponto de baixo
                        d += 2 * (dy + dx);
                        x++;
                        y--;
                    } else { // escolhido Ã© o ponto de cima
                        d += 2 * dy;
                        x++;
                    }
                    System.out.println("X,Y " + x + "," + y);
                    gl.glVertex2i(x, y);
                }
            } else {//y1 < y2
                d = 2 * (dy - dx);
                while (x < x2) {
                    if (d <= 0) {// escolhido Ã© o ponto de baixo
                        d += (2 * dy);
                        x++;
                    } else {// escolhido Ã© o ponto de cima
                        d += 2 * (dy - dx);
                        x++;
                        y++;
                    }
                    System.out.println("X,Y " + x + "," + y);
                    gl.glVertex2i(x, y);
                }
            }
        } else {//|m| > 1
            if (dy < 0) { // caso y2<y1
                d = dy + (2 * dx);
                while (y > y2) {
                    if (d <= 0) {
                        d += 2 * dx;
                        y--; // varia apenas no eixoy
                    } else {
                        d += 2 * (dy + dx);
                        x++;
                        y--;
                    }
                    System.out.println("X,Y " + x + "," + y);
                    gl.glVertex2i(x, y);
                }
            } else { // caso y1<y2
                d = dy - (2 * dx);
                while (y < y2) {
                    if (d <= 0) {
                        d += 2 * (dy - dx);
                        x++;
                        y++;
                    } else {
                        d += -2 * dx;
                        y++; // varia apenas no eixoy
                    }
                    System.out.println("X,Y " + x + "," + y);
                    gl.glVertex2i(x, y);
                }
            }
        }
        System.out.println("X,Y " + x + "," + y);
        gl.glVertex2i(x2, y2);
    }

    @Override
    public void display(GLAutoDrawable drawable
    ) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(0, 1f, 0);

        float i = 1;

        Random gerador = new Random();
        int X = 2;

        gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
        gl.glLineWidth(50);
        gl.glBegin(GL.GL_LINES);

        int k =0;

        while(k<25){
            gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
            bresenhamCompleto(-10*k,  10*k, 10*k, 10*k,gl);
            gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
            bresenhamCompleto(10*k,  10*k, 10*k, -10*k,gl);
            gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
            bresenhamCompleto(10*k,  -10*k, -10*k, -10*k,gl);
            gl.glColor3f(gerador.nextFloat(), gerador.nextFloat(), gerador.nextFloat());
            bresenhamCompleto(-10*k,  -10*k, -10*k, 10*k,gl);
            k=k+1;
        }

        i = i + 1;
        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0
    ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(GLAutoDrawable drawable
    ) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glMatrixMode(GL2.GL_MATRIX_MODE);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-200f, 200f, -150f, 150f);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4
    ) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {

        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);

        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new Bresenham());

        JFrame frame = new JFrame("Desenho Primitivas");

        frame.add(canvas);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
