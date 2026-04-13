import java.util.*;
public class practice {
    public static void main(String[] args) {
        int arr[]= {11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int n=arr.length;
        for(int i=0;i<n;i++) {
            int j=i+1;
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
        }
        for(int i =0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
    }

}
