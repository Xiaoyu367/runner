import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Anime");
        Group root = new Group();
        Pane pane = new Pane(root);

        Camera camera = new Camera(0,0);
        Hero hero = new Hero(0,0,camera,Duration.millis(100));
        ImageView imageView = new ImageView(new Image("monstre.png"));
        Foe enemie = new Foe(imageView, Duration.millis(500), 2, 2,
                0, 0, 63, 64,1,1000,280,camera,hero);
        ImageView imageView1 = new ImageView(new Image("monstre.png"));
        Foe enemie1 = new Foe(imageView1, Duration.millis(100), 8, 8,
                257, 0, 64, 64,2,1500,280,camera,hero);

        GameScene Scene = new GameScene(pane, 800, 300, true,
                camera,hero,enemie,enemie1);//创建窗口

        final long startNanoTime = System.nanoTime();
        AnimationTimer kk = new AnimationTimer() {
            @Override
            public void handle(long time) {
                if(Scene.getNumberOfLives() == 0 ){

                }
                else{
                    double t = (time - startNanoTime) / 1000000000.0;
                    hero.update(t);
                    camera.update(t,hero);
                    Scene.update(t);
                    enemie.update(t);
                    enemie1.update(t);
                }
            }
        };
        kk.start();

        primaryStage.setScene(Scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
        // write your code here
    }
}