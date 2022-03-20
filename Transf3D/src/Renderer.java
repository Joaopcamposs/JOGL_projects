import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Renderer extends KeyAdapter implements GLEventListener {

    private float alpha;
    private float beta;
    private float delta;

    public Renderer(){
        alpha = 0;
        beta = 0;
        delta = 1f;
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        //Aqui carregamos a matriz responsável pelas transformações (vamos falar dela na próxima aula)
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glRotatef(beta, 0, 0, 1f);
        gl.glRotatef(alpha, 1f, 0, 0);
        gl.glScalef(delta, delta, delta);
        gl.glColor3f(1f, 1f, 0);
        gl.glRectf(-0.5f,-0.5f,0.5f,0.5f);

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
        gl.glEnable(GL2.GL_DEPTH_TEST);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1f, 1f, -1f, 1f, -1f, 1f);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_PAGE_UP:
                delta = delta * 1.1f;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                delta = delta * 0.5f;
                break;
            case KeyEvent.VK_UP:
                alpha = alpha -1;
                break;
            case KeyEvent.VK_DOWN:
                alpha = alpha + 1;
                break;
            case KeyEvent.VK_LEFT:
                beta = beta + 1;
                break;
            case KeyEvent.VK_RIGHT:
                beta = beta -1 ;
                break;
            case KeyEvent.VK_ADD:
                delta = delta +0.1f ;
                break;
            case KeyEvent.VK_SUBTRACT:
                delta = delta -0.1f ;
                if (delta<=0){
                    delta=0.1f;}
                break;
        }
    }

    public static void main(String[] args) {

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);

        Renderer r = new Renderer();
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(r);

        JFrame frame = new JFrame("Aplicação JOGL 3D");
        frame.addKeyListener(r);
        frame.getContentPane().add(canvas);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
}