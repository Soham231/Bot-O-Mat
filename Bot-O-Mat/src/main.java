package src;

import java.util.*;

class main {

    private static List<Robot> robots;
    private static List<String> robotTypes;

    public static void printMessage() {
        System.out.println("\ns - Assign & Start Tasks");
        System.out.println("r - View robot types");
        System.out.println("c - Create a robot");
        System.out.println("v - View created robots");
        System.out.println("l - View leaderboard");
        System.out.println("e - Exit program\n");
    }

    public static void printRobots() {
        System.out.println("Here are the types of Robots:\n");

        System.out.println("Unipedal");
        System.out.println("Bipedal");
        System.out.println("Quadrupedal");
        System.out.println("Arachnid");
        System.out.println("Radial");
        System.out.println("Aeronautical");
    }
    
    public static void addRobots() {
        robotTypes = new ArrayList<>();
        robotTypes.add("unipedal");
        robotTypes.add("bipedal");
        robotTypes.add("quadrupedal");
        robotTypes.add("arachnid");
        robotTypes.add("radial");
        robotTypes.add("aeronautical");
    }

    
    public static void main(String[] args) {
        boolean running = true;
        robots = new ArrayList<>();
        addRobots();
        System.out.println("\nWelcome to Botmania! Please select from the options below.\n\n");

        while (running == true) {
            TaskManager manager = new TaskManager();
            printMessage();
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().replaceAll("\\s", "");

            if (input.equals("r") || input.equals("R")) {
                printRobots();
                System.out.println("\n");
            } else if (input.equals("c") || input.equals("C")) {
                System.out.println("\nCreate your own robot!\n");
                System.out.println("Please create your robot in the following format: ");
                System.out.println("Name, Typeofrobot\n");
                boolean robotCreate = true;
                while(robotCreate) {
                    Scanner scanRobot = new Scanner(System.in);
                    String robotInfo = scanRobot.nextLine().replaceAll("\\s", "").toLowerCase();
                    String[] info;
                    if (robotInfo.contains(",")) {
                        info = robotInfo.split(",");
                        if (!robotTypes.contains(info[1])) {
                            System.out.println("Invalid robot type.\n");
                        } else {
                            Robot robot = new Robot(info[0], info[1]);
                            robots.add(robot);
                            System.out.println("Successfully created robot! Continue creating robots or enter q to quit.\n");
                        }
                    } else if (robotInfo.equals("q") || robotInfo.equals("Q")) {
                        robotCreate = false;
                        break;
                    } else {
                        System.out.println("Invalid input.");
                    }
                }
            } else if (input.equals("s") || input.equals("S")) {
                if (robots.size() == 0) {
                    System.out.println("Oops! You haven't created any robots.\n");
                } else {
                    System.out.println("Assigning tasks...");
                    try {
                        manager.run(robots);
                    } catch (InterruptedException e) {
                        System.out.println("Error running tasks...\n");
                        e.printStackTrace();
                    }
                }
            } else if (input.equals("l") || input.equals("L")) {
                manager.printLeaderboard();
            } else if (input.equals("e") || input.equals("E")) {
                System.out.println("Exiting program... See you next time!");
                running = false;
                System.exit(0);
            } else if (input.equals("v") || input.equals("V")) {
                if (robots.size() == 0) {
                    System.out.println("Oops! You haven't created any robots.\n");
                } else {
                    System.out.println("\nAvailble robots:");
                    for (int i = 0; i < robots.size(); i++) {
                        System.out.println(robots.get(i).name + ", " + robots.get(i).type);
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}

