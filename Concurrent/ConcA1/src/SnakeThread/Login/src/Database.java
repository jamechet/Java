package SnakeThread.Login.src;



import org.mapdb.*;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 * Created by Anthony on 16/05/2015.
 */


public class Database extends Login {

    DB db;

    ConcurrentNavigableMap<String, Player> playerMap;

    public Database() {
        // configure and open database using builder pattern.
        // all options are available with code auto-completion.
        db = DBMaker.newFileDB(new File("playerDB"))

                .closeOnJvmShutdown()

                .encryptionEnable("password")

                .make();

        playerMap = db.getTreeMap("Players");
    }

    public void addPlayer(Player p) {
        playerMap.put(p.getUsername(), p);
        db.commit();
    }

    public void addPlayer(String username, String password) {
        playerMap.put(username, new Player(username, password));
        db.commit();
    }

    /**
     * @param userName The players username.
     * @param login2 The players password.
     * @return The player specified. Returns null if player coudn't be found or if password was incorrect.
     */


    /**
     * Created by Anthony on 16/05/2015.
     */
    class Player implements Serializable {


		private String username;
		private String password;

		public Player (String username, String password) {

            this.username = username;
            this.password = password;

        }

        public String getPassword() {
        	return passwordField.getText().trim();
        }
        
    	private String getUsername() {
    		return usernameField.getText().trim();
    	}

        }
    }






