// Rohan Arya
//rxa160430
// CS 2336.003
package Customer;


public class Orders {
    int audi;
    int quant_tickets;
    int adult;
    int senior;
    int child;
    int row[];
    int seats[];
    int ticket_type[];
    
    
    public Orders(int a, int q, int ad, int s, int c, int r[],int st[] , int t[])
    {
        audi = a;
        quant_tickets = q;
        adult =ad;
        senior = s;
        child = c;
        row = r;
        seats = st;
        ticket_type = t;
    }
    
    public int getTotal()
    {
        return quant_tickets;
    }
    
    public double getAmount() // calculates amount to pay for an order
    {
        double sum = 0;
        int i = 0;
        while(i<ticket_type.length)
        {
            if(ticket_type[i]==1)
            {
                sum+=10;
            }
            else if(ticket_type[i]==2)
            {
                sum+=7.50;
                
            }
            else if(ticket_type[i]==3)
            {
                sum+=5.25;
            }
            i++;
        }
        return sum;
    }
    public int getAdult()
    {
        return adult;
    }
    public int getSenior()
    {
        return senior;
    }
    public int getChild()
    {
        return child;
    }
    public int getAudi()
    {
        return audi;
    }
    
}
