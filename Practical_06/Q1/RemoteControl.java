interface Power {
    boolean allow(Switchable device, int hour);
}

interface Switchable {
    void on();
    void off();
    boolean isOn();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

class Fan implements Switchable {
    private boolean run = false;

    @Override
    public void on() {
        run = true;
        System.out.println("Fan is turned on.");
    }

    @Override
    public void off() {
        run = false;
        System.out.println("Fan is turned off.");
    }

    @Override
    public boolean isOn() {
        return run;
    }
}

class Light implements Switchable {
    private boolean light = false;

    @Override
    public void on() {
        light = true;
        System.out.println("Light is turned on.");
    }

    @Override
    public void off() {
        light = false;
        System.out.println("Light is turned off.");
    }

    @Override
    public boolean isOn() {
        return light;
    }
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] device = {new Fan(), new Light()};
        for (Switchable d : device) {
            d.toggle();
        }

        int hour = 20;
        Power p = new Power() {
            @Override
            public boolean allow(Switchable device, int hour) {
                return hour >= 18;
            }
        };

        Power ruleLambda = (d, h) -> h < 22;

        System.out.println("\n--- Testing Rules at hour " + hour + " ---");
        System.out.println("Anonymous class result: " + p.allow(device[0], hour));
        System.out.println("Lambda result: " + ruleLambda.allow(device[0], hour));
    }
}
