package dto;

import entities.Movie;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author andreas
 */
public class MovieDTO {
    private Long id;
    private int year;
    private String name;
    private String[] actors;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.year = movie.getYear();
        this.name = movie.getName();
        this.actors = movie.getActors();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.year;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Arrays.deepHashCode(this.actors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieDTO other = (MovieDTO) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Arrays.deepEquals(this.actors, other.actors)) {
            return false;
        }
        return true;
    }
    
    
}
