package com.skooly.backend.service;
import com.skooly.backend.dto.CourseDTO;
import com.skooly.backend.entity.Course;
import com.skooly.backend.entity.Student;
import com.skooly.backend.entity.Teacher;
import com.skooly.backend.exception.StudentNotFoundException;
import com.skooly.backend.exception.TeacherNotFoundException;
import com.skooly.backend.mapper.CourseMapper;
import com.skooly.backend.repository.CourseRepository;
import com.skooly.backend.repository.StudentRepository;
import com.skooly.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
   private final CourseRepository courseRepository;
   private final CourseMapper courseMapper;
   private final TeacherRepository teacherRepository;
   private final StudentRepository studentRepository;
   
   @Autowired
   public CourseService(CourseRepository courseRepository, CourseMapper courseMapper,
		   TeacherRepository teacherRepository, StudentRepository studentRepository) {
	  this.courseRepository = courseRepository;
	  this.courseMapper = courseMapper;
	  this.teacherRepository = teacherRepository;
	  this.studentRepository = studentRepository;
   }
   
   public List<CourseDTO> getAllCourses() {
	  return courseRepository.findAll()
							 .stream()
							 .map(courseMapper::toDTO)
							 .collect(Collectors.toList());
   }
   
   public CourseDTO getCourseById(Long id) {
	  Course course = courseRepository.findById(id)
									  .orElseThrow(() -> new RuntimeException("Course not found"));
	  return courseMapper.toDTO(course);
   }
   
   public CourseDTO saveCourse(CourseDTO courseDTO) {
	  Course course = courseMapper.toEntity(courseDTO);
	  // Attach the Teacher entity via teacherId.
	  if(courseDTO.getTeacherId() != null){
		 Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
											.orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
		 course.setTeacher(teacher);
	  }
	  // Attach Student entities using the list of student IDs.
	  if(courseDTO.getStudentIds() != null && !courseDTO.getStudentIds().isEmpty()){
		 List<Student> students = courseDTO.getStudentIds().stream()
										   .map(id -> studentRepository.findById(id)
																	   .orElseThrow(() -> new StudentNotFoundException(
																			   "Student not found with id: " + id)))
										   .collect(Collectors.toList());
		 course.setStudents(students);
	  }
	  Course savedCourse = courseRepository.save(course);
	  return courseMapper.toDTO(savedCourse);
   }
   
   public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
	  Course course = courseRepository.findById(id)
									  .orElseThrow(() -> new RuntimeException("Course not found"));
	  // Update basic fields using mapper helper.
	  courseMapper.updateCourseFromDTO(courseDTO, course);
	  // Update teacher information.
	  if(courseDTO.getTeacherId() != null){
		 Teacher teacher = teacherRepository.findById(courseDTO.getTeacherId())
											.orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
		 course.setTeacher(teacher);
	  }
	  // Update student enrollments.
	  if(courseDTO.getStudentIds() != null){
		 List<Student> students = courseDTO
				 .getStudentIds().stream()
				 .map(studentId -> studentRepository
						 .findById(studentId)
						 .orElseThrow(() -> new StudentNotFoundException(
								 "Student not found with id:" + studentId)))
				 .collect(Collectors.toList());
		 course.setStudents(students);
	  }
	  Course updatedCourse = courseRepository.save(course);
	  return courseMapper.toDTO(updatedCourse);
   }
   
   public void deleteCourse(Long id) {
	  courseRepository.deleteById(id);
   }
}