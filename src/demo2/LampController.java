package demo2;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
/**
 * LampController
 *
 * @author shuxia
 * @date 7/22/2021
 */
public class LampController {
    //导向灯
    private Lamp currentLamp;
    public LampController() {
        this.currentLamp = Lamp.S2N;
    }
    public void on() {
        this.currentLamp.turnGreen();
        //导向变灯
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
        es.scheduleAtFixedRate(() -> {
            //红灯前变黄
            this.currentLamp.turnYellow();
            System.out.println("--------------------");
            try {
                sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentLamp = this.currentLamp.turnRed();
        }, 10, 10, TimeUnit.SECONDS);
    }
}
