package main;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NewsAgency implements NewsPublisher {
    
    // FIX: Defined as CopyOnWriteArrayList to access specific thread-safe methods
    private CopyOnWriteArrayList<Subscriber> subscribers;
    
    // For handling notifications in the background
    private ExecutorService notificationPool;

    public NewsAgency() {
        // Initialize the thread-safe list
        this.subscribers = new CopyOnWriteArrayList<>();
        
        // Create a pool of 5 threads to handle notifications asynchronously
        this.notificationPool = Executors.newFixedThreadPool(5);
    }

    @Override
    public void subscribe(Subscriber s) {
        // .addIfAbsent() works now because the variable is explicitly a CopyOnWriteArrayList
        subscribers.addIfAbsent(s); 
        System.out.println("LOG: " + s.getSubscriberName() + " has joined.");
    }

    @Override
    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
        System.out.println("LOG: " + s.getSubscriberName() + " has unsubscribed.");
    }

    @Override
    public void notifySubscribers(String category, String content) {
        System.out.println("\n>> BROADCASTING NEWS (Async): [" + category + "] " + content);
        
        // The loop is safe because CopyOnWriteArrayList creates a snapshot for iteration
        for (Subscriber s : subscribers) {
            
            // Send the update logic to a background thread
            // This prevents the main program from freezing
            notificationPool.submit(() -> {
                s.update(category, content);
            });
        }
    }
    
    // Helper method to trigger the process
    public void publishNews(String category, String content) {
        notifySubscribers(category, content);
    }

    // Good Practice: Call this when shutting down your app to kill the background threads
    public void shutdown() {
        notificationPool.shutdown();
    }
}