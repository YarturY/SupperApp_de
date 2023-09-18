package com.tijuana.supperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tijuana.supperapp.adapter.CategoryAdapter;
import com.tijuana.supperapp.adapter.CourseAdapter;
import com.tijuana.supperapp.model.Category;
import com.tijuana.supperapp.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();

    static List<Course> fullCourseList = new ArrayList<>();
    private View filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Spielen"));
        categoryList.add(new Category(2, "Websites"));
        categoryList.add(new Category(3, "Programmsprache"));
        categoryList.add(new Category(4, "Andere"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1, "java_2", "Java Developer\nEntwickler", "1. Januar", "für Anfanger", "#424345", "Test", 3));
        courseList.add(new Course(2, "python", "Python Developer\nEntwickler", "10. Januar", "für Anfanger", "#9FA52D", "Test", 3));
        courseList.add(new Course(3, "back_end", "PHP Developer\nEntwickler", "23. November", "für Anfanger", "#3282F6", "Test", 1));
        courseList.add(new Course(4, "front_end", "Front-end Developer\nEntwickler", "25. Mai", "für Erfahrene", "#FF5D3D", "Test", 2));
        courseList.add(new Course(5, "node_js", "Node JS Developer\nEntwickler", "1. Juni", "für Erfahrene", "#CCD130", "Test", 2));

        fullCourseList.addAll(courseList);

        setCourceRecycler(courseList);

        filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllCourses();
            }
        });
    }

    public void openShoppingCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);


    }

    private void setCourceRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);
        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        categoryRecycler = findViewById(R.id.categoryRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        categoryRecycler.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showCoursesByCategory(int category) {
        List<Course> filterCourses = new ArrayList<>();
        for (Course c : fullCourseList) {
            if (c.getCategory() == category)
                filterCourses.add(c);
        }
        courseList.clear();
        courseList.addAll(filterCourses);
        courseAdapter.notifyDataSetChanged();
    }

    private void showAllCourses() {
        courseList.clear();
        courseList.addAll(fullCourseList);
        courseAdapter.notifyDataSetChanged();
    }
}

