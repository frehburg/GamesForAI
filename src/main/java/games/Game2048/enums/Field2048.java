package games.Game2048.enums;

public class Field2048 {
    public static Field2048 EMPTY = new Field2048();

    private final int power;

    public Field2048(int power) {
        this.power = power;
        if(power < 1) {
            power = 1;
        }
    }

    private Field2048() {
        this.power = 0;
    }

    public int getPower(){
        return power;
    }

    @Override
    public String toString() {
        if(power == 0) {
            return "    ";
        }
        if(power < 11) {
            StringBuilder s = new StringBuilder((int) Math.pow(2, power) + "");
            while(s.length() < 4) {
                s.insert(0, " ");
            }
            return s.toString();
        }
        return "2^"+power;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Field2048 f) {
            return f.getPower() == this.getPower();
        }
        return false;
    }
}
