package anta.project.edcourser.utils;

import java.util.Random;

public class RandomUtils {

    public static int getInBetween(int from, int to){
        assert to>from;
        return new Random().nextInt(to-from)+from;
    }

    public static int getInBetween(int from, int to, Random rgen){
        assert to>from;
        return rgen.nextInt(to-from)+from;
    }
}
