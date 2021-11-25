import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.concurrent.Delayed;

public class Hero extends AnimatedThing{

    private static double ax,ay;
    private static double vx,vy;
    private static double fx,fy;

    private static Duration duration;
    private static double invincibility;

    private double s=0;
    private final double G=0.2;
    private final double m=15;
    protected final int yGround=150;
    private boolean k = false;

    private double z = 5;
    private double delay = 0;

    private static final Image hero  = new Image("heros.png");

    private static ImageView imageView= new ImageView(hero);

    public Hero(double X, double Y,Camera camera, Duration duration) {
        super(imageView, duration, 6, 6, 10, 0, 82, 100,X,Y,camera);
        this.duration = duration;
    }

    public void jump(){
        if (Y_hero>=yGround+100){
        fy+=125;
        }
    }
    public void setFrameChange(boolean a){
        if (a == true) {
            duration = Duration.millis(100+vx);
        } else if ( a == false) {
            if(vx>90){vx=10;}
            duration = Duration.millis(100-vx);
        }
        setFrameChange(duration);
    }
    public void speed_var(double var){
        vx += var;
    }

    public void forcex_var(double f_var){
        fx+=f_var;
        if (vx<-1){
            vx=-1;
        }
    }
    public void setForces(double Fx,double Fy){
        fx=Fx;
        fy=Fy;
    }

    public void setShoot(boolean m){
        this.k = m;
    }

    public void setSpeedx(double vx){
        this.vx=vx;
    }

    public double resetZ(double z){
        this.z = z;
        if(z < 0){
            this.z = 50;
        }
        return this.z;
    }

    public double isInvincible(boolean s){
        if(s==true){
            this.invincibility = Duration.millis(2500).toSeconds();
        }
        else if(s==false){
            invincibility = 0;
        }
        return invincibility;
    }
    @Override
    public void updateAttitude() {
        if (vy>=0.1){
            if(k == false){
                attitude=Attitude.jumping_down;
            }
            else {
                attitude=Attitude.shoot_jumping_down;
            }
        }
        else if (vy<=-0.1){
            if(k == false){
                attitude=Attitude.jumping_up;
            }
            else {
                attitude=Attitude.shoot_jumping_up;
            }
        }
        else if(k == true){
            attitude=Attitude.shoot;
        }
        else{
            attitude=Attitude.running;
        }
        if(vx==0){
            attitude=Attitude.still;
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
        updateAttitude();
        ay=G-fy/m;
        vy+=ay;
        Y_hero+=vy;
        if (Y_hero > yGround + 100) {
            if (vy > 0) {
                vy = 0;
            }
            Y_hero = yGround + 100;
        }
        ax=fx/m;
        vx+=ax;
        if(vx>30){vx=30;}
        if(vx<5){vx=5;}
        X_hero+=vx;

        z-=1;
        this.z = resetZ(z);
        if( z == 0){this.k = false;}

        setForces(0, 0);
    }

    public double getVx(){
        return vx;
    }

    public double getVy() { return vy; }
}
