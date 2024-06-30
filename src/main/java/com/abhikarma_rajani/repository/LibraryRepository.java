package com.abhikarma_rajani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abhikarma_rajani.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>
{
	public boolean existsBybookId(String bookId);

	@Query("SELECT lib FROM Library lib WHERE lib.bookId LIKE %?1%"
			+ " OR lib.bookName LIKE %?1%"
			+ " OR lib.language LIKE %?1%"
		//	+ " OR lib.department LIKE %?1%"
			+ " OR lib.className LIKE %?1%"
		//	+ " OR lib.type LIKE %?1%"
			+ " OR lib.status LIKE %?1%")
	public List<Library> search(String keyword);
}
