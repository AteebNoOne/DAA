/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daaproject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MyProg {
  public ArrayList<Integer> array;

  public MyProg() {
    array = new ArrayList<>();
  }

  public void add(int value) {
    array.add(value);
  }

  public void printArray() {
  for (int element : array) {
    //DAAProject.ArrayTextArea.setText(element +" ");
    DAAProject.ArrayTextArea.append(element+" ");
  }
    DAAProject.ArrayTextArea.append("\n");  
}

  public void readInput() {
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter the number of values to add: ");
  int numValues = scanner.nextInt();
  for (int i = 0; i < numValues; i++) {
    System.out.print("Enter a value: ");
    array.add(scanner.nextInt());
  }
  scanner.close();
}

  // Insertion sort algorithm
  public void insertionSort() {
    for (int i = 1; i < array.size(); i++) {
      int current = array.get(i);
      int j = i - 1;
      while (j >= 0 && array.get(j) > current) {
        array.set(j + 1, array.get(j));
        j--;
      }
      array.set(j + 1, current);
    }
  }

  // Merge sort algorithm
  public void mergeSort() {
    mergeSort(0, array.size() - 1);
  }

  private void mergeSort(int low, int high) {
    if (low < high) {
      int mid = (low + high) / 2;
      mergeSort(low, mid);
      mergeSort(mid + 1, high);
      merge(low, mid, high);
    }
  }

  private void merge(int low, int mid, int high) {
    ArrayList<Integer> temp = new ArrayList<>(high - low + 1);
    int i = low;
    int j = mid + 1;
    while (i <= mid && j <= high) {
      if (array.get(i) < array.get(j)) {
        temp.add(array.get(i));
        i++;
      } else {
        temp.add(array.get(j));
        j++;
      }
    }
    while (i <= mid) {
      temp.add(array.get(i));
      i++;
    }
    while (j <= high) {
      temp.add(array.get(j));
      j++;
    }
    for (int k = 0; k < temp.size(); k++) {
      array.set(low + k, temp.get(k));
    }
  }

  public boolean linearSearch(int value) {
    for (int element : array) {
      if (element == value) {
        return true;
      }
    }  
    return false;
  }  
  
  public boolean binarySearch(int value) {
    int low = 0;
    int high = array.size() - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (array.get(mid) == value) {
        return true;
      } else if (array.get(mid) < value) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }          
    return false;
  }

    public boolean jumpSearch(int value) {
      int blockSize = (int) Math.sqrt(array.size());
      int start = 0;
      int next = blockSize;
      while (next < array.size() && array.get(next) <= value) {
        start = next;
        next += blockSize;
      }
      for (int i = start; i < next; i++) {
        if (array.get(i) == value) {
          return true;
        }
      }
      return false;
    }
  
    public boolean interpolationSearch(int value) {
      int low = 0;
      int high = array.size() - 1;
      while (low <= high && value >= array.get(low) && value <= array.get(high)) {
        int mid = low + ((high - low) / (array.get(high) - array.get(low))) * (value - array.get(low));
        if (array.get(mid) == value) {
          return true;
        } else if (array.get(mid) < value) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      return false;
    }
  
  private long Start;
  public void search(char type,int data){
    if (isEmpty()){
        return;
    }       
      switch(type){
        case 'L':
            Start = System.nanoTime();
            if(linearSearch(data));
            else {notFound(data); return;};
            break;
        case 'B':
            if(isSorted() == false){
                JOptionPane.showMessageDialog(null, "Sort the data first", "Binary Search", 2);
                return;
            }
            else{
            Start = System.nanoTime();    
            if(binarySearch(data));
            else {notFound(data); return;};
            }
            break;
        case 'J':
            if(isSorted() == false){
                JOptionPane.showMessageDialog(null, "Sort the data first", "Binary Search", 2);
                return;
            }
            else{
            Start = System.nanoTime();    
            if(jumpSearch(data));
            else {notFound(data); return;};
            }
            break;
        case 'I':
            if(isSorted() == false){
                JOptionPane.showMessageDialog(null, "Sort the data first", "Interpolation Search", 2);
                return;
            }    
            else {
            Start = System.nanoTime();    
            if(interpolationSearch(data));
            else {notFound(data); return;};
            }
            break;            
      }
      long End = System.nanoTime();
      long Total = End - Start;
      DAAProject.StatusLabel.setText("Search time: " + Total + " nanoseconds");
  }
    private void notFound(int data){
        JOptionPane.showMessageDialog(null, "Value:"+data+" Not found in array");
    }
  
    public void printMergeSortExecutionTime() {
    if (isEmpty()){
        return;
    }         
    if (isSorted()){
        JOptionPane.showMessageDialog(null, "Array already sorted", "Sorting Algorithms", 0);
        return;
    }            
    long startTime = System.nanoTime();
    mergeSort();
    long endTime = System.nanoTime();
    long sortTime = endTime - startTime;
    JOptionPane.showMessageDialog(null, "Array sorted successfully", "Merge Sort", 1);    
    DAAProject.StatusLabel.setText("Merge Sort time: " + sortTime + " nanoseconds");    
  } 
    
    public void printInsertionSortExecutionTime() {
    if (isEmpty()){
        return;
    }         
    if (isSorted()){
        JOptionPane.showMessageDialog(null, "Array already sorted", "Sorting Algorithms", 0);
        return;
    }    
    long startTime = System.nanoTime();
    insertionSort();
    long endTime = System.nanoTime();
    long sortTime = endTime - startTime;
    JOptionPane.showMessageDialog(null, "Array sorted successfully", "Insertion Sort", 1); 
    DAAProject.StatusLabel.setText("Insertion Sort time: " + sortTime + " nanoseconds");    
    } 
    
    private boolean isSorted() {
      for (int i = 1; i < array.size(); i++) {
        if (array.get(i) < array.get(i - 1)) {
          return false; // Array is not sorted
        }
      }
      return true; // Array is sorted
    }    
    
    private boolean isEmpty(){
        if (array.size() == 0 ) {
        JOptionPane.showMessageDialog(null, "Empty Array", "DAA Project", 3);    
        return true; // Array is empty, so skip sorting
        }
          return false;
    }
    
    public void addFibbo(){
        array.add(0); // Add the first term
        array.add(1); // Add the second term
        for (int i = 2; i < 10; i++) {
          int nextTerm = array.get(i - 1) + array.get(i - 2);
          array.add(nextTerm);
        }
    }

    public void addRandom100(){
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
          int randomNumber = rand.nextInt(500)+500;
          array.add(randomNumber);
        }
    }
    
    public void myInfoMain(char type){
        DAAProject.myMenu_Info.setEnabled(false);        
        MyInfo z = new MyInfo();
        z.setVisible(true);
        switch(type){
            case 'L':
                z.HeadingInfo.setText("Linear Search");
                z.TimeInfoWorst.setText("Big O (n)");
                z.TimeInfoAvg.setText("Big O (n)");                
                z.TimeInfoBest.setText("Big O (1)");
                break;
            case 'B':
                z.HeadingInfo.setText("Binary Search");        
                z.TimeInfoWorst.setText("Big O log(n)");
                z.TimeInfoAvg.setText("Big O log(n)");                
                z.TimeInfoBest.setText("Big O (1)");                
                break;
            case 'J':
                z.HeadingInfo.setText("Jump Search");        
                z.TimeInfoWorst.setText("Big O sqrt(n)+m-1");
                z.TimeInfoAvg.setText("Big O sqrt(n)");                
                z.TimeInfoBest.setText("Big O (1)");                
                break;
            case 'I':
                z.HeadingInfo.setText("Interpolation Search");        
                z.TimeInfoWorst.setText("Big O (n)");
                z.TimeInfoAvg.setText("Big O log(log(n))");                
                z.TimeInfoBest.setText("Big O (1)");                
                break;   
            case 'S':
                z.HeadingInfo.setText("Insertion Sort");        
                z.TimeInfoWorst.setText("Big O n^2");
                z.TimeInfoAvg.setText("Big O n^2");
                z.TimeInfoBest.setText("Big O n"); 
                break; 
            case 'M':
                z.HeadingInfo.setText("Merge Sort");        
                z.TimeInfoWorst.setText("Big O n log(n)");
                z.TimeInfoAvg.setText("Big O n log(n)");
                z.TimeInfoBest.setText("Big O n log(n)");                                
                break;                 
        }
        if(type == 'M'){
            z.SpaceInfo.setText("Big O (n)");
        }
        else{
        z.SpaceInfo.setText("Big O (1)");
        }
    }
}