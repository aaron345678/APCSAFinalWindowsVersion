import java.util.*;
/**
 * Write a description of class EditNames here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EditNames
{
    // instance variables - replace the example below with your own
    public String movedLetter(String name) {
        String finished = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        for(int i = 0; i<name.length(); i++) {
            String currentChar = name.substring(i,i+1).toLowerCase();
            int index = alphabet.indexOf(currentChar) + 2;
            if(index>=26) {
                index = index-26;
            }
            if(alphabet.indexOf(currentChar)!=-1 && capAlphabet.indexOf(currentChar) == -1) {
                finished+=alphabet.substring(index,index+1);
            } else if(currentChar.equals(" ")){
                finished+="@";
            } else {
                finished+=currentChar;
            }
        }
        return finished;
    }


    public String demoveLetter(String name) {
        String finished = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i<name.length(); i++) {
            String currentChar = name.substring(i,i+1).toLowerCase();
            int index = alphabet.indexOf(currentChar) - 2;
            if(index<0) {
                index = 26+index;
            }
            if(currentChar.equals("@")) {
                finished+=" ";//alphabet.substring(index,index+1);
            } else if(alphabet.indexOf(currentChar)!=-1){
                finished+=alphabet.substring(index,index+1);
            } else {
                finished+=currentChar;
            }
        }
        return finished;
    }
    public ArrayList<String> encrypt(String[] namesList) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i<namesList.length; i++) {
            String scrambledName = movedLetter(namesList[i]);
            list.add(scrambledName);
        }
        return list;
    }

    public ArrayList<String> decrypt(String[] namesList) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i<namesList.length; i++) {
            String scrambledName = demoveLetter(namesList[i]);
            list.add(scrambledName);
        }
        return list;
    }
}