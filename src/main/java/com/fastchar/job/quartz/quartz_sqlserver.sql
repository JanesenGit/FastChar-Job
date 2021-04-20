
-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_BLOB_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_BLOB_TRIGGERS]

CREATE TABLE [dbo].[QRTZ_BLOB_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [BLOB_DATA] varbinary(max)  NULL
)


ALTER TABLE [dbo].[QRTZ_BLOB_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_CALENDARS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_CALENDARS]


CREATE TABLE [dbo].[QRTZ_CALENDARS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CALENDAR_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CALENDAR] varbinary(max)  NOT NULL
)


ALTER TABLE [dbo].[QRTZ_CALENDARS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_CRON_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_CRON_TRIGGERS]


CREATE TABLE [dbo].[QRTZ_CRON_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [CRON_EXPRESSION] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TIME_ZONE_ID] nvarchar(80) COLLATE Chinese_PRC_CI_AS  NULL
)


ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_FIRED_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_FIRED_TRIGGERS]


CREATE TABLE [dbo].[QRTZ_FIRED_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [ENTRY_ID] nvarchar(95) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [INSTANCE_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [FIRED_TIME] bigint  NOT NULL,
  [SCHED_TIME] bigint  NOT NULL,
  [PRIORITY] int  NOT NULL,
  [STATE] nvarchar(16) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NULL,
  [JOB_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NULL,
  [IS_NONCONCURRENT] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NULL,
  [REQUESTS_RECOVERY] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NULL
)


ALTER TABLE [dbo].[QRTZ_FIRED_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_JOB_DETAILS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_JOB_DETAILS]


CREATE TABLE [dbo].[QRTZ_JOB_DETAILS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DESCRIPTION] nvarchar(250) COLLATE Chinese_PRC_CI_AS  NULL,
  [JOB_CLASS_NAME] nvarchar(250) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [IS_DURABLE] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [IS_NONCONCURRENT] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [IS_UPDATE_DATA] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [REQUESTS_RECOVERY] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_DATA] varbinary(max)  NULL
)


ALTER TABLE [dbo].[QRTZ_JOB_DETAILS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_LOCKS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_LOCKS]


CREATE TABLE [dbo].[QRTZ_LOCKS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [LOCK_NAME] nvarchar(40) COLLATE Chinese_PRC_CI_AS  NOT NULL
)


ALTER TABLE [dbo].[QRTZ_LOCKS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_PAUSED_TRIGGER_GRPS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS]


CREATE TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL
)


ALTER TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_SCHEDULER_STATE]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_SCHEDULER_STATE]


CREATE TABLE [dbo].[QRTZ_SCHEDULER_STATE] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [INSTANCE_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [LAST_CHECKIN_TIME] bigint  NOT NULL,
  [CHECKIN_INTERVAL] bigint  NOT NULL
)


ALTER TABLE [dbo].[QRTZ_SCHEDULER_STATE] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_SIMPLE_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS]


CREATE TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [REPEAT_COUNT] bigint  NOT NULL,
  [REPEAT_INTERVAL] bigint  NOT NULL,
  [TIMES_TRIGGERED] bigint  NOT NULL
)


ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_SIMPROP_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS]


CREATE TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [STR_PROP_1] nvarchar(512) COLLATE Chinese_PRC_CI_AS  NULL,
  [STR_PROP_2] nvarchar(512) COLLATE Chinese_PRC_CI_AS  NULL,
  [STR_PROP_3] nvarchar(512) COLLATE Chinese_PRC_CI_AS  NULL,
  [INT_PROP_1] int  NULL,
  [INT_PROP_2] int  NULL,
  [LONG_PROP_1] bigint  NULL,
  [LONG_PROP_2] bigint  NULL,
  [DEC_PROP_1] decimal(13,4)  NULL,
  [DEC_PROP_2] decimal(13,4)  NULL,
  [BOOL_PROP_1] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NULL,
  [BOOL_PROP_2] nvarchar(1) COLLATE Chinese_PRC_CI_AS  NULL
)


ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[QRTZ_TRIGGERS]') AND type IN ('U'))
	DROP TABLE [dbo].[QRTZ_TRIGGERS]


CREATE TABLE [dbo].[QRTZ_TRIGGERS] (
  [SCHED_NAME] nvarchar(120) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [JOB_GROUP] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [DESCRIPTION] nvarchar(250) COLLATE Chinese_PRC_CI_AS  NULL,
  [NEXT_FIRE_TIME] bigint  NULL,
  [PREV_FIRE_TIME] bigint  NULL,
  [PRIORITY] int  NULL,
  [TRIGGER_STATE] nvarchar(16) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [TRIGGER_TYPE] nvarchar(8) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [START_TIME] bigint  NOT NULL,
  [END_TIME] bigint  NULL,
  [CALENDAR_NAME] nvarchar(150) COLLATE Chinese_PRC_CI_AS  NULL,
  [MISFIRE_INSTR] smallint  NULL,
  [JOB_DATA] varbinary(max)  NULL
)


ALTER TABLE [dbo].[QRTZ_TRIGGERS] SET (LOCK_ESCALATION = TABLE)



-- ----------------------------
-- Primary Key structure for table QRTZ_BLOB_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_BLOB_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_BLO__20F4F10160083D91] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_CALENDARS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_CALENDARS] ADD CONSTRAINT [PK__QRTZ_CAL__DEBD34E062E4AA3C] PRIMARY KEY CLUSTERED ([SCHED_NAME], [CALENDAR_NAME])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_CRON_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_CRO__20F4F10165C116E7] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_FIRED_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_FIRED_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_FIR__7793D06D689D8392] PRIMARY KEY CLUSTERED ([SCHED_NAME], [ENTRY_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_JOB_DETAILS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_JOB_DETAILS] ADD CONSTRAINT [PK__QRTZ_JOB__E0CAAB8A6B79F03D] PRIMARY KEY CLUSTERED ([SCHED_NAME], [JOB_NAME], [JOB_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_LOCKS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_LOCKS] ADD CONSTRAINT [PK__QRTZ_LOC__7D2E9A036E565CE8] PRIMARY KEY CLUSTERED ([SCHED_NAME], [LOCK_NAME])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_PAUSED_TRIGGER_GRPS] ADD CONSTRAINT [PK__QRTZ_PAU__696155E97132C993] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_SCHEDULER_STATE
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_SCHEDULER_STATE] ADD CONSTRAINT [PK__QRTZ_SCH__C8C3A19E740F363E] PRIMARY KEY CLUSTERED ([SCHED_NAME], [INSTANCE_NAME])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_SIM__20F4F10176EBA2E9] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Primary Key structure for table QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_SIM__20F4F10179C80F94] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Indexes structure for table QRTZ_TRIGGERS
-- ----------------------------
CREATE NONCLUSTERED INDEX [SCHED_NAME]
ON [dbo].[QRTZ_TRIGGERS] (
  [SCHED_NAME] ASC,
  [JOB_NAME] ASC,
  [JOB_GROUP] ASC
)



-- ----------------------------
-- Primary Key structure for table QRTZ_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_TRIGGERS] ADD CONSTRAINT [PK__QRTZ_TRI__20F4F1017CA47C3F] PRIMARY KEY CLUSTERED ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
ON [PRIMARY]



-- ----------------------------
-- Foreign Keys structure for table QRTZ_BLOB_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_BLOB_TRIGGERS] ADD CONSTRAINT [qrtz_blob_triggers_ibfk_1] FOREIGN KEY ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) ON DELETE NO ACTION ON UPDATE NO ACTION



-- ----------------------------
-- Foreign Keys structure for table QRTZ_CRON_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_CRON_TRIGGERS] ADD CONSTRAINT [qrtz_cron_triggers_ibfk_1] FOREIGN KEY ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) ON DELETE NO ACTION ON UPDATE NO ACTION



-- ----------------------------
-- Foreign Keys structure for table QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_SIMPLE_TRIGGERS] ADD CONSTRAINT [qrtz_simple_triggers_ibfk_1] FOREIGN KEY ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) ON DELETE NO ACTION ON UPDATE NO ACTION



-- ----------------------------
-- Foreign Keys structure for table QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_SIMPROP_TRIGGERS] ADD CONSTRAINT [qrtz_simprop_triggers_ibfk_1] FOREIGN KEY ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) REFERENCES [dbo].[QRTZ_TRIGGERS] ([SCHED_NAME], [TRIGGER_NAME], [TRIGGER_GROUP]) ON DELETE NO ACTION ON UPDATE NO ACTION



-- ----------------------------
-- Foreign Keys structure for table QRTZ_TRIGGERS
-- ----------------------------
ALTER TABLE [dbo].[QRTZ_TRIGGERS] ADD CONSTRAINT [qrtz_triggers_ibfk_1] FOREIGN KEY ([SCHED_NAME], [JOB_NAME], [JOB_GROUP]) REFERENCES [dbo].[QRTZ_JOB_DETAILS] ([SCHED_NAME], [JOB_NAME], [JOB_GROUP]) ON DELETE NO ACTION ON UPDATE NO ACTION
