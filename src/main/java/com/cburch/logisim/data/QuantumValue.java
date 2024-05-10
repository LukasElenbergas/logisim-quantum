package com.cburch.logisim.data;

import java.util.ArrayList;

public class QuantumValue {

    int id;
    int bit;
    ArrayList<String> instructions;

    public QuantumValue(int id, int bit, ArrayList<String> instructions) {
        this.id = id;
        this.bit = bit;
        this.instructions = instructions;
    }

    public QuantumValue(QuantumValue q) {
        this.id = q.id;
        this.bit = q.bit;
        this.instructions = q.instructions;
    }

    public void appendInstruction(String instr) { this.instructions.add(instr); }

    // For debugging purposes
    public void printValues() {
        System.out.println(this);
        System.out.println("Qubit ID: " + this.id + " Bit value: " + this.bit);
        System.out.println("Instruction list: " + this.instructions);
    }
}
