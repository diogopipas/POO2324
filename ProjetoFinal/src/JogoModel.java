import java.util.ArrayList;
import java.util.Random;

class JogoModel {

    static final int TOTAL_GAME_AREA = 20;

    private Celula[][] cellgrid = new Celula[TOTAL_GAME_AREA][TOTAL_GAME_AREA];
    private int currentScore = 0;
    private int currentLive = 3;
    //false means game is paused
    private boolean isPlaying = false;
    private int timeinterval = 1500;
    //this is a redundant variabele because it is SnakeCordinates.size()
    //but it is good to have this as a seperate variable
    private int snakeParts;
    //used Point as it is easy to store x and y cordinates than a 2D array
    private ArrayList<Ponto> SnakeCoordinates;
    private Ponto foodPosition;
    private Arrow currentArrow = Arrow.RIGHT;
    private Arrow previousArrow = Arrow.LEFT;
    //boolean to see if user wants to reset game
    private boolean reset = false;

    public JogoModel(){
        for (Celula[] cellgrid1 : cellgrid) {
            for (int j = 0; j < cellgrid1.length; j++) {
                cellgrid1[j] = new Celula();
            }
        }

        changeFoodPosition();

        SnakeCoordinates = new ArrayList<>();

        updateSnakeParts();
    }

    //changes the Position of the fruit by randomly assigning cordinates
    public void changeFoodPosition(){
        int x, y;
        x = new Random().nextInt(TOTAL_GAME_AREA-1);
        y = new Random().nextInt(TOTAL_GAME_AREA-1);
        System.out.println(x);
        System.out.println(y);

        //to prevent overlapping of snake's coordinates and the fruits position
        if(snakeParts>0){
            while (SnakeCoordinates.contains(new Ponto(x, y))) {
                x = new Random().nextInt(TOTAL_GAME_AREA-1);
                y = new Random().nextInt(TOTAL_GAME_AREA-1);
            }}

        this.foodPosition = new Ponto(x, y);
    }

    //remove Fruit from the grid
    public void removeFood(){
        //technically supposed to be eaqual to null but that won't work in my code
        //assigned negative coordinates
        this.foodPosition = new Ponto(-1, -1);
    }

    public void updateSnakeParts(){
        this.snakeParts = SnakeCoordinates.size();
    }

    //add new snakePart, x and y are the cordinates of the new part
    public void addNewSnakePart(double x, double y){
        this.SnakeCoordinates.add(new Ponto(x, y));
    }

    //alter the coordinates of the snake parts at the necessary index
    public void alterSnakeCoordinates(int position, double newX, double newY){
        this.SnakeCoordinates.remove(position);
        this.SnakeCoordinates.add(position, new Ponto(newX, newY));
    }

    public Celula[][] getCellGrid(){
        return cellgrid;
    }

    public int getCellType(int x, int y){
        return cellgrid[x][y].getCellType();
    }

    void setCellType(int x, int y, int type){
        cellgrid[x][y].setCellType(y);
    }

    public void setReset(boolean reset){this.reset = reset;}

    public void setScore(int score){ this.currentScore = score;}
    public int getCurrentScore() {return currentScore;}

    public void setLive(int lives){ this.currentLive = lives;  }
    public int getCurrentLive() {return currentLive; }

    public void setArrowKey(String key){
        //other part of the if check to make sure snake only moves forward and not backward

        if (key.toLowerCase().equals("up") && !previousArrow.equals(Arrow.DOWN)) {
            this.currentArrow = Arrow.UP;
        }
        else if (key.toLowerCase().equals("down") && !previousArrow.equals(Arrow.UP)) {
            this.currentArrow = Arrow.DOWN;
        }
        else if (key.toLowerCase().equals("right") && !previousArrow.equals(Arrow.LEFT)){
            this.currentArrow = Arrow.RIGHT;
        }
        else if (key.toLowerCase().equals("left") && !previousArrow.equals(Arrow.RIGHT)){
            this.currentArrow = Arrow.LEFT;
        }
    }
    //gets the user input
    public String getArrowKey(){
        return this.currentArrow.toString();
    }

    public int incrementScore(){
        currentScore +=1;
        return currentScore;
    }

    public int decrementLive(){
        currentLive -=1;
        return currentLive;
    }

    public int getGridHeight(){return cellgrid.length;}
    public int getGridWidth(){return cellgrid[0].length;}

    public void setPlayingMode(boolean isPlaying) {this.isPlaying = isPlaying;}
    public boolean getPlayingMode() {return isPlaying;}

    public int getTimeInterval(){return timeinterval;}
    public void setTimeInterval(int time){this.timeinterval = time;}

    public void snakeDies(){
        //lives is decremeted and snakecordinates are reset to intial
        decrementLive();
        this.snakeParts = 0;
        SnakeCoordinates.removeAll(SnakeCoordinates);
        this.previousArrow = Arrow.LEFT;
        this.currentArrow = Arrow.RIGHT;
    }

    //reseting all the variables
    public void ResetGame(){
        snakeDies();
        setScore(0);
        setLive(3);
        removeFood();
        this.previousArrow = Arrow.LEFT;
        this.currentArrow = Arrow.RIGHT;
    }

    public void NextStep(){
        Celula nextVersion[][] = new Celula[TOTAL_GAME_AREA][TOTAL_GAME_AREA];

        if(reset){
            ResetGame();
        }

        else{
            if (snakeParts == 0) {
                addNewSnakePart(0, 2);
                updateSnakeParts();
            }
            else if (snakeParts < 4) {
                addNewSnakePart(0, 2);
                updateSnakeParts();
                updateSnakePosition();

            }
            else{updateSnakePosition();}

            if(SnakeCoordinates.get(0).getX() == foodPosition.getX() && SnakeCoordinates.get(0).getY() == foodPosition.getY())
            {
                eat();
            }

            //if snake touches the boundary
            if(SnakeCoordinates.get(0).getX() < 0 || SnakeCoordinates.get(0).getX() > TOTAL_GAME_AREA-1 ||SnakeCoordinates.get(0).getY() <0 ||SnakeCoordinates.get(0).getY() > TOTAL_GAME_AREA-1)
            {
                snakeDies();
                isPlaying = false;
            }

            //if snake eats itself
            for (int i = 1; i < SnakeCoordinates.size()-1; i++) {
                if (SnakeCoordinates.get(0).equals(SnakeCoordinates.get(i))) {
                    snakeDies();
                    isPlaying = false;
                    break;
                }
            }
        }

        for (int y = 0; y < cellgrid.length; y++) {
            for (int x = 0; x < cellgrid[y].length; x++) {
                if (SnakeCoordinates.contains(new Ponto(x, y))) {
                    nextVersion[y][x] = new Celula(1);
                }
                else if (foodPosition.equals(new Ponto(x, y))) {
                    nextVersion[y][x] = new Celula(2);
                }
                else {nextVersion[y][x] = new Celula(0);}
            }
        }

        cellgrid = nextVersion;
    }

    public void updateSnakePosition(){

        for (int i = snakeParts-1; i > 0; i--) {
            alterSnakeCoordinates(i, SnakeCoordinates.get(i-1).getX(), SnakeCoordinates.get(i-1).getY());
        }
        switch (currentArrow) {
            case DOWN:
                alterSnakeCoordinates(0, SnakeCoordinates.get(0).getX(), (SnakeCoordinates.get(0).getY())+1);
                break;
            case UP:
                alterSnakeCoordinates(0, SnakeCoordinates.get(0).getX(), (SnakeCoordinates.get(0).getY())-1);
                break;
            case RIGHT:
                alterSnakeCoordinates(0, (SnakeCoordinates.get(0).getX())+1, (SnakeCoordinates.get(0).getY()));
                break;
            case LEFT:
                alterSnakeCoordinates(0, (SnakeCoordinates.get(0).getX())-1, (SnakeCoordinates.get(0).getY()));
                break;
            default:
                break;
        }

        previousArrow = currentArrow;
//        System.out.println(snakeParts);
//        System.out.println(currentArrow.toString());
//        for (int i = 0; i < SnakeCordinates.size(); i++) {
//            System.out.println(SnakeCordinates.get(i).x +" "+SnakeCordinates.get(i).y+" ,");
//        }

    }

    public void eat(){
        addNewSnakePart(SnakeCoordinates.get(snakeParts-1).getX(), SnakeCoordinates.get(snakeParts-1).getY());
        changeFoodPosition();
        incrementScore();
        updateSnakeParts();
    }

}
//Arrow controls
enum Arrow {
    UP, DOWN, RIGHT, LEFT
}
