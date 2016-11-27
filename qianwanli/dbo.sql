/*
Navicat SQL Server Data Transfer

Source Server         : SQLserver
Source Server Version : 105000
Source Host           : localhost:1433
Source Database       : db_gongwen
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2015-09-14 10:01:53
*/


-- ----------------------------
-- Table structure for dtproperties
-- ----------------------------
DROP TABLE [dbo].[dtproperties]
GO
CREATE TABLE [dbo].[dtproperties] (
[id] int NOT NULL ,
[objectid] int NULL ,
[property] varchar(55) NOT NULL ,
[value] varchar(255) NULL ,
[uvalue] nvarchar(255) NULL ,
[lvalue] image NULL ,
[version] int NOT NULL 
)


GO

-- ----------------------------
-- Table structure for huodong
-- ----------------------------
DROP TABLE [dbo].[huodong]
GO
CREATE TABLE [dbo].[huodong] (
[id] varchar(50) NOT NULL ,
[title] varchar(100) NULL ,
[time] datetime NULL ,
[image] varchar(100) NULL ,
[content] text NULL ,
[author] varchar(20) NULL 
)


GO

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE [dbo].[t_admin]
GO
CREATE TABLE [dbo].[t_admin] (
[userId] int NOT NULL ,
[userName] varchar(55) NULL ,
[userPw] varchar(50) NULL 
)


GO

-- ----------------------------
-- Table structure for t_gongwen
-- ----------------------------
DROP TABLE [dbo].[t_gongwen]
GO
CREATE TABLE [dbo].[t_gongwen] (
[id] varchar(50) NOT NULL ,
[wenhao] varchar(50) NULL ,
[bumen] varchar(50) NULL ,
[title] varchar(50) NULL ,
[fujian] varchar(250) NULL ,
[beizhu] varchar(50) NULL ,
[tianjiashi] varchar(50) NULL ,
[fujianswfpath] varchar(250) NULL ,
[type] int NULL ,
[whois] varchar(50) NULL DEFAULT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_gongwen', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件，7，设备资料，8，设备管理，9，日常规范，10，日常维护'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件，7，设备资料，8，设备管理，9，日常规范，10，日常维护'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_gongwen', 
'COLUMN', N'whois')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上报填表，用员工id区分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'whois'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上报填表，用员工id区分'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'whois'
GO

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE [dbo].[t_organization]
GO
CREATE TABLE [dbo].[t_organization] (
[id] varchar(50) NOT NULL ,
[name] varchar(55) NULL ,
[description] varchar(50) NULL ,
[p_id] varchar(50) NULL ,
[del] varchar(50) NULL 
)


GO

-- ----------------------------
-- Table structure for t_yuangong
-- ----------------------------
DROP TABLE [dbo].[t_yuangong]
GO
CREATE TABLE [dbo].[t_yuangong] (
[id] varchar(50) NOT NULL ,
[bianhao] varchar(55) NULL ,
[name] varchar(50) NULL ,
[sex] varchar(50) NULL ,
[loginname] varchar(50) NULL ,
[loginpw] varchar(50) NULL ,
[del] varchar(50) NULL 
)


GO

-- ----------------------------
-- Indexes structure for table huodong
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table huodong
-- ----------------------------
ALTER TABLE [dbo].[huodong] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_admin
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_admin
-- ----------------------------
ALTER TABLE [dbo].[t_admin] ADD PRIMARY KEY ([userId])
GO

-- ----------------------------
-- Indexes structure for table t_gongwen
-- ----------------------------
CREATE INDEX [type_index] ON [dbo].[t_gongwen]
([type] ASC) 
GO

-- ----------------------------
-- Primary Key structure for table t_gongwen
-- ----------------------------
ALTER TABLE [dbo].[t_gongwen] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_organization
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_organization
-- ----------------------------
ALTER TABLE [dbo].[t_organization] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_yuangong
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_yuangong
-- ----------------------------
ALTER TABLE [dbo].[t_yuangong] ADD PRIMARY KEY ([id])
GO
