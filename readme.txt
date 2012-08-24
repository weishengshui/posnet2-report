The posnet2-report project.

Struts-2.1.8.1
Spring-2.5.6
Hibernate-3.3.1.GA
Jrebel-5.0
Jboss-5.1

javarebel-maven-plugin is default closed in project root pom.xml,Comment out the JRebel plugin before you commit!

You should add some parameters to Jboss5.1 VM-arguments:
-noverify -Drebel.hibernate_plugin=true -Drebel.struts2-plugin=true 
-Drebel.spring_plugin=true -Drebel.aspectj_plugin=true