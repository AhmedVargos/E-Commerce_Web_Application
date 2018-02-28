/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apicompany.e.commerceapplication.view.utility;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David Emil
 */
public class Validation {

    private final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-.]+([A-Za-z0-9-_.]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,})$");
    private final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z[ ]{0,1}]{3,20}$");
    private Matcher patternMatcher;

    /**
     * check if the string is empty or not
     *
     * @param string
     * @return - true if empty <br>
     * - false if not 
     *
     */
    public boolean isEmptyString(String string) {
        boolean emptyString = false;
        if (string.trim().length() == 0 || string.equals(null) || "".equals(string.trim())) {
            emptyString = true;
        }
        return emptyString;
    }

    /**
     * check if the email is valid or not
     *
     * @param email
     * @return - true if valid <br>
     * - false if not 
     *
     */
    public boolean isEmail(String email) {
        patternMatcher = EMAIL_PATTERN.matcher(email);
        return !isEmptyString(email) && patternMatcher.matches();
    }

    /**
     * check if the name is valid or not
     *
     * @param name
     * @return - true if valid <br>
     * - false if not 
     *
     */
    public boolean isName(String name) {
        patternMatcher = NAME_PATTERN.matcher(name);
        return !isEmptyString(name) && patternMatcher.matches();
    }
    
    /**
    * check if the password is valid or not, it should be more than or equal 8 and less than or equal 30 
    * @param password 
    * @return - true if valid <br> 
    *          - false if not 
    **/
    public boolean isValidPassword(String password) {
        boolean validPass = false;
        if (password.length() >= 8 && password.length() <= 30) {
            validPass = true;
        }
        return validPass;
    }
    
    /**
     * check if the name is valid or not
     *
     * @param password1
     * @param password2
     * @return - true if password1 equals password2 <br>
     * - false if not 
     *
     */
    public boolean isPasswordMatches(String password1, String password2) {
        boolean passwordMatch = false;
        if (password1.equals(password2)) {
            passwordMatch = true;
        }
        return passwordMatch;
    }
    
    /**
    * check if the age of the user is legal or not 
    * @param dateOfBirth 
    * @return - true if age greater than 16 years old <br> 
    *          - false if not 
    **/
    public boolean isLegalAged(LocalDate dateOfBirth){
        boolean legalAge = false;
        if(!isEmptyString(dateOfBirth.toString()) && dateOfBirth.getYear() <= 2002){
            legalAge = true;
        }
    
        return legalAge;
    }
    
}
