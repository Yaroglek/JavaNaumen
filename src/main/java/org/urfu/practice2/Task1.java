package org.urfu.practice2;

import java.util.Arrays;
import java.util.Random;

public final class Task1 {
    private Task1() {
    }

    public static void startTask(int n) {
        if (n < 0) {
            return;
        }

        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(101) - 50;
        }

        System.out.println(Arrays.toString(array));
        System.out.println(getMinAbsElement(array));
    }

    private static int getMinAbsElement(int[] array) {
        int minAbsValue = array[0];

        for (int num : array) {
            if (Math.abs(num) < Math.abs(minAbsValue)) {
                minAbsValue = num;
            }
        }

        return minAbsValue;
    }
}
