/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordbank;


import java.io.FileReader;


import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import java.util.Scanner;

public class ReadWriteJSON {
    
    private static JSONArray users;
    public ReadWriteJSON() throws Exception {
        users = openJSONFile();
    }
    
////  TESTING
//    public static void main (String args[]) throws Exception{ 
//        ReadWriteJSON obj = new ReadWriteJSON();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter Email: ");
//
//        String email = scan.nextLine();
//        System.out.println("Enter Password: ");
//        String pass = scan.nextLine();
//
//        boolean b = validateUser(email, pass);
//        if (b) {
//            System.out.println("Email and password Valid");
//            System.out.println(getUserType(email));
//        } else {
//            System.out.println("Email and password invalid");
//        }
//
//    }
    
    public static boolean validateUser(String email, String password){
        
        for(int i = 0; i < users.size(); i++){
            String emailJSON= parseUserObject((JSONObject) users.get(i), "email");
            if (email.equals(emailJSON)) {
                String passJSON = parseUserObject((JSONObject) users.get(i), "password");
                return password.equals(passJSON);
            }
        }
        
        return false;
    }
    
    public static String getUserType(String email){
        for(int i = 0; i < users.size(); i++){
            String emailJSON= parseUserObject((JSONObject) users.get(i), "email");
            if (email.equals(emailJSON)) {
                return parseUserObject((JSONObject) users.get(i), "user-type");
            }
        }
        
        return "user not found";
    }
    
    private static JSONArray openJSONFile() throws Exception {
        JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader(System.getProperty("user.dir") + "\\src\\passwordbank\\src\\data.json"));
        return (JSONArray) obj.get("users");
    }
    
    private static String parseUserObject(JSONObject user, String component) 
    {
        //Get Component
        String comp = (String) user.get(component);     
        return comp;
    }
    
}
