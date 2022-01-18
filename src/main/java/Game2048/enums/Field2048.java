package Game2048.enums;

import java.util.HashMap;

public class Field2048 {
    public static Field2048 EMPTY = new Field2048();

    private int power;

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
            String s = (int)Math.pow(2, power) + "";
            while(s.length() < 4) {
                s = " " + s;
            }
            return s;
        }
        return "2^"+power;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Field2048) {
            Field2048 f = (Field2048) o;
            if(f.getPower() == this.getPower())
                return true;
        }
        return false;
    }
}
