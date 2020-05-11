create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

-- INSERT INTO USER (user_type, email, first_name, last_name, password) VALUES ('ADMIN','admin@miu.edu','admin','admin','$2a$10$/8XXI4KpM2cp8evP7.NXjOmLLXFNHj9VtqB9wOUojWOk3fAISRvx.');
-- INSERT INTO USER (user_type, email, first_name, last_name, password)  VALUES ('BUYER','buyer@miu.edu','John','Doe','$2a$10$rH.SaKTlzH0W4mbQ6JkZz.Ss7whuKuwBUyFIkr1OY.15SYSC0jJ2O');
-- INSERT INTO USER (user_type, email, first_name, last_name, password)  VALUES ('SELLER','seller@miu.edu','Mister','Bean','$2a$10$gKkVcQ71UXf7yC3l1A4cD.C5YAD5dYo6cUQyyS4J/Q5qokZnJ94x.');




