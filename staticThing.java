import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double x;
    private double y;
    private double x_bull = 0;
    private double y_bull;
    private double vx = 0;
    private ImageView imageView;
    private Hero hero;
    private Camera camera;

    protected Rectangle2D hitbox_heart;
    protected Rectangle2D hitbox_bull;
    public staticThing(double x, double y, String filename){
        Image image = new Image(filename);
        imageView = new ImageView(image);
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.x=x;
        this.y=y;

        this.hitbox_heart = new Rectangle2D(x,y,50,50);
        this.hitbox_bull  = new Rectangle2D(x,y,20,20);
    }
    public void setSpeed(double v){
        this.vx=v;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public void update(double time,double X,Hero hero,Camera camera){
        this.hitbox_heart = new Rectangle2D(X+x,y,25,25);
        this.hitbox_bull  = new Rectangle2D(x_bull+hero.getX()+55,hero.getY()+35,20,20);
        x_bull+=vx;
    }

    public double getX_bull() {
        return x_bull;
    }

    public double getY_bull() {
        return y_bull;
    }
}
