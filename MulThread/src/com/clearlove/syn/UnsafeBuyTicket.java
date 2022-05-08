package com.clearlove.syn;

/**
 * @author promise
 * @date 2022/4/19 - 21:34
 * 不安全的买票
 * 线程不安全，有负数
 */
public class UnsafeBuyTicket {

  public static void main(String[] args) {
    BuyTicket station = new BuyTicket();

    new Thread(station, "苦逼的我").start();
    new Thread(station, "牛逼的你们").start();
    new Thread(station, "可恶的黄牛党").start();
  }
}

class BuyTicket implements Runnable {

  // 票
  private int ticketNums = 10;

  // 外部停止方式
  boolean flag = true;

  @Override
  public void run() {
    // 买票
    while (flag) {
      try {
        buy();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  // synchronized 同步方法，锁的是this
  private synchronized void buy() throws InterruptedException {
    // 判断是否有票
    if (ticketNums <= 0) {
      flag = false;
      return;
    }
    Thread.sleep(100);
    // 买票
    System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
  }
}
