--liquibase formatted sql

--changeset andrey:003-add-task-indexes
CREATE INDEX idx_tasks_category ON tasks (category);
CREATE INDEX idx_tasks_status ON tasks (status);
--rollback DROP INDEX idx_tasks_category; DROP INDEX idx_tasks_status;
