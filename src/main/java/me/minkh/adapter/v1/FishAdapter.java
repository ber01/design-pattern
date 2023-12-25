package me.minkh.adapter.v1;

public class FishAdapter implements Animal {

    private final Fish fish;

    public FishAdapter(Fish fish) {
        this.fish = fish;
    }

    @Override
    public void walk() {
        this.fish.swarm();
    }

}
