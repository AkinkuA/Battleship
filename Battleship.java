import java.lang.Math;
import java.util.Scanner;
import java.util.Random;

public class Battleship {
  // field
  boolean gameMode = false;
  boolean setMode = false;
  int grid[][] = new int[10][10];
  int gridComp[][] = new int[10][10];
  int health[] = new int[7];
  int healthComp[] = new int[7];
  // ship data
  final int CARRIER = 0;
  final int BATTLESHIP = 1;
  final int CRUISER = 2;
  final int DESTROYER = 3;
  final int DESTROYER_2 = 4;
  final int SUBMARINE = 5;
  final int SUBMARINE_2 = 6;
  // ship lengths
  final int CARRIER_LENGTH = 5;
  final int BATTLESHIP_LENGTH = 4;
  final int CRUISER_LENGTH = 3;
  final int DESTROYER_LENGTH = 2;
  final int SUBMARINE_LENGTH = 1;
  
  // constructor
  Battleship() {
	  health[0] = 0;
	  health[1] = 0;
	  health[2] = 0;
	  health[3] = 0;
	  health[4] = 0;
	  health[5] = 0;
	  health[6] = 0;
	  healthComp[0] = 5;
	  healthComp[1] = 4;
	  healthComp[2] = 3;
	  healthComp[3] = 2;
	  healthComp[4] = 2;
	  healthComp[5] = 1;
	  healthComp[6] = 1;
  }  // end of Battleship
  
  // AMIR
  
  // check for game mode
  public boolean getGameMode() {
    return gameMode;
  }  // end of gameMode

  // set the game mode
  public void setGameModeOn() {
    gameMode = true;
  }  // end of set mode on
  
  // set game mode off
  public void setGameModeOff() {
    gameMode = false;
  }  // end of setModeOff
  
  // check for set mode
  public boolean getSetMode() {
    return setMode;
  }

  // set set mode off
  public void setSetModeOn() {
    setMode = true;
  }
  
  // set set mode on
  public void setSetModeOff() {
    setMode = false;
  }
  
  // set a ship for player 
  public void setPlayerShip(int x, int y, int shipType) {
    if(grid[x][y] == 7) {
      grid[x][y] = shipType;
    } else {
      // space not available
    }
  }  // end of setPlayerShip
  
  // default grid
  public void setGrid() {
    for(int i = 0; i < 10; i++) {
	    for(int j = 0; j < 10; j++) {
	      grid[i][j] = 7;
		    gridComp[i][j] = 7;
      }
	  }
  }  // end of setGrid

  // setting the pieces for computer
  public void setCompShips() {
    createAndPlaceAShip(SUBMARINE_2, SUBMARINE_LENGTH, 1);
    createAndPlaceAShip(SUBMARINE, SUBMARINE_LENGTH, 1);
    createAndPlaceAShip(DESTROYER_2, DESTROYER_LENGTH, 1);
    createAndPlaceAShip(DESTROYER, DESTROYER_LENGTH, 1);
    createAndPlaceAShip(CRUISER, CRUISER_LENGTH, 1);
    createAndPlaceAShip(BATTLESHIP, BATTLESHIP_LENGTH, 1);
    createAndPlaceAShip(CARRIER, CARRIER_LENGTH, 1);
  }  // end of setCompShips
  
  // check the grid for if space is available for ship placement
  public boolean checkInGrid(int x, int y, int length, int direction) {  // [1] North, [2] East, [3] South, [4] West
    for(int i = 0; i < length; i++) {
      if(direction == 1) {  // North
        if((x - i) < 0) {
          return false;
        } else if(gridComp[x - i][y] == 7) {  // x - i going up the row
          // Pass
        } else {
          return false;
        }
      } else if(direction == 2) {  // East
		    if((y + i) > 9) {
		      return false;
		    } else if(gridComp[x][y + i] == 7) {  // y + i going right through the column
		      // Pass
		    } else {
		      return false;
		    }
      } else if(direction == 3) {  // South
		    if((x + i) > 9) {
		      return false;
		    } else if(gridComp[x + i][y] == 7) {  // x + i going down the row
		      // Pass
		    } else {
		      return false;
		    }
      } else if(direction == 4) {  // West
		    if((y - i) < 0) {
		      return false;
		    } else if(gridComp[x][y - i] == 7) {  // y - i going left through the column
		      // Pass
		    } else {
		      return false;
		    }
      } else {
        return false;  // invalid direction!
      }
    }
	  return true;
  }  // end of checkInGrid
  
  // called by createAndPlaceShip to actually put them
  public void setShipInGrid(int x, int y, int shipType, int length, int direction) {
	  if(direction == 1) {  // North
	    for(int i = 0; i < length; i++) {
	      gridComp[x - i][y] = shipType;
	    }
	  } else if(direction == 2) {  // East
	    for(int i = 0; i < length; i++) {
	      gridComp[x][y + i] = shipType;
	    }
	  } else if(direction == 3) {  // South
	    for(int i = 0; i < length; i++) {
	      gridComp[x + i][y] = shipType;
	    }
	  } else if(direction == 4) {  // West
	    for(int i = 0; i < length; i++) {
	      gridComp[x][y - i] = shipType;
	    }
	  } else {
	    // Invalid direction!
	  }
	}  // end of setShipInGrid
  
  // create and place ships
  public void createAndPlaceAShip(int shipType, int length, int quantity) {
	  int x = randomInt(9);
	  int y = randomInt(9);
	  int direction = randomInt(3) + 1;
	  for(int i = 0; i < quantity; i++) {
		  while(checkInGrid(x, y, length, direction) != true) {
		    x = randomInt(9);
		    y = randomInt(9);
		    direction = randomInt(3) + 1;
		  }
	    setShipInGrid(x, y, shipType, length, direction);
	  }
  }  // end of createAndPlaceAShip
  
  // random integer generator between 0 to x
  public int randomInt(int in) {
    Double r = Math.random();
    Double x = r*in;
    int out = x.intValue();
    return out;
  }  // end of randomInt
  
  // KARTIK
  
  // check the x, y for valid input. compare to computer's grid
  public boolean checkMove(int x, int y) {
    if(((x <= 9) && (x >= 0) && (y >= 0) && (y <= 9))) {
	    if(gridComp[x][y] >= 8) {
	      System.out.println("Invalid input");
	      return false;
	    } else {
	      return true;
      }
    } else {
      System.out.println("Invalid input");
      return false;
    }
  }  // end of checkMove
  
  // check the x, y for valid input. compare to suer's grid
  public boolean checkPlacement(int x, int y) {
    if(grid[x][y] == 7) {
      return true;
    } else if(grid[x][y] <= 6) {
      return false;
    } else {
      return false;
    }
}  // end of checkPlacement
  
  // player turn handle
	public void playerTurn(int x, int y) {
			switch(gridComp[x][y]) {
				case 7:
					gridComp[x][y] = 8;
					break;
				case 6:
				  healthComp[6] =  healthComp[6] - 1;
				  gridComp[x][y] = 9;
				  break;
				case 5:
				  healthComp[5] =  healthComp[5] - 1;
					gridComp[x][y] = 9;
					break;
				case 4:
				  healthComp[4] =  healthComp[4] - 1;
					gridComp[x][y] = 9;
					break;
				case 3:
				  healthComp[3] =  healthComp[3] - 1;
					gridComp[x][y] = 9;
					break;
				case 2:
				  healthComp[2] =  healthComp[2] - 1;
					gridComp[x][y] = 9;
					break;
				case 1:
				  healthComp[1] =  healthComp[1] - 1;
					gridComp[x][y] = 9;
					break;
				case 0:
				  healthComp[0] =  healthComp[0] - 1;
					gridComp[x][y] = 9;
					break;
				default:
				  break;
	  }
	}  // end of playerTurn
	
	// computer turn handle
	public void compTurn() {
		Random r = new Random();
		int x;
		int y;
		boolean move = true;
		while(move)
	  {
		  x = r.nextInt(10);
		  y = r.nextInt(10);
			if(grid[x][y] >= 0 && grid[x][y] <= 7)
		  { 
				switch(grid[x][y]) {
					case 7:
					  grid[x][y] = 8;
					  move = false;
					  break;
					case 6:
					  health[6] =  health[6] - 1;
					  grid[x][y] = 9;
					  move = false;
					  break;
					case 5:
					  health[5] =  health[5] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					case 4:
					  health[4] =  health[4] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					case 3:
					  health[3] =  health[3] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					case 2:
					  health[2] =  health[2] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					case 1:
					  health[1] =  health[1] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					case 0:
					  health[0] =  health[0] - 1;
						grid[x][y] = 9;
						move = false;
						break;
					default:
					  move = false;
					  break;
			  }
	    }
			else
		  {
			  System.out.println("move already done. move again");
		  }
	  }
  }  // end of compTurn
  
	// check for win and lose. [return]  0 = no win yet, 1 = player win, 2 = computer
	public int getGameResult() {
	  int totalHealthPlayer = 0;
	  int totalHealthComp = 0;
	  for(int i = 0; i < 7; i++) {
	  	totalHealthPlayer = totalHealthPlayer + health[i];
	  	totalHealthComp = totalHealthComp + healthComp[i];
	  }
	  if(totalHealthComp == 0) {
	    return 1;
	  } else if(totalHealthPlayer == 0) {
	    return 2;
	  } else {
	  	return 0;
	  }
	}
	
  // main
  public static void main(String[] args) {
	  Battleship battle1 = new Battleship();
	  battle1.setGrid();
	  battle1.setCompShips();
    for(int i = 0; i < 10; i++) {
      for(int j = 0; j < 10; j++) {
        if(battle1.gridComp[i][j] <= 6) {
          System.out.print(battle1.gridComp[i][j]);
        } else if(battle1.gridComp[i][j] == 7) {
          System.out.print("-");
        } else {
          System.out.print("x");
        }
      }
      System.out.println("");
    }
    // game start
    int xUser; int yUser;
    while(true) {
      Scanner in = new Scanner(System.in);
      System.out.print("[Player] X: ");
      xUser = in.nextInt();
      System.out.print("[Player] Y: ");
      yUser = in.nextInt();
      if(battle1.checkMove(xUser, yUser)) {
        battle1.playerTurn(xUser, yUser);
        battle1.compTurn();
        for(int i = 0; i < 10; i++) {
          for(int j = 0; j < 10; j++) {
            if(battle1.gridComp[i][j] <= 6) {
              System.out.print(battle1.gridComp[i][j]);
            } else if(battle1.gridComp[i][j] == 7) {
              System.out.print("-");
            } else {
              System.out.print("x");
            }
          }
          System.out.println("");
        }
      }
      // print ship health
      for(int i = 0; i < 7; i++) {
        System.out.print(battle1.health[i] + "-");
      }
      System.out.println("");
      if(battle1.getGameResult() == 1) {
        System.out.println("You win!");
      } else if(battle1.getGameResult() == 2) {
        System.out.println("Computer win");
      } else {
        System.out.println("Play on");
      }
    }
  }  // end of main
  
}  // end of line