/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.sql.SQLException;

/**
 *
 * @author patho
 */
public class RegistrationError {
    private String userNameLenError = "";
    private String passwordLenError = "";
    private String confirmNotMatch = "";
    private String lastNameLenError = "";
    private String AccountExisted = "";
    public boolean checkUserNameLen(String userName, int min, int max){
        userName = userName.trim();
        if(userName.length()<min || userName.length()>max){
            userNameLenError =  "Name length must in range " + min + " - " + max;
            return false;
        }return true;
    }
    public boolean checkPasswordLen(String password, int min, int max){
        password = password.trim();
        if(password.length()<min || password.length()>max){
            passwordLenError =  "Name length must in range " + min + " - " + max;
            return false;
        }return true;
    }
    public boolean checkConfirm(String confirm, String password){
        password = password.trim();
        confirm = confirm.trim();
        if(!password.equals(confirm)){
            confirmNotMatch =  "Password not match ";
            return false;
        }return true;
    }
    public boolean checkLastNameLen(String lastLame, int min, int max){
        lastLame = lastLame.trim();
        if(lastLame.length()<min || lastLame.length()>max){
            lastNameLenError =  "Last Name length must in range " + min + " - " + max;
            return false;
        }return true;
    }
    public boolean checkAccountExist(String userName) 
            throws SQLException, ClassNotFoundException{
        userName = userName.trim();
        RegistrationDAO dao = new RegistrationDAO();
        if(dao.CheckAccountExisted(userName)){
            userNameLenError =  "Accout existed";
            return false;
        }return true;
    }

    public String getUserNameLenError() {
        return userNameLenError;
    }

    public String getPasswordLenError() {
        return passwordLenError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public String getLastNameLenError() {
        return lastNameLenError;
    }

    public String getAccountExisted() {
        return AccountExisted;
    }
}
