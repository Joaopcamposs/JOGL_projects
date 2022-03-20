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
    private int X;
    private int Y;
    private int X1;
    private int Y1;

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

        //Aplica as transforma��es apenas nesse grupo de comandos
        gl.glPushMatrix();
        gl.glTranslatef(transX, transY, 0);
        gl.glRotatef(alpha, 0, 0, 1.0f);//rotaciona em 90 graus
        gl.glScalef(sX, sY, 0);

        gl.glMatrixMode(GL2.GL_VIEWPORT);
        gl.glViewport(X+0,Y+0,X1+500,Y1+500);
        drawCasa(gl, 1.5f, -20.0f, -20.0f);

        gl.glMatrixMode(GL2.GL_VIEWPORT);
        gl.glViewport(X+300,Y+89,X1+250,Y1+250);
        drawCasa(gl, 1.5f, -20.0f, -20.0f);

        gl.glMatrixMode(GL2.GL_VIEWPORT);
        gl.glViewport(X+460,Y+142,X1+100,Y1+100);
        drawCasa(gl, 1.5f, -20.0f, -20.0f);

        gl.glPopMatrix();
        gl.glFlush();
    }


    private void drawCasa(GL2 gl, float pro, float posx, float posy){

        // estrela
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex2f(pro*(posx+39),pro*(posy+66));
        gl.glVertex2f(pro*(posx+35),pro*(posy+66));
        gl.glVertex2f(pro*(posx+38), pro*(posy+62));
        gl.glVertex2f(pro*(posx+37), pro*(posy+58));
        gl.glVertex2f(pro*(posx+40), pro*(posy+60));
        gl.glVertex2f(pro*(posx+43), pro*(posy+58));
        gl.glVertex2f(pro*(posx+42), pro*(posy+62));
        gl.glVertex2f(pro*(posx+45), pro*(posy+66));
        gl.glVertex2f(pro*(posx+41), pro*(posy+65));
        gl.glEnd();

        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex2f(pro*(posx+40), pro*(posy+70));
        gl.glVertex2f(pro*(posx+39), pro*(posy+66));
        gl.glVertex2f(pro*(posx+41), pro*(posy+65));
        gl.glEnd();

        // quadrado casa
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f( pro*(posx+0), pro*(posy+40));
        gl.glVertex2f( pro*(posx+40), pro*(posy+40));
        gl.glVertex2f( pro*(posx+40), pro*(posy+0));
        gl.glVertex2f( pro*(posx+0), pro*(posy+0));
        gl.glEnd();

        // telhado
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glVertex2f(pro*(posx+20), pro*(posy+60));
        gl.glVertex2f(pro*(posx+40), pro*(posy+40));
        gl.glVertex2f(pro*(posx+0), pro*(posy+40));
        gl.glEnd();

        // graminha

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 1.0f, 0f);
        gl.glVertex2f( pro*(posx+(-10)), pro*(posy+0));
        gl.glVertex2f( pro*(posx+50), pro*(posy+0));
        gl.glVertex2f( pro*(posx+50), pro*(posy+2));
        gl.glVertex2f( pro*(posx+(-10)), pro*(posy+2));
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

            case KeyEvent.VK_RIGHT:
                X += 100;
                break;
            case KeyEvent.VK_LEFT:
                X -= 100;
                break;
            case KeyEvent.VK_UP:
                Y += 100;
                break;
            case KeyEvent.VK_DOWN:
                Y -= 100;
                break;
            case KeyEvent.VK_D:
                X1 += 100;
                break;
            case KeyEvent.VK_A:
                X1 -= 100;
                break;
            case KeyEvent.VK_W:
                Y1 += 100;
                break;
            case KeyEvent.VK_S:
                Y1 -= 100;
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
        JFrame frame = new JFrame("Transformacoes Geometricas 2D");
        frame.getContentPane().add(canvas);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //inicializa o sistema e chama display() a 60fps
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(render);
        animator.start();
    }
}
