package com.example.knjiznica.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.knjiznica.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "SELECT *  FROM student u WHERE u.first_name = ?1", nativeQuery = true)
	Collection<Student> findAllActiveStudents(String ime);

	@Query(value = "SELECT u FROM student u WHERE u.first_name = ?1 and u.last_name = ?2", nativeQuery = true)
	Student findStudentByNames(String firstName, String lastName);

	@Query(value = "SELECT * FROM users u WHERE u.first_name = :imexxx and u.last_name = :prezime", nativeQuery = true)

	Student findUserByStatusAndNameNamedParamsNative(@Param("imexxx") String imexxx, @Param("prezime") String prezime);

	@Query(value = "SELECT * FROM student u ORDER BY id", nativeQuery = true)
	Page<Student> findAllUsersWithPaginationNative(Pageable pageable);

	@Query(value = "SELECT *  FROM student s WHERE s. ime = ?1", nativeQuery = true)
	Collection<Student> findStudentsByName(String ime);

	@Query(value = "SELECT * FROM student s WHERE s. godinaStudija = : godina", nativeQuery = true)
			  Collection<Student> findStudentsByGodinaStudija( @Param("godina") int godina);

	@Query(value = "SELECT *  FROM student u LIMIT ({?pagenumber}-1)*{?itemsPerPage},{?itemsPerPage}", nativeQuery = true)
	Collection<Student> findAllPaging(int pagenumber, int itemsPerPage);

	@Query(value = "SELECT * FROM student  /*#pageable*/ ORDER BY id", countQuery = "SELECT COUNT(*) FROM student", nativeQuery = true)
	Page<Student> findByLastname(Pageable pageable);

	@Query(value = "select * from student u order by rand() \n#pageable\n", countQuery = "SELECT COUNT(*) from student u", nativeQuery = true)
	Page<Student> findAllRandom(Pageable pageable);

}