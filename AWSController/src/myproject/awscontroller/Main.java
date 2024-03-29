//============================================================================
// Name        : Main.java
// Author      : gitahn59
// Version     : 1.0
// Copyright   : MIT
// Description : Main thread and user interface class
//============================================================================

package myproject.awscontroller;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller(); // AWS controller 
		try {
			controller.init(); // initialize AWS controller
		}catch(Exception e) { 
			// There is no credentials file or invalid format
			System.out.println("Cannot load the credentials from the credential profiles file.");
			System.out.println("Please make sure that your credentials file is at the correct location (~/.aws/credentials), ");
			System.out.println("and is invalid format.");
			return; // halt 
		}
		
		Scanner menu = new Scanner(System.in); 
		Scanner id_string= new Scanner(System.in); 
		int menuNum = 0; // menu Number
		String instanceId=""; // AWS instance id
		
		while(true){
			// User Interface
			System.out.println("                                                            ");
			System.out.println("                                                            ");
			System.out.println("------------------------------------------------------------");
			System.out.println("           Amazon AWS Control Panel using SDK               ");
			System.out.println("                                                            ");
			System.out.println("  Cloud Computing, Computer Science Department              ");
			System.out.println("                           at ChungbukNational University  ");
			System.out.println("------------------------------------------------------------");
			System.out.println("  1. list instance                2. available zones         ");
			System.out.println("  3. start instance               4. available regions      ");
			System.out.println("  5. stop instance                6. create instance        ");
			System.out.println("  7. reboot instance              8. list images            ");
			System.out.println("                                 99. quit                   ");
			System.out.println("------------------------------------------------------------");
			System.out.print("Enter an integer: ");
			
			menuNum = Integer.parseInt(menu.nextLine()); // input menu Number
			
			switch(menuNum) {
				case 1: 
					try {
						controller.listInstances(); // print instances
					}catch(Exception e) { 
						System.out.println("Cannot list instances.");
						System.out.println("Please check your key.");
					}
					break;
				case 2: 
					System.out.println("Available zones....");
					int size = controller.listAvailableZones();
					System.out.printf("You have access to %d Availability Zones.",size);
					break;
				case 3: 
					System.out.print("Enter instance id: ");
					instanceId = id_string.nextLine(); // input instance id
					System.out.println("Starting .... "+instanceId);
					try {
						controller.startInstance(instanceId); // start instance
					}catch(Exception e) { // invalid id
						System.out.println("Cannot start instance.");
						System.out.println("Please check your instance id.");
						continue; // continue loop
					}
					System.out.println("Successfully started instance "+instanceId);
					break;
				case 4: 	
					System.out.println("Available regions....");
					controller.listAvailableRegions();
					break;
				case 5: 
					System.out.print("Enter instance id: ");
					instanceId = id_string.nextLine(); // input instance id
					System.out.println("Stopping .... "+instanceId);
					try {
						controller.stopInstance(instanceId); // stop instance
					}catch(Exception e) { // invalid id
						System.out.println("Cannot stop instance.");
						System.out.println("Please check your instance id.");
						continue; // continue loop
					}
					System.out.println("Successfully stopped instance "+instanceId);
					break;
				case 6: 
					System.out.print("Enter instance ami id: ");
					String amiId = id_string.nextLine(); // input AMI id
					instanceId = controller.createInstance(amiId);
					System.out.printf("Successfully started EC2 instance %s based on AMI %s ",instanceId, amiId);
					System.out.println();
					break;
				case 7: 
					System.out.print("Enter instance id: ");
					instanceId = id_string.nextLine(); // input instance id
					System.out.println("Rebooting .... "+instanceId);
					try {
						controller.rebootInstance(instanceId); // reboot instance
					}catch(Exception e) { // invalid id
						System.out.println("Cannot reboot instance.");
						System.out.println("Please check your instance id.");
						continue; // continue loop
					}
					System.out.println("Successfully rebooted instance "+instanceId);
					break;
				case 8: 	
					System.out.println("Listing images....");
					controller.listImages();
					break;
				case 99:   
					return; // halt
				default: 
					System.out.println("Wrong integer");
			}
		}
	}
}
