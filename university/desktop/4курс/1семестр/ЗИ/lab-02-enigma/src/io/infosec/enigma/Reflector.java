package io.infosec.enigma;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kostadin Almishev
 */
public class Reflector {
    private final Map<Byte, Byte> wires = new HashMap<>(255);

    public Reflector() {
        for (byte i = Byte.MIN_VALUE; i < 0; i++) {
            wires.put(i, (byte)(i + 128));
            wires.put((byte)(i + 128), i);
        }
    }

    public Reflector(Reflector that) {
        for (Map.Entry<Byte, Byte> wire : that.wires.entrySet()) {
            this.wires.put(wire.getKey(), wire.getValue());
        }
    }

    public Byte reflect(Byte input) {
        return wires.get(input);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reflector)) return false;

        Reflector reflector = (Reflector) o;

        return wires.equals(reflector.wires);
    }
}
