package lv.acc.springboot.config;


import lv.acc.springboot.service.BookManagmentServiceImpl;
import lv.acc.springboot.storage.Database;
import lv.acc.springboot.validators.InputValidators;
import lv.acc.springboot.validators.ResultValidators;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public BookManagmentServiceImpl bookManagmentService(Database db, InputValidators validator, ResultValidators resultValidators){
        return new BookManagmentServiceImpl(db, validator, resultValidators);
    }
}
