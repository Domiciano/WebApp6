package edu.co.icesi.introspringboot.controller;


import edu.co.icesi.introspringboot.entity.Course;
import edu.co.icesi.introspringboot.entity.Professor;
import edu.co.icesi.introspringboot.service.CourseService;
import edu.co.icesi.introspringboot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Thymeleaf
@Controller
@RequestMapping("/courses")
public class CourseController {



    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public String index(Model model) {
        //Inyeccion de la información
        model.addAttribute(
                "title",
                "Lista de cursos");
        List<Course> courseList = courseService.getAll();
        model.addAttribute("courseList", courseList);
        return "course/index";
        //Retornamos un index.html que esta dentro de
        //resources/course/index.html
    }

    // http://localhost:8080/course/detail/1
    @GetMapping("/detail/{id}")
    public String getCourseById(Model model, @PathVariable Integer id) {
        //Obtener el curso con id 1
        Course course = courseService.getById(id);
        model.addAttribute(
                "course",
                course
        );
        return "course/detail";
    }


    //http://localhost:8080/course/detail2?id=1
    //Filtros
    @GetMapping("/detail2")
    public String getCourseById2(Model model, @RequestParam Integer id) {
        //Obtener el curso con id 1
        Course course = courseService.getById(id);
        model.addAttribute(
                "course",
                course
        );
        return "course/detail";
    }

    //Creando un curso

    //1. Crear el GET del formulario
    @GetMapping("/new")
    public String createCourse(Model model) {

        model.addAttribute(
                "course",
                new Course());

        model.addAttribute(
                "professors",
                    professorService.getAll()
                );
        return "course/new";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/course/";
    }


}
