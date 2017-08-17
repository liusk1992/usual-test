/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author liusk
 * @version $Id: Test.java, v 0.1 2017年5月20日 下午5:37:12 liusk Exp $
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 创建一个线程池对象，控制要创建几个线程对象。
        // public static ExecutorService newFixedThreadPool(int nThreads)
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 可以执行Runnable对象或者Callable对象代表的线程
        pool.submit(new MyRunnable(1L));
        pool.submit(new MyRunnable(2L));
        pool.submit(new MyRunnable(3L));
        pool.submit(new MyRunnable(4L));

        //结束线程池
        pool.shutdown();
    }

    public static void test2() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}

class MyRunnable implements Runnable {

    private long guid = 0L;

    /**
     * 
     */
    public MyRunnable(long guid) {
        this.guid = guid;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + ":" + x + "--" + this.guid);
        }
    }
}
