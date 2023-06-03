
CREATE TABLE hs_order_flow (
                               id varchar(1000) NOT NULL,
                               order_num varchar(1000) NULL,
                               order_name varchar(1000) NULL,
                               order_status varchar(1000) NULL,
                               demand_source varchar(1000) NULL,
                               order_type varchar(1000) NULL,
                               expect_achieve_date varchar(1000) NULL,
                               predict_achieve_date varchar(1000) NULL,
                               demand_charge varchar(1000) NULL,
                               rule_charge varchar(1000) NULL,
                               founder varchar(1000) NULL,
                               create_date varchar(1000) NULL,
                               is_delete varchar(1000) NULL,
                               create_time varchar(1000) NULL,
                               update_time varchar(1000) NULL
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4;

CREATE TABLE generation_rule (
                                 id bigint auto_increment NOT NULL,
                                 `type` varchar(100) NULL,
                                 val varchar(100) NULL,
                                 name varchar(100) NULL,
                                 update_time DATETIME DEFAULT now() NULL,
                                 rules varchar(2000) NULL,
                                 rule_type varchar(100) NULL,
                                 CONSTRAINT generation_rule_PK PRIMARY KEY (id)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE generation_history (
                                    id bigint auto_increment NOT NULL,
                                    `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                    val varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                    name varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                    update_time datetime DEFAULT CURRENT_TIMESTAMP  NULL,
                                    rules varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                    rule_type varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                                    insert_time DATETIME NULL,
                                    `day` date NULL,
                                    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci
    COMMENT='';
