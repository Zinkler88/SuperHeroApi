SuperHeroApi


Postman API testing 





Automation Steps:

 Create EC2 instance

 Install jenkins
    Jenkins Pipeline on Amazon EC2 :   http://3.13.34.182:8080/


 Build docker image
    docker build -f Dockerfile -t superhero-api .
 Run image
    docker run -p 8082



https://cloud.docker.com/repository/docker/zinkler88/superhero-api




 optional
 install mongo instance or mongo image on EC2


 Tricks

 usermod -aG docker jenkins
 usermod -aG root jenkins
 chmod 664 /var/run/docker.sock


 chmod 777 /var/run/docker.sock


 
 
 
 


