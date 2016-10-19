--问题表增加回答数字段
ALTER TABLE problem ADD answer_count INT(11) NOT NULL DEFAULT 0 COMMENT '回答数'

