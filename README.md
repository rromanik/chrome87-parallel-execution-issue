# chrome87-parallel-execution-issue
This is an example code that reproduces the problem with test execution in Google Chrome 87.0.4280.66.  
If tests are executed in parallel, some screenshots cannot be taken, and the following messages appear in console:  
```
[1606236424.381][SEVERE]: Timed out receiving message from renderer: 10.000
```
or
```
[1606236418.010][SEVERE]: Timed out receiving message from renderer: 10.000
[1606236418.011][WARNING]: screenshot failed, retrying timeout: Timed out receiving message from renderer: 10.000
```
The issue started reproducing with the following configuration:
- Google Chrome 87.0.4280.66
- ChromeDriver 87.0.4280.20  
and wasn't observed for Google Chrome 86 and earlier.

To reproduce the issue, run:
```
mvn clean test -Dtestng.dtd.http=true
```

**NOTE 1**
The issue is not reproduce if Chrome runs in the 'headless' mode. To run the tests with headless chrome,
 go to 'ChromeFactory.java' and uncomment line 18:
 ```
 options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
```
After that, run the same command `mvn clean test -Dtestng.dtd.ttp=true`

**NOTE 2**
The same test can be executed in Firefox browser. Just go to 'testng.xml', comment the chrome test
`<class name="nik.roma.test.TakeScreenshotChromeTest" />` and uncomment the firefox test
`<class name="nik.roma.test.TakeScreenshotFirefoxTest" />`
and run the same command `mvn clean test -Dtestng.dtd.http=true`

**Update**
I logged an issue https://bugs.chromium.org/p/chromedriver/issues/detail?id=3664&sort=-pri%20-id&q=&can=1
which turned out to be a duplicate of https://bugs.chromium.org/p/chromedriver/issues/detail?id=3657&sort=-pri%20-id&q=&can=1

