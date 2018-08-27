package com.edgewalk.accidence.signaling;

/**
 *
 */
public class Apple {
    public int index;
    public boolean flag;
    public void product() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("生产第%d个苹果\n",++index);
    }

    public void take(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("消费第%d个苹果\n",index);
    }
}
