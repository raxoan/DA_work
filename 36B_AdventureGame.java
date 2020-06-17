package finalFromFang;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public final class AdventureGame implements InterfaceList {
	private ArrayList<Enemy> enemies = new ArrayList<>();
	final static String filename = "enemies.txt";

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		ArrayList<Fighter> fighters = new ArrayList<>();

		try {
			AdventureGame a = new AdventureGame();
			File file = new File(filename);
			Scanner input = new Scanner(file);
			a.readFile(input);
			input.close();

			input = new Scanner(System.in);
			a.bubbleSort();

			System.out.println("Welcome to Adventure Game!\n\nPlease put your game account information:");
			User u = new User(new StringBuilder("Tom"), 20, 10, 3);
			Fighter<User> fighter0 = new Fighter<>(u);
			fighters.add(fighter0);
			System.out.println(fighter0.getFighter().toString());
			
			u.setNumSides(20);
			int pos, hp, sizeEnemies = a.enemies.size();
			Enemy e;
			String choice = "", name;
			
			while (!choice.equals("X")) {//&& sizeEnemies != 0) { 
				a.printMenu();
				System.out.print("Enter your choice: ");
				choice = input.nextLine().toUpperCase();
				//a.printFighters(a.enemies);

				if (choice.equals("A")) {
					System.out.println("\nWe currently have " + a.enemies.size() + " Enemies:");
					a.printFighters(a.enemies);
					System.out.print("Enter the enemy you want to fight with:\nName: ");
					name = input.nextLine();
					hp = readInt(input, "Health Point: ");
					input.nextLine();
					e = new Enemy(new StringBuilder(name), hp);
					pos = a.binarySearch(e);
					
					if (pos != -1) {
						e = a.enemies.get(pos);
						e.setNumSides(20);
						u.setHp(20);
						//a.gameSetUp(u, enemy);
						a.gameSetUp(u, e);
						Fighter<Enemy> fighter = new Fighter<>(e);//e
						fighters.add(fighter);
						
						//if below use if statement, only remove died enemy. 
						//This means user eventually can kill all enemies if he chose not quit;
						//Because user fight with 6 enemies, to be fair, every game, 
						//I give 20 hp to user fight with new enemy. Otherwise, it will end soon.
						// We will add the hp user left every game, recode the total. In this way
						// User will fight many times because he maybe will fight again with the enemy
			            // who won him;

						//if (e.getHp() == 0) {
							//System.out.println(e.getName() + " is removed from enemies list:\n" + a.enemies.remove(pos));
							//sizeEnemies--; 
						//}
						
						//Below doesn't use if statement, it will remove the enemy after user fight with him once.
						//In this way, user will only fight 6 times. Some enemies will be still alive.
						System.out.println(e.getName() + " is removed from enemies list:\n" + a.enemies.remove(pos));
						sizeEnemies--; 
						if (sizeEnemies == 0) {
							System.out.println("\nYou have fought with all enemies.");
							break;
						}
					} else {
						System.out.println("The enemy does not exist.");
					}	
				} else if (choice.equals("B")) {
					u.setHp(0);
					System.out.println("You ran away. You lose.");
				} else if (choice.equals("X")) {
					break;
				} else {
					System.out.println("\nInvalid Option!\n");
				}
			}
			input.close();
			
			if (Enemy.getScore() > 0) {
				System.out.println("Congratulations! You won a score: " + Enemy.getScore());
			} else {
				System.out.println("You did not win a score. Better luck next time.");
			}
	
			File outfile = new File("result.txt");
			PrintWriter output = new PrintWriter(outfile);
			output.println(u.getName() + "'s Game Result of fighting with enemies:");
			for (Fighter f : fighters) {
				//output.println(f.getFighter().toString() + "\n");
				output.println(f);
			}
			output.println("\nCongratulations, " +  u.getName() + "! The total score you won is " + Enemy.getScore());
			output.close();
		} catch (IOException ex) {
			System.out.println(ex);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Reads file and save the content to an Enemy object array list
	 * 
	 * @param infile input file contains information of AI enemies
	 * @param input  Scanner object
	 * @return the array list of Enemy object
	 * @throws IOException handle a possible exception
	 */
	public void readFile(Scanner input) throws IOException {
		String name;
		int hp, armor, speed;

		while (input.hasNextLine()) {
			name = input.nextLine();
			hp = input.nextInt();
			armor = input.nextInt();
			speed = input.nextInt();
			//StringBuilder s = new StringBuilder(name);
			Enemy e = new Enemy(new StringBuilder(name), hp, armor, speed);
			enemies.add(e);

			if (input.hasNextLine()) {
				input.nextLine();
			}
		}
	}
	
	
	/**
     * Read a double from the console.
     *
     * @param input Scanner object to read from.
     * @param prompt User prompt to show before input
     * @return An int entered by the user.
     */
    public static int readInt(Scanner input, String prompt) {    
        int number = 0;
        boolean more = true;
        while (more) {
            try {
                System.out.print(prompt);
                number = input.nextInt();
                more = false;
            } catch(InputMismatchException e) {
                System.out.println(
                    "Error. Enter digits only!");
                input.nextLine(); // clear the buffer
            } catch(Exception e) {
                System.out.println("\nSome other exception!");
                input.nextLine();            
            }
        }
        return number;
    }


	/**
	 * Searches for e in the catalogue
	 * 
	 * @param e the Enemy to locate
	 * @return the location of b in the catalogue
	 */
	@Override
	public int binarySearch(Enemy e) {
		int mid, low = 0, high = enemies.size() - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (enemies.get(mid).equals(e)) {
				return mid;
			} else if (e.compareTo(enemies.get(mid)) < 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * Sorts the catalogue into ascending order
	 */
	@Override
	public void bubbleSort() {
		for (int i = 0; i < enemies.size() - 1; i++) {
			for (int j = 0; j < enemies.size() - i - 1; j++) {
				if (enemies.get(j).compareTo(enemies.get(j + 1)) > 0) {
					Enemy temp = enemies.get(j);
					enemies.set(j, enemies.get(j + 1));
					enemies.set(j + 1, temp);
				}
			}
		}
	}

	/**
	 * Prints a menu of options to interact with the catalogue C. Print catalogue\n
	 */
	@Override
	public void printMenu() {
		System.out.println("\nPlease select from one of the options:\n");
		System.out.println("A. Fight" + "\nB. Run away\nX. Exit\n");
	}

	/**
	 * Prints out the current catalog
	 */
	@Override
	public void printFighters(ArrayList<Enemy> list) {
		System.out.println("\nThese are the enemies:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i) + "\n");
		}
	}

	/**
	 * Prompts the user to input a number of die sides Prints out the user choice
	 * 
	 * @param input the Scanner
	 */
	private void gameSetUp(User u, Enemy e) throws Exception {
		u.makeRoll();
		System.out.println("\nPlease roll a speed to add your default speeed.\n");
		System.out.println(u.getName() + "'s roll + default speed: " + u.getSumRollSpeed());
		e.makeRoll();
		System.out.println(e.getName() + "'s roll + default speed: " + e.getSumRollSpeed());
		
		if (u.getSumRollSpeed() > e.getSumRollSpeed()) {
			u.printGoFirst();
			gameUserGoFirst(u, e);
		} else {
			e.printGoFirst();
			gameEnemyGoFirst(e, u);
		}
	}
	
	/**
	 * 
	 * @param u User object
	 * @param e Enemy object
	 */
	public void gameUserGoFirst(User u, Enemy e) {
		int dmgR = 0;
		while (u.getHp() > 0 && e.getHp() > 0) {
			System.out.println(u.getName() + " rolls armor.");
			u.makeRoll(); // If user roll armor > than enemy's default armor class, User hit, otherwise miss
			if (u.getRoll() > e.getArmor()) {
				System.out.println(u.getName() + " hit!");
				u.makeRoll(); // If hit, roll again to decide damage number
				dmgR = u.getRoll();
				System.out.println(u.getName() + " did " + dmgR + " damage on " + e.getName() + "!\n");
				
				e.updateHp(dmgR); // update enemy's health point by minus damage
				if (e.getHp() == 0 && u.getHp() != 0) {
					System.out.println(u.getName() + " won " + e.getName() + "!\n");
					Enemy.updateScore(u.getHp()); // If enemy loses, user gets the score of his own health point
					break;
				}
			} else {
				System.out.println(u.getName() + " missed.\n");	
			}
			System.out.println(e.getName() + " rolls armor.");
			e.makeRoll();
			if (e.getRoll() > u.getArmor()) { // If roll > user's default armor class, enemy hit, otherwise miss
				System.out.println(e.getName() + " hit!");
				e.makeRoll(); // Enemy makes roll again to decide damage
				dmgR = e.getRoll();
				System.out.println(e.getName() + " did " + dmgR + " damage on " + u.getName() + "!\n");
				
				u.updateHp(dmgR); // update user's health point by minus damage
				if (u.getHp() == 0 && e.getHp() != 0) {
					System.out.println(e.getName() + " win!\n");
					break;
				}
			} else {
				System.out.println(e.getName() + " missed.\n");
			}
		}
	
	}
	
	/**
	 * 
	 * @param e Enemy object
	 * @param u User object
	 */
	public void gameEnemyGoFirst(Enemy e, User u) {
		int dmg = 0;
		while (u.getHp() > 0 && e.getHp() > 0) {
			System.out.println(e.getName() + " rolls armor.");
			e.makeRoll(); 
			if (e.getRoll() > u.getArmor()) {
				System.out.println(e.getName() + " hit!");
				e.makeRoll(); 
				dmg = e.getRoll();
				System.out.println(e.getName() + " did " + dmg + " damage on " + u.getName() + "!\n");
				
				u.updateHp(dmg); 
				if (u.getHp() == 0 && e.getHp() != 0) {
					System.out.println(e.getName() + " win!\n");
					break;
				}
			} else {
				System.out.println(e.getName() + " missed.\n");
			}
			System.out.println(u.getName() + " rolls armor.");
			u.makeRoll();
			if (u.getRoll() > e.getArmor()) {
				System.out.println(u.getName() + " hit!");
				u.makeRoll(); 
				dmg  = u.getRoll();
				System.out.println(u.getName() + " did " + dmg  + " damage on " + e.getName() + "!\n");
				
				e.updateHp(dmg); 
				if (e.getHp() == 0 && u.getHp() != 0) {
					System.out.println(u.getName() + " won " + e.getName() + "!\n");
					Enemy.updateScore(u.getHp()); 
					break;
				}
			} else {
				System.out.println(u.getName() + " missed.\n");
			}
		}
	}
}
