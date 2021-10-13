/*
 Navicat Premium Data Transfer

 Source Server         : local postgre
 Source Server Type    : PostgreSQL
 Source Server Version : 90623
 Source Host           : localhost:5432
 Source Catalog        : test
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90623
 File Encoding         : 65001

 Date: 13/10/2021 20:10:37
*/


-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS "public"."log";
CREATE TABLE "public"."log" (
  "log_id" SERIAL ,
  "user_id" SERIAL,
  "log_ip" varchar(256) COLLATE "pg_catalog"."default",
  "log_operation" varchar(64) COLLATE "pg_catalog"."default",
  "log_method" varchar(128) COLLATE "pg_catalog"."default",
  "log_params" varchar(8192) COLLATE "pg_catalog"."default",
  "log_time" int8,
  "log_gmt_create" timestamp(6) NOT NULL,
  "log_gmt_modified" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."log"."log_id" IS '日志id';
COMMENT ON COLUMN "public"."log"."user_id" IS '用户的id';
COMMENT ON COLUMN "public"."log"."log_ip" IS '调用方法的ip地址';
COMMENT ON COLUMN "public"."log"."log_operation" IS '方法的描述';
COMMENT ON COLUMN "public"."log"."log_method" IS '调用的controller中的那个方法';
COMMENT ON COLUMN "public"."log"."log_params" IS '请求参数';
COMMENT ON COLUMN "public"."log"."log_time" IS '执行时间';
COMMENT ON COLUMN "public"."log"."log_gmt_create" IS '创建日志时间';
COMMENT ON COLUMN "public"."log"."log_gmt_modified" IS '修改日志时间';

-- ----------------------------
-- Table structure for tqsdata
-- ----------------------------
DROP TABLE IF EXISTS "public"."tqsdata";
CREATE TABLE "public"."tqsdata" (
  "id" SERIAL ,
  "gs_number" int2,
  "times" int2,
  "code" varchar COLLATE "pg_catalog"."default",
  "get_date" date,
  "f_ac" float8,
  "f_yz1" float8,
  "f_yz2" float8,
  "q_mo" float8,
  "q_mf" float8,
  "q_moa" float8,
  "q_mfa" float8,
  "q_mo1" float8,
  "q_mf1" float8,
  "q_mo2" float8,
  "q_mf2" float8,
  "p_to" float8,
  "p_tf" float8,
  "p_io" float8,
  "p_if" float8,
  "p_c" float8,
  "p_ac1" float8,
  "p_ac2" float8,
  "p_ac3" float8,
  "p_ac4" float8,
  "t_t1" float8,
  "t_t2" float8,
  "t_b1" float8,
  "t_b2" float8,
  "t_h" float8,
  "t_vo" float8,
  "t_vf" float8,
  "t_hb1" float8,
  "t_hb2" float8,
  "t_hb3" float8,
  "t_hb4" float8,
  "t_ce1" float8,
  "t_ce2" float8,
  "t_ce3" float8,
  "t_ce4" float8,
  "t_ce5" float8,
  "t_o" float8,
  "t_f" float8,
  "d_o" float8,
  "d_f" float8,
  "f" float8,
  "f_v" float8,
  "f_v_xx" float8,
  "i_sv" float8,
  "i_sva" float8,
  "b" float8,
  "b_a" float8,
  "b_th" float8,
  "b_tha" float8,
  "r_m" float8,
  "r_ma" float8,
  "r_m_xx" float8,
  "y_c" float8,
  "y_ca" float8,
  "p_c_top" float8,
  "t_c" float8,
  "f_ac_top" float8,
  "t_ac" float8,
  "t_90_a" float8,
  "t_10_a" float8,
  "t_90_ac" float8,
  "t_10_ac" float8,
  "p_c0" float8,
  "f_ac0" float8,
  "delta" float8,
  "beta" float8,
  "alpha" float8,
  "gamma" float8
)
;
COMMENT ON COLUMN "public"."tqsdata"."id" IS '主键';
COMMENT ON COLUMN "public"."tqsdata"."gs_number" IS '试车台号';
COMMENT ON COLUMN "public"."tqsdata"."times" IS '试车次数';
COMMENT ON COLUMN "public"."tqsdata"."code" IS '试验代号';
COMMENT ON COLUMN "public"."tqsdata"."get_date" IS '试验日期';
COMMENT ON COLUMN "public"."tqsdata"."f_ac" IS '高空舱内推力';
COMMENT ON COLUMN "public"."tqsdata"."f_yz1" IS '高空舱内侧向推力';
COMMENT ON COLUMN "public"."tqsdata"."f_yz2" IS '高空舱内侧向推力';
COMMENT ON COLUMN "public"."tqsdata"."q_mo" IS '氧化剂流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mf" IS '燃料流量';
COMMENT ON COLUMN "public"."tqsdata"."q_moa" IS '氧化剂流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mfa" IS '燃料流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mo1" IS '氧化剂涡轮流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mf1" IS '燃料涡轮流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mo2" IS '氧化剂涡轮流量';
COMMENT ON COLUMN "public"."tqsdata"."q_mf2" IS '燃料涡轮流量';
COMMENT ON COLUMN "public"."tqsdata"."p_to" IS '氧化剂箱压强';
COMMENT ON COLUMN "public"."tqsdata"."p_tf" IS '燃料箱压强';
COMMENT ON COLUMN "public"."tqsdata"."p_io" IS '氧化剂阀前压强';
COMMENT ON COLUMN "public"."tqsdata"."p_if" IS '燃料阀前压强';
COMMENT ON COLUMN "public"."tqsdata"."p_c" IS '燃烧室压强';
COMMENT ON COLUMN "public"."tqsdata"."p_ac1" IS '真空舱压强';
COMMENT ON COLUMN "public"."tqsdata"."p_ac2" IS '真空舱压强';
COMMENT ON COLUMN "public"."tqsdata"."p_ac3" IS '真空舱压强';
COMMENT ON COLUMN "public"."tqsdata"."p_ac4" IS '真空舱压强';
COMMENT ON COLUMN "public"."tqsdata"."t_t1" IS '推力室喉部温度';
COMMENT ON COLUMN "public"."tqsdata"."t_t2" IS '推力室喉部温度';
COMMENT ON COLUMN "public"."tqsdata"."t_b1" IS '推力室身部温度';
COMMENT ON COLUMN "public"."tqsdata"."t_b2" IS '推力室身部温度';
COMMENT ON COLUMN "public"."tqsdata"."t_h" IS '推力室头部温度';
COMMENT ON COLUMN "public"."tqsdata"."t_vo" IS '氧化剂阀壁面温度';
COMMENT ON COLUMN "public"."tqsdata"."t_vf" IS '燃料阀壁面温度';
COMMENT ON COLUMN "public"."tqsdata"."t_hb1" IS '推力室头身焊缝温度';
COMMENT ON COLUMN "public"."tqsdata"."t_hb2" IS '推力室头身焊缝温度';
COMMENT ON COLUMN "public"."tqsdata"."t_hb3" IS '推力室头身焊缝温度';
COMMENT ON COLUMN "public"."tqsdata"."t_hb4" IS '推力室头身焊缝温度';
COMMENT ON COLUMN "public"."tqsdata"."t_ce1" IS '真空舱温度';
COMMENT ON COLUMN "public"."tqsdata"."t_ce2" IS '真空舱温度';
COMMENT ON COLUMN "public"."tqsdata"."t_ce3" IS '真空舱温度';
COMMENT ON COLUMN "public"."tqsdata"."t_ce4" IS '真空舱温度';
COMMENT ON COLUMN "public"."tqsdata"."t_ce5" IS '真空舱温度';
COMMENT ON COLUMN "public"."tqsdata"."t_o" IS '氧化剂温度';
COMMENT ON COLUMN "public"."tqsdata"."t_f" IS '燃料温度';
COMMENT ON COLUMN "public"."tqsdata"."d_o" IS '氧化剂密度';
COMMENT ON COLUMN "public"."tqsdata"."d_f" IS '燃料密度';
COMMENT ON COLUMN "public"."tqsdata"."f" IS '发动机振动';
COMMENT ON COLUMN "public"."tqsdata"."f_v" IS '真空推力';
COMMENT ON COLUMN "public"."tqsdata"."f_v_xx" IS '额定真空推力';
COMMENT ON COLUMN "public"."tqsdata"."i_sv" IS '真空比冲';
COMMENT ON COLUMN "public"."tqsdata"."i_sva" IS '真空比冲';
COMMENT ON COLUMN "public"."tqsdata"."b" IS '燃烧室综合参数';
COMMENT ON COLUMN "public"."tqsdata"."b_a" IS '燃烧室综合参数';
COMMENT ON COLUMN "public"."tqsdata"."b_th" IS '燃烧室理论综合参数';
COMMENT ON COLUMN "public"."tqsdata"."b_tha" IS '燃烧室理论综合参数';
COMMENT ON COLUMN "public"."tqsdata"."r_m" IS '混合比';
COMMENT ON COLUMN "public"."tqsdata"."r_ma" IS '混合比';
COMMENT ON COLUMN "public"."tqsdata"."r_m_xx" IS '额定混合比';
COMMENT ON COLUMN "public"."tqsdata"."y_c" IS '燃烧室效率';
COMMENT ON COLUMN "public"."tqsdata"."y_ca" IS '燃烧室效率';
COMMENT ON COLUMN "public"."tqsdata"."p_c_top" IS '燃烧室压力峰值';
COMMENT ON COLUMN "public"."tqsdata"."t_c" IS '燃烧室压力峰值时间';
COMMENT ON COLUMN "public"."tqsdata"."f_ac_top" IS '推力峰值';
COMMENT ON COLUMN "public"."tqsdata"."t_ac" IS '推力峰值时间';
COMMENT ON COLUMN "public"."tqsdata"."t_90_a" IS '燃烧室压力T90';
COMMENT ON COLUMN "public"."tqsdata"."t_10_a" IS '燃烧室压力T10';
COMMENT ON COLUMN "public"."tqsdata"."t_90_ac" IS '推力T90';
COMMENT ON COLUMN "public"."tqsdata"."t_10_ac" IS '推力T10';
COMMENT ON COLUMN "public"."tqsdata"."p_c0" IS '燃烧室压强零位';
COMMENT ON COLUMN "public"."tqsdata"."f_ac0" IS '高空舱推力零位';
COMMENT ON COLUMN "public"."tqsdata"."delta" IS '横移距离';
COMMENT ON COLUMN "public"."tqsdata"."beta" IS '横移位置角';
COMMENT ON COLUMN "public"."tqsdata"."alpha" IS '偏斜角';
COMMENT ON COLUMN "public"."tqsdata"."gamma" IS '偏斜位置角';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "user_id" SERIAL ,
  "user_account" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "user_password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "user_password_clear_text" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "user_role" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(64) COLLATE "pg_catalog"."default",
  "user_phone" varchar(64) COLLATE "pg_catalog"."default",
  "user_email" varchar(64) COLLATE "pg_catalog"."default",
  "user_status" int4 NOT NULL,
  "user_is_enabled" int4 NOT NULL,
  "user_gmt_create" timestamp(6) NOT NULL,
  "user_gmt_modified" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."user"."user_id" IS '用户的id';
COMMENT ON COLUMN "public"."user"."user_account" IS '用户的账号，用来登录';
COMMENT ON COLUMN "public"."user"."user_password" IS '用户密码';
COMMENT ON COLUMN "public"."user"."user_password_clear_text" IS '用户明文密码';
COMMENT ON COLUMN "public"."user"."user_role" IS '用户的身份，用来权限管理';
COMMENT ON COLUMN "public"."user"."user_name" IS '用户个人信息，姓名';
COMMENT ON COLUMN "public"."user"."user_phone" IS '用户个人信息，电话';
COMMENT ON COLUMN "public"."user"."user_email" IS '用户个人信息，Email';
COMMENT ON COLUMN "public"."user"."user_status" IS '用户状态，1启用，0禁用，默认1 ，管理员设定。';
COMMENT ON COLUMN "public"."user"."user_is_enabled" IS '用户是否启用，1启用，0未启用';
COMMENT ON COLUMN "public"."user"."user_gmt_create" IS '用户创建时间';
COMMENT ON COLUMN "public"."user"."user_gmt_modified" IS '用户最后修改信息时间';
COMMENT ON TABLE "public"."user" IS '用户类';


-- ----------------------------
-- Primary Key structure for table log
-- ----------------------------
ALTER TABLE "public"."log" ADD CONSTRAINT "log_pkey" PRIMARY KEY ("log_id");

-- ----------------------------
-- Primary Key structure for table tqsdata
-- ----------------------------
ALTER TABLE "public"."tqsdata" ADD CONSTRAINT "tqsdata_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("user_id");

-- ----------------------------
-- Foreign Keys structure for table log
-- ----------------------------
ALTER TABLE "public"."log" ADD CONSTRAINT "FK_LOG_HAS_USER" FOREIGN KEY ("user_id") REFERENCES "public"."user" ("user_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
