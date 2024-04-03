package br.com.nicolas.aluramusic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nicolas.aluramusic.main.Main;
import br.com.nicolas.aluramusic.repository.ArtistRepository;

@SpringBootApplication
public class AluraMusicApplication implements CommandLineRunner {
	@Autowired
	private ArtistRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(AluraMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.showMenu();
	}

}
