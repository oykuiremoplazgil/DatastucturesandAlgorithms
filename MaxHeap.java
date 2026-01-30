public class MaxHeap {
private static final int DEFAULT_CAPACITY = 10;
private int currentSize; // size of the min heap
private int[] array; // the array that keeps the heap
public MaxHeap() {
this(DEFAULT_CAPACITY);
}
public MaxHeap(int capacity) {
currentSize = 0;
array = new int[capacity + 1]; // +1 because we are not using array[0]
}
public MaxHeap(int[] items) {
currentSize = items.length;
array = new int[(currentSize + 2) * 11 / 10];
int i = 1;
for (int item : items)
array[i++] = item;
buildHeap();
}
public int[] getArray() {
return this.array;
}
public int getCurrentSize() {
return currentSize;
}
public boolean isEmpty() {
return currentSize == 0;
}
public void makeEmpty() {
currentSize = 0;
}
public int findMax() {
if (isEmpty()) return -1;
return array[1];
}
private void enlargeArray(int newSize) {
int[] old = array;
array = new int[newSize];
for (int i = 0; i < old.length; i++) {
array[i] = old[i];
}
}
public void insert(int x) {
if (x <= 0) return;
if (currentSize == array.length - 1)
enlargeArray(array.length * 2 + 1);
percolateUp(x, ++currentSize);
}
private void percolateUp(int x, int hole) {
for (array[0] = x; x > array[hole / 2]; hole /= 2)
array[hole] = array[hole / 2];
array[hole] = x;
}
private void percolateDown(int hole) {
int child;
int tmp = array[hole];
for (; hole * 2 <= currentSize; hole = child) {
child = hole * 2;
if (child != currentSize && array[child + 1] > array[child]) child++;
if (array[child] > tmp) array[hole] = array[child];
else break;
}

array[hole] = tmp;
}
public int deleteMax() {
if (isEmpty()) return -1;
int minItem = findMax();
array[1] = array[currentSize--];
percolateDown(1);
return minItem;
}
private void buildHeap() {
for (int i = currentSize / 2; i > 0; i--) {
percolateDown(i);
}
}
public void printHeap() {
int level = 0;
System.out.println("\n---------------------------");
for (int i = 1; i < currentSize + 1; i++) {
System.out.print(array[i] + " ");
if ((i + 1) % Math.pow(2, level) == 0) {
System.out.println();
level++;
}
}
System.out.println("\n---------------------------\n");
}
public Integer getHeight() {
if (currentSize <= 0) return -1;
return (int) (Math.floor(Math.log(currentSize) / Math.log(2)));
}
public Boolean increaseKey(int index, int amount) {
if (index <= 0 || index > currentSize){
return false;
}
if (amount<=0) return false;
array[index] += amount;
percolateUp(array[index], index);
return true;
}
public Boolean delete(int index) {
if (index <= 0 || index > currentSize){
return false;
}
array[index] = array[currentSize];
currentSize--;
if (index > 1 && array[index] > array[index / 2]) {
percolateUp(array[index], index);
} else {
percolateDown(index);
}
return true;
}
public Boolean isMaxHeap(int[] arr) {
if (arr == null || arr.length <= 2){
return false;
}
int n = arr.length - 1;
for (int i = 1; i <= n / 2; i++) {
int left = 2 * i;
int right = 2 * i + 1;
if (left <= n && arr[i] < arr[left]) return false;
if (right <= n && arr[i] < arr[right]) return false;
}
return true;
}
