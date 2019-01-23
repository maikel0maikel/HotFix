package com.zbq.demo.hotfix.method;

/**
 * 生成方法
 * 在eclipse中run as 选择run configurations 选择
 * common选项卡在Output File 一栏中选择保存的文件
 */
public class ProductMethod {
    public static void main(String[] args) {
        productMethod();
    }

    private static void productMethod() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("private void method" + i + "(){");
            if (i == 0) {
                System.out.println("    method" + 9999 + "();");
            } else {

                System.out.println("    method" + (i - 1) + "();");
            }
            System.out.println("}");
        }
    }
}
