package service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import model.Student;


// t -> t.getId().equalsIgnoreCase(studentId)).findFirst()

@Service
public class APIService {

	@CacheEvict(value="twenty-second-cache", key = "'StudentInCache'+#studentId",
			condition = "#isCacheable == null || !#isCacheable", beforeInvocation = true)
	@Cacheable(value="twenty-second-cache", key = "'StudentInCache'+#studentId", 
			condition = "#isCacheable != null && #isCacheable")
	public List<Student> fetchStudent(String studentId, boolean isCacheable) throws InterruptedException {
		Thread.sleep(4000);
		Stream stream = Stream.of(1,2,3,4);
		List<Student> list = new ArrayList<>();
		list.add(new Student("19VC21", "John", "Wayne", "11"));
		list.add(new Student("19VC22", "Mary", "Jane", "11"));
		list.add(new Student("19VC23", "Peter", "Parker", "11"));
		list.stream().filter(t -> t.getId().equalsIgnoreCase(studentId)).findFirst();
		return list;				
	}
}