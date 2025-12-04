package main;

interface Subscriber {
    void update(String newsCategory, String newsContent);
    String getSubscriberName();
}