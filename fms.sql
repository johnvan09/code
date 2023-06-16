/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : fms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 16/06/2023 08:56:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for budget
-- ----------------------------
DROP TABLE IF EXISTS `budget`;
CREATE TABLE `budget`  (
  `bid` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NULL DEFAULT NULL,
  `id` int(0) NULL DEFAULT NULL,
  `bname` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bmoney` float NULL DEFAULT NULL,
  `binfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btime` date NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `FK_Reference_11`(`id`) USING BTREE,
  INDEX `FK_Reference_6`(`userId`) USING BTREE,
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`id`) REFERENCES `moneycategory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of budget
-- ----------------------------

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `userId` int(0) NULL DEFAULT NULL,
  `friendId` int(0) NULL DEFAULT NULL,
  `forbidden` tinyint(0) NULL DEFAULT NULL,
  INDEX `FK_Reference_1`(`userId`) USING BTREE,
  INDEX `FK_Reference_2`(`friendId`) USING BTREE,
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`friendId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------

-- ----------------------------
-- Table structure for invite
-- ----------------------------
DROP TABLE IF EXISTS `invite`;
CREATE TABLE `invite`  (
  `userId1` int(0) NULL DEFAULT NULL,
  `userId2` int(0) NULL DEFAULT NULL,
  `wid` int(0) NULL DEFAULT NULL,
  INDEX `FK_Reference_7`(`userId1`) USING BTREE,
  INDEX `FK_Reference_8`(`wid`) USING BTREE,
  INDEX `FK_Reference_9`(`userId2`) USING BTREE,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`userId1`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`wid`) REFERENCES `wishlist` (`wid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`userId2`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invite
-- ----------------------------
INSERT INTO `invite` VALUES (1, 1, 12);

-- ----------------------------
-- Table structure for money
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money`  (
  `mid` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NULL DEFAULT NULL,
  `id` int(0) NULL DEFAULT NULL,
  `mname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mnums` float NULL DEFAULT NULL,
  `mway` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mtime` date NULL DEFAULT NULL,
  `beizhu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE,
  INDEX `FK_Reference_10`(`id`) USING BTREE,
  INDEX `FK_Reference_4`(`userId`) USING BTREE,
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`id`) REFERENCES `moneycategory` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of money
-- ----------------------------
INSERT INTO `money` VALUES (1, 1, 2, '早饭', 10, '微信', '2023-06-14', '真贵');
INSERT INTO `money` VALUES (2, 1, 2, '吃饭', 10, '支付宝', '2023-06-14', '好吃');
INSERT INTO `money` VALUES (3, 69187354, 5, '午饭', 15, '微信', '2023-06-13', 'null');
INSERT INTO `money` VALUES (4, 69187354, 5, '自行车', 2, '微信', '2023-06-13', '无');
INSERT INTO `money` VALUES (5, 69187354, 2, '火锅', 80, '支付宝', '2023-06-27', '');
INSERT INTO `money` VALUES (6, 69187354, 3, '游乐场', 100, '微信', '2023-06-07', '');
INSERT INTO `money` VALUES (7, 69187354, 1, '生活费', 1000, '微信', '2023-05-31', '');
INSERT INTO `money` VALUES (8, 69187354, 3, '游戏', 50, '支付宝', '2023-06-13', '无');

-- ----------------------------
-- Table structure for moneycategory
-- ----------------------------
DROP TABLE IF EXISTS `moneycategory`;
CREATE TABLE `moneycategory`  (
  `id` int(0) NOT NULL,
  `inorout` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moneycategory
-- ----------------------------
INSERT INTO `moneycategory` VALUES (1, '收入', '生活费');
INSERT INTO `moneycategory` VALUES (2, '支出', '餐饮费');
INSERT INTO `moneycategory` VALUES (3, '支出', '娱乐费');
INSERT INTO `moneycategory` VALUES (4, '收入', '兼职收入');
INSERT INTO `moneycategory` VALUES (5, '支出', '交通费');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `nid` int(0) NOT NULL AUTO_INCREMENT,
  `nname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ninfo` varchar(15000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nimg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `writer` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nlnums` int(0) NULL DEFAULT NULL,
  `ncdate` date NULL DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '如何赚取第一桶金', '<p>原标题：首个国产新冠特效药多少钱？能否应对奥密克戎？</p><p>到目前为止，中和抗体对所有主要的突变，包括“阿尔法”、“贝塔”、“伽马”、“德尔塔”，包括最新的“奥密克戎”变异株都保持了中和活性。</p><p>作者：张尼 任靖</p><p>近期，国家药监局宣布应急批准腾盛博药旗下控股子公司腾盛华创新冠病毒中和抗体联合治疗药物安巴韦单抗注射液及罗米司韦单抗注射液注册申请。</p><p>国产新冠“特效药”问世，外界关注度颇高。面对“奥密克戎”变异株，这款药品能否有效应对？今后的产能是否有保障？对此，腾盛博药首席财务官、生物医药博士李安康接受了中新网“中国新观察”栏目专访，进行独家解读。</p><p><strong>中新网：作为新冠病毒中和抗体联合治疗药物，此次获批的新药和目前其他在研药物的技术路线有什么区别？优势是什么？</strong></p><p><strong>李安康：</strong>中和抗体联合疗法就是从人体内找到专门针对新冠病毒的中和抗体，对它进行进一步优化，同时挑选一对结合在新冠病毒不同部位的中和抗体进行大规模的生产，做成稳定、成分可控的药物。</p><p>我们的中和抗体主要优势体现在，<strong>第一，它是一对长效抗体</strong>，通过基因工程修饰，它在人体内存在和有活性的时间比一般的抗体长。一般抗体半衰期仅2到3周，而<strong>这对抗体在人体内半衰期能达到3个月，给人体提供6到9个月甚至更长时间的保护。</strong></p><p><strong>第二，安全性方面，它来自于人体，</strong>在三期临床数据中，它对安全系统的表现比对照组安慰剂还要好。</p><p><strong>第三，有效性方面，它在三期临床中能把死亡和住院的风险降低80%。</strong></p><p><strong>第四，它是一对组合的疗法，同时拥有两个抗体，对各种变异毒株的覆盖度高</strong>，新冠病毒也许可以通过突变从一个抗体逃逸，不受一个抗体的攻击，但是同时不受两个抗体作用的可能性很低。</p><p><strong>中新网：安巴韦单抗注射液及罗米司韦单抗注射液的适应症人群是哪些？</strong></p><p><strong>李安康：</strong>抗体现在获批的适应症是用于治疗轻型和普通型且伴有进展为重型高风险因素的人群。获批年龄在12岁以上，12至17岁人群是附条件批准，18岁以上成年人是直接批准的。</p><p><strong>对于轻型和普通型人群来说，越早使用抗病毒药物获益越好。</strong>因为感染新冠早期，病毒处于繁殖和扩增的阶段，能够及时被药物扼杀。而到了晚期及更重症阶段，不仅有病毒对人体的伤害，更多的是人自身的免疫系统产生免疫反应后对人体自身的伤害。</p><p>所以，如果到后期再采用中和抗体治疗，不能够达到100%的效率。从重症风险来说，有一部分病人有基础疾病，或者年纪比较大，在感染新冠后更容易发展成重症，所以这部分人不治疗的风险会很高。因此我们优先在这部分有高风险进展到重症的人群中使用新冠抗体进行治疗。</p><p class=\"ql-align-center\"><img src=\"https://n.sinaimg.cn/sinakd20211217s/425/w700h525/20211217/006a-2ba16bf3dcad7d011e275c099b00ab3a.jpg\" alt=\"中国首家获批的自主知识产权新冠病毒中和抗体联合治疗药物安巴韦单抗注射液及及罗米司韦单抗注射液。应妮 摄\"><strong>中国首家获批的自主知识产权新冠病毒中和抗体联合治疗药物安巴韦单抗注射液及及罗米司韦单抗注射液。应妮 摄</strong></p><p><strong>中新网：近期“奥密克戎”变异株引发关注，各国也加快了对应疫苗和药物的研发速度，该药物是否可以应对目前的病毒变异？</strong></p><p><strong>李安康：</strong>因为这是一对抗体，所以只要有一个抗体保持对病毒的中和活性，它就可以用来进行治疗，目前实验室结果已经显示这个抗体保持了对“奥密克戎”的有效性。</p><p><strong>到目前为止，它对所有主要的这些突变，包括“阿尔法”、“贝塔”、“伽马”、“德尔塔”，包括最新的“奥密克戎”变异株都保持了中和活性。</strong></p><p><strong>中新网：此次获批的中和抗体联合治疗药物后期是否有望给健康人群进行预防性接种？</strong></p><p><strong>李安康：</strong>目前在研究中，有进一步数据后可以推广到预防应用方面。但从大规模预防的角度来说，建立第一步防线应该通过大规模接种疫苗，再重点针对疫苗不能够完全保护或者不适用的人群，采用中和抗体进行预防。</p><p>因为中和抗体可以<strong>直接达成预防作用</strong>，它不依赖于人自身的免疫系统，所以<strong>对于那些免疫系统本身不够完善或者有缺陷的人来说，可以通过中和抗体直接完成保护。</strong>比如年纪特别大、免疫系统比较弱的人群，或者说有一些基础疾病、免疫系统有缺陷的人群，包括像艾滋病人群都可以采用中合抗体进行免疫保护。</p><p>但目前还在对进一步的研究进行布局和计划，因此还不能明确何时能给健康人群接种。</p><p class=\"ql-align-center\"><img src=\"https://n.sinaimg.cn/sinakd20211217s/425/w700h525/20211217/bdc9-860d6980cb6b71ca0cb47422ea5b1aca.jpg\" alt=\"资料图：市民接受核酸检测。钱晨菲 摄\"><strong>资料图：市民接受核酸检测。钱晨菲 摄</strong></p><p><strong>中新网：目前新药在国内的售价以及产能部署情况如何？</strong></p><p><strong>李安康：</strong>之前几款在美获批的中和抗体药物，美国政府给出的采购价格是2100美金，国内的定价还在跟相关部门商谈中，正在测算定价。从抗体药的生产来说，生产成本和生产规模相关联，生产规模越大成本越低，所以未来会根据订单量，包括需求和市场反应来定价。</p><p>药品的产能不会是大的瓶颈，公司目前在和专门进行外包生产的生产商进行合作。未来会根据订单量安排产能及生产规模。我们之前生产的一批药品储备能够满足当前的需求。</p><p><strong>中新网：安巴韦单抗注射液及罗米司韦单抗注射液是否有“走出去”的计划？</strong></p><p><strong>李安康：</strong>在今年10月，三期临床完整数据出来以后，我们已同时向中国药监局和美国药监局递交了数据，并向美国提交了紧急使用授权申请材料，目前在跟监管部门保持沟通，希望能够尽快获批。</p><p>在三期的临床中，除了美国，还有巴西、南非、阿根廷等国家也参与了临床试验，我们也会努力推进在这些地方的注册。此外，我们也在跟不同区域潜在的合作伙伴进行讨论，希望更多的病人能够通过中和抗体获益。</p><p class=\"ql-align-center\"><img src=\"https://n.sinaimg.cn/sinakd20211217s/426/w700h526/20211217/c9ff-55b5609cc6752ea1f992c69c9234edc5.jpg\" alt=\"腾盛博药首席财务官、生物医药博士李安康接受中新网“中国新观察”栏目采访。\"><strong>腾盛博药首席财务官、生物医药博士李安康接受中新网“中国新观察”栏目采访。</strong></p><p><strong>中新网：目前，疫情还在全球肆虐，国内外医药企业都在加快新冠特效药的研发工作，国内药企在研发方面处于什么样的位置？未来还要解决哪些问题？</strong></p><p><br></p><p><strong>李安康：</strong>我们应该是最早从新冠康复患者血清中分离出中和抗体，并进入临床试验的药企之一。从过去这段时间看来，中国药企在药物研发上面也取得了长足的进步。</p><p>从新冠药物开发来说，由于国内的疫情控制比较好，所以在我们完成一期临床后，就很难找到足够多的病人去进行大规模三期临床的研究了，所以我们选择进行国际合作，通过国际多中心的三期去验证我们抗体药物的有效性。</p><p>从国内药物开发特别是新冠这样的药物开发来说，必须要走出去，利用国际临床资源来支持后期的药物开发。</p><p><strong>中新网：人类是否能够以特效药或疫苗来彻底遏制或者阻断新冠疫情的传播？</strong></p><p><strong>李安康：</strong>准确的预测未来疫情会怎么发展是比较困难的，因为病毒确实是在不断变异，各国也采用不同的手段、政策控制疫情。</p><p>能否清除疫情尚不明确。但是从过去对抗疫情、进行药物研发各方面的防治工作来看，<strong>只要采取科学的态度、手段去研究、创新、开发治疗和预防手段，病毒是可以被控制住的，不会对人类健康造成根本性的威胁。</strong></p>', 'news.jpg', '王富贵', 44, '2023-06-14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(0) NOT NULL,
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `psd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `forbidden` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '叶富杰', '1.jpg', '男', '15779856321', '123@123.com', 0);
INSERT INTO `user` VALUES (69187354, 'test', '123456', 'johnvan', '4.jpg', '男', '13664896142', 'waerae@123.com', 0);

-- ----------------------------
-- Table structure for wishlist
-- ----------------------------
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist`  (
  `wid` int(0) NOT NULL,
  `wname` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wmoney` float NULL DEFAULT NULL,
  `whmoney` float NULL DEFAULT NULL,
  `wcdate` date NULL DEFAULT NULL,
  `wstatus` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wfdate` date NULL DEFAULT NULL,
  PRIMARY KEY (`wid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES (1, '1', 100, 100, '2023-06-22', '已完成', '2023-06-14');
INSERT INTO `wishlist` VALUES (12, 'apple', 100, 100, '2023-06-14', '已完成', '2023-06-14');
INSERT INTO `wishlist` VALUES (13, 'bb', 100, 100, '2023-06-08', '已完成', '2023-06-14');

-- ----------------------------
-- View structure for personal_expense
-- ----------------------------
DROP VIEW IF EXISTS `personal_expense`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `personal_expense` AS select `user`.`name` AS `用户昵称`,`m`.`mname` AS `账单名`,`m`.`mnums` AS `金额`,`m`.`mway` AS `支付方式`,`m`.`mtime` AS `时间`,`c`.`inorout` AS `支出` from ((`money` `m` join `moneycategory` `c` on((`m`.`id` = `c`.`id`))) join `user` on((`user`.`userId` = `m`.`userId`))) where (`c`.`inorout` = '支出');

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES ('叶富杰', '吃饭', 10, '支付宝', '2023-06-14', '支出');
INSERT INTO `wishlist` VALUES ('叶富杰', '早饭', 10, '微信', '2023-06-14', '支出');
INSERT INTO `wishlist` VALUES ('johnvan', '自行车', 2, '微信', '2023-06-13', '支出');
INSERT INTO `wishlist` VALUES ('johnvan', '午饭', 15, '微信', '2023-06-13', '支出');
INSERT INTO `wishlist` VALUES ('johnvan', '游戏', 50, '支付宝', '2023-06-13', '支出');
INSERT INTO `wishlist` VALUES ('johnvan', '游乐场', 100, '微信', '2023-06-07', '支出');
INSERT INTO `wishlist` VALUES ('johnvan', '火锅', 80, '支付宝', '2023-06-27', '支出');

-- ----------------------------
-- View structure for personal_income
-- ----------------------------
DROP VIEW IF EXISTS `personal_income`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `personal_income` AS select `user`.`name` AS `用户昵称`,`m`.`mname` AS `账单名`,`m`.`mnums` AS `金额`,`m`.`mway` AS `支付方式`,`m`.`mtime` AS `时间`,`c`.`inorout` AS `支出` from ((`money` `m` join `moneycategory` `c` on((`m`.`id` = `c`.`id`))) join `user` on((`user`.`userId` = `m`.`userId`))) where (`c`.`inorout` = '收入');

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES ('johnvan', '生活费', 1000, '微信', '2023-05-31', '收入');

-- ----------------------------
-- View structure for personal_wishlist_invite
-- ----------------------------
DROP VIEW IF EXISTS `personal_wishlist_invite`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `personal_wishlist_invite` AS select `i`.`userId1` AS `用户编号`,`user`.`name` AS `用户昵称`,`w`.`wname` AS `心愿名`,`w`.`wmoney` AS `心愿总金额`,`w`.`whmoney` AS `已存金额`,`w`.`wcdate` AS `创建时间`,`w`.`wfdate` AS `完成时间`,`w`.`wstatus` AS `完成状态` from ((`wishlist` `w` join `invite` `i` on((`w`.`wid` = `i`.`wid`))) join `user` on((`user`.`userId` = `i`.`userId1`)));

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES (1, '叶富杰', 'apple', 100, 100, '2023-06-14', '2023-06-14', '已完成');

-- ----------------------------
-- Triggers structure for table wishlist
-- ----------------------------
DROP TRIGGER IF EXISTS `update_wishlist_status`;
delimiter ;;
CREATE TRIGGER `update_wishlist_status` BEFORE UPDATE ON `wishlist` FOR EACH ROW BEGIN
    IF NEW.whmoney = NEW.wmoney THEN
        SET NEW.wstatus = '已完成',
				NEW.wfdate = now();
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
