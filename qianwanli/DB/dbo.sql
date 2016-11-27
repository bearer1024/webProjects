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

Date: 2015-08-07 09:59:58
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
-- Records of dtproperties
-- ----------------------------

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
-- Records of huodong
-- ----------------------------

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
-- Records of t_admin
-- ----------------------------
INSERT INTO [dbo].[t_admin] ([userId], [userName], [userPw]) VALUES (N'1', N'a', N'a')
GO
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
[type] int NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_gongwen', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_gongwen'
, @level2type = 'COLUMN', @level2name = N'type'
GO

-- ----------------------------
-- Records of t_gongwen
-- ----------------------------
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362809698608', N'2013第一号公文', N'财务部', N'测试公文2013第一号公文', N'/upload/1362809694568.doc', N'测试公文2013第一号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362811362859', N'2013第二号公文', N'市场部', N'测试公文2013第二号公文', N'/upload/1362811359167.doc', N'测试公文2013第二号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1365438971662', N'测试', N'测试', N'测试', N'/upload/1365438968115.doc', N'测试', N'2013-04-09 00:35', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438310410918', N'2222', N'国务院', N'国务院', N'/upload/1438310403475.xlsx', N'哈哈', N'2015-07-31 10:39', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317803355', N'4444', N'444', N'4444', N'/upload/1438317784552.doc', N'44444', N'2015-07-31 12:42', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317916769', N'5555', N'5555', N'555', N'/upload/1438317907211.xlsx', N'534343', N'2015-07-31 12:44', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438318093760', N'123', N'12333', N'33232', N'/upload/1438318084928.pdf', N'323223', N'2015-07-31 12:47', null, null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438660694916', N'12222', N'2222', N'22222', N'/upload/baodao.docx', N'22222', N'2015-08-04 11:58', N'upload/baodao.swf', null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438691419955', N'111111111111111111111111111111111111111111111', N'222222222222222222222', N'22', N'/upload/123.pdf', N'22222222222222222222222222222222222222', N'2015-08-04 20:30', N'upload/123.swf', null)
GO
GO
INSERT INTO [dbo].[t_gongwen] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438691657565', N'12222', N'', N'', N'/upload/123.pdf', N'', N'2015-08-04 20:34', N'upload/123.swf', null)
GO
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
-- Records of t_organization
-- ----------------------------
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'1', N'后勤部', N'后勤部', N'0', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'2', N'市场部', N'生产部', N'0', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'3', N'市场一部', N'市场一部', N'2', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'4', N'市场二部', N'市场二部', N'2', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'5', N'人事部', N'人事部', N'0', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'7', N'后勤一部', N'后勤一部', N'1', N'no')
GO
GO
INSERT INTO [dbo].[t_organization] ([id], [name], [description], [p_id], [del]) VALUES (N'8', N'后勤二部', N'后勤二部', N'1', N'no')
GO
GO

-- ----------------------------
-- Table structure for t_shebei
-- ----------------------------
DROP TABLE [dbo].[t_shebei]
GO
CREATE TABLE [dbo].[t_shebei] (
[id] varchar(50) NOT NULL ,
[wenhao] varchar(50) NULL ,
[bumen] varchar(50) NULL ,
[title] varchar(50) NULL ,
[fujian] varchar(250) NULL ,
[beizhu] varchar(50) NULL ,
[tianjiashi] varchar(50) NULL ,
[fujianswfpath] varchar(250) NULL ,
[type] int NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_shebei', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_shebei'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_shebei'
, @level2type = 'COLUMN', @level2name = N'type'
GO

-- ----------------------------
-- Records of t_shebei
-- ----------------------------
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362809698608', N'2013第一号公文', N'财务部', N'测试公文2013第一号公文', N'/upload/1362809694568.doc', N'测试公文2013第一号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362811362859', N'2013第二号公文', N'市场部', N'测试公文2013第二号公文', N'/upload/1362811359167.doc', N'测试公文2013第二号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1365438971662', N'测试', N'测试', N'测试', N'/upload/1365438968115.doc', N'测试', N'2013-04-09 00:35', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438310410918', N'2222', N'国务院', N'国务院', N'/upload/1438310403475.xlsx', N'哈哈', N'2015-07-31 10:39', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317803355', N'4444', N'444', N'4444', N'/upload/1438317784552.doc', N'44444', N'2015-07-31 12:42', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317916769', N'5555', N'5555', N'555', N'/upload/1438317907211.xlsx', N'534343', N'2015-07-31 12:44', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438318093760', N'123', N'12333', N'33232', N'/upload/1438318084928.pdf', N'323223', N'2015-07-31 12:47', null, null)
GO
GO
INSERT INTO [dbo].[t_shebei] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438660694916', N'12222', N'2222', N'22222', N'/upload/baodao.docx', N'22222', N'2015-08-04 11:58', N'upload/baodao.swf', null)
GO
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
-- Records of t_yuangong
-- ----------------------------
INSERT INTO [dbo].[t_yuangong] ([id], [bianhao], [name], [sex], [loginname], [loginpw], [del]) VALUES (N'10', N'2012001', N'李强', N'男', N'2012001', N'000000', N'no')
GO
GO
INSERT INTO [dbo].[t_yuangong] ([id], [bianhao], [name], [sex], [loginname], [loginpw], [del]) VALUES (N'1438310823636', N'123333', N'王拽', N'男', N'zhuaizhuai', N'zhuai', N'no')
GO
GO
INSERT INTO [dbo].[t_yuangong] ([id], [bianhao], [name], [sex], [loginname], [loginpw], [del]) VALUES (N'1438311713154', N'12333444', N'哈哈', N'男', N'haha', N'000000', N'no')
GO
GO
INSERT INTO [dbo].[t_yuangong] ([id], [bianhao], [name], [sex], [loginname], [loginpw], [del]) VALUES (N'8', N'2012002', N'刘亮', N'男', N'2012002', N'000000', N'no')
GO
GO
INSERT INTO [dbo].[t_yuangong] ([id], [bianhao], [name], [sex], [loginname], [loginpw], [del]) VALUES (N'9', N'2012003', N'王刚', N'男', N'2012003', N'000000', N'no')
GO
GO

-- ----------------------------
-- Table structure for t_yunxing
-- ----------------------------
DROP TABLE [dbo].[t_yunxing]
GO
CREATE TABLE [dbo].[t_yunxing] (
[id] varchar(50) NOT NULL ,
[wenhao] varchar(50) NULL ,
[bumen] varchar(50) NULL ,
[title] varchar(50) NULL ,
[fujian] varchar(250) NULL ,
[beizhu] varchar(50) NULL ,
[tianjiashi] varchar(50) NULL ,
[fujianswfpath] varchar(250) NULL ,
[type] int NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_yunxing', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_yunxing'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'1,法律，2，行政法规，3，上级文件，4运行手册，5，上报填表，6，培训课件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_yunxing'
, @level2type = 'COLUMN', @level2name = N'type'
GO

-- ----------------------------
-- Records of t_yunxing
-- ----------------------------
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362809698608', N'2013第一号公文', N'财务部', N'测试公文2013第一号公文', N'/upload/1362809694568.doc', N'测试公文2013第一号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1362811362859', N'2013第二号公文', N'市场部', N'测试公文2013第二号公文', N'/upload/1362811359167.doc', N'测试公文2013第二号公文', N'2013-04-06 14:14', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1365438971662', N'测试', N'测试', N'测试', N'/upload/1365438968115.doc', N'测试', N'2013-04-09 00:35', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438310410918', N'2222', N'国务院', N'国务院', N'/upload/1438310403475.xlsx', N'哈哈', N'2015-07-31 10:39', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317803355', N'4444', N'444', N'4444', N'/upload/1438317784552.doc', N'44444', N'2015-07-31 12:42', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438317916769', N'5555', N'5555', N'555', N'/upload/1438317907211.xlsx', N'534343', N'2015-07-31 12:44', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438318093760', N'123', N'12333', N'33232', N'/upload/1438318084928.pdf', N'323223', N'2015-07-31 12:47', null, null)
GO
GO
INSERT INTO [dbo].[t_yunxing] ([id], [wenhao], [bumen], [title], [fujian], [beizhu], [tianjiashi], [fujianswfpath], [type]) VALUES (N'1438660694916', N'12222', N'2222', N'22222', N'/upload/baodao.docx', N'22222', N'2015-08-04 11:58', N'upload/baodao.swf', null)
GO
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
-- Indexes structure for table t_shebei
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_shebei
-- ----------------------------
ALTER TABLE [dbo].[t_shebei] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_yuangong
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_yuangong
-- ----------------------------
ALTER TABLE [dbo].[t_yuangong] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_yunxing
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_yunxing
-- ----------------------------
ALTER TABLE [dbo].[t_yunxing] ADD PRIMARY KEY ([id])
GO
