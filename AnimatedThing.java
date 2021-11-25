import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public abstract class AnimatedThing{
    enum Attitude{running,jumping_up,jumping_down,still,attack,shoot,shoot_jumping_up,shoot_jumping_down};
    private ImageView imageView;
    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double duration;
    protected double X_hero;
    protected double Y_hero;
    private int s=0;
    private int i = 0;
    private int q = 0;
    protected Attitude attitude;

    protected Rectangle2D hitbox;
    protected Rectangle2D hitbox_enemie;
    protected Rectangle2D hitbox_enemie1;

    private Camera camera;

    public AnimatedThing(ImageView imageView, Duration duration, int count, int columns,
                         int offsetX, int offsetY, int width, int height, double X, double Y, Camera camera) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        this.duration  = duration.toSeconds();
        this.X_hero    = X;
        this.Y_hero    = Y;
        this.camera    = camera;
        imageView.setX(X);
        imageView.setY(Y);

        this.hitbox=new Rectangle2D(X_hero,Y_hero,width,height);
        this.hitbox_enemie=new Rectangle2D(X_hero,Y_hero,width,height);
        this.hitbox_enemie1=new Rectangle2D(X_hero,Y_hero,width,height);
    }
    public void setFrameChange(Duration duration){
        this.duration = duration.toSeconds();
    }

    public void setTime(int time){
        this.i = time;
    }

    public void setTime1(int time){ this.q = time; }

    public void update(double time){
        final int index=(int) ((time%(count*duration))/duration);

        this.hitbox= new Rectangle2D(this.getX(), this.getY(), width, height);
        this.hitbox_enemie=new Rectangle2D((this.getX()+2000*i),this.getY(),width,height);
        this.hitbox_enemie1=new Rectangle2D((this.getX()+3000*q),this.getY(),width,height);
        if(attitude==Attitude.running) {
            this.imageView.setViewport(new Rectangle2D((index % columns) * width + offsetX, offsetY, width, height));
        }
        else if(attitude==Attitude.jumping_up){
            this.imageView.setViewport(new Rectangle2D(offsetX,offsetY+160,width,height));
        }
        else if(attitude==Attitude.jumping_down){
            this.imageView.setViewport(new Rectangle2D(offsetX+width,offsetY+160, width, height));
        }
        else if(attitude==Attitude.still){
            this.imageView.setViewport(new Rectangle2D(0,0,width,height));
        }
        else if(attitude==Attitude.attack){
            if((index % columns)<=5){
                this.imageView.setViewport(new Rectangle2D((index % columns) * width + offsetX,offsetY,width,height));
            }
            if((index % columns)>5){
                this.imageView.setViewport(new Rectangle2D(((index % columns)-6)*width,height,width,height));
            }
        }
        else if(attitude==Attitude.shoot){
            this.imageView.setViewport(new Rectangle2D((index % columns)*(width+1)+ offsetX - 11,
                    offsetY+325, width, height));
        }
        else if(attitude==Attitude.shoot_jumping_up){
            this.imageView.setViewport(new Rectangle2D(offsetX,offsetY+485,width,height));
        }
        else if(attitude==Attitude.shoot_jumping_down){
            this.imageView.setViewport(new Rectangle2D(offsetX+width,offsetY+485,width,height));
        }
    }

    public abstract void updateAttitude();

    public double getX(){return X_hero;}

    public double getY(){return Y_hero;}

    public ImageView getImageView(){ return imageView;}

    public Rectangle2D getHitbox() {
        return hitbox;
    }
    public Rectangle2D getHitbox_enemie(){
        return hitbox_enemie;
    }

    public boolean Intersect(Rectangle2D hitbox2){
        return hitbox.intersects(hitbox2);
    }
}


