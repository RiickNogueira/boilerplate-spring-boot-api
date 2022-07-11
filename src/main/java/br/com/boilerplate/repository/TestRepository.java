package br.com.boilerplate.repository;

import br.com.boilerplate.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    public List<Test> findAllByOrderByDescription();

}
