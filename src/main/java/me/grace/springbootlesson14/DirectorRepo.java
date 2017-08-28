package me.grace.springbootlesson14;

import org.springframework.data.repository.CrudRepository;

public interface DirectorRepo extends CrudRepository<Director, Long>
{
    Director findFirstByNameContains(String partialString);

    Iterable<Director> findAllByNameContains(String partialString);
}
