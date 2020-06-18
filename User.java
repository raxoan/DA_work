package aaronEditFinal;

/**
 * User.java
 *
 * @author Fang Zheng
 * @author Aaron Wong
 * @author Sihan Sun
 * @author Kaiqi Yan
 * CIS 36B, Final project
 */

public class User extends Die {
	//private String level;
	private StringBuilder name;
	private final int maxHP; // Maximum HP, doesn't change but is only referenced.
	private int hp; // Health Point, if uses' hp equals 0, loses. If enemy's hp = 0, user wins.
	private int armor; // Armor Class. If enemy roll a number bigger than this, enemy attack first
	private int speed;
	private int damage; // The number of sides on the User's damage die

	/**
	 * User constructor that sets the number of sides of the dice the user will
	 * roll and gives other variable default values
	 *
	 * @param sides the number of sides of the dice
	 */
	public User() {
		this(0, 6, new StringBuilder("User"), 0, 0, 0, 0, 0);
	}

	/**
	 *
	 * @param name
	 * @param maxHP
	 * @param hp
	 * @param armor
	 * @param speed
	 */
	public User(StringBuilder name, int maxHP, int hp, int armor, int speed) {
		this(0, 10, name, maxHP, hp, armor, speed, 0);//
	}

	public User(StringBuilder name, int maxHP, int hp) {
		this(0, 10, name, maxHP, hp, 0, 0, 0);//
	}


	/**
	 *
	 * @param roll
	 * @param numSides
	 * @param name
	 * @param maxHP
	 * @param hp
	 * @param armor
	 * @param speed
	 * @param damage
	 */
	public User(int roll, int numSides, StringBuilder name, int maxHP, int hp, int armor, int speed, int damage) {
		super();
		this.name = name;
		this.maxHP = maxHP;
		this.hp = hp;
		this.armor = armor;
		this.speed = speed;
		this.damage = damage;
	}

	/**
	 *
	 * @return
	 */
	public StringBuilder getName() {
		return name;
	}

	/**
	 *
	 * @return
	 */
	public int getMaxHp() {
		return maxHP;
	}
	/**
	 *
	 * @return
	 */
	public int getHp() {
		return hp;
	}

	/**
	 *
	 * @return
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 *
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 *
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = new StringBuilder(name);
	}

//	/**
//	 *
//	 * @param max hp
//	 * @throws IndexOutOfBoundsException
//	 */
//	public void setMaxHp(int maxHP) throws  IndexOutOfBoundsException{
//		if (maxHP < 0) {
//			throw new IndexOutOfBoundsException("Health Point cannot be a negative number.");
//		} else {
//			this.maxHP = maxHP;
//		}
//	}
//

	/**
	 *
	 * @param hp
	 * @throws IndexOutOfBoundsException
	 */
	public void setHp(int hp) throws  IndexOutOfBoundsException{
		if (hp < 0) {
			throw new IndexOutOfBoundsException("Health Point cannot be a negative number.");
		} else {
			this.hp = hp;
		}
	}

	/**
	 *
	 * @param n
	 */
	public void updateHp(int n) {
		hp -= n;
		if (hp < 0)
			hp = 0;
	}

	/**
	 *
	 * @param armor
	 */
	public void setArmo(int armor) {
		this.armor = armor;
	}

	/**
	 *
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 *
	 */
	public void setDamage() {
		makeRoll();
		damage = getRoll();
	}

	/**
	 * Returns the sum of the roll and speed, The one has bigger number will attact
	 * first
	 *
	 * @return the sum of the roll and spend
	 */
	public int getSumRollSpeed() {
		return getRoll() + speed;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Name: " + name + "\nHealth Point: " + hp + "\nArmor Class: " + armor + "\nSpeed: " + speed + "\nDamage: d" + damage;
	}

	/**
	 *
	 */
	public void printGoFirst() {
		System.out.println(name + "'s initiative is higher. " +  name + " goes first.\n");

	}

	/**
	 * Overrides equals for Object. For the
	 * purposes of this project, we will consider two enemies to be equal on the
	 * basis of name and health point only
	 *
	 * @return whether two books have the same title and author
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Character)) {
			return false;
		} else {
			User u = (User) o;
			return name.toString().equals(u.name.toString()) && hp == u.hp;
		}
	}

	/**
	 * Now only compare name and hp, can change later.
	 */

	public int compareTo(User u) {
		if (this.equals(u)) {
			return 0;
		} else if (!name.toString().equals(u.name.toString())) {
			return name.compareTo(u.name);
		} else {
			return Integer.compare(hp, u.hp);
		}
	}

}
