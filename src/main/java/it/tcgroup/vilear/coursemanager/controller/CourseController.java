package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.PaginationResponseV1;
import it.tcgroup.vilear.coursemanager.entity.enumerated.CourseStatusEnum;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/coursemanager")
@Api("CourseManager")
public class CourseController {


    @Autowired
    private CourseService courseService;

    /*Inserimento di un nuovo corso*/
    @PostMapping(value = "/course/registration",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Insert Course", notes = "Insert course using info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = IdResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<IdResponseV1> postInsertCourse(
            @ApiParam(value = "Body of the Course to be created", required = true)
            @RequestBody CourseRequestV1 courseInsertRequestV1,
            @RequestHeader(name = "id-user") String idUser) {



        return new ResponseEntity<>( courseService.insertCourse(courseInsertRequestV1, idUser), HttpStatus.OK);
    }

    /*Modifica di un corso esistente*/
    @PatchMapping(value = "/course/{UUID_COURSE}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Upload a part of the Course", notes = "Update a part of the course using the info passed in the body")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> patchCourse(
            @ApiParam(value = "String user logged", required = true)
            @RequestHeader(name = "id-user") String userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "Some attributes of the body of the Course to be modified", required = true)
            @RequestBody CourseRequestV1 coursePatchRequestV1) throws Exception {

        return new ResponseEntity<>(courseService.patchCourse(coursePatchRequestV1, UUID.fromString(idCourse), userId), HttpStatus.OK);
    }

    /*Recupero di uno specifico corso*/
    @GetMapping(value = "/course/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Course", notes = "Returns a Course using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> getCourseById(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") String userId,
            @ApiParam(value = "UUID of the Course to be founfd", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {


        return new ResponseEntity<>(courseService.getCourse(UUID.fromString(idCourse), userId), HttpStatus.OK);
    }

    /*Recupero di tutti i corsi*/
    @GetMapping(value = "/course/all/{id-user}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Recover Course", notes = "Returns a Course using the UUID passed in the path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = CourseResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<CourseResponseV1>> getAllCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @PathVariable(name = "id-user") String userId) {


        return new ResponseEntity<>(courseService.getAllCourse(userId), HttpStatus.OK);
    }

    /*Rotta per la paginazione*/
    @GetMapping(value = "/course",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all filiali", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<PaginationResponseV1<CourseResponseV1>> getCoursePagination(
            @ApiParam(value = "String user logged", required = true)
            @RequestParam(name = "id-user", required = true) String userId,
            @ApiParam(value = "Defines how many Discenti can contain the single page", required = false)
            @RequestParam(value = "page_size", defaultValue = "20") Integer page_size,
            @ApiParam(value = "Defines the page number to be displayed", required = false)
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "course_title", required = false) String courseTitle,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "status", required = false) CourseStatusEnum status ) {


        return new ResponseEntity<>(courseService.getCoursePagination(page, page_size, courseTitle, status, userId),HttpStatus.OK);
    }

    /*Cambio stato del corso*/
    @PatchMapping(value = "/course/due/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all filiali", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> patchStatusCourse(
            @ApiParam(value = "String user logged", required = true)
            @RequestParam(name = "id-user") String userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "", required = false)
            @RequestParam(value = "status", required = false) CourseStatusEnum status) throws Exception {

        return new ResponseEntity<>(courseService.patchStatusCourse(userId,status,UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*Aggiunta di partecipanti*/
    @PatchMapping(value = "/course/add/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Get all filiali", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = PaginationResponseV1.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<CourseResponseV1> patchStatusCourse(
            @ApiParam(value = "String user logged", required = true)
            @RequestParam(name = "id-user") String userId,
            @ApiParam(value = "Partecipanti attuali", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "Partecipanti attuali", required = false)
            @RequestParam(value = "number_of_actual_participants", required = false) int max) throws Exception {

        return new ResponseEntity<>(courseService.patchPartecipantiCourse(userId,max,UUID.fromString(idCourse)), HttpStatus.OK);
    }

    /*Eliminare un corso*/
    @DeleteMapping( value = "/course/{UUID_COURSE}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity deleteCourse(
            @ApiParam(value = "UUID user logged", required = true)
            @RequestHeader(name = "id-user") String userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse) {


        courseService.deleteCourse(UUID.fromString(idCourse),userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
