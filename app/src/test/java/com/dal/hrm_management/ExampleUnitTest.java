package com.dal.hrm_management;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkNotNullEmail() throws IllegalArgumentException{
        Login email=new Login();
        email.setEmail(null);
        assertTrue(email.checkNotNullEmail());

    }

    @Test
    public void checkEmailContainA(){
        Login email=new Login();
        email.setEmail("ssss@");
        assertTrue(email.checkEmailContainA());
    }

    @Test
    public void checkEmailContainSpace(){
        Login email=new Login();
        email.setEmail("ssss ");
        assertTrue(email.checkEmailContainSpace());
    }

    @Test
    public void validatorEmail(){
        Login email=new Login("test@gmail.com");
    }

    @Test
    public void checkPasswordNotNull(){
        Login email = new Login();
        email.setPassword("sadasfda");
        assertTrue(email.checkPasswordNotNull());
    }

    @Test
    public void checkPasswordContainSpecialChar(){
        Login email =  new Login();
        email.setPassword("*sghffg");
        assertTrue(email.checkEmailContainSpecialChar());
    }

    @Test
    public void checkEmailOrPasswordNull(){
        Login login = new Login("asfdgfasf","");
        assertTrue(!login.checkEmailOrPassNull());
    }
    @Test
    public void kiemTraEmailHopLe(){
        Login login = new Login("hihi@gmail.com");
        assertTrue(login.kiemTraEmailHopLe());
    }
}