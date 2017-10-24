package services;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.etrade.etws.oauth.sdk.client.IOAuthClient;
import com.etrade.etws.sdk.client.ClientRequest;
import com.etrade.etws.sdk.common.ETWSException;

import static java.awt.Desktop.*;

public class EtradeAuthorizeService {
    String 	authorizeURL 	= null;
    URI		uri		= null;
    
    public EtradeAuthorizeService(IOAuthClient client, ClientRequest request) throws 	URISyntaxException,
	    										IOException,
	    										ETWSException {
        authorizeURL 	= client.getAuthorizeUrl(request); 		// E*TRADE authorization URL
        uri 		= new java.net.URI(authorizeURL);
        Desktop desktop = getDesktop();
        desktop.browse(uri);
    }
}

