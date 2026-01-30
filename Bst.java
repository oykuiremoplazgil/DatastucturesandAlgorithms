import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
class BinaryNode
{
Integer value;
BinaryNode leftNode;
BinaryNode rightNode;
public BinaryNode(Integer value)
{
this.value = value;
this.leftNode = null;
this.rightNode = null;
}
public BinaryNode(Integer value, BinaryNode leftNode, BinaryNode rightNode) {
this.value = value;
this.leftNode = leftNode;
this.rightNode = rightNode;
}
public Integer getValue() {
return value;
}
public void setValue(Integer value) {
this.value = value;
}
public BinaryNode getLeftNode() {
return leftNode;
}
public void setLeftNode(BinaryNode leftNode) {
this.leftNode = leftNode;
}
public BinaryNode getRightNode() {
return rightNode;
}
public void setRightNode(BinaryNode rightNode) {
this.rightNode = rightNode;
}
}
public class BST {
private BinaryNode rootNode;
/**
* Constructors
**/
public BST() {
this.rootNode = null;
}
public BST(BinaryNode rootNode) {
this.rootNode = rootNode;
}
/**
* Setters & getters
**/
public BinaryNode getRootNode() {
return rootNode;
}
public void setRootNode(BinaryNode rootNode) {
this.rootNode = rootNode;
}
/**
* Given member methods
**/
public void insert(Integer x) {
if (x >= 0)
setRootNode(insert(getRootNode(), x));
}
// private helper method for public insert method
private BinaryNode insert(BinaryNode rootNode, Integer x) {
// if the root is null, create a new node and return it
if (rootNode == null) {
return new BinaryNode(x);

}
// if given key is less than the root node, recur for left subtree
if (x < rootNode.getValue()) {
rootNode.setLeftNode(insert(rootNode.getLeftNode(), x));
}
// else, recur for right subtree
else {
// key >= root.data
rootNode.setRightNode(insert(rootNode.getRightNode(), x));
}
return rootNode;
}
public void printTreeInorder(BinaryNode nodeToRecur) {
if (nodeToRecur == null) {
return;
}
printTreeInorder(nodeToRecur.getLeftNode());
System.out.print(nodeToRecur.getValue() + " ");
printTreeInorder(nodeToRecur.getRightNode());
}
// TODO: converts BST into an inorder traversed arraylist
protected ArrayList<Integer> toInorderList(BinaryNode parent, ArrayList<Integer> list) {
if (list == null) {
list = new ArrayList<>();
}
if (parent == null) {
return list;
}
toInorderList(parent.getLeftNode(), list);
list.add(parent.getValue());
toInorderList(parent.getRightNode(), list);
return list;
}
// TODO: return the elements on a given level in a list
public ArrayList<Integer> listLevel(BinaryNode parent, ArrayList<Integer> list, Integer level) {
if (list == null) {
list = new ArrayList<>();
}
if (parent == null || level == 0) {
return list;
}
if (level == 0) {
list.add(parent.getValue());
} else {
listLevel(parent.getLeftNode(), list, level - 1);
listLevel(parent.getRightNode(), list, level - 1);
}
return list;
}
// TODO: checks if the BST has unique elements
public Boolean hasUniqueElements() {
ArrayList<Integer> list = toInorderList(rootNode, new ArrayList<>());
HashSet<Integer> set = new HashSet<>(list);
return list.size() == set.size();
}
// TODO: finds minimum value in BST
public Integer findMin() {
if (rootNode == null) {
return 0;
}
BinaryNode current = rootNode;
while (current.getLeftNode() != null) {
current = current.getLeftNode();
}
return current.getValue();
}
// TODO: finds the zero-indexed depth of the BST
public Integer findDepth(BinaryNode node) {
if (node==null){
return -1;

}
if (node.getLeftNode() == null && node.getRightNode() == null) {
return -1;
}
int leftDepth = findDepth(node.getLeftNode());
int rightDepth = findDepth(node.getRightNode());
return Math.max(leftDepth, rightDepth) + 1;
