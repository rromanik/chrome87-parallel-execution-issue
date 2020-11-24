# chrome87-parallel-execution-issue
This is an example code that reproduces the problem with test execution in Chrome v87.0.4280.66.  
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
- ChromeDriver v.87.0.4280.20  
and wasn't observed for Google Chrome 86 and earlier.

To reproduce the issue, run:
```
mvn clean test -Dtestng.dtd.http=true
```

