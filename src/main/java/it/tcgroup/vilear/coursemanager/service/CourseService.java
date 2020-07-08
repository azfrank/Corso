package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    IdResponseV1 insertCourse(CourseRequestV1 corso, String userId);
    CourseResponseV1 getCourse(UUID idCourse, String userId);
    CourseResponseV1 patchCourse(CourseRequestV1 coursePatchRequestV1, UUID idCourse, String userId);
    List<CourseResponseV1> getAllCourse(String userId);

    PaginationResponseV1<CourseResponseV1> getCoursePagination(int page, int pageSize, String courseTitle, CourseStatusEnum status, String userid);

    CourseResponseV1 patchStatusCourse(String userId, CourseStatusEnum status, UUID idCourse);

    CourseResponseV1 patchPartecipantiCourse(String userId,int max, UUID idCourse);
}
