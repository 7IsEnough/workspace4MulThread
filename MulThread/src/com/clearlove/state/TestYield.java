package com.clearlove.state;

/**
 * @author promise
 * @date 2022/4/16 - 16:13
 * 测试礼让线程
 * 礼让不一定成功，看CPU心情
 */
public class TestYield {

  public static void main(String[] args) {
    MyYield myYield = new MyYield();

    new Thread(myYield, "a").start();
    new Thread(myYield, "b").start();
  }
}

class MyYield implements Runnable {

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName() + "线程开始执行");
    // 礼让
    Thread.yield();
    System.out.println(Thread.currentThread().getName() + "线程停止执行");
  }
}
