package com.clearlove.syn;

/**
 * @author promise
 * @date 2022/4/19 - 21:51
 * 不安全的取钱
 * 两个人去取钱
 */
public class UnsafeBank {

  public static void main(String[] args) {
    // 账户
    Account account = new Account(100, "皮肤基金");

    Drawing you = new Drawing(account, 50, "你");
    Drawing skin = new Drawing(account, 100, "skin");

    you.start();
    skin.start();
  }
}

// 账户
class Account {

  // 余额
  int money;

  // 卡名
  String name;

  public Account(int money, String name) {
    this.money = money;
    this.name = name;
  }
}

// 银行：模拟取款
class Drawing extends Thread {
  // 账户
  Account account;

  // 取了多少钱
  int drawingMoney;

  // 现在手里有多少钱
  int nowMoney;

  public Drawing(Account account, int drawingMoney, String name) {
    super(name);
    this.account = account;
    this.drawingMoney = drawingMoney;
  }

  @Override
  public void run() {
    // 判断有没有钱
    if (account.money - drawingMoney < 0) {
      System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
      return;
    }

    // sleep可以放大问题的真实性
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 卡内余额 = 余额 - 你取的钱
    account.money = account.money - drawingMoney;

    // 你手里的钱
    nowMoney = nowMoney + drawingMoney;

    System.out.println(account.name + "余额为：" + account.money);
    // Thread.currentThread().getName() = this.getName()
    System.out.println(this.getName() + "手里的钱：" + nowMoney);
  }
}
