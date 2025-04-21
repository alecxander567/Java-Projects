import java.util.Scanner;
public class accounts {

    String username;
    int passcode;

    accounts(String username, int passcode) {
        this.username = username;
        this.passcode = passcode;
    }

    void inputAccounts() {
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter your username : ");
        this.username = scan.nextLine();
        System.out.print("Enter your passcode : ");
        this.passcode = scan.nextInt();
    }

    void callAcc() {
        System.out.println("\nUsername : " + username);
        System.out.println("Passcode : " + passcode);
    }

    public static accounts[] verifyCredentials(accounts[] acc, String[] posts, String enterUserName, int enterPasscode) {
        Scanner scan = new Scanner(System.in);
        int index = -1;

        for (int i = 0; i < acc.length; i++) {
            if (acc[i].username.equals(enterUserName) && acc[i].passcode == enterPasscode) {
                System.out.println("\nLog in successful!");
                index = i;
                postSomething(posts);
                break;
            } else {
                System.out.println("\nInvalid Credentials!");
                break;
            }
        }

        if (index == -1) {
            System.out.print("\nDo you want to create a new account? (y/n) : ");
            char option = scan.next().charAt(0);

            if (option == 'y' || option == 'Y') {
                signUpAccounts(acc);
            }
        }

        return acc;
    }

    public static accounts[] signUpAccounts(accounts[] acc) {
        Scanner scan = new Scanner(System.in);

        accounts[] newAcc = new accounts[acc.length + 1];

        for (int i = 0; i < acc.length; i++) {
            newAcc[i] = acc[i];
        }
        
        scan.nextLine();
        System.out.print("Enter your username : ");
        String newUserName = scan.nextLine();
        System.out.print("Enter your passcode : ");
        int newPasscode = scan.nextInt();

        newAcc[acc.length] = new accounts(newUserName, newPasscode);
        
        System.out.println("\nAccount created successfully!");
        return newAcc;
    }

    public static String[] postSomething(String[] posts) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWhat's on your mind today? : ");
        for (int i = 0; i < posts.length; i++) {
            posts[i] = scan.nextLine();
        }

        System.out.println("\nYour post has been posted!");
        return posts;
    }

    public static void displayPosts(String[] posts) {
        for (int i = 0; i < posts.length; i++) {
            System.out.println(posts[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
            int option;

            System.out.println("Posting in terminal");
            System.out.print("------------------------");
            
            accounts[] acc = new accounts[3];
            String[] posts = new String[1];

            for (int i = 0; i < acc.length; i++) {
                acc[i] = new accounts("", 0);
            }

            while (true) {
                System.out.print("\nEnter your choice : ");
                option = scan.nextInt();

                switch (option) {
                    case 1:
                        for (int i = 0; i < acc.length; i++) {
                            acc[i].inputAccounts();
                        }
                        break;
                    case 2:
                        for (int i = 0; i < acc.length; i++) {
                            acc[i].callAcc();
                        }
                        break;
                    case 3:
                        String enterUserName;
                        int enterPasscode;

                        scan.nextLine();
                        System.out.print("\nEnter your username : ");
                        enterUserName = scan.nextLine();
                        System.out.print("Enter passcode : ");
                        enterPasscode = scan.nextInt();

                        acc = verifyCredentials(acc, posts, enterUserName, enterPasscode);
                        break;
                    case 4:
                        boolean hasPost = false;
                        if (posts != null) {
                            for (String post : posts) {
                                if (post != null && !post.trim().isEmpty()) {
                                    hasPost = true;
                                    break;
                                }
                            }
                        }

                        if (!hasPost) {
                            System.out.println("\nYou must log in first!");
                        } else {
                            displayPosts(posts);
                        }
                        break;
                    case 0:
                        System.out.println("\nProgram exiting...");
                        break;
                }
            }
        }
    }