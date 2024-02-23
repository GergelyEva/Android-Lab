public class Pen {
    String color;
    int weight;

    public Pen(String color, int weight){
       this.color=color;
       this.weight=weight;
    }
    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
