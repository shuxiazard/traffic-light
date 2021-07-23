package demo3;

import demo2.Lamp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * OrdinaryLampController
 *
 * @author shuxia
 * @date 7/22/2021
 */
public class OrdinaryLampController {
    //普通红绿灯
    private OrdinaryLamp straight;
public OrdinaryLampController(){this.straight=OrdinaryLamp.STRAIGHT;}
public void on(){
    this.straight.turnGreen();
    //普通变灯
    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
      service.scheduleAtFixedRate(()->{
            this.straight.turnYellow();
            try {
                sleep(3000);
                this.straight.turnRed();
                System.out.println("--------------------");
                sleep(3000);
                this.straight.turnGreen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },10,10, TimeUnit.SECONDS);
}
}
