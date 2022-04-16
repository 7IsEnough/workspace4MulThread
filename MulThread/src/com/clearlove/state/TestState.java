package com.clearlove.state;

import java.lang.Thread.State;

/**
 * @author promise
 * @date 2022/4/16 - 16:32
 * 观察测试线程的状态
 */
public class TestState {

  public static void main(String[] args) throws InterruptedException {
    Thread thread =
        new Thread(
            () -> {
              for (int i = 0; i < 5; i++) {
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
              System.out.println("///////");
            });

    // 观察状态
    State state = thread.getState();
    // NEW
    System.out.println(state);

    // 观察启动后
    // 启动线程
    thread.start();
    // RUN
    state = thread.getState();
    System.out.println(state);

    while (state != State.TERMINATED) {
      // 只要线程不终止，就一直输出状态
      Thread.sleep(100);
      // 更新线程状态
      state = thread.getState();
      // 输出状态
      System.out.println(state);
    }
  }
}
