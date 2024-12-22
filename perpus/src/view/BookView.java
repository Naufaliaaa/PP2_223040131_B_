package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
//import java.awt.print.Book;
import java.util.List;
import model.Book;

public class BookView extends JFrame {
    private JTextField txtTitle = new JTextField(20);
    private JTextField txtAuthor = new JTextField(20);
    private JTextField txtYear = new JTextField(20);
    private JTextField txtGenre = new JTextField(20);
    private JTextField txtStock = new JTextField(20);
    private JButton btnAdd = new JButton("Add Book");
    private JButton btnUpdate = new JButton("Update Book");
    private JButton btnDelete = new JButton("Delete Book");
    private JButton btnRefresh = new JButton("Refresh");
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookView() {
        setTitle("Library Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Title:"));
        panel.add(txtTitle);
        panel.add(new JLabel("Author:"));
        panel.add(txtAuthor);
        panel.add(new JLabel("Year:"));
        panel.add(txtYear);
        panel.add(new JLabel("Genre:"));
        panel.add(txtGenre);
        panel.add(new JLabel("Stock:"));
        panel.add(txtStock);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        String[] columnNames = {"ID", "Title", "Author", "Year", "Genre", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getTitleInput() {
        return txtTitle.getText();
    }

    public String getAuthorInput() {
        return txtAuthor.getText();
    }

    public int getYearInput() {
        return Integer.parseInt(txtYear.getText());
    }

    public String getGenreInput() {
        return txtGenre.getText();
    }

    public int getStockInput() {
        return Integer.parseInt(txtStock.getText());
    }

    public void setBookTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getGenre(), book.getStock()});
        }
    }

    public int getSelectedBookId() {
        int row = bookTable.getSelectedRow();
        return (row != -1) ? (int) tableModel.getValueAt(row, 0) : -1;
    }

    public void addAddBookListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addUpdateBookListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteBookListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addRefreshBookListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }
}
