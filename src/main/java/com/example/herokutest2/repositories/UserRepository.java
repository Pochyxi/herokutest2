package com.example.herokutest2.repositories;


import com.example.herokutest2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername( String username);

    @Query(
            value = "select c from User c where upper(c.username) like upper(concat('%', :nome, "
                    + "'%'))"
    )
    List<User> getUserByUsernameContains( @Param ( "nome" ) String nome );


}
