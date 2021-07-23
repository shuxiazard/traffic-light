package demo2;

import java.util.ArrayList;

/**
 * Test
 *
 * @author shuxia
 * @date 7/22/2021
 */
public class Test {
    public static void main(String[] args) {
        //导向灯变换
        new LampController().on();
        //普通灯变换
        new OrdinaryLampController().on();
        for (Lamp value : Lamp.values()) {
            new Road(value.name());
        }
    }

}
