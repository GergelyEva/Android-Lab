public class Car{
    int speed;
    double regularPrice;
    String color;

    public Car(int speed, double regularPrice, String color){
        this.speed=speed;
        this.regularPrice=regularPrice;
        this.color=color;

    }
    double getSalePrice() {
        return 0;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
