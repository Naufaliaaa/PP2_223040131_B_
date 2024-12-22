package main;
import model.MyBatisUtil;
import model.BookMapper;
import org.apache.ibatis.session.SqlSession;
import view.BookView;
import controller.BookController;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        BookMapper mapper = session.getMapper(BookMapper.class);

        BookView view = new BookView();
        new BookController(view, mapper, session);

        view.setVisible(true);
    }
}
