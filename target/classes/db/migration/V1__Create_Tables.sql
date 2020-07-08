CREATE TABLE IF NOT EXISTS course (
	id UUID PRIMARY KEY,
	course_title varchar(255) ,
	user_nick varchar(255) ,
	max_numeric_of_participants int,
	number_of_actual_participants int,
    creation_course_date timestamptz,
    last_change_date timestamptz,
    status varchar(255),
    user_id varchar(255)
)