import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2d {
    public double x;
    public double y;

    public Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getLength(){
        return sqrt((this.x * this.x) + (this.y *this.y));
    }

    public Vector2d add(Vector2d vec){
        return new Vector2d(this.x + vec.x, this.y + vec.y);
    }

    public Vector2d add(double dx, double dy){
        return new Vector2d(this.x + dx, this.y + dy);
    }

    public Vector2d mult(double scalar){
        return new Vector2d(this.x * scalar, this.y * scalar);
    }

    public Vector2d resizeTo(double size){
        double vecSize = this.getLength();
        if (vecSize == 0){
            System.out.println("Vector cannot be resized because it is of length 0!");
            return new Vector2d(0, 0);
        }
        double scaleFactor = size/vecSize;
        return new Vector2d(this.x * scaleFactor, this.y * scaleFactor);
    }

    public void setX(double nx){
        this.x = nx;
    }

    public void setY(double ny){
        this.y = ny;
    }

    public void set(double nx, double ny){
        this.setX(nx);
        this.setY(ny);
    }

    public Vector2d copy(){
        return new Vector2d(this.x, this.y);
    }

    public Vector2d getOrthogonal(){
        return new Vector2d( this.y, -this.x);
    }

    public double distancewith(Vector2d vec){
        return sqrt(pow(vec.x - this.x, 2) + pow(vec.y - this.y, 2));
    }
}
