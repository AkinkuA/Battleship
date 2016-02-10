import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class BattleShipStatisticsFileIO {

	private Scanner fileInput;
	private BufferedWriter fileWriter;
	private String name;
	private int score;
	private ArrayList<Object[]> highScores;
	private Object[] user;
	
	public BattleShipStatisticsFileIO() {
		highScores = new ArrayList<Object[]>(0);
		user = new Object[2];
		try {
			 BufferedWriter newFile = new BufferedWriter(new FileWriter("HighScores.txt"));
			 newFile.write("none 0");
			 newFile.newLine();
			 newFile.close();
		} catch (IOException e) {
			
		}
	}
	
	public int getUserHighScore(String userName) {
		try {
			fileInput = new Scanner(new BufferedReader(new FileReader("HighScores.txt")));
				while (fileInput.hasNext()) {
					name = fileInput.next();
					score = fileInput.nextInt();
					if (name.equals(userName)) {
						fileInput.close();
						return(score);
					}
				}
				return(-1);
		} catch (IOException e) {
			return(-2);
		}
	}
	
	public int getOverallHighScore() {
		try {
			fileInput = new Scanner(new BufferedReader(new FileReader("HighScores.txt")));
			name = fileInput.next();
			score = fileInput.nextInt();
			fileInput.close();
			if (name.equals("none") && score == 0) {
				return(-1);
			} else {
				return(score);
			}
		} catch (IOException e) {
			return (-2);
		}

	}
	
	public String getOverallUserName() {
		try {
			fileInput = new Scanner(new BufferedReader(new FileReader("HighScores.txt")));
			name = fileInput.next();
			score = fileInput.nextInt();
			fileInput.close();
			if (name.equals("none") && score == 0) {
				return("-1");
			} else {
				return(name);
			}
		} catch (IOException e) {
			return("-2");
		}

	}
	
	public int clearUser(String userName) {
		try {
			fileInput = new Scanner(new BufferedReader(new FileReader("HighScores.txt")));
			while (fileInput.hasNext()) {
				user = null;
				user[0] = fileInput.next();
				user[1] = new Integer(fileInput.nextInt());
				highScores.add(user);
			}
			fileInput.close();
			int result = -1;
			
			for (int i = 1; i < highScores.size(); i++) {
				user = highScores.get(i);
				if (user[0].toString().equals(userName)) {
					highScores.remove(i);
					result = 0;
					
					user = highScores.get(0);
					int highest = 0;
					int highPoint = 0;
					if (userName.equals( user[0].toString() )) {
						highScores.remove(0);
						for (int j = 0; j < highScores.size(); j++) {
							user = highScores.get(i);
							if (Integer.valueOf(user[1].toString()) > highest) {
								highPoint = j;
							}
						}
						if (highest == 0) {
							user[0] = "none";
							user[1] = 0;
							highScores.add(user);
						} else {
							user = highScores.get(highPoint);
							highScores.add(0, user);
						}
					}
					fileWriter = new BufferedWriter(new FileWriter("HighScores.txt"));
					
					for (int k = 0; k < highScores.size(); k++) {
						user = highScores.get(k);
						fileWriter.write(user[0].toString() + " ");
						fileWriter.write(user[1].toString());
						fileWriter.newLine();
					}
					fileInput.close();
				}
			}

			return(result);
		} catch (IOException e) {
			return(-2);
		}
	}
	
	public int writeUserHighScore(String userName, int userScore) {
		try {
			fileInput = new Scanner(new BufferedReader(new FileReader("HighScores.txt")));
			while (fileInput.hasNext()) {
				user = null;
				user[0] = fileInput.next();
				user[1] = new Integer(fileInput.nextInt());
				highScores.add(user);
			}
			fileInput.close();
			
			user = highScores.get(0);
			
			if (userScore > Integer.valueOf(user[1].toString())) {
				user[0] = userName;
				user[1] = userScore;
				highScores.remove(0);
				highScores.add(0, user);
			}
			
			highScores.add(user);
			
			fileWriter = new BufferedWriter(new FileWriter("HighScores.txt"));
				
			for (int i = 0; i < highScores.size(); i++) {
				user = highScores.get(i);
				fileWriter.write(user[0].toString() + " ");
				fileWriter.write(user[1].toString());
				fileWriter.newLine();
			}
			fileInput.close();
			return(0);
		} catch (IOException e) {
			return(-2);
		}
		
	}

}
