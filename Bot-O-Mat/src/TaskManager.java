package src;

import java.util.*;

public class TaskManager {

    private static List<Task> tasks;
    private static HashMap<String, Integer> leaderboard;
    private static HashMap<String, List<String>> tasksCompleted;
    private static boolean hasRanOnce = false;

    public void initalizeList() {
        tasks = new ArrayList<>();
        tasks.add(new Task("do the dishes", 1000));
        tasks.add(new Task("sweep the house", 3000));
        tasks.add(new Task("do the laundry", 10000));
        tasks.add(new Task("take out the recycling", 4000));
        tasks.add(new Task("make a smmich", 7000));
        tasks.add(new Task("mow the lawn", 7000));
        tasks.add(new Task("rake the leaves", 18000));
        tasks.add(new Task("give the dog a bath", 14500));
        tasks.add(new Task("bake some cookies", 8000));
        tasks.add(new Task("wash the car", 1000));
    }

    public List<Task> selectFive() {
        Random rand = new Random();
        List<Task> toDo = new ArrayList<>();
		//randomly assign five tasks
		for(int i = 0; i < 5; i++) {
			Task temp = tasks.get(rand.nextInt(tasks.size()));
			if (!toDo.contains(temp)) {
				toDo.add(temp);
			} else {
                i -= 1;
            }
		}
        return toDo;
    }

    public void run(List<Robot> robots) throws InterruptedException {
        initalizeList();
        leaderboard = new HashMap<>();
        tasksCompleted = new HashMap<>();

        for (Robot robot : robots) {
            List<Task> selected = selectFive();
            List<String> tasks = new ArrayList<>();
            int timeTaken = 0;
            for (Task t : selected) {
                System.out.println(robot.getName() + " is completing the following task: " + t.getDescription() + "...\n");
                Thread.sleep(t.getEta());
                timeTaken += t.getEta();
                if (tasksCompleted.get(robot.getName()) == null) {
                    tasks.add(t.getDescription());
                } else if (tasksCompleted.get(robot.getName()).contains(t.getDescription())) {
                    continue;
                } else {
                    tasks.add(t.getDescription());
                }
                System.out.println("Task complete!\n");
            }
            leaderboard.put(robot.getName(), timeTaken);
            tasksCompleted.put(robot.getName(), tasks);

        }
        hasRanOnce = true;
    }

    public void printLeaderboard() {
        if (hasRanOnce == false) {
            System.out.println("You need to begin tasks first!\n");
        } else {
            System.out.println("Fastest times to complete 5 tasks:\n");
            HashMap<String, Integer> currentBoard = sortByScore(leaderboard);
            for (Map.Entry<String, Integer> stat : currentBoard.entrySet()) {
                System.out.println("Name: " + stat.getKey() + ", Score: " + stat.getValue() + "\n");
            }
            System.out.println("Tasks completed by each Robot: \n");
            for (Map.Entry<String, List<String>> t : tasksCompleted.entrySet()) {
                System.out.println(t.getKey() + ":\n");
                for (String s : t.getValue()) {
                    System.out.println(s);
                }
                System.out.println("\n");
            }
        }
    }

    public HashMap<String, Integer> sortByScore(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> list =
               new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }

        return temp;
    }



}
