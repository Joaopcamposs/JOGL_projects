import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

public class Renderer extends KeyAdapter implements GLEventListener{

    private float transX;
    private float transY;
    private float alpha;
    private float sX;
    private float sY;

    public Renderer() {
        transX = 0f;
        transY = 0f;
        alpha = 0f;
        sX = 1f;
        sY = 1f;
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        drawGrid(gl);
        drawAxis(gl);

        //Aplica as transforma��es apenas nesse grupo de comandos
        gl.glPushMatrix();
        gl.glTranslatef(transX, transY, 0);
        gl.glRotatef(alpha, 0, 0, 1.0f);//rotaciona em 90 graus
        gl.glScalef(sX, sY, 0);
        drawTriangle(gl);
        gl.glPopMatrix();
        gl.glFlush();
    }

    //Desenho do Tri�ngulo feito a partir da origem do Sistema de Coordenadas
    private void drawTriangle(GL2 gl) {

        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(25f, 50f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(50f, 0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(0f, 0f);
        gl.glEnd();
    }

    //Desenha o Grid de Plano de Fundo
    private void drawGrid(GL2 gl) {

        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glLineWidth(1f);
        gl.glBegin(GL.GL_LINES);

        //linhas horizontais
        for(int i = -100; i < 100; i+=10) {
            for(int j = -100; j < 100; j+=10) {
                gl.glVertex2i(i, j);
                gl.glVertex2i(i * (-1), j);
            }
        }

        //linhas verticais
        for(int i = -100; i < 100; i+=10) {
            for(int j = -100; j < 100; j+=10) {
                gl.glVertex2i(j, i);
                gl.glVertex2i(j, i * (-1));
            }
        }

        gl.glEnd();
    }

    //Desenha as linhas dos eixos X, Y
    private void drawAxis(GL2 gl) {

        gl.glColor3f(0.8f, 0.8f, 0.8f);
        gl.glLineWidth(3f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2i(-100, 0);
        gl.glVertex2i(100, 0);
        gl.glVertex2i(0, 100);
        gl.glVertex2i(0, -100);
        gl.glEnd();
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-100.0, 100.0, -100, 100.0);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                transX -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                transX += 10;
                break;
            case KeyEvent.VK_UP:
                transY += 10;
                break;
            case KeyEvent.VK_DOWN:
                transY -= 10;
                break;
            case KeyEvent.VK_ADD:
                sY += 0.1;
                break;
            case KeyEvent.VK_SUBTRACT:
                sY -= 0.1;
                break;
            case KeyEvent.VK_A:
                alpha -= 30;
                break;
            case KeyEvent.VK_D:
                alpha += 30;
                break;
        }
    }

    public static void main(String[] args) {

        //acelera o rendering
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);
        cap.setHardwareAccelerated(true);

        //cria o painel e adiciona um ouvinte GLEventListener
        Renderer render = new Renderer();
        GLCanvas canvas = new GLCanvas(cap);
        canvas.addGLEventListener(render);

        //cria uma janela e adiciona o painel
        JFrame frame = new JFrame("Transforma��es Geom�tricas 2D");
        frame.getContentPane().add(canvas);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //inicializa o sistema e chama display() a 2fps
        FPSAnimator animator = new FPSAnimator(canvas, 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(render);
        animator.start();
    }
}
