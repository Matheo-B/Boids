import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    Visual visual;
    Boids boids;

    final int HEIGHT = 700;
    final int WIDTH = 700;
    final int frameRate = 60;
    final int msTimeStep = 1000/frameRate;


    public Main(){
        this.boids = new Boids(this.HEIGHT, this.WIDTH);
        this.visual = new Visual(this.boids, this.HEIGHT, this.WIDTH);

        // periodic call to the timer
        Timer timer = new Timer(msTimeStep, this);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Boids");
            Main main = new Main();

            frame.add(main.visual);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.boids.updateBirds();
        this.visual.repaint();
    }
}
