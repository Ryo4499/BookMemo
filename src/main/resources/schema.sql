CREATE TABLE "users" (
	"user_id" SERIAL NOT NULL,
	"user_name" VARCHAR(50) NOT NULL,
	"user_email" VARCHAR(128) NOT NULL,
	"user_password" VARCHAR(256) NOT NULL,
	"remember_user" VARCHAR(256) NOT NULL, 
	PRIMARY KEY ("user_id"),
	UNIQUE ("user_email")
);

CREATE TABLE "memos" (
	"memo_id" BIGSERIAL NOT NULL,
	"title" VARCHAR(30) NOT NULL,
	"content" VARCHAR(3000) NOT NULL,
	"category" VARCHAR(30) NOT NULL,
	"book_name" VARCHAR(50) NOT NULL,
	"user_id" INTEGER NOT NULL,
	"created_date" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_date" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("memo_id"),
	CONSTRAINT "FK__users" FOREIGN KEY ("user_id") REFERENCES "public"."users" ("user_id") ON UPDATE CASCADE ON DELETE CASCADE
);
