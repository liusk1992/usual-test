package cn.liusk.designpattern.behavior.strategy;

public class Multiply extends AbsCalculator implements ICalculator {

    @Override
    public int calculator(String exp) {
        int[] arr = splitExp(exp, "\\*");
        int a = arr[0];
        int b = arr[1];
        return a * b;
    }

}
