package model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BookMapper {
   @Select("SELECT * FROM books")
    List<Book> getAllBooks();

    @Insert("INSERT INTO books (title, author, year, genre, stock) VALUES (#{title}, #{author}, #{year}, #{genre}, #{stock})")
    void insertBook(Book book);

    @Update("UPDATE books SET title=#{title}, author=#{author}, year=#{year}, genre=#{genre}, stock=#{stock} WHERE id=#{id}")
    void updateBook(Book book);

    @Delete("DELETE FROM books WHERE id=#{id}")
    void deleteBook(int id); 
}
