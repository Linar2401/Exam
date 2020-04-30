package ru.linar.sem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.linar.sem.dto.ChapterDto;
import ru.linar.sem.models.Book;
import ru.linar.sem.models.BookChapter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookChapterRepo extends JpaRepository<BookChapter, Long> {
    List<BookChapter> findAllByBook(Book book);
    List<BookChapter> findAllByBookAndSerialNumberGreaterThanOrderBySerialNumber(Book book, Byte number);
    List<BookChapter> findAllByBookAndAndSerialNumberLessThanOrderBySerialNumberDesc(Book book, Byte number);
    List<BookChapter> findAllBySerialNumberLessThanOrderBySerialNumberDesc(Byte number);
    List<BookChapter> findAllBySerialNumberGreaterThanOrderBySerialNumber(Byte number);
    Optional<BookChapter> findByBookAndSerialNumber(Book book, Byte serialNumber);
    @Query(
            value = "SELECT book_chapter_id as id, name as number from `sem`.book_chapter where book_id = :id_book order by serial_number",
            nativeQuery = true)
    List<Object[]> findAllByBook_Id(@Param("id_book")Long id);
    @Query(
            value = "SELECT book_chapter_id from `sem`.book_chapter where book_id = :id_book and serial_number < :s_number order by serial_number DESC",
            nativeQuery = true)
    List<Object> findAllByBook_IdLessThan(@Param("id_book")Long id, @Param("s_number")Byte number);

    @Query(
            value = "SELECT book_chapter_id from `sem`.book_chapter where book_id = :id_book and serial_number > :s_number order by serial_number",
            nativeQuery = true)
    List<Object> findAllByBook_IdMoreThan(@Param("id_book")Long id, @Param("s_number")Byte number);
}
