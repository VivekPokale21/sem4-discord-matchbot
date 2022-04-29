package ipl;

import java.util.ArrayList;

public class sort {
    void mergeSort(ArrayList<match> a, int n) {
        if (n < 2) {return;}
        int mid = n / 2;
        ArrayList<match> l = new ArrayList<>(mid);
        ArrayList<match> r = new ArrayList<>(n-mid);

        for (int i = 0; i < mid; i++) {l.add(a.get(i));}
        for (int i = mid; i < n; i++) {r.add(a.get(i));}
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    void merge(ArrayList<match> a, ArrayList<match> l, ArrayList<match> r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l.get(i).startTime.isBefore(r.get(j).startTime)) {
                a.set(k++,l.get(i++));
            }
            else {
                a.set(k++,r.get(j++));
            }
        }
        while (i < left) {
            a.set(k++,l.get(i++));
        }
        while (j < right) {
            a.set(k++,r.get(j++));
        }
    }
}
