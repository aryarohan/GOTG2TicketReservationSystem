// Rohan Arya
//rxa160430
// CS 2336.003
package LinkList;

public abstract class BaseNode {        
    protected int row;      // stores row
    protected int seat;     // stores seat
    
    protected BaseNode(int r, int s)
    {
        row =r;
        seat =s;
    }
    
    protected BaseNode()
    {
        row =0;
        seat =0;
    }
    
   public int getSeat()
   {
       return seat;
   }
   
   public int getRow()
   {
       return row;
   }
   
   public void setSeat(int s)
   {
       seat =s;
   }
   
   public void setRow(int r)
   {
       row=r;
   }
}
