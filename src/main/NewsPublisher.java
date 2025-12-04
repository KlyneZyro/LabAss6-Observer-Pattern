package main;

//The interface for the Publisher (Agency)
interface NewsPublisher {
 void subscribe(Subscriber s);
 void unsubscribe(Subscriber s);
 void notifySubscribers(String category, String content);
}
