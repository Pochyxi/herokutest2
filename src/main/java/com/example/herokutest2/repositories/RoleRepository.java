package com.example.herokutest2.repositories;


import com.example.herokutest2.entities.Role;
import com.example.herokutest2.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType( RoleType roleType);

}
