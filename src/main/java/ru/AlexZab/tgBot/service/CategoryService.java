package ru.AlexZab.tgBot.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.AlexZab.tgBot.entity.Category;
import ru.AlexZab.tgBot.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getNameCategoryByParentId(Long id){
        List<Category> categories = new ArrayList<>();
        if(id==null){
            categories = categoryRepository.getRootCategory();
        }else{
            categories = categoryRepository.getCategoryByParentID(id);
        }

        List<String> names = new ArrayList<>();

        for (Category category : categories) {
            names.add(category.getName());
        }

        return names;
    }

    public Long getIdCategoryByName(String name){
        return categoryRepository.getCategoryByName(name).getId();
    }

    public List<String> getNameAllCategories(){
        List<Category> categories = categoryRepository.getAllCategories();
        List<String> names = new ArrayList<>();

        for (Category category : categories) {
            names.add(category.getName());
        }
        return names;
    }

    public Boolean hasChildCategories(Long id){
        return categoryRepository.hasChildCategories(id);
    }

    public Long getParentCategoryByName(String name){
        return categoryRepository.getCategoryByName(name).getParent().getId();
    }

}
