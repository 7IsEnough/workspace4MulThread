package com.clearlove.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author promise
 * @date 2022/4/23 - 14:02
 * 测试JUC安全类型的集合
 */
public class TestJUC {
  public static void main(String[] args) {
    CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
    for (int i = 0; i < 10000; i++) {
      new Thread(() -> {
        list.add(Thread.currentThread().getName());
      }).start();
    }

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(list.size());
  }
}
