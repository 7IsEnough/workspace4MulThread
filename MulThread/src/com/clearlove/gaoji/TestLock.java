package com.clearlove.gaoji;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author promise
 * @date 2022/4/25 - 19:50
 *
 */
public class TestLock {

  public static void main(String[] args) {
    TestLock2 testLock2 = new TestLock2();

    new Thread(testLock2).start();
    new Thread(testLock2).start();
    new Thread(testLock2).start();
  }
}

class TestLock2 implements Runnable{

  int ticketNums = 10;

  // 定义Lock锁
  private final ReentrantLock lock = new ReentrantLock();

  @Override
  public void run() {
    while (true) {
      // 加锁
      try {
        lock.lock();
        if (ticketNums > 0) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(ticketNums--);
        }else {
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        // 解锁
        lock.unlock();
      }

    }
  }
}
