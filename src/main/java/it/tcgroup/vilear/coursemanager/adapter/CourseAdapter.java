package it.tcgroup.vilear.coursemanager.adapter;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseAdapter {

    @Autowired
    private AddressAdapter addressAdapter;

    public CourseEntity adptCourseRequestToCourse(CourseRequestV1 corso){

        if(corso == null)
            return null;

        CourseEntity course = new CourseEntity();

        course.setCourseTitle(corso.getCourseTitle());
        course.setMaxNumericOfParticipants(corso.getMaxNumericOfParticipants());
        course.setStatus(CourseStatusEnum.INSERITO);
        course.setUserNick(corso.getUserNick());
        course.setNumberOfActualParticipants(0);

        return course;
    }


    public IdResponseV1 adptCourseIdToCourseIdResponse(CourseEntity course) {
        if(course == null)
            return null;

        IdResponseV1 corso = new IdResponseV1();
        corso.setId(course.getId());

        return corso;
    }

    public CourseResponseV1 adptCourseToCourseResponse(CourseEntity course){

        if(course == null)
            return null;

        CourseResponseV1 courseeResponse = new CourseResponseV1();

        courseeResponse.setCourseTitle(course.getCourseTitle());
        courseeResponse.setMaxNumericOfParticipants(course.getMaxNumericOfParticipants());
        courseeResponse.setStatus(CourseStatusEnum.INSERITO);
        courseeResponse.setUserNick(course.getUserNick());
        courseeResponse.setNumberOfActualParticipants(0);

        return courseeResponse;
    }
}
