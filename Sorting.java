public class Sorting {
public static void altSelectionSort(int[] arr){
if (arr == null || arr.length <= 1) return;
int left = 0, right = arr.length - 1;
while (left < right) {
int minIndex = left;
int maxIndex = right;
for (int i = left; i <= right; i++) {
if (arr[i] < arr[minIndex]) minIndex = i;
if (arr[i] > arr[maxIndex]) maxIndex = i;
}
int minValue = arr[minIndex];
int maxValue = arr[maxIndex];
for (int j = minIndex; j > left; j--) arr[j] = arr[j - 1];
arr[left] = minValue;
if (maxIndex == left) maxIndex = minIndex;
else if (maxIndex > left) maxIndex++;
for (int j = maxIndex; j < right; j++) arr[j] = arr[j + 1];
arr[right] = maxValue;
left++;
right--;
}
}
public static void heapSortDecr(int[] arr){
MinHeap heap = new MinHeap(arr);
while(!heap.isEmpty()) {
int min = heap.deleteMin();
heap.getArray()[heap.getCurrentSize() + 1] = min;
}
for (int i=0; i<arr.length;i++){
arr[i]=heap.getArray()[i+1];
}
}
}
