import java.util.ArrayList;
class LNode
{
private Integer value;
private LNode nextNode;
public LNode(Integer value) {
this.value = value;
}
public Integer getNodeValue() {
return value;
}
public void setNodeValue(Integer value) {
this.value = value;
}
public LNode getNextNode() {
return nextNode;
}
public void setNextNode(LNode nextNode) {
this.nextNode = nextNode;
}
}
class LList
{
private LNode listHead;
private int lSize;
public LList(LNode listHead) {
this.listHead = listHead;
this.lSize = 0;
}
public LNode getHead() {
return listHead;
}
public void setHead(LNode listHead) {
this.listHead = listHead;
}
public int getLListSize() {
return lSize;
}
public void setLListSize(int lSize) {
this.lSize = lSize;
}
public void addLast(Integer element)
{
LNode toBeAddedNode = new LNode(element);
if (getHead() == null) {
setHead(toBeAddedNode);
setLListSize(getLListSize()+1);
}
else if (getHead().getNextNode() == null)
{
getHead().setNextNode(toBeAddedNode);
setLListSize(getLListSize()+1);
}
else
{
LNode curr = getHead();
while (curr.getNextNode() != null)
{
curr = curr.getNextNode();
}
curr.setNextNode(toBeAddedNode);
setLListSize(getLListSize()+1);
}
}
public void addFirst(Integer element)
{
LNode toBeAddedNode = new LNode(element);

if (getHead() == null)
{
setHead(toBeAddedNode);
setLListSize(getLListSize()+1);
}
else
{
LNode oldHead = getHead();
setHead(toBeAddedNode);
getHead().setNextNode(oldHead);
setLListSize(getLListSize()+1);
}
}
}
public class MyHashtable
{
private int capacity;
private int elementSize;
private LList[] table;
public MyHashtable(int capacity) {
this.capacity = capacity;
this.elementSize = 0;
this.table = new LList[capacity];
}
public int getCapacity() {
return capacity;
}
public void setCapacity(int capacity) {
this.capacity = capacity;
}
public int getElementSize() {
return elementSize;
}
public void setElementSize(int elementSize) {
this.elementSize = elementSize;
}
public LList[] getTable() {
return table;
}
public void setTable(LList[] table) {
this.table = table;
}
// Printing all values
public void printTable()
{
for (int ind = 0; ind < getCapacity(); ind++)
{
if (getTable()[ind] != null)
{
LNode curr = getTable()[ind].getHead();
System.out.print(ind + ":|" );
while (curr != null)
{
if (curr.getNodeValue() != null)
System.out.print(curr.getNodeValue() + "-->");
else
System.out.print("| |");
curr = curr.getNextNode();
}
System.out.println("|");
}
else
{
System.out.println(ind + ":||");
}
}
}
private boolean isPrime(int n)
{
// Corner cases
if (n <= 1)
return false;
if (n <= 3)
return true;

// This is checked so that we can skip
// middle five numbers in below loop
if (n % 2 == 0 || n % 3 == 0)
return false;
for (int i = 5; i * i <= n; i = i + 6)
if (n % i == 0 || n % (i + 2) == 0)
return false;
return true;
}
private int nextPrime(int N)
{
// Base case
if (N <= 1)
return 2;
int prime = N*2;
boolean found = false;
// Loop continuously until isPrime returns
// true for a number greater than n
while (!found)
{
prime++;
if (isPrime(prime))
found = true;
}
return prime;
}
protected Integer hashx(Integer x) {
if (x == null || x < 0) {
return null;
}
if (capacity <= 0){
return null;
}
return x % capacity;
}
// Rehashing the hashtable
protected void rehash()
{
if (capacity == 0 || elementSize <= capacity){
return;
}
int oldCap = capacity;
int newCap = nextPrime(oldCap * 2);
LList[] oldTable = table;
capacity = newCap;
table = new LList[newCap];
for (int i = 0; i < newCap; i++)
table[i] = new LList(null);
int oldSize = elementSize;
elementSize = 0;
for (int i = 0; i < oldCap; i++) {
if (oldTable[i] != null) {
LNode curr = oldTable[i].getHead();
while (curr != null)
{
insert(curr.getNodeValue());
curr = curr.getNextNode();
}
}
}
elementSize = oldSize;
}
// Adding new value
public void insert(Integer x)
{
if (x == null || x < 0) return;
if (elementSize > capacity)
rehash();
Integer index = hashx(x);

if (index == null) return;
if (table[index] == null)
table[index] = new LList(null);
LNode curr = table[index].getHead();
while (curr != null)
{
if (curr.getNodeValue().equals(x)) return;
curr = curr.getNextNode();
}
table[index].addFirst(x);
elementSize++;
if (elementSize > capacity) {
rehash();
}
}
public Boolean contains(Integer value)
{
if (value == null || value < 0) return false;
Integer index = hashx(value);
if (index == null) {
return false;
}
if (table[index] == null) {
return false;
}
LNode curr = table[index].getHead();
while (curr != null) {
if (curr.getNodeValue().equals(value))
return true;
curr = curr.getNextNode();
}
return false;
}
}
