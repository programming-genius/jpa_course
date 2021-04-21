package it.jpa.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
 
    private static Class<DemoApplication> applicationClass = DemoApplication.class;

}

/*@RestController
class HelloController {

	@Autowired
	private ParkrunCourseRepository parkrunCourseRepository;
	
	@Autowired
	private PostRepository postRepository;

	@RequestMapping("/hello")
	public String hello() {
		ParkrunCourse parkrunCourse = (ParkrunCourse) parkrunCourseRepository.findById(1L).get();
		// Do some work & in the mean time the database has been updated by a batch job
		// refresh object and now up to date
		parkrunCourseRepository.refresh(parkrunCourse);
		return "Hi !";
	}
	
	@RequestMapping("/post")
	public String post() {
		try {
			postRepository.add(new Post());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "Hi !";
	}
}*/
