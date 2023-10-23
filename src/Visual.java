import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Visual extends JPanel {

    Boids boids;
    int HEIGHT;
    int WIDTH;

    public Visual(Boids boids, int height, int width){
        this.boids = boids;
        this.HEIGHT = height;
        this.WIDTH = width;

        setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Bird> birds = this.boids.birds;
        Polygon poly;
        Vector2d[] vectors;
        for (Bird bird : birds) {
            vectors = bird.getTriangle();
            poly = this.getPolygonFromVector2d(vectors);
            g.setColor(bird.color);
            g.fillPolygon(poly);
        }
    }

    public Polygon getPolygonFromVector2d(Vector2d[] vectors){
        final int numberOfVectors = vectors.length;
        final int[] xpoints = new int[numberOfVectors];
        final int[] ypoints = new int[numberOfVectors];
        for (int i = 0; i < numberOfVectors; ++i){
            xpoints[i] = (int)vectors[i].x;
            ypoints[i] = (int)vectors[i].y;
        }
        return new Polygon(xpoints, ypoints, numberOfVectors);
    }
}
