package com.cinema.Cinema;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.services.MovieService;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import com.cinema.Cinema.repositories.MovieRepository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.el.BeanNameResolver;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(CinemaApplication.class, args);
		//SpringApplication.run(CinemaApplication.class, args);

		MovieRepository movieRepository = configurableApplicationContext.getBean(MovieRepository.class);

		/*movieRepository.save(new Movie("Creed","Action"));
		movieRepository.save(new Movie("Scream VI","Terror"));
		movieRepository.save(new Movie("Mari(dos)","Comedy"));
		movieRepository.save(new Movie("Watcher","Thriller"));
		movieRepository.save(new Movie("As bestas","Mystery"));
		 */
	}

}
