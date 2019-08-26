use master
if DB_ID('tcodedb')is not null
drop database tcodedb
create database tcodedb on
(
	name='tcodedb.mdf',
	filename='D:\实战项目库\T-SKY开源论坛\7月19日最新模板\db\tcodedb.mdf'
)

use tcodedb
--1.用户基础信息表 user 详细信息
if OBJECT_ID('users')is not null
drop table users
create table users
(
	u_account varchar(10) not null primary key,--用户账号
	u_password varchar(20) not null,--用户密码
	u_email varchar(50) not null,--用户邮箱
	u_IP varchar(20),--最后一次登录IP
	u_state int not null,--是否可用
	u_num int Default(0)
)
insert users(u_account,u_password,u_email,u_IP)
values('mms122','admin','1234567@qq.com','192.168.21.133','1',2),
('yjj123','admin123','1234567@qq.com','192.168.21.133','1',2),
('sjk123','admin123','1234567@qq.com','192.168.21.133','1',2),
('zjj123','admin123','1234567@qq.com','192.168.21.133','1',2),
('bha123','admin123','1234567@qq.com','192.168.21.133','1',2),
('chh123','admin123','1234567@qq.com','192.168.21.133','1',2)


select * from users 


--2.板块表plate详细信息
if OBJECT_ID('plate')is not null
drop table plate
create table plate
(
	p_no varchar(6) not null primary key,--板块编号
	p_name varchar(30) not null,--板块名称
	p_time datetime,--板块建立时间
	p_account varchar(6)--板块版主
)
insert plate(p_no,p_name,p_time,p_account)
values('PL1001','编程语言',GETDATE(),'Yh1002'),
('PL1002','Web应用',GETDATE(),'Yh1002'),
('PL1003','移动开发',GETDATE(),'Yh1002'),
('PL1004','企业应用',GETDATE(),'Yh1002'),

('PL1005','开源巨作',GETDATE(),'Yh1002'),

('PL1006','软件更新',GETDATE(),'Yh1004'),
('PL1007','行业资讯',GETDATE(),'Yh1004'),

('PL1008','开发讨论',GETDATE(),'Yh1003'),

('PL1009','反馈吐槽',GETDATE(),'Yh1003')

select * from plate

--3.帖子表posts详细信息
if OBJECT_ID('posts')is not null
drop table posts
create table posts
(
	p_pid varchar(15) not null primary key,--帖子ID	
	p_ptheme varchar(50) not null,--帖子标题
	p_ptime datetime not null,--帖子发表时间
	p_paccount varchar(20)not null,--发帖人账号
	p_pnick varchar(16), --发帖人昵称
	p_ptext varchar(8000) not null,--帖子内容
	p_pgood int not null default(0),--帖子点赞数
	p_pplate varchar(30) not null--所属板块
)
--删除函数
drop function dbo.CreateOrderID
--创建函数
create function dbo.CreateOrderID()  
returns decimal(12,0)  
as  
begin  
 DECLARE @id decimal(12,0)  
 DECLARE @Date varchar(8)  
  
 --获取当前帖子的帖子ID
 select @id = max(p_pid) from posts  
   
 --获取当前日期XXXXXXXX如20010101  
 select @Date = CONVERT(varchar(8), GETDATE(),112)  
  
 select @id = @Date + case when charindex(@Date, @id) = 1 then   
 Cast(right(Cast(Right(@id,4) as decimal) + 1 + 10000,4) as varchar(12))   
 else '0001' end  
    return @id  
end  

insert posts(p_pid,p_ptheme,p_ptime,p_paccount,p_pnick,p_ptext,p_pgood,p_pplate)
values(dbo.CreateOrderID(),'[Java] JAVA和JVM运行原理','2014/03/12 10:54','bha','yssy','JVM是java的核心和基础，在java编译器和os平台之间的虚拟处理器。它是一种利用软件方法实现的抽象的计算机基于下层的操作系统和硬件平台，可以在上面执行java的字节码程序。
编译器负责把Java程序转成Class文件,方便Jvm来读取它.
Jvm是java虚拟机,其实它就是解释器,把Class文件中的命令转成某种平台的命令,比如把Java命令转成Windows下的命令,然后Java程序就执行了.
这里和大家简单分享一下JAVA和JVM运行的原理，Java语言写的源程序通过Java编译器，编译成与平台无关的‘字节码程序’(.class文件，也就是0，1二进制程序)，然后在OS之上的Java解释器中解释执行，而JVM是java的核心和基础，在java编译器和os平台之间的虚拟处理器
JAVA和JVM运行的原理
1.Java语言运行的过程
Java语言写的源程序通过Java编译器，编译成与平台无关的‘字节码程序’(.class文件，也就是0，1二进制程序)，然后在OS之上的Java解释器中解释执行。
Java语言运行的过程
也相当与Java语言运行的过程
注：JVM（java虚拟机）包括解释器，不同的JDK虚拟机是相同的，解释器不同。
2.JVM：
JVM是java的核心和基础，在java编译器和os平台之间的虚拟处理器。它是一种利用软件方法实现的抽象的计算机基于下层的操作系统和硬件平台，可以在上面执行java的字节码程序。
java编译器只要面向JVM，生成JVM能理解的代码或字节码文件。Java源文件经编译成字节码程序，通过JVM将每一条指令翻译成不同平台机器码，通过特定平台运行。
JVM执行程序的过程 ：
I.加载。class文件
II.管理并分配内存
III.执行垃圾收集
JRE（java运行时环境）由JVM构造的java程序的运行环境 ','',''),
(dbo.CreateOrderID(),'什么是Java语言？java语言简介','2010-10-19 09:04:00','chh','Robin','Java是由Sun Microsystems公司于1995年5月推出的Java程序设计语言（以下简称Java语言）和Java平台的总称。用Java实现的HotJava浏览器（支持Java applet）显示了Java的魅力：跨平台、动态的Web、Internet计算。从此，Java被广泛接受并推动了Web的迅速发展，常用的浏览器现在均支持Java applet。另一方面，Java技术也不断更新。
Java平台由Java虚拟机（Java Virtual Machine）和Java 应用编程接口（Application Programming Interface、简称API）构成。Java 应用编程接口为Java应用提供了一个独立于操作系统的标准接口，可分为基本部分和扩展部分。在硬件或操作系统平台上安装一个Java平台之后，Java应用程序就可运行。现在Java平台已经嵌入了几乎所有的操作系统。这样Java程序可以只编译一次，就可以在各种系统中运行。Java应用编程接口已经从1.1x版发展到1.2版。目前常用的Java平台基于Java1.4，最近版本为Java1.6。
Java分为三个体系J2SE(Java2 Standard Edition)，J2EE(Java 2 Platform,Enterprise Edition)，J2ME(Java 2 Micro Edition)。
Java语言是一个支持网络计算的面向对象程序设计语言。Java语言吸收了Smalltalk语言和C++语言的优点，并增加了其它特性，如支持并发程序设计、网络通信、和多媒体数据控制等。主要特性如下：
1、Java语言是简单的。Java语言的语法与C语言和C++语言很接近，使得大多数程序员很容易学习和使用Java。另一方面，Java丢弃了C++ 中很少使用的、很难理解的、令人迷惑的那些特性，如操作符重载、多继承、自动的强制类型转换。特别地，Java语言不使用指针，并提供了自动的废料收集，使得程序员不必为内存管理而担忧。
2、Java语言是一个面向对象的。Java语言提供类、接口和继承等原语，为了简单起见，只支持类之间的单继承，但支持接口之间的多继承，并支持类与接口之间的实现机制（关键字为implements）。Java语言全面支持动态绑定，而C++ 语言只对虚函数使用动态绑定。总之，Java语言是一个纯的面向对象程序设计语言。
3、Java语言是分布式的。Java语言支持Internet应用的开发，在基本的Java应用编程接口中有一个网络应用编程接口（java.net），它提供了用于网络应用编程的类库，包括URL、URLConnection、Socket、 ServerSocket等。Java的RMI(远程方法激活)机制也是开发分布式应用的重要手段。
4、Java语言是健壮的。Java的强类型机制、异常处理、废料的自动收集等是Java程序健壮性的重要保证。对指针的丢弃是Java的明智选择。Java的安全检查机制使得Java更具健壮性。
5、Java语言是安全的。Java通常被用在网络环境中，为此，Java提供了一个安全机制以防恶意代码的攻击。除了Java语言具有的许多安全特性以外，Java对通过网络下载的类具有一个安全防范机制（类ClassLoader），如分配不同的名字空间以防替代本地的同名类、字节代码检查，并提供安全管理机制（类SecurityManager）让Java应用设置安全哨兵。
6、Java语言是体系结构中立的。Java程序（后缀为java的文件）在Java平台上被编译为体系结构中立的字节码格式（后缀为class的文件）, 然后可以在实现这个Java平台的任何系统中运行。这种途径适合于异构的网络环境和软件的分发。
7、Java语言是可移植的。这种可移植性来源于体系结构中立性，另外，Java还严格规定了各个基本数据类型的长度。Java系统本身也具有很强的可移植性，Java编译器是用Java实现的，Java的运行环境是用ANSI C实现的。
8、Java语言是解释型的。如前所述，Java程序在Java平台上被编译为字节码格式， 然后可以在实现这个Java平台的任何系统中运行。在运行时，Java平台中的Java解释器对这些字节码进行解释执行，执行过程中需要的类在联接阶段被载入到运行环境中。
9、Java是高性能的。与那些解释型的高级脚本语言相比，Java的确是高性能的。事实上，Java的运行速度随着JIT(Just-In-Time)编译器技术的发展越来越接近于C++。
10、Java语言是多线程的。在Java语言中，线程是一种特殊的对象，它必须由Thread类或其子（孙）类来创建。通常有两种方法来创建线程：其一，使用型构为Thread(Runnable) 的构造子将一个实现了Runnable接口的对象包装成一个线程，其二，从Thread类派生出子类并重写run方法，使用该子类创建的对象即为线程。值得注意的是Thread类已经实现了Runnable接口，因此，任何一个线程均有它的run方法，而run方法中包含了线程所要运行的代码。线程的活动由一组方法来控制。 Java语言支持多个线程的同时执行，并提供多线程之间的同步机制（关键字为synchronized）。
11、Java语言是动态的。Java语言的设计目标之一是适应于动态变化的环境。Java程序需要的类能动态地被载入到运行环境，也可以通过网络来载入所需要的类。这也有利于软件的升级。另外，Java中的类有一个运行时刻的表示，能进行运行时刻的类型检查。
Java语言的优良特性使得Java应用具有无比的健壮性和可靠性，这也减少了应用系统的维护费用。Java对对象技术的全面支持和Java平台内嵌的API能缩短应用系统的开发时间并降低成本。Java的编译一次，到处可运行的特性使得它能够提供一个随处可用的开放结构和在多平台之间传递信息的低成本方式。特别是Java企业应用编程接口（Java Enterprise APIs）为企业计算及电子商务应用系统提供了有关技术和丰富的类库。','',''),
(dbo.CreateOrderID(),'JAVA线程8 - ThreadLocal','2014/02/26 15:59','klq122','dddd','一、ThreadLocal简介
ThreadLocal并不是一个Thread，而是Thread的局部变量。 
当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。 
二、为什么会出现ThreadLocal类
在java多线程并发访问共享变量时，为了线程安全，我们的做法可能是对多个线程访问进行同步控制。但是这样会降低系统的性能。在JDK 1.2的版本中就提供java.lang.ThreadLocal类，它为每一个线程都维护了一个变量，每个线程都有了自己独立的变量后，就没有了并发下的线程安全问题。ThreadLocal类是以空间来换取线程安全的一种策略。 
三、ThreadLocal原理
ThreadLocal为每一个线程维护变量的副本实现思路： 
在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本。 ',1,''),
(dbo.CreateOrderID(),'JDBC（Java Data Base Connectivity,java数据库连接）','2012/06/03 21:00 ','sjk','白衣','JDBC是一种用于执行SQL语句的Java API，可以为多种关系数据库提供统一访问，它由一组用Java语言编写的类和接口组成。

Java数据库连接体系结构是用于Java应用程序连接数据库的标准方法，JDBC对Java程序员而言是API，对实现与数据库连接的服务提供商而言是接口模型。作为API，JDBC为程序开发提供标准的接口，并为数据库厂商及第三方中间件厂商实现与数据库的连接提供了标准方法。

通常一个数据库厂商在推出自己的数据库产品的时候都会提供一套访问数据库的API，这些API可以用各种语言的形式提供，客户端应用程序调用这些API来访问数据库。每一个厂商提供的API都不相同，导致了使用某一个特定的数据库的程序不能移植到另一个数据库上。JDBC以Java类库来取代数据库厂商的专有API，客户端只需要调用JDBC API，由JDBC驱动程序（第三方数据库厂商实现Java JDBC标准而定义的特定于某一数据库操作的API）去处理与数据库的通信。

应用：

使用某种数据库持久化数据要导入相应的数据库驱动包。
主要数据库JDBC驱动的类名：
SQL Server：com.microsoft.jdbc.sqlserver.SQLServerDriver
MySQL:com.mysql.jdbc.Driver
Oracle:oracle.jdbc.driver.OracleDriver

1、首先我们一般要加载并注册数据库驱动，有以下三种方式可以做到这一点：
     ①、Class.forName("JDBC驱动类名") //通过反射机制加载注册驱动，常用
     ②、System.setProperty("jdbc.driver","JDBC驱动类名");//设置系统属性指定数据库驱动
     ③、DriverManager.registerDriver(new com.mysql.jdbc.Driver());

     总结：推荐①，和②两种方式。
     原因：③在编译时需要导入对应的lib。①，②不需要。
     第②种方式的话，可以同时导入多个jdbc驱动，中间用冒号“：”分开
     比如System.setProperty("jdbc.drivers","XXXDriver:XXXDriver:XXXDriver");
     这样就一次注册了三个数据库驱动

     在调用Class.forName(“XXXDriver”)时，完成了将具体的驱动程序向JDBC API中驱动管理器DriverManager
     的注册，该 注册方法在类构造完成前完成，一般使用静态语句块，
     通过查看MySQL的驱动实现com.mysql.jdbc.Driver类可以看到

static {
  	try {
		java.sql.DriverManager.registerDriver(new Driver());
	} catch (SQLException E) {
		throw new RuntimeException("Cant register driver!");
	}
}

     在调用DriverManager的getConnection方法时，一般先在已注册的驱动中查找可以了解此URL的驱动， 
     然后调用该驱动的connect方法，从而建立连接，返回的连接都是一个实现java.sql.Connection接口的具体类。
     JDBC标准中的Driver接口：
     JDBCDriverInterface

     它是任何数据库提供商的驱动类必须实现的接口，驱动类必须实现该接口中的所有方法！

     MySQL驱动类Driver源码： 

package com.mysql.jdbc;

import java.sql.SQLException;

/**
 * The Java SQL framework allows for multiple database drivers. Each driver
 * should supply a class that implements the Driver interface
 * 
 * <p>
 * The DriverManager will try to load as many drivers as it can find and then
 * for any given connection request, it will ask each driver in turn to try to
 * connect to the target URL.
 * 
 * <p>
 * It is strongly recommended that each Driver class should be small and
 * standalone so that the Driver class can be loaded and queried without
 * bringing in vast quantities of supporting code.
 * 
 * <p>
 * When a Driver class is loaded, it should create an instance of itself and
 * register it with the DriverManager. This means that a user can load and
 * register a driver by doing Class.forName("foo.bah.Driver")
 */
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
	// ~ Static fields/initializers
	// ---------------------------------------------

	//
	// Register ourselves with the DriverManager
	//
	static {
		try {
			java.sql.DriverManager.registerDriver(new Driver());
		} catch (SQLException E) {
			throw new RuntimeException("Cant register driver!");
		}
	}
	
	/**
	 * Construct a new driver and register it with DriverManager
	 */
	public Driver() throws SQLException {
		// Required for Class.forName().newInstance()
	}
}
2、从DriverManager中的到一个DBMS连接，DriverManager类是整个JDBC的起点！利用它可以创建连接，
     从而完成后续的操作。调用DriverManager类的getConnection(String url, String user, String pwd)建立到
     数据库的连接，返回一个连接对象。

     常见的url：
     SQL Server ：jdbc:microsoft:sqlserver://localhost:1443;databasename=数据库名
     Oracle ：jdbc:oracle:thin:@localhost:1521:ORCL(数据库实例名)
     MySQL ：jdbc:mysql://localhost:3306:/databasename
     Jdbc-odbc桥 ：jdbc:odbc:test(test为odbc数据源名称)

public class DBUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/spring";
	private static String user = "root";
	private static String pwd = "mysql5";
	private static Connection conn = null;
	
	static {
		try {
			//使用Class.forName()加载并注册JDBC驱动程序
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			//加载 Driver 类并在 DriverManager 类中注册后，它们即可用来与数据库建立连接。
			//当调用 DriverManager.getConnection 方法发出连接请求时，
			//DriverManager 将检查每个驱动程序，查看它是否可以建立连接。
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
	
	public static void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}
3、访问数据库进行CRUD操作，对数据库操作的接口类一般都位于java.sql包和javax.sql包下。
数据库连接被用于向数据库服务器发送命令和SQL语句。
常用的数据库操作接口类：
DriverManager：
public class DriverManager extends Object
管理一组 JDBC 驱动程序的基本服务。
在调用 getConnection 方法时，DriverManager 会试着从初始化时加载的那些驱动程序以及使用与当前 applet 或应用程序相同的类加载器显式加载的那些驱动程序中查找合适的驱动程序。
Connection：
public interface Connection extends Wrapper
与特定数据库的连接（会话）。在连接上下文中执行 SQL 语句并返回结果。
注：在配置 Connection 时，JDBC 应用程序应该使用适当的 Connection 方法，比如 setAutoCommit 或 setTransactionIsolation。在有可用的 JDBC 方法时，应用程序不能直接调用 SQL 命令更改连接的配置。默认情况下，Connection 对象处于自动提交模式下，这意味着它在执行每个语句后都会自动提交更改。如果禁用了自动提交模式，那么要提交更改就必须显式调用 commit 方法；否则无法保存数据库更改。
Statement：
public interface Statement extends Wrapper
用于执行静态 SQL 语句并返回它所生成结果的对象。
Statement 对象用来将 SQL 语句发送到数据库。不带参数的 SQL 语句通常使用 Statement 对象执行。如果多次执行相同的 SQL 语句，使用 PreparedStatement 对象可能更有效。
PreparedStatement:
public interface PreparedStatement extends Statement
表示预编译的 SQL 语句的对象。 
SQL 语句被预编译并存储在 PreparedStatement 对象中。然后可以使用此对象多次高效地执行该语句。因此多次执行的 SQL 语句经常创建为 PreparedStatement 对象，以提高效率。 
PreparedStatement pstmt = con.prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?"); 
pstmt.setBigDecimal(1, 153833.00) 
pstmt.setInt(2, 110592)
resultset rs = pstmt.executequery();
ResultSet:
public interface ResultSet extends Wrapper
表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。 
ResultSet 对象具有指向其当前数据行的光标。最初，光标被置于第一行之前。next 方法将光标移动到下一行；因为该方法在 ResultSet 对象没有下一行时返回 false，所以可以在 while 循环中使用它来迭代结果集。','',''),
(dbo.CreateOrderID(),'什么是C#编程语言明明白白学C#','2015/01/27 10:08','ssa122','ssa','什么是C#编程语言明明白白学C#

C#是微软公司发布的一门面向对象的编程语言。它作为一门语言，则具备语言的四个要素。并且，它符合编程语言的四个要素的特性。同时，它作为面向对象的编程语言，可以解决各种复杂、大型的问题本文选自明明白白学C#大学霸。

C#语言另外一个重要特点是，它是基于Microsoft .NET Framework的编程语言。Microsoft .NET Framework简单理解是一个软件。也就是说，如果要在电脑上执行C#编写的程序，就首先需要在电脑上安装该软件。同时，微软官方针对C#还提供开发工具Visual Studio。C#、.NET Framework和Visual Studio形成微软开发的套装本文选自明明白白学C#','',''),
(dbo.CreateOrderID(),'PHP-什么是PHP?为什么用PHP?有谁在用PHP?','2015/12/12 21:55','yjj','凌洛','什么是 PHP？
PHP 是一种脚本语言， "PHP Hypertext Preprocessor" 的首字母缩略词 PHP 语法吸收了C语言、Java和Perl的特点，利于学习，使用广泛，主要适用于Web开发领域.
PHP 是由Rasmus Lerdorf于1995年创建的, 1997年以色列的两位工程师Zeev Suraski 和 Andi Gutmans，重写了 PHP 的剖析器。 PHP 是一种被广泛使用的开源脚本语言
PHP 脚本在服务器上执行
PHP 是开源的，没有成本，可供免费下载和使用
PHP 是一门令人惊叹的流行语言，是网络上最大的博客系统的核心（WordPress), 足以运行最大的社交网络（facebook）, 它的易用程度足以成为初学者的首选服务器端语言！

什么是 PHP 文件？
PHP 文件的后缀是 ".php"
PHP 文件能够包含文本、HTML、CSS 以及 PHP 代码
PHP 代码在服务器上执行，而结果以纯文本返回浏览器

PHP 能够做什么？
PHP 是服务端脚本语言，主要功能就是编写网站程序或接口程序
PHP 能够生成动态页面内容
PHP 能够提供接口，供Android和iOS平台调用
PHP 能够创建、打开、读取、写入、删除以及关闭服务器上的文件
PHP 能够接收表单数据
PHP 能够向浏览器发送并取回cookies, 能够在服务器存储和读取Session
PHP 能够与数据库配合，完成添加、删除、修改数据库中的数据
PHP 能够限制用户访问网站中的某些页面
PHP 能够对数据进行加密
通过 PHP，您可以不受限于只输出 HTML。您还能够输出图像、PDF 文件、甚至 Flash 影片。您也可以输出任何文本，比如 XHTML/XML/JSON。

为什么使用 PHP？
PHP 运行于各种平台（Windows, Linux, Unix, Mac OS X 等）
PHP 兼容几乎所有服务器（Nginx, Apache, IIS 等）
PHP 支持多种数据库, 比如:MySQL/MariaDB, SQLite, MongoDB, ProgresSQL, Redis等
PHP 是开源的、免费的。请从官方 PHP 资源下载：www.php.net
PHP 易于学习，并可高效地运行在服务器端
PHP 是目前最全球最流行的服务器端语言(截止到2015年有81.7%的网站在使用)

谁在使用 PHP ?
国外的有Facebook、维基百科等
国内的有腾讯、百度、阿里巴巴、新浪、微博、凤凰、网易等

我们可以看 w3techs.com 网站的统计，在网站的服务器端，使用PHP的占81.7%, 使用ASP.NET的占16.1%, 使用Java 的占3.0%, 使用静态文件(static files )的 占 1.6%, 剩下的使用 ColdFusion 0.7%, Ruby 0.6%, Perl 0.5%, Python 0.2%, JavaScript 0.2%, Erlang 0.1%, Miva Script 0.1%。（数据来自 W3Techs.com, 12 December 2015)

Usage of server-side programming languages for websites
PHP is used by 81.7% of all the websites whose server-side programming language we know.
http://w3techs.com/technologies/overview/programming_language/all

为什么很多人说PHP不适合开发大型网站？
这是很老的说法，现在已经不靠谱。现在PHP在安全、性能等方面已经有了很大的提高，尤其是PHP7的发布，PHP已经有了质的飞越。已经有很多的大型网站都是使用了PHP开发的。在网站做大做强的过程中，也不在单纯只依赖一种语言，而使多种语言配合使用，比如某公司的网站的后台管理系统和接口使用PHP开发,脚本采用Python开发,页面采用JavaScript调取HTTP接口的方式, 客户端使用Java(Android平台)和Objective-C(iOS平台)等。

(1) 说PHP没有命名空间: PHP在已经PHP5.3中引入了命名空间
(2) 不一致的函数命名规则: 这个是由于历史原因造成的， 如果是处女座的那就没办法了。
(3) PHP缺少标准框架: PHP框架众多，你可以选择一个合适的框架，或者自己写一个。
(4) 安全性要求极高的网站不适合，你可以打开一些银行的网站和移动联通的网站，他们都是用JSP语言开发：
个人感觉安全性和编程语言关系不是很大，到是和开发人员的关系很大。借用贴吧网友的一句话"不是程序适不适合搞大网站，而是你自己合不合适做大网站"。
PHP主要还是Nginx/Apache、MySQL/Redis/MongoDB等配合的好一些。
一个网站使用什么语言看的是他们技术总监会什么语言。
招商银行、携程使用的是ASP.NET。

PHP是最好的语言吗?
哈哈，笑笑就得了，何必这么认真呢？没有最好的语言，只有最合适的语言。','',''),
(dbo.CreateOrderID(),'详解cookie、 sessionStorage 和localStorage','2018-01-08 23:07:00','zjj','夏红豆','1.cookie:存储在用户本地终端上的数据。有时也用cookies，指某些网站为了辨别用户身份，进行session跟踪而存储在本地终端上的数据，通常经过加密。一般应用最典型的案列就是判断注册用户是否已经登过该网站。

2.HTML5 提供了两种在客户端存储数据的新方法：(http://www.w3school.com.cn/html5/html_5_webstorage.asp)...两者都是仅在客户端（即浏览器）中保存，不参与和服务器的通信；

localStorage - 没有时间限制的数据存储,第二天、第二周或下一年之后，数据依然可用。
如何创建和访问 localStorage：
<script type="text/javascript">
localStorage.lastname="Smith";
document.write(localStorage.lastname);
</script>
下面的例子对用户访问页面的次数进行计数：

1 <script type="text/javascript">
2 if (localStorage.pagecount){
3   localStorage.pagecount=Number(localStorage.pagecount) +1;
4   }
5 else{
6   localStorage.pagecount=1;
7   }
8 document.write("Visits "+ localStorage.pagecount + " time(s).");
9 </script>
sessionStorage - 针对一个 session 的数据存储,当用户关闭浏览器窗口后，数据会被删除。
创建并访问一个 sessionStorage：
<script type="text/javascript">
  sessionStorage.lastname="Smith";
  document.write(sessionStorage.lastname);
</script>
下面的例子对用户在当前 session 中访问页面的次数进行计数：

1 <script type="text/javascript">
2 if (sessionStorage.pagecount){
3   sessionStorage.pagecount=Number(sessionStorage.pagecount) +1;
4   }
5 else{
6   sessionStorage.pagecount=1;
7   }
8 document.write("Visits "+sessionStorage.pagecount+" time(s) this session.");
9 </script>
sessionStorage 、localStorage 和 cookie 之间的区别
共同点：都是保存在浏览器端，且同源的。

区别：cookie数据始终在同源的http请求中携带（即使不需要），即cookie在浏览器和服务器间来回传递；cookie数据还有路径（path）的概念，可以限制cookie只属于某个路径下。存储大小限制也不同，cookie数据不能超过4k，同时因为每次http请求都会携带cookie，所以cookie只适合保存很小的数据，如会话标识。

而sessionStorage和localStorage不会自动把数据发给服务器，仅在本地保存。sessionStorage和localStorage 虽然也有存储大小的限制，但比cookie大得多，可以达到5M或更大。

数据有效期不同，sessionStorage：仅在当前浏览器窗口关闭前有效，自然也就不可能持久保持；localStorage：始终有效，窗口或浏览器关闭也一直保存，因此用作持久数据；cookie只在设置的cookie过期时间之前一直有效，即使窗口或浏览器关闭。

作用域不同，sessionStorage不在不同的浏览器窗口中共享，即使是同一个页面；localStorage 在所有同源窗口中都是共享的；cookie也是在所有同源窗口中都是共享的。Web Storage 支持事件通知机制，可以将数据更新的通知发送给监听者。Web Storage 的 api 接口使用更方便。','','')


select * from posts

--4.评论表comment详细信息
if OBJECT_ID('comment')is not null
drop table comment
create table comment
(
	c_no int primary key identity(1,1),--评论编号
	c_cid varchar(12) not null,--帖子ID
	c_ctext varchar(255) not null,--评论内容
	c_uaccount varchar(6) not null,--发帖人
	c_caccount varchar(6),--回帖人
	c_ctime datetime not null--回帖时间
)
insert comment(c_cid,c_ctext,c_uaccount,c_caccount,c_ctime)
values('201907240001','非常有帮助，先Mark一下！','枫兮兮','ssa122','2019-5-10');

select * from comment

--5.用户组表usertype 详细信息	
if OBJECT_ID('usertype')is not null
drop table usertype
create table usertype
(
	utype_no int identity(1,1) primary key,
	utype_id int not null,--用户组ID
	utype_name varchar(20) not null,--用户组名称
	utype_privilege varchar(100) not null,--用户组权限
	utype_desc varchar(100) not null,--用户组简述
	
)
insert usertype
values(18,'普通用户组','浏览、点赞、收藏、订阅、发表','完成注册并实名认证后即可成为'),
(28,'资深用户组','浏览、点赞、收藏、订阅、发表,申请/被推荐进版务组','积分达到15000后自动升级'),
(48,'会员用户组','可购买优质源码以及阅读优秀作者文章','购买会员后自动获得'),
(88,'开发用户组','发布源码、参与网站新功能内测','需申请成为开发者'),
(255,'官方人员组','全站最高权限','仅限官方人员')

select * from usertype

--6.点赞表forgood详细信息
if OBJECT_ID('forgood')is not null
drop table forgood
create table forgood
(
	fg_no int identity(1,1),--点赞编号
	fg_account varchar(6) not null,--用户账号
	fg_pid varchar(8) not null--帖子ID
)

select * from forgood

--7.用户扩展信息表userinfo详细信息
if OBJECT_ID('userinfo')is not null
drop table userinfo
create table userinfo
(
	u_account varchar(10) not null primary key,--用户账号
	u_email varchar(50) not null,--用户邮箱
	u_nick varchar(8) not null,--用户昵称
	u_points int not null default(0),--用户积分
	ifidinfo int not null,--是否完成实名认证
	u_group int not null,--用户所在用户组
	u_state int not null,--是否可用状态
	u_regtime datetime not null,--注册时间
	u_birthday datetime not null,	--用户生日
	u_sex varchar(6) check(u_sex='男' or u_sex='女') default('男'),--用户性别
	u_idinfo varchar(8),			--实名认证码
	u_name varchar(8),				--真实姓名
	u_constellation varchar(6),		--星座
	u_tel varchar(11) not null,		--手机号码
	u_idcard varchar(18) not null,	--证件号码
	u_career varchar(20) not null,	--职业
	u_dev varchar(100) not null,	--擅长领域
	u_university varchar(40),		--毕业院校
	u_qq varchar(11),				--QQ账号
	u_wechat varchar(10),			--微信号
	u_weibo varchar(20),			--微博号
	u_remark text,					--个人简介
	u_pic varchar(50) not null      --照片
)

insert userinfo(u_account,u_email,u_nick,u_points,ifidinfo,u_group,u_state,
u_regtime,u_birthday,u_sex,u_idinfo,u_name,u_constellation,u_tel,u_idcard,u_career,
u_dev,u_university,u_qq,u_wechat,u_weibo,u_remark)
values('bha','1234567@qq.com','安华萧萧','2555','1','255',
'1',GETDATE(),GETDATE(),'男','33092111','宾华安','巨蟹座',
'13455532211','440921199902311234','计算机/互联网','JAVA、SQLSERVER',
'暨南大学教育学院','34221234','bha123','bha123','我很牛逼！','bha.png'),
('yjj','1234567@qq.com','凌洛','5555','1','255',
'1',GETDATE(),GETDATE(),'男','33092112','杨俊杰','巨蟹座',
'13422532211','420921199912314234','计算机/互联网','CSharp、SQLSERVER',
'暨南大学教育学院','1134510293','ydw123','jackson123','我更牛逼！','yjj.png'),
('sjk','1234567@qq.com','白衣','1555','1','255',
'1',GETDATE(),GETDATE(),'男','33092113','舒健凯','巨蟹座',
'13433332211','42032119990231122X','计算机/互联网','JAVA、PHP',
'暨南大学教育学院','22311441','sjk123','sjk123','我最牛逼！','sjk.png'),
('zjj','1233@qq.com','夏红豆','11555','1','255',
'1',GETDATE(),GETDATE(),'男','33092114','张家杰','双子座',
'13433332211','42032119990220333X','计算机/互联网','CSharp、Python',
'广东科贸学院','22331441','zjj123','zjj123','上面的都是傻逼','zjj.png'),
('chh','233@163.com','Robin','555','1','255',
'1',GETDATE(),GETDATE(),'男','33092116','陈洪汉','白羊座',
'13433332211','42032119990220333X','计算机/互联网','CSharp、Python',
'暨南大学教育学院','33333333','chh123','chh123','此人比较懒，没有简介','chh.png'),
('ssa122','yjj@gmail.com','ssa','955','1','88',
'1',GETDATE(),GETDATE(),'男','33092118','asds','摩羯座',
'19933332211','42032119890220333X','计算机/互联网','PHP、Python、JAVA、CSharp、Go、JQuery',
'清华大学信息工程学院','999999','ssa123','ssa123','我最tm牛逼！我骄傲','ssa.png'),
('klq122','sdda@gmail.com','dddd','11955','1','48',
'1',GETDATE(),GETDATE(),'男','33092119','qewe','射手座',
'19933332211','42032119890225333X','计算机/互联网','全能',
'麻省理工学院','999999','fff123','aaa123','我最tm牛逼！我骄傲','klq.png')

select * from users
select * from userinfo

select * from userinfo ui,usertype ut where ui.u_group=ut.utype_id


--8.实名认证表 idinfo详细信息
if OBJECT_ID('idinfo')is not null
drop table idinfo
create table idinfo
(
	info_no int identity(1001,1) primary key,--认证编号
	info_code int not null,--实名认证码
	info_name varchar(8) not null,--身份证名字
	info_number varchar(18) not null,--证件号码
	info_position varchar(255) not null,--正面所在路径
	info_positionb varchar(255) not null,--反面所在路径
	ifo_address varchar(255),--居住地
)

select * from idinfo


go

if OBJECT_ID('port')is not null
drop table port
create table port
(
	port_no varchar(15) primary key,--反馈编号
	port_account varchar(12) not null,--反馈人
	port_type varchar(12) not null,--反馈类型
	port_text varchar(8000) not null,--反馈内容
	port_time datetime not null,--反馈时间
	ifsuccess bit not null,--反馈处理状态
	port_plate varchar(6) Default('PL1009') not null --所属板块

)



--9.吐槽反馈表port详细信息
if OBJECT_ID('port')is not null
drop table port
create table port
(
	port_no varchar(15) primary key,--反馈编号
	port_account varchar(6) not null,--反馈人
	port_type varchar(12) not null,--反馈类型
	port_text varchar(8000) not null,--反馈内容
	port_time datetime not null,--反馈时间
	ifsuccess bit not null,--反馈处理状态
	port_plate varchar(6) Default('PL1009') not null --所属板块

)
drop function dbo.CreatePortID

create function dbo.CreatePortID()  
returns decimal(12,0)  
as  
begin  
 DECLARE @id decimal(12,0)  
 DECLARE @Date varchar(8)  
  
 --获取当前帖子的帖子ID
 select @id = max(port_no) from port  
   
 --获取当前日期XXXXXXXX如20010101  
 select @Date = CONVERT(varchar(8), GETDATE(),112)  
  
 select @id = @Date + case when charindex(@Date, @id) = 1 then   
 Cast(right(Cast(Right(@id,4) as decimal) + 1 + 10000,4) as varchar(12))   
 else '0001' end  
    return @id  
end  
go
insert into port values(dbo.CreatePortID(),'ssa122','网站建议','希望尽快上线版务委员会，加油！',GETDATE(),'1','PL1009'),
(dbo.CreatePortID(),'mma152','网站建议','希望资讯更新再努把力，紧跟国外开发资讯！',GETDATE(),'0','PL1009'),
(dbo.CreatePortID(),'moe153','给个好评','太有意思了！爱死这网站了',GETDATE(),'0','PL1009'),
(dbo.CreatePortID(),'kkk992','内容投诉','有些帖子太水啦，望能严查，维护社区秩序！',GETDATE(),'1','PL1009'),
(dbo.CreatePortID(),'kkp566','用户投诉','举报账号为：lbw29，水水水',GETDATE(),'0','PL1009')

select * from port


--10.粉丝表 fans详细信息
if OBJECT_ID('fans')is not null
drop table fans
create table fans
(
	
)

--11.论坛帖子回复表bbs_reply
if OBJECT_ID('bbs_reply')is not null
drop table bbs_reply
create table bbs_reply
(
	id int identity(1,1) not null,--ID标识
	topicid int not null, --主题ID
	title varchar(300),--标题
	body varchar(255),--回复内容
	utype_id int, --回复用户ID
	modifiedon datetime, --最后编辑时间
	createon datetime,--创建时间
)
select * from bbs_reply

--12.论坛附件表bbs_attachment
if OBJECT_ID('bbs_attachment')is not null
drop table bbs_attachment
create table bbs_attachment
(
	id int identity(1,1) not null,--ID标识
	topicid int, --帖子ID
	name varchar(200),--附件名称
	filepath varchar(510),--附件地址
	point int, --下载所需积分
	descrption varchar(2000), --描述
	utype_id int,--投票者ID
	counts int,--附件被下载的总次数
	isenabled bit,--是否启用
	createon datetime --上传时间
)
select * from bbs_attachment

--13.知识表knowledge
if OBJECT_ID('knowledge')is not null
drop table knowledge
create table knowledge
(
	k_id int identity(1001,1) primary key,
	k_name varchar(30) not null,
	k_price int not null,
	k_title varchar(50) not null,
	k_remark text not null
)

insert knowledge(k_name,k_price,k_title,k_remark)
values('Java',7,'Java高并发之魂：synchronized深度解析','高并发问题向来是Java程序员进阶的重点，也是面试的难点。想要打通高并发的奇经八脉，synchronized是你不得不趟过的坑，本课程从synchronized，从使用方法到底层原理源码，娓娓道来。'),
('Shiro',10,'Shiro安全框架入门','本课程通过从shiro和spring Security对比入手，进而讲解Shiro整体架构以及认证，授权的过程，通过结合代码带同学更深刻的认识Shiro。同时课程会讲解Shiro其他特性，比如：Session管理，缓存管理等，以及集成Spring在实际项目中应用Shiro。'),
('Python',12,'Python3入门机器学习','用新版python3语言和流行的scikit-learn框架，算法与编程两翼齐飞，由浅入深，一步步的进入机器学习的世界。学到的不只是一门课程，更是不断思考的能力在实际应用的基础上，更进一步深入探讨如何在项目中选择最合适的算法分析不同算法间的优缺点'),
('前端',15,'前端性能优化-通用的缓存SDK','性能优化成神之路的必看之课，是前端性能优化基础简介的后续篇章，作为一门前端性能优化的进阶课程，本课程介绍了常用的性能优化方法，以及老师自己在工作中摸索出来的，并且在大厂使用的课程。主要介绍造成性能优化dom时候，主要碰到的重绘以及回流问题。 '),
('TypeScript',15,'前端下一代开发语言TypeScript 从基础到axios实战','TypeScript是前端的发展趋势，但是很多同学却缺少实战经验，而且市面上缺少TypeScript 的实战课程。使用TypeScript一步步去实现一个完整的axios JS库，然后进行完整的单元测试，最后把开发的JS 库打包并发布到npm上')

select * from knowledge

delete from knowledge where k_id = '1007'

--14、用户认证状态表
if OBJECT_ID('ifstate')is not null
drop table ifstate
create table ifstate
(
	is_sid int primary key,
	is_sname varchar(20) not null
)

insert into ifstate values('0','禁用');

insert into ifstate values('1','启用');


--15、用户登录轨迹表
if OBJECT_ID('logininfo')is not null
drop table logininfo
create table logininfo
(
	lg_id int identity(1,1) primary key,
	lg_account varchar(20) not null,
	lg_info varchar(100) not null,
	lg_ip varchar(20) not null,
	lg_address varchar(100) not null
)

select * from logininfo where lg_account = 'sjk123'

--16、知识购买清单表
if OBJECT_ID('buy')is not null
drop table buy
create table buy
(
	b_id int identity(1,1) primary key,
	b_orderid varchar(50) not null,
	b_account varchar(50) not null,
	b_title varchar(250) not null,
	b_text varchar(500) not null,
	b_price varchar(100) not null,
)

insert into buy values(?,?,?,?,?,?)

select * from buy

--17、会员详情表
if OBJECT_ID('vipinfo')is not null
drop table vipinfo
create table vipinfo
(
	v_id int identity(1,1) primary key,
	v_name varchar(50) not null,
	v_date varchar(20) not null,
	v_text varchar(500) not null,
	v_price varchar(50) not null
)

select * from vipinfo

insert into vipinfo values('TCODE月会员','/月','月卡会员可以享受签到、发帖、评论、反馈积分额外加30分，购买后将加30积分','30');
insert into vipinfo values('TCODE年会员','/年','年卡会员可以享受签到、发帖、评论、反馈积分额外加30分，购买后将加60积分','60');
insert into vipinfo values('TCODE永久会员','/永久','永久会员可以享受签到、发帖、评论、反馈积分额外加30分，购买后将加120积分','120');

--18、会员购买清单表
if OBJECT_ID('vip_buy')is not null
drop table vip_buy
create table vip_buy
(
	vb_id int identity(1,1) primary key,
	vb_orderid varchar(50) not null,
	vb_account varchar(20) not null,
	vb_title varchar(20) not null,
	vb_text varchar(500) not null,
	vb_price varchar(100) not null,
)

select * from vip_buy

select * from buy

--19、会员激活码表







