# Space-Debris
![](http://i.imgur.com/YSuepB6.png)

Steps to configure code on eclipse 

1.Import the project named  SpaceDebris as Java project and others as android project 

2.Change package name to any (eg. com.spaceman.spaceman)

 ![](http://i.imgur.com/qd87jXi.png)

3.Visit https://developers.facebook.com/  and register as developer if you are not.

4. Create an application on facebook developer console , visit this https://developers.facebook.com/docs/apps/register for reference.

5. Configure app id in string.xml file in project 
   ![](http://i.imgur.com/t9jzwBE.png)
6.Fill all the necessary detials in setting and app details section in fb developer console.

7.Copy the namespace from setting section and paste it in Mainactivity.java file line number -3803,3804,3817
![](http://i.imgur.com/h8akzOF.png)
![](http://i.imgur.com/otuPBID.png) 

7.Navigate to "Open Graph Story" click on add Custom story , then add "Ask" and "live" object and click on Create story.
![](http://i.imgur.com/ZHOCegD.png)

8.CLick on action and then click ask 
![](http://i.imgur.com/DGQEDmx.png)

then select Tags and explicitly shared option and save it.
![](http://i.imgur.com/UKl4AYV.png)

8.Navigate to role section , add any one  facebook account to developer (this is for testing purpose), then he/she need to aprove from developer console
![](http://i.imgur.com/XZvpb3v.png)


9.Build the project and run , then login with your facebook id 
10.Then after loosing life you will redirect to no life screen, click on ask life then select friends(which you added as developer-eg- Nitesh saha) which you added as developers in Role section then click on send.
10. Then 
