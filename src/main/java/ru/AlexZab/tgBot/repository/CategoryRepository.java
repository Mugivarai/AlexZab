package ru.AlexZab.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.AlexZab.tgBot.entity.Category;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.parent.id = :id")
    List<Category> getCategoryByParentID(Long id);

    @Query("Select c FROM Category c WHERE c.parent.id IS NULL")
    List<Category> getRootCategory();

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = :name ")
    Category getCategoryByName(String name);

    @Query("SELECT c FROM Category c")
    List<Category> getAllCategories();

    @Query("SELECT COUNT(c) > 0 FROM Category c WHERE c.parent.id = :id")
    Boolean hasChildCategories(Long id);

}
