package advancedchess.WebSocket;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@ServerEndpoint("/websocket/{username}")
@Component

public class WebSocketServer {
    // Store all socket session and their corresponding username.
    private static Map < Session, String > sessionUsernameMap = new Hashtable < > ();
    private static Map < String, Session > usernameSessionMap = new Hashtable < > ();

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username)
            throws IOException {
        logger.info("Entered into Open");

        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

        String message = "User:" + username + " has Joined the Chat";
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String username = sessionUsernameMap.get(session);


          if(profanityFilter(message) == true){
              String censoredMessage = "";
              for(int i = 0; i < message.length(); i++){
                  censoredMessage += "*";
              }
              broadcast(username + ": " + censoredMessage);
          }else {
              message = getArt(message);
              broadcast(username + ": " + message);
          }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        String message = username + " disconnected";
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
    }

//    private void sendMessageToPArticularUser(String username, String message) {
//        try {
//            usernameSessionMap.get(username).getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            logger.info("Exception: " + e.getMessage().toString());
//            e.printStackTrace();
//        }
//    }

    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }
//This comment is for testing purposes
    private boolean profanityFilter(String message){
        //Use naughtyword for demonstration purposes
        ArrayList<String> swearList = new ArrayList<String>(Arrays.asList("poop", "naughtyword", "naughtyword2", "ass", "arse", "shit", "wtf", "stupid"));
        for(int i = 0; i < swearList.size(); i++){
            if(message.toLowerCase().equals(swearList.get(i))){
                return true;
            }
        }
        return false;
    }

    private String getArt(String message){

      /*  |\---/|
        | o_o |
         \_^_/ */

        String stBuilder = "\n";
        if(message.equals(":cat:")){
            stBuilder += "        |\\---/|\n";
            stBuilder += "        | o_o |\n";
            stBuilder += "         \\_^_/\n";

        }
        else if(message.equals(":)")){

   /*   _.-'''''-._
    .'  _     _  '.
   /   (_)   (_)   \
  |  ,           ,  |
  |  \`.       .`/  |
   \  '.`'""'"`.'  /
    '.  `'---'`  .'
      '-._____.-' */



            stBuilder +="      _.-'''''-._\n";
            stBuilder +="    .'  _     _  '.\n";
            stBuilder +="   /   (_)   (_)   \\\n";
            stBuilder +="  |  ,           ,  |\n";
            stBuilder +="  |  \\`.       .`/  |\n";
            stBuilder +="   \\  '.`'\"\"'\"`.'  /\n";
            stBuilder +="    '.  `'---'`  .'\n";
            stBuilder +="      '-._____.-'\n";
        }
        else {
            stBuilder = message;
        }

        return stBuilder;

    }



}

/*
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

 *
 * @author Vamsi Krishna Calpakkam
 *

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {

    // Store all socket session and their corresponding username.
//    private static Map < Session, String > sessionUsernameMap = new Hashtable < > ();
//    private static Map < String, Session > usernameSessionMap = new Hashtable < > ();
//
//    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username)
            throws IOException {
        logger.info("Entered into Open");

        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);

        String message = "User:" + username + " has Joined the Chat";
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String username = sessionUsernameMap.get(session);

        if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
        {
            String destUsername = message.split(" ")[0].substring(1); // don't do this in your code!
            sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
            sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
        } else // Message to whole chat
        {
            broadcast(username + ": " + message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);

        String message = username + " disconnected";
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
    }

    private void sendMessageToPArticularUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }
}
*/
//test2