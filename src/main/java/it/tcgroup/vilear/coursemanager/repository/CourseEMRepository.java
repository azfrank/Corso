package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;
import org.h2.tools.Console;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CourseEMRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CourseEntity> getCourseList(String userId){

        String sql = "SELECT * FROM course b";

        List<String> whereCondition = new LinkedList<>();

        if( userId != null){
            whereCondition.add("b.user_id = '" + userId + "'");
        }


        int i = 1;
        String where="";
        if(whereCondition.size() > 0)
            where = " WHERE ";
        for( String attCondition : whereCondition){
            where += attCondition;
            if(i < whereCondition.size())
                where += " AND ";
            i++;
        }

        sql += where;
        Query query = em.createNativeQuery(sql, CourseEntity.class);

        return query.getResultList();

    }

    public List<CourseEntity> getCourseForPagination(String userId, String courseTitle, CourseStatusEnum status){

        String sql = "SELECT * FROM course b";

        List<String> whereCondition = new LinkedList<>();

        if( userId != null){
            whereCondition.add("b.user_id = '" + userId + "'");
        }

        if( courseTitle != null){
            whereCondition.add("upper(b.course_title) = upper('" + courseTitle + "')");
        }

        if( status != null){
            whereCondition.add("b.status = '" + status.toString() + "'");
        }


        int i = 1;
        String where="";
        if(whereCondition.size() > 0)
            where = " WHERE ";
        for( String attCondition : whereCondition){
            where += attCondition;
            if(i < whereCondition.size())
                where += " AND ";
            i++;
        }

        sql += where + "ORDER BY(creation_course_date) DESC";
        Query query = em.createNativeQuery(sql, CourseEntity.class);

        return query.getResultList();

    }

}