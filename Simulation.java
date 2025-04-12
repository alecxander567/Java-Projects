import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            Random num = new Random();
            int rows;
            
            int totalInterTime = 0;
            int totalServTime = 0;
            int totalArrTime = 0;
            int totalTimeBegins = 0;
            int totalIdleTime = 0;
            int totalWaitingTime = 0;
            int totalTimeEnds = 0;
            int totalTimeSpentSystem = 0;
            
            System.out.println("\nMODELING AND SIMULATION SYSTEM");
            System.out.print("---------------------------------");
            
            while (true) {
                try {
                    System.out.print("\nEnter the number of customers : ");
                    rows = scan.nextInt();
                    
                    int[] customerNo = new int[rows];
                    int[] interTime = new int[rows];
                    int[] arrTime = new int[rows];
                    int[] servTime = new int[rows];
                    int[] timeBegins = new int[rows];
                    int[] waitingTime = new int[rows];
                    int[] timeEnds = new int[rows];
                    int[] timeSpentSystem = new int[rows];
                    int[] idleTime = new int[rows];
                    
                    // Generate inter-arrival and service times
                    for (int i = 0; i < rows; i++) {
                        customerNo[i] = i + 1;
                        interTime[i] = (i == 0) ? 0 : num.nextInt(5) + 1;
                        servTime[i] = num.nextInt(5) + 6;
                        
                        totalInterTime += interTime[i];
                        totalServTime += servTime[i];
                    }
                    
                    // Calculate arrival times
                    arrTime[0] = 0;
                    for (int i = 1; i < rows; i++) {
                        arrTime[i] = arrTime[i - 1] + interTime[i];
                    }
                    
                    for (int i = 0; i < rows; i++) {
                        totalArrTime += arrTime[i];
                    }
                    
                    // Compute other fields
                    for (int i = 0; i < rows; i++) {
                        if (i == 0) {
                            timeBegins[i] = arrTime[i];
                            idleTime[i] = arrTime[i];
                        } else {
                            timeBegins[i] = Math.max(arrTime[i], timeEnds[i - 1]);
                            idleTime[i] = Math.max(0, timeBegins[i] - timeEnds[i - 1]);
                        }
                        
                        waitingTime[i] = Math.max(0, timeBegins[i] - arrTime[i]);
                        timeEnds[i] = timeBegins[i] + servTime[i];
                        timeSpentSystem[i] = timeEnds[i] - arrTime[i];
                        
                        totalTimeBegins += timeBegins[i];
                        totalIdleTime += idleTime[i];
                        totalWaitingTime += waitingTime[i];
                        totalTimeEnds += timeEnds[i];
                        totalTimeSpentSystem += timeSpentSystem[i];
                    }
                    
                    System.out.println();
                    
                    // Print the formatted table
                    System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s\n",
                            "No.", "IAT", "AT", "ST", "TSB", "WT", "TE", "TSS", "IDT");
                    
                    for (int i = 0; i < rows; i++) {
                        System.out.printf("%-5d %-5d %-5d %-5d %-5d %-5d %-5d %-5d %-5d\n",
                                customerNo[i], interTime[i], arrTime[i], servTime[i],
                                timeBegins[i], waitingTime[i], timeEnds[i],
                                timeSpentSystem[i], idleTime[i]);
                    }
                    
                    System.out.printf("\n%-5s %-5d %-5d %-5d %-5d %-5d %-5d %-5d %-5d\n",
                            "Ttl:", totalInterTime, totalArrTime, totalServTime,
                            totalTimeBegins, totalWaitingTime, totalTimeEnds,
                            totalTimeSpentSystem, totalIdleTime);
                    
                    System.out.println("\nAverage Waiting Time: " + (double) totalWaitingTime / rows);
                    System.out.printf("Average Inter-Arrival Time: %.2f\n", (double) totalInterTime / rows);
                    System.out.printf("Average Service Time: %.2f\n", (double) totalServTime / rows);
                    System.out.printf("Average Time Spent in the System: %.2f\n", (double) totalTimeSpentSystem / rows);
                    System.out.printf("Probability of Customers Waiting in Queue: %.2f\n", (double) totalWaitingTime / rows);
                    System.out.printf("Probability of Idle Time: %.2f\n", (double) Arrays.stream(idleTime).sum() / rows);
                    
                } catch (Exception e) {
                    System.out.println("\nInvalid input please try again.");
                }
                
                scan.nextLine();
                
                System.out.print("\nDo you want to simulate again? (y/n) : ");
                char choice = scan.next().charAt(0);
                
                if (choice == 'n' || choice == 'N') {
                    System.out.println("\nProgram terminated...goodbye.");
                    break;
                }
            }
        }
    }
} 