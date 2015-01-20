INSERT INTO applications
(name, app_type, description, technical_owner, home_page, ticket_url, documentation_url, business_owner, created_at, updated_at)
VALUES
  ('solar', 'desktop', 'policy admin system', 'joe@mail.com', 'www.no-home.com', 'http://jira', 'http://wiki', 'elizabeth@philafin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO applications
(name, app_type, description, technical_owner, home_page, ticket_url, documentation_url, business_owner, created_at, updated_at)
VALUES
  ('fmdb', 'web', 'fund mgmt system', 'joe@mail.com', 'www.fmdb.com', 'http://jira', 'http://wiki', 'kim@philafin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO applications
(name, app_type, description, technical_owner, home_page, ticket_url, documentation_url, business_owner, created_at, updated_at)
VALUES
  ('reportal', 'web', 'reports to clients', 'derryl@mail.com', 'reportal.com', 'http://jira', 'http://wiki', 'skip@philafin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

-- IT Managers

INSERT INTO employees
(username, password, enabled, fname, lname, manager_id, is_manager, title, department, phone_cell, phone_home, phone_office, email, tags, created_at, updated_at, hire_date, termination_date)
VALUES
  (
    'KJ1234', 'PWD1234', true, 'Kim', 'Jacques', 0, true, 'CIO', 'Information Technology', '222-111-3333', '111-222-3333', '433-343-3434', 'kj@mail.com', 'CIO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, DATE_ADD(CURRENT_DATE,INTERVAL 36 MONTH )
  );

INSERT INTO employees
(username, password, enabled, fname, lname, manager_id, is_manager, title, department, phone_cell, phone_home, phone_office, email, tags, created_at, updated_at, hire_date, termination_date)
VALUES
  (
    'GF1234', 'PWD1234', true, 'Gary', 'Francis', 0, true, 'apl manager', 'Information Technology', '222-111-3333', '111-222-3333', '433-343-3434', 'gf@mail.com', 'apl, solar', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, DATE_ADD(CURRENT_DATE,INTERVAL 36 MONTH )
  );


-- IT Direct Reports
INSERT INTO employees
(username, password, enabled, fname, lname, manager_id, is_manager, title, department, phone_cell, phone_home, phone_office, email, tags, created_at, updated_at, hire_date, termination_date)
VALUES (
  'JL1234', 'PWD1234', true, 'Juan', 'Lamadrid',
  (select m.id from employees m where m.lname = 'Jacques'),
  false, 'enterprise architect', 'Information Technology', '201-401-6603', '908-393-3933', '973-301-3486', 'juan@mail.com', 'fmdb, java',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, DATE_ADD(CURRENT_DATE,INTERVAL 36 MONTH )
);

INSERT INTO employees
(username, password, enabled, fname, lname, manager_id, is_manager, title, department, phone_cell, phone_home, phone_office, email, tags, created_at, updated_at, hire_date, termination_date)
VALUES (
  'DV1234', 'PWD1234', true, 'Derryl', 'Varghese',
  (select m.id from employees m where m.lname = 'Francis'),
  false, 'java developer', 'Information Technology', '444-333-2222', '222-333-4444', '121-232-3434', 'dv@mail.com', 'fmdb, reportal',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_DATE, DATE_ADD(CURRENT_DATE,INTERVAL 36 MONTH )
);

-- Resource Access
INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('ppdm', 'application', (select m.id from employees m where m.lname = 'Francis'), 'create_pp_adjustment',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('ppdm', 'application', (select m.id from employees m where m.lname = 'Francis'), 'delete_pp_adjustment',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('ppdm', 'application', (select m.id from employees m where m.lname = 'Francis'), 'update_pp_adjustment',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );


INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('solar', 'application', (select m.id from employees m where m.lname = 'Lamadrid'), 'create_case',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('solar', 'application', (select m.id from employees m where m.lname = 'Lamadrid'), 'delete_case',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO resource_permission
(resource_name, resource_type, employee_id, permission, created_at, updated_at)
VALUES
  ('solar', 'application', (select m.id from employees m where m.lname = 'Lamadrid'), 'update_case',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );


-- permission lookups
INSERT INTO permissions
(resource_name, resource_type, permission, description, tags, created_at, updated_at)
VALUES
    ('solar', 'application', 'delete-contract', 'allows users to delete any contract', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO permissions
(resource_name, resource_type, permission, description, tags, created_at, updated_at)
VALUES
    ('solar', 'application', 'trx-premium', 'allows users to enter premium transactions', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO permissions
(resource_name, resource_type, permission, description, tags, created_at, updated_at)
VALUES
    ('solar', 'application', 'ops-batch', 'allows users to start batch processing', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

INSERT INTO permissions
(resource_name, resource_type, permission, description, tags, created_at, updated_at)
VALUES
    ('fmdb-api', 'api', 'funds-list', 'ability to list funds', 'admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

