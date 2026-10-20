--liquibase formatted sql

--changeset andrey:004-add-task-user-index
CREATE INDEX idx_tasks_user_id ON tasks (user_id);
--rollback DROP INDEX idx_tasks_user_id;