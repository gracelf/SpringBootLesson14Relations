package me.grace.springbootlesson14;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepo directorRepo;

    @Autowired
    MovieRepo movieRepo;


    @RequestMapping("/")
    public String index(Model model){
        //create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        //Now let's create a movies
        Movie movie= new Movie();
        movie.setTitle("star movies");
        movie.setYear(2017);
        movie.setDescription("About Starts...");
        director.addMovie(movie);

        movie=new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
        director.addMovie(movie);

        //save the director to the database
        directorRepo.save(director);

        //grad all the directors from the database and send them to the template
        model.addAttribute("directors", directorRepo.findAll());

        return "index";

    }

    @GetMapping("/adddir")
    public String adddir(Model model) {
        model.addAttribute("director", new Director());

        return "directorform";
    }

    @PostMapping("/adddir")
    public String processNewdir(@ModelAttribute("director") Director director) {

        directorRepo.save(director);

        return "directorconfirm";

    }


    @GetMapping("/listdir")
    public String listdir(Model model)
    {
        Iterable<Director> alldirector= directorRepo.findAll();

        model.addAttribute("alldir", alldirector);
        return "listdir";
    }


    @RequestMapping("/update/{id}")
    public String updatemoivetodir(@PathVariable("id") long id, Model model)
    {


        Director onedir = directorRepo.findOne(id);
        model.addAttribute("onedir", onedir);

//        Set<Movie> onemovset=onedir.getMovies();
//        model.addAttribute("movieset", onemovset);

        Movie movie = new Movie();
        movie.setDirector(onedir);
        model.addAttribute("movie",movie);
        System.out.println(id);
        System.out.println(onedir.getId());
        return "movieform";
    }

//    @GetMapping("/addmovie")
//    public String addmov(Model model) {
//        model.addAttribute("movie", new Movie());
//
//        return "movieform";
//    }

    @PostMapping("/addmovie")
    public String processNewmovie(@ModelAttribute("movie") Movie movie, @ModelAttribute("onedir") Director onedir, Model model)
    {



        //both of the below works, data passed successfully
//        System.out.println(dirName);
        System.out.println(movie.getTitle());

        System.out.println(onedir.getId());

        //onedir.addMovie(movie);
        movieRepo.save(movie);



        //save the director to the database



//        Director thedir = directorRepo.findFirstByNameContains("dirName");
//
//        System.out.println(thedir.getId());

//        if(thedir.getName()=="null"){
//            Director director=new Director();
//            director.setName("dirName");
//
//            Set<Movie> allmovie=director.getMovies();
//            allmovie.add(movie);
//            director.setMovies(allmovie);
//        }

//        Set<Movie> allmov=thedir.getMovies();
//        allmov.add(movie);
//        thedir.setMovies(allmov);
//
        model.addAttribute("onedir", onedir);
        model.addAttribute("movie", movie);

        return "movieconfirm";

    }



}
