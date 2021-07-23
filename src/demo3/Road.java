package demo3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Road
 *
 * @author shuxia
 * @date 7/22/2021
 */
public class Road {
    //车辆
    private List<String> car = new ArrayList<>();
    //路线
    private String name;
    Random random = new Random();

    public Road(String name) {
        this.name = name;
        //生成汽车
        final ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(() -> {
            System.out.println(name);
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.car.add(OrdinaryLamp.valueOf(name) + "" + i);
            }
        });
        //汽车通过
        final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(
                () -> {
                    if (car.size() > 0) {
                        final OrdinaryLamp lamp = OrdinaryLamp.valueOf(this.name);
                        //随机黄灯超线
                        final int flag = random.nextInt(2);
                        if (flag == 0) {
                            lamp.setOverTheLine();
                        }
                        final Semaphore semaphore = lamp.getSemaphore();
                        //黄灯
                        if (semaphore.equals(Semaphore.YELLOW)) {
                            final boolean overTheLine = lamp.isOverTheLine();
                            if (overTheLine) {
                                System.out.println(car.remove(0) + "--黄灯--通过了");
                            }
                        }
                        if (semaphore.equals(Semaphore.GREEN)) { System.out.println(car.remove(0) + "--绿灯--通过了"); }
                    }
                }, 1, 1, TimeUnit.SECONDS);

    }

}
