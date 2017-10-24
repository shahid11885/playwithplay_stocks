package services;

import com.etrade.etws.account.Account;
import com.etrade.etws.account.AccountListResponse;
import com.etrade.etws.oauth.sdk.client.IOAuthClient;
import com.etrade.etws.oauth.sdk.client.OAuthClientImpl;
import com.etrade.etws.oauth.sdk.common.Token;
import com.etrade.etws.sdk.client.ClientRequest;
import com.etrade.etws.sdk.client.Environment;
import com.etrade.etws.sdk.common.ETWSException;

import java.io.IOException;

public class EtradeRequestTokenService {
    public IOAuthClient  client             = null;
    public ClientRequest request            = null;
    public Token token                      = null;
    public String oauth_consumer_key        = null;                     // Your consumer key
    public String oauth_consumer_secret     = null;                     // Your consumer secret
    public String oauth_request_token       = null;                     // Request token
    public String oauth_request_token_secret= null;                     // Request token secret

    public EtradeRequestTokenService() throws IOException, ETWSException {
        client      			= OAuthClientImpl.getInstance();// Instantiate IOAUthClient
        request     			= new ClientRequest();          // Instantiate ClientRequest
        request.setEnv(Environment.SANDBOX);                            // Use sandbox environment
    
        request.setConsumerKey(oauth_consumer_key);                     // Set consumer key
        request.setConsumerSecret(oauth_consumer_secret);               // Set consumer secret
        token                       	= client.getRequestToken(request);  // Get request-token object
        oauth_request_token         	= token.getToken();             // Get token string
        oauth_request_token_secret  	= token.getSecret();            // Get token secret
    }
}
