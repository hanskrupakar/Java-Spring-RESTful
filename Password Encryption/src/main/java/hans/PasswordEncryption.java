package hans;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Scanner;

public class PasswordEncryption{

	public static void main(String args[]) throws Exception {
		Scanner scan = new Scanner(System.in);
		String p;
		System.out.print("Enter Password to Encrypt: ");
		p=scan.next();
	  	String cryptedPassword = new BCryptPasswordEncoder(5).encode(p); 
	  	//5 - LOGROUNDS (ranges from 4-31, default=10) can be changed according to performance requirement and level of encryption
		System.out.println("\nCRYPTED PASSWORD: "+cryptedPassword+"\n");
 	}
}
