package Models;

import java.util.ArrayList;
import java.sql.*;


public class FilmDAO {
	//sets the connection details, note the username and password are default but would be changed should this be a private db
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "root";
	String db = "films";
    String password = "###";
    //connects to cloud database at the ip shown, connects to the specific db called films
    String url = "###"+db;
  //  java.sql.Driver d=new com.mysql.jdbc.Driver();

	

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
			
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	//Function for getting all the films
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		System.out.println("Open Connection");//SOUT for debugging purposes
		// Create select statement and execute it
		
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    System.out.println(selectSQL);//SOUT for debugging purposes
		  
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		    	
		   }
		    stmt.close();
		    closeConnection();
		   
		} catch(SQLException se) { System.out.println(se); }
	   System.out.println(allFilms);//SOUT for debugging purposes
	   return allFilms;
	   
   }
   
   //Function for search for films via ID
   public Film getFilmByID(int id){
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);//SOUT for debugging purposes
		    System.out.println(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
		System.out.println(oneFilm);
	   return oneFilm;
   }
   //Function for getting film by name
   public ArrayList<Film> getFilmByName(String title){
	   
	   
		ArrayList<Film> allFilmNames = new ArrayList<Film>();
		openConnection();
		System.out.println("Open Connection");//SOUT for debugging purposes
		// Create select statement and execute it
		try{
			//sql query to select from films where the title matches or partially matches what has been entered
		    String selectSQL = "select * from films where title like '" + title + "%'";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    System.out.println(selectSQL);//SOUT for debugging purposes
		  
		    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilmNames.add(oneFilm);
		    	
		   }
		    stmt.close();
		    closeConnection();
		   
		} catch(SQLException se) { System.out.println(se); }
	   return allFilmNames;
	   
  }
   
   //Function for inserting films
   public boolean insertFilm(Film i)throws SQLException{
	   openConnection();
	   boolean bool = false;
	   
   //Inserts the entered fileds into the DB via the insertSQL query
   try {String insertSQL = "INSERT INTO films VALUES("+ i.getId() + ",'" + i.getTitle() + "'," + i.getYear() + ",'" + i.getDirector() + "','" +  i.getStars() + "','"+  i.getReview() + "');";
	   stmt.executeUpdate(insertSQL);
	   System.out.println(insertSQL);//SOUT for debugging purposes
   }
   finally {
	   if(stmt!=null) {
		   stmt.close();}
	   }
   return bool;
}
   //function for updating films, updates films where the id = entered id
   public boolean updateFilm(Film i)throws SQLException{
	   openConnection();
	   boolean bool = false;
	   
	//updates the entered fileds into the DB via the insertSQL query if the ID matches an existing entry
   try {String insertSQL = "UPDATE films SET id ='"+ i.getId() + "',title='" + i.getTitle() + "',year=" + i.getYear() + ",director='" + i.getDirector() + "',stars='" +  i.getStars() + "',review='"+  i.getReview() +"'  WHERE id = "+ i.getId() +";"; 
	   stmt.executeUpdate(insertSQL);
	   System.out.println(insertSQL);//SOUT for debugging purposes
   }
   finally {
	   if(stmt!=null) {
		   stmt.close();}
	   }
   return bool;
   }
   //Function for deleting films
   public boolean delFilm(int id) throws SQLException{
	   openConnection();
	   boolean bool = false;
	 //deletes the film entered by the user if that ID matches an entry in the DB
	try {
		String deleteSQL = "DELETE FROM films WHERE ID="+id;
		stmt.executeUpdate(deleteSQL);
		System.out.println(deleteSQL);//SOUT for debugging purposes
		}
		catch(SQLException se) { System.out.println(se); }
			finally {
			}
				if( stmt!=null) {
					stmt.close();}
	
	return bool;	
   }  
}