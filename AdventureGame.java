package aaronEditFinal;
/**
 * @author Aaron Wong
 * @author Fang Zheng
 * @author Sihan Sun
 * @author Kaiqi Yan
 */
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

			input = new Scanner(System.in);
			a.bubbleSort();

			System.out.println("Welcome to our Adventure Game!\n\nPlease enter your user name:");
			StringBuilder username = new StringBuilder(input.nextLine());

			User u = new User(username, 20, 20, 15, 7);
			Fighter<User> fighter0 = new Fighter<>(u);
			fighters.add(fighter0);
			System.out.println(fighter0.getFighter().toString());

			u.setNumSides(20);
			int pos, hp, sizeEnemies = a.enemies.size();
			Enemy e;
			String choice = "", name;

			System.out.println("Your goal is to defeat as many enemies as possible, get ready!");

			while (!choice.equals("X")) {// && sizeEnemies != 0) {
				a.printMenu();
				System.out.print("What do you do? ");
				choice = input.nextLine().toUpperCase();
				// a.printFighters(a.enemies);

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
						u.setHp(u.getMaxHp()); // reset HP to maximum before battle
						// a.gameSetUp(u, enemy);
						a.gameSetUp(u, e);
						Fighter<Enemy> fighter = new Fighter<>(e);// e
						fighters.add(fighter);

						// if below use if statement, only remove died enemy.
						// This means user eventually can kill all enemies if he chose not quit;
						// Because user fight with 6 enemies, to be fair, every game,
						// I give 20 hp to user fight with new enemy. Otherwise, it will end soon.
						// We will add the hp user left every game, recode the total. In this way
						// User will fight many times because he maybe will fight again with the enemy
						// who won him;

						// if (e.getHp() == 0) {
						// System.out.println(e.getName() + " is removed from enemies list:\n" +
						// a.enemies.remove(pos));
						// sizeEnemies--;
						// }

						// Below doesn't use if statement, it will remove the enemy after user fight
						// with him once.
						// In this way, user will only fight 6 times. Some enemies will be still alive.
						System.out.println(e.getName() + " is removed from enemies list:\n" + a.enemies.remove(pos));
						sizeEnemies--;
						if (sizeEnemies == 0) {
							System.out.println("\nYou have fought with all enemies.");
							break;
						}
					} else {
						System.out.println("The enemy does not exist.");
					}
				} else if (choice.equals("X")) {
					break;
				} else {
					System.out.println("\nInvalid Option!\n");
				}
			}
			input.close();

			if (Enemy.getScore() > 0) {
				System.out.println("Congratulations! You earned a score of: " + Enemy.getScore()  +
						"\nCheck the results.txt file to see your overall results.");
			} else {
				System.out.println("You did not earn any points. Better luck next time.");
			}

			File outfile = new File("result.txt");
			PrintWriter output = new PrintWriter(outfile);
			output.println(u.getName() + "'s Game Result of fighting with enemies:");
			for (Fighter f : fighters) {
				// output.println(f.getFighter().toString() + "\n");
				output.println(f);
			}
			output.println("\nCongratulations, " + u.getName() + "! The total score you earned is " + Enemy.getScore());
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
			// StringBuilder s = new StringBuilder(name);
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
	 * @param input  Scanner object to read from.
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
			} catch (InputMismatchException e) {
				System.out.println("Error. Enter digits only!");
				input.nextLine(); // clear the buffer
			} catch (Exception e) {
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
		System.out.println("A. Fight" + "\nX. Exit and print score\n");
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
	 * Character Creation.
	 * Prompt user to enter and roll for various attributes for their character
	 */
	private void createUser() {
		
	}

	/**
	 * Prompts the user to input a number of die sides Prints out the user choice
	 * 
	 * @param input the Scanner
	 */
	private void gameSetUp(User u, Enemy e) throws Exception {
		u.makeRoll();
		System.out.println("\nRoll for initiative!\n");
		System.out.println(u.getName() + " rolls a " + u.getSumRollSpeed());
		e.makeRoll();
		System.out.println(e.getName() + " rolls a " + e.getSumRollSpeed());
		
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
		int dmgR = 0, userHit;
		String turn = "";
		Scanner input = new Scanner(System.in);
		System.out.println(u.getName() + " rolls first to attack. Enter (A) to attack!");
		while (u.getHp() > 0 && e.getHp() > 0) {
			while (!turn.equalsIgnoreCase("R")) {
				turn = input.nextLine();
				if (turn.equalsIgnoreCase("A")) {
					u.makeRoll(); // If user roll armor > than enemy's default armor class, User hit, otherwise
									// miss
					userHit = u.getRoll() + 4; // User receives a +4 bonus to hit the enemy.
					System.out.println(u.getName() + " rolls a " + userHit + " against " + e.getName()
							+ "\'s AC of: " + e.getArmor());
					if (userHit > e.getArmor()) {
						System.out.println(u.getName() + " hits!");
						dmgR = u.rollDmg(8) + 2; // User rolls a d8 for damage, +2 bonus
						System.out.println(u.getName() + " deals " + dmgR + " damage to " + e.getName() + "!\n");
						e.updateHp(dmgR); // update enemy's health point by minus damage
						System.out.println(e.getName() + " now has " + e.getHp() + " HP remaining.\n");
						if (e.getHp() == 0 && u.getHp() != 0) {
							System.out.println(u.getName() + " defeats " + e.getName() + "!\n");
							Enemy.updateScore(u.getHp()); // If enemy loses, user gets the score of his own health point
							System.out.println("You earn " + u.getHp() + " points for defeating " + e.getName());
							System.out.println("$$$$$$$$$$\n");
							break;
						}
					} else {
						System.out.println(u.getName() + " misses.\n");
					}
					System.out.println("Now, " + e.getName() + " rolls to attack.");
					e.makeRoll();
					System.out.println(e.getName() + " rolls a " + e.getRoll() + " against " + u.getName()
							+ "\'s AC of: " + u.getArmor());
					if (e.getRoll() > u.getArmor()) { // If roll > user's default armor class, enemy hit, otherwise miss
						System.out.println(e.getName() + " hits!");
						//e.makeRoll(); // Enemy makes roll again to decide damage
						dmgR = e.rollDmg(4);
						System.out.println(e.getName() + " deals " + dmgR + " damage to " + u.getName() + "!\n");
						u.updateHp(dmgR); // update user's health point by minus damage
						System.out.println(u.getName() + " now has " + u.getHp() + " HP remaining.\n");
						if (u.getHp() == 0 && e.getHp() != 0) {
							System.out.println("Oh no! You were defeated by " + e.getName() + "\n");
							break;
						}
					} else {
						System.out.println(e.getName() + " misses.\n");
					}
					System.out.print("Do you (A) Attack or (R) Run away? ");
				} else if (turn.equalsIgnoreCase("R")) {
					System.out.println("You run away!");
					System.out.println("~~~~~~~~~~\n");
					u.setHp(0); // Setting user HP to zero to break out of combat
					break;
				} else {
					System.out.println("Invalid option, please try again.\nDo you (A) Attack or (R) Run away? ");
				}
			}
		}
//		input.close();

	}

	/**
	 * 
	 * @param e Enemy object
	 * @param u User object
	 */
	public void gameEnemyGoFirst(Enemy e, User u) {
		int dmg = 0, userHit;
		String turn = "";
		Scanner input = new Scanner(System.in);
		System.out.println(e.getName() + " rolls first to attack. Enter (A) to fight!");
		while (u.getHp() > 0 && e.getHp() > 0) {
			while (!turn.equalsIgnoreCase("R")) {
				turn = input.nextLine();
				if (turn.equalsIgnoreCase("A")) {
					
					e.makeRoll();
					System.out.println(e.getName() + "rolls a " + e.getRoll() + " against " + u.getName() + "\'s AC of: "
							+ u.getArmor());
					if (e.getRoll() > u.getArmor()) { // If roll > user's default armor class, enemy hit, otherwise miss
						System.out.println(e.getName() + " hits!");
						dmg = e.rollDmg(4); // Enemy rolls a d4 to deal damage 
						System.out.println(e.getName() + " deals " + dmg + " damage to " + u.getName() + "!\n");
						u.updateHp(dmg); // update user's health point by minus damage
						System.out.println(u.getName() + " now has " + u.getHp() + " HP remaining.");
						if (u.getHp() == 0 && e.getHp() != 0) {
							System.out.println("Oh no! You were defeated by " + e.getName());
							break;
						}
					} else {
						System.out.println(e.getName() + " misses.\n");
					}
					u.makeRoll(); // If user roll armor > than enemy's default armor class, User hit, otherwise
									// miss
					userHit = u.getRoll() + 4; // User receives a +4 bonus to hit the enemy.
					System.out.println(u.getName() + " rolls a " + userHit + " against " + e.getName() + "\'s AC of: "
							+ e.getArmor());
					if (userHit > e.getArmor()) {
						System.out.println(u.getName() + " hits!");
						//u.makeRoll(); // If hit, roll again to decide damage number
						dmg = u.rollDmg(8) + 2; // User rolls a d8 for damage, adding a +2 bonus
						System.out.println(u.getName() + " deals " + dmg + " damage to " + e.getName() + "!\n");
						e.updateHp(dmg); // update enemy's health point by minus damage
						System.out.println(e.getName() + " now has " + e.getHp() + " HP remaining.");
						if (e.getHp() == 0 && u.getHp() != 0) {
							System.out.println(u.getName() + " defeats " + e.getName() + "!\n");
							Enemy.updateScore(u.getHp()); // If enemy loses, user gets the score of his own health point
							System.out.println("You earn " + u.getHp() + " points for defeating " + e.getName());
							System.out.println("$$$$$$$$$$\n");
							break;
						}
					} else {
						System.out.println(u.getName() + " misses.");
					}
					System.out.print("Do you (A) Attack or (R) Run away? ");
				} else if (turn.equalsIgnoreCase("R")) {
					System.out.println("You run away!");
					System.out.println("~~~~~~~~~~\n");
					u.setHp(0); // Setting user HP to zero to break out of combat
					break;
				} else {
					System.out.print("Invalid input, please try again.\nDo you (A) Attack or (R) Run away? ");
				}
			}
		}
	}
}
