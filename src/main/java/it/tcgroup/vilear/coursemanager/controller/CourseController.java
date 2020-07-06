package it.tcgroup.vilear.coursemanager.controller;

import io.swagger.annotations.*;
import it.tcgroup.vilear.coursemanager.controller.payload.request.CourseRequestV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.CourseResponseV1;
import it.tcgroup.vilear.coursemanager.controller.payload.response.IdResponseV1;
import it.tcgroup.vilear.coursemanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping(value = "/course/{UUID_COURSE}/{user_id}",
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
            @PathVariable(name = "user_id") String userId,
            @ApiParam(value = "UUID of the Course", required = true)
            @PathVariable(name = "UUID_COURSE") String idCourse,
            @ApiParam(value = "Some attributes of the body of the Course to be modified", required = true)
            @RequestBody CourseRequestV1 coursePatchRequestV1) throws Exception {

        return new ResponseEntity<>(courseService.patchCourse(coursePatchRequestV1, UUID.fromString(idCourse), userId), HttpStatus.OK);
    }

}
