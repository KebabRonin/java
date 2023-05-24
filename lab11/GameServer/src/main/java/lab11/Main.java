package lab11;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
public class Main {

    public static void old_main ( String [] args ) {
        GameServer server = new GameServer();
    }

    @RestController
    @SpringBootApplication
    public class MyApplication {

        @RequestMapping("/")
        String home() {
            return "Hello World!";
        }

        public static void main(String[] args) {
            SpringApplication.run(MyApplication.class, args);
        }

    }
}