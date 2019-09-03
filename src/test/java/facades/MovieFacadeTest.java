package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MovieFacade.getMovieFacade(emf);
    }
    
    List<Movie> movies = new ArrayList();

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        
        String[] actors = new String[]{"Andreas", "Asger", "William", "Martin"};
        movies.add(new Movie(1998, "Test Name 1", actors));
        movies.add(new Movie(1999, "Test Name 2", actors));
        
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
            
            for(Movie m : movies) {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetMovieCount() {
        assertEquals(2, facade.getMovieCount(), "Expects two rows in the database");
    }
    
    @Test
    public void testGetMovies() {
        List<MovieDTO> databaseMovies = facade.getMovies();
        assertEquals(new MovieDTO(movies.get(0)), databaseMovies.get(0));
        assertEquals(new MovieDTO(movies.get(1)), databaseMovies.get(1));
        assertEquals(2, databaseMovies.size(), "Expects two rows in the database");
    }
    
    @Disabled
    @Test
    public void testGetMovieById() {
        MovieDTO databaseMovie = facade.getMovieById(1);
        assertEquals(new MovieDTO(movies.get(0)), databaseMovie);
    }
    
    @Test
    public void testGetMovieByName() {
        MovieDTO databaseMovie = facade.getMovieByName("Test Name 1");
        assertEquals(new MovieDTO(movies.get(0)), databaseMovie);
    }
}
