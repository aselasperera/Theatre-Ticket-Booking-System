public class Person { // Define a public class named "Person"
    String name ; // Declare instance variables to store name, surname, and email of a person
    String surname;
    String email;

    // Define a constructor to initialize the Person object with name, surname, and email
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void print_person() {
        System.out.println("Name : "+name);// Print the name of the person
        System.out.println("Surname : "+surname); // Print the surname of the person
        System.out.println("Email : "+email); // Print the email of the person

    }
}
