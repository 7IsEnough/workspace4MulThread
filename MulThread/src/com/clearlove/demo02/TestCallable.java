package com.clearlove.demo02;

import com.clearlove.demo01.TestThread2;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.io.FileUtils;

/**
 * @author promise
 * @date 2022/4/5 - 16:43
 * 线程创建方式3：实现callable接口
 * callable的好处
 *  1.可以定义返回值
 *  2.可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {

  /** 网络图片地址 */
  private String url;

  /** 保存的文件名 */
  private String name;

  public TestCallable(String url, String name) {
    this.url = url;
    this.name = name;
  }


  // 下载图片线程的执行体
  @Override
  public Boolean call() {
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.downloader(url, name);
    System.out.println("下载的文件名为：" + name);
    return true;
  }


  public static void main(String[] args) throws ExecutionException, InterruptedException {
    TestCallable t1 = new TestCallable("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "1.png");
    TestCallable t2 = new TestCallable("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "2.png");
    TestCallable t3 = new TestCallable("https://i0.hdslb.com/bfs/archive/ca375eb31fa90b8e23b88ed3433c2f60de1c2e6e.png", "3.png");

    // 创建执行服务
    ExecutorService ser = Executors.newFixedThreadPool(3);

    // 提交执行
    Future<Boolean> r1 = ser.submit(t1);
    Future<Boolean> r2 = ser.submit(t2);
    Future<Boolean> r3 = ser.submit(t3);

    // 获取结果
    Boolean rs1 = r1.get();
    Boolean rs2 = r2.get();
    Boolean rs3 = r3.get();

    System.out.println(rs1);
    System.out.println(rs2);
    System.out.println(rs3);

    // 关闭服务
    ser.shutdownNow();


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
