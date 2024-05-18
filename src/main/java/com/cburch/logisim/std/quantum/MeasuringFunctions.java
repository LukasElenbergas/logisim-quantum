package com.cburch.logisim.std.quantum;

import org.ejml.data.Complex_F64;
import org.ejml.data.ZMatrixRMaj;
import org.ejml.dense.row.CommonOps_ZDRM;

import java.util.ArrayList;
import java.util.Arrays;

public class MeasuringFunctions {
    private static final double DECIMAL_ROUNDING_PLACES = 1e15;

    private MeasuringFunctions() { }

    private static ZMatrixRMaj ZERO_PROJECT() {
        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj ONE_PROJECT() {
        double[][] matrix = {
                {0.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 1.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj IDENTITY_GATE() {
        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 1.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj X_GATE() {
        double[][] matrix = {
                {0.0, 0.0, 1.0, 0.0},
                {1.0, 0.0, 0.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj Y_GATE() {
        double[][] matrix = {
                {0.0, 0.0, 0.0, -1.0},
                {0.0, 1.0, 0.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj Z_GATE() {
        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, -1.0, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj HADAMARD_GATE() {
        double pos_opt = 1 / Math.sqrt(2);
        double neg_opt = -1.0 * pos_opt;
        double[][] matrix = {
                {pos_opt, 0.0, pos_opt, 0.0},
                {pos_opt, 0.0, neg_opt, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj S_GATE() {
        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, 0.0, 1.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj T_GATE() {
        double rads = Math.PI / 4;
        double real = Math.cos(rads);
        double imag = Math.sin(rads);

        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, real, imag}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj PHASE_GATE(double theta) {
        double rads = Math.toRadians(theta);
        double real = Math.round(Math.cos(rads) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double imag = Math.round(Math.sin(rads) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;

        double[][] matrix = {
                {1.0, 0.0, 0.0, 0.0},
                {0.0, 0.0, real, imag}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj RX_GATE(double theta) {
        double rads = Math.toRadians(theta);
        double real = Math.round(Math.cos(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double imag = -1.0 * Math.round(Math.sin(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;

        double[][] matrix = {
                {real, 0.0, 0.0, imag},
                {0.0, imag, real, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj RY_GATE(double theta) {
        double rads = Math.toRadians(theta);
        double cosP = Math.round(Math.cos(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double sinP = Math.round(Math.sin(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double sinN = -1.0 * sinP;

        double[][] matrix = {
                {cosP, 0.0, sinN, 0.0},
                {sinP, 0.0, cosP, 0.0}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj RZ_GATE(double theta) {
        double rads = Math.toRadians(theta);
        double realP = Math.round(Math.cos(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double imagP = Math.round(Math.sin(rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double realN = Math.round(Math.cos(-1.0 * rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;
        double imagN = Math.round(Math.sin(-1.0 * rads / 2) * DECIMAL_ROUNDING_PLACES) / DECIMAL_ROUNDING_PLACES;

        double[][] matrix = {
                {realN, imagN, 0.0, 0.0},
                {0.0, 0.0, realP, imagP}
        };
        return new ZMatrixRMaj(matrix);
    }

    private static ZMatrixRMaj MULTI_INPUT_GATE(ArrayList<String> operations, boolean toffoli) {
        int finalSize = (int) Math.pow(2, operations.size());

        if (!toffoli) {
            ArrayList<ZMatrixRMaj> instructionsOff = new ArrayList<>();
            ArrayList<ZMatrixRMaj> instructionsOn = new ArrayList<>();

            for (String gateName : operations) {
                switch (gateName) {
                    case "C" -> {
                        instructionsOff.add(ZERO_PROJECT());
                        instructionsOn.add(ONE_PROJECT());
                    }
                    case "CX" -> {
                        instructionsOff.add(IDENTITY_GATE());
                        instructionsOn.add(X_GATE());
                    }
                    case "CZ" -> {
                        instructionsOff.add(IDENTITY_GATE());
                        instructionsOn.add(Z_GATE());
                    }
                    default -> {
                        instructionsOff.add(singleQubitGateIdentifier(gateName));
                        instructionsOn.add(singleQubitGateIdentifier(gateName));
                    }
                }
            }

            ZMatrixRMaj kronWhenOff = kron(instructionsOff);
            ZMatrixRMaj kronWhenOn = kron(instructionsOn);
            ZMatrixRMaj controlXorZMatrix = new ZMatrixRMaj(finalSize, finalSize);
            CommonOps_ZDRM.add(kronWhenOff, kronWhenOn, controlXorZMatrix);

            return controlXorZMatrix;

        } else {
            ArrayList<ZMatrixRMaj> instructionsOff = new ArrayList<>();
            ArrayList<ZMatrixRMaj> instructionsOffOn = new ArrayList<>();
            ArrayList<ZMatrixRMaj> instructionsOnOff = new ArrayList<>();
            ArrayList<ZMatrixRMaj> instructionsOn = new ArrayList<>();

            int controlCount = 0;
            for (String gateName : operations) {
                if (gateName.equals("C")) {
                    controlCount++;
                    if (controlCount == 1) {
                        instructionsOff.add(ZERO_PROJECT());
                        instructionsOffOn.add(ZERO_PROJECT());
                        instructionsOnOff.add(ONE_PROJECT());
                        instructionsOn.add(ONE_PROJECT());
                    } else {
                        instructionsOff.add(ZERO_PROJECT());
                        instructionsOffOn.add(ONE_PROJECT());
                        instructionsOnOff.add(ZERO_PROJECT());
                        instructionsOn.add(ONE_PROJECT());
                    }
                } else if (gateName.equals("CCX")) {
                    instructionsOff.add(IDENTITY_GATE());
                    instructionsOffOn.add(IDENTITY_GATE());
                    instructionsOnOff.add(IDENTITY_GATE());
                    instructionsOn.add(X_GATE());
                } else {
                    instructionsOff.add(singleQubitGateIdentifier(gateName));
                    instructionsOffOn.add(singleQubitGateIdentifier(gateName));
                    instructionsOnOff.add(singleQubitGateIdentifier(gateName));
                    instructionsOn.add(singleQubitGateIdentifier(gateName));
                }
            }

            ZMatrixRMaj kronWhenOff = kron(instructionsOff);
            ZMatrixRMaj kronWhenOffOn = kron(instructionsOffOn);
            ZMatrixRMaj kronWhenOnOff = kron(instructionsOnOff);
            ZMatrixRMaj kronWhenOn = kron(instructionsOn);
            ZMatrixRMaj toffoliMatrix = new ZMatrixRMaj(finalSize, finalSize);
            CommonOps_ZDRM.add(kronWhenOff, kronWhenOn, toffoliMatrix);
            CommonOps_ZDRM.add(kronWhenOffOn, toffoliMatrix, toffoliMatrix);
            CommonOps_ZDRM.add(kronWhenOnOff, toffoliMatrix, toffoliMatrix);

            return toffoliMatrix;
        }
    }

    public static int[] collapseQubits(int[] initialBits, String[][] instructions, int gateNumber, int qubitNumber) {
        // SIZE VALUES FOR THE FINAL ARRAY TO BE MULTIPLIED WITH THE STATE VECTOR
        int finSize = (int) Math.pow(2, qubitNumber);

        // CREATE 2D ARRAYLIST AND STORE CONVERTED GATE INSTRUCTIONS
        ArrayList<ArrayList<ZMatrixRMaj>> gateInstructions = new ArrayList<>();
        for (int i = 0; i < gateNumber; i++) {
            gateInstructions.add(new ArrayList<>());
            getProperGates(gateInstructions.get(i), instructions[i], qubitNumber);
        }

        // CALCULATE THE KRON PRODUCTS OF THE PARALLEL GATES
        ZMatrixRMaj[] kronProductMatrices = new ZMatrixRMaj[gateNumber];
        for (int i = 0; i < gateNumber; i++) {
            kronProductMatrices[i] = kron(gateInstructions.get(i));
            kronProductMatrices[i].print();
        }

        // CALCULATE THE OVERALL MATRIX OF THE CIRCUIT

        // CREATE A STATE VECTOR OUT OF THE INITIAL BITS

        // GET THE OUTPUT OF THE QUANTUM CIRCUIT FOR THE PROPAGATION

        return initialBits;
    }

    public static boolean checkEntangledStatus(String[][] matrix, int rowNum) {
        int controlCount = 0;
        boolean containsC;
        boolean containsCX;
        boolean containsCZ;
        boolean containsCCX;
        Boolean[] result = new Boolean[rowNum];

        for (int i = 0; i < rowNum; i++) {

            containsC = Arrays.asList(matrix[i]).contains("C");
            if (containsC) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j].equals("C")) controlCount++;
                }
            }
            containsCX = Arrays.asList(matrix[i]).contains("CX");
            containsCZ = Arrays.asList(matrix[i]).contains("CZ");
            containsCCX = Arrays.asList(matrix[i]).contains("CCX");

            if (containsCCX) {
                result[i] = controlCount >= 2;
            } else {
                result[i] = containsC == (containsCX || containsCZ);
            }
        }

        return !Arrays.asList(result).contains(false);
    }

    private static ZMatrixRMaj singleQubitGateIdentifier(String gateName) {
        if (gateName.equals("I")) {
            return IDENTITY_GATE();
        } else if (gateName.equals("X")) {
            return X_GATE();
        } else if (gateName.equals("Y")) {
            return Y_GATE();
        } else if (gateName.equals("Z")) {
            return Z_GATE();
        } else if (gateName.equals("H")) {
            return HADAMARD_GATE();
        } else if (gateName.equals("S")) {
            return S_GATE();
        } else if (gateName.equals("T")) {
            return T_GATE();
        } else if (gateName.contains("P")) {
            return PHASE_GATE(Double.parseDouble(gateName.substring(1)));
        } else if (gateName.contains("Rx")) {
            return RX_GATE(Double.parseDouble(gateName.substring(2)));
        } else if (gateName.contains("Ry")) {
            return RY_GATE(Double.parseDouble(gateName.substring(2)));
        } else if (gateName.contains("Rz")) {
            return RZ_GATE(Double.parseDouble(gateName.substring(2)));
        } else {
            return null;
        }
    }

    private static void getProperGates(ArrayList<ZMatrixRMaj> gates, String[] instr, int qubitNumber) {

        for (int i = 0; i < qubitNumber; i++) {

            if (instr[i].contains("C")) {

                boolean regularOrder = instr[i].equals("C");
                boolean toffoli = Arrays.asList(instr).contains("CCX");

                ArrayList<String> multiQubitGate = new ArrayList<>();
                int numOfControl = 0;

                loop:
                for (int j = i; j < qubitNumber; j++) {

                    multiQubitGate.add(instr[j]);

                    switch (instr[j]) {
                        case "C" -> {
                            numOfControl++;
                            instr[j] = "-";
                            if (!regularOrder) {
                                if (!toffoli) break loop;
                                else if (numOfControl == 2) break loop;
                            }
                        }
                        case "CX", "CZ" -> {
                            instr[j] = "-";
                            if (regularOrder) break loop;
                        }
                        case "CCX" -> {
                            instr[j] = "-";
                            if (regularOrder && numOfControl == 2) break loop;
                        }
                        default -> instr[j] = "-";
                    }
                }

                gates.add(MULTI_INPUT_GATE(multiQubitGate, toffoli));
            } else {
                ZMatrixRMaj gateToAdd = singleQubitGateIdentifier(instr[i]);
                if (gateToAdd != null) {
                    gates.add(singleQubitGateIdentifier(instr[i]));
                }
            }
        }
    }

    private static ZMatrixRMaj kron(ArrayList<ZMatrixRMaj> instructions) {

        if (instructions.size() == 1) {
            return instructions.getFirst();
        } else {
            ZMatrixRMaj A = instructions.getFirst();
            instructions.removeFirst();
            ZMatrixRMaj B = kron(instructions);

            int sizeC = A.numCols * B.numCols;
            ZMatrixRMaj C = new ZMatrixRMaj(sizeC, sizeC);

            for (int i = 0; i < A.numRows; ++i) {
                for (int j = 0; j < A.numCols; ++j) {
                    Complex_F64 a = new Complex_F64();
                    A.get(i, j, a);

                    for(int rowB = 0; rowB < B.numRows; ++rowB) {
                        for (int colB = 0; colB < B.numCols; ++colB) {
                            Complex_F64 b = new Complex_F64();
                            B.get(rowB, colB, b);

                            Complex_F64 val = a.times(b);

                            C.set(i * B.numRows + rowB, j * B.numCols + colB, val.real, val.imaginary);
                        }
                    }
                }
            }

            return C;
        }
    }
}
