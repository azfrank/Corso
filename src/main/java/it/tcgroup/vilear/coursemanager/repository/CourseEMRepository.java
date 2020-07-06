package it.tcgroup.vilear.coursemanager.repository;

import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
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

    public List<CourseEntity> getFilialiForPagination(String name, String email, String rightOfAccessToTheCourses, Boolean superFiliale, String city, String region, String province){

        String sql = "SELECT * FROM course b";

        List<String> whereCondition = new LinkedList<>();

        if( name != null){
            whereCondition.add("upper(b.name) = upper('" + name + "') ");
        }
        if( rightOfAccessToTheCourses != null){
            whereCondition.add("b.right_of_access_to_the_courses = '" + rightOfAccessToTheCourses + "' ");
        }
        if( superFiliale != null){
            whereCondition.add("b.super = " + superFiliale + " ");
        }
        if( email != null){
            whereCondition.add("b.email = '" + email + "'");
        }
        if( city != null){
            whereCondition.add("upper(b.address ->> 'city') = upper('" + city + "') ");
        }
        if( region != null){
            whereCondition.add("upper(b.address ->> 'region') = upper('" + region + "') ");
        }
        if( province != null){
            whereCondition.add("upper(b.address ->> 'province') = upper('" + province + "') ");
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
}
