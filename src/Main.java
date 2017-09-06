//Rohan Arya
// rxa160430
// CS 2336.003

import LinkList.*;
import Customer.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) throws FileNotFoundException {
        
        int [] r1 = new int[1];                     // stores total rows for first auditorium
        int [] r2 = new int[1];                     //stores total rows for second auditorium
        int [] r3 = new int[1];                     //stores total rows for third auditorium
        int [] c1 = new int[1];                     //stores total columns for first auditorium
        int [] c2 = new int[1];                     //stores total columns for second auditorium
        int [] c3 = new int[1];                     //stores total columns for third auditorium
        
        File A1 = new File("A1.txt");               // getting audi input files
        File A2 = new File("A2.txt");
        File A3 = new File("A3.txt");
        
        LinkedList empty1 = new LinkedList();       //linked list for empty seats for audi 1
        LinkedList empty2 = new LinkedList();       //linked list for empty seats for audi 2
        LinkedList empty3 = new LinkedList();       //linked list for empty seats for audi 3
        LinkedList reserved1 = new LinkedList();    //linked list for reserved seats for audi 1
        LinkedList reserved2 = new LinkedList();    //linked list for reserved seats for audi 2
        LinkedList reserved3 = new LinkedList();    // linked list for reserved seats for audi 3
       
        
        
        readfile(A1,empty1,reserved1,c1,r1);        // reading input files
        readfile(A2,empty2,reserved2,c2,r2);
        readfile(A3,empty3,reserved3,c3,r3);
        
        File userfile = new File("userdb.dat");         // getting usernames passwords input file
        HashMap<String,Customer> users = fillHashMap(userfile); // storing username - passwords in hashmap
        Scanner keyboard = new Scanner(System.in);
        boolean run = true;
        
        do{
            System.out.println("Enter username");
            String username = keyboard.nextLine();
            if(username.compareTo("admin")==0)                // if admin logs in
            {
                System.out.println("Enter password");
                String password;
                int i =2;
                do
                {
                     password = keyboard.nextLine();
                     int choice =0;
                    if(password.compareTo(users.get(username).getPassword())==0)           // if password is correct
                    {
                        do{
                            System.out.println("1.View Auditorium\n2.Print Report\n3.Exit");     // admin menu
                        
                        do{
                                try{
                                    choice = keyboard.nextInt();                                 // getting input from user on menu above
                                    if(choice>3 || choice <1)
                                        System.out.println("Illegal Input, Enter again");
                                }
                                catch(InputMismatchException Ex)
                                {

                                    System.out.println("Illegal Input, Enter again");
                                    keyboard.next();
                                }
               
                                }while(choice>3 || choice < 1);
                        if(choice ==1)
                        {
                            System.out.println("1. Auditorium 1\n2. Auditorium 2");
                            System.out.println("3. Auditorium 3");
                            int c=-1;
                            do{
                               try{
                                   c = keyboard.nextInt();    // getting audi number
                                   if(c!=1 && c!=2 &&c!=3)
                                       System.out.println("Wrong input, enter again");
                               }
                               catch(InputMismatchException Ex)
                                {
                                    System.out.println("Wrong input, enter again");
                                    keyboard.next();
                                }
                                if(c==1)                               // displaying audi based on user input
                                    display(c1[0],r1[0],empty1);
                                else if(c==2)
                                    display(c2[0],r2[0],empty2);
                                else if(c==3)
                                    display(c3[0],r3[0],empty3);
                            }while(c!=1 && c!=2 &&c!=3);
                        }
                        else if(choice ==2)                                          // printing final report
                        {
                            printreport(empty1,empty2,empty3,reserved1,reserved2,reserved3,users);
                        }
                        else if(choice ==3)
                        {
                            run = false;
                            empty1.writehelper(c1[0],r1[0],A1);                                      // recursive function to write linked lists back to file
                            empty2.writehelper(c2[0],r2[0],A2);
                            empty3.writehelper(c3[0],r3[0],A3);
                        }
                        }while(choice!=3);
                    }
                    
                else                                                                  // if wrong password, give 2 more attempts
                    {
                        if(i==2)
                            System.out.println("Wrong Password, "+i+" tries remaining");
                        else if(i==1)
                            System.out.println("Wrong Password, "+i+" try remaining");
                        i--;
                        if(i==-1)
                           System.out.println("Wrong password, Login failed");
                    }
                
                
                }while(i>-1 && password.compareTo(users.get(username).getPassword())!=0);
            }
            else if(users.containsKey(username))                                 // if user other than admin logs in
            {
                System.out.println("Enter password");                            // get password
                int i = 2;
                String password;
                do{
                    password = keyboard.nextLine();
                    if(password.compareTo(users.get(username).getPassword())==0)
                    {
                        Customer current = users.get(username);                        // getting customer details from hashmap
                        int choice = 0;
                        do
                        {
                            System.out.println("1.Reserve Seats\n2.View Orders\n3.Update Order\n4.Print Receipt\n5.Log Out");
                            do{
                                try{
                                    choice = keyboard.nextInt();                                 // getting input from user on menu above
                                    if(choice>5 || choice <1)
                                        System.out.println("Illegal Input, Enter again");
                                }
                                catch(InputMismatchException Ex)
                                {

                                    System.out.println("Illegal Input, Enter again");
                                    keyboard.next();
                                }
               
                                }while(choice>5 || choice < 1);
                            if(choice == 1)
                            {
                                System.out.println("1.Auditorium 1\n2.Auditorium 2\n3.Auditorium 3");
                                int c = 0,adult=-1,senior=-1,child=-1;
                                do{
                                    try{
                                        c = keyboard.nextInt();                               // getting auditorium number to reserve seats
                                        if(c!=1 && c!=2 &&c!=3)
                                            System.out.println("Wrong input, enter again");
                                    }
                                    catch(InputMismatchException Ex)
                                    {
                                        System.out.println("Wrong input, enter again");
                                        keyboard.next();
                                    }
                                }while(c!=1 && c!=2 &&c!=3);
                                if(c==1)
                                {
                                    display(c1[0],r1[0],empty1);                              // displaying audi 1
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;                             // calculating total tickets adult,senior,child
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];                                 // type 1 adult, type 2 senior, type 3 child
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)                                                //getting seat inputs
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r1[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r1[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c1[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c1[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty1,row,seats,total);            // checking if entered seats are available
                                    if(checker)                                                       // reserve if available
                                    {
                                        current.addorder(1,total, adult, senior,child, row, seats, type); // add order to customers account
                                        reserveseats(empty1,reserved1,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else
                                    {
                                        int best [] = bestavail(empty1,r1,c1,total);                   // if not available check for best available seats
                                        if(best[0]!=-1) // if best seats found
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty1,reserved1,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.addorder(1,total,adult,senior,child,row,seats,type);
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                else if(c==2)
                                {
                                    display(c2[0],r2[0],empty2);                               // displaying audi 1
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;                                             // calculating total tickets adult,senior,child
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)                                             //getting seat inputs
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r2[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r2[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c2[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c2[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty2,row,seats,total);
                                    if(checker)                                                          // checking if entered seats are available
                                    {
                                        current.addorder(2,total, adult, senior,child, row, seats, type); // adding order to customers account
                                        reserveseats(empty2,reserved2,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else                                                          // if not available check for best available seats
                                    {
                                        int best [] = bestavail(empty2,r2,c2,total);
                                        if(best[0]!=-1)
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty2,reserved2,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.addorder(2,total,adult,senior,child,row,seats,type);       // adding order to customers account
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                else if(c==3)
                                {
                                    display(c3[0],r3[0],empty3);                                 // Displaying audi 3
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;                                        // Calculaing total seats adult,senior,child
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)                                  // getting seat inputs
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r3[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r3[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c3[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c3[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty3,row,seats,total);
                                    if(checker)                                                         // if entered seats are available
                                    {
                                        current.addorder(3,total, adult, senior,child, row, seats, type);                   // add to customer orders
                                        reserveseats(empty3,reserved3,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else
                                    {
                                        int best [] = bestavail(empty3,r3,c3,total);           // else check for best available seats
                                        if(best[0]!=-1)
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty3,reserved3,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.addorder(3,total,adult,senior,child,row,seats,type);            // add to customer orders
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                
                                
                            }
                            else if (choice==2)
                            {
                                current.view();          // displays logged in customers orders
                            }
                            else if(choice ==3)
                            {
                                current.view();              // displays logged in customers orders
                                if(current.totalorders()>0){
                                System.out.println();
                                System.out.println("Enter order number to update:");
                                int number = 0;
                                do{                                                // get order number to update
                                    try{
                                        number = keyboard.nextInt();
                                        if(number<1 || number>current.totalorders())
                                            System.out.println("Wrong input, Enter again");
                                    }
                                    catch(InputMismatchException ex)
                                    {
                                        System.out.println("Wrong input, Enter again");
                                        keyboard.next();
                                    }
                                }while(number<1 || number>current.totalorders());
                                System.out.println();
                                System.out.println("1. Add tickets to order\n2. Delete tickets from order\n3. Cancel order");
                                int p=0;
                                do{
                                    try{
                                        p = keyboard.nextInt();
                                        if(p!=1 && p!=2 && p!=3)
                                            System.out.println("Wrong input, Enter again");
                                    }
                                    catch(InputMismatchException ex){
                                        System.out.println("Wrong input, Enter again");
                                        keyboard.next();
                                    }
                                }while(p!=1 && p!=2 && p!=3);
                                if(p==1)                // if user wants to add tickets, step him throught the reservation process, same as above 
                                {
                                    System.out.println();
                                int c = 0,adult=-1,senior=-1,child=-1;
                                
                                if(current.getAudi(number)==1)         // getting order's audi number
                                {
                                    display(c1[0],r1[0],empty1);
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r1[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r1[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c1[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c1[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty1,row,seats,total);
                                    if(checker)
                                    {
                                        
                                        current.updateorder(number,total,adult,senior,child,row,seats,type);
                                        reserveseats(empty1,reserved1,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else
                                    {
                                        int best [] = bestavail(empty1,r1,c1,total);
                                        if(best[0]!=-1)
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty1,reserved1,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.updateorder(number,total,adult,senior,child,row,seats,type);
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                else if(current.getAudi(number)==2)
                                {
                                    display(c2[0],r2[0],empty2);
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r2[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r2[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c2[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c2[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty2,row,seats,total);
                                    if(checker)
                                    {
                                        current.updateorder(number,total,adult,senior,child,row,seats,type);
                                        reserveseats(empty2,reserved2,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else
                                    {
                                        int best [] = bestavail(empty2,r2,c2,total);
                                        if(best[0]!=-1)
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty2,reserved2,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.updateorder(number,total,adult,senior,child,row,seats,type);
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                else if(current.getAudi(number)==3)
                                {
                                    display(c3[0],r3[0],empty3);
                                    System.out.println("Enter number of adult tickets:");
                                    do{
                                        try{
                                            adult = keyboard.nextInt();
                                            if(adult<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(adult<0);
                                    System.out.println("Enter number of senior tickets:");
                                    do{
                                        try{
                                            senior = keyboard.nextInt();
                                            if(senior<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(senior<0);
                                    System.out.println("Enter number of child tickets:");
                                    do{
                                        try{
                                            child = keyboard.nextInt();
                                            if(child<0)
                                                System.out.println("Wrong input, enter again");
                                        }
                                        catch(InputMismatchException Ex){
                                            System.out.println("Wrong input, enter again");
                                            keyboard.next();
                                        }
                                    }while(child<0);
                                    int total = adult+senior+child;
                                    int row[] = new int[total];
                                    int seats[] = new int[total];
                                    int type[] = new int[total];
                                    int j =0;
                                    while(j<adult)
                                    {
                                        type[j]=1;
                                        j++;
                                    }
                                    while(j<(adult+senior))
                                    {
                                        type[j]=2;
                                        j++;
                                    }
                                    while(j<(adult+senior+child))
                                    {
                                        type[j] = 3;
                                        j++;
                                    }
                                    
                                    
                                    for(int k = 0;k<total;k++)
                                    {
                                        System.out.println("For seat no."+(k+1)+" :");
                                        System.out.println("Enter row :");
                                        do{
                                            try{
                                                row[k] = keyboard.nextInt();
                                                if(row[k]>r3[0] || row[k]<1)                                  // checking if valid row
                                                    System.out.println("Wrong input, row does not exist, Enter again");

                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, row does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(row[k]>r3[0] || row[k]<1);
                                        System.out.println("Enter seatnumber :");
                                        do{
                                            try{
                                                seats[k] = keyboard.nextInt();          // getting seat from user
                                                if(seats[k]>c3[0] || seats[k]<1)      // checking for valid seat 
                                                    System.out.println("Wrong input, seat number does not exist, Enter again");
                                            }
                                            catch(InputMismatchException Ex)
                                            {
                                                System.out.println("Wrong input, seat number does not exist, Enter again");
                                                keyboard.next();
                                            }
                                        }while(seats[k]>c3[0] || seats[k]<1);
                                        
                                    }
                                    boolean checker = seatchecker(empty3,row,seats,total);
                                    if(checker)
                                    {
                                        current.updateorder(number,total,adult,senior,child,row,seats,type);
                                        reserveseats(empty3,reserved3,row,seats,total);
                                        System.out.println("Seats reserved..");
                                    }
                                    else
                                    {
                                        int best [] = bestavail(empty3,r3,c3,total);
                                        if(best[0]!=-1)
                                        {
                                            System.out.print("Chosen seats not available, offering best available seats in auditorium : \nRow: "+best[0]+"\nSeats: ");
                                            for(int k = 0;k<total;k++)
                                                System.out.print((best[1]+k)+" ");
                                            System.out.println("");
                                            System.out.println("Enter Y to accept or N to decline...");
                                            char c5 = keyboard.next().charAt(0);
                                            if(c5=='Y' || c5== 'y')                    // if best seats found offer to user
                                            {
                                                System.out.println("Seats reserved..");
                                                reservebestseats(empty3,reserved3,best[0],best[1],total);
                                                for(int k = 0;k<total;k++)
                                                {
                                                    row[k] = best[0];
                                                    seats[k] = best[1]+k;
                                                }
                                                current.updateorder(number,total,adult,senior,child,row,seats,type);
                                               
                                            }
                                            
                                        }
                                        else
                                            System.out.println("No seats available as per selection");
                                    }
                                }
                                }
                                else if(p==2) // if user wants to delete tickts from order
                                {
                                    int d=0 ;
                                    int []r;
                                    int[]s;
                                    int []t;
                                    int audi = current.getAudi(number);
                                    boolean exit = false;
                                    do{                                   // display all seats from selected order in a menu
                                        r =current.getrows(number);
                                         s = current.getseats(number);
                                        t= current.gettype(number);
                                        for(int l =0;l<current.getOrderSeats(number);l++)
                                        {
                                            System.out.print((l+1)+".) Row: "+r[l]+" Seat: "+s[l]+" Type: ");
                                            if(t[l]==1)
                                                System.out.println("Adult");
                                            else if(t[l]==2)
                                                System.out.println("Senior");
                                            else if(t[l]==3)
                                                System.out.println("Child");
                                            
                                            
                                        }
                                        System.out.println((current.getOrderSeats(number)+1)+".) Exit");
                                        System.out.println("Choose ticket to delete or choose exit to go back...");
                                        do{                                                       // get seat input to delete
                                                try {
                                                    d = keyboard.nextInt();
                                                    if(d<1 || d>(current.getOrderSeats(number)+1))
                                                        System.out.println("Wrong input, enter again");
                                                    
                                                }
                                                catch (InputMismatchException ex){
                                                    System.out.println("Wrong input, enter again");
                                                    keyboard.next();
                                                }
                                            }while(d<1 || d>(current.getOrderSeats(number)+1));
                                        if(d!=(current.getOrderSeats(number)+1)) // delete seat from order
                                        {
                                            current.deleteticket(d, number);
                                            if(current.getO()!=null)                     // un-reserve the seats in the auditorium
                                            {
                                                if(audi==1)
                                                {
                                                    unreserveseats(empty1,reserved1,r[d-1],s[d-1]);
                                                }
                                                else if(audi==2)
                                                {
                                                    unreserveseats(empty2,reserved2,r[d-1],s[d-1]);
                                                }
                                                else if(audi==3)
                                                {
                                                    unreserveseats(empty3,reserved3,r[d-1],s[d-1]);
                                                }
                                            
                                            }
                                            
                                            else{
                                                if(audi==1)
                                                {
                                                    unreserveseats(empty1,reserved1,r[d-1],s[d-1]);
                                                }
                                                else if(audi==2)
                                                {
                                                    unreserveseats(empty2,reserved2,r[d-1],s[d-1]);
                                                }
                                                else if(audi==3)
                                                {
                                                    unreserveseats(empty3,reserved3,r[d-1],s[d-1]);
                                                }
                                            }
                                        }
                                        else
                                            exit = true;
                                    }while(current.getO()!=null && !exit && r.length>1);
                                }
                                else if(p==3)
                                {
                                    int [] r = current.getrows(number);
                                    int [] s = current.getseats(number);
                                    int audi = current.getAudi(number);
                                    
                                    for(int t = 0;t<r.length;t++)      // un-reserve all seats from chosen order
                                    {
                                        if(audi==1)
                                        {
                                            unreserveseats(empty1,reserved1,r[t],s[t]);
                                        }
                                        else if(audi==2)
                                        {
                                            unreserveseats(empty2,reserved2,r[t],s[t]);
                                        }
                                        else if(audi ==3)
                                        {
                                            unreserveseats(empty3,reserved3,r[t],s[t]);
                                        }
                                    }
                                    current.cancelorder(number); // delete all tickets from chosen order
                                }
                                }
                                
                            }
                            else if(choice==4)
                            {
                                receipt(current);    // display receipt
                            }
                        }while(choice!=5);
                        keyboard.nextLine();
                    }
                    else // if incorrect password, give 2 more attempts
                    {
                        if(i==2)
                            System.out.println("Wrong Password, "+i+" tries remaining");
                        else if(i==1)
                            System.out.println("Wrong Password, "+i+" try remaining");
                        i--;
                        if(i==-1)
                           System.out.println("Wrong password, Login failed");
                    }
                
                
                }while(i>-1 && password.compareTo(users.get(username).getPassword())!=0);
            }
            
        }while(run);
        
    }
    
    public static void printreport(LinkedList empty1,LinkedList empty2,LinkedList empty3,LinkedList reserved1,LinkedList reserved2,LinkedList reserved3,HashMap h)  // prints final report
    {
       
        Collection c = h.values();
        Iterator itr = c.iterator();
        int adult1 = 0;
        int senior1 = 0;
        int child1 = 0;
        int adult2 = 0;
        int senior2 = 0;
        int child2 = 0;
        int adult3 = 0;
        int senior3 = 0;
        int child3 = 0;
        while (itr.hasNext()) {
            Customer current = (Customer) itr.next();
            if(current.getUsername().compareTo("admin")!=0)
            {
                Orders [] o = current.getO();
                if(o!=null)
                {
                    for(int i = 0;i<o.length;i++)
                    {
                        if(o[i].getAudi()==1)
                        {
                            adult1+=o[i].getAdult();
                            senior1+=o[i].getSenior();
                            child1+=o[i].getChild();
                        }
                        else if(o[i].getAudi()==2)
                        {
                            adult2+=o[i].getAdult();
                            senior2+=o[i].getSenior();
                            child2+=o[i].getChild();
                        }
                        else if(o[i].getAudi()==3)
                        {
                            adult3+=o[i].getAdult();
                            senior3+=o[i].getSenior();
                            child3+=o[i].getChild();
                        }
                    }
                }
                
            }
    }
        
        int seats1[] = new int[2];
        int seats2[] = new int[2];
        int seats3[] = new int[2];
        for(DoubleLinkNode i = empty1.getHead();i!=null;i=i.getNext())
            seats1[0]++;
        for(DoubleLinkNode i = empty2.getHead();i!=null;i=i.getNext())
            seats2[0]++;
        for(DoubleLinkNode i = empty3.getHead();i!=null;i=i.getNext())
            seats3[0]++;
        for(DoubleLinkNode i = reserved1.getHead();i!=null;i=i.getNext())
            seats1[1]++;
        for(DoubleLinkNode i = reserved2.getHead();i!=null;i=i.getNext())
            seats2[1]++;
        for(DoubleLinkNode i = reserved3.getHead();i!=null;i=i.getNext())
            seats3[1]++;
        
        double total1 = (10*(adult1))+(7.50*(senior1))+(5.25*(child1));
        double total2 = (10*(adult2))+(7.50*(senior2))+(5.25*(child2));
        double total3 = (10*(adult3))+(7.50*(senior3))+(5.25*(child3));
        System.out.printf("%15s%18s%23s%18s%18s%18s%19s\n", " ", "Open seats", "Total Reserved seats","Adult Seats","Senior seats","Child seats", "Total Sales" );
        System.out.printf("%15s%18d%23d%18d%18d%18d%14c%.2f\n", "Auditorium 1", seats1[0], seats1[1],adult1,senior1,child1,'$', total1 );
        System.out.printf("%15s%18d%23d%18d%18d%18d%14c%.2f\n", "Auditorium 2", seats2[0], seats2[1],adult2,senior2,child2,'$' ,total2 );
        System.out.printf("%15s%18d%23d%18d%18d%18d%14c%.2f\n", "Auditorium 3", seats3[0], seats3[1],adult3,senior3,child3,'$', total3 );
        System.out.printf("%15s%18d%23d%18d%18d%18d%14c%.2f\n", "Total", seats1[0]+seats2[0]+seats3[0], seats1[1]+seats2[1]+seats3[1],adult1+adult2+adult3,senior1+senior2+senior3,child1+child2+child3,'$', total1+total2+total3 );
    }
    public static void receipt(Customer current) // prints customer receipt
    {
        Orders [] o = current.getO();
        current.view();
        if(o!=null)
        {
            System.out.println ("-------------------------------------------------");
            System.out.printf("|%15s|%15s|%15s|\n", "Order number ", "Total seats  ", "Amount due  ");
            System.out.println ("-------------------------------------------------");
            double fulltotal = 0;
            for(int i = 0; i<o.length;i++)
            {
                double total =  o[i].getAmount();
                fulltotal+=total;
                
                System.out.printf("|%8d%7c|%8d%7c|%7c%.2f%3c|\n",(i+1),' ',o[i].getTotal(),' ' ,'$',total, ' ');
                 System.out.println ("-------------------------------------------------");
            }
             System.out.printf("%39s%c%.2f\n","Total: ",'$',fulltotal);
        }
        
        
    }
    public static HashMap fillHashMap(File fil) throws FileNotFoundException      // reads username-passwords and fills into hashmap
    {
        HashMap<String,Customer> users = new HashMap<String,Customer>();
        Scanner input = new Scanner(fil);
        while(input.hasNext())
        {
            String line = input.nextLine();
            String username="";
            int j = 0;
            while(line.charAt(j)!=' ')
            {
                username+=line.charAt(j);
                j++;
            }
            j++;
            String password="";
            for(int i=j;i<line.length();i++)
            {
                password+=line.charAt(i);
                
            }
            users.put(username, new Customer(username,password));
            
        }
        return users;
    }
    
    public static void readfile(File fil,LinkedList empty,LinkedList reserved,int c[] ,int r[]) throws FileNotFoundException  // reads audi files
    {
        int counter = 0;
        Scanner input = new Scanner(fil);
        while(input.hasNext())
        {
            String line = input.nextLine();
            c[0]=line.length();
            for(int i = 0;i<c[0];i++)
            {
                if(line.charAt(i)=='#')
                {
                    DoubleLinkNode temp = new DoubleLinkNode(counter+1,i+1);
                    empty.addNode(temp);
                }
                else {
                    DoubleLinkNode temp = new DoubleLinkNode(counter+1,i+1);
                    reserved.addNode(temp);
                }
                
            }
            counter++;
        }
        r[0] = counter;
        input.close();
        sortlist(empty);
        sortlist(reserved);
    }
    
    public static void sortlist(LinkedList l) // function to sort the linked list based on row and seat numbers
    {
        if(l.getHead()==null)
            return;
        DoubleLinkNode temp = l.getHead();
        int n = 0;
        while(temp!=null)
        {
            n++;
            temp = temp.getNext();
        }
        boolean swap;
        for(int i = 0;i<n;i++)
        {
            swap = false;
            for(DoubleLinkNode j = l.getHead();j.getNext()!=null;j=j.getNext())
            {
                if(j.getRow()>j.getNext().getRow())
                {
                    DoubleLinkNode temp1 = new DoubleLinkNode(j.getRow(),j.getSeat());
                    j.setRow(j.getNext().getRow());
                    j.setSeat(j.getNext().getSeat());
                    j.getNext().setRow(temp1.getRow());
                    j.getNext().setSeat(temp1.getSeat());
                    swap = true;
                }
                
            }
            if(!swap)
                break;
        }
            
        for(int i = 0;i<n;i++)
            for(DoubleLinkNode j = l.getHead();j.getNext()!=null;j=j.getNext())
            {
                if(j.getRow()==j.getNext().getRow() && j.getSeat()>j.getNext().getSeat())
                {
                    DoubleLinkNode temp1 = new DoubleLinkNode(j.getRow(),j.getSeat());
                    j.setRow(j.getNext().getRow());
                    j.setSeat(j.getNext().getSeat());
                    j.getNext().setRow(temp1.getRow());
                    j.getNext().setSeat(temp1.getSeat());
                }
                
            }
        DoubleLinkNode tailset = l.getHead();
        while(tailset.getNext()!=null)
        {
            tailset = tailset.getNext();
        }
        l.setTail(tailset);
    }
    
    public static double calculatedist(int x,int y, int x1, int y1)                        // function to calculate distance between two seats
    {
        return Math.sqrt(Math.pow((x-x1),2)+ Math.pow((y-y1),2));
    }
    
    public static int[] bestavail(LinkedList empty,int r[],int c[],int quantity)           // function which returns best available seats if available
    {
        
        char[] [] array = new char[r[0]][c[0]];
        for(int i = 0;i<r[0];i++)
            for(int j = 0;j<c[0];j++)
            {
                if(empty.searchseatempty(i+1,j+1))
                    array[i][j] = '#';
                else
                    array[i][j] = '.';
            }
        int middle;
        if(c[0]%2==1)
            middle=c[0]/2;
        else
            middle = (c[0]/2)-1;
        int middlerow;
        if(r[0]%2==1)
            middlerow=r[0]/2;
        else
            middlerow = (r[0]/2)-1;
        double min_distance=-1;
        int min_row=-1;
        int min_seat=-1;
        for(int i =0;i<r[0];i++)
        {
            int leftside=-1,rightside=-1,midside = -1;
            int temp;

            for(int j = middle - 1;j>(middle-quantity+1) && j>=0 ;j--) // checking to see if some seats are on the left side and some on the right
            {   
                 if(array[i][j]=='#')
                 {
                    int m = 0;
                    for(int k = 1;k<quantity;k++)
                        if((k+j)>c[0]-1)
                        {
                            m=-1;
                            break;
                        }
                        else if(array[i][k+j]!='#')
                          m=-1;
                    if(m!=-1)
                    {
                        midside=j;
                        break;
                    }
                 }
            }

            

            for (int m= middle; m>=0  && quantity<=(m+1); m--)         // finds best seats from middle towards the left side
            {
                if(array[i][m]=='#')
                {
                    int c1 =0;
                    for(int j = 1;j<quantity;j++)
                        if(array[i][m-j]!='#')
                            c1=-1;
                    if(c1!=-1)
                    {
                        leftside = (m+1)-quantity;
                        break;
                    }
                }
            }

            for (int n= middle; n<c[0] && quantity<=(c[0]-n); n++)        // finds best seats from middle towards the right side
            {
                if(array[i][n]=='#')
                {
                    int c1 = 0;
                    for(int j = 1;j<quantity;j++)
                        if(array[i][n+j]!='#')
                            c1=-1;
                    if(c1!=-1)
                    {
                        rightside = n;
                        break;
                    }
                }
            }
            if(midside!=-1)
                temp = midside;
            else if(rightside==-1 && leftside!=-1)                     // if statements that return seats whichever closer to middle or -1 if no seats
                temp = leftside;
            else if(rightside!=-1 && leftside==-1)
                temp = rightside;
            else if(rightside!=-1 && leftside!=-1)
            {
               if((middle-leftside)>(rightside-middle))
                    temp = rightside;
                else if((middle-leftside)<(rightside-middle))
                    temp = leftside;
                else
                    temp =leftside;
            }
            else
                temp = -1;
            
            if(temp!=-1)                                                  // stores the seats that are at minimum distance from middle of audi
            {
                double distance = 0;
                if(quantity%2==1)
                    distance = calculatedist(middlerow,middle,i,temp+(quantity/2)); 
                else
                    distance = calculatedist(middlerow,middle,i,temp+(quantity/2)-1);
                if(min_distance==-1)
                {
                    min_distance = distance;
                    min_row = i;
                    min_seat = temp;
                }
                else if(distance<min_distance)
                {
                    min_distance = distance;
                    min_row = i;
                    min_seat = temp;
                }
                else if(distance==min_distance)
                {
                    min_distance = distance;
                    if(Math.abs(middlerow-min_row)<Math.abs(middlerow-i))
                    {
                        continue;
                    }
                    else if(Math.abs(middlerow-min_row)>Math.abs(middlerow-i))
                    {
                        min_row = i;
                        min_seat = temp;
                    }
                    else if(Math.abs(middlerow-min_row)==Math.abs(middlerow-i))
                    {
                        continue;
                    }
                }
            }
        }
        int array1[] = new int[2];
        
        if(quantity==0)                             // stores -1 if no best seats
        {
            array1[0] = -1;
            array1[1] = -1;
        }
        else if(min_distance > -1)
        {
            array1[0] = min_row+1;          // stores row number
            array1[1] = min_seat+1;         //stores seat number
        }   
        else
        {
            array1[0] = -1;
            array1[1] = -1;
        }   
        
        
        return array1;
    }
    
    public static void reservebestseats(LinkedList empty,LinkedList reserved, int row,int seat, int quantity)    // function to reserve best seats for an auditorium
    {
        DoubleLinkNode temp = empty.getHead();
        while(temp!=null)                                         // loop through until you find the node containing the seat
        {
            if(temp.getRow()==row && temp.getSeat()==seat)
                break;
            temp = temp.getNext();
        }
        DoubleLinkNode temp1 = temp.getPrev();
        for(int i =1; i<=quantity;i++)                           // removing nodes to be added to reserved seats linked list
        {
            temp = temp.getNext();
        }
        if(temp1!=null)
        {
            temp1.getNext().setPrev(null);
            temp1.setNext(temp);
        }
        else if(temp1==null)
        {
            empty.getHead().setNext(null);
            empty.setHead(temp);
        }
        if(temp!=null)
        {
            temp.getPrev().setNext(null);
            temp.setPrev(temp1);
        }
        else if(temp==null)
        {
            empty.setTail(temp1);
        }
        temp = reserved.getHead();
        if(temp==null)
        {
            temp = new DoubleLinkNode();
            reserved.setHead(temp);
            temp.setRow(row);
            temp.setSeat(seat);
            temp.setPrev(null);
            temp.setNext(null);
            for(int i = 1;i<quantity;i++)                             // adding nodes at the end of reserve nodes linked list
            {
                DoubleLinkNode temp2 = new DoubleLinkNode();
                temp2.setRow(row);
                temp2.setSeat(seat+i);
                temp.setNext(temp2);
                temp2.setNext(null);
                temp2.setPrev(temp);
                temp = temp.getNext();

            }
        }
        else {
            while(temp.getNext()!=null)
                temp = temp.getNext();
            
            for(int i = 0;i<quantity;i++)                             // adding nodes at the end of reserve nodes linked list
            {
                DoubleLinkNode temp2 = new DoubleLinkNode();
                temp2.setRow(row);
                temp2.setSeat(seat+i);
                temp.setNext(temp2);
                temp2.setNext(null);
                temp2.setPrev(temp);
                temp = temp.getNext();

            }
        }
        
        
        
        sortlist(empty);                                        // sorting both lists to have them in order of row and seat numbers
        sortlist(reserved);
    }
    
    public static void display(int c,int r,LinkedList l)                      // function to display auditoriums to user
    {
        System.out.print("  ");
        for(int i = 0;i<c;i++)
        {
            if((i+1)<10)
                System.out.print(i+1);
            else 
                System.out.print(((i+1)%10));
        }
        System.out.println();
        
        for(int i = 0 ; i<r;i++)
        {
            System.out.print((i+1)+" ");
            for(int j = 0;j<c;j++)
            {
                if(l.searchseatempty(i+1, j+1))
                    System.out.print("#");
                else
                    System.out.print(".");
            }
                
            System.out.println();
        }
    }
    
    public static boolean seatchecker(LinkedList empty,int row[],int seat[], int quantity)      // function to check if user entered seats are available
    {
        if(quantity==0)                                                                            
        {
            System.out.println("No seats available for entered quantity");
            return false;
        }
        for(int i = 0;i<quantity;i++)
        {
            DoubleLinkNode temp = empty.getHead();
            while(temp!=null)
            {
                if(temp.getRow()==row[i] && temp.getSeat()==seat[i])
                    break;
                temp = temp.getNext();
            }
            if(temp==null)
                return false;
        }
        return true;
    }
    
    public static void unreserveseats(LinkedList empty,LinkedList reserved, int row,int seat)  // function to un reserve seats
    {
        DoubleLinkNode temp = reserved.getHead();
            while(temp!=null)                                         // loop through until you find the node containing the seat
            {
                if(temp.getRow()==row && temp.getSeat()==seat)
                    break;
                temp = temp.getNext();
            }
            DoubleLinkNode temp1 = temp.getPrev();
            temp = temp.getNext();
            if(temp1!=null)
            {
                temp1.getNext().setPrev(null);
                temp1.setNext(temp);
            }
            else if(temp1==null)
            {
                empty.getHead().setNext(null);
                empty.setHead(temp);
            }
            if(temp!=null)
            {
                temp.getPrev().setNext(null);
                temp.setPrev(temp1);
            }
            else if(temp==null)
            {
                empty.setTail(temp1);
            }
            temp = empty.getHead();
            
            if(temp==null)
            {
                temp = new DoubleLinkNode();
                reserved.setHead(temp);
                temp.setRow(row);
                temp.setSeat(seat);
                temp.setPrev(null);
                temp.setNext(null);   
            }
            else
            {
                while(temp.getNext()!=null)
                    temp = temp.getNext();
                DoubleLinkNode temp2 = new DoubleLinkNode();
                temp2.setRow(row);
                temp2.setSeat(seat);
                temp.setNext(temp2);
                temp2.setNext(null);
                temp2.setPrev(temp);
            }
            sortlist(empty);                                        // sorting both lists to have them in order of row and seat numbers
            sortlist(reserved);
        }
        
        
    
    
    public static void reserveseats(LinkedList empty,LinkedList reserved, int row[],int seat[], int quantity)    // function to reserve seats for an auditorium
    {
        for(int i =0;i<quantity;i++)
        {
            DoubleLinkNode temp = empty.getHead();
            while(temp!=null)                                         // loop through until you find the node containing the seat
            {
                if(temp.getRow()==row[i] && temp.getSeat()==seat[i])
                    break;
                temp = temp.getNext();
            }
            DoubleLinkNode temp1 = temp.getPrev();
            temp = temp.getNext();
            if(temp1!=null)
            {
                temp1.getNext().setPrev(null);
                temp1.setNext(temp);
            }
            else if(temp1==null)
            {
                empty.getHead().setNext(null);
                empty.setHead(temp);
            }
            if(temp!=null)
            {
                temp.getPrev().setNext(null);
                temp.setPrev(temp1);
            }
            else if(temp==null)
            {
                empty.setTail(temp1);
            }
            temp = reserved.getHead();
            
            if(temp==null)
            {
                temp = new DoubleLinkNode();
                reserved.setHead(temp);
                temp.setRow(row[i]);
                temp.setSeat(seat[i]);
                temp.setPrev(null);
                temp.setNext(null);   
            }
            else
            {
                while(temp.getNext()!=null)
                    temp = temp.getNext();
                DoubleLinkNode temp2 = new DoubleLinkNode();
                temp2.setRow(row[i]);
                temp2.setSeat(seat[i]);
                temp.setNext(temp2);
                temp2.setNext(null);
                temp2.setPrev(temp);
            }
        }
        
        sortlist(empty);                                        // sorting both lists to have them in order of row and seat numbers
        sortlist(reserved);
    }
}
