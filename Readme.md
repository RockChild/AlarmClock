#Simple alarm application

It has 2 pages: setting page and alarm page.
You can see current time on the page and set alarm. After setting the alarm time, page is reloading every 29 seconds(to avoid time in the end of minutes which can produce not correct reaction) to recheck if time is reched. When alarm time is coming, user is redirected to 'alarm-page' and sound is going off. 

Considering that it will not enough time to dive in QTP and VB was added few Selenide(selenium wrapper) tests covering basic functionality: initial checking of elements on page, verifying triggering alarm after enetering valid time, after some delay and checking invalid values habdling(not triggering)

Because of the lack of time CI steps weren't done. Maven is used as build automation tool.
Tests are added as a part of application instead of separated module, what would be better solution, but again, because of the lack of the time. In this case on CI server should be configured few tasks/jobs which would dependent on results of each other:
* `<mvn install -Dmvn.test.skip=true>` - it will validate, compile and package code in a .war file,skipping tests because we can't run tests before app would be deployed to repository
* `<mvn tomcat7:run-war>` - starting server and deploying the war file into repository
* `<mvn test>` - run UI tests.

In case if application would be constructed as multimoduled project, it would be possible to configure a build script which will run every steps by order and Jenkins should be configured by less steps.

After little time of exploring information about QTP I found that the usual scenario is to record/write tests and to create a bat file which will execute VB test scripts.