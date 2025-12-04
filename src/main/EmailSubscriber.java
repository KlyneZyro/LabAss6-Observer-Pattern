package main;

class EmailSubscriber implements Subscriber {
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public String getSubscriberName() {
        return this.email;
    }

    @Override
    public void update(String category, String content) {
        // Simulating sending an email
        System.out.println("   [EMAIL Sent] To: " + email + " | Subject: " + category + " | Body: " + content);
    }
}



