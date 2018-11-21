/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree234;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author intel
 */
public class Tree234<T>
{
    private Node root = new Node();

    public Node getRoot(){
        return root;
    }
    public boolean find(T key)
    {
        Node curNode = root;
        int childNumber;
        
        while(true)
        {
            if(( childNumber=curNode.findItem(key) ) != -1)
                return true;
            else if( curNode.isLeaf() )
                return false;
            else
                curNode = getNextChild(curNode, key);
        }
    }
    public boolean insert(T val)
    {
        int bol;
        Node curNode = root;
        
        DataItems data = new DataItems(val);
        while(true)
        {
            bol = checkDuplicate(curNode, val);
            if(bol == -1)
                return false;
            
            if( curNode.isFull() )
            {
                split(curNode);
                curNode = curNode.getParent();
                curNode = getNextChild(curNode, val);
            }
            else if( curNode.isLeaf() )
                break;
            else{
                curNode = getNextChild(curNode, val);
                
            }
        }
        
            curNode.insertItem(data);
                return true;
    }
    private int checkDuplicate(Node node, T val){
        
        for(int i=0; i<node.getNumItems(); i++){
            
            if( node.getItem(i).dData.equals(val))
                return -1;
        }
        return 0;
    }
//    public void insert(T dValue)
//    {
//        Node curNode = root;
//        DataItems tempItem = new DataItems(dValue);
//        
//        while(true)
//        {
//            if( curNode.isFull() )
//            {
//                split(curNode);
//                curNode = curNode.getParent();
//                curNode = getNextChild(curNode, dValue);
//            }
//            else if( curNode.isLeaf() )
//                break; 
//            else
//                curNode = getNextChild(curNode, dValue);
//        }
//        
//        curNode.insertItem(tempItem);
//    }


    public void split(Node thisNode)
    {

        DataItems itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;
        itemC = thisNode.removeItem();
        itemB = thisNode.removeItem();
        child2 = thisNode.disconnectChild(2);
        child3 = thisNode.disconnectChild(3);
        Node newRight = new Node();
        
        if(thisNode==root)
        {
            root = new Node();
            parent = root;
            root.connectChild(0, thisNode);
        }
        else
            parent = thisNode.getParent();
        
        itemIndex = parent.insertItem(itemB);
        int n = parent.getNumItems();
        
        for(int j=n-1; j>itemIndex; j--)
        {
            Node temp = parent.disconnectChild(j);
            parent.connectChild(j+1, temp);
        }

        parent.connectChild(itemIndex+1, newRight);
        newRight.insertItem(itemC);
        newRight.connectChild(0, child2);
        newRight.connectChild(1, child3);
    }

    public Node getNextChild(Node theNode, T theValue)
    {
        int j;
        int numItems = theNode.getNumItems();
        for(j=0; j<numItems; j++)
        {
            if( ((Comparable)theValue).compareTo(theNode.getItem(j).dData) < 0 )
                return theNode.getChild(j);
        }

        return theNode.getChild(j);
    }
    public void displayTree()
    {
        recDisplayTree(root, 0, 0);
    }

    private void recDisplayTree(Node thisNode, int level,int childNumber)
    {
        System.out.print("level = "+level+" child= "+childNumber+" ");
        thisNode.displayNode();
        int numItems = thisNode.getNumItems();
        for(int j=0; j<numItems+1; j++)
        {
            Node nextNode = thisNode.getChild(j);
            if(nextNode != null)
                recDisplayTree(nextNode, level+1, j);
            else
                return;
        }
    } 
    protected Node findNodeLargest(Node parent) {

	Node current = parent;
            
        
        while (true) {
            
            if(current.isLeaf())
            {
		break;
            }
            
            current = current.lastChild();

	}
            return current;

    }
    protected Node findNodeSmallest (Node parent) {

	Node current = parent;
        while (true) {

            if (current.isLeaf()) {
                break;
            }
	current = current.firstChild();

	}
            return current;

	}
    protected boolean remove(Node node, T x) {
        
        if (node.isLeaf()) {
            int index = node.findItem(x);

	if (index > -1) {
            node.removeElem(index);
            return true;

	}
        else {
            return false;
	}

	}
	
	else {

            int index = node.findItem(x);
            
            if (index > -1) {
		Node predChild = node.getChild(index);
		
                if (predChild.getNumItems()> 1) {
                    T predecessor = (T)findNodeLargest(predChild).largestElem();
                    node.removeElem(index);
                    DataItems d = new DataItems(predecessor);
                    node.insertItem(d);
			return remove(predChild,predecessor);

		}
		else{
                    Node nextChild = node.getChild(index + 1) ;
                    if (nextChild.getNumItems()> 1) {
                        T successor = (T)findNodeSmallest(nextChild).smallestElem();
                        node.removeElem(index);
                        DataItems s = new DataItems(successor);
			node.insertItem(s);
			return remove(nextChild,successor);


                    }
                    else {
			merge(node,x,index);
                            return remove(predChild,x);

                    }
		}

            }
            else {
		Node c = getChildSibling(node, x);
		int indexC = getNextSiblingIndex(node, x);
		Node leftSibling = null; 
		Node rightSibling = null;
		
                if (indexC-1 >= 0) leftSibling = node.getChild(indexC-1);
		if (indexC+1 <= 3) rightSibling = node.getChild(indexC+1);
		if (c.getNumItems() == 1) {
                    
                    if (leftSibling != null && leftSibling.getNumItems() > 1) {
			T k1 = (T)node.removeElem(indexC-1);
                        DataItems newData = new DataItems(k1);
                        int inew = c.insertItem(newData);
                        Node lastchild = leftSibling.disconnectChild(leftSibling.getNumItems());
//                         = (T)leftSibling.removeItem();
                        DataItems dat = leftSibling.removeItem();
                        node.insertItem(dat);
			Node e0 = c.disconnectChild(0);
			Node e1 = c.disconnectChild(1);
			c.connectChild(1, e0);
			c.connectChild(2, e1);
			c.connectChild(0, lastchild);
                    }
                    else if (rightSibling != null && rightSibling.getNumItems() > 1) {
                        T k1 = (T)node.removeElem(indexC);
                        DataItems datt = new DataItems(k1);
                        int inew = c.insertItem(datt);
                        
                        Node firstchild = rightSibling.disconnectChild(0);
                        int j;
			
                        for (j = 0; j < 4 - 1; j++  ) {
                            Node tmp = rightSibling.getChild(j + 1); 
                            rightSibling.connectChild(j, tmp);
			}
			
                        rightSibling.connectChild(j, null);
                        T k2 = (T)rightSibling.removeElem(0);
                        DataItems dat = new DataItems(k2);
                        node.insertItem(dat);
			c.connectChild(c.getNumItems(), firstchild);
                    }
                    else {
                        if (leftSibling != null) {
			T k1 = (T)node.removeElem(indexC-1);
                        Node tmp = node.disconnectChild(indexC);
			if ( tmp != c)
                            throw new Tree234Exception("Issue!");
			
                        int j;
			
                        for (j = indexC; j < 4 - 1; j++  ) {
                            Node tmp2 = node.getChild(j + 1); 
                            node.connectChild(j, tmp2);
			}
                        node.connectChild(j, null);
                        DataItems dat = new DataItems(k1);
			int inew = leftSibling.insertItem(dat);
                        inew = leftSibling.insertItem(c.getItem(0));
			leftSibling.connectChild(2,c.getChild(0));
                        leftSibling.connectChild(3,c.getChild(1));
                        
                        if (node.getNumItems() <= 0) {
                            Node parent = node.getParent();
			
                            if (node == root) {
				root = leftSibling;
                            } 
                            else {
                                int l;
				for (l = 0; l < 4-1; l++) {
                                    if(node == parent.getChild(l)) {
                                        break;
                                    }
				}
				
                            if (l == 4)
                                throw new Tree234Exception("Issue!");
                                parent.connectChild(l, leftSibling);
                            }
                        }
                            c = leftSibling;
			}
			else {
                            T k1 = (T)node.removeElem(indexC);
                            DataItems dat = new DataItems(k1);
                            c.insertItem(dat);
                            int j;
			
                            for (j = indexC + 1; j < 4 - 1; j++  ) {
                                Node tmp2 = node.getChild(j + 1); 
				node.connectChild(j, tmp2);
                            }
                            
                            node.connectChild(j, null);
                            
                        c.connectChild(2,rightSibling.getChild(0));
			c.connectChild(3,rightSibling.getChild(1));
                        T rightSibElem = (T)rightSibling.removeElem(0);
			DataItems elem = new DataItems(rightSibElem);
                        
                        c.insertItem(elem);
			
                        if (node.getNumItems() <= 0) {
                            Node parent = node.getParent();
                            if (node == root) {
                                root = c;
				c.parent = null;
                            }
                            else {
                                
                                int l;
				for (l = 0; l < 4-1; l++) {
				
                                    if(node == parent.getChild(l)) {
                                        break;
                                    }
				}
                                if (l == 4) 
                                    throw new Tree234Exception("Issue!");
                                parent.connectChild(l, c);
                            }
								
			}
                        }
                      } 
                    }
                
			
                return remove(c,x);
                
            }
            		
	}
    }

    protected int getNextSiblingIndex(Node parent, T x) {

	int numElems = parent.getNumItems();
	for(int index=0; index<numElems; index++) {
		if( ((Comparable) x).compareTo(parent.getItem(index).dData) < 0 ) {
                    return index; 
		}
                
	}
        
	return numElems;
    }
    protected Node getChildSibling(Node parent, T x) {

		int numElems = parent.getNumItems();
		
                for(int index=0; index<numElems; index++) {

			if( ((Comparable)x).compareTo(parent.getItem(index).dData) < 0 ) {
				return parent.getChild(index);
			}
		}
		return parent.getChild(numElems);
    }

    protected void merge(Node node, T k, int index) {

	Node predChild = node.getChild(index);
	Node nextChild = node.getChild(index + 1) ;

	T temp = (T)node.removeElem(index);
	
        if ( !k.equals(temp))
            throw new Tree234Exception("HE HAVE A MERGE ISSUE!");
    
        int i;
		for(i=index+1; i<4-1; i++) {
                    Node tmp = node.disconnectChild(i+1);
                    node.connectChild(i, tmp);
		}
		node.disconnectChild(i);

		Node c0 = nextChild.firstChild();
		Node c1= nextChild.getChild(1);
		predChild.connectChild(2, c0);
		predChild.connectChild(3, c1);

		int addIndex;
                DataItems m = new DataItems(k);
		addIndex = predChild.insertItem(m);
		addIndex = predChild.insertItem(nextChild.getItem(0));

                
		if (predChild.getNumItems() != 3) {

                    throw new Tree234Exception("Another merge Issue!");
		}


	}
    public void DfsTraversal(int a){
            
            if(a == 1){
                System.out.println("InOrder traversal of the tree is");
                inOrder(root);
            }
            else if(a == 2){
                System.out.println("PreOrder traversal of the tree is");
                preOrder(root);
            }
            else if(a == 3){
                System.out.println("PostOrder traversal of the tree is");
                postOrder(root);
            }
        }
        public void inOrder(Node node){
            if (node != null)
            {
                inOrder(node.getChild(0));
                for (int j=0; j<node.getNumItems(); j++)
                {
                    if(j != node.getNumItems())
                    node.getItem(j).displayItem();
                    inOrder(node.getChild(j+1));
                }
            }
            
        }
        public void preOrder(Node node){
            if(node != null)
			{
				for(int j=0;j<node.getNumItems()+1;j++)
				{
                                    if(j != node.getNumItems())
				    node.getItem(j).displayItem();
					preOrder(node.getChild(j));
				}
			}
        }
        public void postOrder(Node node){
            if(node != null)
            {
                postOrder(node.getChild(0));
                for(int j=0;j<node.getNumItems();j++)
                {
                    postOrder(node.getChild(j+1));
                    node.getItem(j).displayItem();
                }
            }
            
        }
        
        public void BfsTraversals(Node node){
            
            Queue<Node> q = new LinkedList<>();
            if(node == root){
                q.add(node);
            }
            
            while(!q.isEmpty()){
                
                Node u = q.remove();
                
                u.displayNode();
                
                for(int i=0; i<=u.getNumItems(); i++){
                    if(u.getChild(i) != null)
                    q.add(u.getChild(i));
                }
            }
            
            
        }

}
