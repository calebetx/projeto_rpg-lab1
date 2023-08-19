package entities;

import java.util.Scanner;

public class Player {
	
	public static Scanner leitor = new Scanner(System.in);
		
	// Player's Attributes
	String name;
	int userHp;
	int especialCount;
	String grade;
	
	public Player(String name, int userHp, int especialCount, String grade) {
		this.userHp = userHp;
		this.especialCount = especialCount;
		this.grade = grade;
	}
	
	public void setClass() {
		if(this.grade.equals("Hero")) {
			this.userHp = 30;
			this.especialCount = 5;
		}
		else if(this.grade.equals("Tank")) {
			this.userHp = 50;
			this.especialCount = 2;
		}
		else if(this.grade.equals("Archer")) { // A DEFINIR
			this.grade = leitor.next();
		}
		else if(this.grade.equals("Warlock")) { // A DEFINIR
			this.name = leitor.next();
		}
	}
	
	
	// Player's Actions
	public static int userAtack() {
		System.out.println("Choice your attack!");
		System.out.println("(1) Punch");
		System.out.println("(2) Especial");
		return leitor.nextInt();
	}
	
	// Return Data
	public void setStatus(String attribute) {
		if(attribute == "userHp") {
			this.userHp = leitor.nextInt();
		}
		else if(attribute == "especialCount") {
			this.especialCount = leitor.nextInt();
		}
		else if(attribute == "grade") {
			this.grade = leitor.next();
		}
		else if(attribute == "name") {
			this.name = leitor.next();
		}
	}
	
	public int getStatusInt(String attribute) {
		if(attribute == "userHp") {
			return this.userHp;
		}
		else if(attribute == "especialCount") {
			return this.especialCount;
		}
		else
			return 0;
	}
	
	public String getStatusStr(String attribute) {
		if(attribute == "grade") {
			return this.grade;
		}
		if(attribute == "name") {
			return this.name;
		}
		else
			return null;
	}

}
