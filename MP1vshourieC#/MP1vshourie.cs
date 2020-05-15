using System;
using static System.Console;

namespace MP1vshourie
{
    public class MP1
    {
        // Inserts a blank line.
        private static void InsertBlankLine()
        {
            WriteLine();
        }

        // Asks the user for the number of scores they would like to provide.
        private static int AskForNumberOfScores()
        {
            // Prompts the user for the number of scores and returns them to different part of the program.
            Write("How many scores per student? ");
            return Convert.ToInt32(ReadLine());
        }

        // Allocates the number of scores desired by the user into the inner array of studentScores.
        private static void SetNumberOfScores(double[][] studentScores, int numberOfScores)
        {
            // For every iteration of the for loop, the desired number of scores are allocated for each student.
            for (int i = 0; i < studentScores.Length; i++)
            {
                studentScores[i] = new double[numberOfScores];
            }
        }

        // Takes in user input regarding the student's name.
        private static string InputStudentName(int studentNumber)
        {
            // Prompts the student's name and returns it to another part of the program. 
            Write("Enter name for student {0}: ", studentNumber);
            return ReadLine();
        }

        // Takes in user input to store a student's score.
        private static double InputScore(int quizNumber)
        {
            // Prompts the user to enter a score for the student and returns the score.
            Write("Quiz {0}: ", quizNumber);
            return Convert.ToDouble(ReadLine());
        }

        // Prompts for student names and scores from the user and scores them in arrays.
        private static void GatherStudentInformation(double[][] studentScores, string[] names)
        {
            // Outer for loop loops through every student.
            for (int i = 0; i < studentScores.Length; i++)
            {
                // Allows the user to input names for every student. i is incremented by one
                // so the prompts become meaningful to the user. 
                names[i] = InputStudentName(i + 1);
                InsertBlankLine();

                // Prompts the user to enter scores for every student.
                Write("Entering scores for {0}\n", names[i].ToUpper());

                // Each iteration of the inner for loop allows the user to input every student's score.
                for(int j = 0; j < studentScores[i].Length; j++)
                {
                    // j is incremented by one so prompts become meaningful to the user.
                    studentScores[i][j] = InputScore(j + 1);
                }

                InsertBlankLine();
            }
        }

        // Calculates and returns the class average for all quizzes.
        private static double CalculateClassAverage(double[][] studentScores)
        {
            // Variables for totaling class scores and averaging them to determine class average.
            double classScoresTotal = 0.0;
            int classScoresCount = 0;

            // Traverses through every student's scores, totaling them and adding up the total count of scores.
            for (int i = 0; i < studentScores.Length; i++)
            {
                for (int j = 0; j < studentScores[i].Length; j++)
                {
                    classScoresTotal += studentScores[i][j];
                    classScoresCount++;
                }
            }

            // The average of the class scores is equal to the total of all class scores divided by the total number
            // of class scores.
            return classScoresTotal / classScoresCount;
        }

        // Prints the class average for all quizzes.
        private static void DisplayClassAverage(double[][] studentScores)
        {
            Write("Class average for all quizzes is {0:0.00}", CalculateClassAverage(studentScores));
        }

        // Checks whether if a student's inputted student name matches with a student in the class.
        private static int CalculateStudentPosition(string studentName, string[] names)
        {
            // Every iteration of the loop checks whether if the name corresponds with any student.
            // If there's a match, the student position in the array is validated and returned.
            for (int i = 0; i < names.Length; i++)
            {
                if(studentName.Equals(names[i], StringComparison.OrdinalIgnoreCase))
                    return i;
            }

            // If there is not a match, the student name is rejected and returned.
            return -1;
        }

        // Calculates and returns the validated student's average score.
        private static double CalculateStudentAverage(int studentPosition, string[] names, double[][] studentScores)
        {
            // Measures the total of a student's quiz scores.
            double studentQuizTotal = 0.0;
            int studentQuizCount = 0;

            // Using the student position, we total up all the scores and count them in every iteration of the
            // loop to calculate the average.
            for (int i = 0; i < studentScores[studentPosition].Length; i++)
            {
                studentQuizTotal += studentScores[studentPosition][i];
                studentQuizCount++;
            }

            // Calculates and returns the average, which is the total of all of the student's quiz scores divided
            // by the number of quizzes the student has taken.
            return studentQuizTotal / studentQuizCount;
        }

        // Prints out all of an individual student's scores.
        private static void DisplayStudentScores(string[] names, int studentPosition, double[][] studentScores)
        {
            // Prints out the name of the validated student.
            Write("{0}'s scores are: ", names[studentPosition]);
            // Each iteration of the loop prints out the specific student's scores.
            for (int i = 0; i < studentScores[studentPosition].Length; i++)
            {
                Write("{0:0.00} ", studentScores[studentPosition][i]);
            }
        }

        // Displays a student's scores on all of the quizzes and their average.
        private static void DisplayStudentAverage(string[] names, double[][] studentScores)
        {
            // Meant to store the name and position in the array for the student whose scores 
            // the user wants to see.
            string studentName = "";
            int studentPosition = 0;

            // Prompts the user for the student's name.
            WriteLine("Calculating average by student.\n");
            Write("Enter student name: ");
            studentName = ReadLine();

            // Calculates the student's position on the names/studentScores array.
            studentPosition = CalculateStudentPosition(studentName, names);

            // If the student position cannot be found, an error message is printed. If found, the program 
            // displays all the student's scores and displays their average on all of the quizzes.
            if (studentPosition == -1)
                Write("Student not found.");
            else
            {
                DisplayStudentScores(names, studentPosition, studentScores);
                InsertBlankLine();
                Write("{0}'s average is {1:0.00}", names[studentPosition],
                    CalculateStudentAverage(studentPosition, names, studentScores));
            }
        }

        // Calculates the average class performance for a specific quiz.
        private static double CalculateQuizAverage(int quizNumber, double[][] studentScores)
        {
            // Variables used to derive the average class performance on a quiz.
            double quizScoresTotal = 0.0;
            int quizScoresCount = 0;

            // Loops through all of the scores for a certain quiz, allowing the program to sum up 
            // the scores and keep track of a count of the number of scores.
            for (int i = 0; i < studentScores.Length; i++)
            {
                quizScoresTotal += studentScores[i][quizNumber];
                quizScoresCount++;
            }

            // The average class performance for a quiz is the total of all student scores on a quiz
            // divided by the total number of scores for the quiz.
            return quizScoresTotal / quizScoresCount;
        }

        // Prints the average class performance for a specific quiz onto the console.
        private static void DisplayQuizAverage(double[][] studentScores)
        {
            // Variables used for intaking the user's desired quiz number.
            int quizNumber = 0;

            // Prints out the average of the user-desired quiz number.
            WriteLine("Calculating average by Quiz Number");
            Write("Enter Quiz Number: ");

            // Decrements by one so the studentScores array can properly reference the quiz number.
            quizNumber = Convert.ToInt32(ReadLine()) - 1;
            InsertBlankLine();

            // Prints the quiz average for the user-specified quiz number. The quiz number is incremented
            // by one so that the count starts at one, not zero.
            Write("Quiz {0} average is {1:0.00}", (quizNumber + 1), CalculateQuizAverage(quizNumber, studentScores));
        }

        // Returns the user's choice on the menu screen.
        private static string ReturnMenuScreenInput()
        {
            // Prompts the user to choose an option and returns it to another part of the program.
            WriteLine("\t\t\t\tMenu");
            WriteLine("1. Class Average");
            WriteLine("2. Student Average");
            WriteLine("3. Quiz Average\n");
            Write("Enter choice number, or x to exit: ");
            return ReadLine();
        }

        // Executes certain options of the menu depending on the user's input.
        private static void ExecuteMenu(string userInput, string[] names, double[][] studentScores)
        {
            // If the user enters 1, the class average for all quizzes will be printed.
            // If the user enters 2, a specific student's average for all quizzes will be printed.
            // If the user enters 3, the average score for a specific quiz will be printed.
            switch(userInput)
            {
                case "1":
                    DisplayClassAverage(studentScores);
                    break;
                case "2":
                    InsertBlankLine();
                    DisplayStudentAverage(names, studentScores);
                    break;
                case "3":
                    InsertBlankLine();
                    DisplayQuizAverage(studentScores);
                    break;
            }
        }

        // Executes menu options repeatedly until the user opts out of the menu screen by entering "X."
        private static void CycleThroughMenu(string[] names, double[][] studentScores)
        {
            // Variable meant to store user input from the menu screen.
            string userInput = "";

            // Loops through the menu and executes the menu options until the sentinel value is entered.
            do
            {
                userInput = ReturnMenuScreenInput();
                ExecuteMenu(userInput, names, studentScores);
                if (!userInput.Equals("X", StringComparison.OrdinalIgnoreCase))
                {
                    InsertBlankLine();
                    InsertBlankLine();
                    Write("Press Enter to continue... ");
                    ReadLine();
                    Console.Clear();
                }
            } while (!userInput.Equals("X", StringComparison.OrdinalIgnoreCase));
        }
        
        public static void Main(string[] args)
        {
            // Variable meant to store user input for the number of scores they want.
            int numberOfScores = 0;

            // Parallel arrays meant to store each student's scores and names.
            double[][] studentScores;
            string[] names;

            // Allocates three students to each of the arrays.
            studentScores = new double[3][];
            names = new string[3];

            // Retrieves the number of scores which the user wishes to store for each student.
            numberOfScores = AskForNumberOfScores();
            InsertBlankLine();

            // Allocates the number of scores desired by the user into the inner studentScores array.
            SetNumberOfScores(studentScores, numberOfScores);

            // Prompts for the names and scores for all students and stores them in the necessary arrays.
            GatherStudentInformation(studentScores, names);

            Console.Clear();

            // Executes the menu options repeatedly until the user opts out of the menu screen by entering "X."
            CycleThroughMenu(names, studentScores);
        }
    }
}
