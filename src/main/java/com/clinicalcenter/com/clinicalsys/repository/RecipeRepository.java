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
    @Query(value = "update clinicalsys.recipe SET clinicalsys.recipe.is_validate = true where  clinicalsys.recipe.id = ?1", nativeQuery = true)
    void validate(Long id);

    @Transactional
    @Query("select r from Recipe r where r.id = ?1")
    Recipe findRecipe(Long l);

    @Query(value = "SELECT * FROM clinicalsys.recipe where clinicalsys.recipe.nurse_id = ?1 " +
            "and clinicalsys.recipe.is_validate = false", nativeQuery = true)
    Set<Recipe> findRecipesForNurse(Long id);
}
