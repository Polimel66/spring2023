package org.myApplication.app.repositories;

import org.myApplication.app.entity.BookEntity;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {
    /**
     * Метод получения книг из базы данных постранично
     *
     * @param pageRequest параметр, содержащий номер страницы,
     *                    количество записей и параметры сортировки записей, которые нужно вывести
     * @return список книг
     */
    @Query("from BookEntity")
    List<BookEntity> findAllBooksByPageRequest(PageRequest pageRequest);

    /**
     * Метод поиска книг по названию и/или автору, совпадающих
     * или содержащих введенную строку
     *
     * @param title       строка для проверки названий
     * @param author      строка для проверки авторов
     * @param pageRequest параметр, содержащий номер страницы,
     *                    количество записей и параметры сортировки записей, которые нужно вывести
     * @return список книг, соответствующих поисковому запросу
     */
    @Query("select b from BookEntity b where UPPER(b.title) like UPPER(?1) or UPPER(b.author) like UPPER(?2)")
    List<BookEntity> findByTitleOrAuthorByPageRequest(String title, String author, PageRequest pageRequest);
}
