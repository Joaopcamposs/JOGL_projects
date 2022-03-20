import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Window
{
    private Renderer renderer;

    public Window()
    {
        // Cria janela
        JFrame janela = new JFrame("Animacao do Sistema Solar");
        janela.setBounds(50,100,500,500);
        janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        Container caixa=janela.getContentPane();
        caixa.setLayout(layout);

        // Cria um objeto GLCapabilities para especificar o n�mero de bits
        // por pixel para RGBA
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities c = new GLCapabilities(profile);
        c.setRedBits(8);
        c.setBlueBits(8);
        c.setGreenBits(8);
        c.setAlphaBits(8);

        // Cria o objeto que ir� gerenciar os eventos
        renderer = new Renderer();

        // Cria um canvas, adiciona na janela, e especifica o objeto "ouvinte"
        // para os eventos Gl, de mouse e teclado
        GLCanvas canvas = new GLCanvas(c);
        janela.add(canvas,BorderLayout.CENTER);
        canvas.addGLEventListener(renderer);
        canvas.addMouseListener(renderer);
        canvas.addKeyListener(renderer);
        janela.setVisible(true);
        canvas.requestFocus();

        final FPSAnimator anim = new FPSAnimator(canvas,5);

        janela.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                anim.stop();
                System.exit(0);
            }
        });

        anim.start();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Window wd = new Window();
    }
}
