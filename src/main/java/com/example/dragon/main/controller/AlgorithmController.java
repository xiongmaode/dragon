package com.example.dragon.main.controller;

/**
 * @ClassNAME AlgorithmController
 * @Description 算法的尝试
 * @Author XiongMao
 * @Date 2019-8-14
 */
public class AlgorithmController {

    public static void main(String[] args) {
        //1+（1+2）+（1+2+3）+...+(1+2+3+...+100)
        int sum = 0;
        for (int i = 1; i < 101; i++) {
            for (int y = i; y > 0; y--) {
                sum = sum + y;
            }
        }
        System.out.println(sum);
    }
}
