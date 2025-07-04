package com.gusgutsy.afterhalls;

public class DurabilityTracker {
    private int ticks = 0;

    public void tick() {
        ticks++;
    }

    public boolean shouldHeal() {
        return ticks >= 300;
    }

    public void reset() {
        ticks = 0;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
}