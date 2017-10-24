package services;

import com.etrade.etws.account.Account;
import com.etrade.etws.account.AccountListResponse;
import com.etrade.etws.oauth.sdk.client.IOAuthClient;
import com.etrade.etws.oauth.sdk.client.OAuthClientImpl;
import com.etrade.etws.oauth.sdk.common.Token;
import com.etrade.etws.sdk.client.AccountsClient;
import com.etrade.etws.sdk.client.ClientRequest;
import com.etrade.etws.sdk.client.Environment;
import com.etrade.etws.sdk.common.ETWSException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static java.awt.Desktop.getDesktop;


public class EtradeTestService {
    public IOAuthClient  	client           		= null;
    public ClientRequest 	request          		= null;
    public Token 		token                     	= null;
    public String 		oauth_consumer_key        	= null;                 // Your consumer key
    public String 		oauth_consumer_secret     	= null;                 // Your consumer secret
    public String 		oauth_request_token       	= null;                 // Request token
    public String 		oauth_request_token_secret	= null;                 // Request token secret
    public String 		authorizeURL 			= null;
    public URI 			uri				= null;
    
    public String 		oauth_access_token 		= null; 		// Variable to store access token
    public String 		oauth_access_token_secret 	= null; 		// Variable to store access token secret
    public String 		oauth_verify_code 		= "Your verification_code"; // Should contain the Verification Code received from the authorization step
    
    
    public static void main(String args[]) {
        if (args.length != 2) {
            System.err.print("Usage: java EtradeTestService oauth_consumer_key oauth_consumer_secret");
            System.exit(-1);
	}
	
    	// Grab the consumer-key and consumer-secret from command line
	EtradeTestService	etrade = new EtradeTestService(args);
	try {
	    etrade.testconnections();
	} catch (IOException e) {
	    System.out.print(e);
	    e.printStackTrace();
	} catch (ETWSException e) {
	    System.out.print(e);
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	    System.out.print(e);
	    e.printStackTrace();
	}
    }
    
    public EtradeTestService(String args[]) {
        this.oauth_consumer_key		= args[0];
        this.oauth_consumer_secret	= args[1];
    }
    
    public void  testconnections() throws IOException, ETWSException, URISyntaxException {
    
        //
	// Obtain a request token
	//
	client      			= OAuthClientImpl.getInstance();// Instantiate IOAUthClient
	request     			= new ClientRequest();          // Instantiate ClientRequest
	request.setEnv(Environment.LIVE);                            // Use sandbox environment
 
	request.setConsumerKey(oauth_consumer_key);                     // Set consumer key
	request.setConsumerSecret(oauth_consumer_secret);               // Set consumer secret
	token                       	= client.getRequestToken(request);  // Get request-token object
	oauth_request_token         	= token.getToken();             // Get token string
	oauth_request_token_secret  	= token.getSecret();            // Get token secret
	
	request.setToken(oauth_request_token); 				// Set request token
	request.setTokenSecret(oauth_request_token_secret); 		// Set request-token secret
	//
	// Obtain a verification code
	//
	authorizeURL 	= client.getAuthorizeUrl(request); 		// E*TRADE authorization URL
	uri 		= new java.net.URI(authorizeURL);
	Desktop desktop = getDesktop();
	desktop.browse(uri);
 
	oauth_verify_code		= getOAuthVerifyCode();		// Command line input ?
	request.setVerifierCode(oauth_verify_code); 			// Set verification code
 
	// Get access token
	token 				= client.getAccessToken(request); // Get access-token object
	oauth_access_token 		= token.getToken(); 		// Access token string
	oauth_access_token_secret 	= token.getSecret(); 		// Access token secret
	
	
	/* Retrieve a list of accounts */
	request = new ClientRequest(); // Instantiate ClientRequest
	// Prepare request
	request.setEnv(Environment.LIVE);
	request.setConsumerKey(oauth_consumer_key);
	request.setConsumerSecret(oauth_consumer_secret);
	request.setToken(oauth_access_token);
	request.setTokenSecret(oauth_access_token_secret);
 
	//oauth_verify_code		= getOAuthVerifyCode();		// Command line input ?
	request.setVerifierCode(oauth_verify_code); 			// Set verification code
 
	try
	{
	    AccountsClient account_client = new AccountsClient(request);
	    AccountListResponse response = account_client.getAccountList();
	    List alist = response.getResponse();
	    Iterator<Account> al = alist.iterator();
	    while (al.hasNext()) {
		Account a = al.next();
	 
		System.out.println("===================");
		System.out.println("Account: " + a.getAccountId());
		System.out.println("===================");
	    }
	}
	catch (Exception e) {
	
	}
    }
    
    
    public String getOAuthVerifyCode() {
	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter Verify Code as provided by E*Trade Redirect: ");
	String verifyCode = scanner.next();
	return verifyCode;
    }
}
