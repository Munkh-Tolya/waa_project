package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Category;
import edu.miu.cs545.waa_project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }
}
