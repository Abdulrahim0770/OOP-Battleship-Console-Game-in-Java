import java.util.Scanner;
import java.util.Random;

/**
 *AbdulRahim Ibrahim, 40025194
//Comp 249 
//Assignment #1
// Saturday,4th February 2017
 *
 */

public class Game {
	
	private static String[][] board; 

	
	
public static void createboard() { //Create an 8x8 board with all positions stored as -
		board = new String[8][8];
		for(int i=0 ; i < 8 ; i++ ) {
            for(int j=0 ; j < 8 ; j++ ) {
                board[i][j]= "-";
            }
		}
	}
	
public static void displayboard() {// display the created board
		System.out.println("12345678");
		
		for(int i=0 ; i < 8 ; i++ ) {
			for(int j=0 ; j < 8 ; j++ ) {
				System.out.print(board[i][j]);
				if (j == 7) {
					System.out.println();
				}
			}
		}
	}
	
public static boolean isValidTile(int column, int row, String[][] board) {		
		// Check if within board bounds
		if (column >= 0 && column <= 7) {
			if (row >= 0 && row <= 7) {
				return true;
			}
		}
		System.out.println("Tile out of bounds!");
		return false;
	}
	
public static boolean isFreeTile(int column, int row, String[][] board) {
		// Tile is empty
		if (board[column][row] == "-")
			return true;
		else
			System.out.println("Position already taken/called");
			return false;
	}
	
public static void storeTile(int coloumn, int row, String[][] board, int inputNumber ) {
		
		// Tile coordinate is valid, store it!
		
		if (inputNumber < 6)
				board[row][coloumn] = "s";		
		else 
			board[row][coloumn] = "g";	
	}

public static void storeTileComp(int coloumn, int row, String[][] board, int inputNumber ) {
	
	// Tile coordinate is valid, store it!
	
	if (inputNumber < 6 && board[row][coloumn] != "s" && board[row][coloumn] != "g" ) {
			board[row][coloumn] = "S";		
	}
	if (inputNumber >= 6 && board[row][coloumn] != "s" && board[row][coloumn] != "g" && board[row][coloumn] != "S") {
		board[row][coloumn] = "G";	
	}
}

	
public static void initializeuser() {// for the next 10 user inputs, 6 of them will be ships and 4 will be grenades and will be stored within the board
		System.out.println("Give me a column letter and a row number (e.g B2)");
		
		String coordinate = null;
		int coloumn = 0;
		int row = 0;
		
		Scanner mykeyboard = new Scanner (System.in);
		
		for (int i=0; i<10; i++) {
			do {
			
			if (i < 6) 
				
				System.out.println("Enter Coordinate of Ship " + (i + 1));
			
			else 
				System.out.println("Enter Coordinate of Grenade " + (i - 6));
				
			coordinate = mykeyboard.next();
			coordinate = coordinate.toLowerCase();
			coloumn = (int)coordinate.charAt(0) - 97;//ascii values 1
			row = (int)coordinate.charAt(1) - 49;//ascii values A
			
			} while(!(isValidTile(coloumn, row, board) && isFreeTile(coloumn, row, board)));
			
			storeTile(coloumn, row, board, i);
		}
	}
		
public static void initializecomputer() {//generate random positions for computer
		Random rand = new Random();
		for (int i=0; i<10; i++) {
			String coordinate = null;
			int column = 0;
			int row = 0;
			
			do {
				
				if (i < 6) { 
				column = rand.nextInt(8);
				row = rand.nextInt(8);
				coordinate = column + "" + row;
				i++;
				
				if (board[row][column] == "s" ) {//Generate a different position if its already taken by a ship/grenade or its own ship
					i--;
					column = rand.nextInt(8);
					row = rand.nextInt(8);
					coordinate = column + "" + row;
				}
				if(board[row][column] == "g") {
					i--;
					column = rand.nextInt(8);
					row = rand.nextInt(8);
					coordinate = column + "" + row;
					
				}
				
				if(board[row][column] == "S") {
					i--;
					column = rand.nextInt(8);
					row = rand.nextInt(8);
					coordinate = column + "" + row;
					
				}}
				
				if(i >= 6) {
					if (board[row][column] == "s" ) {
						i--;
						column = rand.nextInt(8);
						row = rand.nextInt(8);
						coordinate = column + "" + row;
						i++;
					}
					if(board[row][column] == "g") {
						i--;
						column = rand.nextInt(8);
						row = rand.nextInt(8);
						coordinate = column + "" + row;
						i++;
					}
					
					if(board[row][column] == "S") {
						i--;
						column = rand.nextInt(8);
						row = rand.nextInt(8);
						coordinate = column + "" + row;
						i++;
					}
					
					if(board[row][column] == "G") {
						i--;
						column = rand.nextInt(8);
						row = rand.nextInt(8);
						coordinate = column + "" + row;
						i++;
					}	
				}
						
			}while(!isValidTile(column, row, board));
		
			// Stores the tile into the battleBoard
			storeTileComp(column, row, board, i);
		}
	}
	
	
	// Returns true if hit a grenade!
public static boolean UserhitGrenade(int col, int row, String[][] board) {
		if (board[col][row] == "G")
			return true;
		else 
			return false;
	}
	
	
	

public static void main(String[] args) {
		
		boolean userpenalty;
		boolean comppenalty;
		int turn = 1;
		boolean victory = false; 
		String coordinate = null;
		int col = 0;
		int row = 0;
		Random rand = new Random();
		int userships = 6;
		int compships = 6;
		
		createboard();
		
		initializeuser();
		
		initializecomputer();
		
		
		while (victory) {
			
			if (turn % 2 == 0) {
				do {
					Scanner mykeyboard = new Scanner(System.in);
					System.out.println("Hey User, Enter Coordinate to Shoot : ");
					coordinate = mykeyboard.next();
					
					// Convert to lowercase, and save our Column and row
					coordinate = coordinate.toLowerCase();
					col = (int)coordinate.charAt(0) - 97;
					row = (int)coordinate.charAt(1) - 49;
				
				} while (!isValidTile(col, row, board));
				
				displayboard();
			
				if (board[row][col] == "S") {
					board[row][col] = "HIT";
					compships --;
				}
				
				if(board[row][col] == "-") {
					board[row][col] = "*";
				}
				
				if (board[row][col] == "G") {
					userpenalty = true;
				}
				
				if (comppenalty = true) {
					comppenalty = false;
					turn --;
				}
				
				if (compships == 0) {
					victory = true;
					System.out.println("you win, close");
					System.out.println(0);
				}
			}
			else 
				do {
					col = rand.nextInt(8);
					row = rand.nextInt(8);
					
					if (board[row][col] == "s") {
						board[row][col] = "HIT";
						compships --;
					}
					
					if(board[row][col] == "-") {
						board[row][col] = "*";
					}
					
					if (board[row][col] == "g") {
						comppenalty = true;
					}
					
					if(userpenalty = true) {
						userpenalty = false;
						turn --;
					}
					
					if (userships == 0) {
						victory = true;
						System.out.println("Computer win, close");
						System.out.println(0);
					}
					
				}while (!isValidTile(col, row, board));
										
			turn ++;
				

		
}
}
}

