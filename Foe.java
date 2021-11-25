import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Foe extends AnimatedThing{
    private Hero hero;
    private static double vx;
    private int num;
    private static final Image Monstre  = new Image("monstre.png");
    private static ImageView imageView= new ImageView(Monstre);

    public Foe(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY,
               int width, int height, int num, double X, double Y, Camera camera, Hero hero) {
        super(imageView, duration, count, columns, offsetX, offsetY, width, height, X, Y, camera);
        this.hero = hero;
        this.num  = num;
    }

    @Override
    public void updateAttitude() {
        if(num == 1){
            attitude=Attitude.running;
        }
        if(num == 2){
            attitude=Attitude.attack;
        }
    }

    public void update(double time){
        super.update(time);
        updateAttitude();
        if(num == 1){
            vx = hero.getVx() - Math.random()*100;;
            if(vx>10){vx=10;}
            if(vx<5){vx=5;}
            X_hero+=vx;
        }
        if(num == 2){
            vx = hero.getVx() - Math.random()*100;;
            if(vx>10){vx=10;}
            if(vx<5){vx=5;}
            X_hero+=vx/2;
        }

    }
}
