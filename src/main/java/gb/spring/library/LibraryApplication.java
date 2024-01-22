package gb.spring.library;

import gb.spring.library.repository.BookRepository;
import gb.spring.library.repository.IssueRepository;
import gb.spring.library.repository.ReaderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	@Bean
	IssueRepository issueRepository() {
		return new IssueRepository();
	}
	@Bean
	BookRepository bookRepository() {
		return new BookRepository();
	}
	@Bean
	ReaderRepository readerRepository() {return new ReaderRepository();}

}
