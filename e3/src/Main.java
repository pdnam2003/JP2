import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("RS01", RoomType.SINGLE,8));
        rooms.add(new Room("RD001", RoomType.DOUBLE, 12.0));
        rooms.add(new Room("RQ002", RoomType.QUEEN, 35.0));
        rooms.add(new Room("RT001", RoomType.TRIPLE, 12.5));
        rooms.add(new Room("RQ001", RoomType.QUAD, 20.5));

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("001", "Mr. Linus ", "84125325346457"));
        customers.add(new Customer("002", "Mr. Bill", "91124235346467"));
        customers.add(new Customer("003", "Mr. Turing", "911423534646"));

        List<Booking> bookings = new ArrayList<Booking>();

        bookings.add(new Booking(1
                ,  rooms.get(0), customers.get(0)
                , LocalDateTime.parse("2023-03-15T09:30:15")
                ,LocalDateTime.parse("2023-03-16T12:30:45")));

        bookings.add(new Booking(
                2,
                rooms.get(1),
                customers.get(1),
                LocalDateTime.parse("2023-06-09T19:30:25"),
                LocalDateTime.parse("2023-06-10T11:25:15")
        ));

        bookings.add(new Booking(
                3,
                rooms.get(1),
                customers.get(2),
                LocalDateTime.parse("2023-03-11T10:10:05"),
                LocalDateTime.parse("2023-03-13T11:05:10")
        ));

        bookings.add(new Booking(
                4,
                rooms.get(2),
                customers.get(0),
                LocalDateTime.parse("2023-11-11T11:11:15"),
                LocalDateTime.parse("2023-11-13T11:15:15")
        ));

        bookings.add(new Booking(
                5,
                rooms.get(0),
                customers.get(1),
                LocalDateTime.parse("2023-10-25T09:20:25"),
                LocalDateTime.parse("2023-10-26T12:25:30")
        ));

        bookings.add(new Booking(
                6,
                rooms.get(0),
                customers.get(2),
                LocalDateTime.parse("2023-08-18T18:25:35"),
                LocalDateTime.parse("2023-08-19T11:35:20")
        ));
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("All service :");
            System.out.println("1 :book room ");
            System.out.println("2 display booking");
            System.out.println("3:Revenue statistics by RoomType");
            System.out.println("RoomType with the largest revenue in 2023");
            System.out.println(" 5: exit");
            System.out.print("Select function: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1 :

                    break;
                case 2 :

                    break;
                case  3 :

                    break;
                case 4 :

                    break;
                    case 5 :
                        break;
            }
        }






    }

}