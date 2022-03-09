package advancedchess;

import advancedchess.GameLogs.GameLog;
import advancedchess.GameState.GameState;
import advancedchess.GameState.GameStateRepository;
import advancedchess.User.User;
import advancedchess.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Main {
    public static void main(String args[]){

        SpringApplication.run(Main.class, args);
    }




/*
    @Bean
    CommandLineRunner initBoard(UserRepository userRepository){
        return args -> {

            User user1 = new User("John", "johnspassword");
            User user2 = new User("Jane", "janespassword");
            User user3 = new User("Justin", "justinspassword");
            user1.setLeaderBoard();
            user2.setLeaderBoard();
            user3.setLeaderBoard();
            user1.addGameLog(new GameLog(452021, "You won this match!"));
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);


        };

    }


/*
    @Bean
    CommandLineRunner initBoard(GameStateRepository gameStateRepository){
        return args -> {



            GameState board1 = new GameState("firstBoard","rnbkqbnrpppppppp--------------------------------pppppppprnbkqbnr", 111111);
            gameStateRepository.save(board1);
            GameState board2 = new GameState("aaasecondBoard","rnbkqbnrpppppppp--------------------------------pppppppprnbkqbnr", 101010);
            gameStateRepository.save(board2);


        };

    }
*/


@Bean
public Docket getAPIdocs(){
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
}



}
