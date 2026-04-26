package es.ubu.sd.p2;

import es.ubu.sd.p2.model.Role;
import es.ubu.sd.p2.model.User;
import es.ubu.sd.p2.repository.RoleRepository;
import es.ubu.sd.p2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase principal que inicializa y arranca la aplicación Spring Boot.
 *
 * * @author José Luis Petisco Rodríguez
 * @version 1.0
 */
@SpringBootApplication
public class BasicoApplication {

	/**
	 * Punto de entrada principal de la aplicación.
	 *
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BasicoApplication.class, args);
	}

	/**
	 * Inicializa la base de datos con un usuario administrador por defecto en caso de que la tabla de usuarios esté
	 * vacía.
	 *
	 * @param userRepository  Repositorio para la gestión y persistencia de usuarios.
	 * @param roleRepository  Repositorio para la gestión y persistencia de roles.
	 * @param passwordEncoder Codificador para encriptar la contraseña de forma segura.
	 * @return Un {@link CommandLineRunner} que se ejecuta al levantar el contexto de Spring.
	 */
	@Bean
	public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository,
									  PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.count() == 0) {

				Role adminRole = new Role();
				adminRole.setRoleName("ROLE_ADMIN");
				adminRole.setShowOnCreate(1);
				roleRepository.save(adminRole);

				User adminUser = new User();
				adminUser.setUsername("admin");
				adminUser.setPassword(passwordEncoder.encode("admin"));
				adminUser.setUserRole(adminRole);
				userRepository.save(adminUser);

				System.out.println("=============================================");
				System.out.println("USUARIO POR DEFECTO CREADO: admin / admin");
				System.out.println("=============================================");
			}
		};
	}
}