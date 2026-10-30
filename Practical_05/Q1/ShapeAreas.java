import java.util.Scanner;

abstract class Shape {
    public abstract double area();
    public abstract String getName();
}

class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double area() {
        return Math.PI * radius * radius;
    }
    public String getName() {
        return "Circle";
    }
}

class Rectangle extends Shape {
    private double length, width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    public double area() {
        return length * width;
    }
    public String getName() {
        return "Rectangle";
    }
}

class Triangle extends Shape {
    private double base, height;
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    public double area() {
        return 0.5 * base * height;
    }
    public String getName() {
        return "Triangle";
    }
}

public class ShapeAreas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total = 0;
        double largest = 0;
        Shape largestShape = null;
        while (true) {
            System.out.print("Enter shape (circle, rectangle, triangle, quit): ");
            String choice = sc.next().toLowerCase();
            System.out.println("==========================================");
            if (choice.equals("quit")) {
                break;
            }
            Shape s = null;
            switch (choice) {
                case "circle":
                    System.out.print("Enter radius: ");
                    double r = sc.nextDouble();
                    s = new Circle(r);
                    System.out.println("==========================================");
                    break;
                case "rectangle":
                    System.out.print("Enter length: ");
                    double l = sc.nextDouble();
                    System.out.print("Enter width: ");
                    double w = sc.nextDouble();
                    s = new Rectangle(l, w);
                    System.out.println("==========================================");
                    break;
                case "triangle":
                    System.out.print("Enter base: ");
                    double b = sc.nextDouble();
                    System.out.print("Enter height: ");
                    double h = sc.nextDouble();
                    s = new Triangle(b, h);
                    System.out.println("==========================================");
                    break;
                default:
                    System.out.println("Invalid shape.");
            }
            if (s != null) {
                double a = s.area();
                System.out.printf("%s area: %.2f%n", s.getName(), a);
                total += a;
                if (a > largest) {
                    largest = a;
                    largestShape = s;
                }
            }
        }
        System.out.printf("Total area: %.2f%n", total);
        if (largestShape != null) {
            System.out.printf("Largest shape: %s with area: %.2f%n", largestShape.getName(), largest);
        }
        sc.close();
    }
}
