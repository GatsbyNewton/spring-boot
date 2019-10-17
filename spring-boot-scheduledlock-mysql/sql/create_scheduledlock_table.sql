CREATE TABLE IF NOT EXISTS shedlock (
    name VARCHAR(256) NOT NULL DEFAULT '' COMMENT '定时任务的名字',
    lock_until TIMESTAMP NULL COMMENT '锁的结束时间',
    locked_at TIMESTAMP NULL COMMENT '锁的开始时间',
    locked_by VARCHAR(256) NOT NULL DEFAULT '' COMMENT '',
    PRIMARY KEY (name)
)ENGINE=InnoDB;