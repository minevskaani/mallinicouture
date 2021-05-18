package io.infosec.enigma;

/**
 * @author Kostadin Almishev
 */
public class Enigma {

    private final Rotor leftRotor ;
    private final Rotor middleRotor;
    private final Rotor rightRotor;

    private final Reflector reflector;

    public Enigma() {
        this.leftRotor = new Rotor();
        this.middleRotor = new Rotor();
        this.rightRotor = new Rotor();

        this.reflector = new Reflector();
    }

    public Enigma(Enigma that) {
        this.leftRotor = new Rotor(that.leftRotor);
        this.middleRotor = new Rotor(that.middleRotor);
        this.rightRotor = new Rotor(that.rightRotor);

        this.reflector = new Reflector(that.reflector);
    }

    public byte value(byte symbol) {
        symbol = leftRotor.value(symbol);
        symbol = middleRotor.value(symbol);
        symbol = rightRotor.value(symbol);

        symbol = reflector.reflect(symbol);

        symbol = rightRotor.backwardValue(symbol);
        symbol = middleRotor.backwardValue(symbol);
        symbol = leftRotor.backwardValue(symbol);

        if (leftRotor.rotate()) {
            if (middleRotor.rotate()) {
                rightRotor.rotate();
            }
        }

        return symbol;
    }

    public byte[] value(byte[] symbols) {
        byte[] result = new byte[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            result[i] = value(symbols[i]);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enigma)) return false;

        Enigma enigma = (Enigma) o;

        if (!leftRotor.equals(enigma.leftRotor)) return false;
        if (!middleRotor.equals(enigma.middleRotor)) return false;
        if (!rightRotor.equals(enigma.rightRotor)) return false;
        return reflector.equals(enigma.reflector);
    }
}
