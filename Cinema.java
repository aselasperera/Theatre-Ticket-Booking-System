import java.util.ArrayList; // Importing the ArrayList class from the java.util package
import java.util.Scanner; // Importing the Scanner class from the java.util package
import java.util.InputMismatchException; //imports the InputMismatchException class from the java.io.package

public class Cinema {
    public static ArrayList<Ticket> tickets = new ArrayList<Ticket>();// Create an ArrayList to store Ticket objects

    public static int[][] seat_booking = new int[3][];// Create a 2D array to represent the seating area, with 3 rows and 20 seats per row

    static { // each row of the seating area with 20 seats
        seat_booking[0] = new int[20];
        seat_booking[1] = new int[20];
        seat_booking[2] = new int[20];
    }

    public static void main(String[] args) { // Main method, the starting point of the program
        int option;
        while (true) {
            try {
                while (true) {
                    Scanner get = new Scanner(System.in);// Create a Scanner to get user option
                    System.out.println("\n\n\n\nWelcome to the New London Cinema");// Display the options
                    System.out.println("-".repeat(50));
                    System.out.println("\nPlease select an option:");
                    System.out.println("1) Buy a ticket");
                    System.out.println("2) Cancel ticket");
                    System.out.println("3) Print seating area");
                    System.out.println("4) List available seats");
                    System.out.println("5) Print person information");
                    System.out.println("6) Print ticket information and total price");
                    System.out.println("7) Sort tickets by price");
                    System.out.println("8) Quit");
                    System.out.println("\n" + "-".repeat(50));
                    System.out.print("\nEnter option: ");
                    option = get.nextInt();

                    switch (option) {// Process the user's option based on the selected option
                        case 1:
                            buy_tickets(get);
                            break;
                        case 2:
                            cancel_tickets(get);
                            break;
                        case 3:
                            print_seating_area();
                            break;
                        case 4:
                            show_available();
                            break;
                        case 5:
                            show_person_info(get);
                            break;
                        case 6:
                            calculate_price();
                            break;
                        case 7:
                            sort_tickets();
                            break;
                        case 8:
                            System.exit(8); // Exit the program
                            break;
                        case 0:
                            System.exit(0); // Exit the program
                            break;
                        default:
                            System.out.println("Invalid Selection");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Integer required");
            }
        }
    }

    public static void buy_tickets(Scanner get) { // Method for buying tickets
        System.out.print("Enter row number: "); // Get the row number
        int row_numb = get.nextInt() - 1;
        if (row_numb < 0 || row_numb >= seat_booking.length) { // Check if the row number is valid
            System.out.println();
            System.out.println("Invalid row number..!");
            return;
        }

        System.out.print("Enter seat number: "); // Get the seat number from the user
        int seat_numb = get.nextInt() - 1;
        if (seat_numb < 0 || seat_numb >= seat_booking[row_numb].length) {   // Check if the seat number is valid
            System.out.println();
            System.out.println("Invalid seat number..!");
            return;
        }

        if (seat_booking[row_numb][seat_numb] == 1) { // Check if the seat is already sold
            System.out.println();
            System.out.println("Seat already booked");
            return;
        }

        System.out.print("Enter name: ");// Get the name, surname, and email from the user
        get.nextLine();
        String name = get.nextLine();
        System.out.print("Enter surname: ");
        String surname = get.nextLine();
        System.out.print("Enter email: ");
        String email = get.nextLine();

        if (email.charAt(0) == '@' || !email.contains("@")) { // It checks whether the email starts with an '@' symbol or if it doesn't contain the '@' symbol at all.
            // If either of these conditions is true, it prints "Invalid email address"
            System.out.println();
            System.out.println("Invalid email address");
            return;
        }

        Person person = new Person(name, surname, email);

        double price; // Set the ticket price based on the row number
        if (row_numb == 0) { //row number 1 =15
            price = 15.0;
        } else if (row_numb == 1) { //row number 2=12
            price = 12.0;
        } else {
            price = 10.0; //row number 3=10
        }

        seat_booking[row_numb][seat_numb] = 1; // Mark the seat as sold on the seating area

        Ticket ticket = new Ticket(row_numb + 1, seat_numb + 1, price, person); // Create a new Ticket object with the given details and add it to the list of tickets
        tickets.add(ticket);

        System.out.println();
        System.out.println("Seat booked successfully");
    }

    public static void cancel_tickets(Scanner get) { // Method for canceling tickets
        System.out.print("Enter row number: "); // Get the row number
        int row_numb = get.nextInt() - 1;
        if (row_numb < 0 || row_numb >= seat_booking.length) { // Check if the row number is valid
            System.out.println("Invalid row number..!");
            return;
        }

        System.out.print("Enter seat number: "); // Get the seat number from the user
        int seat_numb = get.nextInt() - 1; // Check if the seat number is valid
        if (seat_numb < 0 || seat_numb >= seat_booking[row_numb].length) {
            System.out.println("Invalid seat number..!");
            return;
        }

        if (seat_booking[row_numb][seat_numb] == 0) { // Check if the seat is already available
            System.out.println();
            System.out.println("Ticket is already cancelled.");
            return;
        }

        seat_booking[row_numb][seat_numb] = 0;// print the seat as available and remove the ticket from the list
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (ticket.row == row_numb + 1 && ticket.seat == seat_numb + 1) {
                tickets.remove(i);
                System.out.println();
                System.out.println("Ticket cancelled  successfully.");
                return;
            }
        }
    }

    public static void print_seating_area() { // Method for printing the seating area
        System.out.println("     ***********\n     *  STAGE  *\n     ***********");

        for (int i = 0; i < seat_booking.length; i++) {// Iterate through the rows of the seating area and prints the seats in each row using the print_seating method
            // Call the print_seating method to print seats for each row
            print_seating(i);
            System.out.println(); // Move to the next row
        }
    }

    public static void show_available() { // Method for showing available seats
        System.out.print("Seats available in row 1: ");// Display available seats for each row
        print_rows_and_seats(0);
        System.out.println();
        System.out.print("Seats available in row 2: ");
        print_rows_and_seats(1);
        System.out.println();
        System.out.print("Seats available in row 3: ");
        print_rows_and_seats(2);
        System.out.println();
    }
    public static void show_person_info(Scanner get) {    // Method for showing person information for a specific seat
        System.out.print("Enter row number: "); // Get the row number
        int row_numb = get.nextInt() - 1;
        if (row_numb < 0 || row_numb >= seat_booking.length) { // Check if the row number is valid
            System.out.println("Invalid row number..!");
            return;
        }

        System.out.print("Enter seat number: ");// Get the seat number
        int seat_numb = get.nextInt() - 1;
        if (seat_numb < 0 || seat_numb >= seat_booking[row_numb].length) { // Check if the seat number is valid
            System.out.println("Invalid seat number..!");
            return;
        }

        if (seat_booking[row_numb][seat_numb] == 1) { // Check if the seat is sold
            for (Ticket ticket : tickets) { // Find the corresponding ticket and print its information
                if (ticket.getrow() == row_numb + 1 && ticket.getseat() == seat_numb + 1) {
                    ticket.print_ticket_info();
                    break;
                }
            }
        } else {
            System.out.println("Ticket not sold");
        }
    }

    public static void calculate_price() { // Method for calculating and printing the total ticket price
        double total_price = 0.0;
        // Calculate the total price by summing the prices of all tickets in the list
        for (Ticket ticket : tickets) {
            total_price += ticket.price;
        }

        for (Ticket ticket : tickets) { // Print ticket information and total price
            ticket.print_ticket_info();
            System.out.println();
        }
        System.out.println("-".repeat(50));
        System.out.println("Total ticket price: £" + total_price);
    }

    public static void sort_tickets() { // Method for sorting tickets by price and printing the ticket information
        double total_price = 0.0;
        System.out.println("_".repeat(50));
        Ticket[] sorted = tickets.toArray(new Ticket[0]); // Convert the ArrayList to array to apply the sorting algorithm
        int n = sorted.length;
        boolean check;
        do { // Bubble sort algorithm to sort tickets based on their price, from lowest to highest
            check = false;
            for (int i = 1; i < n; i++) {
                if (sorted[i - 1].getprice() > sorted[i].getprice()) {
                    Ticket temporary = sorted[i - 1];
                    sorted[i - 1] = sorted[i];
                    sorted[i] = temporary;
                    check = true;
                }
            }
            n--;
        } while (check);

        for (Ticket ticket : sorted) { // Print sorted ticket information and calculate total price
            ticket.print_ticket_info();
            total_price += ticket.price;
            System.out.println();
        }
        System.out.println("_".repeat(30));
        System.out.println("Total price: £" + total_price);
    }
    public static void seating_loop(int i) {
        if (i == 10) {// to print a space when the value of i is equal to 10 in print seating area method
            System.out.print("  ");
        }
    }

    public static void print_seating(int i) { // Method to print the seats in a row
        for (int j = 0; j < seat_booking[i].length; j++) {
            if (j == seat_booking[i].length / 2) {
                // If the current seat is the middle seat, print "X" if it's sold, "O" if it's available
                if (seat_booking[i][j] == 1) {
                    System.out.print(" ");
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                    System.out.print("O");
                }
            } else {
                // If the current seat is not the middle seat, print "X" if it's sold, "O" if it's available
                if (seat_booking[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
        }
    }

    public static void print_rows_and_seats(int i) { // Method to print available seats for a row
        for (int j = 0; j < seat_booking[i].length; j++) {
            if (seat_booking[i][j] == 1) {//i and j is equal to 1 skip to the next one in available seats
                System.out.print("");
            } else {//
                System.out.print(j + 1);
                if (j == seat_booking[i].length - 1) {
                    System.out.print(".");
                } else {
                    System.out.print(",");
                }
            }
        }
    }
}
