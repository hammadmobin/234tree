# Introduction:
If we allow more data items and children per node, the result is a  multi-way tree. 2-3-4 trees. 
What’s in a Name? 
The 2, 3, and 4 in the name 2-3-4 tree refer to how many links to child nodes can potentially be contained in a given node. For non-leaf nodes, three arrangements are possible: 
•A node with one data item always has two children. 
•A node with two data items always has three children. 
•A node with three data items always has four children. 
In short, a non-leaf node must always have one more child than it has data items. Or, to put it symbolically, if the number of child links is L and the number of data items is D, then 
L = D + 1
because a  2-3-4 tree can have nodes with up to four children, it’s called  a multi-way tree of order 4.
2-3-4 Tree Organization
 The data items in a node are arranged in ascending key order, by convention from left to right (lower to higher numbers)
• All children in the sub tree rooted at child 0 have key values less than key 0. 
• All children in the sub tree rooted at child 1 have key values greater than key 0 but less than key 1.
•All children in the sub tree rooted at child 2 have key values greater than key 1 but less than key 2. 
•All children in the sub tree rooted at child 3 have key values greater than key 2.
# Searching
Finding a data item with a particular key is similar to the search routine in a binary tree. You start at the root and, unless the search key is found there, select the link that leads to the sub tree with the appropriate range of values.
Insertion 
 data items are always inserted in leaves, which are on the bottom row of the tree
If no full nodes are encountered during the search, insertion is easy. When the appropriate leaf node is reached, the new data item is simply inserted into it. Figure  shows a data item with key 18 being inserted into a 2-3-4 tree.

# Node Split
Insertion becomes more complicated if a full node is encountered on the path down to the insertion point. When this happens, the node must be  split. It’s this splitting process that keeps the tree balanced. The kind of 2-3-4 tree we’re discussing here is often called a  top-down 2-3-4 tree because nodes are split on the way down to the insertion point.
Splitting Node:
•A new, empty node is created. It’s a sibling of the node being split, and is placed to its right. 
•Data item C is moved into the new node.
• Data item B is moved into the parent of the node being split. 
• Data item A remains where it is. 
• The rightmost two children are disconnected from the node being split and connected to the new node.
Here the insertion required only one node split, but more than one full node may be encountered on the path to the insertion point. When this is the case, there will be multiple splits.
# Deleting a 234 Nodes:

Deleting an element in a 2-3-4 tree assumes we will grow (merge) nodes on the way down.
The idea is intuitive, but writing the algorithm down in English seems to make it look/sound harder than it is.

Again, when dealing with trees, there are different cases. Here, there are 3 different cases:

1) If the element, k is in the node and the node is a leaf containing at least 2 keys, simply remove k from the node.
2) If the element, k is in the node and the node is an internal node perform one of the following:
	1) If the element's left child has at least 2 keys, replace the element with its predecessor, p, and then 
	   Recursively delete p.
	2) If the element's right child has at least 2 keys, replace the element with its successor, s, and then 
	   recursively delete s.
	3) If both children have only 1 key (the minimum), merge the right child into the left child and include the 
	   element, k, in the left child. Free the right child and recursively delete k from the left child.
3) If the element, k, is not in the internal node, follow the proper link to find k. To ensure that all nodes 
   we travel through will have at least 2 keys, you may need to perform one of the following before descending into 
   a node. Then, you will descend into the corresponding node. Eventually, case 1 or 2 will be arrived at 
   (if k is in the tree).
	1) If the child node (the one being descending into) has only 1 key and has an immediate sibling with at 
	   least 2 keys, move an element down from the parent into the child and move an element from the sibling 
	   into the parent.
	2) If both the child node and its immediate siblings have only 1 key each, merge the child node with one of
 the siblings and move an element down from the parent into the merged node. This element will be the middle element in the node. Free the node whose elements were merged into the other node.

Also, much like when deleting from a binary tree, all deletions are actually done at the leaf level, meaning that 
Case #1 is the way all items are actually deleted from the tree. We may have to push elements down into the leaves 
before actually deleting them.



# Efficiency of 2-3-4 Trees
# Height:

More specifically, in 2-3-4 trees there are up to four children per node. If every node
were full, the height of the tree would be proportional to log4N. Logarithms to the
base 2 and to the base 4 differ by a constant factor of 2. Since they aren’t all full, the height of the 2-3-4 tree is somewhere between
log2(N) and log2(N)/2.

# Time Complexity:

On the other hand, there are more items to examine in each node, which increases
the search time. Because the data items in the node are examined using a linear
search, this multiplies the search times by an amount proportional to M, the average
number of items per node. The result is a search time proportional to M*log4N.	
Thus, for 2-3-4 trees the increased number of items per node tends to cancel out the
decreased height of the tree. The search times for a 2-3-4 tree and for a balanced
binary tree such as a red-black tree are approximately equal, and are both O(log N).


