CREATE TABLE IF NOT EXISTS branch (
	id UUID PRIMARY KEY,
	name varchar(255) ,
	email varchar(255) ,
	super bool ,
	address jsonb ,
	right_of_access_to_the_courses varchar(255)
);
