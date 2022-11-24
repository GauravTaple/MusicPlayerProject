package com.jspiders.musicplayerproject1.object;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

public class SongOperations {
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private static CallableStatement callableStatement;
	private static PreparedStatement preparedStatement;
	private static int result;
	private static Properties properties = new Properties();
	private static FileReader fileReader;
	private static String filePath = "C:\\Users\\manas\\OneDrive\\Desktop\\Core Java\\Eclipseee\\musicplayerproject1\\resources\\db_info.properties";
	private static String query;
	Scanner sc = new Scanner(System.in);
	public static ArrayList<Integer> arraylist = new ArrayList<Integer>();
	static SongOperations songOperations = new SongOperations();
	public static int count = 0;

	public static void Connection() {
		try {
			fileReader = new FileReader(filePath);
			properties.load(fileReader);
			Class.forName(properties.getProperty("driverPath"));
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int countSongs() {
		int count = 0;
		try {
			Connection();
			query = "select id from student1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				arraylist.add(resultSet.getInt(1));
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void AddSong() {
		System.out.println("How many song to add");
		int songAdd = sc.nextInt();
		for (int i = 0; i < songAdd; i++) {
			count++;
			try {
				Connection();
				System.out.println("Enter Id");
				int id = sc.nextInt();

				System.out.println("Enter Name");
				String name = sc.next();

				System.out.println("Enter Movie");
				String movie = sc.next();

				System.out.println("Enter length");
				float length = sc.nextFloat();

				System.out.println("Enter Singer");
				String sing = sc.next();

				System.out.println("Enter Composer");
				String comp = sc.next();

				System.out.println("Enter Lyricist");
				String lyrics = sc.next();
				query = "insert into student1 values(?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, movie);
				preparedStatement.setFloat(4, length);
				preparedStatement.setString(5, sing);
				preparedStatement.setString(6, comp);
				result = preparedStatement.executeUpdate();
				System.out.println("Add Successfully...!!!");
				// SongMain.MainMenu();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closeConnection();
	}

	public void viewplaylist() {
		try {
			Connection();
			query = "select * from student1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void RemoveSongs() {
		try {
			System.out.println("Present songs are");
			songOperations.viewplaylist();
			Connection();
			System.out.println("Enter id for delete song:- ");
			int id = sc.nextInt();
			query = "delete from student1 where id=" + id;
			preparedStatement = connection.prepareStatement(query);
			result = preparedStatement.executeUpdate(query);
			// arraylist.remove(Integer.valueOf(id));
			System.out.println("Song deleted sussessfully...!!!");
			count--;
			songOperations.viewplaylist();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void playAllSongs() {
		try {
			Connection();
			query = "select * from student1";
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2) + " is playing");
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void SelectSongs() {
		try {
			Connection();
			songOperations.viewplaylist();
			System.out.println("enter id to play song");

			int id = sc.nextInt();
			int count = songOperations.countSongs();
			if (id <= count) {
				query = "select name from student1 where id=" + id;
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					System.out.println(resultSet.getString(1) + " song playing");
					// Thread.sleep(1000);
				}
			} else {
				System.out.println("enter proper id");
				songOperations.SelectSongs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void PlayRandomSongs() {
		try {
			Connection();
			query = "select id,name from student1";
			resultSet = statement.executeQuery(query);

			songOperations.countSongs();
			double id = (Math.random() * (arraylist.size()));
			query = "select name from student1 where id=" + arraylist.get((int) id);
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " is playing");
				// Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public void EditSongs() {
		try {
			Connection();
			System.out.println("present songs are...");
			songOperations.viewplaylist();
			System.out.println("select id of song to update");
			int id = sc.nextInt();
			int count = songOperations.countSongs();
			if (id <= count) {
				System.out.println("Edit followings\n1.name\n2.singer\n3.length\n");
				int update = sc.nextInt();
				if (update <= 3) {
					switch (update) {
					case 1:
						System.out.println("enter new songName");
						String songName = sc.next();
						query = "update student1 set name=? where id=" + id;
						preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, songName);
						result = preparedStatement.executeUpdate();
						System.out.println("song update successfully...");
						break;

					case 2:
						System.out.println("enter new singer");
						String singerName = sc.next();
						query = "update student1 set singer=? where id=" + id + "";
						preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, singerName);
						result = preparedStatement.executeUpdate();
						System.out.println("song update successfully...");
						break;

					case 3:
						System.out.println("enter length of song");
						float length = sc.nextFloat();
						query = "update student1 set singer=? where id=" + id;
						preparedStatement = connection.prepareStatement(query);
						preparedStatement.setDouble(1, length);
						result = preparedStatement.executeUpdate();
						System.out.println("song update successfully...");
						break;

					default:
						System.out.println("invalid input");
						songOperations.EditSongs();
						break;
					}
				} else {
					System.out.println("invalid input");
					songOperations.EditSongs();
				}
			} else {
				System.err.println("select proper id");
				songOperations.EditSongs();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection();
	}

	public static void closeConnection() {
		try {

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
