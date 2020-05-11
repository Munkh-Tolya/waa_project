package edu.miu.cs545.waa_project.service;

import edu.miu.cs545.waa_project.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(int id);
}
