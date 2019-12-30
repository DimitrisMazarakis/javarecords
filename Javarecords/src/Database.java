
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {

	public static void main(String args[]) throws Exception {
		createTables();
		viewAlbum();
		startInserts();
	}

	// CREATE TABLE METHOD

	public static void createTables() {// creates the tables
		try {
			Connection con = getConnection();
			PreparedStatement createArtists = con.prepareStatement("CREATE TABLE IF NOT EXISTS  Artists(\n"
					+ "    artistid int,\n" 
					+ "    username char(30) NOT NULL,\n"  
					+ "    password char(30),\n"
					+ "    rating decimal(10,2),\n " 
					+ "    relevancyIndex decimal(10,2),\n" 
					+ "    genre char(20),\n"
					+ "    payPercentage decimal(10,2)\n," 
					+ "    img char(30)\n,"
					+ "    PRIMARY KEY (artistid))\n\n");// create Artists table

			PreparedStatement createlistOfVenues = con
					.prepareStatement("CREATE TABLE IF NOT EXISTS listOfVenues(venueid int,\r\n"
							+ "                           venuename char(30),\r\n"
							+ "						      distanceVenueArtist int,\r\n"
							+ "						      OrganizerName char(30),\r\n"
							+ "                           Ventype char(50),\r\n"
							+ "                           style char(50),\r\n"
							+ "                           location char (20),\r\n"
							+ "                           clean char(30),\r\n"
							+ "                           veninstall char(20),\r\n"
							+ "                           duration int,\r\n"
							+ "                           cut char(20),\n\n"
							+ "                           PRIMARY KEY (venueid))");// create listOfVenues table
			PreparedStatement createlistOfManagers = con
					.prepareStatement("CREATE TABLE IF NOT EXISTS listOfManagers( managerid int,\r\n"
							+ "							 username char(30),\r\n"
							+ "							 managerpassword char(50),\r\n"
							+ "                          PRIMARY KEY (managerid))");// create listOfManagers table
			PreparedStatement createAlbums = con
					.prepareStatement("CREATE TABLE IF  NOT EXISTS Albums(albumName char(30),\r\n"
							+ "								      pricep decimal(10,2),\r\n"
							+ "                                   priced decimal(10,2),\r\n"
							+ "                                   physical int,\r\n"
							+ "                                   digital int,\r\n"
							+ "                                   artistid int,\r\n"
							+ "                                   PRIMARY KEY (albumName),"
							+ "                                   FOREIGN KEY (artistid) REFERENCES Artist(artistid))");// create
																														// Albums
			PreparedStatement createSongs = con
					.prepareStatement("CREATE TABLE IF NOT EXISTS Songs(songname char(30),\r\n"
							+ "								 albumName char(30),\r\n"
							+ "                              PRIMARY KEY (songname, albumname),\r\n"
							+ "								 FOREIGN KEY(albumName) REFERENCES Albums(albumName))");// create
																													// Songs
																													// table

			createArtists.executeUpdate();
			createlistOfVenues.executeUpdate();
			createlistOfManagers.executeUpdate();
			createAlbums.executeUpdate();
			createSongs.executeUpdate();
		} catch (Exception e) {
			System.out.println("Something went wrong :(");
		}

	}

	// ADDING METHODS

	public static void addsArtists(int artistid, String username, String password, double rating, double relevancyIndex,
			 String genre, double payPercentage, String img) {// inserts new artists
		try {
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(
					"INSERT INTO Artists(artistid,username,password,rating,relevancyIndex,genre,payPercentage,img) VALUES (?,?,?,?,?,?,?,?)");
			statement.setInt(1, artistid);// adds artistid
			statement.setString(2, username);// addes username
			statement.setString(3, password);// adds password
			statement.setDouble(4, rating);// adds rating
			statement.setDouble(5, relevancyIndex);// adds relevancyIndex
			statement.setString(6, genre);// adds genre
			statement.setDouble(7, payPercentage);// addes payPercentage
			statement.setString(8, img);// adds img
			statement.executeUpdate();// execute the insert
			statement.close();
			System.out.println("added succesfully!!!:)");
		} catch (Exception e) {
			System.out.println("Could not add artist to database because it already exists :(");
		}
	}
	
	public static void addsManagers(int managerid, String username, String password) {//inserts new managers
		try {
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(
					"INSERT INTO listOfManagers(managerid,username,password) VALUES(?,?,?)");
			statement.setInt(1, managerid);// adds managerid
			statement.setString(2, username);// addes username
			statement.setString(3, password);// adds password
			statement.executeUpdate();// execute the insert
			statement.close();
			System.out.println("added succesfully!!!:)");
		} catch (Exception e) {
			System.out.println("Could not add VENUE to database because it already exists :(");
		}
	}

	public static void addsVenues(int venueid, String venuename, int distanceVenueArtist, String OrganizerName,
			String Ventype, String style, String location, String clean, String veninstall, int duration, String cut) {// inserts
																														// new
																														// venues
		try {
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(
					"INSERT INTO listOfVenues(venueid, venuename, distanceVenueArtist, OrganizerName, Ventype, style, location, clean, veninstall, duration, cut) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, venueid);// adds venueid
			statement.setString(2, venuename);// addes venuename
			statement.setInt(3, distanceVenueArtist);// adds distanceVenueArtist
			statement.setString(4, OrganizerName);// adds OrganizerName
			statement.setString(5, Ventype);// adds Ventype
			statement.setString(6, style);// adds style
			statement.setString(7, location);// addes location
			statement.setString(8, clean);// adds clean
			statement.setString(9, veninstall);// adds veninstall
			statement.setInt(10, duration);// addes duration
			statement.setString(11, cut);// adds cut
			statement.executeUpdate();// execute the insert
			statement.close();
			System.out.println("added succesfully!!!:)");
		} catch (Exception e) {
			System.out.println("Could not add venue to database because it already exists :(");
		}
	}

	public static void addsAlbums(String albumName, double pricep, double priced, int physical, int digital) {// inserts
																												// new
																												// albums
		try {
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(
					"INSERT INTO Albums(albumName, pricep, priced, physical, digital) VALUES(?,?,?,?,?)");
			statement.setString(1, albumName);// adds albumName
			statement.setDouble(2, pricep);// adds pricep
			statement.setDouble(3, priced);// adds priced
			statement.setInt(4, physical);// adds physical
			statement.setInt(5, digital);// adds digital
			statement.executeUpdate();// execute the insert
			statement.close();
			System.out.println("added succesfully!!!:)");
		} catch (Exception e) {
			System.out.println("Could not add album to database because it already exists :(");
		}
	}

	public static void addsSongs(String songname, String albumName) {// inserts new songs
		try {
			PreparedStatement statement = (PreparedStatement) getConnection()
					.prepareStatement("INSERT INTO Songs(songname, albumName) VALUES(?,?)");
			statement.setString(1, songname);// adds songname
			statement.setString(2, albumName);// adds albumName
			statement.executeUpdate();// execute the insert
			statement.close();
			System.out.println("added succesfully!!!:)");
		} catch (Exception e) {
			System.out.println("Could not add song to database because it already exists :(");
		}
	}

	// VIEW METHODS

	public static void viewAlbum() {// fills Albums arrayList with albums from database
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Albums");
			ResultSet result = statement.executeQuery();
			Artist art;
			art.getAlbums().clear();// emptying Albums arraylist to update it
			Album album;
			while (result.next()) {
				album = new Album(result.getString("albumName"), result.getDouble("pricep"), result.getDouble("priced"),
						result.getInt("physical"), result.getInt("digital"));// TODO constructor Album
				art.getAlbums().add(album);// adds new album to the Albums arraylist
			}
			System.out.println("Done!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void viewArtists() {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Artists");
			ResultSet result = statement.executeQuery();
			Manager man;
			man.getArtists().clear();// emptying Artists arraylist to update it
			Artist art;
			while (result.next()) {
				art = new Artist(result.getInt("artistid"), result.getString("username"), result.getString("password"),
						result.getDouble("rating"), result.getDouble("relevancyIndex"),
						findWhichAlbum(result.getInt("artistid")), result.getString("genre"),
						result.getDouble("payPercentage"));
				man.getArtists().add(art);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	

//METHODS TO FIND STAFFS FROM DATABASES TABLES

	public static ArrayList<Album> findWhichAlbum(int artistid) {// finds the album for the artist
		ArrayList<Album> Albums = new ArrayList<Album>();// temp album arraylist
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement(
					"SELECT albumName, pricep,priced,physical,digital  FROM Artists, Albums WHERE artistid.Artist=artistid.Albums");
			ResultSet result = statement.executeQuery();
			Album album;
			while (result.next()) {
				album = new Album(result.getString("albumName"), result.getDouble("pricep"), result.getDouble("priced"),
						result.getInt("physical"), result.getInt("digital"));
				Albums.add(album);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Albums;
	} 

//METHOD TO FILL THE DATABASE TABLES
	
	public static void startInserts() {// adds staffs to the database tables
		addsAlbums("rock", 2.4, 5.86, 345, 4366);
		addsArtists(1,"eminem","123efvwv",213.23,1244.55,"rock",14.4,"RAP GOD");
		addsVenues(6, "ROCK FESTIVAL", 2000 , "ELA POIOS",
				"YOLO", "ROCK FISIKA" , "PANTOYYY NTOY","????", "?????????", 120, "??????????????");
		addsSongs("TA MATOKLADA SOU LAMPOUN", "rock");
	}
	
//CONNECTION METHOD

	public static Connection getConnection() throws Exception {// connecting to database
		try {
			// String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://remotemysql.com:3306/49YiSU5950";
			String username = "49YiSU5950";
			String password = "JCbOC8Tsqd";
			// Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println("Something went wrong :(");
			return null;
		}
	}
}