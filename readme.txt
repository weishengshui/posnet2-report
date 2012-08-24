The posnet2-report project.

1、third-party lib
Struts-2.1.8.1
Spring-2.5.6
Hibernate-3.3.1.GA

2、operating ambient
Jrebel-5.0
Jboss-5.1.0.GA
posnet2-ds.xml(It's a datasources connection-pool for Jboss)

3、javarebel-maven-plugin
javarebel-maven-plugin is default closed in project root pom.xml,Comment out the JRebel plugin before you commit!

4、Jboss5.1 VM-arguments
-noverify -Drebel.hibernate_plugin=true -Drebel.struts2-plugin=true 
-Drebel.spring_plugin=true -Drebel.aspectj_plugin=true