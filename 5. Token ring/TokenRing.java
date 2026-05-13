import java.util.Scanner;

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int token = 0;

        System.out.print("Enter the No. of processes: ");
        int n = sc.nextInt();

        // Display ring
        System.out.print("Ring:");
        for(int i = 0; i < n; i++){
            System.out.print(" P" + i + " ->");
        }
        System.out.println(" P0");

        int s, r, data;

        while(true){
            System.out.println("\n  Token is at P" + token);
            System.out.print("  Enter the sender process (0-" + (n-1) + "): ");
            s = sc.nextInt();
            System.out.print("  Enter the receiver process (0-" + (n-1) + "): ");
            r = sc.nextInt();
            System.out.print("  Enter the data to send: ");
            data = sc.nextInt();

            System.out.print("\n  PHASE 1: [TOKEN Traversal]\n  ");
            while(token != s){
                System.out.print(" P" + token + " ->");
                token = (token + 1) % n;
            }
            System.out.println(" P" + token + " (sender receives token)");

            System.out.println("\n  PHASE 2: [DATA FORWARDING]");

            if(s == r){
                System.out.println("  Sender and Receiver are the same node!");
                System.out.println("  P" + s + " receives its own data: " + data);
            } else {
                System.out.print("  Data travels: P" + s);
                token = (token + 1) % n;
                while(token != r){
                    System.out.print(" -> P" + token);
                    token = (token + 1) % n;
                }
                System.out.println(" -> P" + token);
                System.out.println("\n  P" + r + " (receiver gets data): " + data);
            }
        }
    }
}