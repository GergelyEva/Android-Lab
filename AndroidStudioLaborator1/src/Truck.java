public class Truck extends Car{
    int weight;

    Truck(int speed, double regularPrice, String color, int weight) {
        super(speed, regularPrice, color);
        this.weight=weight;
    }

    @Override
    double getSalePrice() {
        double newprice=0;
        if(weight>2000){
            newprice=super.regularPrice-(super.getRegularPrice()/10);
            return newprice;
        }
        else{
            newprice=super.getRegularPrice()-(super.getRegularPrice()/5);
            return newprice;
        }
    }
}
