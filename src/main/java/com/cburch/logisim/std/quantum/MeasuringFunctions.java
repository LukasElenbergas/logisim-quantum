package com.cburch.logisim.std.quantum;

import java.util.Arrays;

public class MeasuringFunctions {

    private MeasuringFunctions() { }

    public static boolean checkEntangledStatus(String[][] matrix, int rowNum) {
        boolean containsC;
        boolean containsCX;
        boolean containsCZ;
        Boolean[] result = new Boolean[rowNum];

        for (int i = 0; i < rowNum; i++) {
            containsC = Arrays.asList(matrix[i]).contains("C");
            containsCX = Arrays.asList(matrix[i]).contains("CX");
            containsCZ = Arrays.asList(matrix[i]).contains("CZ");

            result[i] = containsC == (containsCX || containsCZ);
        }

        return !Arrays.asList(result).contains(false);
    }

    public static int[] collapseQubits(int[] initialBits, String[][] instructions, int gateNumber, int qubitNumber) {



        return initialBits;
    }
}
