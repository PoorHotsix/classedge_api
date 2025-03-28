package com.learnova.classedge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import com.learnova.classedge.domain.Post;
import com.learnova.classedge.domain.QPost;
import com.learnova.classedge.dto.PostSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public CustomPostRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    QPost post = QPost.post;

    @Override
    public List<Object[]> findAllByTitle1(String titleStr) {

        String qlString = "SELECT p.id, p.title, p.email FROM Post AS p WHERE p.title LIKE :title";

        @SuppressWarnings("unchecked")
        List<Object[]> posts = em.createQuery(qlString)
                .setParameter("title", "%" + titleStr + "%")
                .getResultList();

        return posts;
    }

    @Override
    public List<Post> findAllByTitle(String titleStr) {

        String qlString = "SELECT p FROM Post AS p WHERE p.title LIKE :title";

        List<Post> posts = em.createQuery(qlString, Post.class)
                .setParameter("title", "%" + titleStr + "%")
                .getResultList();

        return posts;

    }

    @Override
    public long getTotalCount() {

        String qlString = "SELECT COUNT(p) FROM Post AS p";

        return em.createQuery(qlString, Long.class)
                .getSingleResult().longValue();

    }

    // QueryDSL
    @Override
    public Page<Post> paging(PostSearchCondition condition,  Pageable pageable) {

        // 데이터 조회

        List<Post> posts = jpaQueryFactory
                .select(post)
                .from(post)
                .where(
                    nicknameLike(condition.getNickname()),
                        contentsLike(condition.getContents()),
                        titleLike(condition.getTitle()),
                        boardNameEq(condition.getBoardName()))
                .orderBy(post.id.desc())
                .offset(pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

          // 총 데이터 개수

          long totalCount = jpaQueryFactory
          .select(post)
          .from(post)
          .where(
                    nicknameLike(condition.getNickname()),
                    contentsLike(condition.getContents()),
                  titleLike(condition.getTitle()),
                  boardNameEq(condition.getBoardName())
                  )
          .fetchCount();
            



        return PageableExecutionUtils.getPage(posts, pageable, () -> {
            return totalCount;
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findPostAll() {

        String qlString = "SELECT p.id, p.title, size(p.files), f.filename FROM Post p LEFT OUTER JOIN p.files f";

        return em.createQuery(qlString)
                .getResultList();

    }

    private BooleanExpression nicknameLike(String nickname) {

        return nickname == null ? null : post.member.nickname.like("%" + nickname + "%");

    }

    private BooleanExpression contentsLike(String contents) {

        return contents == null ? null : post.contents.like("%" + contents + "%");

    }

    private BooleanExpression titleLike(String title) {

        return title == null ? null : post.title.like("%" + title + "%");

    }

    private BooleanExpression boardNameEq(String boardName) {

        return boardName == null ? null : post.boardName.eq(boardName);

    }

}
