import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class GameScene extends Scene {
    private final staticThing leftImage;
    private final staticThing rightImage;
    private final staticThing imagebleu;
    private final staticThing heartImage;
    private final staticThing heartImage1;
    private final staticThing heartImage2;
    private final staticThing bull,bull1,bull2,bull3,bull4,bull5,bull6;
    private int numberOfLives = 3;
    private Camera camera;
    private Hero hero;
    private Foe enemie;
    private Foe enemie1;
    private Pane pane;
    private ArrayList<staticThing> bulls;

    private int s=0;
    private static double now_time = 0;
    private double invincibility = 0;
    private int count=1;
    private int count_enemie=0;
    private int count_enemie1=0;
    private boolean b = true;
    private int index = 6;

    staticThing getHeart = new staticThing(((Math.random() * 300) + 100), 100, "heart.png");

    private ArrayList<Foe> enemies;

    private final double InitialSpeed=1;

    public GameScene(Pane pane, double width, double height, boolean depthBuffer,
                     Camera camera, Hero hero, Foe enemie, Foe enemie1){
        super(pane, width, height, depthBuffer);
        this.camera= camera;
        this.pane  = pane;
        this.enemie = enemie;
        this.enemie1 = enemie1;
        this.imagebleu = new staticThing(0,-100,"imagebleu.png");
        this.leftImage = new staticThing(0, 0,"desert.png");
        this.rightImage = new staticThing(800, 0, "desert.png");
        this.heartImage  = new staticThing(20,20,"heart.png");
        this.heartImage1 = new staticThing(60,20,"heart.png");
        this.heartImage2 = new staticThing(100,20,"heart.png");
        this.bull        = new staticThing(300,150,"bull.png");
        this.bull1        = new staticThing(300,150,"bull.png");
        this.bull2        = new staticThing(300,150,"bull.png");
        this.bull3        = new staticThing(300,150,"bull.png");
        this.bull4        = new staticThing(300,150,"bull.png");
        this.bull5        = new staticThing(300,150,"bull.png");
        this.bull6        = new staticThing(300,150,"bull.png");

        bulls = new ArrayList<>();
        bulls.add(0,bull);
        bulls.add(1,bull1);
        bulls.add(2,bull2);
        bulls.add(3,bull3);
        bulls.add(4,bull4);
        bulls.add(5,bull5);
        bulls.add(6,bull6);

        pane.getChildren().add(imagebleu.getImageView());
        pane.getChildren().addAll(leftImage.getImageView(),rightImage.getImageView(), hero.getImageView());
        pane.getChildren().add(heartImage.getImageView());
        pane.getChildren().add(heartImage1.getImageView());
        pane.getChildren().add(heartImage2.getImageView());

        this.hero =hero;
        this.hero.setSpeedx(InitialSpeed);

        enemies= new ArrayList<>();
        enemies.add(enemie);
        enemies.add(enemie1);
        pane.getChildren().add(enemie.getImageView());
        pane.getChildren().add(enemie1.getImageView());

        this.setOnMouseClicked(event -> hero.jump());
        this.setOnKeyPressed(keyEvent -> {
            String key=keyEvent.getCode().toString();
            if(key.equals("W")){
                hero.jump();
            }
            if(key.equals("D")){
                hero.speed_var(1);
                hero.forcex_var(100);
                hero.setFrameChange(false);
            }
            if(key.equals("A")){
                hero.speed_var(-1);
                hero.forcex_var(-100);
                hero.setFrameChange(true);
            }
            if(key.equals("F")){
                boolean m = true;
                hero.setShoot(m);
                hero.resetZ(50);

                for(int i = 0; i<6; i++){
                    if(pane.getChildren().contains(bulls.get(i).getImageView()) == false){
                        pane.getChildren().add(bulls.get(i).getImageView());
                        bulls.get(i).setSpeed(5);
                        index-=1;
                        break;
                    }
                }
                if(index < 6){
                        if((bulls.get(index-1).getX_bull()-30)> hero.getX()-camera.getX()){
                            pane.getChildren().add(bulls.get(index).getImageView());
                            bulls.get(index).setSpeed(5);
                            index+=1;
                        }
                }
                System.out.println(index);
            }
        });
    }

    public void setNumberOfLives(int number){
        switch (number){
            case 0 : pane.getChildren().removeAll(heartImage.getImageView());
            case 1 : pane.getChildren().removeAll(heartImage1.getImageView());
            case 2 : pane.getChildren().removeAll(heartImage2.getImageView());
        }
    }

    public void addNumberOfLives(int number){
        if(number == 2 ){
            if(pane.getChildren().contains(heartImage1.getImageView()) == false){
                pane.getChildren().add(heartImage1.getImageView());
            }
        }
        if(number == 3 ){
            if(pane.getChildren().contains(heartImage2.getImageView()) == false){
                pane.getChildren().add(heartImage2.getImageView());
            }
        }
    }
    public void update(double time){
        rightImage.getImageView().setY(-camera.getY());
        leftImage.getImageView().setY(-camera.getY());

        leftImage.getImageView().setX(- (camera.getX())%800);
        rightImage.getImageView().setX(- (camera.getX())%800+800);
        
        hero.getImageView().setX(hero.getX()-camera.getX());
        hero.getImageView().setY(hero.getY()-camera.getY());

        for(int i=0;i<=index;i++){
            bulls.get(i).update(time,0,hero,camera);
            bulls.get(i).getImageView().setX(bulls.get(i).getX_bull()+hero.getX()-camera.getX()+55);
            if(pane.getChildren().contains(bulls.get(i).getImageView()) == false){
                bulls.get(i).getImageView().setY(hero.getY()-camera.getY()+35);
            }
//            if(bulls.get(i).getX_bull()+hero.getX()-camera.getX()+55 > 1000 ){
//                if(pane.getChildren().contains(bulls.get(i).getImageView()) == true){
//                    pane.getChildren().removeAll(bulls.get(i).getImageView());
//                    index-=1;
//                }
//            }
        }

        if(enemie.getX()-camera.getX()+1000+2000*count_enemie<0){
            count_enemie+=1;
            if(pane.getChildren().contains(enemie.getImageView()) == false){
                pane.getChildren().add(enemie.getImageView());
            }
        }
        enemie.getImageView().setX(enemie.getX()-camera.getX()+2000*count_enemie);
        enemie.getImageView().setY(enemie.getY()-camera.getY());
        enemie.setTime(count_enemie);

        if(enemie1.getX()-camera.getX()+1500+3000*count_enemie1<0){
            count_enemie1+=1;
            if(pane.getChildren().contains(enemie1.getImageView()) == false){
                pane.getChildren().add(enemie1.getImageView());
            }
        }
        enemie1.getImageView().setX(enemie1.getX()-camera.getX()+3000*count_enemie1);
        enemie1.getImageView().setY(enemie1.getY()-camera.getY());
        enemie1.setTime1(count_enemie1);

        if(pane.getChildren().contains(enemie.getImageView()) == true){
            if (hero.Intersect(enemie.hitbox_enemie)){
                if( now_time == 0 ) {
                    invincibility = hero.isInvincible(true);
                    this.now_time = time;
                    numberOfLives -= 1;
                    setNumberOfLives(numberOfLives);
                }
                if( time - now_time > invincibility){
                    now_time = 0;
                }
            }
        }
        if(pane.getChildren().contains(enemie1.getImageView()) == true){
            if (hero.Intersect(enemie1.hitbox_enemie1)){
                if( now_time == 0 ) {
                    invincibility = hero.isInvincible(true);
                    this.now_time = time;
                    numberOfLives -= 1;
                    setNumberOfLives(numberOfLives);
                }
                if( time - now_time > invincibility){
                    now_time = 0;
                }
            }
        }
        if(b == true){
            if (count_enemie%10 == 5) {
                pane.getChildren().add(getHeart.getImageView());
                b = false;
            }
        }
        if(b == false){
            getHeart.update(time,camera.getX(),hero,camera);
        }
        if(hero.Intersect(getHeart.hitbox_heart)){
            if(pane.getChildren().contains(getHeart.getImageView())){
                numberOfLives+=1;
                if(numberOfLives == 3){numberOfLives = 3;}
                addNumberOfLives(numberOfLives);
                pane.getChildren().removeAll(getHeart.getImageView());
            }
        }
        for(int i = 0; i <index ;i++){
            if(pane.getChildren().contains(bulls.get(i).getImageView()) == true){
                if(enemie.Intersect(bulls.get(i).hitbox_bull)){
                    pane.getChildren().remove(enemie.getImageView());
                    pane.getChildren().remove(bulls.get(i).getImageView());
                }
                if(enemie1.Intersect(bulls.get(i).hitbox_bull)){
                    pane.getChildren().remove(enemie1.getImageView());
                    pane.getChildren().remove(bulls.get(i).getImageView());
                }
            }
        }
        if((count_enemie-1)%5 == 0){
            pane.getChildren().remove(getHeart.getImageView());
            b = true;
        }
 //       System.out.println(enemie1.hitbox_enemie1);
   //     System.out.println(bull.hitbox_bull);
    }

    public double getNumberOfLives(){
        return numberOfLives;
    }
}
