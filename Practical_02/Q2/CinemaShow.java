public class CinemaShow { 
    private String title; 
    private int seatsAvailable; 
    private final int capacity; 
    private static int totalBooked=0; 

    public CinemaShow(String title,int capacity){ 
        this.title=title; 
        this.capacity=capacity; 
        seatsAvailable=capacity; 
    } 
    public CinemaShow(String title){ 
        this(title,100); 
    } 
    public boolean book(int n){ 
        if(n<=seatsAvailable){ 
            seatsAvailable-=n; 
            totalBooked+=n; 
            return true; 
        } 
        else{ 
            return false; 
        } 
    } 
    public void cancel(int n){ 
        seatsAvailable+=n; 
        if(seatsAvailable>capacity){ 
            seatsAvailable=capacity; 
        } 
    } 
    public int getSeatsAvailable() { 
        return seatsAvailable; 
    } 
    public static int getTotalBooked(){ 
        return totalBooked; 
    } 

    public static void main(String[] args) { 
        CinemaShow m1=new CinemaShow("Tenet",60); 
        System.out.println(m1.book(30)); 
        System.out.println("Seats left: " + m1.seatsAvailable); 
        System.out.println(m1.book(40)); 
        System.out.println("Seats left: " + m1.seatsAvailable); 

        m1.cancel(10); 
        System.out.println("Seats left after cancel: " + m1.getSeatsAvailable()); 

        System.out.println("Total booked: " + CinemaShow.getTotalBooked()); 
    } 
}
