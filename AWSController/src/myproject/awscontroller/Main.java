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
		Scanner menu = new Scanner(System.in); 
		Scanner id_string= new Scanner(System.in); 
		int menuNum = 0; // menu Number
		String instanceId; // AWS instance id
		
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
				case 1: break;
				case 2: break;
				case 3: break;
				case 4: break;
				case 5: break;
				case 6: break;
				case 7: break;
				case 8: break;
				case 99:   
					return; // halt
				default: 
					System.out.println("Wrong integer");
			}
		}
	}
}
