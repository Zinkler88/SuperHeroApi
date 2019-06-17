node{
    stage('Scm checkout'){
        git 'https://github.com/Zinkler88/SuperHeroApi.git'
    }


    stage('Mvn Package'){
        def mvnHome = tool name: 'maven-3', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package"
    }
    stage('Build Docker Image'){
        sh 'docker build -t zinkler88/superhero-api .'
    }

    stage('Push Docker Image'){

        withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh 'docker login -u zinkler88 -p ${dockerHubPwd}'
        }
        sh 'docker push zinkler88/superhero-api'
    }
    stage('Run Container on Dev Server'){
        def dockerRun = 'docker run -d --rm -p 8080:8080 --name SuperHeroApi1 zinkler88/superhero-api'
        sshagent(['dev-server']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@10.0.0.61 ${dockerRun}"
     }
    }
}