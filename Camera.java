public class Camera {

    private int x;
    private int y;
    private static int s=0;
    private static double v_x,v_y;
    private static double a_x,a_y;
    private static double duration;

    private final static double k=1;
    private final static double m=70;
    private final static double f=10;

    public Camera(int x, int y){
        this.x=x;
        this.y=y;
    }

    public double getX(){return x;}

    public double getY(){return y;}

    public void update(double time, Hero hero) {
        double x_hero=hero.getX();
        double y_hero=hero.getY();
        double x_on_screen=100;
        double y_on_screen=150;
        double c1=k/m;
        double c2=f/m;

        a_x=c1*(x_hero-x_on_screen-x)-c2*v_x;
        v_x+=a_x;
        x+=v_x;

        a_y=c1*(y_hero-y_on_screen-y)-c2*v_y;

        v_y+=a_y;
        y+=v_y;

    }

    @Override
    public String toString() {return this.x+","+this.y;}
}
