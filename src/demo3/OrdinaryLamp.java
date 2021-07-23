package demo3;

/**
 * OrdinaryLamp
 * 普通红绿灯
 *
 * @author shuxia
 * @date 7/22/2021
 */
public enum OrdinaryLamp {
    //路线
    STRAIGHT(Semaphore.GREEN, false), Right(Semaphore.GREEN, false);
    //灯状态
    private Semaphore semaphore = null;
    //过线
    private boolean overTheLine;

    private OrdinaryLamp(Semaphore semaphore, boolean overTheLine) {
        this.semaphore = semaphore;
        this.overTheLine = overTheLine;
    }

    //绿灯
    public void turnGreen() {
        this.semaphore = Semaphore.GREEN;
        System.out.println(this.name() + "绿灯");
    }

    //黄灯
    public void turnYellow() {
        this.semaphore = Semaphore.YELLOW;
        System.out.println(this.name() + "黄灯");
    }

    //红灯
    public void turnRed() {
        this.semaphore = Semaphore.RED;
        System.out.println(this.name() + "红灯");
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public boolean isOverTheLine() {
        return overTheLine;
    }

    public void setOverTheLine() {
        this.overTheLine = !this.overTheLine;
    }
}
