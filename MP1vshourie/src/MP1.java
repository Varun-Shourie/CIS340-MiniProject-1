// MP1, Varun Shourie, CIS340, Tuesday/Thursday, 3:00PM-4:15PM

import java.util.Scanner;

public class MP1 {
	// Instantiates an object of Scanner to take in user input.
	static Scanner scanner = new Scanner(System.in);
	
	// Inserts a blank line.
	private static void insertBlankLine() {
		System.out.println();
	}
	
	// Inserts six blank lines.
	private static void insertSixBlankLines() {
		// For every iteration of the loop, a blank space is printed.
		for(int i = 0; i < 6; i++)
			System.out.println();
	}
	
	// Inserts four blank lines. 
	private static void insertFourBlankLines() {
		// For every iteration of the loop, a blank space is printed.
		for(int i = 0; i < 4; i++) 
			System.out.println();
	}
	
	// Asks the user for the number of scores they would like to provide.
	private static int askForNumberOfScores() {
		// Prompts the user for number of scores.
		System.out.print("How many scores per student? ");
		
		// Returns the number of scores to a different part of the program.
		return Integer.parseInt(scanner.nextLine());
	}
	
	// Allocates the number of scores desired by the user into the inner array of studentScores.
	private static void setNumberOfScores(double[][] studentScores, int numberOfScores) {
		// For every iteration of the for loop, the desired number of scores are allocated for each student.
		for(int i = 0; i < studentScores.length; i++) {
			studentScores[i] = new double[numberOfScores];
		}
	}
	
	// Takes in user input regarding the student's name.
	private static String inputStudentName(int studentNumber) {
		// Prompts for the student's name & returns it to another part of the program.
		System.out.printf("Enter name for student %d: ", studentNumber);
		return scanner.nextLine();
	}
	
	// Takes in user input to store a student's score.
	private static double inputScore(int quizNumber) {
		// Prompts the user to enter a score for the student and returns the score.
		System.out.printf("Quiz %d: ", quizNumber);
		return Double.parseDouble(scanner.nextLine());
	}
	
	// Prompts for student names & scores from the user and stores them in arrays.
	private static void gatherStudentInformation(double[][] studentScores, String[] names) {
		// Outer for loop loops through every student. 
		for(int i = 0; i < studentScores.length; i++) {
			// Allows the user to input names for every student. i is incremented by one 
			// so the prompts become meaningful to the user.
			names[i] = inputStudentName(i+1);
			insertBlankLine();
			
			// Prompts the user to enter scores for every student.
			System.out.printf("Entering scores for %s\n", names[i].toUpperCase());
			
			// Each iteration of the inner for loop allows the user to input every student's score.
			for(int j = 0; j < studentScores[i].length; j++) {
				// j is incremented by one so prompts become meaningful to the user.
				studentScores[i][j] = inputScore(j+1);
			}
			insertBlankLine();
		}
	}
	
	// Calculates and returns the class average for all quizzes.
	private static double calculateClassAverage(double[][] studentScores) {
		// Variables for totaling class scores and averaging them to determine the class average.
		double classScoresTotal = 0.0;
		int classScoresCount = 0;
		
		// Traverses through every student's scores, totaling them and adding up 
		// the total count of scores.
		for(int i = 0; i < studentScores.length; i++) {
			for(int j = 0; j < studentScores[i].length; j++) {
				classScoresTotal += studentScores[i][j];
				classScoresCount++;
			}
		}
		
		// The average of the class scores is equal to the total of all class scores divided
		// by the total number of class scores.
		return classScoresTotal / classScoresCount;
	}
	
	// Prints the class average for all quizzes.
	private static void displayClassAverage(double[][] studentScores) {
		System.out.printf("Class average for all quizzes is %.2f", 
				calculateClassAverage(studentScores));
	}
	
	// Checks whether if a student's inputted student name matches with a student in the class.
	private static int calculateStudentPosition(String studentName, String[] names) {
		
		// Every iteration of the loop checks whether if the name corresponds with any student.
		// If there's a match, the student position in the array is validated and returned. 
		for(int i = 0; i < names.length; i++) {
			if(studentName.equalsIgnoreCase(names[i])) {
				return i;
			}
		}
		
		// If there is not a match, the student name is rejected and returned.
		return -1;
	}
	
	// Calculates and returns the validated student's average score.
	private static double calculateStudentAverage(int studentPosition, String[] names, double[][] studentScores) {
		// Measures the total of a student's quiz scores.
		double studentQuizTotal = 0.0;
		int studentQuizCount = 0;
		
		// Using the student position, we total up all the scores and count them in every iteration of the loop
		// to calculate the average. 
		for(int i = 0; i < studentScores[studentPosition].length; i++) {
			studentQuizTotal += studentScores[studentPosition][i];
			studentQuizCount++;
		}
		
		// Calculates and returns the average, which is the total of all of the student's quiz scores
		// divided by the number of quizzes the student has taken.
		return studentQuizTotal / studentQuizCount;
	}
	
	// Prints out all of an individual student's scores.
	private static void displayStudentScores(String[] names, int studentPosition, double[][] studentScores) {
		// Prints out the name of the validated student.
		System.out.printf("%s's scores are: ", names[studentPosition]);
		// Each iteration of the loop prints out the specific student's scores.
		for(int i = 0; i < studentScores[studentPosition].length; i++) {
			System.out.printf("%.2f ", studentScores[studentPosition][i]);
		}
	}
	
	// Displays a student's scores on all of the quizzes and their average.
	private static void displayStudentAverage(String[] names, double[][] studentScores) {
		// Meant to store the name and position in the array for the student whose scores the user wants to see.
		String studentName = "";
		int studentPosition = 0;
		
		// Prompts the user for the student's name.
		System.out.println("Calculating average by student.\n");
		System.out.print("Enter student name: ");
		studentName = scanner.nextLine();
		
		// Calculates the student's position on the names/studentScores array.
		studentPosition = calculateStudentPosition(studentName,names);
		
		// If the student position cannot be found, an error message is printed. If found, the program displays
		// all the student's scores and displays their average on all of the quizzes.
		if(studentPosition == -1) 
			System.out.print("Student not found.");
		else {
			displayStudentScores(names, studentPosition, studentScores);
			insertBlankLine();
			System.out.printf("%s's average is %.2f",names[studentPosition],
					calculateStudentAverage(studentPosition, names, studentScores));
		}
	}
	
	// Calculates the average class performance for a specific quiz.
	private static double calculateQuizAverage(int quizNumber, double[][] studentScores) {
		// Variables used to derive the average class performance on a quiz.
		double quizScoresTotal = 0.0;
		int quizScoresCount = 0;
			
		// Loops through all of the scores for a certain quiz, allowing the program to sum
		// up the scores and keep track of a count of the number of scores. 
		for(int i = 0; i < studentScores.length; i++) {
			quizScoresTotal += studentScores[i][quizNumber];
			quizScoresCount++;
		}
			
		// The average class performance for a quiz is the total of all student scores on a quiz
		// divided by the total number of scores for the quiz. 
		return quizScoresTotal / quizScoresCount;
	}
	
	// Prints the average class performance for a specific quiz onto the console.
	private static void displayQuizAverage(double[][] studentScores) {
		// Variables used for intaking the user's desired quiz number.
		int quizNumber = 0;
			
		// Prints out the average of the user-desired quiz number.
		System.out.println("Calculating average by Quiz Number");
		System.out.print("Enter Quiz Number: ");
		
		// Decrements by one so the studentScores array can properly reference the quiz number.
		quizNumber = Integer.parseInt(scanner.nextLine()) - 1;
		insertBlankLine();
			
		// Prints the quiz average for the user-specified quiz number. quizNumber is incremented
		// by 1 so that the count starts at 1, not 0.
		System.out.printf("Quiz %d average is %.2f", (quizNumber + 1), 
				calculateQuizAverage(quizNumber, studentScores));
		}
	
	// Returns the user's choice on the menu screen.
	private static String returnMenuScreenInput () {
		// Prompts the user to choose an option and returns it to another part of the program.
		System.out.println("\t\t\t\tMenu");
		System.out.println("1. Class Average");
		System.out.println("2. Student Average");
		System.out.println("3. Quiz Average\n");
		System.out.print("Enter choice number, or x to exit: ");
		return scanner.nextLine();
	}
	
	// Executes certain options of the menu depending on the user's input. 
	private static void executeMenu(String userInput, String[] names, double[][] studentScores) {
		// If the user enters 1, the class average for all quizzes will be printed.
		// If the user enters 2, a specific student's average for all quizzes will be printed.
		// If the user enters 3, the average score for a specific quiz will be printed. 
		switch(userInput) {
		case "1":
			displayClassAverage(studentScores);
			break;
		case "2":
			insertBlankLine();
			displayStudentAverage(names, studentScores);
			break;
		case "3":
			insertBlankLine();
			displayQuizAverage(studentScores);
			break;
		}
	}
	
	// Executes menu options repeatedly until the user opts out of the menu screen by entering "X".
	public static void cycleThroughMenu(String[] names, double[][] studentScores) {
		// Variable meant to store user input from the menu screen.
		String userInput = "";
		
		// Loops through the menu and executes the menu options until the sentinel value is entered.
		do {
			userInput = returnMenuScreenInput();
			executeMenu(userInput, names, studentScores);
			if(!userInput.equalsIgnoreCase("X"))
				insertSixBlankLines();
		} while (!userInput.equalsIgnoreCase("X"));
	}
	
	public static void main(String[] args) {
		// Variable meant to store user input for the number of scores they want.
		int numberOfScores = 0;
		// Parallel arrays meant to store each student's scores and names.
		double[][] studentScores;
		String[] names;
		
		// Allocates three students to each of the arrays.
		studentScores = new double[3][];
		names = new String[3];
		
		// Retrieves the number of scores which the user wishes to store for each student.
		numberOfScores = askForNumberOfScores();
		insertBlankLine();
		
		// Allocates the number of scores desired by the user into the inner studentScores array.
		setNumberOfScores(studentScores, numberOfScores);
		
		// Prompts for the names and scores for all students and stores them in the necessary arrays.
		gatherStudentInformation(studentScores, names);
		
		// Adds four lines on top of the one line from gatherStudentInformation() to create 5 lines.
		insertFourBlankLines();
		
		// Executes menu options repeatedly until the user opts out of the menu screen by entering "X".
		cycleThroughMenu(names, studentScores);
		
	}
	
}
