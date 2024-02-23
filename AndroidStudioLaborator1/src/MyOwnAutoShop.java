import java.util.Scanner;

public class MyOwnAutoShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sedan cars receive discount based on length.");
        Sedan SedanCar=new Sedan(100,1500,"blue",25);
        System.out.println("Our Sedan has a speed of: "+SedanCar.getSpeed()+ " it's color is: "+SedanCar.getColor()+
                " the price of it is: "+ SedanCar.regularPrice+" it's length is: "+ SedanCar.length);
        System.out.println("The discounted price of the brand new Sedan is:"+SedanCar.getSalePrice());

        Ford FordCar1=new Ford(100,1500,"green",2015,200);
        System.out.println("Our first Ford has a speed of: "+FordCar1.getSpeed()+ " it's color is: "+FordCar1.getColor()+
                "the price of it is: "+ FordCar1.regularPrice+" it's year is: "+ FordCar1.year + " the manufacturer discount is:"+
                        +FordCar1.manufacturerDiscount);

        System.out.println("The new price for the Ford, considering the manufacturer discount is: "+FordCar1.getSalePrice());

        Ford FordCar2=new Ford(120,1500,"red",2017,300);
        System.out.println("Our first Ford has a speed of: "+FordCar2.getSpeed()+ " it's color is: "+FordCar2.getColor()+
                " the price of it is: "+ FordCar2.regularPrice+" it's year is: "+ FordCar2.year + " the manufacturer discount is: "+
                +FordCar2.manufacturerDiscount);

        System.out.println("The new price for the Ford, considering the manufacturer discount is: "+FordCar1.getSalePrice());
    }
}
