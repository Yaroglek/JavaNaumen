package org.urfu.practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Task2 {
    private Task2() {
    }

    public static void startTask(int n) {
        if (n < 0) {
            return;
        }

        var list = new ArrayList<Double>(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            list.add(random.nextDouble(101) - 50);
        }

        System.out.println(list);
        quickSort(list, 0, n - 1);
        System.out.println(list);
    }

    private static void quickSort(List<Double> arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(List<Double> list, int begin, int end) {
        int middle = begin + (end - begin) / 2;
        Double pivot = list.get(middle);

        swap(list, middle, end);

        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (list.get(j) <= pivot) {
                i++;

                swap(list, i, j);
            }
        }

        swap(list, i + 1, end);

        return i + 1;
    }

    private static void swap(List<Double> list, int i, int j) {
        Double temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
