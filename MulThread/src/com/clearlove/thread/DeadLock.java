package com.clearlove.thread;

/**
 * @author promise
 * @date 2022/4/23 - 14:11
 * 死锁：多个线程互相抱着对方需要的资源，然后形成僵持
 */
public class DeadLock {

  public static void main(String[] args) {
    Makeup girl1 = new Makeup(0, "灰姑凉");
    Makeup girl2 = new Makeup(1, "白雪公主");

    girl1.start();
    girl2.start();
  }
}

// 口红
class Lipstick {

}

// 镜子
class Mirror {

}

class Makeup extends Thread {

  // 需要的资源只有一份，用static来保证只有一份
  static Lipstick lipstick = new Lipstick();
  static Mirror mirror = new Mirror();

  // 选择
  int choice;

  // 使用化妆品的人
  String girlName;

  Makeup(int choice, String name) {
    this.choice = choice;
    this.girlName = name;
  }

  @Override
  public void run() {
    // 化妆
    try {
      makeup();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  // 化妆，互相持有对方的锁，就是需要拿到对方的资源
  private void makeup() throws InterruptedException {
    if (choice == 0) {
      // 获取口红的锁
      synchronized (lipstick) {
        System.out.println(this.girlName + "获得口红的锁");
        Thread.sleep(1000);
        }
      // 1s后想获得镜子
      synchronized (mirror) {
        System.out.println(this.girlName + "获得镜子的锁");
      }
    } else {
      // 获取口红的锁
      synchronized (mirror) {
        System.out.println(this.girlName + "获得镜子的锁");
        Thread.sleep(2000);
      }
      // 1s后想获得镜子
      synchronized (lipstick) {
        System.out.println(this.girlName + "获得口红的锁");
      }
    }
  }
}


