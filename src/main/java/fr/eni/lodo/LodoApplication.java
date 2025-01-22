package fr.eni.lodo;

import fr.eni.lodo.models.User;
import fr.eni.lodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class LodoApplication {


	public static void main(String[] args) {
		SpringApplication.run(LodoApplication.class, args);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		System.out.println(encoder.encode("coucou"));
		System.out.println(List.of("CLIENT"));
//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//
//		User user1 = new User();
//		user1.setUsername("client");
//		user1.setPassword(encoder.encode("azerty"));
//		user1.setRoles(List.of("CLIENT"));
//
//		User user2 = new User();
//		user2.setUsername("employer");
//		user2.setPassword(encoder.encode("azerty"));
//		user2.setRoles(List.of("EMPLOYER"));
//
//		String sql =    "INSERT INTO public.users(username, password, roles) " +
//				"VALUES (?, ?, ?);";
//		jdbcTemplate.update(sql, user1.getUsername(), user1.getPassword(), user1.getRoles());
//		jdbcTemplate.update(sql, user2.getUsername(), user2.getPassword(), user2.getRoles());
	}

}
