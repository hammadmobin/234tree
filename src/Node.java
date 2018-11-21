/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree234;

/**
 *
 * @author intel
 */
public class Node <T>{
 
    private static final int ORDER = 4;
    private int numItems;
    public Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItems itemArray[] = new DataItems[ORDER-1];

    public void connectChild(int childNum, Node child)
    {
        childArray[childNum] = child;
        if(child != null)
        child.parent = this;
    }

    public Node disconnectChild(int childNum)
    {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    public Node getChild(int childNum)
    { 
        return childArray[childNum]; 
    }

    public Node getParent()
    {
        return parent; 
    }
    
    public boolean isLeaf()
    {
        return (childArray[0]==null) ? true : false;
    }

    public int getNumItems() 
    {
        return numItems; 
    }

    public DataItems getItem(int index)
    {
        return itemArray[index]; 
    }

    public boolean isFull()
    {
        return (numItems==ORDER-1) ? true : false; 
    }

    public int findItem(T key)
    {
        

        for(int j=0; j<ORDER-1; j++)
        {
            
            if(itemArray[j] == null)
                break;
            else if(itemArray[j].dData.equals(key) )
                return j;
        }
        
        return -1;
    }
    
    public int insertItem(DataItems newItem)
    {
        
        numItems++;
        T newKey = (T)newItem.dData;
        

        for(int j=ORDER-2; j>=0; j--)
        {
            
            if(itemArray[j] == null)
                continue;
            else
            {
                T itsKey = (T)itemArray[j].dData;
                
                if(( (Comparable<T>)newKey).compareTo(itsKey) < 0)
                    itemArray[j+1] = itemArray[j];
                
                else
                {
                    itemArray[j+1] = newItem;
                    return j+1;
                    
                }
            }
        }
        itemArray[0] = newItem;
        return 0;
    }
    public DataItems removeItem()
    {
        DataItems temp = itemArray[numItems-1];
        itemArray[numItems-1] = null;
        numItems--;
        return temp;
    }

    public Node lastChild() {
	return childArray[numItems];
    }

    public Node firstChild() {
	return childArray[0];
    }

    public T smallestElem(){
        return (T)itemArray[0].dData;
    }
    public T largestElem(){
        return (T)itemArray[numItems-1].dData;
    }
    public T removeElem(int index){
        T toReturn = (T)itemArray[index].dData;
	int i;
	
        for (i = index; i < numItems-1; i++) {
            itemArray[i] = itemArray[i+1];
	}
        itemArray[i] = null;
	numItems--;
        return toReturn;

    }
    


    public void displayNode()
    {
        for(int j=0; j<numItems; j++)
            itemArray[j].displayItem();
            System.out.println("/");
    }
}
