package com.clearlove.state;

/**
 * @author promise
 * @date 2022/4/16 - 20:32
 * 测试线程的优先级
 * 理解方式--1张彩票与100张彩票的中奖
 */
public class TestPriority {

  public static void main(String[] args) {
    // 主线程默认优先级
    System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());

    MyPriority myPriority = new MyPriority();

    Thread t1 = new Thread(myPriority);
    Thread t2 = new Thread(myPriority);
    Thread t3 = new Thread(myPriority);
    Thread t4 = new Thread(myPriority);
    Thread t5 = new Thread(myPriority);
    Thread t6 = new Thread(myPriority);

    // 先设置优先级，再启动
    t1.start();

    t2.setPriority(1);
    t2.start();

    t3.setPriority(4);
    t3.start();

    // MAX_PRIORITY = 10
    t4.setPriority(Thread.MAX_PRIORITY);
    t4.start();

    t5.setPriority(8);
    t5.start();

    t6.setPriority(7);
    t6.start();
  }
}

class MyPriority implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
  }
}
