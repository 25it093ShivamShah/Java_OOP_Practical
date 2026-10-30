import java.util.Scanner; 

public class ParkingLot { 
    private int twoWheelers,fourWheelers; 
    private final int twoCap,fourCap; 
    private static long revenue=0; 

    public ParkingLot(int twoCap,int fourCap){ 
        this.twoCap=twoCap; 
        this.fourCap=fourCap; 
        twoWheelers=0; 
        fourWheelers=0; 
    } 

    public void park(String type){ 
        if(type.equalsIgnoreCase("two")){ 
            if(twoWheelers<twoCap){ 
                twoWheelers++; 
                revenue+=20; 
                System.out.println("Two wheeler parked~ "); 
            } 
            else{ 
                System.out.println("Full!!"); 
            } 
        } 
        else if(type.equalsIgnoreCase("four")){ 
            if(fourWheelers<fourCap){ 
                fourWheelers++; 
                revenue+=40; 
                System.out.println("Four wheeler parked~"); 
            } 
            else{ 
                System.out.println("Full!!"); 
            } 
        } 
        else{ 
            System.out.println("Invalid Choice!"); 
        } 
    } 

    public void leave(String type){ 
        if(type.equalsIgnoreCase("two")){ 
            if(twoWheelers>0){ 
                twoWheelers--; 
                System.out.println("Two wheeler left~"); 
            } 
            else{ 
                System.out.println("No two wheelers to leave!"); 
            } 
        } 
        else if(type.equalsIgnoreCase("four")){ 
            if(fourWheelers>0){ 
                fourWheelers--; 
                System.out.println("Four Wheeler left"); 
            } 
            else{ 
                System.out.println("No two wheeler to left"); 
            } 
        } 
        else{ 
            System.out.println("Invalid Choice!"); 
        } 
    } 
    public void printStatus() { 
        System.out.println("\nFinal Occupancy:"); 
        System.out.println("Two-wheelers: " + twoWheelers + "out of" + twoCap); 
        System.out.println("Four-wheelers: " + fourWheelers + "out of" + fourCap); 
        System.out.println("Total Revenue: " + revenue); 
    } 
    public static void main(String[] args) { 
        Scanner scanner=new Scanner(System.in); 

        System.out.print("Enter capacity for two wheelers: "); 
        int twoCap=scanner.nextInt(); 
        System.out.print("Enter capacity for four wheelers: "); 
        int fourCap=scanner.nextInt(); 

        ParkingLot lot=new ParkingLot(twoCap,fourCap); 
        while(true){ 
            System.out.println("\nChose Option: park / leave / status / exit"); 
            String option=scanner.next(); 

            if(option.equalsIgnoreCase("park")){ 
                System.out.print("Enter car type(two/four): "); 
                String type=scanner.next(); 
                lot.park(type); 
            } 
            else if(option.equalsIgnoreCase("leave")){ 
                System.out.print("Enter car type(two/four): "); 
                String type=scanner.next(); 
                lot.leave(type); 
            } 
            else if(option.equalsIgnoreCase("status")){ 
                lot.printStatus(); 
            } 
            else if(option.equalsIgnoreCase("exit")){ 
                lot.printStatus(); 
                System.out.println("End..."); 
                break; 
            } 
            else{ 
                System.out.println("Invalid Option!"); 
            } 
        } 
        scanner.close(); 
    } 
}
