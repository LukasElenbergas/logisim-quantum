package com.cburch.logisim.data;

import java.util.ArrayList;

public class QuantumValue {

    public int bit;
    public ArrayList<String> instructions;

    public QuantumValue(int bit, ArrayList<String> instructions) {
        this.bit = bit;
        this.instructions = instructions;
    }

    public QuantumValue(QuantumValue q, String instruction) {
        this.bit = q.bit;
        this.instructions = new ArrayList<>(q.instructions);
        this.instructions.add(instruction);
    }

    public boolean equals(QuantumValue other) {
        return this.bit == other.bit
                && this.instructions.equals(other.instructions);
    }

    // For debugging purposes
    public void printValues() {
        System.out.println(this);
        System.out.println("Bit value: " + this.bit);
        System.out.println("Instruction list: " + this.instructions);
    }
}
