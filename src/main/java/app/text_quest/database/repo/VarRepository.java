package app.text_quest.database.repo;

import app.text_quest.database.model.Var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 <h2> An interface {@link VarRepository}

 <p> Provides accessing to variables in the database

 @see org.springframework.data.jpa.repository.JpaRepository */
@Repository
public interface VarRepository extends JpaRepository<Var, Long> {


    /**
     Searches variable by its name

     @param name of the variable
     @return variable model or null if didn't found
     */
    Var findByName(String name);
}
