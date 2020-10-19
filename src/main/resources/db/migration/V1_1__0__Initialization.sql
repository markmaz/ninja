CREATE TABLE IF NOT EXISTS public.customer (
                                               "id" uuid NOT NULL,
                                               "name" varchar NULL,
                                               "lastmodifiedby" varchar NULL,
                                               "lastmodifieddatetime" timestamptz NULL,
                                               CONSTRAINT customers_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.service (
                                              "id" uuid NOT NULL,
                                              "servicename" varchar NOT NULL,
                                              "priceformac" numeric NOT NULL,
                                              "lastmodifiedby" varchar NULL,
                                              "priceforwindows" numeric NULL,
                                              "lastmodifieddatetime" timestamptz NULL,
                                              "comments" varchar NULL,
                                              CONSTRAINT service_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."user" (
                                             "id" uuid NOT NULL,
                                             "username" varchar NOT NULL,
                                             "password" varchar NOT NULL,
                                             "lastmodifiedby" varchar NULL,
                                             "lastmodifieddatetime" timestamptz NULL,
                                             CONSTRAINT user_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS public.customer_service (
                                                       "id" uuid NOT NULL,
                                                       "customerid" uuid NULL,
                                                       "serviceid" uuid NULL,
                                                       "lastmodifiedby" varchar NULL,
                                                       "lastmodifieddatetime" timestamptz NULL,
                                                       CONSTRAINT customer_service_pk PRIMARY KEY (id),
                                                       CONSTRAINT customer_service_fk FOREIGN KEY (customerid) REFERENCES customer(id),
                                                       CONSTRAINT customer_service_fk_1 FOREIGN KEY (serviceid) REFERENCES service(id)
);

CREATE TABLE IF NOT EXISTS public.device (
                                             "id" uuid NOT NULL,
                                             "systemname" varchar NULL,
                                             "devicetype" varchar NULL,
                                             "customerid" uuid NULL,
                                             "lastmodifieddatetime" timestamptz NULL,
                                             "devicecost" numeric NULL,
                                             "lastmodifiedby" varchar NULL,
                                             CONSTRAINT device_pk PRIMARY KEY (id),
                                             CONSTRAINT devices_fk FOREIGN KEY (customerid) REFERENCES customer(id)
);

INSERT INTO public.user (id,username,password,lastModifiedBy,lastmodifieddatetime) VALUES
('946258b1-8068-4855-8979-40aa9f93b3f5','markmaz','$2a$10$4vHza5eR1PQlQjuLG.PDneGvLN2l4c5Yy6OeH5dFmRrtKJXKc12A.','markmaz','2020-10-17 23:12:18-05');

INSERT INTO public.customer (id,name,lastModifiedBy,lastmodifieddatetime) VALUES ('bab6dfe7-3f74-4fdd-9eed-45b4990346fa','Test Customer','markmaz','2020-10-18 08:14:51-05');
INSERT INTO public.customer (id,name,lastModifiedBy,lastmodifieddatetime) VALUES ('19cdc4fb-c0bf-497c-a9c8-d8a007af3bb4','My New Customer','markmaz','2020-10-18 09:46:26-05');
INSERT INTO public.customer (id,name,lastModifiedBy,lastmodifieddatetime) VALUES ('201d960f-d090-47b5-92f9-c2a7868293bf','Mark','markmaz','2020-10-18 13:33:39-05');

INSERT INTO public.service (id,servicename,priceformac,lastmodifiedby,priceforwindows,lastmodifieddatetime,comments) VALUES ('fab870c6-d870-48d9-b679-fd9b55a17797','TeamViewer',1.00,'markmaz',1.00,'2020-10-18 11:18:05-05',NULL);
INSERT INTO public.service (id,servicename,priceformac,lastmodifiedby,priceforwindows,lastmodifieddatetime,comments) VALUES ('1b664f9a-9918-49bb-b0a0-c49edf4d3721','Anti-virus',7.00,'markmaz',5.00,'2020-10-18 13:31:40-05',NULL);
INSERT INTO public.service (id,servicename,priceformac,lastmodifiedby,priceforwindows,lastmodifieddatetime,comments) VALUES ('e5956e88-8512-4499-bbb4-72af100b0ba3','Cloudberry',3.00,'markmaz',3.00,'2020-10-18 13:32:26-05','To back up data in their devices');
INSERT INTO public.service (id,servicename,priceformac,lastmodifiedby,priceforwindows,lastmodifieddatetime,comments) VALUES ('3fad76d4-0b10-4e17-a335-93f591ed2b48','PSA',2.00,'markmaz',2.00,'2020-10-18 13:32:47-05','Ticketing system');

INSERT INTO public.device (id,systemname,deviceType,lastmodifiedby,customerid,lastmodifieddatetime,devicecost) VALUES ('1388ba22-d91d-4e28-9d9f-e4110210f07e','New Device','MAC','markmaz','19cdc4fb-c0bf-497c-a9c8-d8a007af3bb4','2020-10-18 10:11:22-05',NULL);
INSERT INTO public.device (id,systemname,deviceType,lastmodifiedby,customerid,lastmodifieddatetime,devicecost) VALUES ('655c5ab9-d2f6-4007-b5aa-de8451027785','New server','WINDOWS_SERVER','markmaz','201d960f-d090-47b5-92f9-c2a7868293bf','2020-10-18 13:52:46-05',4);
INSERT INTO public.device (id,systemname,deviceType,lastmodifiedby,customerid,lastmodifieddatetime,devicecost) VALUES ('b6e2f78f-4ff5-4968-be5e-369d7b73bc23','Macbook Pro','MAC','markmaz','201d960f-d090-47b5-92f9-c2a7868293bf','2020-10-18 14:13:24-05',4);
INSERT INTO public.device (id,systemname,deviceType,lastmodifiedby,customerid,lastmodifieddatetime,devicecost) VALUES ('805e905b-dcfa-49cf-8aa3-ff4928f1013f','Macbook Pro 2','MAC','markmaz','201d960f-d090-47b5-92f9-c2a7868293bf','2020-10-18 14:13:41-05',4);

INSERT INTO public.customer_service (id,customerid,serviceid,lastmodifiedby,lastmodifieddatetime) VALUES ('cf6e86ee-f9fe-4f20-b22b-7705086073ed','19cdc4fb-c0bf-497c-a9c8-d8a007af3bb4','fab870c6-d870-48d9-b679-fd9b55a17797','markmaz','2020-10-18 12:28:34-05');
INSERT INTO public.customer_service (id,customerid,serviceid,lastmodifiedby,lastmodifieddatetime) VALUES ('38387ea8-e287-4a8b-a802-84fa8a5b6263','201d960f-d090-47b5-92f9-c2a7868293bf','fab870c6-d870-48d9-b679-fd9b55a17797','markmaz','2020-10-18 13:54:14-05');
INSERT INTO public.customer_service (id,customerid,serviceid,lastmodifiedby,lastmodifieddatetime) VALUES ('b32acf11-211a-4391-bc01-243dc7fb169a','201d960f-d090-47b5-92f9-c2a7868293bf','1b664f9a-9918-49bb-b0a0-c49edf4d3721','markmaz','2020-10-18 14:15:43-05');

