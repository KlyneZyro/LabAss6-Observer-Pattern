package main;

class SMSSubscriber implements Subscriber {
    private String phoneNumber;

    public SMSSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getSubscriberName() {
        return this.phoneNumber;
    }

    @Override
    public void update(String category, String content) {
        // Simulating a short text message
        System.out.println("   [SMS Sent] To: " + phoneNumber + " | Alert: " + content);
    }
}