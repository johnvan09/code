package cdp.fms.dao;

import cdp.fms.model.News;

import java.util.List;

public interface NewsDao {
    /**
     * 添加新闻
     **/
    boolean addNews(News news);

    /**
     * 修改新闻
     **/
    boolean changeNews(News news);

    /**
     * 删除新闻
     **/
    boolean deleteNews(int newsId);

    /**
     * 查询新闻
     **/
    News getNewsById(int newsId);

    /**
     * 推荐新闻
     **/
    List<News> getHotNews();

    /**
     * 获取所有新闻
     **/
    List<News> getAllNews();

    /**
     * 浏览量加一
     **/
    void updateLiuLanNums(int newsId);
}
