package me.grace.springbootlesson14;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends CrudRepository<Director, Long> {

}
