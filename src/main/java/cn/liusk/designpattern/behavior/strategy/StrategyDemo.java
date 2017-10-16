package cn.liusk.designpattern.behavior.strategy;

public class StrategyDemo {

    public static void main(String[] args) {
        ICalculator calcu = new Multiply();
        System.out.println(calcu.calculator("2*5"));
    }

}
