package src;

public class Task {
    private String description;
    private int eta;

    //constructor
    Task(String description, int eta) {
        this.description = description;
        this.eta = eta;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEta() {
        return eta;
    }

    public void setETA(int eta) {
        this.eta = eta;
    }
}
