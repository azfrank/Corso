package it.tcgroup.vilear.coursemanager.service.impl;


import ch.qos.logback.core.net.SyslogOutputStream;
import it.tcgroup.vilear.coursemanager.adapter.CourseAdapter;
import it.tcgroup.vilear.coursemanager.common.exception.NotFoundException;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.CourseEntity;
import it.tcgroup.vilear.coursemanager.entity.Pagination;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;
import it.tcgroup.vilear.coursemanager.repository.CourseEMRepository;
import it.tcgroup.vilear.coursemanager.repository.CourseRepository;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

                courseRepository.save(course);

                return courseAdapter.adptCourseToCourseResponse(course);
            } else
                throw new NotFoundException("Course is not INSERITO");
        }else
            throw new NotFoundException("Non sei tu l'user che gestisce il corso");

    }

    @Override
    public CourseResponseV1 getCourse(UUID idCourse,String userId) {

        Optional<CourseEntity> course = courseRepository.findById(idCourse);

        if (course.isPresent())  {
            CourseEntity corso = course.get();
            if (corso.getUserId().compareTo(userId) == 0) {
                return courseAdapter.adptCourseToCourseResponse(corso);
            } else
                throw new NotFoundException("Non sei tu l'user che gestisce il corso");
        } else
            throw new NotFoundException("Corso non trovato");

    }

    @Override
    public List<CourseResponseV1> getAllCourse(String userId) {



        List<CourseEntity> courseList = courseEMRepository.getCourseList(userId);

        List<CourseEntity>  courseListFull = courseEMRepository.getCourseList(userId);


        for(int i=0; i < courseList.size();i++){

            if (courseList.get(i).getUserId().compareTo(userId) == 0) {
                courseListFull.add(courseList.get(i));
            }
        }
            return courseAdapter.adptCourseToCourseResponse(courseList);
    }

    @Override
    public PaginationResponseV1<CourseResponseV1> getCoursePagination(int page, int pageSize, String courseTitle, CourseStatusEnum status, String userId){

        Pagination<CourseEntity> coursePagination = new Pagination<>();

        List<CourseEntity> courseList = courseEMRepository.getCourseForPagination(userId, courseTitle, status);

        coursePagination.setStats(new PaginationResponseV1.InfoPagination(courseList.size(), page, pageSize));

        int start = coursePagination.getStats().getStartPage();
        int count = 0;
        List<CourseEntity> courseForPagination = new LinkedList<>();

        while (count < coursePagination.getStats().getPageSize() && ((start - 1) + count) < courseList.size()) {
            courseForPagination.add((courseList.get((start - 1) + count)));
            count++;
        }

        coursePagination.setItems(courseForPagination);

        return courseAdapter.adpCoursePaginationToCoursePaginationResponse(coursePagination);
    }

    @Modifying
    @Override
    public CourseResponseV1 patchStatusCourse(String userId, CourseStatusEnum status, UUID idCourse){

        Optional<CourseEntity> optCourse = courseRepository.findById(idCourse);
        if(!optCourse.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }
        CourseEntity course = optCourse.get();

        if ( course.getUserId().compareTo(userId) == 0) {
            if(course.getMaxNumericOfParticipants() == course.getNumberOfActualParticipants()) {

                switch (status) {
                    case IN_CORSO:
                        if (course.getStatus() == CourseStatusEnum.INSERITO) {
                            course.setStatus(CourseStatusEnum.IN_CORSO);
                            course.setLastChangeDate(new Date());

                            courseRepository.save(course);

                            return courseAdapter.adptCourseToCourseResponse(course);
                        } else
                            throw new NotFoundException("Impossibile cambiare il corso da " + course.getStatus().toString() + " a " + CourseStatusEnum.INSERITO.toString());

                    case TERMINATO:
                        if (course.getStatus() == CourseStatusEnum.IN_CORSO) {
                            course.setStatus(CourseStatusEnum.TERMINATO);
                            course.setLastChangeDate(new Date());
                            courseRepository.save(course);

                            return courseAdapter.adptCourseToCourseResponse(course);
                        } else
                            throw new NotFoundException("Impossibile cambiare il corso da " + course.getStatus().toString() + " a " + CourseStatusEnum.TERMINATO.toString());

                }
            } else
                throw new NotFoundException("Il numero di partecipanti non ha raggiunto il massimo");
        }else
            throw new NotFoundException("Non sei tu l'user che gestisce il corso");

        return null;
    }

    @Modifying
    @Override
    public CourseResponseV1 patchPartecipantiCourse(String userId,int max, UUID idCourse){

        Optional<CourseEntity> optCourse = courseRepository.findById(idCourse);
        if(!optCourse.isPresent()){
            throw new NotFoundException("Course with id " + idCourse+ " not found");
        }
        CourseEntity course = optCourse.get();

        if ( course.getUserId().compareTo(userId) == 0) {
            if (course.getMaxNumericOfParticipants() >= max){
                course.setNumberOfActualParticipants(max);
                course.setLastChangeDate(new Date());

                courseRepository.save(course);

                return courseAdapter.adptCourseToCourseResponse(course);
            }else
                throw new NotFoundException("Numero massimo di partecipanti inferiore al numero di partecipanti che si vuole inserire");
        }else
            throw new NotFoundException("Non sei tu l'user che gestisce il corso");
    }

    @Override
    public void deleteCourse(UUID idCourse, String userId){

        Optional<CourseEntity> optCourse = courseRepository.findById(idCourse);

        if (optCourse.isPresent()) {
            CourseEntity course = optCourse.get();
            if (course.getUserId().compareTo(userId) == 0) {
                if (course.getStatus() == CourseStatusEnum.TERMINATO) {
                    @Deprecated
                    int x = new Date().getYear();
                    @Deprecated
                    int y = course.getLastChangeDate().getYear();
                    System.out.println(x + "" + y );
                    if (x-y >= 1) {
                        courseRepository.delete(course);
                    } else
                        throw new NotFoundException("Ultimo update meno di 1 anno fa");
                }else
                    throw new NotFoundException("Corso non terminato");
            }else
                throw new NotFoundException("Non sei tu l'user che gestisce il corso");
        }
        else
            throw new NotFoundException("Course with uuid: " + idCourse + " isn't present ");
    }
    
}
