CREATE TABLE IF NOT EXISTS branch (
	id UUID PRIMARY KEY,
<<<<<<< Updated upstream
	name varchar(255) ,
	email varchar(255) ,
	super bool ,
	address jsonb ,
	right_of_access_to_the_courses varchar(255)
);
=======
	course_title varchar(255) ,
	user_nick varchar(255) ,
	max_numeric_of_participants int,
	number_of_actual_participants int,
    creation_course_date timestamptz,
    last_change_date timestamptz,
    status varchar(255)
)
>>>>>>> Stashed changes
