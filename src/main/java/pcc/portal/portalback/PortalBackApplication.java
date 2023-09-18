package pcc.portal.portalback;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pcc.portal.portalback.entity.Role;
import pcc.portal.portalback.entity.User;
import pcc.portal.portalback.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class PortalBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalBackApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null,"ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_MANAGER"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(null, "Sup", "pppp","por","por@ku.th","1234","0986185361",new ArrayList<>()));
////            userService.saveUser(new User(null, "test1", "test1","test1@ku.th","1234",new ArrayList<>()));
////            userService.saveUser(new User(null, "test2", "test2","test2@ku.th","1234",new ArrayList<>()));
////            userService.saveUser(new User(null, "test3", "test3","test3@ku.th","1234",new ArrayList<>()));
//
//            userService.addRoleToUser("por","ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("por","ROLE_ADMIN");
//            userService.addRoleToUser("por","ROLE_USER");
////            userService.addRoleToUser("test1","ROLE_USER");
////            userService.addRoleToUser("test2","ROLE_MANAGER");
////            userService.addRoleToUser("test3","ROLE_MANAGER");
//
//        };
//    }

}

