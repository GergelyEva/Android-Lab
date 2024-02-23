import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //Ex1  number is positive or negative
        int number;
        System.out.println("Insert a number:");
        number= sc.nextInt();
        if(number>0){
            System.out.println("The number is positive");
        }
        else{
            System.out.println("The number is negative");

        }
        //Ex2 average value of array elements
        int[] array={1,2,3,4,5};
        int total=0;
        int counter=0;
        System.out.println("The numbers in the array are:");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
            total+=array[i];
         counter++;
        }
        System.out.println("The total of the numbers in the array is:"+total);

        System.out.println("The average of the array is:"+(total/counter));

        //Ex3 prime numbers from 0 to 100
        for(int i=0;i<=100;i++){
            if(isPrime(i)){
                System.out.println(i);
            }
        }

        //Ex4 Encapsulation, example with pen
        Pen BluePen=new Pen("blue",20);
        System.out.println("I have a "+ BluePen.getColor()+" pen and it's weight is "+BluePen.getWeight()+" grams");


        //Ex5 with the car and truck
        System.out.println("The base price for both Trucks is 1500, however the first one has a weight of 1500, " +
                "while the second has a weight of 2500. Under 2000 there is a discount of 20% and over 2000" +
                "the discount is 10%");
        Truck Truck1 = new Truck(100,1500,"green",1500);
        System.out.println("The price for Truck1 is:"+ Truck1.getSalePrice());
        Truck Truck2 = new Truck(100,1500,"red",2500);
        System.out.println("The price for Truck1 is:"+ Truck2.getSalePrice());

        System.out.println("Now, the Ford cars are kind of special. They have the manufacturer discount, " +
                "that is not available at trucks. The base price for the Ford Car is also 1500, while the" +
                "manufacturer discount is 200");
        Ford FordCar=new Ford(100,1500,"green",2015,200);
        System.out.println("The new price for the Ford, considering the manufacturer discount is: "+FordCar.getSalePrice());

        System.out.println("Sedan cars receive discount based on length.");
        Sedan SedanCar=new Sedan(100,1500,"blue",25);
        System.out.println("Our Sedan has a speed of: "+SedanCar.getSpeed()+ "it's color is: "+SedanCar.getColor()+
                "the price of it is: "+ SedanCar.regularPrice+" it's length is: "+ SedanCar.length);
        System.out.println("The discounted price of the brand new Sedan is:"+SedanCar.getSalePrice());

    }
    public static boolean isPrime(int n) {
        if (n<= 1) {
            return false;
        }
        for (int i = 2; i< n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}


