-- public.customer definition

-- Drop table

--DROP TABLE public.customer;

CREATE TABLE IF NOT EXISTS public.customer (
	"id" uuid NOT NULL,
	"name" varchar NULL,
	"lastModifiedBy" varchar NULL,
	"lastmodifieddatetime" timestamptz(0) NULL,
	CONSTRAINT customers_pk PRIMARY KEY (id)
);


-- public.service definition

-- Drop table

--DROP TABLE public.service;

CREATE TABLE IF NOT EXISTS public.service (
	"id" uuid NOT NULL,
	"serviceName" varchar NOT NULL,
	"priceForMac" numeric NOT NULL,
	"lastModifiedBy" varchar NULL,
	"priceForWindows" numeric NULL,
	"lastModifiedDateTime" timestamptz(0) NULL,
	"comments" varchar NULL,
	CONSTRAINT service_pk PRIMARY KEY (id)
);


-- public."user" definition

-- Drop table

--DROP TABLE public."user";

CREATE TABLE IF NOT EXISTS public."user" (
	"id" uuid NOT NULL,
	"username" varchar NOT NULL,
	"password" varchar NOT NULL,
	"lastModifiedBy" varchar NULL,
	"lastmodifieddatetime" timestamptz(0) NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);


-- public.customer_service definition

-- Drop table

--DROP TABLE public.customer_service;

CREATE TABLE IF NOT EXISTS public.customer_service (
	"id" uuid NOT NULL,
	"customerid" uuid NULL,
	"serviceid" uuid NULL,
	"lastModifiedBy" varchar NULL,
	"lastmodifieddatetime" timestamptz(0) NULL,
	CONSTRAINT customer_service_pk PRIMARY KEY (id),
	CONSTRAINT customer_service_fk FOREIGN KEY (customerid) REFERENCES customer(id),
	CONSTRAINT customer_service_fk_1 FOREIGN KEY (serviceid) REFERENCES service(id)
);


-- public.device definition

-- Drop table

--DROP TABLE public.device;

CREATE TABLE IF NOT EXISTS public.device (
	"id" uuid NOT NULL,
	"systemName" varchar NULL,
	"deviceType" varchar NULL,
	"lastModifiedBy" varchar NULL,
	"customerid" uuid NULL,
	"lastmodifieddatetime" timestamptz(0) NULL,
	"devicecost" numeric NULL,
	CONSTRAINT device_pk PRIMARY KEY (id),
	CONSTRAINT devices_fk FOREIGN KEY (customerid) REFERENCES customer(id)
);