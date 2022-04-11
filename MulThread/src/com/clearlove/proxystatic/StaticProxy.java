package com.clearlove.proxystatic;

/**
 * @author promise
 * @date 2022/4/11 - 21:50
 * 静态代理模式总结：
 *    1.真实对象和代理对象都要实现同一个接口
 *    2.代理对象要代理真实角色
 * 好处：
 *    1.代理对象可以做很多真实对象做不了的事情
 *    2.真实对象专注做自己的事情
 */
public class StaticProxy {
  public static void main(String[] args) {
    // 你要结婚
    You you = new You();

    new Thread(() -> System.out.println("爱老虎油")).start();

    new WeddingCompany(new You()).happyMarry();

  }
}

interface Marry {
  void happyMarry();
}

// 真实角色，你去结婚
class You implements Marry {

  @Override
  public void happyMarry() {
    System.out.println("明老师要结婚了，超开心");
  }
}

// 代理角色，帮助你结婚
class WeddingCompany implements Marry {

  // 代理谁--> 真实目标角色
  private Marry target;

  public WeddingCompany(Marry target) {
    this.target = target;
  }

  @Override
  public void happyMarry() {
    before();
    // 这就是真实对象
    this.target.happyMarry();
    after();
  }

  private void after() {
    System.out.println("结婚之后，收尾款");
  }

  private void before() {
    System.out.println("结婚之前，布置现场");
  }
}
