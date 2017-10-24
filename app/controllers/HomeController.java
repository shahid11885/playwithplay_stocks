package controllers;

import model.Account;
import model.Login;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import play.Logger;

import views.html.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    FormFactory formFactory;
    
    
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
      //  return ok(index.render("Your new application is ready."));
        return ok(login.render("Your new application is ready."));
    
    }
    
    public Result login() {
	Form<Login> loginForm = formFactory.form(Login.class);
	Login loginPojo	= loginForm.bindFromRequest().get();
	
	Logger.debug(loginPojo.toString());
	Account[] accts	= new Account[3];
	int c = 0;
	accts[c++]	= new Account("1", "shahid", 6317.22);
	accts[c++]	= new Account("2", "shahifi", 63417.22);
	accts[c++]	= new Account("3", "ajshd", 63127.22);
	
	return ok(accounts.render(accts));
	
    }
}
