import java.util.ArrayList;
import java.util.Random;

class JogoModel{

    static final int TOTAL_GAME_AREA = 20;

    private Celula[][] cellgrid = new Celula[TOTAL_GAME_AREA][TOTAL_GAME_AREA];
    private int currentScore = 0;
    //false means game is paused
    private boolean isPlaying = false;
    private int timeinterval = 1500;
    //this is a redundant variabele because it is SnakeCordinates.size()
    //but it is good to have this as a seperate variable
    private int snakeParts;
    //used Point as it is easy to store x and y cordinates than a 2D array
    private ArrayList<Ponto> SnakeCoordinates;
    private Ponto foodPosition;
    private Ponto obstaclePosition;
    private Arrow currentArrow = Arrow.RIGHT;
    private Arrow previousArrow = Arrow.LEFT;
    //boolean to see if user wants to reset game
    private boolean reset = false;

    public JogoModel(){
        for (Celula[] cellgrid1 : cellgrid) {
            for (int j = 0; j < cellgrid1.length; j++) {
                //cellgrid1[j] = new Celula();
            }
        }
        generateObstacle();
        changeFoodPosition();

        SnakeCoordinates = new ArrayList<>();

        updateSnakeParts();
    }

    /**
     * Comida Methods
     */
    //changes the Position of the fruit by randomly assigning cordinates
    public void changeFoodPosition(){
        int x, y;
        x = new Random().nextInt(TOTAL_GAME_AREA-1);
        y = new Random().nextInt(TOTAL_GAME_AREA-1);

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
        this.foodPosition = null;
    }

    public Ponto getFoodPosition(){
        return this.foodPosition;
    }

    /**
     * Cobra Methods
     */
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

    public boolean snakeCollides(){
        return SnakeCoordinates.get(0).getX() == obstaclePosition.getX() && SnakeCoordinates.get(0).getY() == obstaclePosition.getY();
    }
    public void snakeDies(){
        this.snakeParts = 0;
        SnakeCoordinates.removeAll(SnakeCoordinates);
        this.previousArrow = Arrow.LEFT;
        this.currentArrow = Arrow.RIGHT;
        generateObstacle();
        setScore(0);
    }

    public void snakeEats(){
        addNewSnakePart(SnakeCoordinates.get(snakeParts-1).getX(), SnakeCoordinates.get(snakeParts-1).getY());
        changeFoodPosition();
        incrementScore();
        updateSnakeParts();
    }

    /**
     * Arena Methods
     */

    public int getArenaWidth(){return cellgrid[0].length;}
    public int getArenaHeight(){return cellgrid.length;}
    public boolean detectCollision(){
        return SnakeCoordinates.get(0).getX() < 0 || SnakeCoordinates.get(0).getX() > TOTAL_GAME_AREA-1 ||SnakeCoordinates.get(0).getY() < 0 || SnakeCoordinates.get(0).getY() > TOTAL_GAME_AREA-1;
    }

    public void generateObstacle(){
        int x, y;
        x = new Random().nextInt(TOTAL_GAME_AREA-1);
        y = new Random().nextInt(TOTAL_GAME_AREA-1);

        //to prevent overlapping of snake's coordinates and the fruits position
        if(snakeParts>0){
            while (SnakeCoordinates.contains(new Ponto(x, y))) {
                x = new Random().nextInt(TOTAL_GAME_AREA-1);
                y = new Random().nextInt(TOTAL_GAME_AREA-1);
            }}

        this.obstaclePosition = new Ponto(x, y);
    }


    /**
     * Celula Methods
     */

    public Celula[][] getCellGrid(){
        return cellgrid;
    }

    /*
    public int getCellType(int x, int y){
        return cellgrid[x][y].getCellType();
    }

    void setCellType(int x, int y, int type){
        cellgrid[x][y].setCellType(y);
    }

     */

    /**
     * Misc methods
     */
    public void setReset(boolean reset){this.reset = reset;}

    public void setScore(int score){ this.currentScore = score;}
    public int getCurrentScore() {return currentScore;}

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

    public String randomizeArrow(){
        int x = new Random().nextInt(0, 4);
        String Arrows[] = {"LEFT", "RIGHT", "DOWN", "UP"};
        return Arrows[x];
    }

    public int incrementScore(){
        currentScore +=1;
        return currentScore;
    }


    public void setPlayingMode(boolean isPlaying) {this.isPlaying = isPlaying;}
    public boolean getPlayingMode() {return isPlaying;}

    public int getTimeInterval(){return timeinterval;}
    public void setTimeInterval(int time){this.timeinterval = time;}


    //reseting all the variables
    public void ResetGame(){
        snakeDies();
        setScore(0);
        //removeFood();
        this.previousArrow = Arrow.LEFT;
        this.currentArrow = Arrow.RIGHT;
    }

    public void NextStep(){
        Celula nextVersion[][] = new Celula[TOTAL_GAME_AREA][TOTAL_GAME_AREA];
        if (snakeParts < 4) {
            addNewSnakePart(new Random().nextInt(0, 20), new Random().nextInt(0, 20));
            updateSnakeParts();
            updateSnakePosition();
        }else{
            updateSnakePosition();
        }

        if(SnakeCoordinates.get(0).getX() == foodPosition.getX() && SnakeCoordinates.get(0).getY() == foodPosition.getY())
        {
            snakeEats();
        }

        if(snakeCollides() || detectCollision()){
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

    /*
        for (int y = 0; y < cellgrid.length; y++) {
            for (int x = 0; x < cellgrid[y].length; x++) {
                if (SnakeCoordinates.contains(new Ponto(x, y))) {
                    nextVersion[y][x] = new Celula(1);
                }
                else if (foodPosition.equals(new Ponto(x, y))) {
                    nextVersion[y][x] = new Celula(2);
                }
                else if(obstaclePosition.equals(new Ponto(x, y))){
                    nextVersion[y][x] = new Celula(3);
                }
                else {nextVersion[y][x] = new Celula(0);}
            }
        }

     */

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

}
//Arrow controls
enum Arrow {
    UP, DOWN, RIGHT, LEFT
}
