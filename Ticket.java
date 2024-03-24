public class Ticket {
    // Class fields to store information about the ticket
    int row;
    int seat;
    double price;
    Person person;


    public Ticket(int row, int seat, double price, Person person) { //this is Constructor to the Ticket object with provided information
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public void print_ticket_info(){
        System.out.println("Row : "+row); // Print the row number
        System.out.println("Seat : "+seat); // Print the seat number
        System.out.println("Price £ : "+price); // Print the ticket price
        person.print_person();
    }
    public double getprice(){
        return price;
    }
    public double getrow(){
        return row;
    }
    public double getseat(){
        return seat;
    }
}
