import java.util.Scanner;
public class App {
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void printHangman(int numWrongGuesses){
        switch(numWrongGuesses){
            case 0: 
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
            case 1:
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |    ◯    ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
            case 2:
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |    ◯    ");
                System.out.println("  |    |    ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
            case 3:
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |    ◯    ");
                System.out.println("  |   -|    ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
            case 4:
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |    ◯    ");
                System.out.println("  |   -|-   ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
            case 5:
                System.out.println("   ____     ");
                System.out.println("  |    |    ");
                System.out.println("  |    ◯    ");
                System.out.println("  |   -|-   ");
                System.out.println("  |   / \\    ");
                System.out.println("  |         ");
                System.out.println("  |         ");
                System.out.println("‾‾‾‾‾‾‾‾‾‾‾ ");
                break;
        }
    }
    public static boolean checkGuess(char guess, String correctWord, char[]wordStatus){
        boolean letterExists = false;
        for (int i = 0; i<correctWord.length(); i++){
            if (correctWord.charAt(i) == guess){
                wordStatus[i] = guess;
                letterExists = true;
            }
        }
        return letterExists;
    }
    public static boolean checkIfThereAreBlanks(char[] wordStatus){
        boolean gameStillGoing = false;
        for (int i = 0; i < wordStatus.length; i++){
            if (wordStatus[i] == '⎽'){
                gameStillGoing = true;
            }
        }
        for(char letter : wordStatus){
            if (letter == '⎽'){
                gameStillGoing = true;
            }
        }
        return gameStillGoing;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        clearScreen();
        int wrongGuesses = 0;
        char[] wrongLetters = new char[25];
        System.out.println("Type the corresponding number to choose a theme: Fruits (1), Animals (2), Countries (3), Difficult Words (4)");
        int themeChoice = input.nextInt();
        String correctWord = "";
        String[] words = {};
        String theme = "";
        if (themeChoice == 1){
            words = new String[]{"watermelon", "banana", "orange", "grapefruit", "blueberry", "tangerine", "lychee", "strawberry", "pomegranate", "raspberry", "cantaloupe", "nectarine", "apricot", "clementine", "avocado", "blackberry", "papaya"};
            theme = "Fruits";
        }
        else if (themeChoice == 2){
            words = new String[]{"zebra", "horse", "axolotl", "capybara", "leopard", "platypus", "rhinoceros", "giraffe", "elephant", "stingray", "dolphin", "ostrich", "kangaroo", "jellyfish", "peacock", "hamster", "porcupine", "donkey", "crocodile"};
            theme = "Animals";
        }
        else if (themeChoice == 3){
            words = new String[]{"vietnam", "thailand", "luxembourg", "netherlands", "denmark", "portugal", "argentina", "mongolia", "mexico", "australia", "pakistan", "singapore", "malaysia", "jamaica", "ethiopia", "belgium", "bangladesh", "ukraine"};
            theme = "Countries";
        }
        else if (themeChoice == 4){
            words = new String[]{"avenue", "abyss", "axiom", "blitz", "caliph", "oxygen", "gizmo", "glyph", "jiujitsu", "khaki", "quartz", "waltz", "wyvern", "strength", "microwave", "galaxy", "syndrome", "abrupt", "beekeeper", "espionage", "kilobyte" };
            theme = "Difficult Words";
        }
        else{
            System.out.println("Not a valid number:");
            System.out.println("Type the corresponding number to choose a theme: Fruits (1), Animals (2), Colors (3), Difficult Words (4)");
        }
        correctWord = words[(int)(Math.random() * words.length)];
        char[] wordStatus = new char[correctWord.length()];
        clearScreen();
        for (int i = 0; i < wordStatus.length; i++){
            wordStatus[i] = '⎽';
        }
        char guess;
        boolean gameStillGoing = true;
        while (wrongGuesses < 6 && checkIfThereAreBlanks(wordStatus)){
            boolean letterExists = false;
            System.out.println("Theme: " + theme);
            printHangman(wrongGuesses);
            System.out.print("Word: ");
            for (int i = 0; i<correctWord.length(); i++){
                System.out.print(wordStatus[i]);
            }
            System.out.println();
            System.out.print("Incorrect Letters:");
            for (int i = 0; i < wrongGuesses; i++){
                System.out.print(" " + wrongLetters[i] + " ");
            }
            System.out.println();
            System.out.print("Guess a Letter: ");
            guess = input.next().charAt(0);
            if (!checkGuess(guess, correctWord, wordStatus)){
                wrongLetters[wrongGuesses] = guess;
                wrongGuesses++;
            }
            clearScreen();
        }
        if (!checkIfThereAreBlanks(wordStatus)){
            printHangman(wrongGuesses);
            System.out.print("Congratulations, the word was " + correctWord + ".");
        }   
        if (wrongGuesses == 6){
            System.out.println("Sorry, you lost. The word was " + correctWord + ".");
            System.out.println("   ____      ");
            System.out.println("  |    |     ");
            System.out.println("  |    ◯     ");
            System.out.println("  |   -|-    ");
            System.out.println("  |   / \\   ");
            System.out.println("  |          ");
            System.out.println("  |          ");
            System.out.println("‾‾‾‾‾‾‾‾‾‾‾  ");
            System.out.print("Word: " + correctWord);
            System.out.println();
            System.out.print("Incorrect Letters:");
            for (int i = 0; i<wrongGuesses; i++){
                System.out.print(" " + wrongLetters[i] + " ");
            }
        }
    } 
}