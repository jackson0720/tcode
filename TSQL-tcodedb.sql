use master
if DB_ID('tcodedb')is not null
drop database tcodedb
create database tcodedb on
(
	name='tcodedb.mdf',
	filename='D:\ʵս��Ŀ��\T-SKY��Դ��̳\7��19������ģ��\db\tcodedb.mdf'
)

use tcodedb
--1.�û�������Ϣ�� user ��ϸ��Ϣ
if OBJECT_ID('users')is not null
drop table users
create table users
(
	u_account varchar(10) not null primary key,--�û��˺�
	u_password varchar(20) not null,--�û�����
	u_email varchar(50) not null,--�û�����
	u_IP varchar(20),--���һ�ε�¼IP
	u_state int not null,--�Ƿ����
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


--2.����plate��ϸ��Ϣ
if OBJECT_ID('plate')is not null
drop table plate
create table plate
(
	p_no varchar(6) not null primary key,--�����
	p_name varchar(30) not null,--�������
	p_time datetime,--��齨��ʱ��
	p_account varchar(6)--������
)
insert plate(p_no,p_name,p_time,p_account)
values('PL1001','�������',GETDATE(),'Yh1002'),
('PL1002','WebӦ��',GETDATE(),'Yh1002'),
('PL1003','�ƶ�����',GETDATE(),'Yh1002'),
('PL1004','��ҵӦ��',GETDATE(),'Yh1002'),

('PL1005','��Դ����',GETDATE(),'Yh1002'),

('PL1006','�������',GETDATE(),'Yh1004'),
('PL1007','��ҵ��Ѷ',GETDATE(),'Yh1004'),

('PL1008','��������',GETDATE(),'Yh1003'),

('PL1009','�����²�',GETDATE(),'Yh1003')

select * from plate

--3.���ӱ�posts��ϸ��Ϣ
if OBJECT_ID('posts')is not null
drop table posts
create table posts
(
	p_pid varchar(15) not null primary key,--����ID	
	p_ptheme varchar(50) not null,--���ӱ���
	p_ptime datetime not null,--���ӷ���ʱ��
	p_paccount varchar(20)not null,--�������˺�
	p_pnick varchar(16), --�������ǳ�
	p_ptext varchar(8000) not null,--��������
	p_pgood int not null default(0),--���ӵ�����
	p_pplate varchar(30) not null--�������
)
--ɾ������
drop function dbo.CreateOrderID
--��������
create function dbo.CreateOrderID()  
returns decimal(12,0)  
as  
begin  
 DECLARE @id decimal(12,0)  
 DECLARE @Date varchar(8)  
  
 --��ȡ��ǰ���ӵ�����ID
 select @id = max(p_pid) from posts  
   
 --��ȡ��ǰ����XXXXXXXX��20010101  
 select @Date = CONVERT(varchar(8), GETDATE(),112)  
  
 select @id = @Date + case when charindex(@Date, @id) = 1 then   
 Cast(right(Cast(Right(@id,4) as decimal) + 1 + 10000,4) as varchar(12))   
 else '0001' end  
    return @id  
end  

insert posts(p_pid,p_ptheme,p_ptime,p_paccount,p_pnick,p_ptext,p_pgood,p_pplate)
values(dbo.CreateOrderID(),'[Java] JAVA��JVM����ԭ��','2014/03/12 10:54','bha','yssy','JVM��java�ĺ��ĺͻ�������java��������osƽ̨֮������⴦����������һ�������������ʵ�ֵĳ���ļ���������²�Ĳ���ϵͳ��Ӳ��ƽ̨������������ִ��java���ֽ������
�����������Java����ת��Class�ļ�,����Jvm����ȡ��.
Jvm��java�����,��ʵ�����ǽ�����,��Class�ļ��е�����ת��ĳ��ƽ̨������,�����Java����ת��Windows�µ�����,Ȼ��Java�����ִ����.
����ʹ�Ҽ򵥷���һ��JAVA��JVM���е�ԭ��Java����д��Դ����ͨ��Java���������������ƽ̨�޹صġ��ֽ������(.class�ļ���Ҳ����0��1�����Ƴ���)��Ȼ����OS֮�ϵ�Java�������н���ִ�У���JVM��java�ĺ��ĺͻ�������java��������osƽ̨֮������⴦����
JAVA��JVM���е�ԭ��
1.Java�������еĹ���
Java����д��Դ����ͨ��Java���������������ƽ̨�޹صġ��ֽ������(.class�ļ���Ҳ����0��1�����Ƴ���)��Ȼ����OS֮�ϵ�Java�������н���ִ�С�
Java�������еĹ���
Ҳ�൱��Java�������еĹ���
ע��JVM��java���������������������ͬ��JDK���������ͬ�ģ���������ͬ��
2.JVM��
JVM��java�ĺ��ĺͻ�������java��������osƽ̨֮������⴦����������һ�������������ʵ�ֵĳ���ļ���������²�Ĳ���ϵͳ��Ӳ��ƽ̨������������ִ��java���ֽ������
java������ֻҪ����JVM������JVM�����Ĵ�����ֽ����ļ���JavaԴ�ļ���������ֽ������ͨ��JVM��ÿһ��ָ���ɲ�ͬƽ̨�����룬ͨ���ض�ƽ̨���С�
JVMִ�г���Ĺ��� ��
I.���ء�class�ļ�
II.���������ڴ�
III.ִ�������ռ�
JRE��java����ʱ��������JVM�����java��������л��� ','',''),
(dbo.CreateOrderID(),'ʲô��Java���ԣ�java���Լ��','2010-10-19 09:04:00','chh','Robin','Java����Sun Microsystems��˾��1995��5���Ƴ���Java����������ԣ����¼��Java���ԣ���Javaƽ̨���ܳơ���Javaʵ�ֵ�HotJava�������֧��Java applet����ʾ��Java����������ƽ̨����̬��Web��Internet���㡣�Ӵˣ�Java���㷺���ܲ��ƶ���Web��Ѹ�ٷ�չ�����õ���������ھ�֧��Java applet����һ���棬Java����Ҳ���ϸ��¡�
Javaƽ̨��Java�������Java Virtual Machine����Java Ӧ�ñ�̽ӿڣ�Application Programming Interface�����API�����ɡ�Java Ӧ�ñ�̽ӿ�ΪJavaӦ���ṩ��һ�������ڲ���ϵͳ�ı�׼�ӿڣ��ɷ�Ϊ�������ֺ���չ���֡���Ӳ�������ϵͳƽ̨�ϰ�װһ��Javaƽ̨֮��JavaӦ�ó���Ϳ����С�����Javaƽ̨�Ѿ�Ƕ���˼������еĲ���ϵͳ������Java�������ֻ����һ�Σ��Ϳ����ڸ���ϵͳ�����С�JavaӦ�ñ�̽ӿ��Ѿ���1.1x�淢չ��1.2�档Ŀǰ���õ�Javaƽ̨����Java1.4������汾ΪJava1.6��
Java��Ϊ������ϵJ2SE(Java2 Standard Edition)��J2EE(Java 2 Platform,Enterprise Edition)��J2ME(Java 2 Micro Edition)��
Java������һ��֧��������������������������ԡ�Java����������Smalltalk���Ժ�C++���Ե��ŵ㣬���������������ԣ���֧�ֲ���������ơ�����ͨ�š��Ͷ�ý�����ݿ��Ƶȡ���Ҫ�������£�
1��Java�����Ǽ򵥵ġ�Java���Ե��﷨��C���Ժ�C++���Ժܽӽ���ʹ�ô��������Ա������ѧϰ��ʹ��Java����һ���棬Java������C++ �к���ʹ�õġ��������ġ������Ի����Щ���ԣ�����������ء���̳С��Զ���ǿ������ת�����ر�أ�Java���Բ�ʹ��ָ�룬���ṩ���Զ��ķ����ռ���ʹ�ó���Ա����Ϊ�ڴ��������ǡ�
2��Java������һ���������ġ�Java�����ṩ�ࡢ�ӿںͼ̳е�ԭ�Ϊ�˼������ֻ֧����֮��ĵ��̳У���֧�ֽӿ�֮��Ķ�̳У���֧������ӿ�֮���ʵ�ֻ��ƣ��ؼ���Ϊimplements����Java����ȫ��֧�ֶ�̬�󶨣���C++ ����ֻ���麯��ʹ�ö�̬�󶨡���֮��Java������һ����������������������ԡ�
3��Java�����Ƿֲ�ʽ�ġ�Java����֧��InternetӦ�õĿ������ڻ�����JavaӦ�ñ�̽ӿ�����һ������Ӧ�ñ�̽ӿڣ�java.net�������ṩ����������Ӧ�ñ�̵���⣬����URL��URLConnection��Socket�� ServerSocket�ȡ�Java��RMI(Զ�̷�������)����Ҳ�ǿ����ֲ�ʽӦ�õ���Ҫ�ֶΡ�
4��Java�����ǽ�׳�ġ�Java��ǿ���ͻ��ơ��쳣�������ϵ��Զ��ռ�����Java����׳�Ե���Ҫ��֤����ָ��Ķ�����Java������ѡ��Java�İ�ȫ������ʹ��Java���߽�׳�ԡ�
5��Java�����ǰ�ȫ�ġ�Javaͨ�����������绷���У�Ϊ�ˣ�Java�ṩ��һ����ȫ�����Է��������Ĺ���������Java���Ծ��е���లȫ�������⣬Java��ͨ���������ص������һ����ȫ�������ƣ���ClassLoader��������䲻ͬ�����ֿռ��Է�������ص�ͬ���ࡢ�ֽڴ����飬���ṩ��ȫ������ƣ���SecurityManager����JavaӦ�����ð�ȫ�ڱ���
6��Java��������ϵ�ṹ�����ġ�Java���򣨺�׺Ϊjava���ļ�����Javaƽ̨�ϱ�����Ϊ��ϵ�ṹ�������ֽ����ʽ����׺Ϊclass���ļ���, Ȼ�������ʵ�����Javaƽ̨���κ�ϵͳ�����С�����;���ʺ����칹�����绷��������ķַ���
7��Java�����ǿ���ֲ�ġ����ֿ���ֲ����Դ����ϵ�ṹ�����ԣ����⣬Java���ϸ�涨�˸��������������͵ĳ��ȡ�Javaϵͳ����Ҳ���к�ǿ�Ŀ���ֲ�ԣ�Java����������Javaʵ�ֵģ�Java�����л�������ANSI Cʵ�ֵġ�
8��Java�����ǽ����͵ġ���ǰ������Java������Javaƽ̨�ϱ�����Ϊ�ֽ����ʽ�� Ȼ�������ʵ�����Javaƽ̨���κ�ϵͳ�����С�������ʱ��Javaƽ̨�е�Java����������Щ�ֽ�����н���ִ�У�ִ�й�������Ҫ���������ӽ׶α����뵽���л����С�
9��Java�Ǹ����ܵġ�����Щ�����͵ĸ߼��ű�������ȣ�Java��ȷ�Ǹ����ܵġ���ʵ�ϣ�Java�������ٶ�����JIT(Just-In-Time)�����������ķ�չԽ��Խ�ӽ���C++��
10��Java�����Ƕ��̵߳ġ���Java�����У��߳���һ������Ķ�����������Thread������ӣ������������ͨ�������ַ����������̣߳���һ��ʹ���͹�ΪThread(Runnable) �Ĺ����ӽ�һ��ʵ����Runnable�ӿڵĶ����װ��һ���̣߳��������Thread�����������ಢ��дrun������ʹ�ø����ഴ���Ķ���Ϊ�̡߳�ֵ��ע�����Thread���Ѿ�ʵ����Runnable�ӿڣ���ˣ��κ�һ���߳̾�������run��������run�����а������߳���Ҫ���еĴ��롣�̵߳Ļ��һ�鷽�������ơ� Java����֧�ֶ���̵߳�ͬʱִ�У����ṩ���߳�֮���ͬ�����ƣ��ؼ���Ϊsynchronized����
11��Java�����Ƕ�̬�ġ�Java���Ե����Ŀ��֮һ����Ӧ�ڶ�̬�仯�Ļ�����Java������Ҫ�����ܶ�̬�ر����뵽���л�����Ҳ����ͨ����������������Ҫ���ࡣ��Ҳ��������������������⣬Java�е�����һ������ʱ�̵ı�ʾ���ܽ�������ʱ�̵����ͼ�顣
Java���Ե���������ʹ��JavaӦ�þ����ޱȵĽ�׳�ԺͿɿ��ԣ���Ҳ������Ӧ��ϵͳ��ά�����á�Java�Զ�������ȫ��֧�ֺ�Javaƽ̨��Ƕ��API������Ӧ��ϵͳ�Ŀ���ʱ�䲢���ͳɱ���Java�ı���һ�Σ����������е�����ʹ�����ܹ��ṩһ���洦���õĿ��Žṹ���ڶ�ƽ̨֮�䴫����Ϣ�ĵͳɱ���ʽ���ر���Java��ҵӦ�ñ�̽ӿڣ�Java Enterprise APIs��Ϊ��ҵ���㼰��������Ӧ��ϵͳ�ṩ���йؼ����ͷḻ����⡣','',''),
(dbo.CreateOrderID(),'JAVA�߳�8 - ThreadLocal','2014/02/26 15:59','klq122','dddd','һ��ThreadLocal���
ThreadLocal������һ��Thread������Thread�ľֲ������� 
��ʹ��ThreadLocalά������ʱ��ThreadLocalΪÿ��ʹ�øñ������߳��ṩ�����ı�������������ÿһ���̶߳����Զ����ظı��Լ��ĸ�����������Ӱ�������߳�����Ӧ�ĸ����� 
����Ϊʲô�����ThreadLocal��
��java���̲߳������ʹ������ʱ��Ϊ���̰߳�ȫ�����ǵ����������ǶԶ���̷߳��ʽ���ͬ�����ơ����������ή��ϵͳ�����ܡ���JDK 1.2�İ汾�о��ṩjava.lang.ThreadLocal�࣬��Ϊÿһ���̶߳�ά����һ��������ÿ���̶߳������Լ������ı����󣬾�û���˲����µ��̰߳�ȫ���⡣ThreadLocal�����Կռ�����ȡ�̰߳�ȫ��һ�ֲ��ԡ� 
����ThreadLocalԭ��
ThreadLocalΪÿһ���߳�ά�������ĸ���ʵ��˼·�� 
��ThreadLocal������һ��Map�����ڴ洢ÿһ���̵߳ı���������Map��Ԫ�صļ�Ϊ�̶߳��󣬶�ֵ��Ӧ�̵߳ı��������� ',1,''),
(dbo.CreateOrderID(),'JDBC��Java Data Base Connectivity,java���ݿ����ӣ�','2012/06/03 21:00 ','sjk','����','JDBC��һ������ִ��SQL����Java API������Ϊ���ֹ�ϵ���ݿ��ṩͳһ���ʣ�����һ����Java���Ա�д����ͽӿ���ɡ�

Java���ݿ�������ϵ�ṹ������JavaӦ�ó����������ݿ�ı�׼������JDBC��Java����Ա������API����ʵ�������ݿ����ӵķ����ṩ�̶����ǽӿ�ģ�͡���ΪAPI��JDBCΪ���򿪷��ṩ��׼�Ľӿڣ���Ϊ���ݿ⳧�̼��������м������ʵ�������ݿ�������ṩ�˱�׼������

ͨ��һ�����ݿ⳧�����Ƴ��Լ������ݿ��Ʒ��ʱ�򶼻��ṩһ�׷������ݿ��API����ЩAPI�����ø������Ե���ʽ�ṩ���ͻ���Ӧ�ó��������ЩAPI���������ݿ⡣ÿһ�������ṩ��API������ͬ��������ʹ��ĳһ���ض������ݿ�ĳ�������ֲ����һ�����ݿ��ϡ�JDBC��Java�����ȡ�����ݿ⳧�̵�ר��API���ͻ���ֻ��Ҫ����JDBC API����JDBC�������򣨵��������ݿ⳧��ʵ��Java JDBC��׼��������ض���ĳһ���ݿ������API��ȥ���������ݿ��ͨ�š�

Ӧ�ã�

ʹ��ĳ�����ݿ�־û�����Ҫ������Ӧ�����ݿ���������
��Ҫ���ݿ�JDBC������������
SQL Server��com.microsoft.jdbc.sqlserver.SQLServerDriver
MySQL:com.mysql.jdbc.Driver
Oracle:oracle.jdbc.driver.OracleDriver

1����������һ��Ҫ���ز�ע�����ݿ����������������ַ�ʽ����������һ�㣺
     �١�Class.forName("JDBC��������") //ͨ��������Ƽ���ע������������
     �ڡ�System.setProperty("jdbc.driver","JDBC��������");//����ϵͳ����ָ�����ݿ�����
     �ۡ�DriverManager.registerDriver(new com.mysql.jdbc.Driver());

     �ܽ᣺�Ƽ��٣��͢����ַ�ʽ��
     ԭ�򣺢��ڱ���ʱ��Ҫ�����Ӧ��lib���٣��ڲ���Ҫ��
     �ڢ��ַ�ʽ�Ļ�������ͬʱ������jdbc�������м���ð�š������ֿ�
     ����System.setProperty("jdbc.drivers","XXXDriver:XXXDriver:XXXDriver");
     ������һ��ע�����������ݿ�����

     �ڵ���Class.forName(��XXXDriver��)ʱ������˽����������������JDBC API������������DriverManager
     ��ע�ᣬ�� ע�᷽�����๹�����ǰ��ɣ�һ��ʹ�þ�̬���飬
     ͨ���鿴MySQL������ʵ��com.mysql.jdbc.Driver����Կ���

static {
  	try {
		java.sql.DriverManager.registerDriver(new Driver());
	} catch (SQLException E) {
		throw new RuntimeException("Cant register driver!");
	}
}

     �ڵ���DriverManager��getConnection����ʱ��һ��������ע��������в��ҿ����˽��URL�������� 
     Ȼ����ø�������connect�������Ӷ��������ӣ����ص����Ӷ���һ��ʵ��java.sql.Connection�ӿڵľ����ࡣ
     JDBC��׼�е�Driver�ӿڣ�
     JDBCDriverInterface

     �����κ����ݿ��ṩ�̵����������ʵ�ֵĽӿڣ����������ʵ�ָýӿ��е����з�����

     MySQL������DriverԴ�룺 

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
2����DriverManager�еĵ�һ��DBMS���ӣ�DriverManager��������JDBC����㣡���������Դ������ӣ�
     �Ӷ���ɺ����Ĳ���������DriverManager���getConnection(String url, String user, String pwd)������
     ���ݿ�����ӣ�����һ�����Ӷ���

     ������url��
     SQL Server ��jdbc:microsoft:sqlserver://localhost:1443;databasename=���ݿ���
     Oracle ��jdbc:oracle:thin:@localhost:1521:ORCL(���ݿ�ʵ����)
     MySQL ��jdbc:mysql://localhost:3306:/databasename
     Jdbc-odbc�� ��jdbc:odbc:test(testΪodbc����Դ����)

public class DBUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/spring";
	private static String user = "root";
	private static String pwd = "mysql5";
	private static Connection conn = null;
	
	static {
		try {
			//ʹ��Class.forName()���ز�ע��JDBC��������
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			//���� Driver �ಢ�� DriverManager ����ע������Ǽ������������ݿ⽨�����ӡ�
			//������ DriverManager.getConnection ����������������ʱ��
			//DriverManager �����ÿ���������򣬲鿴���Ƿ���Խ������ӡ�
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
3���������ݿ����CRUD�����������ݿ�����Ľӿ���һ�㶼λ��java.sql����javax.sql���¡�
���ݿ����ӱ����������ݿ���������������SQL��䡣
���õ����ݿ�����ӿ��ࣺ
DriverManager��
public class DriverManager extends Object
����һ�� JDBC ��������Ļ�������
�ڵ��� getConnection ����ʱ��DriverManager �����Ŵӳ�ʼ��ʱ���ص���Щ���������Լ�ʹ���뵱ǰ applet ��Ӧ�ó�����ͬ�����������ʽ���ص���Щ���������в��Һ��ʵ���������
Connection��
public interface Connection extends Wrapper
���ض����ݿ�����ӣ��Ự������������������ִ�� SQL ��䲢���ؽ����
ע�������� Connection ʱ��JDBC Ӧ�ó���Ӧ��ʹ���ʵ��� Connection ���������� setAutoCommit �� setTransactionIsolation�����п��õ� JDBC ����ʱ��Ӧ�ó�����ֱ�ӵ��� SQL ����������ӵ����á�Ĭ������£�Connection �������Զ��ύģʽ�£�����ζ������ִ��ÿ�����󶼻��Զ��ύ���ġ�����������Զ��ύģʽ����ôҪ�ύ���ľͱ�����ʽ���� commit �����������޷��������ݿ���ġ�
Statement��
public interface Statement extends Wrapper
����ִ�о�̬ SQL ��䲢�����������ɽ���Ķ���
Statement ���������� SQL ��䷢�͵����ݿ⡣���������� SQL ���ͨ��ʹ�� Statement ����ִ�С�������ִ����ͬ�� SQL ��䣬ʹ�� PreparedStatement ������ܸ���Ч��
PreparedStatement:
public interface PreparedStatement extends Statement
��ʾԤ����� SQL ���Ķ��� 
SQL ��䱻Ԥ���벢�洢�� PreparedStatement �����С�Ȼ�����ʹ�ô˶����θ�Ч��ִ�и���䡣��˶��ִ�е� SQL ��侭������Ϊ PreparedStatement ���������Ч�ʡ� 
PreparedStatement pstmt = con.prepareStatement("UPDATE EMPLOYEES SET SALARY = ? WHERE ID = ?"); 
pstmt.setBigDecimal(1, 153833.00) 
pstmt.setInt(2, 110592)
resultset rs = pstmt.executequery();
ResultSet:
public interface ResultSet extends Wrapper
��ʾ���ݿ����������ݱ�ͨ��ͨ��ִ�в�ѯ���ݿ��������ɡ� 
ResultSet �������ָ���䵱ǰ�����еĹ�ꡣ�������걻���ڵ�һ��֮ǰ��next ����������ƶ�����һ�У���Ϊ�÷����� ResultSet ����û����һ��ʱ���� false�����Կ����� while ѭ����ʹ�����������������','',''),
(dbo.CreateOrderID(),'ʲô��C#������������װ�ѧC#','2015/01/27 10:08','ssa122','ssa','ʲô��C#������������װ�ѧC#

C#��΢��˾������һ���������ı�����ԡ�����Ϊһ�����ԣ���߱����Ե��ĸ�Ҫ�ء����ң������ϱ�����Ե��ĸ�Ҫ�ص����ԡ�ͬʱ������Ϊ�������ı�����ԣ����Խ�����ָ��ӡ����͵����Ȿ��ѡ�������װ�ѧC#��ѧ�ԡ�

C#��������һ����Ҫ�ص��ǣ����ǻ���Microsoft .NET Framework�ı�����ԡ�Microsoft .NET Framework�������һ�������Ҳ����˵�����Ҫ�ڵ�����ִ��C#��д�ĳ��򣬾�������Ҫ�ڵ����ϰ�װ�������ͬʱ��΢��ٷ����C#���ṩ��������Visual Studio��C#��.NET Framework��Visual Studio�γ�΢��������װ����ѡ�������װ�ѧC#','',''),
(dbo.CreateOrderID(),'PHP-ʲô��PHP?Ϊʲô��PHP?��˭����PHP?','2015/12/12 21:55','yjj','����','ʲô�� PHP��
PHP ��һ�ֽű����ԣ� "PHP Hypertext Preprocessor" ������ĸ���Դ� PHP �﷨������C���ԡ�Java��Perl���ص㣬����ѧϰ��ʹ�ù㷺����Ҫ������Web��������.
PHP ����Rasmus Lerdorf��1995�괴����, 1997����ɫ�е���λ����ʦZeev Suraski �� Andi Gutmans����д�� PHP ���������� PHP ��һ�ֱ��㷺ʹ�õĿ�Դ�ű�����
PHP �ű��ڷ�������ִ��
PHP �ǿ�Դ�ģ�û�гɱ����ɹ�������غ�ʹ��
PHP ��һ�����˾�̾���������ԣ������������Ĳ���ϵͳ�ĺ��ģ�WordPress), �������������罻���磨facebook��, �������ó̶����Գ�Ϊ��ѧ�ߵ���ѡ�����������ԣ�

ʲô�� PHP �ļ���
PHP �ļ��ĺ�׺�� ".php"
PHP �ļ��ܹ������ı���HTML��CSS �Լ� PHP ����
PHP �����ڷ�������ִ�У�������Դ��ı����������

PHP �ܹ���ʲô��
PHP �Ƿ���˽ű����ԣ���Ҫ���ܾ��Ǳ�д��վ�����ӿڳ���
PHP �ܹ����ɶ�̬ҳ������
PHP �ܹ��ṩ�ӿڣ���Android��iOSƽ̨����
PHP �ܹ��������򿪡���ȡ��д�롢ɾ���Լ��رշ������ϵ��ļ�
PHP �ܹ����ձ�����
PHP �ܹ�����������Ͳ�ȡ��cookies, �ܹ��ڷ������洢�Ͷ�ȡSession
PHP �ܹ������ݿ���ϣ������ӡ�ɾ�����޸����ݿ��е�����
PHP �ܹ������û�������վ�е�ĳЩҳ��
PHP �ܹ������ݽ��м���
ͨ�� PHP�������Բ�������ֻ��� HTML�������ܹ����ͼ��PDF �ļ������� Flash ӰƬ����Ҳ��������κ��ı������� XHTML/XML/JSON��

Ϊʲôʹ�� PHP��
PHP �����ڸ���ƽ̨��Windows, Linux, Unix, Mac OS X �ȣ�
PHP ���ݼ������з�������Nginx, Apache, IIS �ȣ�
PHP ֧�ֶ������ݿ�, ����:MySQL/MariaDB, SQLite, MongoDB, ProgresSQL, Redis��
PHP �ǿ�Դ�ġ���ѵġ���ӹٷ� PHP ��Դ���أ�www.php.net
PHP ����ѧϰ�����ɸ�Ч�������ڷ�������
PHP ��Ŀǰ��ȫ�������еķ�����������(��ֹ��2015����81.7%����վ��ʹ��)

˭��ʹ�� PHP ?
�������Facebook��ά���ٿƵ�
���ڵ�����Ѷ���ٶȡ�����Ͱ͡����ˡ�΢������ˡ����׵�

���ǿ��Կ� w3techs.com ��վ��ͳ�ƣ�����վ�ķ������ˣ�ʹ��PHP��ռ81.7%, ʹ��ASP.NET��ռ16.1%, ʹ��Java ��ռ3.0%, ʹ�þ�̬�ļ�(static files )�� ռ 1.6%, ʣ�µ�ʹ�� ColdFusion 0.7%, Ruby 0.6%, Perl 0.5%, Python 0.2%, JavaScript 0.2%, Erlang 0.1%, Miva Script 0.1%������������ W3Techs.com, 12 December 2015)

Usage of server-side programming languages for websites
PHP is used by 81.7% of all the websites whose server-side programming language we know.
http://w3techs.com/technologies/overview/programming_language/all

Ϊʲô�ܶ���˵PHP���ʺϿ���������վ��
���Ǻ��ϵ�˵���������Ѿ������ס�����PHP�ڰ�ȫ�����ܵȷ����Ѿ����˺ܴ����ߣ�������PHP7�ķ�����PHP�Ѿ������ʵķ�Խ���Ѿ��кܶ�Ĵ�����վ����ʹ����PHP�����ġ�����վ������ǿ�Ĺ����У�Ҳ���ڵ���ֻ����һ�����ԣ���ʹ�����������ʹ�ã�����ĳ��˾����վ�ĺ�̨����ϵͳ�ͽӿ�ʹ��PHP����,�ű�����Python����,ҳ�����JavaScript��ȡHTTP�ӿڵķ�ʽ, �ͻ���ʹ��Java(Androidƽ̨)��Objective-C(iOSƽ̨)�ȡ�

(1) ˵PHPû�������ռ�: PHP���Ѿ�PHP5.3�������������ռ�
(2) ��һ�µĺ�����������: �����������ʷԭ����ɵģ� ����Ǵ�Ů�����Ǿ�û�취�ˡ�
(3) PHPȱ�ٱ�׼���: PHP����ڶ࣬�����ѡ��һ�����ʵĿ�ܣ������Լ�дһ����
(4) ��ȫ��Ҫ�󼫸ߵ���վ���ʺϣ�����Դ�һЩ���е���վ���ƶ���ͨ����վ�����Ƕ�����JSP���Կ�����
���˸о���ȫ�Ժͱ�����Թ�ϵ���Ǻܴ󣬵��ǺͿ�����Ա�Ĺ�ϵ�ܴ󡣽����������ѵ�һ�仰"���ǳ����ʲ��ʺϸ����վ���������Լ��ϲ�����������վ"��
PHP��Ҫ����Nginx/Apache��MySQL/Redis/MongoDB����ϵĺ�һЩ��
һ����վʹ��ʲô���Կ��������Ǽ����ܼ��ʲô���ԡ�
�������С�Я��ʹ�õ���ASP.NET��

PHP����õ�������?
������ЦЦ�͵��ˣ��α���ô�����أ�û����õ����ԣ�ֻ������ʵ����ԡ�','',''),
(dbo.CreateOrderID(),'���cookie�� sessionStorage ��localStorage','2018-01-08 23:07:00','zjj','�ĺ춹','1.cookie:�洢���û������ն��ϵ����ݡ���ʱҲ��cookies��ָĳЩ��վΪ�˱���û���ݣ�����session���ٶ��洢�ڱ����ն��ϵ����ݣ�ͨ���������ܡ�һ��Ӧ������͵İ��о����ж�ע���û��Ƿ��Ѿ��ǹ�����վ��

2.HTML5 �ṩ�������ڿͻ��˴洢���ݵ��·�����(http://www.w3school.com.cn/html5/html_5_webstorage.asp)...���߶��ǽ��ڿͻ��ˣ�����������б��棬������ͷ�������ͨ�ţ�

localStorage - û��ʱ�����Ƶ����ݴ洢,�ڶ��졢�ڶ��ܻ���һ��֮��������Ȼ���á�
��δ����ͷ��� localStorage��
<script type="text/javascript">
localStorage.lastname="Smith";
document.write(localStorage.lastname);
</script>
��������Ӷ��û�����ҳ��Ĵ������м�����

1 <script type="text/javascript">
2 if (localStorage.pagecount){
3   localStorage.pagecount=Number(localStorage.pagecount) +1;
4   }
5 else{
6   localStorage.pagecount=1;
7   }
8 document.write("Visits "+ localStorage.pagecount + " time(s).");
9 </script>
sessionStorage - ���һ�� session �����ݴ洢,���û��ر���������ں����ݻᱻɾ����
����������һ�� sessionStorage��
<script type="text/javascript">
  sessionStorage.lastname="Smith";
  document.write(sessionStorage.lastname);
</script>
��������Ӷ��û��ڵ�ǰ session �з���ҳ��Ĵ������м�����

1 <script type="text/javascript">
2 if (sessionStorage.pagecount){
3   sessionStorage.pagecount=Number(sessionStorage.pagecount) +1;
4   }
5 else{
6   sessionStorage.pagecount=1;
7   }
8 document.write("Visits "+sessionStorage.pagecount+" time(s) this session.");
9 </script>
sessionStorage ��localStorage �� cookie ֮�������
��ͬ�㣺���Ǳ�����������ˣ���ͬԴ�ġ�

����cookie����ʼ����ͬԴ��http������Я������ʹ����Ҫ������cookie��������ͷ����������ش��ݣ�cookie���ݻ���·����path���ĸ����������cookieֻ����ĳ��·���¡��洢��С����Ҳ��ͬ��cookie���ݲ��ܳ���4k��ͬʱ��Ϊÿ��http���󶼻�Я��cookie������cookieֻ�ʺϱ����С�����ݣ���Ự��ʶ��

��sessionStorage��localStorage�����Զ������ݷ��������������ڱ��ر��档sessionStorage��localStorage ��ȻҲ�д洢��С�����ƣ�����cookie��ö࣬���Դﵽ5M�����

������Ч�ڲ�ͬ��sessionStorage�����ڵ�ǰ��������ڹر�ǰ��Ч����ȻҲ�Ͳ����ܳ־ñ��֣�localStorage��ʼ����Ч�����ڻ�������ر�Ҳһֱ���棬��������־����ݣ�cookieֻ�����õ�cookie����ʱ��֮ǰһֱ��Ч����ʹ���ڻ�������رա�

������ͬ��sessionStorage���ڲ�ͬ������������й�����ʹ��ͬһ��ҳ�棻localStorage ������ͬԴ�����ж��ǹ���ģ�cookieҲ��������ͬԴ�����ж��ǹ���ġ�Web Storage ֧���¼�֪ͨ���ƣ����Խ����ݸ��µ�֪ͨ���͸������ߡ�Web Storage �� api �ӿ�ʹ�ø����㡣','','')


select * from posts

--4.���۱�comment��ϸ��Ϣ
if OBJECT_ID('comment')is not null
drop table comment
create table comment
(
	c_no int primary key identity(1,1),--���۱��
	c_cid varchar(12) not null,--����ID
	c_ctext varchar(255) not null,--��������
	c_uaccount varchar(6) not null,--������
	c_caccount varchar(6),--������
	c_ctime datetime not null--����ʱ��
)
insert comment(c_cid,c_ctext,c_uaccount,c_caccount,c_ctime)
values('201907240001','�ǳ��а�������Markһ�£�','������','ssa122','2019-5-10');

select * from comment

--5.�û����usertype ��ϸ��Ϣ	
if OBJECT_ID('usertype')is not null
drop table usertype
create table usertype
(
	utype_no int identity(1,1) primary key,
	utype_id int not null,--�û���ID
	utype_name varchar(20) not null,--�û�������
	utype_privilege varchar(100) not null,--�û���Ȩ��
	utype_desc varchar(100) not null,--�û������
	
)
insert usertype
values(18,'��ͨ�û���','��������ޡ��ղء����ġ�����','���ע�Ტʵ����֤�󼴿ɳ�Ϊ'),
(28,'�����û���','��������ޡ��ղء����ġ�����,����/���Ƽ���������','���ִﵽ15000���Զ�����'),
(48,'��Ա�û���','�ɹ�������Դ���Լ��Ķ�������������','�����Ա���Զ����'),
(88,'�����û���','����Դ�롢������վ�¹����ڲ�','�������Ϊ������'),
(255,'�ٷ���Ա��','ȫվ���Ȩ��','���޹ٷ���Ա')

select * from usertype

--6.���ޱ�forgood��ϸ��Ϣ
if OBJECT_ID('forgood')is not null
drop table forgood
create table forgood
(
	fg_no int identity(1,1),--���ޱ��
	fg_account varchar(6) not null,--�û��˺�
	fg_pid varchar(8) not null--����ID
)

select * from forgood

--7.�û���չ��Ϣ��userinfo��ϸ��Ϣ
if OBJECT_ID('userinfo')is not null
drop table userinfo
create table userinfo
(
	u_account varchar(10) not null primary key,--�û��˺�
	u_email varchar(50) not null,--�û�����
	u_nick varchar(8) not null,--�û��ǳ�
	u_points int not null default(0),--�û�����
	ifidinfo int not null,--�Ƿ����ʵ����֤
	u_group int not null,--�û������û���
	u_state int not null,--�Ƿ����״̬
	u_regtime datetime not null,--ע��ʱ��
	u_birthday datetime not null,	--�û�����
	u_sex varchar(6) check(u_sex='��' or u_sex='Ů') default('��'),--�û��Ա�
	u_idinfo varchar(8),			--ʵ����֤��
	u_name varchar(8),				--��ʵ����
	u_constellation varchar(6),		--����
	u_tel varchar(11) not null,		--�ֻ�����
	u_idcard varchar(18) not null,	--֤������
	u_career varchar(20) not null,	--ְҵ
	u_dev varchar(100) not null,	--�ó�����
	u_university varchar(40),		--��ҵԺУ
	u_qq varchar(11),				--QQ�˺�
	u_wechat varchar(10),			--΢�ź�
	u_weibo varchar(20),			--΢����
	u_remark text,					--���˼��
	u_pic varchar(50) not null      --��Ƭ
)

insert userinfo(u_account,u_email,u_nick,u_points,ifidinfo,u_group,u_state,
u_regtime,u_birthday,u_sex,u_idinfo,u_name,u_constellation,u_tel,u_idcard,u_career,
u_dev,u_university,u_qq,u_wechat,u_weibo,u_remark)
values('bha','1234567@qq.com','��������','2555','1','255',
'1',GETDATE(),GETDATE(),'��','33092111','������','��з��',
'13455532211','440921199902311234','�����/������','JAVA��SQLSERVER',
'���ϴ�ѧ����ѧԺ','34221234','bha123','bha123','�Һ�ţ�ƣ�','bha.png'),
('yjj','1234567@qq.com','����','5555','1','255',
'1',GETDATE(),GETDATE(),'��','33092112','���','��з��',
'13422532211','420921199912314234','�����/������','CSharp��SQLSERVER',
'���ϴ�ѧ����ѧԺ','1134510293','ydw123','jackson123','�Ҹ�ţ�ƣ�','yjj.png'),
('sjk','1234567@qq.com','����','1555','1','255',
'1',GETDATE(),GETDATE(),'��','33092113','�潡��','��з��',
'13433332211','42032119990231122X','�����/������','JAVA��PHP',
'���ϴ�ѧ����ѧԺ','22311441','sjk123','sjk123','����ţ�ƣ�','sjk.png'),
('zjj','1233@qq.com','�ĺ춹','11555','1','255',
'1',GETDATE(),GETDATE(),'��','33092114','�żҽ�','˫����',
'13433332211','42032119990220333X','�����/������','CSharp��Python',
'�㶫��óѧԺ','22331441','zjj123','zjj123','����Ķ���ɵ��','zjj.png'),
('chh','233@163.com','Robin','555','1','255',
'1',GETDATE(),GETDATE(),'��','33092116','�º麺','������',
'13433332211','42032119990220333X','�����/������','CSharp��Python',
'���ϴ�ѧ����ѧԺ','33333333','chh123','chh123','���˱Ƚ�����û�м��','chh.png'),
('ssa122','yjj@gmail.com','ssa','955','1','88',
'1',GETDATE(),GETDATE(),'��','33092118','asds','Ħ����',
'19933332211','42032119890220333X','�����/������','PHP��Python��JAVA��CSharp��Go��JQuery',
'�廪��ѧ��Ϣ����ѧԺ','999999','ssa123','ssa123','����tmţ�ƣ��ҽ���','ssa.png'),
('klq122','sdda@gmail.com','dddd','11955','1','48',
'1',GETDATE(),GETDATE(),'��','33092119','qewe','������',
'19933332211','42032119890225333X','�����/������','ȫ��',
'��ʡ��ѧԺ','999999','fff123','aaa123','����tmţ�ƣ��ҽ���','klq.png')

select * from users
select * from userinfo

select * from userinfo ui,usertype ut where ui.u_group=ut.utype_id


--8.ʵ����֤�� idinfo��ϸ��Ϣ
if OBJECT_ID('idinfo')is not null
drop table idinfo
create table idinfo
(
	info_no int identity(1001,1) primary key,--��֤���
	info_code int not null,--ʵ����֤��
	info_name varchar(8) not null,--���֤����
	info_number varchar(18) not null,--֤������
	info_position varchar(255) not null,--��������·��
	info_positionb varchar(255) not null,--��������·��
	ifo_address varchar(255),--��ס��
)

select * from idinfo


go

if OBJECT_ID('port')is not null
drop table port
create table port
(
	port_no varchar(15) primary key,--�������
	port_account varchar(12) not null,--������
	port_type varchar(12) not null,--��������
	port_text varchar(8000) not null,--��������
	port_time datetime not null,--����ʱ��
	ifsuccess bit not null,--��������״̬
	port_plate varchar(6) Default('PL1009') not null --�������

)



--9.�²۷�����port��ϸ��Ϣ
if OBJECT_ID('port')is not null
drop table port
create table port
(
	port_no varchar(15) primary key,--�������
	port_account varchar(6) not null,--������
	port_type varchar(12) not null,--��������
	port_text varchar(8000) not null,--��������
	port_time datetime not null,--����ʱ��
	ifsuccess bit not null,--��������״̬
	port_plate varchar(6) Default('PL1009') not null --�������

)
drop function dbo.CreatePortID

create function dbo.CreatePortID()  
returns decimal(12,0)  
as  
begin  
 DECLARE @id decimal(12,0)  
 DECLARE @Date varchar(8)  
  
 --��ȡ��ǰ���ӵ�����ID
 select @id = max(port_no) from port  
   
 --��ȡ��ǰ����XXXXXXXX��20010101  
 select @Date = CONVERT(varchar(8), GETDATE(),112)  
  
 select @id = @Date + case when charindex(@Date, @id) = 1 then   
 Cast(right(Cast(Right(@id,4) as decimal) + 1 + 10000,4) as varchar(12))   
 else '0001' end  
    return @id  
end  
go
insert into port values(dbo.CreatePortID(),'ssa122','��վ����','ϣ���������߰���ίԱ�ᣬ���ͣ�',GETDATE(),'1','PL1009'),
(dbo.CreatePortID(),'mma152','��վ����','ϣ����Ѷ������Ŭ�������������⿪����Ѷ��',GETDATE(),'0','PL1009'),
(dbo.CreatePortID(),'moe153','��������','̫����˼�ˣ���������վ��',GETDATE(),'0','PL1009'),
(dbo.CreatePortID(),'kkk992','����Ͷ��','��Щ����̫ˮ���������ϲ飬ά����������',GETDATE(),'1','PL1009'),
(dbo.CreatePortID(),'kkp566','�û�Ͷ��','�ٱ��˺�Ϊ��lbw29��ˮˮˮ',GETDATE(),'0','PL1009')

select * from port


--10.��˿�� fans��ϸ��Ϣ
if OBJECT_ID('fans')is not null
drop table fans
create table fans
(
	
)

--11.��̳���ӻظ���bbs_reply
if OBJECT_ID('bbs_reply')is not null
drop table bbs_reply
create table bbs_reply
(
	id int identity(1,1) not null,--ID��ʶ
	topicid int not null, --����ID
	title varchar(300),--����
	body varchar(255),--�ظ�����
	utype_id int, --�ظ��û�ID
	modifiedon datetime, --���༭ʱ��
	createon datetime,--����ʱ��
)
select * from bbs_reply

--12.��̳������bbs_attachment
if OBJECT_ID('bbs_attachment')is not null
drop table bbs_attachment
create table bbs_attachment
(
	id int identity(1,1) not null,--ID��ʶ
	topicid int, --����ID
	name varchar(200),--��������
	filepath varchar(510),--������ַ
	point int, --�����������
	descrption varchar(2000), --����
	utype_id int,--ͶƱ��ID
	counts int,--���������ص��ܴ���
	isenabled bit,--�Ƿ�����
	createon datetime --�ϴ�ʱ��
)
select * from bbs_attachment

--13.֪ʶ��knowledge
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
values('Java',7,'Java�߲���֮�꣺synchronized��Ƚ���','�߲�������������Java����Ա���׵��ص㣬Ҳ�����Ե��ѵ㡣��Ҫ��ͨ�߲������澭������synchronized���㲻�ò��˹��Ŀӣ����γ̴�synchronized����ʹ�÷������ײ�ԭ��Դ�룬�渵�����'),
('Shiro',10,'Shiro��ȫ�������','���γ�ͨ����shiro��spring Security�Ա����֣���������Shiro����ܹ��Լ���֤����Ȩ�Ĺ��̣�ͨ����ϴ����ͬѧ����̵���ʶShiro��ͬʱ�γ̻ὲ��Shiro�������ԣ����磺Session�����������ȣ��Լ�����Spring��ʵ����Ŀ��Ӧ��Shiro��'),
('Python',12,'Python3���Ż���ѧϰ','���°�python3���Ժ����е�scikit-learn��ܣ��㷨����������ɣ���ǳ���һ�����Ľ������ѧϰ�����硣ѧ���Ĳ�ֻ��һ�ſγ̣����ǲ���˼����������ʵ��Ӧ�õĻ����ϣ�����һ������̽���������Ŀ��ѡ������ʵ��㷨������ͬ�㷨�����ȱ��'),
('ǰ��',15,'ǰ�������Ż�-ͨ�õĻ���SDK','�����Ż�����֮·�ıؿ�֮�Σ���ǰ�������Ż��������ĺ���ƪ�£���Ϊһ��ǰ�������Ż��Ľ��׿γ̣����γ̽����˳��õ������Ż��������Լ���ʦ�Լ��ڹ��������������ģ������ڴ�ʹ�õĿγ̡���Ҫ������������Ż�domʱ����Ҫ�������ػ��Լ��������⡣ '),
('TypeScript',15,'ǰ����һ����������TypeScript �ӻ�����axiosʵս','TypeScript��ǰ�˵ķ�չ���ƣ����Ǻܶ�ͬѧȴȱ��ʵս���飬����������ȱ��TypeScript ��ʵս�γ̡�ʹ��TypeScriptһ����ȥʵ��һ��������axios JS�⣬Ȼ����������ĵ�Ԫ���ԣ����ѿ�����JS ������������npm��')

select * from knowledge

delete from knowledge where k_id = '1007'

--14���û���֤״̬��
if OBJECT_ID('ifstate')is not null
drop table ifstate
create table ifstate
(
	is_sid int primary key,
	is_sname varchar(20) not null
)

insert into ifstate values('0','����');

insert into ifstate values('1','����');


--15���û���¼�켣��
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

--16��֪ʶ�����嵥��
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

--17����Ա�����
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

insert into vipinfo values('TCODE�»�Ա','/��','�¿���Ա��������ǩ�������������ۡ��������ֶ����30�֣�����󽫼�30����','30');
insert into vipinfo values('TCODE���Ա','/��','�꿨��Ա��������ǩ�������������ۡ��������ֶ����30�֣�����󽫼�60����','60');
insert into vipinfo values('TCODE���û�Ա','/����','���û�Ա��������ǩ�������������ۡ��������ֶ����30�֣�����󽫼�120����','120');

--18����Ա�����嵥��
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

--19����Ա�������







