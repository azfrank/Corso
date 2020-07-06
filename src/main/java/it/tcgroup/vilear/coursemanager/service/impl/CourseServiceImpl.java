package it.tcgroup.vilear.coursemanager.service.impl;


import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;
import it.tcgroup.vilear.coursemanager.repository.CourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseAdapter courseAdapter;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseEMRepository courseEMRepository;

    @Override
    public IdResponseV1 insertCourse(CourseRequestV1 courseInsertRequest, String idUser){

        CourseEntity course = courseAdapter.adptCourseRequestToCourse(courseInsertRequest);
        course.setCreationCourseDate(new Date());
        course.setLastChangeDate(new Date());
        course.setUserId(idUser);
        courseRepository.save(course);

        return courseAdapter.adptCourseIdToCourseIdResponse(course);

    }

    @Modifying
    @Override
    public CourseResponseV1 patchCourse(CourseRequestV1 courseUpdateRequest, UUID idCourse, String userId){

        Optional<CourseEntity> optCourse = courseRepository.findById(idCourse);
        if(!optCourse.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }
        CourseEntity course = optCourse.get();

        CourseEntity coursePatch = courseAdapter.adptCourseRequestToCourse(courseUpdateRequest);

        if ( course.getUserId().compareTo(userId) == 0) { // Controllo se è utente che ha inserito il corso
            if (course.getStatus() == CourseStatusEnum.INSERITO) { // Controllo se il corso è in status "INSERITO"
                if (coursePatch.getMaxNumericOfParticipants() != null)
                    course.setMaxNumericOfParticipants(coursePatch.getMaxNumericOfParticipants());

                if (coursePatch.getCourseTitle() != null)
                    course.setCourseTitle(coursePatch.getCourseTitle());

                course.setLastChangeDate(new Date());
            } else
                throw new NotFoundException("Course is not INSERITO");
        }else
            throw new NotFoundException("Non sei tu l'user che gestisce il corso");

        courseRepository.save(course);

        return courseAdapter.adptCourseToCourseResponse(course);
    }


}
