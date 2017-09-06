// Rohan Arya
//rxa160430
// CS 2336.003
package LinkList;


public class DoubleLinkNode extends BaseNode{
    protected DoubleLinkNode next;     // points to next node
    protected DoubleLinkNode prev;     // points to previous node
    
    public DoubleLinkNode(int r, int s)
    {
        super(r,s);
        next = null;
        prev = null;
    }

    
    public DoubleLinkNode() {
        super();
        next = null;
        prev = null;
    }
    public void setNext(DoubleLinkNode n)
    {
        next = n;
    }
    
     public DoubleLinkNode getNext()
    {
        return next;
    }
     
     public void setPrev(DoubleLinkNode n)
    {
        prev = n;
    }
    
     public DoubleLinkNode getPrev()
    {
        return prev;
    }

    
}
