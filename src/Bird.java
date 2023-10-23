import java.awt.*;

public class Bird {
    Vector2d pos;
    Vector2d vel;
    Color color;
    double size;

    public Bird(Vector2d pos, Vector2d vel, Color color, double size){
        this.pos = pos;
        this.vel = vel;
        this.color = color;
        this.size = size;
    }

    public void updatePos(double deltaTime){
        Vector2d scaledVel = this.vel.mult(deltaTime);
        this.pos = this.pos.add(scaledVel);
    }

    public void updateVel(double deltaTime, Vector2d velChange){
        Vector2d scaledVelChange = velChange.mult(deltaTime);
        this.vel = this.vel.add(scaledVelChange);
    }

    public void clampVel(double minVel, double maxVel){
        if (this.vel.getLength() < minVel){
            this.vel = this.vel.resizeTo(minVel);
            return;
        }
        if (this.vel.getLength() > maxVel){
            this.vel = this.vel.resizeTo(maxVel);
        }

    }

    public Vector2d[] getTriangle(){
        Vector2d[] points = new Vector2d[3];
        points[0] = this.pos.add(this.vel.resizeTo(this.size));
        Vector2d p1, p2;

        p1 = this.pos.add(this.vel.resizeTo(-this.size/3.0));
        p2 = this.pos.add(this.vel.resizeTo(-this.size/3.0));

        p1 = p1.add(this.vel.getOrthogonal().resizeTo(this.size/3.0));
        p2 = p2.add(this.vel.getOrthogonal().resizeTo(-this.size/3.0));

        points[1] = p1;
        points[2] = p2;
        return points;
    }
}
