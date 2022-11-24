package com.jspiders.musicplayerproject1.object;

import java.util.Scanner;

public class SongMain {
	static SongMain songmain = new SongMain();
	static boolean loop = true;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (loop) {
			try {
				songmain.musicPlayer();
			} catch (NullPointerException e) {
				System.out.println("Not Selected");
			}
		}
	}

	public void musicPlayer() {
		SongOperations op = new SongOperations();
		MainMenu();
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			AddRemove();
			int c1 = sc.nextInt();
			switch (c1) {
			case 1:
				op.AddSong();
				break;
			case 2:
				// op.viewplaylist();
				op.RemoveSongs();
				break;
			case 3:
				loop = false;
				MainMenu();
				break;
			default:
				System.out.println("Invalid choice...!!!");
				System.out.println("Try Again...!!!");
				break;
			}
			break;
		case 2:
			PlaySong();
			int c2 = sc.nextInt();
			switch (c2) {
			case 1:
				op.SelectSongs();
				break;
			case 2:
				op.playAllSongs();
				break;
			case 3:
				op.PlayRandomSongs();
				break;
			case 4:
				// MainMenu();
				break;
			default:
				System.out.println("Invalid choice...!!!");
				System.out.println("Try Again...!!!");
				break;
			}
			break;
		case 3:
			op.EditSongs();
			loop = false;
			MainMenu();
			break;
		case 4:
			Exit();
			break;
		default:
			System.out.println("Option is not matched...!!!");
			System.out.println("Try Again...!!!");
			break;
		}
	}

	public static void MainMenu() {
		System.out.println("1.Add/remove song");
		System.out.println("2.Play song");
		System.out.println("3.Edit song");
		System.out.println("4.Exit");
	}

	public static void AddRemove() {
		System.out.println("1.Add song");
		System.out.println("2.Remove song");
		System.out.println("3.Back");
	}

	public static void PlaySong() {
		System.out.println("1.Play a specific song");
		System.out.println("2.play all songs");
		System.out.println("3.Play a random song");
		System.out.println("4.Back");
	}

	private static void Exit() {
		System.out.println("Thank U...!!!");
		loop = false;
	}

}
