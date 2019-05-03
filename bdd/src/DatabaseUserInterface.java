
import java.awt.*;
import java.awt.event.*;
import java.sql.* ;  // for standard JDBC programs
import java.math.* ; // for BigDecimal and BigInteger support

/**
 * This is a skeleton for realizing a very simple database user interface in java. 
 * The interface is an Applet, and it implements the interface ActionListener. 
 * If the user performs an action (for example he presses a button), the procedure actionPerformed 
 * is called. Depending on his actions, one can implement the database connection (disconnection), 
 * querying or insert. 
 * 
 * @author zmiklos
 *
 */
public class DatabaseUserInterface extends java.applet.Applet implements ActionListener {
	String URL = "jdbc:mysql://mysql.istic.univ-rennes1.fr/base_17001420";
	String USER = "user_17001420";
	String PASS = "perig";
	Connection conn;
	Statement stmt = null;
 private TextField mStat, m1, m2, m3,m4,m5,m6,m7,m8,m9;
TextArea mRes;
 private Button b1, b2, b3, b4,b5,b6,b7,b8,b9,b10;
 private static final long serialVersionUID = 1L; 
 
 
 /**
  * This procedure is called when the Applet is initialized.
  * 
  */
 public void init ()
 {    
	 /**
	  * Definition of text fields
	  */
     //m1 = new TextField(80);
     //m1.setText("What are you going to do when the light is:");
     //m1.setEditable(false);
     mStat = new TextField(150);
     mStat.setEditable(false);
     m1 = new TextField(150);
     m2 = new TextField(150);
     m3 = new TextField(150);
     m4 = new TextField(150);
     m5 = new TextField(150);
     m6 = new TextField(150);
     m7 = new TextField(150);
     m8 = new TextField(150);
     m9 = new TextField(150);
     mRes = new TextArea(10,150);
     mRes.setEditable(false);
    
     
     
     /**
      * First we define the buttons, then we add to the Applet, finally add and ActionListener 
      * (with a self-reference) to capture the user actions.  
      */
     b1 = new Button("CONNECT");
     b2 = new Button("DISCONNECT");
     b3 = new Button("QUERY");
     b4 = new Button("INSERT");
     b5 = new Button("consulter tous les livres");
     b6 = new Button("consulter les livres du parcours spécifié");
     b7 = new Button("consulter les livres du module spécifié");
     b8 = new Button("consulter tous les parcours existants");
     b9 = new Button("consulter tous les modules existants");
     b10 = new Button("Supprimer le livre");
     add(b1) ;
     add(b2) ;
     add(b3) ;
     add(b4);
     add(b10);
     add(b5);
     add(b6);
     add(b7);
     add(b8);
     add(b9);
     b1.addActionListener(this);
     b2.addActionListener(this);
     b3.addActionListener(this);
     b4.addActionListener(this);
     b5.addActionListener(this);
     b6.addActionListener(this);
     b7.addActionListener(this);
     b8.addActionListener(this);
     b9.addActionListener(this);
     b10.addActionListener(this);
     add(mStat);
     add(new Label("Input fields: ", Label.CENTER));
     add(m1);
     add(m2);
     add(m3);
     add(m4);
     add(m5);
     add(m6);
     add(m7);
     add(m8);
     add(m9);
     
     m1.setText("Titre livre");  //According to the database schema
     m2.setText("ISBN"); //According to the database schema
     m3.setText("Nom module");  //According to the database schema
     
     m4.setText("Id Prof?");  //According to the database schema
     m5.setText("Mot de passe prof?"); //According to the database schema
     m6.setText("De quel parcours voulez vous voir les livres?");  //According to the database schema

     m7.setText("Que quel module voulez vous voir les livres?");  //According to the database schema
     m8.setText("de quelle année?"); //According to the database schema
     m9.setText("de quel semestre?");  //According to the database schema
     add(new Label("Query results: ", Label.CENTER));
     add(mRes);
     mRes.setText("Query results");
     
     setStatus("Waiting for user actions.");
 }
 
 /**
  * This procedure is called upon a user action.
  * 
  *  @param event The user event. 
  */
  public void actionPerformed(ActionEvent event)
 {
  
	 // Extract the relevant information from the action (i.e. which button is pressed?)
	 Object cause = event.getSource();

	 // Act depending on the user action
	 // Button CONNECT
     if (cause == b1)
     {
			connectToDatabase();
     }
     
     // Button DISCONNECT
     if (cause == b2)
     {
    	 disconnectFromDatabase();
     }
     
     //Button QUERY
     if (cause == b3)
     {
				queryDatabase();
		
     }
     
     //Button INSERT
     if (cause == b4)
     {
			insertDatabase();
 }
     if (cause==b5) {
    	 queryLivres();
     }
     
     if (cause==b6) {
    	 queryLivreParcour();
     }
     
     if (cause==b7) {
    	 queryLivreModule();
     }
     
     if (cause==b8) {
    	 queryParcours();
     }
     
     if (cause==b9) {
    	 queryModules();
    	 
     }
     
     if (cause==b10) {
    	 supprFromDatabase();
     }
     
     }
 

/**
 * Set the status text. 
 * 
 * @param text The text to set. 
 */
private void setStatus(String text){
	    mStat.setText("Status: " + text);
  }
/**
 * Procedure, where the database connection should be implemented. 
 */


private void queryParcours() {  
	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("select * from parcours;");
		String s="";
		while (result.next()) {
			s+="Nom du parcours: " + result.getString("nomParcours")+"\n";
		}
		mRes.setText(s);
		query.close();
		result.close();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}

private void queryModules() {  
	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("select * from module;");
		String s="";
		while (result.next()) {
			s+="Nom du module: " + result.getString("nomModule")+"\n";
		}
		mRes.setText(s);
		query.close();
		result.close();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}


private void connectToDatabase() {
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   System.out.println("Connecting to database...");
		   conn = DriverManager.getConnection(URL,USER,PASS);
		   setStatus("Connected");
		}
	catch(Exception e){
		System.err.println(e.getMessage());
		setStatus("Connection failed");
	}

}


/**
 * Procedure, where the database connection should be implemented. 
 */
private void disconnectFromDatabase(){
	try{
	setStatus("Disconnected from the database");
	conn.close();
	System.out.println("Disconnected from the database bb");
	} catch(Exception e){
		System.err.println(e.getMessage());
		setStatus("Disconnection failed");
	}
}



private void queryLivreModule() {  
	String module=m7.getText();
	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("SELECT * FROM module NATURAL JOIN module_has_livre NATURAL JOIN livre WHERE module.nomModule='"+module+"';");
		String s="";
		while (result.next()) {
			s+="name: " + result.getString("nomLivre")+", ISBN: "+result.getString("isbn")+"\n";
		}
		mRes.setText(s);
		query.close();
		result.close();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}

private void queryLivreParcour() {  
	String parcour=m6.getText();
	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("select * from livre natural join module_has_livre natural join module "
				+ "natural join module_has_parcours natural join parcours where parcours.nomParcours='"+parcour+"';");
		String s="";
		while (result.next()) {
			s+="name: " + result.getString("nomLivre")+", ISBN: "+result.getString("isbn")+"\n";
			
		}
		mRes.setText(s);

		query.close();
		result.close();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}

private void queryLivres() {  
	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("select * from livre;");
		String s="";
		while (result.next()) {
			s+="name: " + result.getString("nomLivre")+", ISBN: "+result.getString("isbn")+"\n";
		}
		mRes.setText(s);
		query.close();
		result.close();
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}

/**
 * Execute a query and display the results. Implement the database querying and the 
 * display of the results here 
 */
private void queryDatabase() {  

	setStatus("Querying the database");
	try {
		Statement query=conn.createStatement();
		ResultSet result= query.executeQuery("select * from personne;");
		String s="";
		while (result.next()) {
			s+="name: " + result.getString("name")+", age: "+result.getString("age")+", color: "+result.getString("color")+"\n";
		}
		mRes.setText(s);
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
		setStatus("Querying failed");
	}
}
private void supprFromDatabase() {
try {
	String titre = m1.getText();
	String isbn = m2.getText();
	String module = m3.getText();

	String id = m4.getText();
	String mdp= m5.getText();
	Statement query=conn.createStatement();
	ResultSet result= query.executeQuery("select * from Compte where Compte.Identifiant="+id+";");
	String s="";
	while(result.next()) {
	s = s+result.getString("Mot_de_passe");
	}

	if (s.equals(mdp)) {
		String sql1="delete from module_has_livre where livre_isbn='"+isbn+"';";
		PreparedStatement suppr1=conn.prepareStatement(sql1);
		suppr1.execute();
		String sql="delete from livre where nomLivre='"+titre+"' and isbn='"+isbn+"';";
		PreparedStatement suppr=conn.prepareStatement(sql);
		suppr.execute();
	}
}
catch(Exception e) {

	System.out.println(e.getMessage());
	setStatus("Insertion failed");
}
}
/**
 * Insert tuples to the database. 
 * @throws SQLException 
 */
private void insertDatabase() {
try {
	System.out.println("Inserting records into the table...");
	String titre = m1.getText();
	String isbn = m2.getText();
	String module = m3.getText();
	
	String id = m4.getText();
	String mdp= m5.getText();
	Statement query=conn.createStatement();
	ResultSet result= query.executeQuery("select * from Compte where Compte.Identifiant="+id+";");
	String s="";
	while(result.next()) {
	s = s+result.getString("Mot_de_passe");
	}

	if (s.equals(mdp)) {
	String sql="insert into livre values(?,?)";
	PreparedStatement insert=conn.prepareStatement(sql);
	insert.setString(1,isbn);
	insert.setString(2, titre);
	insert.execute();
	
	String sql2="insert into module_has_livre values(?,?)";
	PreparedStatement insert2=conn.prepareStatement(sql2);
	insert2.setString(2,isbn);
	ResultSet idmod=query.executeQuery("select * from module where module.nomModule='"+module+"';");
	int cherche=7;
	while(idmod.next()) {
	cherche = idmod.getInt("idModule");
	}
	System.out.println(cherche);
	insert2.setInt(1, cherche);
	insert2.execute();
	
	setStatus("\"Inserting --( \" + name + \", \" + surname + \", \" + id + \" )-- to the database\"\n" + "");
	}
	
	if (s!=mdp) {System.out.println("Identifiant ou mdp incorrect");}
}
catch(Exception e) {
	System.out.println(e.getMessage());
	setStatus("Insertion failed");
}
}
}
