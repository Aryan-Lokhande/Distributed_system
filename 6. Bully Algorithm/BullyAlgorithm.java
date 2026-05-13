import java.util.Scanner;

public class BullyAlgorithm {

    static boolean[] alive = new boolean[5]; 
    static int coordinator;

    static int findCoordinator() {
        for (int i = 4; i >= 0; i--) { 
            if (alive[i]) {
                return i + 1; 
            }
        }
        return -1; 
    }

    static void holdElection(int p) {
        System.out.println("\n[ELECTION] Process " + p + " initiates election");

        boolean higherAliveFound = false;
    
        for (int i = p; i < 5; i++) {      
            int target = i + 1;             
            System.out.println("  ELECTION msg: P" + p + " -> P" + target);

            if (alive[i]) {                 
                System.out.println("  OK msg: P" + target + " -> P" + p + "  (P" + p + " steps back)");
                higherAliveFound = true;
                
                holdElection(target);
                return; 
            }
        }

        if (!higherAliveFound) {
            if(coordinator == p){
                System.out.println("\n Process P"+p+" is already a Coordinator");
                return;
            }
            coordinator = p;
            System.out.println("\n[COORDINATOR] Process " + p + " wins! Sending COORDINATOR msg to all lower processes.");
            for (int i = 0; i < p - 1; i++) {
                System.out.println("  COORDINATOR msg: P" + p + " -> P" + (i + 1));
            }
            System.out.println(">>> New Coordinator = P" + coordinator);
        }
    }

    static void bringUp(int p) {
        if (p < 1 || p > 5) {
            System.out.println("Invalid! Enter 1–5.");
            return;
        }
        if (alive[p - 1]) {
            System.out.println("P" + p + " is already UP.");
            return;
        }

        alive[p - 1] = true;
        System.out.println("P" + p + " is now UP.");

        
        holdElection(p);
    }

    static void bringDown(int p) {
        if (p < 1 || p > 5) {
            System.out.println("Invalid! Enter 1–5.");
            return;
        }
        if (!alive[p - 1]) {
            System.out.println("P" + p + " is already DOWN.");
            return;
        }

        alive[p - 1] = false;
        System.out.println("P" + p + " is now DOWN.");
    }

    static void sendMessage(int p) {
        if (!alive[p - 1]) {
            System.out.println("P" + p + " is DOWN. Can't send message.");
            return;
        }

        System.out.println("P" + p + " tries to contact Coordinator P" + coordinator + "...");

        if (alive[coordinator - 1]) {
            
            System.out.println("Coordinator P" + coordinator + " responds: OK");
        } else {
            
            System.out.println("Coordinator P" + coordinator + " is DOWN! P" + p + " triggers election.");
            holdElection(p);
        }
    }
    
    static void showStatus() {
        System.out.print("Alive processes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print("P" + (i+1) + "=" + (alive[i] ? "UP" : "DOWN") + "  ");
        }
        System.out.println("\nCurrent Coordinator: P" + coordinator);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) alive[i] = true;
        coordinator = 5;

        System.out.println("=== BULLY ALGORITHM ===");
        showStatus();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Bring UP a process");
            System.out.println("2. Bring DOWN a process");
            System.out.println("3. Send message (triggers election if coordinator down)");
            System.out.println("4. Show status");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Which process to bring UP (1-5): ");
                    bringUp(sc.nextInt());
                    break;
                case 2:
                    System.out.print("Which process to bring DOWN (1-5): ");
                    bringDown(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Which process sends message (1-5): ");
                    sendMessage(sc.nextInt());
                    break;
                case 4:
                    showStatus();
                    break;
                case 5:
                    System.out.println("Exiting.");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}