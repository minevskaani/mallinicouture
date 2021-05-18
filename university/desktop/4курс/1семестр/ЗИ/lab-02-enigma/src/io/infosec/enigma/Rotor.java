package io.infosec.enigma;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Kostadin Almishev
 */
public class Rotor {
    private final Wire[] wires = new Wire[256];
    private final Wire[] backwardWires = new Wire[256];

    private int pos = 0;

    public Rotor() {
        Random rnd = new Random();

        List<Byte> nums = new LinkedList<>();
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            nums.add((byte)i);
        }

        for (int i = 0; i < 256; i++) {
            byte currNumber = nums.get(rnd.nextInt(nums.size()));
            wires[i] = new Wire((byte)i, currNumber);
            backwardWires[i] = new Wire(currNumber, (byte) i);

            nums.remove((Byte)currNumber);
        }
    }

    public Rotor(Rotor that) {
        for (int i = 0; i < wires.length; i++) {
            wires[i] = new Wire(that.wires[i]);
            backwardWires[i] = new Wire(that.backwardWires[i]);
        }

        pos = that.pos;
    }


    public byte value(byte symbol) {
        //return wires[((int)symbol + 128 + pos) % 256];
        for (int i = 0; i < wires.length; i++) {
            if (wires[i].key == symbol) {
                return wires[(i + pos) % 256].value;
            }
        }

        throw new RuntimeException("Symbol not found in the Rotor");
    }

    public byte backwardValue(byte symbol) {
        //return backwardWires[((int)symbol + 128 + 256 - pos) % 256];

        for (int i = 0; i < backwardWires.length; i++) {
            if (backwardWires[i].key == symbol) {
                return backwardWires[(i + 256 - pos) % 256].value;
            }
        }

        throw new RuntimeException("Symbol not found in the Rotor");
    }

    public boolean rotate() {
        if (pos == 255) {
            pos = 0;
            return true;
        }

        pos += 1;
        return false;
    }

    class Wire {
        byte key;
        byte value;

        public Wire(byte key, byte value) {
            this.key = key;
            this.value = value;
        }

        public Wire(Wire that) {
            this.key = that.key;
            this.value = that.value;
        }
    }

}
