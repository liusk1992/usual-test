package cn.liusk.designpattern.behavior.strategy;

public class AbsCalculator {
    public int[] splitExp(String exp, String sym) {
        int[] arr = new int[2];
        String[] str = exp.split(sym);
        arr[0] = Integer.parseInt(str[0]);
        arr[1] = Integer.parseInt(str[1]);
        return arr;
    }
}
