package controller;

import model.Book;
import model.BookMapper;
import org.apache.ibatis.session.SqlSession;
import view.BookView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class BookController {
    private BookView view;
    private BookMapper mapper;
    private SqlSession session;

    public BookController(BookView view, BookMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        this.view.addAddBookListener(new AddBookListener());
        this.view.addUpdateBookListener(new UpdateBookListener());
        this.view.addDeleteBookListener(new DeleteBookListener());
        this.view.addRefreshBookListener(new RefreshBookListener());
    }

    class AddBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.getTitleInput();
            String author = view.getAuthorInput();
            int year = view.getYearInput();
            String genre = view.getGenreInput();
            int stock = view.getStockInput();

            if (!title.isEmpty() && !author.isEmpty()) {
                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setYear(year);
                book.setGenre(genre);
                book.setStock(stock);
                mapper.insertBook(book);
                session.commit();
                JOptionPane.showMessageDialog(view, "Book added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class UpdateBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedBookId();
            if (selectedId != -1) {
                String title = view.getTitleInput();
                String author = view.getAuthorInput();
                int year = view.getYearInput();
                String genre = view.getGenreInput();
                int stock = view.getStockInput();

                Book book = new Book();
                book.setId(selectedId);
                book.setTitle(title);
                book.setAuthor(author);
                book.setYear(year);
                book.setGenre(genre);
                book.setStock(stock);
                mapper.updateBook(book);
                session.commit();
                JOptionPane.showMessageDialog(view, "Book updated successfully!");
            }
        }
    }

    class DeleteBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedBookId();
            if (selectedId != -1) {
                mapper.deleteBook(selectedId);
                session.commit();
                JOptionPane.showMessageDialog(view, "Book deleted successfully!");
            }
        }
    }

    class RefreshBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Book> books = mapper.getAllBooks();
            view.setBookTable(books);
        }
    }
}
