package main;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Setup
        NewsAgency agency = new NewsAgency();
        
        Subscriber user1 = new EmailSubscriber("Klyne");
        Subscriber user2 = new SMSSubscriber("Zyro");
        Subscriber user3 = new EmailSubscriber("Reyes@neu.edu.ph");

        // 2. Subscribe
        System.out.println("--- Subscription Phase ---");
        agency.subscribe(user1);
        agency.subscribe(user2);
        agency.subscribe(user3);

        // 3. Publish News
        agency.publishNews("Tech", "New Java Version Released!");

        // 4. Unsubscribe and Publish Again
        Thread.sleep(500); // Wait a moment for previous async tasks to finish
        System.out.println("\n--- Unsubscribe Phase ---");
        
        agency.unsubscribe(user2);
        agency.publishNews("Sports", "Local team wins championship!");

        // 5. Cleanup
        // Because we used background threads, we must shut them down 
        // or the program will keep running forever.
        Thread.sleep(1000); // Wait for final emails to send
        agency.shutdown();
        System.out.println("\n--- System Shutdown ---");
    }
}