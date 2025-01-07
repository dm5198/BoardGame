package hw2.com;

public class Player {
	
	String name;
    int score;
    int position;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public void resetScore() {
        score = 0;
    }

    public void addToScore(int points) {
        score += points;
    }


}
