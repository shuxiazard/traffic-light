package demo3;


/**
 * Test
 *
 * @author shuxia
 * @date 7/22/2021
 */
public class Test {
    public static void main(String[] args) {
        for (OrdinaryLamp value : OrdinaryLamp.values()) {
            new Road(value.name());
        }
        new OrdinaryLampController().on();
    }

}
