Cake Manager Micro Service (fictitious)
=======================================

A summer intern started on this project but never managed to get it finished.
The developer assured us that some of the above is complete, but at the moment accessing the /cakes endpoint
returns a 404, so getting this working should be the first priority.

Requirements:
* By accessing the root of the server (/) it should be possible to list the cakes currently in the system. This must be presented in an acceptable format for a human to read.

* It must be possible for a human to add a new cake to the server.

* By accessing an alternative endpoint (/cakes) with an appropriate client it must be possible to download a list of
the cakes currently in the system as JSON data.

* The /cakes endpoint must also allow new cakes to be created.

Comments:
* We feel like the software stack used by the original developer is quite outdated, it would be good to migrate the entire application to something more modern.
* Would be good to change the application to implement proper client-server separation via REST API.


Steps to run the application
=====================

1) build images using
    'mvn spring-boot:build-image'
    'docker build -t waracle/cake-manager .'

2) Run the image using
    'docker run -p 8282:8282 waracle/cake-manager'

3) check container running using 'docker ps'

4) to stop application: docker stop <<container id from step3>>

Endpoints:
==========

1) List cakes in human readable format:

   GET http://localhost:8282

   returns webpage which shows Cakes information.

2) List cakes in json format:

   GET http://localhost:8282/v1/cakes

   return list cakes with following structure

   '[{
    title: "Lemon cheesecake",
    desc: "A cheesecake made of lemon",
    image: "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
    }...]'

3) Add new cakes

    POST http://localhost:8282/v1/cakes

    Json Payload:

        {
        	"title" : "FootballCake",
        	"description" : "football cake...",
        	"image": "https://test.cakestoday.co.uk/media/catalog/product/cache/7b3d987532f4d3dc572695e5d1995d5b/i/m/img_2083-min.jpg"
        }

    Response:
        200 OK


Change log:
==========

1) I updated the project latest spring boot application.

2) added root level endpoint to display cakes in human readable format

3) upgraded rest endpoints and make sure it works as expected.

4) Add docker file to make it containerization.


Bonus points:
* Tests => i used mockito and web mvc test for unit test cases, for IT test, i use wiremock or cucumber but not implemented as part of this exercise.
* Authentication via OAuth2 => i added dependency in pom file and required code snippets/configs(client_id/client_secret) to authenticate user using oauth2 provider but commented out.
* Continuous Integration via any cloud CI system : I used Jenkins/Gitlab to make service CI/CD pipelines. we can configure shell script/job to execute when repository events is triggered.
* Containerisation : used docker
