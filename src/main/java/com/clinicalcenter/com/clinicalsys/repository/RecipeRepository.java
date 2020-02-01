package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Transactional
    @Query("select r from Recipe r where r.isValidate = false")
    Set<Recipe> getRecipesForValidating();

    @Transactional
    @Modifying
    @Query("update Recipe r SET r.isValidate = true where r.id = ?1")
    void validate(Long id);

    @Transactional
    @Query("select r from Recipe r where r.id = ?1")
    Recipe findRecipe(Long l);
}
