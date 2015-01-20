DROP TABLE IF EXISTS resource_permission;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS employees;

CREATE TABLE applications
(
    id                INT NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50),
    app_type          VARCHAR(50),
    description       VARCHAR(255),
    technical_owner   VARCHAR(50),
    home_page         VARCHAR(50),
    ticket_url        VARCHAR(50),
    documentation_url VARCHAR(100),
    business_owner    VARCHAR(50),
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    CONSTRAINT applications_pkey PRIMARY KEY (id)
);

CREATE TABLE employees
(
    id               INT          NOT NULL AUTO_INCREMENT,
    username         VARCHAR(50),
    password         VARCHAR(100) NOT NULL,
    enabled          BOOLEAN      NOT NULL,
    fname            VARCHAR(50),
    lname            VARCHAR(50),
    manager_id       INT,
    is_manager       BOOLEAN      NOT NULL DEFAULT FALSE,
    title            VARCHAR(50),
    department       VARCHAR(50),
    phone_cell       VARCHAR(50),
    phone_home       VARCHAR(50),
    phone_office     VARCHAR(50),
    email            VARCHAR(50),
    tags             VARCHAR(255),
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    hire_date        DATE,
    termination_date DATE,
    CONSTRAINT employees_pkey PRIMARY KEY (id)
);

-- list of employees and any associated permissions.
CREATE TABLE resource_permission
(
    id            INT         NOT NULL AUTO_INCREMENT,
    resource_name VARCHAR(50) NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    employee_id   INT         NOT NULL,
    permission    VARCHAR(50) NOT NULL,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    CONSTRAINT resource_permission_pkey PRIMARY KEY (id)
);

ALTER TABLE resource_permission ADD CONSTRAINT fk_employeed_id FOREIGN KEY (employee_id) REFERENCES employees (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

-- maintains master list of all resources and their list of possible permissions.
CREATE TABLE permissions
(
    id            INT         NOT NULL AUTO_INCREMENT,
    resource_name VARCHAR(50) NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    permission    VARCHAR(50) NOT NULL,
    description   VARCHAR(255),
    tags          VARCHAR(255),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    CONSTRAINT permissions_pkey PRIMARY KEY (id)
);
