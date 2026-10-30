@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent { }

class EmailSender implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class UrgentSmsSender implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("Urgent SMS: " + message);
    }
}

public class Notification {
    public static void main(String[] args) {
        Notifier email = message -> System.out.println("Email: " + message);
        Notifier sms = message -> System.out.println("SMS: " + message);

        Notifier[] notifiers = {email, sms};

        String message = "Exam starts tomorrow!";
        System.out.println("Broadcasting message:");
        for (Notifier notifier : notifiers) {
            notifier.send(message);
        }

        System.out.println("\nUrgent notification:");
        Notifier urgentNotifier = new UrgentSmsSender();
        if (urgentNotifier instanceof Urgent) {
            urgentNotifier.send(message);
            urgentNotifier.send(message);
        } else {
            urgentNotifier.send(message);
        }
    }
}
