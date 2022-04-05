package com.clearlove.demo01;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/**
 * @author promise
 * @date 2022/4/5 - 13:53
 * 练习Thread，实现多线程同步下载图片
 */
public class TestThread2 implements Runnable{

  /** 网络图片地址 */
  private String url;

  /** 保存的文件名 */
  private String name;

  public TestThread2(String url, String name) {
    this.url = url;
    this.name = name;
  }


  // 下载图片线程的执行体
  @Override
  public void run() {
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.downloader(url, name);
    System.out.println("下载的文件名为：" + name);
  }

  public static void main(String[] args) {
    TestThread2 t1 = new TestThread2("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "1.png");
    TestThread2 t2 = new TestThread2("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "2.png");
    TestThread2 t3 = new TestThread2("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "3.png");

    new Thread(t1).start();
    new Thread(t2).start();
    new Thread(t3).start();
  }
}


// 下载器
class WebDownloader {
  // 下载方法
  public void downloader(String url, String name) {
    try {
      FileUtils.copyURLToFile(new URL(url), new File(name));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("IO异常，downloader方法出现问题:");
    }
  }
}
