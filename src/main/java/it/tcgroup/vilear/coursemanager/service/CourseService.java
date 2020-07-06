package it.tcgroup.vilear.coursemanager.service;

import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;

import java.util.UUID;

public interface CourseService {

    IdResponseV1 insertCourse(CourseRequestV1 corso, String userId);

    CourseResponseV1 patchCourse(CourseRequestV1 coursePatchRequestV1, UUID idCourse, String userId);

}
