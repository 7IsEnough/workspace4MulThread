package com.clearlove.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author promise
 * @date 2022/4/20 - 23:54
 * 线程不安全的集合
 */
public class UnsafeList {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {

      new Thread(
              () -> {
                synchronized (list) {
                  list.add(Thread.currentThread().getName());
                }
              })
          .start();
    }

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(list.size());
  }
}
