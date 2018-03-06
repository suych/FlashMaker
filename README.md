欢迎来到A的代码实验室！
========
在这里我们将探索奇妙的代码世界！<br>
我会在这里开发一些有趣的小工具，帮助他人更快速的完成高质量的代码编写，当然，这对于我自己而言也是一个提高。
大部分项目或工具都是以Java或者JavaScript为主。

1.FlashMaker/闪电代码生成器
--------
这是我们的第一个小工具，用来快速生成和Oracle数据库表相关的领域建模代码(即DO模型的Java代码)。<br>
采用Java来编写，使用Spring+Mybatis的经典MVC架构。<br>

环境变量配置如下：<br>

    JDK版本：1.8.0_65
    Tomcat版本：8.5
    是否Maven：是
    基础框架：spring4.1.6+mybatis3.4.1
    数据库：Oracle11g

使用方法：<br>

    1.将代码下载至本地workspace
    2.配置Pom.xml文件，接通你的Maven库
    3.配置jdbc.properties文件，保证工程可以连接到你的数据库
    4.constant.properties文件，生成的文件将保存在该路径下
    5.发布至Tomcat，并启动工程
    6.打开浏览器输入url：
        http://ip:port/FlashMaker/domainobject/generate/数据库中的表名/本地包名.do
      例如我的地址:
        http://localhost:8080/FlashMaker/domainobject/generate/dbm_data/org.suych.do
    其中，数据库中的表名就是你要生成领域建模的表名，本地包名对应生成Java文件的本地包名（可以为空，生成文件后你再自行填写即可）。
    7.生成完毕后，控制台会提示(-------mission completed-------)
    8.在第4步中你配置的文件路径下查看生成的Java文件吧！

这个工具会越来越强大，但目前而言仅仅会生成DO代码:)<br>
让我们拭目以待吧，祝你在这里玩的开心！
