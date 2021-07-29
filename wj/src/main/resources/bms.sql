/*
 Navicat Premium Data Transfer

 Source Server         : marsh
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : bms

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 29/07/2021 17:15:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name_zh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (2, '/admin/dashboard', 'DashboardAdmin', '运行情况', NULL, 'dashboard/admin/index', 1);
INSERT INTO `admin_menu` VALUES (3, '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (4, '/admin', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (6, '/admin/user/profile', 'Profile', '用户信息', NULL, 'user/UserProfile', 3);
INSERT INTO `admin_menu` VALUES (7, '/admin/user/role', 'Role', '角色配置', NULL, 'user/Role', 3);
INSERT INTO `admin_menu` VALUES (8, '/admin/content/book', 'BookManagement', '图书管理', NULL, 'content/BookManagement', 4);
INSERT INTO `admin_menu` VALUES (9, '/admin/content/borrowhistory', 'BorrowHistory', '借阅历史', NULL, 'content/BorrowHistory', 4);
INSERT INTO `admin_menu` VALUES (10, '/admin/content/borrowcheck', 'BorrowCheck', '归还确认', NULL, 'content/BorrowCheck', 4);
INSERT INTO `admin_menu` VALUES (11, '/admin/content/borrowinfo', 'BorrowInfo', '当前借阅信息', NULL, 'content/BorrowInfo', 4);

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `admin_permission` VALUES (2, 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `admin_permission` VALUES (3, 'content_management', '内容管理', '/api/admin/content');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 'sysAdmin', '系统管理员', 1);
INSERT INTO `admin_role` VALUES (2, 'contentManager', '内容管理员', 1);
INSERT INTO `admin_role` VALUES (3, 'visitor', '访客', 1);
INSERT INTO `admin_role` VALUES (9, 'test', '测试角色', 1);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rid` int(0) NULL DEFAULT NULL,
  `mid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (19, 4, 1);
INSERT INTO `admin_role_menu` VALUES (20, 4, 2);
INSERT INTO `admin_role_menu` VALUES (21, 3, 1);
INSERT INTO `admin_role_menu` VALUES (22, 3, 2);
INSERT INTO `admin_role_menu` VALUES (23, 9, 1);
INSERT INTO `admin_role_menu` VALUES (24, 9, 2);
INSERT INTO `admin_role_menu` VALUES (121, 1, 1);
INSERT INTO `admin_role_menu` VALUES (122, 1, 2);
INSERT INTO `admin_role_menu` VALUES (123, 1, 3);
INSERT INTO `admin_role_menu` VALUES (124, 1, 6);
INSERT INTO `admin_role_menu` VALUES (125, 1, 7);
INSERT INTO `admin_role_menu` VALUES (126, 1, 4);
INSERT INTO `admin_role_menu` VALUES (127, 1, 8);
INSERT INTO `admin_role_menu` VALUES (128, 1, 9);
INSERT INTO `admin_role_menu` VALUES (129, 1, 10);
INSERT INTO `admin_role_menu` VALUES (130, 1, 5);
INSERT INTO `admin_role_menu` VALUES (188, 2, 1);
INSERT INTO `admin_role_menu` VALUES (189, 2, 2);
INSERT INTO `admin_role_menu` VALUES (190, 2, 4);
INSERT INTO `admin_role_menu` VALUES (191, 2, 8);
INSERT INTO `admin_role_menu` VALUES (192, 2, 9);
INSERT INTO `admin_role_menu` VALUES (193, 2, 10);

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `rid` int(0) NULL DEFAULT NULL,
  `pid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_permission_role_1`(`rid`) USING BTREE,
  INDEX `fk_role_permission_permission_1`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (83, 5, 3);
INSERT INTO `admin_role_permission` VALUES (108, 1, 1);
INSERT INTO `admin_role_permission` VALUES (109, 1, 2);
INSERT INTO `admin_role_permission` VALUES (110, 1, 3);
INSERT INTO `admin_role_permission` VALUES (139, 2, 3);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NULL DEFAULT NULL,
  `rid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_operator_role_operator_1`(`uid`) USING BTREE,
  INDEX `fk_operator_role_role_1`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (40, 24, 2);
INSERT INTO `admin_user_role` VALUES (63, 3, 2);
INSERT INTO `admin_user_role` VALUES (64, 1, 1);
INSERT INTO `admin_user_role` VALUES (68, 2, 3);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(0) NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `ISBN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `abs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount` int(0) NOT NULL,
  `cid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `fk_book_category_on_cid`(`cid`) USING BTREE,
  INDEX `title`(`title`) USING BTREE,
  CONSTRAINT `fk_book_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 'https://i.loli.net/2019/04/10/5cadaa0d0759b.jpg', '且在人间', '余秀华', '湖南文艺出版社', '9787540488949', 45.00, '诗人余秀华中篇小说首次结集出版。\r\n\r\n        《且在人间》——以余秀华为生活原型，讲述一个残疾女人悲苦倔强、向死而生的故事。\r\n\r\n        女主人公周玉生活在乡村，患有“脑瘫”，她几乎被所有人漠视，甚至被整个社会抛弃，但是她渴望被当成一个普通的健康人,而不是带着怜悯或不屑，她只要求平等。爱情的缺 失，家庭的不幸，生活的种种际遇让周玉用诗歌的方式把 情感抒发出来，最终她用诗歌创作出了一个文学的世界，得到了人们的认可。', 5, 2);
INSERT INTO `book` VALUES (2, 'https://i.loli.net/2019/04/10/5cada7e73d601.jpg', '三体', '刘慈欣', '重庆出版社', '9787536692930', 23.00, '文化大革命如火如荼进行的同时。军方探寻外星文明的绝秘计划“红岸工程”取得了突破性进展。但在按下发射键的那一刻，历经劫难的叶文洁没有意识到，她彻底改变了人类的命运。地球文明向宇宙发出的第一声啼鸣，以太阳为中心，以光速向宇宙深处飞驰……\r\n\r\n四光年外，“三体文明”正苦苦挣扎——三颗无规则运行的太阳主导下的百余次毁灭与重生逼迫他们逃离母星。而恰在此时。他们接收到了地球发来的信息。在运用超技术锁死地球人的基础科学之后。三体人庞大的宇宙舰队开始向地球进发……\r\n\r\n人类的末日悄然来临。', 5, 2);
INSERT INTO `book` VALUES (32, 'https://i.loli.net/2019/04/10/5cada99bd8ca5.jpg', '叙事的虚构性', '[美] 海登·怀特 ', '南京大学出版社', '9787305211522', 80.00, '海登•怀特被誉为人类伟大的思想家之一。从1973年出版具有里程碑意义的专著《元史学》以来，怀特的作品对于历史学、文学研究、人类学、哲学、艺术史、电影传媒研究等将叙事学作为关注焦点的学科而言意义非凡。\n\n本书由罗伯特•多兰作序，他巧妙地将怀特重要但难得一见的文章汇集成册，研究探讨他关于历史书写和叙事的革命性理论。怀特的这些文章大多采用论文体，内容涉及多位思想家，探讨诸多主题，文笔犀利，语言优美。\n\n《叙事的虚构性》追溯怀特重要思想的演变轨迹，是历史编纂学者和学习者、历史理论和文学研究学者们的重要读物。', 3, 3);
INSERT INTO `book` VALUES (35, 'https://i.loli.net/2019/04/10/5cada940e206a.jpg', '圣母', '[日]秋吉理香子 ', '新星出版社', '9787513335256', 42.00, '一起男童被害案搅得蓝出市人心惶惶。\n\n好不容易怀孕生产的保奈美抱紧年幼的孩子，立誓要不惜任何代价保护她。\n\n男人是在孩子出生后才成为父亲的，但女人，是从小生命来到体内的那一瞬间起，就是母亲了。患有不孕症的保奈美是经历过艰辛的治疗过程才终于有了孩子的，她不允许这起命案威胁到宝贵的孩子！\n\n母亲，就是要消除所有对子女的威胁，每一位母亲都应肩负这样的使命，这是神圣的天职！', 2, 1);
INSERT INTO `book` VALUES (37, 'https://i.loli.net/2019/04/10/5cada8986e13a.jpg', '奢侈与逸乐', '[英]马克辛·伯格', '中国工人出版社', '9787500869085', 88.00, '本书探讨了十八世纪英国新式、时尚的消费品的发明、制造和购买。', 4, 3);
INSERT INTO `book` VALUES (38, 'https://i.loli.net/2019/04/10/5cada8b8a3a17.jpg', '忧伤动物', '[德]莫妮卡·马龙 ', '漓江出版社', '9787540785987', 42.00, '“忧伤动物”(animal triste)这个词组取自一句最早可以追溯到亚里士多德时代的拉丁语名言，即“欢爱后，每个动物都忧伤不已”（Post coitum omne animal triste est）。无疑，这部冠以如此标题的小说让人有不祥的预感并暗示着宿命的思想。小说的女主人公是位近乎百岁的老人。在多年前有意斩断了与外界的一切联系之后，在她的后半生里，她唯一能做的就是或躺或坐在“印着鲜红、艳绿和深紫色的大花”、让人想起“食肉植物的花朵”的床单上，追忆几十年前她和自己...', 5, 1);
INSERT INTO `book` VALUES (54, 'https://i.loli.net/2019/04/10/5cada9d9d23a6.jpg', '爱界', '[英] 费伊·韦尔登 ', '人民文学出版社', '9787020144211', 45.00, '去不去爱，爱的界限何在，一直是普拉克西丝的人生课题。\n\n年迈的她独自待在肮脏而昏暗的地下室里，想写回忆录，可她该写些什么呢？是写父母未婚同居生下了她，她还年幼天真无邪时，母女就遭父亲抛弃？还是写她曾经或是主动或是被动地成了未婚同居者、妻子、情人、母亲、后母？还是写她两年的牢狱生活？她想描绘二十世纪女性的众生相，想记录女性群体在情感、灵魂和思想方面所处的三重困境，想道出女性之间的大爱如何铸成姐妹之谊。', 1, 3);
INSERT INTO `book` VALUES (55, 'https://i.loli.net/2019/04/10/5cada824c7119.jpg', '密室中的旅行', '[美] 保罗·奥斯特 ', '九州出版社', '9787020067435', 15.00, '一旦被抛进这个世界，我们就永远不会消失，即使造物者已经死去。\n\n.\n\n布兰克先生发现自己被囚禁在一个陌生的房间里，对过去的身份和经历一无所知。桌上有四叠六英寸厚的文稿，其中有一份未完待续的囚犯自述；还有一叠似曾相识的照片，照片中的人物将逐一登场。他续写了那个囚犯的故事，却发现自己正在经历的一切也早已被记录在文稿中……', 6, 1);
INSERT INTO `book` VALUES (59, 'https://i.loli.net/2019/04/10/5cada87fd5c72.jpg', '基本穿搭', '[日]大山旬 ', '四川人民出版社', '9787220111679', 45.00, '对穿衣搭配感到不耐烦，认为时尚很麻烦，穿什么都可以或者对衣服有着自己的想法但不够自信，本书就是为这样的人而准备的穿衣指南。不需要追随瞬息万变的时尚潮流，也不需要烦恼色彩搭配，只要掌握最低限度的知识和备齐常规单品，谁都能完成清爽得体的 80分搭配。', 2, 4);
INSERT INTO `book` VALUES (60, 'https://i.loli.net/2019/04/10/5cada976927da.jpg', '冒牌人生', '陈思安', '四川文艺出版社', '9787541151934', 45.00, '《冒牌人生》收录了十篇短篇小说。十个故事分别以城市中的怪人为主角，他们默默无闻地生存在城市主流生活的边缘地带：或是等待手术的性别认同障碍者，或是武艺高强而深藏不露的夜市摊主，或是卧底追凶的底层保安，或是甘于...', 3, 1);
INSERT INTO `book` VALUES (61, 'https://i.loli.net/2019/04/10/5cada9202d970.jpg', '战争哀歌', '[越]保宁 ', '湖南文艺出版社', '9787540490881', 48.00, '《战争哀歌》超越了战争，战争是它的背景，它的内核是关于逝去的青春，关于美和伤痛！\n\n一场突如其来的战争打碎了阿坚和阿芳这对年轻情侣的生活，在血肉横飞的战争中，主人公阿坚成了幸存者，但战争带来的伤痛还远远没有平息。那些经历仍旧萦绕在阿坚的生活之中，被战争毁灭的不仅 仅是阿坚， 阿芳也遭遇了难以想象的梦魇。时间越长，阿坚越觉得自己不是活着，而是被困在这人世间。', 2, 1);
INSERT INTO `book` VALUES (62, 'https://i.loli.net/2019/04/10/5cada9c852298.jpg', '胡椒的全球史', '[美] 玛乔丽·谢弗 ', '上海三联出版社', '9787542666062', 49.00, '看似不起眼的胡椒，却是家家餐桌必备。在中世纪时，更是欧洲达官显贵们的最爱、财富与地位的象征。黑胡椒原产于印度，距离欧洲各港口有十万八千里之远，取之向来不易。商人们对其供应来源不遗余力的追寻，成为世界史上一股重要的推动力量，促成全球贸易的兴起，重新划定了世界经济版图。', 6, 2);
INSERT INTO `book` VALUES (63, 'https://i.loli.net/2019/04/10/5cada962c287c.jpg', '与病对话', '胡冰霜', '北京联合出版公司', ' 9787559628893', 52.00, '一部融合科普性与趣味性、兼具心理学与哲学意味的医学散文。\n\n一位满怀仁心的资深医者对几十年行医生涯的回望与省思。\n\n全书以真实的病例和鲜活的故事贯穿始终，作者从一位全科医生、心理学者的视角观察、解读疾病与患者身心之关系，厘清大众对诸多常见疾病的误解...', 4, 1);
INSERT INTO `book` VALUES (64, 'https://i.loli.net/2019/04/10/5cada858e6019.jpg', '上帝笑了99次', '[英]彼得·凯弗', '北京联合出版公司', '9787559627605', 78.00, '一只美洲羊驼会坠入爱河吗？机器人能变成人吗？怎样才能不赢得公主青睐？人类一思考，上帝就发笑。在99个奇妙、怪诞、滑稽的问题背后，其实是99个烧脑的哲学、道德、法律领域的经典悖论，也是99道极富挑战性的大思考测试。本书内容覆盖了大多数常见哲学话题，包括形而上学、逻辑学、伦理学、语言哲学、政治哲学、自我认知、人际关系、美学、存在主义等，还配有20多幅漫画插图。在锻炼思维之外，本书也能帮我们建立个性化的哲学知识体系。', 5, 3);
INSERT INTO `book` VALUES (65, 'https://i.loli.net/2019/04/10/5cada8e1aa892.jpg', '互联网算法', '[美] 菲斯曼等 ', '江西人民出版社', ' 9787210109631', 42.00, '只要你租过房子、上网买过东西、自己经营过企业，那么你就处在商业变革的前线。在这场变革中，亚马逊、谷歌、优步等不同以往的企业取得了史无前例的成功，而促成这场变革的不只是科技进步，还有经济学思想。\n\n在这本趣味横生的书中，我们会看到，经济思想的革命远比科技革命更宏大。从谷歌广告的算法，到网上购物规避欺诈，都要依靠经济学家建立的经济模型，甚至连互联网公司...', 1, 6);
INSERT INTO `book` VALUES (66, 'https://i.loli.net/2019/04/10/5cada9ec514c9.jpg', '七侯笔录', '马伯庸', '湖南文艺出版社', '9787540490805', 92.00, '一个关于文化的离奇故事，一段关于文人的壮丽传说。\n\n几千年来，每一位风华绝代的文人墨客辞世之时，都会让自己的灵魂寄寓在一管毛笔之中。他们身躯虽去，才华永存，这些伟大的精神凝为性情不一的笔灵，深藏于世间，只为一句“不教天下才情付诸东流”的誓言。其中最伟大的七位古人，他们所凝聚的七管笔灵，被称为“管城七侯”。\n\n一位不学无术的现代少年，无意中邂逅了李白的青莲笔，命运就此与千年之前的诗仙交织一处，并为他开启了一个叫作笔冢的神秘世界。', 2, 3);
INSERT INTO `book` VALUES (67, 'https://i.loli.net/2019/04/10/5cada9870c2ab.jpg', '中心与边缘', '[美] 希尔斯', '译林出版社', '9787544776523', 88.00, '美国著名社会学家爱德华·希尔斯的主要研究成果包括他对“克里斯玛”、“中心”和“边缘”等概念的解释，以及他对“大众社会”一词的修正，这些研究对分析政治和文化领导力以及社会凝聚力具有重要价值。本书对希尔斯数十载社会理论研究进行了全面而详细的总结，为解释与探究当代社会的结构与变化提供了极具科学性的参考依据。', 3, 3);
INSERT INTO `book` VALUES (68, 'https://i.loli.net/2019/04/10/5cad643643d4c.jpg', '水浒群星闪耀时', '李黎', '上海文艺出版社', '9787532167524', 45.00, '本书以众所周知的梁山英雄为写作对象，重点书写其上山后、招安前的日常生活，涉及他们的喜怒哀乐、同类中人、乡愁怀旧、未来憧憬、命运追问等。书中涉及宋江、武松、鲁智深、李俊、燕青等等耳熟能详的人物而显得有些“改编”与水浒研究的意味，但鉴于所有人物皆为虚构，本书稿的内容是虚构之上的虚构，旨在宏大叙事的语境下突出个人的细微之处和命运感。', 2, 1);
INSERT INTO `book` VALUES (69, 'https://i.loli.net/2019/04/10/5cad63931ce27.jpg', '谋杀狄更斯', '[美] 丹·西蒙斯 ', '上海文艺出版社', '9787532168569', 128.00, '“狄更斯的那场意外灾难发生在1865年6月9日，那列搭载他的成功、平静、理智、手稿与情妇的火车一路飞驰，迎向铁道上的裂隙，突然触目惊心地坠落了。”', 6, 1);
INSERT INTO `book` VALUES (120, 'https://i.loli.net/2021/07/28/FkqCw1BtZVsMAQY.jpg', 'Python编程', '[美] 埃里克·马瑟斯', '人民邮电出版社', '9787115546081', 89.00, '本书是针对所有层次Python读者而作的Python入门书。全书分两部分：第一部分介绍用Python编程所必须了解的基本概念，包括Matplotlib等强大的Python库和工具，以及列表、字典、if语句、类、文件与异常、代码测试等内容；第二部分将理论付诸实践，讲解如何开发三个项目，包括简单的2D游戏、利用数据生成交互式的信息图以及创建和定制简单的Web应用，并帮助读者解决常见编程问题和困惑。', 14, 6);
INSERT INTO `book` VALUES (121, 'https://i.loli.net/2021/07/28/snGURJCW4keXt7q.jpg', '深入理解计算机系统', ' [美] Randal E.Bryant / [美] David O\' Hallaron', '机械工业出版社', '9787111321330', 99.00, '本书从程序员的视角详细阐述计算机系统的本质概念，并展示这些概念如何实实在在地影响应用程序的正确性、性能和实用性。全书共12章，主要内容包括信息的表示和处理、程序的机器级表示、处理器体系结构、优化程序性能、存储器层次结构、链接、异常控制流、虚拟存储器、系统级I/O、网络编程、并发编程等。', 8, 6);
INSERT INTO `book` VALUES (122, 'https://i.loli.net/2021/07/28/1iETRhLF6IYZBfs.jpg', '程序员修炼之道', ' [美] Andrew Hunt', '电子工业出版社', '9787121384356', 89.00, '本书之所以在全球范围内广泛传播，被一代代开发者奉为圭臬，盖因它可以创造出真正的价值：或编写出更好的软件，或探究出编程的本质，而所有收获均不依赖于特定语言、框架和方法。时隔20年的新版，经过全面的重新选材、组织和编写，覆盖哲学、方法、工具、设计、解耦、并发、重构、需求、团队等务实话题的最佳实践及重大陷阱，以及易于改造、复用的架构技术。本书极具洞察力与趣味性，适合从初学者到架构师的各阶层读者潜心研读或增广见闻。', 18, 6);
INSERT INTO `book` VALUES (123, 'https://i.loli.net/2021/07/28/4iko2pY59yZfSFg.jpg', '星之继承者', '[英] 詹姆斯·P.霍根', '新星出版社', '9787513342094', 58.00, '2027年，月球上发现一具五万年前的人类尸体。如果地球存在高科技史前文明，不可能没有任何遗迹；但若说他来自外星，则又违背进化论。同时，科研发现月球正面与背面的土壤年份相差几十亿年，而从猿猴到智人的进化链上缺失的一环一直没有找到……这一切谜团，都指向五万年前太阳系的那场浩劫，人类创世的史诗大幕，已徐徐拉开。', 4, 2);
INSERT INTO `book` VALUES (124, 'https://i.loli.net/2021/07/28/wCl7i4HaVBynk9z.jpg', '克莱因壶', '[日]冈岛二人', '化学工业出版社', '9787122346032', 48.00, '上杉彰彦从未如此兴奋——他写的故事被伊普西隆研发公司买下，即将制作成颠覆时代的全新游戏《克莱因2》（Klein 2），并受邀与少女梨纱一同担任游戏测试员。', 2, 2);
INSERT INTO `book` VALUES (125, 'https://i.loli.net/2021/07/28/mgDwZdNlQBXAqsC.jpg', '莫失莫忘', '[英] 石黑一雄', '上海译文出版社', '9787532777938', 49.00, '《莫失莫忘》是石黑一雄一部具有反乌托邦色彩的科幻小说，曾入围2005年布克奖和美国书评人协会奖决选名单。英格兰乡村深处的黑尔舍姆学校中，凯西、露丝和汤米三个好朋友在这里悠然成长。他们被导师小心呵护，接受良好的诗歌和艺术教育。然而，看似一座世外桃源的黑尔舍姆，却隐藏着许多秘密。凯西三人长大后，逐渐发现记忆中美好的成长过程，处处都是无法追寻的惶惑与骇人的问号……', 1, 2);
INSERT INTO `book` VALUES (126, 'https://i.loli.net/2021/07/28/Za1uEAbmOjV8tY4.jpg', '海底两万里', '[法] 儒勒·凡尔纳', '译林出版社', ' 9787806574317', 19.50, '十九世纪下半叶，“异国风情”曾受到不少作家，画家的青睐和读者的追捧，《海底两万里》的奇妙旅行为异域风情另辟蹊径，使人耳目一新；这就是为什么这部“海洋小说”不仅在当时一出版即成为畅销书，而且日后受到一代又一代读者欢迎的原因。', 4, 2);
INSERT INTO `book` VALUES (127, 'https://i.loli.net/2021/07/28/TXZRCpuNO3eAgDJ.jpg', '小家，越住越大', '逯薇', '中信出版集团', '9787508660196', 58.00, '房价高、面积小、东西多、收纳少；节奏快、压力大、时间紧、想法杂……这些是都市人生活普遍面临的问题。但是，即使只有有限的空间，也希望住得舒适、快乐， 纵然蜗居在不大的房子，也有一颗追求高品质生活的心。', 7, 4);
INSERT INTO `book` VALUES (128, 'https://i.loli.net/2021/07/28/vO6lsJEqRfbg2U9.jpg', '东京风格', '[日]都筑响一', '新星出版社', '9787513335379', 158.00, '你相信这是在东京吗？十平方米的空间，一扇窗，一块睡觉的地方，没有浴室或厨房，挂满衣服的晾衣绳横贯头顶，脚下是纠缠的电话线、成摞的杂志、昨日的垃圾，桌上是塞满烟头的烟灰缸、喝了一半的饮料、东倒西歪的护肤品。 在小小的蜗居里，存在着媒体幻象之外真实的东京风格。家具是捡来的，风景是借来的，然而心爱之物触手可及，玩偶、漫画、唱片、鞋帽收藏几乎溢出房间，常去的小酒馆、公园、海滩、赛马场出门就到。无须花很多钱也能潇洒地生活。', 7, 4);
INSERT INTO `book` VALUES (129, 'https://i.loli.net/2021/07/28/Es72THlfKG6zedP.jpg', '鱼翅与花椒', '[英]扶霞·邓洛普', '上海译文出版社', '9787532777914', 48.00, '扶霞是我的好友，也是我认识的所有外国人中，非常了解中国烹饪的一位。这本屡获殊荣的《鱼翅与花椒》无疑是很多外国人写中国食物的书籍里极为鲜活有趣和精准的 。——陈晓卿（“舌尖上的中国”总导演）', 11, 4);
INSERT INTO `book` VALUES (130, 'https://i.loli.net/2021/07/28/536MugolGmsDLJ2.jpg', '雅舍谈吃', '梁实秋', '山东画报出版社', '9787807130574', 17.00, '梁实秋先生是公认的华语世界中的一代文化宗师之一。他的许多文学作品都流播海内外，被人们赞为经典。其中“雅舍小品”更是他的代表作，先后印行了三百多版。《雅舍谈吃》是梁实秋先生一生在饮食文化方面才华的集中展示，如果您也喜欢吃，就和这位美食家一起去大饱口福吧！', 14, 4);

-- ----------------------------
-- Table structure for book_borrow
-- ----------------------------
DROP TABLE IF EXISTS `book_borrow`;
CREATE TABLE `book_borrow`  (
  `borrowid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `returndate` date NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`borrowid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_borrow
-- ----------------------------
INSERT INTO `book_borrow` VALUES (1, 'test', '水浒群星闪耀时', '2021-07-15', '2021-07-29', '2021-07-16', '按时归还');
INSERT INTO `book_borrow` VALUES (2, 'test', '水浒群星闪耀时', '2021-07-15', '2021-07-29', '2021-07-31', '逾期归还');
INSERT INTO `book_borrow` VALUES (3, 'test', '谋杀狄更斯', '2021-07-15', '2021-08-26', '2021-07-29', '按时归还');
INSERT INTO `book_borrow` VALUES (4, 'test', '谋杀狄更斯', '2021-07-15', '2021-07-18', '2021-07-20', '逾期归还');
INSERT INTO `book_borrow` VALUES (5, 'test', '谋杀狄更斯', '2021-07-15', '2021-07-29', '2021-07-16', '按时归还');
INSERT INTO `book_borrow` VALUES (6, 'test', '谋杀狄更斯', '2021-07-19', '2021-08-02', '2021-07-20', '按时归还');
INSERT INTO `book_borrow` VALUES (7, 'test', '不像艺术家一样思考', '2021-07-20', '2021-07-17', '2021-07-20', '逾期归还');
INSERT INTO `book_borrow` VALUES (8, 'test', '基本穿搭', '2021-07-03', '2021-07-16', '2021-07-20', '逾期归还');
INSERT INTO `book_borrow` VALUES (9, 'test', '冒牌人生', '2021-07-04', '2021-07-19', '2021-07-20', '逾期归还');
INSERT INTO `book_borrow` VALUES (10, 'test', '谋杀狄更斯', '2021-07-21', '2021-08-11', '2021-07-29', '按时归还');
INSERT INTO `book_borrow` VALUES (11, 'test', '爱界', '2021-07-26', '2021-08-09', '2021-07-26', '按时归还');
INSERT INTO `book_borrow` VALUES (12, 'test', '水浒群星闪耀时', '2021-07-27', '2021-08-10', '2021-07-27', '按时归还');
INSERT INTO `book_borrow` VALUES (13, 'student', '星之继承者', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (14, 'student', '小家，越住越大', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (15, 'student', '克莱因壶', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (16, 'test', '鱼翅与花椒', '2021-07-28', '2021-08-11', '2021-07-29', '按时归还');
INSERT INTO `book_borrow` VALUES (17, 'test', '深入理解计算机系统', '2021-07-28', '2021-08-11', '2021-07-29', '按时归还');
INSERT INTO `book_borrow` VALUES (18, 'test', 'Python编程', '2021-07-15', '2021-07-27', '2021-07-29', '逾期归还');
INSERT INTO `book_borrow` VALUES (19, 'test', 'Python编程', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (20, 'test', '程序员修炼之道', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (21, 'test', '星之继承者', '2021-07-28', '2021-08-11', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (22, 'test', '雅舍谈吃', '2021-07-29', '2021-08-12', NULL, '借阅中');
INSERT INTO `book_borrow` VALUES (23, 'test', '雅舍谈吃', '2021-07-12', '2021-07-26', NULL, '借阅逾期');

-- ----------------------------
-- Table structure for book_fine
-- ----------------------------
DROP TABLE IF EXISTS `book_fine`;
CREATE TABLE `book_fine`  (
  `fid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `borrowid` int(0) NOT NULL,
  `fine` decimal(10, 2) NOT NULL,
  `days` int(0) NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE,
  INDEX `borrowid`(`borrowid`) USING BTREE,
  CONSTRAINT `borrowid` FOREIGN KEY (`borrowid`) REFERENCES `book_borrow` (`borrowid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_fine
-- ----------------------------
INSERT INTO `book_fine` VALUES (15, 'test', 2, 0.20, 2);
INSERT INTO `book_fine` VALUES (16, 'test', 4, 0.20, 2);
INSERT INTO `book_fine` VALUES (19, 'test', 7, 0.30, 3);
INSERT INTO `book_fine` VALUES (20, 'test', 8, 0.40, 4);
INSERT INTO `book_fine` VALUES (21, 'test', 9, 0.10, 1);
INSERT INTO `book_fine` VALUES (27, 'test', 18, 0.20, 2);
INSERT INTO `book_fine` VALUES (28, 'test', 16, 0.00, 0);

-- ----------------------------
-- Table structure for book_return
-- ----------------------------
DROP TABLE IF EXISTS `book_return`;
CREATE TABLE `book_return`  (
  `returnid` int(0) NOT NULL AUTO_INCREMENT,
  `borrowid` int(0) NOT NULL,
  `returndate` date NOT NULL,
  `deny` tinyint(1) NOT NULL,
  `fine` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`returnid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_return
-- ----------------------------
INSERT INTO `book_return` VALUES (10, 23, '2021-07-29', 0, 0.30);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学');
INSERT INTO `category` VALUES (2, '流行');
INSERT INTO `category` VALUES (3, '文化');
INSERT INTO `category` VALUES (4, '生活');
INSERT INTO `category` VALUES (5, '经管');
INSERT INTO `category` VALUES (6, '科技');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '35b9529f89cfb9b848060ca576237e17', '8O+vDNr2sI3N82BI31fu1A==', '管理员', '12312312314', 'evan_nightly@163.com', 1);
INSERT INTO `user` VALUES (2, 'test', '85087738b6c1e1d212683bfafc163853', 'JBba3j5qRykIPJQYTNNH9A==', '测试', '123123123123', '123@123.com', 1);
INSERT INTO `user` VALUES (3, 'editor', '8583a2d965d6159edbf65c82d871fa3e', 'MZTe7Qwf9QgXBXrZzTIqJQ==', '编辑', NULL, NULL, 1);
INSERT INTO `user` VALUES (110, 'test1', 'aaa935fc191cbf2ff730bb7dcffaec67', 'YiNv3IzmbsaTVdX7jg0ENg==', '1', '16549544gfda', '1498894@qq.com', 1);
INSERT INTO `user` VALUES (113, 'student', '3f9a085d8a8abdf778504f775f40ac37', 'AmrbhhUgZ5AimtoY8+1F0g==', '学生', '121648158', '1654165165@qq.com', 1);

SET FOREIGN_KEY_CHECKS = 1;
