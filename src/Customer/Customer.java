// Rohan Arya
//rxa160430
// CS 2336.003
package Customer;


public class Customer {
    String username; // stores username
    String password; // stores password
    int numberoforders = 0; // stores total orders
    Orders [] o; // stores all orders
    
    public Customer(String u, String p)
    {
        username = u;
        password = p;
    }
    public int getOrderSeats(int i)
    {
        return o[i-1].row.length;
    }
    
    public int[] getrows(int i)
    {
        return o[i-1].row;
    }
    public int[] getseats(int i)
    {
        return o[i-1].seats;
    }
    public int[] gettype(int i)
    {
        return o[i-1].ticket_type;
    }
            
    
    
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    
    public void setUsername(String u)
    {
        username = u;
        
    }
     public void setPassword(String u)
    {
        password = u;
        
    }
     
    public int totalorders()
    {
        if(o!=null){
        return o.length;
        }
        else
            return 0;
    }
     public void deleteticket(int d,int ordnum) // deletes ticket from chosen order
     {
        if(o[ordnum-1].row.length!=1)
        {
            int [] row1 = new int[o[ordnum-1].row.length-1];
        int [] seat1 = new int[o[ordnum-1].seats.length-1];
        int [] type1 = new int[o[ordnum-1].ticket_type.length-1];
        
        int i = 0;
        int j = 0;
        
        o[ordnum-1].quant_tickets--;
        if(o[ordnum-1].ticket_type[d-1]==1)
            o[ordnum-1].adult--;
        else if(o[ordnum-1].ticket_type[d-1]==2)
            o[ordnum-1].senior--;
        else if(o[ordnum-1].ticket_type[d-1]==3)
            o[ordnum-1].child--;
        
        while(i<o[ordnum-1].row.length)
        {
            if(i!=(d-1)){
                row1[j] = o[ordnum-1].row[i];
                seat1[j] = o[ordnum-1].seats[i];
                type1[j] = o[ordnum-1].ticket_type[i];
                j++;
            }
            i++;
            
        }
        o[ordnum-1].row = row1;
        o[ordnum-1].seats = seat1;
        o[ordnum-1].ticket_type = type1;
        }
        else
        {
            
            cancelorder(ordnum);
        }
        
     }
     
     public Orders[] getO()
     {
         return o;
     }
     
     public void cancelorder(int ordnum)  // cancels chosen order
     {
         numberoforders--;
         System.out.println("Order has been cancelled...");
         if(o.length!=1)
         {
             Orders[] o2 = new Orders[o.length-1];
             int i = 0,j=0;
             while(i<o.length)
             {
                 if(i!=(ordnum-1))
                 {
                     o2[j] = o[i];
                     j++;
                 }
                 i++;
             }
             o = o2;
         }
         else 
             o = null;
     }
     
     
    public void addorder(int audi,int quantity,int adult,int senior,int child,int row[],int seat[],int type[])  // add new order
     {
         if(numberoforders==0)
         {
             o = new Orders[1];
             o[0] = new Orders(audi,quantity,adult,senior,child,row,seat,type);
         }
         else
         {
             o = newarray(o);
             o[numberoforders] = new Orders(audi,quantity,adult,senior,child,row,seat,type);
         }
         numberoforders++;
     }
    public Orders[] newarray(Orders o[])
    {
        Orders[] newarray = new Orders[o.length+1];
        for(int i = 0;i<o.length;i++)
            newarray[i] = o[i];
        return newarray;
    }
    
    public void view()  // displays all orders
    {
        if(o!=null){
            for(int i = 0; i<o.length;i++)
            {
            System.out.println("Order no."+(i+1));
            System.out.println("Auditorium: "+o[i].audi);
            System.out.printf("%8s%8s%8s\n"," "," Row","    Seat");
            for(int j = 0;j<o[i].row.length;j++)
            {
                System.out.printf("%8s%d%7d%8d\n","Seat no.",(j+1),o[i].row[j],o[i].seats[j]);
            }
            System.out.println("Number of adult tickets: "+o[i].adult);
            System.out.println("Number of senior tickets: "+o[i].senior);
            System.out.println("Number of child tickets: "+o[i].child);
            
            
            }
        }
        else
            System.out.println("No orders yet");
        
    }
    
    public void updateorder(int ordnum,int quantity,int ad,int se,int ch,int r[],int s[],int t[])  // add tickets to existing order
    {
        o[ordnum-1].quant_tickets+=quantity;
        o[ordnum-1].adult += ad;
        o[ordnum-1].senior+=se;
        o[ordnum-1].child+=ch;
        int i = o[ordnum-1].row.length;
        int j =0;
        
        int [] row1 = new int[o[ordnum-1].row.length+quantity];
        int [] seat1 = new int[o[ordnum-1].row.length+quantity];
        int [] type1 = new int[o[ordnum-1].row.length+quantity];
        
        for(int k = 0;k<o[ordnum-1].row.length;k++)
        {
            row1[k] = o[ordnum-1].row[k];
            seat1[k] = o[ordnum-1].seats[k];
            type1[k] = o[ordnum-1].ticket_type[k];
        }
        
        while(i<(o[ordnum-1].row.length+quantity))
        {
            row1[i] = r[j];
            seat1[i] = s[j];
            type1[i] = t[j];
            i++;
            j++;
        }
        o[ordnum-1].row = row1;
        o[ordnum-1].seats = seat1;
        o[ordnum-1].ticket_type = type1;
        
    }
    
    public int getAudi(int ordnum)
    {
        return o[ordnum-1].audi;
    }
}
