/*
Navicat MySQL Data Transfer

Source Server         : pinpoint
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : ppdb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-03-01 11:37:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agent_event
-- ----------------------------
DROP TABLE IF EXISTS `agent_event`;
CREATE TABLE `agent_event` (
  `agentEventId` bigint(20) NOT NULL AUTO_INCREMENT,
  `agentId` varchar(16) NOT NULL,
  `eventTimestamp` bigint(20) NOT NULL,
  `eventType_code` int(11) DEFAULT NULL,
  `eventType_desc` varchar(32) DEFAULT NULL,
  `eventType_name` varchar(16) DEFAULT NULL,
  `eventType_messageType` varchar(16) DEFAULT NULL,
  `eventType_category` varchar(16) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `startTimestamp` bigint(20) NOT NULL,
  `eventBody` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`agentEventId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of agent_event
-- ----------------------------

-- ----------------------------
-- Table structure for agent_life_cycle
-- ----------------------------
DROP TABLE IF EXISTS `agent_life_cycle`;
CREATE TABLE `agent_life_cycle` (
  `apiMetaId` int(11) NOT NULL AUTO_INCREMENT,
  `agentId` varchar(16) NOT NULL,
  `startTimestamp` bigint(20) NOT NULL,
  `eventIdentifier` bigint(20) NOT NULL,
  `eventTimestamp` bigint(20) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `stateCode` int(11) DEFAULT NULL,
  `stateDesc` varchar(16) DEFAULT NULL,
  `stateName` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`apiMetaId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of agent_life_cycle
-- ----------------------------
INSERT INTO `agent_life_cycle` VALUES ('1', 'pp20170628', '2', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for api_meta_data
-- ----------------------------
DROP TABLE IF EXISTS `api_meta_data`;
CREATE TABLE `api_meta_data` (
  `apiMetaId` int(11) NOT NULL AUTO_INCREMENT,
  `agentId` varchar(16) NOT NULL,
  `agentStartTime` bigint(20) NOT NULL,
  `apiId` int(11) DEFAULT NULL,
  `apiInfo` varchar(256) DEFAULT NULL,
  `line` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`apiMetaId`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of api_meta_data
-- ----------------------------
INSERT INTO `api_meta_data` VALUES ('1', 'myapm20171108', '1513840552034', '1', 'org.apache.catalina.connector.Request.startAsync(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response)', '1620', '0');
INSERT INTO `api_meta_data` VALUES ('2', 'myapm20171108', '1513840552034', '-2', 'org.apache.catalina.core.StandardHostValve.invoke(org.apache.catalina.connector.Request request, org.apache.catalina.connector.Response response)', '110', '0');
INSERT INTO `api_meta_data` VALUES ('3', 'myapm20171108', '1513840552034', '2', 'Tomcat Servlet Asynchronous Process', '-1', '100');
INSERT INTO `api_meta_data` VALUES ('4', 'myapm20171108', '1513840552034', '-3', 'Tomcat Servlet Process', '-1', '100');
INSERT INTO `api_meta_data` VALUES ('5', 'myapm20171108', '1513840552034', '-1', 'Asynchronous Invocation', '-1', '200');
INSERT INTO `api_meta_data` VALUES ('6', 'myapm20171108', '1513841827801', '3', 'com.mysql.jdbc.NonRegisteringDriver.connect(java.lang.String url, java.util.Properties info)', '326', '0');
INSERT INTO `api_meta_data` VALUES ('7', 'myapm20171108', '1513841827801', '-4', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql)', '4479', '0');
INSERT INTO `api_meta_data` VALUES ('8', 'myapm20171108', '1513841827801', '4', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, int autoGenKeyIndex)', '4488', '0');
INSERT INTO `api_meta_data` VALUES ('9', 'myapm20171108', '1513841827801', '-5', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, int[] autoGenKeyIndexes)', '4610', '0');
INSERT INTO `api_meta_data` VALUES ('89', 'myapm20171108', '1513841827801', '5', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, java.lang.String[] autoGenKeyColNames)', '4624', '0');
INSERT INTO `api_meta_data` VALUES ('90', 'myapm20171108', '1513841827801', '7', 'com.mysql.jdbc.ConnectionImpl.prepareCall(java.lang.String sql, int resultSetType, int resultSetConcurrency)', '4395', '0');
INSERT INTO `api_meta_data` VALUES ('91', 'myapm20171108', '1513841827801', '-8', 'com.mysql.jdbc.ConnectionImpl.prepareCall(java.lang.String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)', '4438', '0');
INSERT INTO `api_meta_data` VALUES ('92', 'myapm20171108', '1513841827801', '-7', 'com.mysql.jdbc.ConnectionImpl.prepareCall(java.lang.String sql)', '4374', '0');
INSERT INTO `api_meta_data` VALUES ('93', 'myapm20171108', '1513841827801', '-6', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, int resultSetType, int resultSetConcurrency)', '4513', '0');
INSERT INTO `api_meta_data` VALUES ('94', 'myapm20171108', '1513841827801', '6', 'com.mysql.jdbc.ConnectionImpl.prepareStatement(java.lang.String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)', '4594', '0');
INSERT INTO `api_meta_data` VALUES ('95', 'myapm20171108', '1513841827801', '8', 'com.mysql.jdbc.ConnectionImpl.setAutoCommit(boolean autoCommitFlag)', '5292', '0');
INSERT INTO `api_meta_data` VALUES ('96', 'myapm20171108', '1513841827801', '-9', 'com.mysql.jdbc.ConnectionImpl.commit()', '1701', '0');
INSERT INTO `api_meta_data` VALUES ('97', 'myapm20171108', '1513841827801', '9', 'com.mysql.jdbc.ConnectionImpl.rollback()', '5027', '0');
INSERT INTO `api_meta_data` VALUES ('98', 'myapm20171108', '1513841827801', '-10', 'com.mysql.jdbc.PreparedStatement.execute()', '1274', '0');
INSERT INTO `api_meta_data` VALUES ('99', 'myapm20171108', '1513841827801', '10', 'com.mysql.jdbc.PreparedStatement.executeQuery()', '2221', '0');
INSERT INTO `api_meta_data` VALUES ('100', 'myapm20171108', '1513841827801', '-11', 'com.mysql.jdbc.PreparedStatement.executeUpdate()', '2361', '0');
INSERT INTO `api_meta_data` VALUES ('101', 'myapm20171108', '1513841827801', '11', 'com.mysql.jdbc.StatementImpl.executeQuery(java.lang.String sql)', '1471', '0');
INSERT INTO `api_meta_data` VALUES ('102', 'myapm20171108', '1513841827801', '-12', 'com.mysql.jdbc.StatementImpl.executeUpdate(java.lang.String sql)', '1725', '0');
INSERT INTO `api_meta_data` VALUES ('103', 'myapm20171108', '1513841827801', '12', 'com.mysql.jdbc.StatementImpl.executeUpdate(java.lang.String sql, int returnGeneratedKeys)', '1887', '0');
INSERT INTO `api_meta_data` VALUES ('104', 'myapm20171108', '1513841827801', '-13', 'com.mysql.jdbc.StatementImpl.execute(java.lang.String sql)', '732', '0');
INSERT INTO `api_meta_data` VALUES ('105', 'myapm20171108', '1513841827801', '13', 'com.mysql.jdbc.StatementImpl.execute(java.lang.String sql, int returnGeneratedKeys)', '988', '0');

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `id` varchar(16) NOT NULL,
  `applicationName` varchar(32) DEFAULT NULL,
  `applicationType` varchar(16) DEFAULT NULL,
  `code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of application
-- ----------------------------
INSERT INTO `application` VALUES ('11', 'appname1', 'TOMCAT', '1010');
INSERT INTO `application` VALUES ('12', 'mysqlname', 'MYSQL', '4040');
INSERT INTO `application` VALUES ('2', 'appname2', 'TOMCAT', '1010');
INSERT INTO `application` VALUES ('3', 'appname3', 'UNDERTOW', '2020');
INSERT INTO `application` VALUES ('33', 'redisname', 'REDIS', '5555');
INSERT INTO `application` VALUES ('4', 'appname4', 'JETTY', '3030');

-- ----------------------------
-- Table structure for application_trace_index
-- ----------------------------
DROP TABLE IF EXISTS `application_trace_index`;
CREATE TABLE `application_trace_index` (
  `atiId` int(11) NOT NULL AUTO_INCREMENT,
  `elapsed` int(11) NOT NULL,
  `err` int(11) NOT NULL,
  `agentId` varchar(16) NOT NULL,
  `acceptedTime` bigint(20) NOT NULL,
  `applicationName` varchar(16) NOT NULL,
  PRIMARY KEY (`atiId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of application_trace_index
-- ----------------------------

-- ----------------------------
-- Table structure for cpu_load
-- ----------------------------
DROP TABLE IF EXISTS `cpu_load`;
CREATE TABLE `cpu_load` (
  `agentId` varchar(16) NOT NULL,
  `startTimestamp` bigint(20) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `jvmCpuLoad` double NOT NULL,
  `systemCpuLoad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cpu_load
-- ----------------------------
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840559533', '0.7248995983935743', '0.7550200803212851');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840564530', '0.00404040404040404', '0.012121212121212121');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840569531', '0.008064516129032258', '0.004032258064516129');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840574530', '0.006072874493927126', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840579531', '0.006048387096774193', '0.004032258064516129');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840584531', '0.010121457489878543', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840589531', '0.006097560975609756', '0.012170385395537525');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840594531', '0.00808080808080808', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840599531', '0.008048289738430584', '0.004024144869215292');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840604530', '0.00404040404040404', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840609530', '0.008097165991902834', '0.0020242914979757085');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840614531', '0.004048582995951417', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840619531', '0.0101010101010101', '0.0101010101010101');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840624531', '0.008130081300813009', '0.01016260162601626');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840629531', '0.006048387096774193', '0.004032258064516129');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840634531', '0.006085192697768763', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840639531', '0.012170385395537525', '0.008113590263691683');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840644531', '0.00404040404040404', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840649530', '0.00808080808080808', '0.00808080808080808');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840654531', '0.006060606060606061', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840659531', '0.00808080808080808', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840664531', '0.006060606060606061', '0.00404040404040404');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840669530', '0.006072874493927126', '0.0020242914979757085');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840674530', '0.008064516129032258', '0.0020161290322580645');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840679531', '0.00404040404040404', '0.00808080808080808');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840684530', '0.00808080808080808', '0.0101010101010101');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840689531', '0.006060606060606061', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840694531', '0.00808080808080808', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840699530', '0.004048582995951417', '0.0020242914979757085');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840704531', '0.006060606060606061', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840709531', '0.010121457489878543', '0.01417004048582996');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840714530', '0.00404040404040404', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840719531', '0.00808080808080808', '0.00404040404040404');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840724531', '0.00404040404040404', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840729531', '0.09349593495934959', '0.0894308943089431');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840734531', '0.39387755102040817', '0.38979591836734695');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840739530', '0.016227180527383367', '0.02028397565922921');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840744531', '0.008064516129032258', '0.010080645161290322');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840749531', '0.010121457489878543', '0.008097165991902834');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840754530', '0.010121457489878543', '0.004048582995951417');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840759531', '0.008064516129032258', '0.004032258064516129');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513840552034', '1513840764531', '0.008097165991902834', '0.0020242914979757085');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841835174', '0.8356713426853707', '0.8777555110220441');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841840171', '0.00808080808080808', '0.00404040404040404');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841845172', '0.006060606060606061', '0.00404040404040404');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841850171', '0.006048387096774193', '0.0020161290322580645');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841855172', '0.006060606060606061', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841860171', '0.006060606060606061', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841865172', '0.008113590263691683', '0.010141987829614604');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841870171', '0.006060606060606061', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841875172', '0.006048387096774193', '0.0020161290322580645');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841880172', '0.006060606060606061', '0.00404040404040404');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841885172', '0.00404040404040404', '0.00808080808080808');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841890171', '0.07099391480730223', '0.06490872210953347');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841895171', '0.4364754098360656', '0.45491803278688525');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841900171', '0.03636363636363636', '0.04242424242424243');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841905171', '0.09939148073022312', '0.11359026369168357');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841910172', '0.026422764227642274', '0.03048780487804878');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841915171', '0.07566462167689161', '0.08588957055214724');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841920172', '0.0101010101010101', '0.00808080808080808');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841925172', '0.010141987829614604', '0.010141987829614604');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841930171', '0.046464646464646465', '0.05454545454545455');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841935172', '0.0326530612244898', '0.04081632653061225');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841940172', '0.07910750507099391', '0.08316430020283976');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841945172', '0.13347022587268995', '0.1806981519507187');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841950172', '0.008064516129032258', '0.010080645161290322');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841955171', '0.00808080808080808', '0.00808080808080808');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841960172', '0.006060606060606061', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841965172', '0.006060606060606061', '0.00202020202020202');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841970172', '0.006036217303822937', '0.004024144869215292');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841975172', '0.006085192697768763', '0');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841980171', '0.06262626262626263', '0.06868686868686869');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841985172', '0.05122950819672131', '0.06557377049180327');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841990172', '0.10105263157894737', '0.13025210084033614');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513841995172', '0.034482758620689655', '0.04878048780487805');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842000172', '0.034552845528455285', '0.04268292682926829');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842005172', '0.06326530612244899', '0.13877551020408163');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842010172', '0.03080082135523614', '0.043121149897330596');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842015171', '0.030425963488843813', '0.04665314401622718');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842020172', '0.032520325203252036', '0.036585365853658534');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842025172', '0.018218623481781375', '0.022267206477732795');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842030172', '0.016227180527383367', '0.02028397565922921');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842035172', '0.12653061224489795', '0.1384928716904277');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842040171', '0.04489795918367347', '0.05521472392638037');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842045172', '0.0389344262295082', '0.05122950819672131');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842050172', '0.024291497975708502', '0.03036437246963563');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842055172', '0.03258655804480652', '0.04684317718940936');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842060171', '0.036585365853658534', '0.0508130081300813');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842065171', '0.053061224489795916', '0.07142857142857142');
INSERT INTO `cpu_load` VALUES ('myapm20171108', '1513841827801', '1513842070172', '0.026262626262626265', '0.03838383838383838');

-- ----------------------------
-- Table structure for jvm_gc
-- ----------------------------
DROP TABLE IF EXISTS `jvm_gc`;
CREATE TABLE `jvm_gc` (
  `agentId` varchar(16) NOT NULL,
  `startTimestamp` bigint(20) NOT NULL,
  `heapUsed` bigint(20) NOT NULL,
  `heapMax` bigint(20) NOT NULL,
  `timestamp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of jvm_gc
-- ----------------------------
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '22778960', '249364480', '1513840559533');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23030816', '249364480', '1513840564530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23030944', '249364480', '1513840569531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23031136', '249364480', '1513840574530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23081736', '249364480', '1513840579531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23081928', '249364480', '1513840584531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23729264', '249364480', '1513840589531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23729392', '249364480', '1513840594531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23729616', '249364480', '1513840599531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23729872', '249364480', '1513840604530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23780456', '249364480', '1513840609530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '23780712', '249364480', '1513840614531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24114240', '249364480', '1513840619531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24114496', '249364480', '1513840624531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24114720', '249364480', '1513840629531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24124464', '249364480', '1513840634531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24255720', '249364480', '1513840639531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24255720', '249364480', '1513840644531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840649530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840654531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840659531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840664531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840669530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '24650808', '249364480', '1513840674530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25094864', '249364480', '1513840679531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25094864', '249364480', '1513840684530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25094864', '249364480', '1513840689531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25097000', '249364480', '1513840694531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25348656', '249364480', '1513840699530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25348656', '249364480', '1513840704531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25665488', '249364480', '1513840709531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25665488', '249364480', '1513840714530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25665488', '249364480', '1513840719531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '25665488', '249364480', '1513840724531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '20734152', '249364480', '1513840729531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '28541312', '249364480', '1513840734531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '30466800', '249364480', '1513840739530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '30573248', '249364480', '1513840744531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '30744520', '249364480', '1513840749531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '31910360', '249364480', '1513840754530');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '31910360', '249364480', '1513840759531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513840552034', '31914624', '249364480', '1513840764531');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24367432', '249364480', '1513841835174');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24484920', '249364480', '1513841840171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24485048', '249364480', '1513841845172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24485240', '249364480', '1513841850171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24485368', '249364480', '1513841855172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24485560', '249364480', '1513841860171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25202232', '249364480', '1513841865172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25202328', '249364480', '1513841870171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25202392', '249364480', '1513841875172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25202488', '249364480', '1513841880172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25202584', '249364480', '1513841885172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '31127312', '249364480', '1513841890171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28996288', '249364480', '1513841895171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '30985968', '249364480', '1513841900171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24736512', '249364480', '1513841905171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '26698880', '249364480', '1513841910172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '32315856', '249364480', '1513841915171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '32548640', '249364480', '1513841920172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '33081104', '249364480', '1513841925172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '23793840', '249364480', '1513841930171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '27783904', '249364480', '1513841935172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '29601688', '249364480', '1513841940172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '27851448', '249364480', '1513841945172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28140600', '249364480', '1513841950172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28478776', '249364480', '1513841955171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28480840', '249364480', '1513841960172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28498136', '249364480', '1513841965172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28498136', '249364480', '1513841970172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28634768', '249364480', '1513841975172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '31460168', '249364480', '1513841980171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '24970608', '249364480', '1513841985172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '23143976', '249364480', '1513841990172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '28107064', '249364480', '1513841995172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '32014152', '249364480', '1513842000172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '26262328', '249364480', '1513842005172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '31175912', '249364480', '1513842010172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '22545216', '249364480', '1513842015171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '25275272', '249364480', '1513842020172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '26557544', '249364480', '1513842025172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '29387776', '249364480', '1513842030172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '33261576', '249364480', '1513842035172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '27394840', '249364480', '1513842040171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '32659032', '249364480', '1513842045172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '22922848', '249364480', '1513842050172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '27844576', '249364480', '1513842055172');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '32729512', '249364480', '1513842060171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '26895728', '249364480', '1513842065171');
INSERT INTO `jvm_gc` VALUES ('myapm20171108', '1513841827801', '29840944', '249364480', '1513842070172');

-- ----------------------------
-- Table structure for map_statistics_caller
-- ----------------------------
DROP TABLE IF EXISTS `map_statistics_caller`;
CREATE TABLE `map_statistics_caller` (
  `callerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` bigint(20) NOT NULL,
  `caller_code` int(11) NOT NULL,
  `caller_desc` varchar(16) NOT NULL,
  `callee_code` int(11) NOT NULL,
  `callee_desc` varchar(16) NOT NULL,
  `isError` int(11) NOT NULL,
  `caller_appId` int(11) NOT NULL,
  `callee_appId` int(11) NOT NULL,
  PRIMARY KEY (`callerId`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of map_statistics_caller
-- ----------------------------
INSERT INTO `map_statistics_caller` VALUES ('219', '1513840729507', '1010', 'TOMCAT', '2101', 'MYSQL', '0', '11', '22');
INSERT INTO `map_statistics_caller` VALUES ('233', '1513840729506', '1010', 'TOMCAT', '3101', 'REDIS', '1', '11', '33');
INSERT INTO `map_statistics_caller` VALUES ('308', '1513840721611', '2', 'USER', '1010', 'TOMCAT', '0', '1', '2');
INSERT INTO `map_statistics_caller` VALUES ('312', '1513840729507', '1010', 'TOMCAT', '2101', 'MYSQL', '0', '11', '22');

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rpm` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `threshold` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('1', '5', '911111111', 'MONITOR_RESPNOSE_TIME', '1111111111');
INSERT INTO `monitor` VALUES ('2', '5', '911111111', 'MONITOR_RESPNOSE_TIME', '1111111111');
INSERT INTO `monitor` VALUES ('5', '3', null, 'MONITOR_RESPNOSE_TIME', '1111111111');
INSERT INTO `monitor` VALUES ('6', '3', null, 'MONITOR_RESPNOSE_TIME', '1111111111');

-- ----------------------------
-- Table structure for monitorpro
-- ----------------------------
DROP TABLE IF EXISTS `monitorpro`;
CREATE TABLE `monitorpro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appId` varchar(16) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `strategyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of monitorpro
-- ----------------------------
INSERT INTO `monitorpro` VALUES ('1', '11', '1,2', '1');
INSERT INTO `monitorpro` VALUES ('2', '11', '1,2', '1');
INSERT INTO `monitorpro` VALUES ('3', '11', '1,2', '1');
INSERT INTO `monitorpro` VALUES ('4', '11', '1,2', '1');
INSERT INTO `monitorpro` VALUES ('5', '11', '1,2', '1');

-- ----------------------------
-- Table structure for monitorstrategy
-- ----------------------------
DROP TABLE IF EXISTS `monitorstrategy`;
CREATE TABLE `monitorstrategy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `monitors` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of monitorstrategy
-- ----------------------------
INSERT INTO `monitorstrategy` VALUES ('1', 'm1', 'app', '1,2');
INSERT INTO `monitorstrategy` VALUES ('2', 'name..', 'outService', '5,6,');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of person
-- ----------------------------

-- ----------------------------
-- Table structure for pp_user
-- ----------------------------
DROP TABLE IF EXISTS `pp_user`;
CREATE TABLE `pp_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pp_user
-- ----------------------------
INSERT INTO `pp_user` VALUES ('1', 'admin', '123456');

-- ----------------------------
-- Table structure for span
-- ----------------------------
DROP TABLE IF EXISTS `span`;
CREATE TABLE `span` (
  `spanId` bigint(20) NOT NULL AUTO_INCREMENT,
  `transactionId` varchar(128) NOT NULL,
  `agentId` varchar(16) NOT NULL,
  `agentStartTime` bigint(20) NOT NULL,
  `transactionSequence` bigint(20) NOT NULL,
  `acceptedTime` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `applicationId` varchar(16) NOT NULL,
  `version` int(11) NOT NULL,
  `serviceType` int(11) NOT NULL,
  `parentSpanId` bigint(20) NOT NULL,
  `startTime` bigint(20) NOT NULL,
  `elapsed` int(11) DEFAULT NULL,
  `rpc` varchar(32) DEFAULT NULL,
  `endPoint` varchar(32) DEFAULT NULL,
  `remoteAddr` varchar(32) DEFAULT NULL,
  `apiId` int(11) DEFAULT NULL,
  `errCode` int(11) DEFAULT NULL,
  `exceptionId` int(11) DEFAULT NULL,
  `exceptionMessage` varchar(32) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `loggingTransactionInfo` int(11) DEFAULT NULL,
  `acceptorHost` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`spanId`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of span
-- ----------------------------
INSERT INTO `span` VALUES ('1', '1', 'myapm20171108', '1513840552034', '1', '1513840721120', '0', '11', '0', '1010', '-1', '1517899658510', '122', '/hcbtra', '192.168.213.126:8080', '192.168.213.127', '-3', '1', '0', null, '0', '0', null);
INSERT INTO `span` VALUES ('2', '1', 'myapm20171108', '1513840552034', '2', '1513840722745', '0', '12', '0', '1010', '-1', '1516959606000', '66', '/hcbtra111', '192.168.213.126:8080', '192.168.213.127', '-3', '1', '0', null, '0', '0', null);
INSERT INTO `span` VALUES ('3', '1', 'myapm20171108', '1513840552034', '3', '1513840723979', '0', '11', '0', '1010', '-1', '1516959606000', '1', '/hcbtra111', '192.168.213.126:8080', '192.168.213.127', '-3', '1', '0', null, '0', '0', null);
INSERT INTO `span` VALUES ('4', '2', 'myapm20171108', '1513840552034', '4', '1513840729442', '0', '11', '0', '1010', '-1', '1516959606000', '1935', '/hcbtra1', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);
INSERT INTO `span` VALUES ('5', '2', 'myapm20171108', '1513840552034', '5', '1513840729632', '0', '11', '0', '1010', '-1', '1516959606000', '1', '/hcbtra111', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);
INSERT INTO `span` VALUES ('6', '2', 'myapm20171108', '1513840552034', '6', '1513840734973', '0', '11', '0', '1010', '-1', '1516959606000', '2', '/hcbtra111', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);
INSERT INTO `span` VALUES ('7', '5', '13234', '43223423452435', '55', '254234524354542', '0', '11', '0', '1010', '-1', '11', '2134', '/aa', 'localhost:127.0.0.1', '10.221.192.11', '-3', '1', '1', '', null, null, null);
INSERT INTO `span` VALUES ('8', '6', 'myapm20171108', '1513840552034', '7', '1513840736502', '0', '11', '0', '1010', '-1', '22', '0', '/aa', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);
INSERT INTO `span` VALUES ('176', 'myapm2017110815138418278011', 'myapm20171108', '1513841827801', '1', '1513841881892', '0', 'MyTestApp1', '0', '1010', '-1', '1516959606000', '136', '/test', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);
INSERT INTO `span` VALUES ('177', 'myapm2017110815138418278012', 'myapm20171108', '1513841827801', '2', '1513841890049', '0', 'MyTestApp1', '0', '1010', '-1', '1516959606000', '2175', '/funny/ppupdate', '192.168.213.126:8080', '192.168.213.127', '-3', '0', '0', 'null point', '0', '0', null);

-- ----------------------------
-- Table structure for span_event
-- ----------------------------
DROP TABLE IF EXISTS `span_event`;
CREATE TABLE `span_event` (
  `spanEventId` bigint(20) NOT NULL AUTO_INCREMENT,
  `transactionId` varchar(128) NOT NULL,
  `spanId` bigint(20) NOT NULL,
  `startElapsed` bigint(20) NOT NULL,
  `endElapsed` int(11) NOT NULL,
  `sequence` int(11) NOT NULL,
  `depth` int(11) NOT NULL,
  `serviceType` int(11) NOT NULL,
  `rpc` varchar(32) DEFAULT NULL,
  `endPoint` varchar(32) DEFAULT NULL,
  `destinationId` varchar(32) DEFAULT NULL,
  `apiId` int(11) DEFAULT NULL,
  `nextSpanId` bigint(20) DEFAULT NULL,
  `exceptionId` int(11) DEFAULT NULL,
  `exceptionMessage` varchar(32) DEFAULT NULL,
  `nextAsyncId` int(11) DEFAULT NULL,
  `sqlId` int(11) DEFAULT NULL,
  `parentSpaneventId` bigint(20) DEFAULT NULL,
  `nextSpaneventId` bigint(20) DEFAULT NULL,
  `beforeSpaneventId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`spanEventId`),
  KEY `spanId` (`spanId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of span_event
-- ----------------------------
INSERT INTO `span_event` VALUES ('1', '1', '1', '11', '2', '1', '1', '1010', null, null, '1', '1', null, '1', 'err......', null, '0', null, '2', null);
INSERT INTO `span_event` VALUES ('2', '1', '1', '22', '2', '2', '-1', '1010', '/aa/bb', '127.0.0.1:8080', '1', '2', '3', '1', 'err......', '1', '2', null, '8', '1');
INSERT INTO `span_event` VALUES ('3', '1', '1', '33', '2', '3', '2', '1010', null, '127.0.0.1', '1', '3', null, '1', 'err......', '1', '0', '2', '6', null);
INSERT INTO `span_event` VALUES ('4', '1', '1', '44', '2', '4', '3', '1010', null, '127.0.0.1', '1', '4', null, null, '', '1', '0', '3', '5', null);
INSERT INTO `span_event` VALUES ('5', '1', '1', '55', '2', '5', '-1', '1010', '/11/bb', '127.0.0.1', '1', '5', null, null, '', '1', '0', '3', null, '4');
INSERT INTO `span_event` VALUES ('6', '1', '1', '1517827658510', '33', '6', '-1', '8080', null, '127.0.0.1', '1', '6', null, '1', 'err......', '1', '1', '2', '7', '3');
INSERT INTO `span_event` VALUES ('7', '1', '1', '1517899658510', '39', '7', '-1', '8080', null, null, '1', '7', null, null, null, '1', '0', '2', null, '6');
INSERT INTO `span_event` VALUES ('8', '1', '1', '1517899658510', '22', '8', '-1', '8080', null, '127.0.0.1', '2', '8', '9', '2', 'err......', '11', '1', null, '9', '2');
INSERT INTO `span_event` VALUES ('9', '1', '1', '1517899658510', '11', '9', '-1', '8080', '/ff', '127.0.0.1', '1', '8', null, '2', 'err......', '1', '1', null, null, '8');
INSERT INTO `span_event` VALUES ('10', '6', '7', '10', '2', '10', '-1', '1010', null, '127.0.0.1', '1', '10', null, '1', 'null point exception', null, null, null, null, null);
INSERT INTO `span_event` VALUES ('11', '6', '8', '22', '2', '11', '-1', '1010', null, '127.0.01', '1', '10', null, '2', 'err......', null, null, null, null, null);

-- ----------------------------
-- Table structure for sql_meta_data_ver2
-- ----------------------------
DROP TABLE IF EXISTS `sql_meta_data_ver2`;
CREATE TABLE `sql_meta_data_ver2` (
  `sqlMetaDataId` int(11) NOT NULL AUTO_INCREMENT,
  `agentId` varchar(16) NOT NULL,
  `agentStartTime` bigint(20) NOT NULL,
  `sqlId` int(11) NOT NULL,
  `sqlInfo` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`sqlMetaDataId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sql_meta_data_ver2
-- ----------------------------
INSERT INTO `sql_meta_data_ver2` VALUES ('1', 'myapm20171108', '1513841827801', '-1', 'update person set name=?,delete * from person');
INSERT INTO `sql_meta_data_ver2` VALUES ('2', 'myapm20171108', '1513841827801', '1', 'select * from person');
INSERT INTO `sql_meta_data_ver2` VALUES ('3', 'myapm20171108', '151334141341', '2', 'delete * from person');

-- ----------------------------
-- Table structure for string_meta_data
-- ----------------------------
DROP TABLE IF EXISTS `string_meta_data`;
CREATE TABLE `string_meta_data` (
  `stringMetaDataId` int(11) NOT NULL AUTO_INCREMENT,
  `agentId` varchar(16) NOT NULL,
  `agentStartTime` bigint(20) NOT NULL,
  `stringId` int(11) NOT NULL,
  `stringValue` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`stringMetaDataId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of string_meta_data
-- ----------------------------
INSERT INTO `string_meta_data` VALUES ('13', 'myapm20171108', '1513841827801', '-1', 'jdbc:mysql://192.168.213.126:3306/test');

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` varchar(128) NOT NULL,
  `rpc` varchar(32) DEFAULT NULL,
  `startTime` bigint(20) DEFAULT NULL,
  `elapsed` int(11) DEFAULT NULL,
  `endtime` mediumtext,
  `application_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES ('1', '/aa/bb', '1519463521284', '200', '1517671992280', '11');
INSERT INTO `transaction` VALUES ('2', '/aa/bb', '1519463521284', '600', '1517671992280', '11');
INSERT INTO `transaction` VALUES ('3', '/aa/bb', '1519463521284', '1000', '1517671992280', '11');
INSERT INTO `transaction` VALUES ('4', '/aa/bb', '1519463521284', '100', '1517671992280', '22');
INSERT INTO `transaction` VALUES ('5', '/ee/ff', '1519463521284', '1000', '1517671992280', '33');
INSERT INTO `transaction` VALUES ('6', '/ee/ff', '1519463521284', '1100', '1517671992280', '11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `number` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `name` varchar(150) NOT NULL,
  `pwd` varchar(150) NOT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`number`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'hcb1', 'pp123', '152', 'hhccbb123@163.com');
INSERT INTO `user` VALUES ('2', '2', 'hcb2', 'pp123', '15237899120', 'hhccbb1123@163.com');
