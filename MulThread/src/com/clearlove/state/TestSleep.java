package com.clearlove.state;

import com.clearlove.demo01.TestThread4;

/**
 * @author promise
 * @date 2022/4/12 - 22:24
 * 模拟网路延时：放大问题的发生性
 */
public class TestSleep implements Runnable{
  // 票数
  private int ticketNums = 10;

  @Override
  public void run() {
    while (true) {
      if (ticketNums <= 0) {
        break;
      }

      // 模拟延时
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread().getName() + "-->" + "拿到了" + ticketNums-- + "票");
    }
  }

  public static void main(String[] args) {
    TestSleep ticket = new TestSleep();

    new Thread(ticket, "小明").start();
    new Thread(ticket, "老师").start();
    new Thread(ticket, "黄牛党").start();
  }
}
