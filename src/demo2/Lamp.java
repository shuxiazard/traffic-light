package demo2;

public enum Lamp {
    // 直行线路的信号灯,直行灯亮完之后,左拐的灯再亮,左拐的灯变红之后,另一个直行的灯变亮
    S2N("N2S", "S2W", Semaphore.RED, true), S2W("N2E", "E2W", Semaphore.RED, false),
    E2W("W2E", "E2S", Semaphore.RED, false), E2S("W2N", "S2N", Semaphore.RED, false),
    // 左拐线路的信号灯,先关灯,然后由直行线路的灯换亮
    N2S(null, null, Semaphore.RED, false), N2E(null, null, Semaphore.RED, false),
    W2E(null, null, Semaphore.RED, false), W2N(null, null, Semaphore.RED, false),
    // 右拐线路的信号灯,假设所有右拐路线的信号灯为长亮
    S2E(null, null, Semaphore.GREEN, false), E2N(null, null, Semaphore.GREEN, false),
    N2W(null, null, Semaphore.GREEN, false), W2S(null, null, Semaphore.GREEN, false),
    //普通红绿灯
    STRAIGHT(null,null, Semaphore.RED,false), Right(null,null,Semaphore.GREEN,false);
    //相反方向
    private String opposite = null;
    //下个变亮的方向
    private String next = null;
    //灯状态
    private Semaphore semaphore = null;
    //过线
    private boolean overTheLine;

    private Lamp(String opposite, String next, Semaphore semaphore, boolean overTheLine) {
        this.opposite = opposite;
        this.next = next;
        this.semaphore = semaphore;
        this.overTheLine = overTheLine;
    }

    //变绿
    public void turnGreen() {
        this.semaphore = Semaphore.GREEN;
        System.out.println(this.name() + "绿灯");
        if (this.opposite != null) {
            Lamp.valueOf(this.opposite).turnGreen();
        }
    }

    //变黄
    public void turnYellow() {
        this.semaphore = Semaphore.YELLOW;
        System.out.println(this.name() + "黄灯");
        if (this.opposite != null) {
            Lamp.valueOf(this.opposite).turnYellow();
        }
    }

    //变红
    public Lamp turnRed() {
        Lamp next = null;
        this.semaphore = Semaphore.RED;
        System.out.println(this.name() + "红灯");
        //相反方向变红
        if (this.opposite != null) {
            Lamp.valueOf(this.opposite).turnRed();
            System.out.println("--------------------");
        }
        //下一个灯
        if (this.next != null) {
            next = Lamp.valueOf(this.next);
            next.turnGreen();
            System.out.println("-------------------");
        }
        return next;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
    public boolean isOverTheLine() { return overTheLine; }
    public void setOverTheLine() { this.overTheLine = !this.overTheLine; }
}
