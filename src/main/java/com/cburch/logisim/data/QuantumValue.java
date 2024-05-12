package com.cburch.logisim.data;

import java.util.ArrayList;

public class QuantumValue {

    public int id;
    public int bit;
    public ArrayList<String> instructions;

    public QuantumValue(int id, int bit, ArrayList<String> instructions) {
        this.id = id;
        this.bit = bit;
        this.instructions = instructions;
    }

    public QuantumValue(QuantumValue q, String instruction) {
        this.id = q.id;
        this.bit = q.bit;
        this.instructions = new ArrayList<>(q.instructions);
        this.instructions.add(instruction);

        // TODO: Remove debugging print
        printValues();
    }

    public boolean equals(QuantumValue other) {
        return this.id == other.id
                && this.bit == other.bit
                && this.instructions.equals(other.instructions);
    }

    // For debugging purposes
    public void printValues() {
        System.out.println(this);
        System.out.println("Qubit ID: " + this.id + " Bit value: " + this.bit);
        System.out.println("Instruction list: " + this.instructions);
    }
}
