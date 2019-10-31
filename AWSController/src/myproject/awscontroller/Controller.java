//============================================================================
// Name        : Main.java
// Author      : gitahn59
// Version     : 1.0
// Copyright   : MIT
// Description : Controller class using AWS SDK
//============================================================================

package myproject.awscontroller;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

public class Controller {
	private AmazonEC2 ec2;
	
	public void init() throws Exception{
		ProfileCredentialsProvider profileCredentialsProvider = new ProfileCredentialsProvider();
		try {
			profileCredentialsProvider.getCredentials(); // check credentials 
		}catch(Exception e) {
			// cannot load credentials 
			// throws Exception
			throw new Exception("Cannot load the credentials from the credential profiles file."
					+"Please make sure that your credentials file is at the correct" 
					+"location (~/.aws/credentials), and is invalid format.",e);
		}
		//initialize AmazomEC2
		ec2=AmazonEC2ClientBuilder.standard()
				.withCredentials(profileCredentialsProvider)
				.withRegion("us-east-1")
				.build();
	} 
}
