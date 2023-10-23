import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boids {
    int height;
    int width;

    final Random random;
    final int NUMBER_OF_BIRDS = 150;
    final int PIXEL_MARGIN = 100;
    final double BORDER_FACTOR = 400;
    final double SEPARATION_DISTANCE= 30;
    final double FOLLOW_DISTANCE = 90;
    final double SEPARATION_FACTOR = 1;
    final double ALIGNMENT_FACTOR = 0.5;
    final double COHESION_FACTOR = 0.5;
    final int MIN_VEL = 100;
    final int MAX_VEL = 150;

    long lastTime = System.nanoTime();
    ArrayList<Bird> birds;

    public Boids(int height, int width){
        this.height = height;
        this.width = width;
        this.birds = new ArrayList<>();
        this.random = new Random();
        this.createBirds();
    }

    public void createBirds(){
        Color c;
        double size;
        Vector2d posVec= new Vector2d(0,0);
        Vector2d velVec = new Vector2d(0, 0);
        Bird b;
        for (int i = 0; i < this.NUMBER_OF_BIRDS; ++i){
            c = new Color((int)(this.random.nextDouble() * 0x1000000));
            size = 10;
            posVec.set(this.random.nextInt(this.width), this.random.nextInt(this.height));
            double rx = (this.random.nextDouble()-0.5)*1.75*this.MAX_VEL;
            double ry = (this.random.nextDouble()-0.5)*1.75*this.MIN_VEL;
            velVec.set(rx, ry);
            b = new Bird(posVec.copy(), velVec.copy(), c, size);
            this.birds.add(b);
        }
    }

    public Vector2d getBorderVel(Bird b){
        Vector2d borderVel = new Vector2d(0, 0);
        if (b.pos.x < this.PIXEL_MARGIN){
            borderVel.setX(this.BORDER_FACTOR);
        } else if (b.pos.x > this.width-this.PIXEL_MARGIN) {
            borderVel.setX(-this.BORDER_FACTOR);
        }
        if (b.pos.y < this.PIXEL_MARGIN){
            borderVel.setY(this.BORDER_FACTOR);
        } else if (b.pos.y > this.height-this.PIXEL_MARGIN) {
            borderVel.setY(-this.BORDER_FACTOR);
        }

        return borderVel;
    }

    public Vector2d getSeparationVel(Bird currentBird, ArrayList<Bird> innerBird){
        Vector2d separationVel = new Vector2d(0, 0);
        for (Bird bird : innerBird){
            separationVel = separationVel.add(currentBird.pos.x - bird.pos.x, currentBird.pos.y - bird.pos.y);
        }
        return separationVel.mult(this.SEPARATION_FACTOR);
    }

    public Vector2d getCohesionVel(Bird currentBird, ArrayList<Bird> outerBird){
        Vector2d cohesionVel = new Vector2d(0,0);
        if (outerBird.isEmpty()){
            return cohesionVel;
        }
        Vector2d avgPos = new Vector2d(0, 0);
        for (Bird b : outerBird){
            avgPos = avgPos.add(b.pos);
        }
        avgPos = avgPos.mult(1.0/outerBird.size());
        cohesionVel = avgPos.add(currentBird.pos.mult(-1));

        return  cohesionVel.mult(this.COHESION_FACTOR);
    }

    public Vector2d getAlignmentVel(Bird currentBird, ArrayList<Bird> outerBird){
        Vector2d alignmentVel = new Vector2d(0,0);
        if (outerBird.isEmpty()){
            return alignmentVel;
        }
        Vector2d avgVel = new Vector2d(0, 0);
        for (Bird b : outerBird){
            avgVel = avgVel.add(b.vel);
        }
        avgVel = avgVel.mult(1.0/outerBird.size());
        alignmentVel = avgVel.add(currentBird.vel.mult(-1));
        return  alignmentVel.mult(this.ALIGNMENT_FACTOR);
    }


    public void updateBirds(){
        long time = System.nanoTime();
        double deltaTime = (time - this.lastTime) * 1.0e-9;
        this.lastTime = time;
        ArrayList<Bird> innerBird = new ArrayList<>();
        ArrayList<Bird> outerBird = new ArrayList<>();
        Bird b;
        for (int i = 0; i < this.birds.size(); ++i){
            b = this.birds.get(i);
            innerBird.clear();
            outerBird.clear();
            for (int j = 0; j < this.birds.size(); ++j){
                if (i == j) continue;
                Bird otherBird = this.birds.get(j);
                double birdDistance = b.pos.distancewith(otherBird.pos);
                if (birdDistance <= this.SEPARATION_DISTANCE){
                    innerBird.add(otherBird);
                } else if (birdDistance <= this.FOLLOW_DISTANCE) {
                    outerBird.add(otherBird);
                }
            }

            Vector2d borderVel = this.getBorderVel(b);
            Vector2d separationVel = this.getSeparationVel(b, innerBird);
            Vector2d alignmentVel = this.getAlignmentVel(b, outerBird);
            Vector2d cohesionVel = this.getCohesionVel(b, outerBird);


            Vector2d velChange = borderVel.add(separationVel).add(alignmentVel).add(cohesionVel);

            b.updateVel(deltaTime, velChange);
            b.clampVel(this.MIN_VEL, this.MAX_VEL);
            b.updatePos(deltaTime);

        }
    }
}
