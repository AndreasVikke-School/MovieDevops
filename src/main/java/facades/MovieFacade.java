package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<MovieDTO> getMovies(){
        EntityManager em = emf.createEntityManager();
        try{
            return em.createQuery("SELECT new dto.MovieDTO(m) FROM Movie m").getResultList();
        }finally{  
            em.close();
        }
    }
    
    public MovieDTO getMovieById(long id){
        EntityManager em = emf.createEntityManager();
        try{
            return new MovieDTO(em.find(Movie.class, id));
        }finally{  
            em.close();
        }
    }
    
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            return (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
        }finally{  
            em.close();
        }
    }

}
