import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Renderer implements GLEventListener {

    private float alpha;
    private float beta;
    private float delta;
    private float gamma;
    private float omega;
    private float phi;
    private float pi;
    private float mi;
    private float zeta;

    public Renderer(){
        alpha = 0;
        beta = 0;
        delta = 0f;
        gamma = 0f;
        omega = 0f;
        phi = 0f;
        pi =0f;
        zeta = 0f;

    }
    @Override
    public void display(GLAutoDrawable drawable) {
        sol(drawable);
    }
    public void sol(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // x-axis and y-axis
        gl.glLoadIdentity();

        gl.glEnd();

        //SOL
        gl.glColor3f(1, 1,0);
        glut.glutWireSphere(0.06f, 30, 30);

        //NETUNO
        gl.glColor3f(0, 0, 1);
        gl.glRotatef(pi, 0.0f, 0.0f, 1.0f);

        gl.glTranslatef(0.9f, 0.0f, 0);
        glut.glutWireSphere(0.02f, 30, 30);
        gl.glTranslatef(-0.9f, 0.0f, 0);

        //URANO
        pi = pi + 0.125f;
        gl.glColor3f(0, 0.6f, 0.8f);
        gl.glRotatef(pi, 0.0f, 0.0f, 1.0f);
        //gl.glPopMatrix();

        gl.glTranslatef(0.8f, 0.0f, 0);
        glut.glutWireSphere(0.025f, 30, 30);
        gl.glTranslatef(-0.8f, 0.0f, 0);
        //gl.glPushMatrix();/

        //SATURNO
        phi = phi + 0.15f;
        gl.glColor3f(0.8f, 0.5f, 0.5f);
        gl.glRotatef(phi, 0.0f, 0.0f, 1.0f);
        //gl.glPopMatrix();

        gl.glTranslatef(0.7f, 0.0f, 0);
        glut.glutWireTorus(0.001f,0.04f, 30,30);
        gl.glColor3f(0.7f, 0.5f, 0.5f);
        glut.glutWireSphere(0.025f, 30, 30);
        gl.glTranslatef(-0.7f, 0.0f, 0);
        //gl.glPushMatrix();/

        //JUPITER
        omega = omega + 0.175f;
        gl.glColor3f(0.25f, 0.25f, 0.25f);
        gl.glRotatef(omega, 0.0f, 0.0f, 1.0f);

        gl.glTranslatef(0.5f, 0.0f, 0);
        glut.glutWireSphere(0.025f, 30, 30);
        gl.glTranslatef(-0.5f, 0.0f, 0);

        //MARTE
        gamma = gamma + 0.20f;
        gl.glColor3f(1, 0, 0);
        gl.glRotatef(gamma, 0.0f, 0.0f, 1.0f);

        gl.glTranslatef(0.35f, 0.0f, 0);
        glut.glutWireSphere(0.015f, 30, 30);
        gl.glTranslatef(-0.35f, 0.0f, 0);

        //TERRA
        delta = delta + 0.225f;
        gl.glColor3f(0.5f, 0.5f, 1f);
        gl.glRotatef(delta, 0.0f, 0.0f, 1.0f);
        //gl.glPopMatrix();

        gl.glTranslatef(0.23f, 0.0f, 0);
        glut.glutWireSphere(0.02f, 30, 30);

        zeta = delta*48;
        gl.glRotatef(zeta, 0.23f, 0.0f, 1.0f);
        gl.glTranslatef(0.05f, 0.0f, 0);
        glut.glutWireSphere(0.005f, 30, 30);
        gl.glTranslatef(-0.05f, 0.0f, 0);
        gl.glRotatef(-zeta, 0.23f, 0.0f, 1.0f);
        gl.glTranslatef(-0.23f, 0.0f, 0);

        beta = beta + 0.25f;

        //VENUS
        gl.glColor3f(1, 0.8f, 1);
        gl.glRotatef(beta, 0.0f, 0.0f, 1.0f);

        gl.glTranslatef(0.15f, 0.0f, 0);
        glut.glutWireSphere(0.015f, 30, 30);
        gl.glTranslatef(-0.15f, 0.0f, 0);

        alpha = alpha + 0.3f;

        //MERCURIO
        gl.glColor3f(0.7f, 0.5f, 0.3f);
        gl.glRotatef(alpha, 0.0f, 0.0f, 1.0f);
        //gl.glPopMatrix();

        gl.glTranslatef(0.1f, 0.0f, 0);
        glut.glutWireSphere(0.0125f, 30, 30);
        gl.glTranslatef(-0.1f, 0.0f, 0);
    }

    public void displayExemplo3(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // x-axis and y-axis
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(-1f, 0);
        gl.glVertex2f(1f, 0);
        gl.glVertex2f(0, 1);
        gl.glVertex2f(0, -1f);
        gl.glEnd();

        // RED-RECTANGLE
        gl.glColor3f(1, 0, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        // TRANSLADE GREEN RECTANGLE
        gl.glColor3f(0, 1, 0);
        gl.glTranslatef(-0.4f, 0.1f, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        gl.glPushMatrix();// save the MODELVIEW on stack

        // ROTATE BLUE RECTANGLE
        gl.glColor3f(0, 0, 1);
        gl.glRotatef(90, 0, 0, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        gl.glPopMatrix();// Restore the MODELVIEW from stack

        // SCALE + TRANSLATE MAGENTA RECTANGLE
        gl.glColor3f(1, 0, 1);
        gl.glScalef(-0.5f, 1.0f, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        gl.glFlush();
    }

    public void displayExemplo2(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        // x-axis and y-axis
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(-1f, 0);
        gl.glVertex2f(1f, 0);
        gl.glVertex2f(0, 1);
        gl.glVertex2f(0, -1f);
        gl.glEnd();

        // RED-RECTANGLE
        gl.glColor3f(1, 0, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        // TRANSLADE GREEN RECTANGLE
        gl.glColor3f(0, 1, 0);
        gl.glTranslatef(-0.4f, 0.1f, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        // ROTATE+TRANSLATE BLUE RECTANGLE
        gl.glColor3f(0, 0, 1);
        gl.glLoadIdentity();// rest the MODELVIEW matrix
        gl.glRotatef(90, 0, 0, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        // SCALE + ROTATE + TRANSLATE MAGENTA RECTANGLE
        gl.glColor3f(1, 0, 1);
        gl.glLoadIdentity();// rest the MODELVIEW matrix
        gl.glScalef(-0.5f, 1.0f, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        gl.glFlush();
    }

    public void displayExemplo1(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        //x-axis and y-axis
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(-1f, 0);
        gl.glVertex2f(1f, 0);
        gl.glVertex2f(0, 1);
        gl.glVertex2f(0, -1f);
        gl.glEnd();

        //RED-RECTANGLE
        gl.glColor3f(1, 0, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        //TRANSLADE GREEN RECTANGLE
        gl.glColor3f(0, 1, 0);
        gl.glTranslatef(-0.4f, 0.1f, 0);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        //ROTATE+TRANSLATE BLUE RECTANGLE
        gl.glColor3f(0, 0, 1);
        gl.glRotatef(90, 0, 0, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        //SCALE + ROTATE + TRANSLATE MAGENTA RECTANGLE
        gl.glColor3f(1, 0, 1);
        gl.glScalef(-0.5f, 1.0f, 1.0f);
        gl.glRectf(0.1f, 0.2f, 0.4f, 0.3f);

        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        //new CoordenadaHomogenea();
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);

        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new Renderer());

        FPSAnimator animator =  new FPSAnimator(canvas,30);

        JFrame frame = new JFrame("Coordenadas Homogeneas");
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);

        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        animator.start();
    }
}
