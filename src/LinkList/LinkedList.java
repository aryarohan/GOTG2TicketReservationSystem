// Rohan Arya
//rxa160430
// CS 2336.003
package LinkList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class LinkedList {
    protected DoubleLinkNode head;  // stores first node of the list
    protected DoubleLinkNode tail; // stores the last node of the list
    
    public LinkedList()
    {
        head = null;
        tail = null;
    }
    public LinkedList(DoubleLinkNode h) // overloaded constructor
    {
        head =h;
        DoubleLinkNode temp = h;
        while(temp.next!=null)
            temp = temp.next;
        tail = temp;
    }
    public void setHead(DoubleLinkNode n) 
    {
        head = n;
    }
    public DoubleLinkNode getHead()
    {
        return head;
    }
    public void setTail(DoubleLinkNode n)
    {
        tail = n;
    }
    public DoubleLinkNode getTail()
    {
        return tail;
    }
    public void addNode(DoubleLinkNode n) // function to add node to list
    {
        if (head == null){
            head = n;
            tail = n;
        }
        else {
        tail.setNext(n);
        n.setPrev(tail);
        n.setNext(null);
        tail = n;
    }
    }
    
    public void deleteNode(int row,int seat)  // functoin to delete node from list
    {
        if (head == null)
            return;
        if (head.getRow() == row && head.getSeat()==seat)
        {
            head = head.next;
            head.prev.next=null;
            head.prev = null;
            return;
        }
        DoubleLinkNode temp = head.next;
        while(temp!=null)
        {
            if(temp.getRow()==row && temp.getSeat()==seat)
            {
                temp.getNext().setPrev(temp.getPrev());
                temp.getPrev().setNext(temp.getNext());
            }
        }
    }
    
    protected void write(int column,int i,int row,int counter,PrintWriter output) throws FileNotFoundException // recursive function to write back the linked list to the file
    {
        
        if(i<=row)
        {
            if(this.searchseatempty(i,counter))
            {
                output.print("#");
            }
            else
                output.print(".");
            counter++;
            if((counter-1)%column==0 && counter!=1)
            {
                
                counter = 1;
                i++;
                if(i<=row)
                output.println();
                
            }
            write(column,i,row,counter,output);
        }
    }
    
    public void writehelper(int column,int row,File fil) throws FileNotFoundException   // helper function for recursive print function above
    {
        PrintWriter output = new PrintWriter(fil); 
        write(column,1,row,1,output);
        output.close();
    }
    
    public boolean searchseatempty(int row, int seat)                // returns whether given seat is empty when called by linked list instance for empty seats
    {
        DoubleLinkNode temp = head;
        while(temp!=null){
            if(temp.getRow()==row && temp.getSeat()==seat)
                return true;
            temp = temp.getNext();
        }
        return false;
    }
    
    
}
