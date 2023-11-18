package hu.bankblaze.bankblaze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankBlazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBlazeApplication.class, args);

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode("jelszo");
		System.out.println(encodedPassword);


	}

}
