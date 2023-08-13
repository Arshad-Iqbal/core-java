package com.practice;

import java.util.Arrays;

public class SpecialIndex {
    public static void main(String[] args) {
        int[] A = {1,1,1};
        solve(A);
    }

    public static int solve(int[] A) {
        // Create even and odd prefix sum
        // Create left even prefix sum
        // create right even prefix sum
        int n = A.length;
        int count = 0;
        int[] peven = new int[n];
        int[] podd = new int[n];
        peven[0] = A[0];
        podd[0] = 0;
        for (int i = 1; i < n; i++) {
            if (i%2 == 0) {
                peven[i] = peven[i-1] + A[i];
                podd[i] = podd[i-1];
            } else {
                podd[i] = podd[i-1] + A[i];
                peven[i] = peven[i-1];
            }
        }
        System.out.println("peven: "+ Arrays.toString(peven));
        System.out.println("podd: "+ Arrays.toString(podd));

        for (int i = 0; i < n; i++) {
           int leftEven = 0;
           if (i > 0) {
               leftEven = peven[i-1];
           }
           int rightEven = podd[n-1] - podd[i];

           int leftOdd = 0;
            if (i > 0) {
                leftOdd = podd[i-1];
            }

            int rightOdd = peven[n-1]- peven[i];
            if ( (leftEven + rightEven) == (leftOdd + rightOdd) ) {
                count++;
            }
        }
        System.out.println("Count = " + count);
        return 1;
    }
}
