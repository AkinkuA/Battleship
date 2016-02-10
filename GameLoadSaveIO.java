import java.io.*;

import java.io.IOException;

public class GameLoadSaveIO {
	
	private int[] userHealth;
	private int[] compHealth;
	private int[][] userGrid;
	private int[][] compGrid;

	public int readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("saveFile.txt"));
			for (int i = 0; i < 7; i++) {
				userHealth[i] = reader.read();
				compHealth[i] = reader.read();
			}
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++){
					userGrid[row][col] = reader.read();
					compGrid[row][col] = reader.read();
				}
			}
			reader.close();
			return(0);
		} catch (IOException e) {
			return(-1);
		}
    }
		
	public int[] getUserHealth() {
		return (userHealth);
	}
	
	public int[] getCompHealth() {
		return (compHealth);
	}
	
	public int[][] getUserGrid() {
		return (userGrid);
	}
	
	public int[][] getCompGrid() {
		return (compGrid);
	}
    
   	public int saveGame() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("saveFile.txt"));
			for (int i = 0; i < 7; i++) {
				writer.write(userHealth[i] + " ");
				writer.write(compHealth[i] + " ");
			}
			for(int row = 0; row < 10; row++) {
				for(int col = 0; col < 10; col++){
					writer.write(userGrid[row][col] + " ");
					writer.write(compGrid[row][col] + " ");
				}
			}
			writer.close();
			return (0);
   		} catch (IOException e) {
   			return (-1);
		}
	}

}
