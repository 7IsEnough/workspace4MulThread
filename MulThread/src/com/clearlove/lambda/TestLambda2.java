package com.clearlove.lambda;

/**
 * @author promise
 * @date 2022/4/10 - 22:14
 */
public class TestLambda2 {


  public static void main(String[] args) {

    // 1.lambda表达式简化
    ILove love = (int a, int b) -> {System.out.println("i love you-->" + a);
        };

    // 简化1：去掉参数类型
    love = (a, b) ->{System.out.println("i love you-->" + a);};

    // 简化2：去掉括号
    love = (a, b) -> {System.out.println("i love you-->" + a);};

    // 简化3：去掉花括号
    love = (a, b) -> System.out.println("i love you-->" + a);

    /**
     * 总结：
     *    1.lambda表达式只能有一行代码的情况下才能简化成为一行，如果有多行，那么就用代码块包裹
     *    2.前提是接口为函数式接口
     *    3.多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号
     */


    love.love(521, 502);
  }
}

interface ILove {
  void love(int a, int b);
}
