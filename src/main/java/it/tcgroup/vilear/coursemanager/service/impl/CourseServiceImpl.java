package it.tcgroup.vilear.coursemanager.service.impl;


import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseAdapter courseAdapter;

    @Autowired
    private CourseRepository courseRepository;

    /*@Autowired
    private CourseEMRepository courseEMRepository;*/

    @Override
    public IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest){

        CourseEntity course = courseAdapter.adptCourseRequestToCourse(courseInsertRequest);
        course.setCreationCourseDate(new Date());
        course.setLastChangeDate(new Date());
        courseRepository.save(course);

        return courseAdapter.adptCourseIdToCourseIdResponse(course);

    }


}
