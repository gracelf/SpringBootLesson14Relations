package me.grace.springbootlesson14;


import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private long year;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="director_id")
    private Director director;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }
}
