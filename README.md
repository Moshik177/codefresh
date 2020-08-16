the project is java base project using testNG,maven and OOP

this project have 3 tests:
1. api test
2. cli test
3. ui test

same scenario:
1. create pipeline with dammay freestyle echo hello world
2. run the pipeline
3. check that the build passed

to run the tests you need to download the jar file at the target folder and
run from the commandline the command : java -jar codefresh-1.0-SNAPSHOT.jar -t ${testName}
api test name: apiPipelineTest
cli test name: cliPipelineTest
ui test name: seleniumPipelineTest
you can also create the jar with maven - run from your IDE the command mvn clean install 

i used picocli lib and reflaction to run the tests from outside ,i created dynamically testNG suite and add testNG listener to mark test as passed or failed i also added logger, when you run a test it's will create folder that contain the log and testng folder logs and xmls(see that at user\codefresh\${timestump})

for the api i used the library rest assured to create http requests and gson to serialize and deserialize responses
for the cli i used native jave process to create differnt thread and get the output result also i get some help from jackson library to upload a spec ymal file,
i add the the codefresh cli file as part of the resources both for windows and linux
for the ui i used selenium with page object methodology, i also add an option at the future to create different browsers(currently i used chrome as baseline)
the test of the selenium is not done, need to handle the template yaml better (put the build part as commant) 

for the second part i add chromedriver and codefresh cli file both for linux to handle at case i use a docker,
all i need to do is:
1.link this githab to get the latest changes, 
2.run maven command to create a jar (mvn clean install)
3.create and build a docker image (for selenium test i needed dokcer with chrome installed, and also linux with java for the others)
3.after that using commandline java -jar with the test that we want
