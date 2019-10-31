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
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StartInstancesRequest;

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
	
	public void listInstances() {
		System.out.println("Listing instances....");
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		
		while(true) {
			DescribeInstancesResult response = ec2.describeInstances(request);
			for(Reservation reservation : response.getReservations()) {
				for(com.amazonaws.services.ec2.model.Instance instance: reservation.getInstances()) {
					//Print instances information
					System.out.printf("[id] %s, " +"[AMI] %s, " +"[type] %s, " +"[state] %10s, " +"[monitoring state] %s",
							instance.getInstanceId(),
							instance.getImageId(),
							instance.getInstanceType(),
							instance.getState().getName(),
							instance.getMonitoring().getState());
					}
				System.out.println();
			}
			
			request.setNextToken(response.getNextToken()); // get next token
			if(response.getNextToken() == null) { 		   // if next token is null
				break; 							 		   // stop loop
			}
		}
	}
	
	public void startInstance(String id) throws Exception{
		StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(id);
		try {
			ec2.startInstances(request); // start instance
		}
		catch(Exception e) { // invalid id
			throw new Exception("Cannot start instance"
					+"Please check your instance id" ,e);
		}		
	}
	
}
