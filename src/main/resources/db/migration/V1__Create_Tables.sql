CREATE TABLE IF NOT EXISTS course (
	id UUID PRIMARY KEY,
	course_title varchar(255) ,
	user_nick varchar(255) ,
	max_numeric_of_participants int(3),
	number_of_actual_participants int(3),
    creation_course_date Date,
    last_change_date Date,
    status varchar(255)
);
