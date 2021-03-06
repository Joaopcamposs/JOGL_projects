import java.awt.event.*;
import com.jogamp.opengl.*;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT_AND_DIFFUSE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_COLOR_MATERIAL;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.glu.*;
import com.jogamp.opengl.util.gl2.GLUT;

public class Renderer extends MouseAdapter implements GLEventListener, KeyListener
{
    // Atributos
    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private GLAutoDrawable glDrawable;
    private double angle, fAspect;
    private float rotX, rotY, obsZ, angRotLua, angRotTerra;
    private float angRotMerc, angRotVenus, angRotMarte, angRotJup, angRotSat, angRotUrano, angRotNet;

    public Renderer()
    {
        // Especifica o �ngulo da proje��o perspectiva
        angle=60;
        // Inicializa o valor para corre��o de aspecto
        fAspect = 1;

        // Inicializa os atributos usados para alterar a posi��o do
        // observador virtual (=c�mera)
        rotX = 90;
        rotY = 0;
        obsZ = 200;
        angRotLua = 0;
        angRotTerra = 0;
        angRotMerc = 0;
        angRotVenus = 0;
        angRotMarte = 0;
        angRotJup = 0;
        angRotSat = 0;
        angRotUrano = 0;
        angRotNet = 0;
    }

    public void defineIluminacao()
    {
        // Define os parâmetros através de vetores RGBA - o último valor deve ser sempre 1.0f
        float luzAmbiente[]={0.2f, 0.2f, 0.2f, 1.0f};
        float luzDifusa[]={0.7f, 0.7f, 0.7f, 1.0f};
        float luzEspecular[]={1.0f, 1.0f, 1.0f, 1.0f};
        float posicaoLuz[]={1.0f, 1.0f, 1.0f, 1.0f};

        // Ativa o uso da luz ambiente
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);
        // Define os parâmetros da luz de número 0
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0 );
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0 );

        // Brilho do material
        float especularidade[]={1.0f, 1.0f, 1.0f, 1.0f};
        int especMaterial = 10;
    }

    public void init(GLAutoDrawable drawable)
    {
        glDrawable = drawable;
        gl = (GL2) drawable.getGL();
        glu = new GLU();
        glut = new GLUT();

        drawable.setGL(gl);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
        gl.glShadeModel(GL2.GL_SMOOTH);
    }

    public void display(GLAutoDrawable drawable)
    {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
        gl.glLoadIdentity();

        especificaParametrosVisualizacao();
        defineIluminacao();

        gl.glLineWidth(2);

        // Desenha o sol
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        glut.glutSolidSphere(18, 20, 20);

        // Desenha Merc�rio
        gl.glPushMatrix();
        gl.glRotatef(angRotMerc,0,0,1);
        angRotMerc	+= 5.5f;
        if(angRotMerc>360)
            angRotMerc = 0;
        gl.glTranslatef(26, 0, 0);
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        glut.glutSolidSphere(0.8f, 20, 20);
        gl.glPopMatrix();

        // Desenha V�nus
        gl.glPushMatrix();
        gl.glRotatef(angRotVenus,0,0,1);
        angRotVenus	+= 4.6f;
        if(angRotVenus>360)
            angRotVenus = 0;
        gl.glTranslatef(34, 0, 0);
        gl.glColor3f(1.0f, 0.5f, 0.0f);
        glut.glutSolidSphere(1.6f, 20, 20);
        gl.glPopMatrix();

        // Desenha Terra
        gl.glPushMatrix();
        gl.glRotatef(angRotTerra,0,0,1);
        angRotTerra	+= 5;
        if(angRotTerra>360)
            angRotTerra = 0;
        gl.glTranslatef(42, 0, 0);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        glut.glutSolidSphere(2.4, 20, 20);
        // Desenha a Lua
        gl.glPushMatrix();
        gl.glRotatef(angRotLua,0,1,0);
        angRotLua += 10;
        if(angRotLua>360)
            angRotLua = 0;
        gl.glTranslatef(4.0f, 0, 0);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        glut.glutSolidSphere(0.5f, 20, 20);
        gl.glPopMatrix();
        gl.glPopMatrix();

        // Desenha Marte
        gl.glPushMatrix();
        gl.glRotatef(angRotMarte,0,0,1);
        angRotMarte	+= 4.8f;
        if(angRotMarte>360)
            angRotMarte = 0;
        gl.glTranslatef(52, 0, 0);
        gl.glColor3f(0.6f, 0.52f, 0.48f);
        glut.glutSolidSphere(1.2, 20, 20);
        gl.glPopMatrix();

        // Desenha J�piter
        gl.glPushMatrix();
        gl.glRotatef(angRotJup,0,0,1);
        angRotJup += 6f;
        if(angRotJup>360)
            angRotJup = 0;
        gl.glTranslatef(63, 0, 0);
        gl.glColor3f(0.6f, 0.6f, 0.65f);
        glut.glutSolidSphere(6, 20, 20);
        gl.glPopMatrix();

        // Desenha Saturno
        gl.glPushMatrix();
        gl.glRotatef(angRotSat,0,0,1);
        angRotSat += 5.5f;
        if(angRotSat>360)
            angRotSat = 0;
        gl.glTranslatef(78, 0, 0);
        gl.glColor3f(0.6f, 0.6f, 0.65f);
        glut.glutSolidSphere(4.0f, 20, 20);
        gl.glRotatef(-45,0,1,0);
        gl.glScalef(1f, 1f, 0.2f);
        glut.glutWireTorus(0.8f,6f,20,20);
        gl.glPopMatrix();

        // Desenha Urano
        gl.glPushMatrix();
        gl.glRotatef(angRotUrano,0,0,1);
        angRotUrano += 6.2f;
        if(angRotUrano>360)
            angRotUrano = 0;
        gl.glTranslatef(90, 0, 0);
        gl.glColor3f(0.5f, 0.5f, 0.8f);
        glut.glutSolidSphere(3.0f, 20, 20);
        gl.glPopMatrix();

        // Desenha Netuno
        gl.glPushMatrix();
        gl.glRotatef(angRotNet,0,0,1);
        angRotNet += 7f;
        if(angRotNet>360)
            angRotNet = 0;
        gl.glTranslatef(100, 0, 0);
        gl.glColor3f(0.3f, 0.7f, 1f);
        glut.glutSolidSphere(4.0f, 20, 20);
        gl.glPopMatrix();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        gl.glViewport(0, 0, width, height);
        fAspect = (float)width/(float)height;
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) { }

    public void posicionaObservador()
    {
        // Especifica sistema de coordenadas do modelo
        gl.glMatrixMode(GL_MODELVIEW);
        // Inicializa sistema de coordenadas do modelo
        gl.glLoadIdentity();
        // Especifica posi��o do observador e do alvo
        gl.glTranslatef(0,0,-obsZ);
        gl.glRotatef(rotX,1,0,0);
        gl.glRotatef(rotY,0,1,0);
    }

    public void especificaParametrosVisualizacao()
    {
        // Especifica sistema de coordenadas de proje��o
        gl.glMatrixMode(GL_PROJECTION);

        // Inicializa sistema de coordenadas de proje��o
        gl.glLoadIdentity();

        // Especifica a proje��o perspectiva(angulo,aspecto,zMin,zMax)
        glu.gluPerspective(angle, fAspect, 0.2, 500);

        posicionaObservador();
    }

    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1) // Zoom in
            if (angle >= 4) angle -= 4;
        if (e.getButton() == MouseEvent.BUTTON3) // Zoom out
            if (angle <= 72) angle += 4;
        glDrawable.display();
    }

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:		rotY--;
                break;
            case KeyEvent.VK_RIGHT:		rotY++;
                break;
            case KeyEvent.VK_UP:		rotX++;
                break;
            case KeyEvent.VK_DOWN:		rotX--;
                break;
            case KeyEvent.VK_HOME:		obsZ++;
                break;
            case KeyEvent.VK_END:		obsZ--;
                break;
            case KeyEvent.VK_ESCAPE:	System.exit(0);
                break;
        }
        glDrawable.display();
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) { }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}