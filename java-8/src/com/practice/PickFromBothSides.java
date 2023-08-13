package com.practice;

public class PickFromBothSides {



    public int solve(int[] A, int B) {
        int n = A.length;
        int[] suff = new int [n + 1];
        suff[n] = 0;
        suff[n - 1] = A[n - 1];

        for (int i = n - 2; i >= 0; i--)
            suff[i] = A[i] + suff[i + 1];
        int prefSum = 0;
        int ans = suff[n - B];

        for (int i = 0; i < B; i++) {
            prefSum = prefSum + A[i];
            int suffSum = suff[n - B + (i + 1)];
            ans = Math.max(ans, prefSum + suffSum);
        }

        return ans;
    }
}
